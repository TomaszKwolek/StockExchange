package stockexchange.datawriter;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.repository.StockUpdaterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "datawriter-context.xml")
public class DataWriterTest {

	@Autowired
	private DataWriter dataWriter;
	@Autowired
	private StockUpdaterRepository stockPricerepository;;

	@Test
	@Sql(scripts = "deleteAll.sql")
	public void testShouldSaveAllIntoDb() throws IOException {
		// given
		int numberOfRowsBefore = 0; 
		int numberOfRowsAfter = 0;	
		//when
		numberOfRowsBefore=stockPricerepository.findAll().size();
		dataWriter.saveAllIntoDB();
		numberOfRowsAfter=stockPricerepository.findAll().size();
		// then
		assertEquals(numberOfRowsBefore, 0);
		if(numberOfRowsAfter<=numberOfRowsBefore){
			fail();
		}
	}

}
