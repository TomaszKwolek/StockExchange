package stockexchange.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.dao.impl.BankDaoImpl;
import stockexchange.model.to.CashPortfolioTo;
import stockexchange.player.BankAuthentication;
import stockexchange.repository.CashPortfolioRepository;

@Service
public class BankImpl implements Bank{
	
	@Autowired
	private CashPortfolioRepository cashPortfolioRepository;
	@Autowired
	private MapperFacade mapper;

	@Override
	public List<CashBalance> getCashBalance(String playerPesel, BankAuthentication authentication) {
		return convertToCashBalance(mapper.mapAsList(cashPortfolioRepository.findCashPortfolio(playerPesel), CashPortfolioTo.class));
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
	
	private List<CashBalance> convertToCashBalance(List<CashPortfolioTo> cashPortfolios){
		List<CashBalance> cashBalances  = new ArrayList<>();
		for (CashPortfolioTo cashPortfolio : cashPortfolios) {
			cashBalances.add(new CashBalance(cashPortfolio.getCurrencyCode(), cashPortfolio.getAmount()));
		}
		return cashBalances;
	}

}
