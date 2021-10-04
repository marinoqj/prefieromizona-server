package es.golemdr.prefieromizona.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.prefieromizona.domain.Canje;

@Repository
public interface CanjesRepository extends JpaRepository<Canje, Long>{

}
