package es.golemdr.prefieromizona.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.prefieromizona.domain.Comercio;

@Repository
public interface ComerciosRepository extends JpaRepository<Comercio, Long>{

}
