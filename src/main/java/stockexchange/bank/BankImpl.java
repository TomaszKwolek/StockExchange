package stockexchange.bank;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.dao.impl.BankDaoImpl;
import stockexchange.player.BankAuthentication;

@Service
public class BankImpl implements Bank{
	
	@Autowired
	private BankDaoImpl bankDao;

	@Override
	public List<CashBalance> getCashBalance(BankAuthentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayementConfirmation withdrawCash(BankAuthentication authentication, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void topUpAccount(BankAuthentication authentication, CashBalance cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeCurrency(String currencyPairCode, BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}

}
