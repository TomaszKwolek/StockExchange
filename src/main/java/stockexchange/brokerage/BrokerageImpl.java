package stockexchange.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.bank.ConfirmationFromBank;
import stockexchange.exceptions.NoStocksDataForDayException;
import stockexchange.exceptions.WrongParameterException;
import stockexchange.helper.OfferHelper;
import stockexchange.model.entity.PlayerEntity;
import stockexchange.model.entity.StockPortfolioEntity;
import stockexchange.model.to.PlayerTo;
import stockexchange.model.to.StockPortfolioTo;
import stockexchange.player.BrokerageAuthentication;
import stockexchange.repository.BrokerageRepository;
import stockexchange.repository.PlayerRepository;
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
	@Autowired
	private PlayerRepository playerRepository;;
	
	private final static int FIRST_ITEM_INDEX=0;
	
	@Override
	public List<Offer> prepareListOfOffersToSell(List<Offer> offersInquiry, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> prepareListOfOffersToBuy(List<Offer> offersInquiry, Date date) throws WrongParameterException {
		StockOfDay stockOfDay = getStockOfDays(date, date).get(FIRST_ITEM_INDEX);
		List<Offer> offers = new ArrayList<>(); 
		for(Offer singleOfferInquiry: offersInquiry){
			offers.add(prepareSignleOfferToBuy(singleOfferInquiry, stockOfDay));
		}
		return offers;
	}

	@Override
	public List<ShareBalance> getSharesBalances(BrokerageAuthentication authentication) {
		return convertToShareBalance(mapper.mapAsList(brokerageRepository.findStockPortfolio(authentication.getPesel()), StockPortfolioTo.class));
	}

	@Override
	public void confirmBuy(BrokerageAuthentication authentication, List<Offer> offers, ConfirmationFromBank payConf) {
	List<StockPortfolioTo> stockPortfolios = mapper.mapAsList(brokerageRepository.findStockPortfolio(authentication.getPesel()), StockPortfolioTo.class);
		updateStockPortfolios(prepareNewStockPortfolio(authentication, stockPortfolios, offers));
	}

	@Override
	public void confirmSell(BrokerageAuthentication authentication, List<Offer> offers) {
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

	private Offer prepareSignleOfferToBuy(Offer singlefferInquiry, StockOfDay stockOfDay) throws WrongParameterException {
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
	
	private List<StockPortfolioTo> prepareNewStockPortfolio(BrokerageAuthentication authentication, List<StockPortfolioTo> stockPortfolios, List<Offer> boughtShares){
		List<StockPortfolioTo> newStockPortfolioTos = stockPortfolios;
		PlayerTo player = getPlayer(authentication);
		boolean existShare = false;
		for (Offer boughtShare : boughtShares) {
			for (StockPortfolioTo stockPortfolioTo : newStockPortfolioTos) {
				existShare = updateAmount(existShare, boughtShare, stockPortfolioTo);
			}
			addNewShareInPortfolio(newStockPortfolioTos, player, existShare, boughtShare);
			existShare = false;
		}
		return newStockPortfolioTos;
	}

	private void addNewShareInPortfolio(List<StockPortfolioTo> newStockPortfolioTos, PlayerTo player,
			boolean existShare, Offer boughtShare) {
		if(!existShare){
			newStockPortfolioTos.add(new StockPortfolioTo(boughtShare.getCompanyCode(), boughtShare.getAmount(), player));
		}
	}

	private boolean updateAmount(boolean existShare, Offer boughtShare, StockPortfolioTo stockPortfolioTo) {
		if(boughtShare.getCompanyCode().equals(stockPortfolioTo.getCompanyCode())){
			existShare = true;
			stockPortfolioTo.setAmount(stockPortfolioTo.getAmount()+boughtShare.getAmount());
		}
		return existShare;
	}
	
	private void updateStockPortfolios(List<StockPortfolioTo> newStockPortfolios){
		brokerageRepository.save(mapper.mapAsList(newStockPortfolios, StockPortfolioEntity.class));
	}
	
	private PlayerTo getPlayer(BrokerageAuthentication authentication) {
		PlayerEntity entity = playerRepository.findPlayerByPesel(authentication.getPesel()).get(FIRST_ITEM_INDEX);
		PlayerTo playerTo =  mapper.map(entity, PlayerTo.class);
		return playerTo;
	}

}
