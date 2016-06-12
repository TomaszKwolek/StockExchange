package stockexchange.bank;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.exceptions.WrongParameterException;
import stockexchange.helper.ParameterValidator;
import stockexchange.parameters.Parameters;
import stockexchange.player.BankAuthentication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "bank-context.xml")
public class BankTestMock {


    @InjectMocks
    private BankImpl bank;
    @Mock
	private ParameterValidator parameterValidator;
    @Mock
    private Parameters param;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldThrowExceptionWrongParameter() {
        // given
		BankAuthentication authentication = new BankAuthentication();
		authentication.setPesel("86031701659");
		BigDecimal amount = new BigDecimal(10000);
		String currencyCode = "PLN";
        Mockito.when(param.getCurrencyCode()).thenReturn("??");
        // when
        try {
			bank.withdrawCash(authentication, amount, currencyCode);
			fail();
			 // then
		} catch (WrongParameterException e) {
			assertTrue(true);
		}
    }

}
