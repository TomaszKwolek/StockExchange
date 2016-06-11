package stockexchange.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.bank.CashBalance;
import stockexchange.bank.ConfirmationFromBank;
import stockexchange.dao.impl.BrokerageDaoImpl;
import stockexchange.exceptions.NoStocksDataForDayException;
import stockexchange.helper.OfferHelper;
import stockexchange.model.to.CashPortfolioTo;
import stockexchange.model.to.StockPortfolioTo;
import stockexchange.model.to.StockPriceTo;
import stockexchange.player.BrokerageAuthentication;
import stockexchange.repository.BrokerageRepository;
import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockExchange;
import stockexchange.stockexchange.StockOfDay;

@Service
public class BrokerageImpl implements Brokerage {

	@Autowired
	private StockExchange stockExchange;
	@Autowired
	private BrokerageRepository brokerageRepository;
	@Autowired
	private OfferHelper offerHelper;
	@Autowired
	private MapperFacade mapper;
	
	private final static int FIRST_ITEM_INDEX=0;
	
	@Override
	public List<Offer> prepareListOfOffersToSell(List<Offer> offersInquiry, Date date) {
		return null;
	}

	@Override
	public List<Offer> prepareListOfOffersToBuy(List<Offer> offersInquiry, Date date) {
		StockOfDay stockOfDay = getStockOfDays(date, date).get(FIRST_ITEM_INDEX);
		List<Offer> offers = new ArrayList<>(); 
		for(Offer singleOfferInquiry: offersInquiry){
			offers.add(prepareSignleOfferToBuy(singleOfferInquiry, stockOfDay));
		}
		return offers;
	}

	@Override
	public List<ShareBalance> getSharesBalance(String playerPesel, BrokerageAuthentication authentication) {
		return convertToShareBalance(mapper.mapAsList(brokerageRepository.findStockPortfolio(playerPesel), StockPortfolioTo.class));
	}

	@Override
	public void confirmBuy(BrokerageAuthentication authentication, List<Offer> ListOfOffers, ConfirmationFromBank payConf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmSell(BrokerageAuthentication authentication, List<Offer> ListOfOffers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StockOfDay> getStockOfDays(Date fromDate, Date toDate) throws  NoStocksDataForDayException{
		List<StockOfDay> stockOfDays = stockExchange.getStockOfDays(fromDate, toDate);
		if(stockOfDays.isEmpty()){
			throw new NoStocksDataForDayException("No stock for selected days!");  
		}
		return stockOfDays;
	}
	
	@Override
	public List<StockOfDay> getStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate)  throws  NoStocksDataForDayException {
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

	
	private Offer prepareSignleOfferToBuy(Offer singlefferInquiry, StockOfDay stockOfDay){
		String companyCode = singlefferInquiry.getCompanyCode();
		Share shareFromStock = offerHelper.findShareInDay(companyCode, stockOfDay);
		Integer maxSharesToBuy = offerHelper.calculateSharesAvailibity(singlefferInquiry.getAmount());
		BigDecimal minUnitPrice = offerHelper.calculateMaxUnitPriceToBuy(shareFromStock).setScale(2, RoundingMode.HALF_DOWN);
		return new Offer(companyCode, maxSharesToBuy, minUnitPrice);
	}
	
	private List<ShareBalance> convertToShareBalance(List<StockPortfolioTo> stockPortfolios){
		List<ShareBalance> shareBalance  = new ArrayList<>();
		for (StockPortfolioTo stockPortfolio : stockPortfolios) {
			shareBalance.add(new ShareBalance(stockPortfolio.getCompanyCode(), stockPortfolio.getAmount()));
		}
		return shareBalance;
	}

}
