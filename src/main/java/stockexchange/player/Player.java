package stockexchange.player;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import stockexchange.bank.Bank;
import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Brokerage;
import stockexchange.brokerage.Offer;
import stockexchange.strategy.Strategy;

@Service
public class Player {

	@Autowired
	private BeanFactory beanFactory;
	@Autowired
	private Brokerage brokerage;
	@Autowired
	private Bank bank;
	
	@Value(value = "#{simulationProperties['playerPesel']}")
	private String playerPesel;
	@Value(value = "#{simulationProperties['strategy']}")
	private String strategy;
	
	private static final Logger log4j = LogManager.getLogger(Player.class.getName());

	public void executeBuyStrategy(Date date) { 
		List<CashBalance>  cashBalances = bank.getCashBalance(playerPesel, new BankAuthentication());
		printCashBalance(cashBalances);
		List<Offer> recommendationsToBuy = beanFactory.getBean(strategy, Strategy.class).prepareRecommendationsToBuy(cashBalances, date, 4);	
		printOffers("Recommendation ", recommendationsToBuy, date);
		List<Offer> offersToBuy = brokerage.prepareListOfOffersToBuy(prepareOfferInquiry(recommendationsToBuy), date);
		printOffers("Offer ", offersToBuy, date);
		List<Offer> sharesToBuy = makeDecisonToBuy(recommendationsToBuy, offersToBuy);
		printOffers("Will be purchased ", sharesToBuy, date);
	 }
	
	public void executeSellStartegy(Date date) { 
		// TODO Auto-generated method
	 } 

	private List<Offer> makeDecisonToBuy(List<Offer> recommendations, List<Offer> offers) {
		List<Offer> sharesToBuy = new ArrayList<>(); 
		for(Offer recommendation: recommendations){
			for(Offer offer: offers){
				if(recommendation.getCompanyCode().equals(offer.getCompanyCode())){
					int compareResult = recommendation.getPrice().compareTo(offer.getPrice());
					if(compareResult >= 1){
						sharesToBuy.add(offer);
					}
				}
			}
		}
		return sharesToBuy; 
	 }
	
	private List<Offer> makeDecisionToSell(List<Offer> recommendations, List<Offer> offers) { 
		// TODO Auto-generated method
		return null;
	 }
	
	private void printCashBalance(List<CashBalance> cashBalances){
		String cashBalanceToPrint = "";
		for (CashBalance cashBalance : cashBalances) {
			cashBalanceToPrint+= cashBalance.getCurrencyCode() + ": "+ cashBalance.getCashAmount()+" | ";
		}
		log4j.log(Level.forName("NOTICE", 150), "Cash Balance at the beginning of the simulation: "+cashBalanceToPrint);
	}

	private void printOffers(String commentar, List<Offer> offer, Date date) {
		for(int i=0; i<offer.size(); i++){
			log4j.log(Level.forName("NOTICE", 150), date+" "+commentar+(i+1)+": "+ offer.get(i).getCompanyCode()+ " " +offer.get(i).getAmount() + " "+offer.get(i).getPrice());
		}
	}
	
	private List<Offer> prepareOfferInquiry(List<Offer> recommendations){
		List<Offer> offerInquiry = new ArrayList<>();
		for(Offer recommendation: recommendations){
			offerInquiry.add(new Offer(recommendation.getCompanyCode(), recommendation.getAmount(), new BigDecimal(0)));
		}
		return offerInquiry;
	}
}
