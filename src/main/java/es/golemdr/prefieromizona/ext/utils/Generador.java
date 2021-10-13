package es.golemdr.prefieromizona.ext.utils;

import java.security.SecureRandom;

public class Generador {
	
	private static SecureRandom aleatorio; 
	
	static {
		aleatorio = new SecureRandom();
	}
	
	public static int generaIdentificador(){
		
		return aleatorio.nextInt(1000000);
		
	}

}
