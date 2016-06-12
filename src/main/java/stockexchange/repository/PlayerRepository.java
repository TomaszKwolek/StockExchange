package stockexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockexchange.model.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{
	
	@Query("from PlayerEntity where pesel=?1")
 	List<PlayerEntity> findPlayerByPesel(String playerPesel);

}
