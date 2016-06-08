package stockexchange.stockexchange;

import java.util.Date;
import java.util.List;

import stockexchange.model.to.StockPriceTo;

public interface StockExchange {

	List<StockOfDay> getStockOfDays(Date fromDate, Date toDate);
	Date getFirstDateOnSE();
	Date getLastDateOnSE();
}

