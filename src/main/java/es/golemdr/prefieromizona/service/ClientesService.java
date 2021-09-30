package es.golemdr.prefieromizona.service;


import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.repository.ClientesRepository;

@Service
public class ClientesService {

		@Autowired
		private ClientesRepository clientesRepository;


		public List<Cliente> getClientes() {

			return clientesRepository.findAll();

		}


		public List<Cliente> getClientes(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return clientesRepository.findAll(paginacion).getContent();

		}


		public int getTotalClientes(){

			return clientesRepository.findAll().size();

		}


		public Cliente insertarActualizarCliente(Cliente cliente) {

			return clientesRepository.save(cliente);

		}


		public Cliente getClienteById(Long idCliente) {

			return clientesRepository.findById(idCliente).get();

		}


		public void borrarCliente(Long idCliente) {

			clientesRepository.deleteById(idCliente);

		}
		
		/**
		 * Método para búsquedas exactas. Se utiliza la implementación por defecto de JPARepository
		 * @param example
		 * @return
		 */
		public Cliente findByExample(Example<Cliente> example){
			
			return clientesRepository.findOne(example).get();
			
		}


}

