package stockexchange.brokerage;

import stockexchange.player.BrokerageAuthentication;
import stockexchange.stockexchange.StockOfDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import stockexchange.bank.PayementConfirmation;
import stockexchange.model.to.StockPriceTo;

public interface Brokerage {

	public List<Offer> prepareListOfOffersToSell(Map<String, Integer> sharesToOfferInquiry);
	public List<Offer> prepareListOfOffersToBuy(Map<String, Integer> sharesToOfferInquiry);
	public List<ShareBalance> getStockBalnce(BrokerageAuthentication authentication);
	public void   confirmBuy(BrokerageAuthentication authentication, List<Offer> ListOfOffers, PayementConfirmation payConf);
	public void   confirmSell(BrokerageAuthentication authentication, List<Offer> ListOfOffers);
	public  List<StockOfDay> getStockOfDays(Date fromDate, Date toDate);
	public  List<StockOfDay> getStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate);
	public  List<StockOfDay> getSelectedStockOfDays(Date fromDate, Date toDate, List<String> shares);
	public Date getFirstDateOnSE();
	public Date  getLastDateOnSE();

}
