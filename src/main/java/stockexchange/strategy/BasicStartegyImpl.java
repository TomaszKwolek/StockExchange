package stockexchange.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;

@Service
public class BasicStartegyImpl implements Strategy {

	@Autowired
	private BrokerageImpl brokerage;
	
	@Override
	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance) {
		// TODO Auto-generated method stub
		return null;
	}

}
