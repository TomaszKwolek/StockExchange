package stockexchange.datawriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import stockexchange.dao.DataWriterDao;
import stockexchange.mapper.DateParser;
import stockexchange.model.entity.StockPriceEntity;
import stockexchange.model.to.StockPriceTo;
import stockexchange.repository.StockPriceRepository;

@Component
public class DataWriterImpl implements DataWriter {

	@Autowired
	private Parser parser;
	@Autowired
	private StockPriceRepository stockPriceRepository;
	@Autowired
	private MapperFacade mapper;
	@Autowired
	private DateParser dateParser;

	@Value(value = "#{simulationProperties['inputDataFilePath']}")
	String inputDataFilePath;

	@SuppressWarnings("resource")
	@Override
	public void saveAllIntoDB() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("src/main/resources/dane.csv"));
		String[] nextLine;
		int id = 1;
		String companyCode = "";
		Date data = new Date();
		BigDecimal price = new BigDecimal(0);
		while ((nextLine = reader.readNext()) != null) {
			try {
				saveIntoDB(id++, nextLine[0], dateParser.stringToDate(nextLine[1]), new BigDecimal(nextLine[2]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		saveIntoDB(2333, "dfdf", new Date(2013 - 02 -13), new BigDecimal(1));
	}

	@Override
	public void clearStocksTable() {
		stockPriceRepository.deleteAll();
	}

	private void saveIntoDB(int id, String companyCode, Date data, BigDecimal price) {
		StockPriceEntity spe = mapper.map(new StockPriceTo(id, companyCode, data, price), StockPriceEntity.class);
		stockPriceRepository.save(spe);
	}

}
