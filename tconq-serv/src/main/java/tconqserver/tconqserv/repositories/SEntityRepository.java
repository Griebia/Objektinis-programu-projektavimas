package tconqserver.tconqserv.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tconqserver.tconqserv.entities.SEntity;

@Repository
public interface SEntityRepository extends JpaRepository<SEntity, Long> {
}
