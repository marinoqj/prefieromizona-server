package es.golemdr.prefieromizona.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.golemdr.prefieromizona.controller.constantes.UrlConstants;
import es.golemdr.prefieromizona.domain.Cliente;
import es.golemdr.prefieromizona.domain.Usuario;
import es.golemdr.prefieromizona.ext.Constantes;
import es.golemdr.prefieromizona.service.ClientesService;
import es.golemdr.prefieromizona.service.UsuariosService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UsuariosController {

	private static final Logger log = LogManager.getLogger(UsuariosController.class);

    @Autowired
    ObjectMapper objectMapper;

	@Resource
	private UsuariosService usuariosService;


	@GetMapping(UrlConstants.USUARIOS_LOGIN)
	public ResponseEntity<?> recuperarUsuario(@PathVariable("login") String login) {

		ResponseEntity<?> resultado = null;
		Usuario usuario = null;
		

		try {

			usuario = new Usuario();
			usuario.setLogin(login);
			
			Example<Usuario> filtro = Example.of(usuario);
			
			usuario = usuariosService.findByExample(filtro);
			log.debug(objectMapper.writeValueAsString(usuario));

			resultado = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

		}catch (Exception e) {
			log.error("Se produjo la excepci?n:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontr? ning?n cliente con login " + login, HttpStatus.NOT_FOUND);
		}

		return resultado;
	}


}

