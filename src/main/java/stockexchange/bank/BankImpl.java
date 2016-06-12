package stockexchange.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.model.entity.CashPortfolioEntity;
import stockexchange.model.to.CashPortfolioTo;
import stockexchange.player.BankAuthentication;
import stockexchange.repository.CashPortfolioRepository;

@Service
public class BankImpl implements Bank{
	
	@Autowired
	private CashPortfolioRepository cashPortfolioRepository;
	@Autowired
	private MapperFacade mapper;
	
	private final static int FIRST_ITEM_INDEX=0;
	private final static String CURRENCY_CODE_PLN="PLN";
	private final static String DIGITAL_SIGN="sign";
	

	@Override
	public List<CashBalance> getCashBalances(BankAuthentication authentication, String currencyCode) {
		return convertToCashBalance(mapper.mapAsList(cashPortfolioRepository.findCashPortfolio(authentication.getPesel(), currencyCode), CashPortfolioTo.class));
	}

	@Override
	public ConfirmationFromBank withdrawCash(BankAuthentication authentication, BigDecimal amount, String currencyCode) {
	    updateCashPortfolio(authentication.getPesel(), amount, currencyCode);
		return new ConfirmationFromBank(DIGITAL_SIGN, CURRENCY_CODE_PLN, amount);
	}

	@Override
	public void topUpAccount(BankAuthentication authentication, CashBalance cash, String currencyCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void changeCurrency(BankAuthentication authentication, String currencyPairCode, BigDecimal amount) {
		// TODO Auto-generated method stub
	}
	
	private List<CashBalance> convertToCashBalance(List<CashPortfolioTo> cashPortfolios){
		List<CashBalance> cashBalances  = new ArrayList<>();
		for (CashPortfolioTo cashPortfolio : cashPortfolios) {
			cashBalances.add(new CashBalance(cashPortfolio.getCurrencyCode(), cashPortfolio.getAmount()));
		}
		return cashBalances;
	}
	
	private void updateCashPortfolio(String playerPesel, BigDecimal amount, String currencyCode) {
		CashPortfolioTo currentCashPortfolio =  mapper.mapAsList(cashPortfolioRepository.findCashPortfolio(playerPesel, currencyCode), CashPortfolioTo.class).get(FIRST_ITEM_INDEX);
		BigDecimal newAmount = currentCashPortfolio.getAmount().subtract(amount);
		CashPortfolioTo cashPortfolioTo = mapper.map(cashPortfolioRepository.findCashPortfolio(playerPesel, currencyCode).get(0), CashPortfolioTo.class);
		cashPortfolioTo.setAmount(newAmount);
		cashPortfolioRepository.save(mapper.map(cashPortfolioTo, CashPortfolioEntity.class));
	}

}
