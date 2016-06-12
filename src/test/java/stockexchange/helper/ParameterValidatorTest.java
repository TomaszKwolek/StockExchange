package stockexchange.helper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "helper-context.xml")
public class ParameterValidatorTest {

	@Autowired
	private ParameterValidator validator;

	@Test
	public void testShouldReturnTrueForCorrectInteger() {
		// given
		String number = "76";
		//when
		// then
		assertTrue(validator.isIntegerNumber(number));
	}

	@Test
	public void testShouldReturnFalseForNotCorrectInteger() {
		// given
		String number = "zx";
		//when
		// then
		assertFalse(validator.isIntegerNumber(number));
	}
	
	@Test
	public void testShouldReturnTrueForString() {
		// given
		String param = "dsfdgfdg";
		//when
		// then
		assertTrue(validator.isStringCorrect(param));
	}

	@Test
	public void testShouldReturnFalseForEmptyString() {
		// given
		String param = "";
		//when
		// then
		assertFalse(validator.isStringCorrect(param));
	}
	
	@Test
	public void testShouldReturnTrueForNumberWitComma() {
		// given
		String number = "5.4";
		//when
		// then
		assertTrue(validator.isNumberWithComma(number));
	}

	@Test
	public void testShouldReturnFalseForNotNumberWithComma() {
		// given
		String number = "fg";
		//when
		// then
		assertFalse(validator.isNumberWithComma(number));
	}

}
