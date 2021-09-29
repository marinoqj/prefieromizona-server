package es.golemdr.prefieromizona.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.prefieromizona.controller.constantes.UrlConstants;
import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.service.ClientesService;
import es.golemdr.prefieromizona.ext.Constantes;


@RestController
public class ClientesController {

	private static final Logger log = LogManager.getLogger(ClientesController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private ClientesService clientesService;


	@GetMapping(UrlConstants.CLIENTES)
	public @ResponseBody List<Cliente> listadoClientes() {

		List<Cliente> clientes = null;
		clientes = clientesService.getClientes();

		return clientes;
	}


	@GetMapping(UrlConstants.CLIENTES_ID)
	public ResponseEntity<?> recuperarCliente(@PathVariable("id") Long idCliente) {

		ResponseEntity<?> resultado = null;
		Cliente cliente = null;

		try {

			cliente = clientesService.getClienteById(idCliente);
			log.debug(objectMapper.writeValueAsString(cliente));

			resultado = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

		}catch (Exception e) {
			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n cliente con ID " + idCliente, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@PostMapping(UrlConstants.CLIENTES)
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) throws JsonProcessingException {

		log.debug(objectMapper.writeValueAsString(cliente));

		cliente = clientesService.insertarActualizarCliente(cliente);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

	@PutMapping(UrlConstants.CLIENTES)
	public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente) {

		ResponseEntity<?> resultado = null;

		try {

			log.debug(objectMapper.writeValueAsString(cliente));
			cliente = clientesService.insertarActualizarCliente(cliente);

			resultado = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n cliente con ID " + cliente.getIdCliente(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}

	@DeleteMapping(UrlConstants.CLIENTES_ID)
	public ResponseEntity<?> borrarCliente(@PathVariable("id") Long idCliente) {

		ResponseEntity<?> resultado = null;

		try {

			clientesService.borrarCliente(idCliente);
			resultado = new ResponseEntity<String>("El cliente se borr? correctamente", HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n cliente con ID " + idCliente, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}
	
	@GetMapping(UrlConstants.CLIENTES_PAGINADO)
	public List<Cliente> listadoClientesPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Cliente> resultado = null;
		resultado = clientesService.getClientes(inicio, elementosXpagina);

		int total = 0;
		total = clientesService.getTotalClientes();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));
		
		return resultado;
	}



}

