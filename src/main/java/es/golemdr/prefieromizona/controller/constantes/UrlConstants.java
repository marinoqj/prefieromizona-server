package es.golemdr.prefieromizona.controller.constantes;


public class UrlConstants {

	

	// CLIENTES
	public static final String CLIENTES = "/clientes";
	public static final String CLIENTES_ID = "/clientes/{id}";
	public static final String CLIENTES_PAGINADO = "/clientes/paginado";
	public static final String CLIENTES_BUSCAR_COD_CLIENTE = "/clientes/buscar/{codCliente}";

	

	// COMERCIOS
	public static final String COMERCIOS = "/comercios";
	public static final String COMERCIOS_ID = "/comercios/{id}";
	public static final String COMERCIOS_PAGINADO = "/comercios/paginado";
	public static final String COMERCIOS_BUSCAR_COD_COMERCIO= "/comercios/buscar/{codComercio}";

	

	// COMPRAS
	public static final String COMPRAS = "/compras";
	public static final String COMPRAS_ID = "/compras/{id}";
	public static final String COMPRAS_COMERCIO_ID = "/compras/comercio/{id}";
	public static final String COMPRAS_CLIENTE_ID = "/compras/cliente/{id}";
	public static final String COMPRAS_PAGINADO = "/compras/paginado";

	
	// CANJES
	public static final String CANJES = "/canjes";
	public static final String CANJES_ID = "/canjes/{id}";
	public static final String CANJES_PAGINADO = "/canjes/paginado";
	public static final String CANJES_COMERCIO_ID = "/canjes/{id}/comercio/";
	public static final String CANJES_CLIENTE_ID = "/canjes/{id}/cliente/";

	

	// PUNTOS
	public static final String PUNTOS = "/puntos";
	public static final String PUNTOS_ID = "/puntos/{id}";
	public static final String PUNTOS_PAGINADO = "/puntos/paginado";
	public static final String PUNTOS_CLIENTE = "/puntos/cliente/{idCliente}";


	// USUARIOS
	public static final String USUARIOS_LOGIN = "/usuarios/{login}";

	

}
