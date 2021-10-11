package es.golemdr.prefieromizona.service;


import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.prefieromizona.domain.Canje;
import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.domain.Comercio;
import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.repository.CanjesRepository;
import es.golemdr.prefieromizona.repository.PuntosRepository;

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


		public List<Canje> getCanjesComercio(Long idComercio) {
			return getCanjes(idComercio, "comercio");
		}
		
		public List<Canje> getCanjesCliente(Long idCliente) {
			return getCanjes(idCliente, "cliente");
		}
		
		public List<Canje> getCanjes(Long id, String tipo) {
			List<Canje> result = null;


			Canje sample = new Canje();
			
			if (tipo.equals("comercio")) {
				Comercio comercio = new Comercio();
				
				comercio.setIdComercio(id);
				sample.setComercio(comercio);				
			} else if (tipo.equals("cliente")) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(id);
				sample.setCliente(cliente);				
			}
			
			ExampleMatcher matcher = ExampleMatcher.matchingAll();
			Example<Canje> example = Example.of(sample, matcher);
			
			result = canjesRepository.findAll(example);

			return result;
		}		


}

