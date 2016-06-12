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
import stockexchange.exceptions.WrongParameterException;
import stockexchange.helper.OfferHelper;
import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockOfDay;

@Component("basicStrategy")
public class BasicStrategyImpl implements Strategy {

	@Autowired
	private Brokerage brokerage;
	@Autowired
	private OfferHelper offerHelper;

	private final static int FIRST_ITEM_INDEX = 0;
	private final static String CURRENCY_CODE = "PLN";
	private final static BigDecimal HUNDRET_PERCENT = new BigDecimal(100);
	private final static int NUMBER_OF_RECOM = 4;
	private final static BigDecimal MAX_PERCENT_OF_CASH_FOR_SINGLE_TRANSACTION = new BigDecimal(60 / NUMBER_OF_RECOM);

	@Override
	public List<Offer> prepareRecommendationsToBuy(List<CashBalance> cashBalances, Date date) throws NoStocksDataForDayException, WrongParameterException {
		CashBalance cashBalance = findCashBalance(CURRENCY_CODE, cashBalances);
		List<Offer> recommendationsToBuy = new ArrayList<>();
		StockOfDay stockOfDay = brokerage.getStockOfDays(date, date).get(FIRST_ITEM_INDEX);
		List<Share> randomlySelectedShares = randomSharesToBuy(stockOfDay, NUMBER_OF_RECOM);
		for (Share share : randomlySelectedShares) {
			recommendationsToBuy.add(prepareSignleRecommendationToBuy(share, cashBalance));
		}
		return recommendationsToBuy;
	}

	@Override
	public List<Offer> prepareRecommendationsToSell(List<ShareBalance> stocksBalance, Date date)
			throws NoStocksDataForDayException {
		List<Offer> recommendationsToSell = new ArrayList<>();

		return recommendationsToSell;
	}

	private CashBalance findCashBalance(String currencyCode, List<CashBalance> cashBalances) {
		CashBalance foundCashBalance = new CashBalance();
		for (CashBalance cashBalance : cashBalances) {
			if (cashBalance.getCurrencyCode().equals(currencyCode)) {
				foundCashBalance = cashBalance;
				return foundCashBalance;
			}
		}
		return foundCashBalance;
	}

	private List<Share> randomSharesToBuy(StockOfDay stockOfDay, int maxNumberOfShares) {
		int maxToRandom = (maxNumberOfShares > stockOfDay.getStock().size()) ? stockOfDay.getStock().size()
				: maxNumberOfShares;
		List<Share> sharesInDay = stockOfDay.getStock();
		Collections.shuffle(sharesInDay);
		return sharesInDay.subList(0, maxToRandom);
	}

	private BigDecimal calculateMaxAmountOfSingleTransaction(CashBalance cashBalance) {
		BigDecimal cashInWallet = cashBalance.getCashAmount();
		BigDecimal maxCashForSingleTransaction = cashInWallet.multiply(MAX_PERCENT_OF_CASH_FOR_SINGLE_TRANSACTION)
				.divide(HUNDRET_PERCENT);
		return maxCashForSingleTransaction;
	}

	private Integer calculateMaxNumberOfSharesToBuy(BigDecimal maxAmount, BigDecimal unitPrice) {
		return maxAmount.divide(unitPrice, 2).intValue();
	}

	private Offer prepareSignleRecommendationToBuy(Share share, CashBalance cashBalance)
			throws WrongParameterException {
		BigDecimal maxAmountTransatcion = calculateMaxAmountOfSingleTransaction(cashBalance).setScale(2,
				RoundingMode.HALF_DOWN);
		BigDecimal maxUnitPrice = offerHelper.calculateMaxUnitPriceToBuy(share).setScale(2, RoundingMode.HALF_DOWN);
		Integer maxSharesToBuy = calculateMaxNumberOfSharesToBuy(maxAmountTransatcion, maxUnitPrice);
		return new Offer(share.getCompanyCode(), maxSharesToBuy, maxUnitPrice);
	}

}
