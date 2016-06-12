package stockexchange.player;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.bank.Bank;
import stockexchange.bank.CashBalance;
import stockexchange.bank.ConfirmationFromBank;
import stockexchange.brokerage.Brokerage;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;
import stockexchange.exceptions.WrongParameterException;
import stockexchange.helper.ParameterValidator;
import stockexchange.parameters.Parameters;
import stockexchange.strategy.Strategy;

@Service
public class Player {

	@Autowired
	private BeanFactory beanFactory;
	@Autowired
	private Brokerage brokerage;
	@Autowired
	private Bank bank;
	@Autowired
	private ParameterValidator parameterValidator;
	@Autowired
	private Parameters param;

	public void executeBuyStrategy(Date date) throws WrongParameterException {
		int player_id = Integer.parseInt(param.getPlayerId());
		String playerPesel = param.getPlayerPesel();
		String currencyCode = param.getCurrencyCode();
		BankAuthentication bankAuthentiction = new BankAuthentication(playerPesel, "", "", player_id);
		BrokerageAuthentication brokerageAuthentiction = new BrokerageAuthentication(playerPesel, "", "", player_id);

		checkParameter();

		List<CashBalance> cashBalances = bank.getCashBalances(bankAuthentiction, currencyCode);
		List<ShareBalance> shareBalances = brokerage.getSharesBalances(brokerageAuthentiction);
		List<Offer> recommendationsToBuy = beanFactory.getBean(param.getStrategy(), Strategy.class).prepareRecommendationsToBuy(cashBalances, date);
		List<Offer> offersToBuy = brokerage.prepareListOfOffersToBuy(prepareOfferInquiry(recommendationsToBuy), date);
		List<Offer> sharesToBuy = makeDecisonToBuy(recommendationsToBuy, offersToBuy);

		PlayerLogPrinter.printCashBalance(cashBalances, date);
		PlayerLogPrinter.printShareBalance(shareBalances, date);
		PlayerLogPrinter.printOffers("Recommendation to buy ", recommendationsToBuy, date);
		PlayerLogPrinter.printOffers("Offer to buy ", offersToBuy, date);
		PlayerLogPrinter.printOffers("Will be purchased ", sharesToBuy, date);

		BigDecimal commision = calculateCommisionForBrokerage(sharesToBuy);
		BigDecimal costOfShares = calculateCostOfShares(sharesToBuy);
		BigDecimal toPay = costOfShares.add(commision);

		PlayerLogPrinter.printCosts(costOfShares, commision, toPay, date);

		ConfirmationFromBank bankConfirmation = bank.withdrawCash(bankAuthentiction, toPay, currencyCode);
		brokerage.confirmBuy(brokerageAuthentiction, sharesToBuy, bankConfirmation);

		cashBalances = bank.getCashBalances(bankAuthentiction, currencyCode);
		shareBalances = brokerage.getSharesBalances(brokerageAuthentiction);

		PlayerLogPrinter.printCashBalance(cashBalances, date);
		PlayerLogPrinter.printShareBalance(shareBalances, date);
	}

	public void executeSellStartegy(Date date) {
		// TODO Auto-generated method
	}

	private void makeSimpleDecision(List<Offer> sharesToBuy, Offer recommendation, Offer offer) {
		if (recommendation.getCompanyCode().equals(offer.getCompanyCode())) {
			int compareResult = recommendation.getPrice().compareTo(offer.getPrice());
			if (compareResult >= 1 && offer.getAmount() > 0) {
				sharesToBuy.add(offer);
			}
		}
	}
	
	private List<Offer> makeDecisonToBuy(List<Offer> recommendations, List<Offer> offers) {
		List<Offer> sharesToBuy = new ArrayList<>();
		for (Offer recommendation : recommendations) {
			for (Offer offer : offers) {
				makeSimpleDecision(sharesToBuy, recommendation, offer);
			}
		}
		return sharesToBuy;
	}

	@SuppressWarnings("unused")
	private List<Offer> makeDecisionToSell(List<Offer> recommendations, List<Offer> offers) {
		// TODO Auto-generated method
		return null;
	}

	private List<Offer> prepareOfferInquiry(List<Offer> recommendations) {
		List<Offer> offerInquiry = new ArrayList<>();
		for (Offer recommendation : recommendations) {
			offerInquiry.add(new Offer(recommendation.getCompanyCode(), recommendation.getAmount(), new BigDecimal(0)));
		}
		return offerInquiry;
	}

	private BigDecimal calculateCommisionForBrokerage(List<Offer> sharesToBuy) {
		BigDecimal commFactor = new BigDecimal(param.getCommissionFactor());
		BigDecimal minComm = new BigDecimal(param.getMinCommission());
		BigDecimal commission = new BigDecimal(0);
		for (Offer share : sharesToBuy) {
			BigDecimal transactionAmount = share.getPrice().multiply(new BigDecimal(share.getAmount()));
			BigDecimal regularCommision = transactionAmount.multiply(commFactor);
			if (regularCommision.compareTo(minComm) == -1) {
				commission = commission.add(minComm);
			} else {
				commission = commission.add(regularCommision);
			}
		}
		return commission;
	}

	private BigDecimal calculateCostOfShares(List<Offer> sharesToBuy) {
		BigDecimal cost = new BigDecimal(0);
		for (Offer share : sharesToBuy) {
			BigDecimal transactionAmount = share.getPrice().multiply(new BigDecimal(share.getAmount()));
			cost = cost.add(transactionAmount);
		}
		cost = cost.add(calculateCommisionForBrokerage(sharesToBuy));
		return cost;
	}

	private void checkParameter() {
		if (!parameterValidator.isPesel(param.getPlayerPesel()) || !parameterValidator.isIntegerNumber(param.getPlayerId())
				|| !parameterValidator.isStringCorrect(param.getStrategy()) || !parameterValidator.isStringCorrect(param.getCurrencyCode())
				|| !parameterValidator.isNumberWithComma(param.getCommissionFactor())
				|| !parameterValidator.isNumberWithComma(param.getMinCommission())) {
			throw new WrongParameterException("Input parameter are not correct!");
		}

	}

}
