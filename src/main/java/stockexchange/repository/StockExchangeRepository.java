package stockexchange.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import stockexchange.model.entity.StockPriceEntity;

public interface StockExchangeRepository extends JpaRepository<StockPriceEntity, Long> {
	
	 	@Query("FROM StockPriceEntity WHERE date=(SELECT MIN(date) FROM StockPriceEntity)")
	 	List<StockPriceEntity> findFirstDateOnSE();
	 
	 	@Query("FROM StockPriceEntity WHERE date=(SELECT MAX(date) FROM StockPriceEntity)")
	 	List<StockPriceEntity> findLastDateOnSE();
	 	
		@Query("from StockPriceEntity spe where date>=?1 and date<=?2")
	 	List<StockPriceEntity> findStockOfDays(Date fromDate, Date toDate);
		
		@Query("from StockPriceEntity spe where company_code=?1 and date>=?2 and date<=?3")
	 	List<StockPriceEntity> findStockOfDaysForCompany(String companyCode, Date fromDate, Date toDate);
}
