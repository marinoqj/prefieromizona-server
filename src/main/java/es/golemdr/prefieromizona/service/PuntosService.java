package es.golemdr.prefieromizona.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.repository.PuntosRepository;

@Service
public class PuntosService {

		@Autowired
		private PuntosRepository puntosRepository;


		public List<Punto> getPuntos() {

			return puntosRepository.findAll();

		}


		public List<Punto> getPuntos(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return puntosRepository.findAll(paginacion).getContent();

		}
		
		
		public List<Punto> getPuntosCliente(int inicio, int elementosXpagina, Long idCliente) {

			Punto sample = new Punto();
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			sample.setCliente(cliente);
			
			ExampleMatcher matcher = ExampleMatcher.matchingAll();
			Example<Punto> example = Example.of(sample, matcher);
			
//			TODO: solucionar paginación			
//			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return puntosRepository.findAll(example);

		}
		


		public int getTotalPuntos(){

			return puntosRepository.findAll().size();

		}


		public Punto insertarActualizarPunto(Punto punto) {

			return puntosRepository.save(punto);

		}


		public Punto getPuntoById(Long idPunto) {

			return puntosRepository.findById(idPunto).get();

		}


		public void borrarPunto(Long idPunto) {

			puntosRepository.deleteById(idPunto);

		}


}

