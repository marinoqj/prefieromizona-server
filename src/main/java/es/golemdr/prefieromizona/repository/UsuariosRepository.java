package es.golemdr.prefieromizona.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.prefieromizona.domain.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

}
