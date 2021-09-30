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
import es.golemdr.prefieromizona.domain.Comercio;
import es.golemdr.prefieromizona.domain.Compra;
import es.golemdr.prefieromizona.ext.Constantes;
import es.golemdr.prefieromizona.service.ComprasService;


@RestController
public class ComprasController {

	private static final Logger log = LogManager.getLogger(ComprasController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private ComprasService comprasService;


	@GetMapping(UrlConstants.COMPRAS)
	public @ResponseBody List<Compra> listadoCompras() {

		List<Compra> compras = null;
		compras = comprasService.getCompras();

		return compras;
	}


	@GetMapping(UrlConstants.COMPRAS_ID)
	public ResponseEntity<?> recuperarCompra(@PathVariable("id") Long idCompra) {

		ResponseEntity<?> resultado = null;
		Compra compra = null;

		try {

			compra = comprasService.getCompraById(idCompra);
			log.debug(objectMapper.writeValueAsString(compra));

			resultado = new ResponseEntity<Compra>(compra, HttpStatus.OK);

		}catch (Exception e) {
			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n compra con ID " + idCompra, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@PostMapping(UrlConstants.COMPRAS)
	public ResponseEntity<?> crearCompra(@RequestBody Compra compra) throws JsonProcessingException {

		log.debug(objectMapper.writeValueAsString(compra));

		compra = comprasService.insertarActualizarCompra(compra);

		return new ResponseEntity<Compra>(compra, HttpStatus.CREATED);
	}

	@PutMapping(UrlConstants.COMPRAS)
	public ResponseEntity<?> actualizarCompra(@RequestBody Compra compra) {

		ResponseEntity<?> resultado = null;

		try {

			log.debug(objectMapper.writeValueAsString(compra));
			compra = comprasService.insertarActualizarCompra(compra);

			resultado = new ResponseEntity<Compra>(compra, HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n compra con ID " + compra.getIdCompra(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}

	@DeleteMapping(UrlConstants.COMPRAS_ID)
	public ResponseEntity<?> borrarCompra(@PathVariable("id") Long idCompra) {

		ResponseEntity<?> resultado = null;

		try {

			comprasService.borrarCompra(idCompra);
			resultado = new ResponseEntity<String>("El compra se borr? correctamente", HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n compra con ID " + idCompra, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@GetMapping(UrlConstants.COMPRAS_PAGINADO)
	public List<Compra> listadoComprasPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Compra> resultado = null;
		resultado = comprasService.getCompras(inicio, elementosXpagina);

		int total = 0;
		total = comprasService.getTotalCompras();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));
		
		return resultado;
	

	}

}