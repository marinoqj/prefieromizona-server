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
import es.golemdr.prefieromizona.domain.Comercio;
import es.golemdr.prefieromizona.ext.Constantes;
import es.golemdr.prefieromizona.service.ComerciosService;


@RestController
public class ComerciosController {

	private static final Logger log = LogManager.getLogger(ComerciosController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private ComerciosService comerciosService;


	@GetMapping(UrlConstants.COMERCIOS)
	public @ResponseBody List<Comercio> listadoComercios() {

		List<Comercio> comercios = null;
		comercios = comerciosService.getComercios();

		return comercios;
	}


	@GetMapping(UrlConstants.COMERCIOS_ID)
	public ResponseEntity<?> recuperarComercio(@PathVariable("id") Long idComercio) {

		ResponseEntity<?> resultado = null;
		Comercio comercio = null;

		try {

			comercio = comerciosService.getComercioById(idComercio);
			log.debug(objectMapper.writeValueAsString(comercio));

			resultado = new ResponseEntity<Comercio>(comercio, HttpStatus.OK);

		}catch (Exception e) {
			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n comercio con ID " + idComercio, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@PostMapping(UrlConstants.COMERCIOS)
	public ResponseEntity<?> crearComercio(@RequestBody Comercio comercio) throws JsonProcessingException {

		log.debug(objectMapper.writeValueAsString(comercio));

		comercio = comerciosService.insertarActualizarComercio(comercio);

		return new ResponseEntity<Comercio>(comercio, HttpStatus.CREATED);
	}

	@PutMapping(UrlConstants.COMERCIOS)
	public ResponseEntity<?> actualizarComercio(@RequestBody Comercio comercio) {

		ResponseEntity<?> resultado = null;

		try {

			log.debug(objectMapper.writeValueAsString(comercio));
			comercio = comerciosService.insertarActualizarComercio(comercio);

			resultado = new ResponseEntity<Comercio>(comercio, HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n comercio con ID " + comercio.getIdComercio(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}

	@DeleteMapping(UrlConstants.COMERCIOS_ID)
	public ResponseEntity<?> borrarComercio(@PathVariable("id") Long idComercio) {

		ResponseEntity<?> resultado = null;

		try {

			comerciosService.borrarComercio(idComercio);
			resultado = new ResponseEntity<String>("El comercio se borr? correctamente", HttpStatus.OK);

		}catch (Exception e) {

			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n comercio con ID " + idComercio, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}

	@GetMapping(UrlConstants.COMERCIOS_PAGINADO)
	public List<Comercio> listadoComerciosPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Comercio> resultado = null;
		resultado = comerciosService.getComercios(inicio, elementosXpagina);

		int total = 0;
		total = comerciosService.getTotalComercios();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));
		
		return resultado;
	}

}

