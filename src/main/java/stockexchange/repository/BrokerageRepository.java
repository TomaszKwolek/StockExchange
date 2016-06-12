package stockexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockexchange.model.entity.StockPortfolioEntity;

@Repository
public interface BrokerageRepository extends JpaRepository<StockPortfolioEntity, Long>{
	
	@Query("from StockPortfolioEntity spe join fetch spe.player p where p.pesel=?1")
 	List<StockPortfolioEntity> findStockPortfolio(String playerPesel);

}
