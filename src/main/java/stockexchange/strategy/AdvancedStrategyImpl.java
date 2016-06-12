package stockexchange.strategy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;

@Component("advancedStrategy")
public class AdvancedStrategyImpl implements Strategy {

	@SuppressWarnings("unused")
	@Autowired
	private BrokerageImpl brokerage;
	
	@Override
	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalance, Date date) {
		return null;
	}

	@Override
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance, Date date) {
		return null;
	}

}
