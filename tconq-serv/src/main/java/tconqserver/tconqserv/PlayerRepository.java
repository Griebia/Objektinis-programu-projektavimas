package tconqserver.tconqserv;

import org.springframework.data.jpa.repository.JpaRepository;

interface PlayerRepository extends JpaRepository<Player, Long> {

}