package stockexchange.stockexchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
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
import stockexchange.repository.StockExchangeRepository;
import stockexchange.stockexchange.StockExchange;
import stockexchange.stockexchange.StockOfDay;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "stockexchange-context.xml")
public class StockExchangeTest {


	@Autowired
	private StockExchange stockExchange;
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFiveStocksOfDaysForFiveDays() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130102"); 
		Date toDate = dateFormat.parse("20131102"); 
		//when
		List<StockOfDay> stockOfDays = stockExchange.getStockOfDays(fromDate, toDate);
		// then
		assertEquals(stockOfDays.size(), 5);
		assertEquals(stockOfDays.get(0).getDate(), fromDate);
		assertEquals(stockOfDays.get(0).getStock().size(), 3);
		assertEquals(stockOfDays.get(4).getDate(), toDate);
		assertEquals(stockOfDays.get(4).getStock().size(), 3);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFiveStocksOfDaysForFiveDaysForCompany() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date fromDate = dateFormat.parse("20130102"); 
		Date toDate = dateFormat.parse("20131102"); 
		//when
		List<StockOfDay> stockOfDays = stockExchange.getStockOfDaysForCompany("PZU", fromDate, toDate);
		// then
		assertEquals(stockOfDays.size(), 5);
		assertEquals(stockOfDays.get(0).getDate(), fromDate);
		assertEquals(stockOfDays.get(0).getStock().size(), 1);
		assertEquals(stockOfDays.get(4).getDate(), toDate);
		assertEquals(stockOfDays.get(4).getStock().size(), 1);
	}
	
}
