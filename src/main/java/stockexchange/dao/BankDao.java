package stockexchange.dao;

import java.math.BigDecimal;

import stockexchange.bank.CashBalance;

public interface BankDao {

	CashBalance getCashBalance(int playersPesel);
	BigDecimal getCurrenciesRate(String currenciesPairCode);
	void updateCashBalance(CashBalance cashBalance);
	
}
