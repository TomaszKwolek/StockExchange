package stockexchange.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "bank-context.xml")
public class BankTest {


	@Autowired
	private Bank bank;

	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnCashBalance() {
//		// given
//		String playerPesel = "86031701659";
//		BigDecimal expectedAmount = new BigDecimal(10000);
//		String excpectedCurrencyCode = "PLN";
//		//when
//		List<CashBalance> cashBalances = bank.getCashBalance(playerPesel, null);
//		CashBalance cashBalance = cashBalances.get(0);
//		// then
//		assertFalse(cashBalances.isEmpty());
//		assertEquals(expectedAmount.compareTo(cashBalance.getCashAmount()), 0);
//		assertEquals(cashBalance.getCurrencyCode(), excpectedCurrencyCode);
	}
	
}
