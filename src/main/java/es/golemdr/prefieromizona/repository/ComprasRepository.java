package es.golemdr.prefieromizona.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.prefieromizona.domain.Compra;

@Repository
public interface ComprasRepository extends JpaRepository<Compra, Long>{

}
