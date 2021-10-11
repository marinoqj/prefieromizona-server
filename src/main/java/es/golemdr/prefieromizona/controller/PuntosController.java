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
import es.golemdr.prefieromizona.domain.Punto;
import es.golemdr.prefieromizona.service.PuntosService;
import es.golemdr.prefieromizona.ext.Constantes;


@RestController
public class PuntosController {

	private static final Logger log = LogManager.getLogger(PuntosController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private PuntosService puntosService;
//
//
//	@GetMapping(UrlConstants.PUNTOS)
//	public @ResponseBody List<Punto> listadoPuntos() {
//
//		List<Punto> puntos = null;
//		puntos = puntosService.getPuntos();
//
//		return puntos;
//	}
//
//
//	@GetMapping(UrlConstants.PUNTOS_ID)
//	public ResponseEntity<?> recuperarPunto(@PathVariable("id") Long idPunto) {
//
//		ResponseEntity<?> resultado = null;
//		Punto punto = null;
//
//		try {
//
//			punto = puntosService.getPuntoById(idPunto);
//			log.debug(objectMapper.writeValueAsString(punto));
//
//			resultado = new ResponseEntity<Punto>(punto, HttpStatus.OK);
//
//		}catch (Exception e) {
//			log.error("Se produjo la excepci?n:" + e.getMessage());
//			resultado =  new ResponseEntity<String>("No se encontr? ning?n punto con ID " + idPunto, HttpStatus.NOT_FOUND);
//		}
//
//		return resultado;
//	}
//
//	@PostMapping(UrlConstants.PUNTOS)
//	public ResponseEntity<?> crearPunto(@RequestBody Punto punto) throws JsonProcessingException {
//
//		log.debug(objectMapper.writeValueAsString(punto));
//
//		punto = puntosService.insertarActualizarPunto(punto);
//
//		return new ResponseEntity<Punto>(punto, HttpStatus.CREATED);
//	}
//
//	@PutMapping(UrlConstants.PUNTOS)
//	public ResponseEntity<?> actualizarPunto(@RequestBody Punto punto) {
//
//		ResponseEntity<?> resultado = null;
//
//		try {
//
//			log.debug(objectMapper.writeValueAsString(punto));
//			punto = puntosService.insertarActualizarPunto(punto);
//
//			resultado = new ResponseEntity<Punto>(punto, HttpStatus.OK);
//
//		}catch (Exception e) {
//
//			log.error("Se produjo la excepci?n:" + e.getMessage());
//			resultado =  new ResponseEntity<String>("No se encontr? ning?n punto con ID " + punto.getIdPunto(), HttpStatus.NOT_FOUND);			
//		}
//
//		return resultado;
//	}
//
//	@DeleteMapping(UrlConstants.PUNTOS_ID)
//	public ResponseEntity<?> borrarPunto(@PathVariable("id") Long idPunto) {
//
//		ResponseEntity<?> resultado = null;
//
//		try {
//
//			puntosService.borrarPunto(idPunto);
//			resultado = new ResponseEntity<String>("El punto se borr? correctamente", HttpStatus.OK);
//
//		}catch (Exception e) {
//
//			log.error("Se produjo la excepci?n:" + e.getMessage());
//			resultado =  new ResponseEntity<String>("No se encontr? ning?n punto con ID " + idPunto, HttpStatus.NOT_FOUND);
//		}
//
//		return resultado;
//	}
//
	@GetMapping(UrlConstants.PUNTOS_PAGINADO)
	public List<Punto> listadoPuntosPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio,
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {

		List<Punto> resultado = null;
		resultado = puntosService.getPuntos(inicio, elementosXpagina);

		int total = 0;
		total = puntosService.getTotalPuntos();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));

		return resultado;
	}

	
	@GetMapping(UrlConstants.PUNTOS_CLIENTE)
	public List<Punto> listadoPuntosCliente(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, @PathVariable("idCliente") Long idCliente,
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {

		List<Punto> resultado = null;
		resultado = puntosService.getPuntosCliente(inicio, elementosXpagina, idCliente);

		int total = 0;
		total = puntosService.getTotalPuntos();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));

		return resultado;
	}



}

