package stockexchange.stockexchange;

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
		// TODO Auto-generated method stub
		return null;
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
