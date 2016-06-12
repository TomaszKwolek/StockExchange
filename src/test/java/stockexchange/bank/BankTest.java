package stockexchange.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.player.BankAuthentication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "bank-context.xml")
public class BankTest {


	@Autowired
	private Bank bank;

	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnCashBalance() {
		// given
		BankAuthentication authentication = new BankAuthentication();
		authentication.setPesel("86031701659");
		BigDecimal expectedAmount = new BigDecimal(10000);
		String currencyCode = "PLN";
		//when
		List<CashBalance> cashBalances = bank.getCashBalances(authentication, currencyCode);
		CashBalance cashBalance = cashBalances.get(0);
		// then
		assertFalse(cashBalances.isEmpty());
		assertEquals(expectedAmount.compareTo(cashBalance.getCashAmount()), 0);
		assertEquals(cashBalance.getCurrencyCode(), currencyCode);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnEmptyList() {
		// given
		BankAuthentication authentication = new BankAuthentication();
		authentication.setPesel("1111123456");
		String currencyCode = "PLN";
		//when
		List<CashBalance> cashBalances = bank.getCashBalances(authentication, currencyCode);
		// then
		assertTrue(cashBalances.isEmpty());
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulWithdrawnCash() {
		// given
		BankAuthentication authentication = new BankAuthentication();
		authentication.setPesel("86031701659");
		String currencyCode = "PLN";
		BigDecimal amount = new BigDecimal(5000);
		ConfirmationFromBank expectedConfirmation = new ConfirmationFromBank();
		expectedConfirmation.setAmount(amount);
		expectedConfirmation.setCurrencyCode(currencyCode);
		//when
		ConfirmationFromBank result = bank.withdrawCash(authentication, amount , currencyCode);
		// then
		assertEquals(result.getAmount(), expectedConfirmation.getAmount());
		assertEquals(result.getCurrencyCode(), expectedConfirmation.getCurrencyCode());
	}
}
