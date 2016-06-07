package stockexchange.stockexchange;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.dao.impl.StockExchangeDaoImpl;

@Service
public class StockExchangeImpl implements StockExchange{

	@Autowired
	private StockExchangeDaoImpl stockExchangeDao;
	
	@Override
	public List<StockOfDay> getStockOfDays(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}


}
