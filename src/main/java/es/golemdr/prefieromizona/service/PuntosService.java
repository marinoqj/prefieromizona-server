package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

