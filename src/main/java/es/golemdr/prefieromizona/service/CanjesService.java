package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;
import java.util.NoSuchElementException;

import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.repository.PuntosRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import es.golemdr.prefieromizona.domain.Canje;
import es.golemdr.prefieromizona.repository.CanjesRepository;

import javax.transaction.Transactional;

@Service
public class CanjesService {

		private static final Logger log = LogManager.getLogger(CanjesService.class);

		@Autowired
		private CanjesRepository canjesRepository;

		@Autowired
		private PuntosRepository puntosRepository;

		public List<Canje> getCanjes() {

			return canjesRepository.findAll();

		}


		public List<Canje> getCanjes(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return canjesRepository.findAll(paginacion).getContent();

		}


		public int getTotalCanjes(){

			return canjesRepository.findAll().size();

		}


		@Transactional
		public Canje insertarActualizarCanje(Canje canje) throws Exception {

			Punto puntosClienteComercio = null;
			Punto filtro = new Punto();

			filtro.setCliente(canje.getCliente());
			filtro.setComercio(canje.getComercio());

			Example<Punto> unPunto = Example.of(filtro);

			try{
				puntosClienteComercio = puntosRepository.findOne(unPunto).get();
			}catch (NoSuchElementException ex){
				log.error("No se encontraron puntos para el cliente en el comercio");
				throw new Exception("No se puende canjear puntos porque el cliente no tiene ninguno");
			}


			if(puntosClienteComercio.getTotal() > canje.getPuntos()){

				puntosClienteComercio.setTotal(puntosClienteComercio.getTotal() - canje.getPuntos());

			}else{

				throw new Exception("El cliente no tiene tantos puntos como se quieren canjear");
			}


			puntosRepository.save(puntosClienteComercio);

			return canjesRepository.save(canje);

		}


		public Canje getCanjeById(Long idCanje) {

			return canjesRepository.findById(idCanje).get();

		}


		public void borrarCanje(Long idCanje) {

			canjesRepository.deleteById(idCanje);

		}


}

