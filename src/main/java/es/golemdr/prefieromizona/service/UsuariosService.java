package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.domain.Usuario;
import es.golemdr.prefieromizona.repository.PuntosRepository;
import es.golemdr.prefieromizona.repository.UsuariosRepository;

@Service
public class UsuariosService {

		@Autowired
		private UsuariosRepository usuariosRepository;
		
		/**
		 * Método para búsquedas exactas. Se utiliza la implementación por defecto de JPARepository
		 * @param example
		 * @return
		 */
		public Usuario findByExample(Example<Usuario> example){
			
			return usuariosRepository.findOne(example).get();
			
		}


}

