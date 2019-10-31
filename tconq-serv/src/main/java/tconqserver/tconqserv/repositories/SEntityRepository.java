package tconqserver.tconqserv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tconqserver.tconqserv.entities.SEntity;

@Repository
public interface SEntityRepository extends JpaRepository<SEntity, Long> {

    @Query(value = "SELECT * FROM sentities  WHERE x = ?1 and y = ?2",nativeQuery=true)
    Optional<SEntity> findbyXY(int x, int y);
    
    //@Query("select e from sentities e where e.player_id <> ?1")
    //ArrayList<SEntity> findAllByPlayerId(Long playerId);     // gets all entities that are not playerId player's
}
