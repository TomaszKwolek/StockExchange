package stockexchange.stockexchange;

import java.util.Date;
import java.util.List;

public interface StockExchange {

	List<StockOfDay> getStockOfDays(Date fromDate, Date toDate);
	List<StockOfDay> getStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate);
	Date getFirstDateOnSE();
	Date getLastDateOnSE();
}

