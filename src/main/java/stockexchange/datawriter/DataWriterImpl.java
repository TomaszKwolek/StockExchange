package stockexchange.datawriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.dao.DataWriterDao;


@Component
public class DataWriterImpl implements DataWriter {

	@Autowired
	private  Parser parser;
	@Autowired
	private DataWriterDao dataWriterDao;

	@Override

	public void saveAllIntoDB() {
		// TODO Auto-generated method stub
		System.out.println("DataWriter tez dziala");
		
	}

	@Override
	public void clearStocksTable() {
		// TODO Auto-generated method stub
		
	}
	
}
