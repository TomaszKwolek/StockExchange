package stockexchange.bank;

import java.math.BigDecimal;
import java.util.List;

import stockexchange.player.BankAuthentication;

public interface Bank {

	List<CashBalance> getCashBalance(BankAuthentication authentication);
	PayementConfirmation withdrawCash(BankAuthentication authentication, BigDecimal amount);
	void topUpAccount(BankAuthentication authentication, CashBalance cash);
	void changeCurrency(String currencyPairCode, BigDecimal amount);
	
		
}
