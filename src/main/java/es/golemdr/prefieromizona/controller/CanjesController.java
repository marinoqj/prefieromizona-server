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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.prefieromizona.controller.constantes.UrlConstants;
import es.golemdr.prefieromizona.domain.Canje;
import es.golemdr.prefieromizona.service.CanjesService;
import es.golemdr.prefieromizona.ext.Constantes;


@RestController
public class CanjesController {

	private static final Logger log = LogManager.getLogger(CanjesController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private CanjesService canjesService;


	@GetMapping(UrlConstants.CANJES)
	public @ResponseBody List<Canje> listadoCanjes() {

		List<Canje> canjes = null;
		canjes = canjesService.getCanjes();

		return canjes;
	}


	@GetMapping(UrlConstants.CANJES_ID)
	public ResponseEntity<?> recuperarCanje(@PathVariable("id") Long idCanje) {

		ResponseEntity<?> resultado = null;
		Canje canje = null;

		try {

			canje = canjesService.getCanjeById(idCanje);
			log.debug(objectMapper.writeValueAsString(canje));

			resultado = new ResponseEntity<Canje>(canje, HttpStatus.OK);

		}catch (Exception e) {
			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n canje con ID " + idCanje, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@PostMapping(UrlConstants.CANJES)
	public ResponseEntity<?> crearCanje(@RequestBody Canje canje) throws Exception {

		log.debug(objectMapper.writeValueAsString(canje));

		canje = canjesService.insertarActualizarCanje(canje);

		return new ResponseEntity<Canje>(canje, HttpStatus.CREATED);
	}

	@PutMapping(UrlConstants.CANJES)
	public ResponseEntity<?> actualizarCanje(@RequestBody Canje canje) {

		ResponseEntity<?> resultado = null;

		try {

			log.debug(objectMapper.writeValueAsString(canje));
			canje = canjesService.insertarActualizarCanje(canje);

			resultado = new ResponseEntity<Canje>(canje, HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n canje con ID " + canje.getIdCanje(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}

	@DeleteMapping(UrlConstants.CANJES_ID)
	public ResponseEntity<?> borrarCanje(@PathVariable("id") Long idCanje) {

		ResponseEntity<?> resultado = null;

		try {

			canjesService.borrarCanje(idCanje);
			resultado = new ResponseEntity<String>("El canje se borr? correctamente", HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n canje con ID " + idCanje, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@GetMapping(UrlConstants.CANJES_PAGINADO)
	public List<Canje> listadoCanjesPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {

		List<Canje> resultado = null;
		resultado = canjesService.getCanjes(inicio, elementosXpagina);

		int total = 0;
		total = canjesService.getTotalCanjes();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));

		return resultado;
	}



}

