package stockexchange.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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
import stockexchange.bank.ConfirmationFromBank;
import stockexchange.brokerage.Brokerage;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;
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
	private final BigDecimal commisionFactor = new BigDecimal(0.005); 
	private final BigDecimal minCommision = new BigDecimal(5); 
	
	private static final Logger log4j = LogManager.getLogger(Player.class.getName());

	public void executeBuyStrategy(Date date) { 
		List<CashBalance>  cashBalance = bank.getCashBalance(playerPesel, new BankAuthentication());
		List<ShareBalance>  shareBalance = brokerage.getSharesBalance(playerPesel, new BrokerageAuthentication());
		List<Offer> recommendationsToBuy = beanFactory.getBean(strategy, Strategy.class).prepareRecommendationsToBuy(cashBalance, date);	
		List<Offer> offersToBuy = brokerage.prepareListOfOffersToBuy(prepareOfferInquiry(recommendationsToBuy), date);
		List<Offer> sharesToBuy = makeDecisonToBuy(recommendationsToBuy, offersToBuy);
		PlayerLogPrinter.printCashBalance(cashBalance, date);
		PlayerLogPrinter.printShareBalance(shareBalance, date);
		PlayerLogPrinter.printOffers("Recommendation to buy ", recommendationsToBuy, date);
		PlayerLogPrinter.printOffers("Offer to buy ", offersToBuy, date);
		PlayerLogPrinter.printOffers("Will be purchased ", sharesToBuy, date);
		BigDecimal commision = calculateCommisionForBrokerage(sharesToBuy);
		BigDecimal costOfShares = calculateCostOfShares(sharesToBuy);
		BigDecimal toPay = costOfShares.add(commision);
		PlayerLogPrinter.printCosts(costOfShares, commision, toPay, date);
		ConfirmationFromBank bankConfirmation = bank.withdrawCash(playerPesel, new BankAuthentication(), toPay);
		cashBalance = bank.getCashBalance(playerPesel, new BankAuthentication());
		PlayerLogPrinter.printCashBalance(cashBalance, date);
		PlayerLogPrinter.printShareBalance(shareBalance, date);
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
	
	private List<Offer> prepareOfferInquiry(List<Offer> recommendations){
		List<Offer> offerInquiry = new ArrayList<>();
		for(Offer recommendation: recommendations){
			offerInquiry.add(new Offer(recommendation.getCompanyCode(), recommendation.getAmount(), new BigDecimal(0)));
		}
		return offerInquiry;
	}
	
	
	private BigDecimal calculateCommisionForBrokerage(List<Offer> sharesToBuy){
		BigDecimal commision = new BigDecimal(0);
		for(Offer share: sharesToBuy){
			BigDecimal transactionAmount = share.getPrice().multiply(new BigDecimal(share.getAmount()));
			BigDecimal regularCommision = transactionAmount.multiply(commisionFactor);
			if(regularCommision.compareTo(minCommision) == -1){
				commision=commision.add(minCommision);
			}
			else{
				commision=commision.add(regularCommision);
			}
		}
		return  commision;
	}
	
	private BigDecimal calculateCostOfShares(List<Offer> sharesToBuy){
		BigDecimal cost = new BigDecimal(0);
		for(Offer share: sharesToBuy){
			BigDecimal transactionAmount = share.getPrice().multiply(new BigDecimal(share.getAmount()));
			cost=cost.add(transactionAmount);
		}
		cost=cost.add(calculateCommisionForBrokerage(sharesToBuy));
		return  cost;
	}
	
}
