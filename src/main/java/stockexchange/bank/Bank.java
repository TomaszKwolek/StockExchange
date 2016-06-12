package stockexchange.bank;

import java.math.BigDecimal;
import java.util.List;

import stockexchange.player.BankAuthentication;

public interface Bank {

	List<CashBalance> getCashBalances(BankAuthentication authentication, String currencyCode);
	ConfirmationFromBank withdrawCash(BankAuthentication authentication, BigDecimal amount, String currencyCode);
	void topUpAccount(BankAuthentication authentication, CashBalance cash, String currencyCode);
	void changeCurrency(BankAuthentication authentication, String currencyPairCode, BigDecimal amount);
		
}
