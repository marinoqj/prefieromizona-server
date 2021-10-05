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

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return comprasRepository.findAll(paginacion).getContent();

		}
		
		public List<Compra> getComprasComercio(Long idComercio) {
			
			List<Compra> result = null;


			Compra sample = new Compra();
			Comercio comercio = new Comercio();
			comercio.setIdComercio(idComercio);
			sample.setComercio(comercio);
			
			ExampleMatcher matcher = ExampleMatcher.matchingAll();
			Example<Compra> example = Example.of(sample, matcher);
			
			result = comprasRepository.findAll(example);

			return result;

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

