package es.golemdr.prefieromizona.service;


import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.OrderBy;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.domain.Comercio;
import es.golemdr.prefieromizona.domain.Compra;
import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.repository.ComprasRepository;
import es.golemdr.prefieromizona.repository.PuntosRepository;

@Service
public class ComprasService {

		private static final Logger log = LogManager.getLogger(ComprasService.class);

		@Autowired
		private ComprasRepository comprasRepository;

		@Autowired
		private PuntosRepository puntosRepository;


		public List<Compra> getCompras() {

			return comprasRepository.findAll();

		}


		public List<Compra> getCompras(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina, Sort.by("fechaCompra").descending());

			return comprasRepository.findAll(paginacion).getContent();

		}
		
		public List<Compra> getCompras(Long id, String tipo, int inicio, int elementosXpagina) {
			
			List<Compra> result = null;
			Compra sample = new Compra();
			Comercio comercio = new Comercio();
			Cliente cliente = new Cliente();

			if (tipo.equals("comercio")) {
				comercio.setIdComercio(id);
				sample.setComercio(comercio);
			} else if (tipo.equals("cliente")) {
				cliente.setIdCliente(id);
				sample.setCliente(cliente);				
			}

			
			ExampleMatcher matcher = ExampleMatcher.matchingAll();
			Example<Compra> example = Example.of(sample, matcher);
			
			Sort criterio = Sort.by("fechaCompra").descending();
			
			if(inicio == 0 && elementosXpagina == 0) {
			
				result = comprasRepository.findAll(example, criterio);	
				
			}else {
			
				Pageable paginacion = PageRequest.of(inicio, elementosXpagina, criterio);
			
				result = comprasRepository.findAll(example, paginacion).getContent();
			}
			

			return result;

		}
				
		
		public List<Compra> getComprasComercio(Long idComercio, int inicio, int elementosXpagina) {
			return getCompras(idComercio, "comercio", inicio, elementosXpagina);
		}
		
		public List<Compra> getComprasCliente(Long idCliente, int inicio, int elementosXpagina) {
			return getCompras(idCliente, "cliente", inicio, elementosXpagina);
		}
		
		public int getTotalComprasComercio(Long idComercio){
			return getCompras(idComercio, "comercio", 0,0).size();
		}

		public int getTotalComprasCliente(Long idCliente){
			return getCompras(idCliente, "cliente", 0,0).size();
		}

		public int getTotalCompras(){

			return comprasRepository.findAll().size();

		}

		@Transactional
		public Compra insertarActualizarCompra(Compra compra) {

			Punto puntosClienteComercio = null;
			Punto filtro = new Punto();

			filtro.setCliente(compra.getCliente());
			filtro.setComercio(compra.getComercio());

			Example<Punto> unPunto = Example.of(filtro);

			try{
				puntosClienteComercio = puntosRepository.findOne(unPunto).get();
			}catch (NoSuchElementException ex){
				log.info("No se encontraron puntos para el cliente en el comercio");
			}


			if(puntosClienteComercio!= null){

				puntosClienteComercio.setTotal(puntosClienteComercio.getTotal() + compra.getPuntos());

			}else{

				puntosClienteComercio = new Punto();
				puntosClienteComercio.setCliente(compra.getCliente());
				puntosClienteComercio.setComercio(compra.getComercio());
				puntosClienteComercio.setTotal(compra.getPuntos());

			}

			puntosRepository.save(puntosClienteComercio);


			return comprasRepository.save(compra);

		}


		public Compra getCompraById(Long idCompra) {

			return comprasRepository.findById(idCompra).get();

		}


		public void borrarCompra(Long idCompra) {

			comprasRepository.deleteById(idCompra);

		}


}

