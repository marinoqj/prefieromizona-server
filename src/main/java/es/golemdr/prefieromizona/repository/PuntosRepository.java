package es.golemdr.prefieromizona.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.prefieromizona.domain.Punto;

@Repository
public interface PuntosRepository extends JpaRepository<Punto, Long>{

}
