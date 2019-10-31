package tconqserver.tconqserv.repositories;

import tconqserver.tconqserv.entities.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}