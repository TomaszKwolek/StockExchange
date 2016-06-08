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
}
