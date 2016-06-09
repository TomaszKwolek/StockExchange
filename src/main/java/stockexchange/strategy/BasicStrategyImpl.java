package stockexchange.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Brokerage;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;
import stockexchange.stockexchange.StockOfDay;

@Component("basicStrategy")
public class BasicStrategyImpl implements Strategy {

	@Autowired
	private Brokerage brokerage;
	
	@Override
	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalance, Date date) {
		List<Offer> recommendationsToBuy = new ArrayList<>();
		List<StockOfDay> stockOfDay = brokerage.getStockOfDays(date, date);
		
		return recommendationsToBuy;
	}

	@Override
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance, Date date) {
		List<Offer> recommendationsToSell = new ArrayList<>();
		
		return recommendationsToSell;
	}

}
