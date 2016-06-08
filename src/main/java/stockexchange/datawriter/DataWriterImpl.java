package stockexchange.datawriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import stockexchange.mapper.DateParser;
import stockexchange.model.entity.StockPriceEntity;
import stockexchange.model.to.StockPriceTo;
import stockexchange.repository.StockPriceRepository;

@Component
public class DataWriterImpl implements DataWriter {

	@Autowired
	private DataCSVReader dataCsvReader;
	@Autowired
	private StockPriceRepository stockPriceRepository;
	@Autowired
	private MapperFacade mapper;
	@Autowired
	private DateParser dateParser;
	@Autowired 
	private Validator validator;

	private static final Logger log4j = LogManager.getLogger(DataWriterImpl.class.getName());
	
	private final int compCodeIndex=0;
	private final int dateStringIndex=1;
	private final int priceStringIndex=2;
	
	@Override
	public void saveAllIntoDB() throws IOException {
		dataCsvReader.openFileReaders();
		String[] nextLine;
		int id = 0;
		stockPriceRepository.deleteAll();
		while ((nextLine = dataCsvReader.readLine()) != null) {
			try {
				saveOneLineIntoDB(++id, nextLine);
			} catch (ParseException e) {
				log4j.log(Level.forName("NOTICE", 150), "Warning! Row "+id+"cannot be write into DB! Wrong date Format.");
			}
		}
		dataCsvReader.closeReaders();
	}

	@Override
	public void clearStocksTable() {
		stockPriceRepository.deleteAll();
	}

	private void saveOneLineIntoDB(int id, String[] line) throws ParseException {
		if(validator.isLineCorrect(line)){
		String companyCode=line[compCodeIndex];
		Date data = dateParser.stringToDate(line[dateStringIndex]);
		BigDecimal price = new BigDecimal(line[priceStringIndex]);
		
		StockPriceEntity spe = mapper.map(new StockPriceTo(id, companyCode, data, price), StockPriceEntity.class);
		stockPriceRepository.save(spe);
		}
		else{
			log4j.log(Level.forName("NOTICE", 150), "Warning! Row "+id+" cannot be write into DB! Wrong input data.");
		}
	}

}
