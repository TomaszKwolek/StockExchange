package stockexchange.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import stockexchange.bank.CashBalance;
import stockexchange.dao.BankDao;

@Repository
public class BankDaoImpl implements BankDao{

	@Override
	public CashBalance getCashBalance(int playersPesel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getCurrenciesRate(String currenciesPairCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCashBalance(CashBalance cashBalance) {
		// TODO Auto-generated method stub
		
	}

}
