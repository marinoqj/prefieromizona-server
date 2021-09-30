package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.domain.Comercio;
import es.golemdr.prefieromizona.repository.ComerciosRepository;

@Service
public class ComerciosService {

		@Autowired
		private ComerciosRepository comerciosRepository;


		public List<Comercio> getComercios() {

			return comerciosRepository.findAll();

		}


		public List<Comercio> getComercios(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return comerciosRepository.findAll(paginacion).getContent();

		}


		public int getTotalComercios(){

			return comerciosRepository.findAll().size();

		}


		public Comercio insertarActualizarComercio(Comercio comercio) {

			return comerciosRepository.save(comercio);

		}


		public Comercio getComercioById(Long idComercio) {

			return comerciosRepository.findById(idComercio).get();

		}


		public void borrarComercio(Long idComercio) {

			comerciosRepository.deleteById(idComercio);

		}
		
		/**
		 * Método para búsquedas exactas. Se utiliza la implementación por defecto de JPARepository
		 * @param example
		 * @return
		 */
		public Comercio findByExample(Example<Comercio> example){
			
			return comerciosRepository.findOne(example).get();
			
		}


}

