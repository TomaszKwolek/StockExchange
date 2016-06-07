package stockexchange.dao;

import stockexchange.stockexchange.Share;

public interface DataWriterDao {

	void savePriceOfShare(Share share);
	
}
