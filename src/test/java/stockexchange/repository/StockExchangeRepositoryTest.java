package stockexchange.repository;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.model.entity.StockPriceEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "repository-context.xml")
public class StockExchangeRepositoryTest {


	@Autowired
	private StockExchangeRepository stockExchangeRepository;
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFifteenStockPrices() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130102"); 
		Date toDate = dateFormat.parse("20131102"); 
		//when
		List<StockPriceEntity> stockPriceEntities = stockExchangeRepository.findStockOfDays(fromDate, toDate);
		// then
		assertEquals(stockPriceEntities.size(), 15);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnNineStockPrices() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130212"); 
		Date toDate = dateFormat.parse("20130801"); 
		//when
		List<StockPriceEntity> stockPriceEntities = stockExchangeRepository.findStockOfDays(fromDate, toDate);
		// then
		assertEquals(stockPriceEntities.size(), 9);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnThreeStockPrices() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130212"); 
		Date toDate = dateFormat.parse("20130212"); 
		//when
		List<StockPriceEntity> stockPriceEntities = stockExchangeRepository.findStockOfDays(fromDate, toDate);
		// then
		assertEquals(stockPriceEntities.size(), 3);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnZeroStockPrices() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20160212"); 
		Date toDate = dateFormat.parse("20110801"); 
		//when
		List<StockPriceEntity> stockPriceEntities = stockExchangeRepository.findStockOfDays(fromDate, toDate);
		// then
		assertEquals(stockPriceEntities.size(), 0);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFiveForPZUShares() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130102"); 
		Date toDate = dateFormat.parse("20131102"); 
		//when
		List<StockPriceEntity> stockPriceEntities = stockExchangeRepository.findStockOfDaysForCompany("PZU", fromDate, toDate);
		// then
		assertEquals(stockPriceEntities.size(), 5);
	}
	
}
