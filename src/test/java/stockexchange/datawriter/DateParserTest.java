package stockexchange.datawriter;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.mapper.DateParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context.xml")
public class DateParserTest {

	@Autowired
	private DateParser dateParser;

	@Test
	public void testShouldReturnCorrectDate() throws ParseException {
		// given
		String date = "20140214";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date excepctedDate = dateFormat.parse("2014/02/14"); 
		//when
		Date parsedData = dateParser.stringToDate(date);
		// then
		assertEquals(excepctedDate, parsedData);
	}
	
	@Test
	public void testShouldThrowExceptionForIncorectDateFormat() {
		// given
		String date = "2fghfgh214";
		//when
		try {
			dateParser.stringToDate(date);
			fail();
		} catch (ParseException e) {
			assertTrue(true);
		}
		// then
	}

}
