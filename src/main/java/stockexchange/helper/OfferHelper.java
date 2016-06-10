package stockexchange.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockOfDay;

@Service
public class OfferHelper {
	
	@Value(value = "#{simulationProperties['stockPriceFactor']}")
	private String stockPriceFactor;
	@Value(value = "#{simulationProperties['stockAvailibityFactor']}")
	private String stockAvailibityFactor;
	

	public BigDecimal calculateMaxUnitPriceToBuy(Share share){
		BigDecimal maxPriceFaktor = new BigDecimal(Double.parseDouble("1") + Double.parseDouble(stockPriceFactor)/100 * Math.random());
		return share.getPrice().multiply(maxPriceFaktor);
	}
	
	public BigDecimal calculateMaxUnitPriceToSell(Share share){
		BigDecimal maxPriceFaktor = new BigDecimal(Double.parseDouble("1") - Double.parseDouble(stockPriceFactor)/100 * Math.random());
		return share.getPrice().multiply(maxPriceFaktor);
	}
	
	public Integer calculateSharesAvailibity(Integer preferedAmount){
		BigDecimal availibityFactor = new BigDecimal(Double.parseDouble("1") - Double.parseDouble(stockAvailibityFactor) * Math.random()/100);
		return availibityFactor.multiply(new BigDecimal(preferedAmount)).intValue();
	}
	
	public Share findShareInDay(String companyCode, StockOfDay stockOfDay){
		List<Share> shares = stockOfDay.getStock();
		Share foundShare = new Share();
		for(Share share: shares){
			if(share.getCompanyCode().equals(companyCode)){
				foundShare=share;
			}
		}
		return foundShare;
	}
	
	
}
