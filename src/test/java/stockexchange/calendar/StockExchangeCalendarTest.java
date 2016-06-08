package stockexchange.calendar;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.brokerage.Brokerage;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "calendar-context.xml")
public class StockExchangeCalendarTest {

	@Autowired
	private StockExchangeCalendar calendar;
	@Autowired
	private Brokerage brokerage;

	@Test
	public void testShoulReturnNextDay() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse("2016/06/30"); 
		Date excepctedNextDate = dateFormat.parse("2016/07/01"); 
		//when
		Date result = calendar.getNextDay(date);
		// then
		assertEquals(result, excepctedNextDate);
	}
	
	@Test
	public void testShoulReturnNextWorkingDay() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse("2016/06/24"); 
		Date excepctedNextDate = dateFormat.parse("2016/06/27"); 
		//when
		Date result = calendar.getNextWorkingDay(date);
		// then
		assertEquals(result, excepctedNextDate);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulUpdateFirstDate() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); 
		Date excepctedNextDate = dateFormat.parse("20130412"); 
		//when
		calendar.updateFirstDateOnSE();
		Date result = calendar.getFirstDateOnSE();
		// then
		assertEquals(result, excepctedNextDate);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulUpdateLastDate() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date excepctedNextDate = dateFormat.parse("20161212"); 
		//when
		calendar.udpateLastDateOnSE();;
		Date result = calendar.getLastDateOnSE();
		// then
		assertEquals(result, excepctedNextDate);
	}

}
