package stockexchange.datawriter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "datawriter-context.xml")
public class ValidatorTest {

	@Autowired
	private Validator validator;

	@Test
	public void testShouldFindNotCorrectCompanyCode() {
		// given
		String[] line = {"PK**O", "20140214", "12.5"};
		//when
		// then
		assertFalse(validator.isCompanyCodeCorrect(line[0]));
		assertFalse(validator.isLineCorrect(line));
	}
	
	@Test
	public void testShouldFindNotCorrectDateType() {
		// given
		String[] line = {"PKO", "2fghfgh214", "12.5"};
		//when
		// then
		assertFalse(validator.isDateStringCorrect(line[1]));
		assertFalse(validator.isLineCorrect(line));
	}
	
	@Test
	public void testShouldFindNotCorrectPrice() {
		// given
		String[] line = {"PKO", "20130214", "12.sd5"};
		//when
		// then
		assertFalse(validator.isPriceCorrect(line[2]));
		assertFalse(validator.isLineCorrect(line));
	}
	
	@Test
	public void testShouldReturnCorrectDataLine() {
		// given
		String[] line = {"PKO", "20120214", "12.5"};
		//when
		// then
		assertTrue(validator.isCompanyCodeCorrect(line[0]));
		assertTrue(validator.isDateStringCorrect(line[1]));
		assertTrue(validator.isPriceCorrect(line[2]));
		assertTrue(validator.isLineCorrect(line));
	}

}
