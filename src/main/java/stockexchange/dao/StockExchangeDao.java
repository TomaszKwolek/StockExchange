package stockexchange.dao;

import java.util.Date;

import stockexchange.stockexchange.StockOfDay;

public interface StockExchangeDao {

	StockOfDay getStockOfDay(Date data);
}
