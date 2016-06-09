package stockexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stockexchange.model.entity.StockPriceEntity;

public interface BrokerageRepository extends JpaRepository<StockPriceEntity, Long>{

	
}
