package mx.com.beo.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 

import static io.restassured.RestAssured.given; 

import java.util.HashMap;
import java.util.Map;
 

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	  
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    { 
//       -------------------cambioContrasena------------------------------------------
    	try{
    		
    		
    		
    		System.out.println("incio Cambio contraseña");

    		Map<String,Object> mapContrasena=new HashMap<String, Object>();
        	 
        	
        	String bodyContrasena="{"
        			+ "\"idPersona\": \"1222\","
        			+ "\"ticket\": {"
        			+ "\"id_user\": \"123\","
        			+ "\"id_creds\": \"123\""
        			+ " },"
        			+ "\"canal\": \"1\","
        			+ "\"otp\": \"otp\","
        			+ "\"oldPassword\": \"123\","
        			+ "\"newPassword\": \"123\","
        			+ "\"confirmNewPassword\": \"1\""
        			+ "}";
        	 
        	String urlContrasena=System.getenv("protocolo2")+"://"+System.getenv("basepath2")+":"+System.getenv("puerto2")+"/"+System.getenv("servicio2");
  
//        	String urlContrasena="http://200.39.24.141/BEO/cambioContrasena";
     
     
     
// http://200.39.24.141/BEO/cambioContrasena
        	
//        	---------------------------------------------------------------------------------------
        	
        	
        	
        	 given(). 
        			headers(mapContrasena)
    				.contentType("application/json").body(bodyContrasena)
    			
    				.when().post(urlContrasena)
    				.then()
    				.log().all(); 
    		
    		
    		
    		
//    		-------------------------------------Consulta datos basicos-.------------------------------------------


            System.out.println("incio Consulta datos basicos");

    		Map<String,Object> map=new HashMap<String, Object>();
        	 
        	
        	String body="{"
        			+ "\"idPersona\": \"1025478963\","
        					+ "\"ticket\": {"
        							+ "\"id_user\": \"\","
        									+ "\"id_creds\": \"\""
        											+ "},"
        											+ "\"canal\": \"CUBA1234575SDF\""
        													+ "}";
        	 
        	String url=System.getenv("protocolo5")+"://"+System.getenv("basepath5")+":"+System.getenv("puerto5")+"/"+System.getenv("servicio5");
        	
//        	String url= "http://200.39.24.141/BEO/consultaDatosBasicos";
        	
        	
//"http://200.39.24.141/BEO/consultaDatosBasicos";
        	
        	
        	
        	
        	
//        	---------------------------------------------------------------------------------------
        	
        	System.out.println("Inicio consulta Servicios Contratados");
        	
        	 given(). 
        			headers(map)
    				.contentType("application/json").body(body)
    			
    				.when().post(url)
    				.then()
    				.log().all(); 
        	 
        	 
        	 System.out.println("Inicio consultaServiciosContratados ");
//        	 -------------------------------------------------------------------
//        	 ---------------------consultaServiciosContratados----------------------------------------------
        	 Map<String,Object> mapAccesoCLi=new HashMap<String, Object>();
        	 
        	  
     
        	String urlAccesoCli=System.getenv("protocolo6")+"://"+System.getenv("basepath6")+":"+System.getenv("puerto6")+"/"+System.getenv("servicio6");
        	 
 
//        	String urlAccesoCli="http://200.39.24.141/BEO/consultaServiciosContratados";
         	 
         	 given(). 
         			headers(mapAccesoCLi)
     				.contentType("application/json").body(body)
     			
     				.when().post(urlAccesoCli)
     				.then()
     				.log().all(); 


  System.out.println("envio notificación"); 
//	 -------------------------------------------------------------------
//	 ---------------------envio notificacion----------------------------------------------
	 Map<String,Object> mapNotificacion=new HashMap<String, Object>();
	


	 String urlNotificacion=System.getenv("protocolo3")+"://"+System.getenv("basepath3")+":"+System.getenv("puerto3")+"/"+System.getenv("servicio3");
 
//	String urlNotificacion="http://200.39.24.141/BEO/envioNotificaciones"; 
//	---------------------------------------------------------------------------------------
	
	String bodyNotificacion="{"
			+ "\"idPersona\": \"2222\","
			+ "\"ticket\": {"
			+ "\"id_user\": \"123\","
			+ "\"id_creds\": \"123\""
			+ "},"
			+ "\"tipoMensaje\": \"mensaje\","
			+ "\"tipoNotificacion\": \"notificacion\","
			+ "\"from\": \"123\","
			+ "\"to\": \"321\","
			+ "\"mapParameters\": {"
			+ "\"key\": \"1223333\""
			+ " },"
			+ "\"canal\": \"1\""
			+ "}";
	
	
	 given(). 
			headers(mapNotificacion)
			.contentType("application/json").body(bodyNotificacion)
		
			.when().post(urlNotificacion)
			.then()
			.log().all(); 
	 
//	 ----------------------------------------------------------------------------------------------------------------
	 System.out.println("Inicio Valida contrato ");
//	 -------------------------------------------------------------------
//	 ---------------------consultaServiciosContratados----------------------------------------------
	 Map<String,Object> mapValidacontrato=new HashMap<String, Object>();
	 
	 String urlValidaContrato=System.getenv("protocolo4")+"://"+System.getenv("basepath4")+":"+System.getenv("puerto4")+"/"+System.getenv("servicio4"); 
	 
//	String urlValidaContrato="http://200.39.24.141/BEO/Validacontrato";
 
  
		String bodyValidaContrato="{"
								+ " \"Usuario\": 1,"
								+ "	 \"ContratoAceptado\": 1"
								+ "}";
 	 
 	 given(). 
 			headers(mapValidacontrato)
				.contentType("application/json").body(bodyValidaContrato)
			
				.when().post(urlValidaContrato)
				.then()
				.log().all();

    		
    	} catch (Exception e) {
    	      System.out.println(""+e.getMessage());
    	}
    	
    	
    	 
    	 
    	 
    }
}
