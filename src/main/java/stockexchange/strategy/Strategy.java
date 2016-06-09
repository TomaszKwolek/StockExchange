package stockexchange.strategy;

import java.util.Date;
import java.util.List;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;

public interface Strategy {


	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalance, Date date);
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance, Date date);



}
