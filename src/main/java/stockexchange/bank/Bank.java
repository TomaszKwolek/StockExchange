package stockexchange.bank;

import java.math.BigDecimal;
import java.util.List;

import stockexchange.player.BankAuthentication;

public interface Bank {

	List<CashBalance> getCashBalance(String playerPesel, BankAuthentication authentication);
	ConfirmationFromBank withdrawCash(String playerPesel, BankAuthentication authentication, BigDecimal amount);
	void topUpAccount(String playerPesel, BankAuthentication authentication, CashBalance cash);
	void changeCurrency(String playerPesel, BankAuthentication authentication, String currencyPairCode, BigDecimal amount);
	
		
}
