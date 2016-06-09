package stockexchange.brokerage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.bank.PayementConfirmation;
import stockexchange.dao.impl.BrokerageDaoImpl;
import stockexchange.model.to.StockPriceTo;
import stockexchange.player.BrokerageAuthentication;
import stockexchange.stockexchange.StockExchange;
import stockexchange.stockexchange.StockOfDay;

@Service
public class BrokerageImpl implements Brokerage {

	@Autowired
	private StockExchange stockExchange;
	//@Autowired
	//private BrokerageDaoImpl breokerageDao;
	
	@Override
	public List<Offer> prepareListOfOffersToSell(Map<String, Integer> sharesToOfferInquiry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> prepareListOfOffersToBuy(Map<String, Integer> sharesToOfferInquiry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShareBalance> getStockBalnce(BrokerageAuthentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmBuy(BrokerageAuthentication authentication, List<Offer> ListOfOffers,
			PayementConfirmation payConf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmSell(BrokerageAuthentication authentication, List<Offer> ListOfOffers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StockOfDay> getStockOfDays(Date fromDate, Date toDate) {
		return stockExchange.getStockOfDays(fromDate, toDate);
	}
	
	@Override
	public List<StockOfDay> getStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate) {
		return stockExchange.getStockOfDaysForCompany(companyCode, fromDate, toDate);
	}

	@Override
	public List<StockOfDay> getSelectedStockOfDays(Date fromDate, Date toDate, List<String> shares) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFirstDateOnSE() {
		return stockExchange.getFirstDateOnSE();
	}

	@Override
	public Date getLastDateOnSE() {
		return stockExchange.getLastDateOnSE();
	}

}
