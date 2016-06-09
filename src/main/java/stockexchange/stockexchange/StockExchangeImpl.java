package stockexchange.stockexchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import stockexchange.dao.impl.StockExchangeDaoImpl;
import stockexchange.model.to.StockPriceTo;
import stockexchange.repository.StockExchangeRepository;

@Service
public class StockExchangeImpl implements StockExchange{

	@Autowired
	private StockExchangeRepository stockExchangeRepository;
	@Autowired
	private MapperFacade mapper;
	
	@Override
	public List<StockOfDay> getStockOfDays(Date fromDate, Date toDate) {
		List<StockPriceTo> stockPriceTos = mapper.mapAsList(stockExchangeRepository.findStockOfDays(fromDate, toDate), StockPriceTo.class);
		List<StockOfDay> stockOfDays = new ArrayList<>();
		for (StockPriceTo stockPriceTo : stockPriceTos) {
			Share newShare = new Share(stockPriceTo.getCompanyCode(), stockPriceTo.getPrice());
			boolean shareAdded = false;
			if(stockOfDays.size()==0){
				List<Share> shares = new ArrayList();
				shares.add(newShare);
				StockOfDay sod = new StockOfDay(stockPriceTo.getDate(), shares);
				stockOfDays.add(sod);
				shareAdded = true;
			}
			for (int i=0; i < stockOfDays.size(); i++) {
				if(stockPriceTo.getDate().equals(stockOfDays.get(i).getDate()) && !shareAdded){
					stockOfDays.get(i).addShareToStock(newShare);
					i=stockOfDays.size();
					shareAdded = true;
				}
			}
			if(!shareAdded){
				List<Share> shares = new ArrayList();
				shares.add(newShare);
				StockOfDay sod = new StockOfDay(stockPriceTo.getDate(), shares);
				stockOfDays.add(sod);
			}
		}
		return stockOfDays;
	}
	
	public List<StockOfDay> getStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate) {
		List<StockPriceTo> stockPriceTos = mapper.mapAsList(stockExchangeRepository.findStockOfDaysForCompany(companyCode, fromDate, toDate), StockPriceTo.class);
		List<StockOfDay> stockOfDays = new ArrayList<>();
		for (StockPriceTo stockPriceTo : stockPriceTos) {
			Share newShare = new Share(stockPriceTo.getCompanyCode(), stockPriceTo.getPrice());
			boolean shareAdded = false;
			if(stockOfDays.size()==0){
				List<Share> shares = new ArrayList();
				shares.add(newShare);
				StockOfDay sod = new StockOfDay(stockPriceTo.getDate(), shares);
				stockOfDays.add(sod);
				shareAdded = true;
			}
			for (int i=0; i < stockOfDays.size(); i++) {
				if(stockPriceTo.getDate().equals(stockOfDays.get(i).getDate()) && !shareAdded){
					stockOfDays.get(i).addShareToStock(newShare);
					i=stockOfDays.size();
					shareAdded = true;
				}
			}
			if(!shareAdded){
				List<Share> shares = new ArrayList();
				shares.add(newShare);
				StockOfDay sod = new StockOfDay(stockPriceTo.getDate(), shares);
				stockOfDays.add(sod);
			}
		}
		return stockOfDays;
	}

	@Override
	public Date getFirstDateOnSE() {
		return stockExchangeRepository.findFirstDateOnSE().get(0).getDate();
	}

	@Override
	public Date getLastDateOnSE() {
		return stockExchangeRepository.findLastDateOnSE().get(0).getDate();
	}

}
