package stockexchange.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import stockexchange.model.entity.CashPortfolioEntity;

public interface CashPortfolioRepository extends JpaRepository<CashPortfolioEntity, Long>{
	
	@Query("from CashPortfolioEntity cpe join fetch cpe.player p where p.pesel=?1")
 	List<CashPortfolioEntity> findCashPortfolio(String playerPesel);
	
	@Query("update CashPortfolioEntity set amount=?2 where id=?1")
	List<CashPortfolioEntity> updateCashPortfolio(int id, BigDecimal newAmount);
	

}
