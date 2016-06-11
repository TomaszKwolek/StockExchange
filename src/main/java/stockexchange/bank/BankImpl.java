package stockexchange.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.dao.impl.BankDaoImpl;
import stockexchange.model.entity.CashPortfolioEntity;
import stockexchange.model.entity.PlayerEntity;
import stockexchange.model.to.CashPortfolioTo;
import stockexchange.model.to.PlayerTo;
import stockexchange.player.BankAuthentication;
import stockexchange.player.Player;
import stockexchange.repository.CashPortfolioRepository;

@Service
public class BankImpl implements Bank{
	
	@Autowired
	private CashPortfolioRepository cashPortfolioRepository;
	@Autowired
	private MapperFacade mapper;
	
	private final static int FIRST_ITEM_INDEX=0;

	@Override
	public List<CashBalance> getCashBalance(String playerPesel, BankAuthentication authentication) {
		return convertToCashBalance(mapper.mapAsList(cashPortfolioRepository.findCashPortfolio(playerPesel), CashPortfolioTo.class));
	}

	@Override
	public ConfirmationFromBank withdrawCash(String playerPesel, BankAuthentication authentication, BigDecimal amount) {
	    updateCashPortfolio(playerPesel, amount);
		return new ConfirmationFromBank("key", "PLN", amount);
	}

	@Override
	public void topUpAccount(String playerPesel, BankAuthentication authentication, CashBalance cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeCurrency(String playerPesel, BankAuthentication authentication, String currencyPairCode, BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}
	
	private List<CashBalance> convertToCashBalance(List<CashPortfolioTo> cashPortfolios){
		List<CashBalance> cashBalances  = new ArrayList<>();
		for (CashPortfolioTo cashPortfolio : cashPortfolios) {
			cashBalances.add(new CashBalance(cashPortfolio.getCurrencyCode(), cashPortfolio.getAmount()));
		}
		return cashBalances;
	}
	
	private void updateCashPortfolio(String playerPesel, BigDecimal amount) {
		List<CashPortfolioTo> cashportfolios = mapper.mapAsList(cashPortfolioRepository.findCashPortfolio(playerPesel), CashPortfolioTo.class);
		CashPortfolioTo currentCashPortfolio =  cashportfolios.get(FIRST_ITEM_INDEX);
		BigDecimal newAmount = currentCashPortfolio.getAmount().subtract(amount);
		CashPortfolioTo CashPortfolioTo = mapper.map(cashPortfolioRepository.findCashPortfolio(playerPesel).get(0), CashPortfolioTo.class);
		CashPortfolioTo.setAmount(newAmount);
		cashPortfolioRepository.save(mapper.map(CashPortfolioTo, CashPortfolioEntity.class));
	}

}
