package stockexchange.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.exceptions.WrongParameterException;
import stockexchange.parameters.Parameters;
import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockOfDay;

@Component
public class OfferHelper {

	@Autowired
	private ParameterValidator paramValidator;
	@Autowired
	private Parameters param;

	private final static String ONE_HUNDRET_PERCENT = "1.0";

	public BigDecimal calculateMaxUnitPriceToBuy(Share share) throws WrongParameterException {
		BigDecimal maxPriceFaktor = new BigDecimal(0);
		String stockPriceFactor = param.getStockPriceFactor();
		checkParameter(stockPriceFactor);
		try {
			maxPriceFaktor = new BigDecimal(Double.parseDouble(ONE_HUNDRET_PERCENT)
					+ Double.parseDouble(stockPriceFactor) / 100.0 * Math.random());
		} catch (NumberFormatException e) {
			throw new WrongParameterException("Stock price faktor wrong number format!");
		}
		return share.getPrice().multiply(maxPriceFaktor);
	}

	public BigDecimal calculateMaxUnitPriceToSell(Share share) throws WrongParameterException {
		BigDecimal maxPriceFaktor = new BigDecimal(0);
		String stockPriceFactor = param.getStockPriceFactor();
		checkParameter(stockPriceFactor);
		try {
			maxPriceFaktor = new BigDecimal(Double.parseDouble(ONE_HUNDRET_PERCENT)
					- Double.parseDouble(stockPriceFactor) / 100.0 * Math.random());
		} catch (NumberFormatException e) {
			throw new WrongParameterException("Stock price faktor wrong number format!");
		}
		return share.getPrice().multiply(maxPriceFaktor);
	}

	public Integer calculateSharesAvailibity(Integer preferedAmount) throws WrongParameterException {
		BigDecimal availibityFactor = new BigDecimal(0);
		String stockAvailibityFactor = param.getStockAvailibityFactor();
		checkParameter(stockAvailibityFactor);
		try {
			availibityFactor = new BigDecimal(Double.parseDouble(ONE_HUNDRET_PERCENT)
					- Double.parseDouble(stockAvailibityFactor) * Math.random() / 100);
		} catch (NumberFormatException e) {
			throw new WrongParameterException("Stock availibity faktor wrong number format!");
		}
		return availibityFactor.multiply(new BigDecimal(preferedAmount)).intValue();
	}

	public Share findShareInDay(String companyCode, StockOfDay stockOfDay) {
		List<Share> shares = stockOfDay.getStock();
		Share foundShare = new Share();
		for (Share share : shares) {
			if (share.getCompanyCode().equals(companyCode)) {
				foundShare = share;
			}
		}
		return foundShare;
	}

	private void checkParameter(String parameter) {
		if (!paramValidator.isIntegerNumber(parameter)) {
			throw new WrongParameterException("Input parameter are not correct!");
		}
	}

}
