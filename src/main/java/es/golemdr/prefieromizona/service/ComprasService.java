package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import es.golemdr.prefieromizona.domain.Compra;
import es.golemdr.prefieromizona.repository.ComprasRepository;

@Service
public class ComprasService {

		@Autowired
		private ComprasRepository comprasRepository;


		public List<Compra> getCompras() {

			return comprasRepository.findAll();

		}


		public List<Compra> getCompras(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return comprasRepository.findAll(paginacion).getContent();

		}


		public int getTotalCompras(){

			return comprasRepository.findAll().size();

		}


		public Compra insertarActualizarCompra(Compra compra) {

			return comprasRepository.save(compra);

		}


		public Compra getCompraById(Long idCompra) {

			return comprasRepository.findById(idCompra).get();

		}


		public void borrarCompra(Long idCompra) {

			comprasRepository.deleteById(idCompra);

		}


}

