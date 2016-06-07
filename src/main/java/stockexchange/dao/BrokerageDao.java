package stockexchange.dao;

import java.util.List;

import stockexchange.brokerage.ShareBalance;

public interface BrokerageDao {

	List<ShareBalance> getStockBalance(int playersPesel);
	void updateStockBalance(List<ShareBalance> cashBalance);
	
}
