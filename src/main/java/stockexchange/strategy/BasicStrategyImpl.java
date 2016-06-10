package stockexchange.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Brokerage;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;
import stockexchange.exceptions.NoStocksDataForDayException;
import stockexchange.helper.OfferHelper;
import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockOfDay;

@Component("basicStrategy")
public class BasicStrategyImpl implements Strategy {

	@Autowired
	private Brokerage brokerage;
	@Autowired
	private OfferHelper offerHelper;
	
	private final int FIRST_ITEM_INDEX=0;
	private final String currencyCode = "PLN";
	private final BigDecimal MAX_PERCENT_OF_CASH_FOR_SINGLE_TRANSACTION=new BigDecimal(20);
	private final BigDecimal HUNDRET_PERCENT=new BigDecimal(100);
	
	@Override
	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalances, Date date, int numberOfRecom)  throws  NoStocksDataForDayException{
		CashBalance cashBalance = findCashBalance(currencyCode, cashBalances); 
		List<Offer> recommendationsToBuy = new ArrayList<>();
		StockOfDay stockOfDay = brokerage.getStockOfDays(date, date).get(FIRST_ITEM_INDEX);
		List<Share> randomlySelectedShares = randomSharesToBuy(stockOfDay, numberOfRecom);
		for(Share share: randomlySelectedShares){
			recommendationsToBuy.add(prepareSignleRecommendationToBuy(share, cashBalance));
		}
		return recommendationsToBuy;
	}

	@Override
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance, Date date, int numberOfRecom)  throws  NoStocksDataForDayException{
		List<Offer> recommendationsToSell = new ArrayList<>();
		
		return recommendationsToSell;
	}
	
	private CashBalance findCashBalance(String currencyCode, List<CashBalance> cashBalances) {
		CashBalance foundCashBalance = new CashBalance(); 
		for(CashBalance cashBalance: cashBalances){
			if(cashBalance.getCurrencyCode().equals(currencyCode)){
				foundCashBalance=cashBalance;
				return foundCashBalance;
			}
		}
		return foundCashBalance;
	}
	
	@SuppressWarnings("unchecked")
	private List<Share> randomSharesToBuy(StockOfDay stockOfDay, int maxNumberOfShares){
		int maxToRandom = (maxNumberOfShares > stockOfDay.getStock().size()) ? stockOfDay.getStock().size() : maxNumberOfShares;
		List<Share> sharesInDay = stockOfDay.getStock();
		Collections.shuffle(sharesInDay);
		return sharesInDay.subList(0, maxToRandom);
	}
	
	private BigDecimal calculateMaxAmountOfSingleTransaction(CashBalance cashBalance){
		BigDecimal cashInWallet = cashBalance.getCashAmount();
		BigDecimal maxCashForSingleTransaction = cashInWallet.multiply(MAX_PERCENT_OF_CASH_FOR_SINGLE_TRANSACTION).divide(HUNDRET_PERCENT);
		return maxCashForSingleTransaction;
	}
	
	private Integer calculateMaxNumberOfSharesToBuy(BigDecimal maxAmount, BigDecimal unitPrice){
		return maxAmount.divide(unitPrice, 2).intValue();
	}

	private Offer prepareSignleRecommendationToBuy(Share share, CashBalance cashBalance){
		BigDecimal maxAmountTransatcion = calculateMaxAmountOfSingleTransaction(cashBalance).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal tempSharePrice = share.getPrice();
		BigDecimal tempPrice = offerHelper.calculateMaxUnitPriceToBuy(share);
		BigDecimal maxUnitPrice = offerHelper.calculateMaxUnitPriceToBuy(share).setScale(2, RoundingMode.HALF_DOWN);
		Integer maxSharesToBuy = calculateMaxNumberOfSharesToBuy(maxAmountTransatcion, maxUnitPrice);
		return new Offer(share.getCompanyCode(), maxSharesToBuy, maxUnitPrice);
	}
	
}
