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
        	 
        	String urlContrasena="http://172.30.9.135:8080/cambioContrasena";
  
        	
        	 given(). 
        			headers(mapContrasena)
    				.contentType("application/json").body(bodyContrasena)
    			
    				.when().post(urlContrasena)
    				.then()
    				.log().all(); 
    		
    		
 

    		
    	} catch (Exception e) {
    	      System.out.println(""+e.getMessage());
    	}
    	
    	
    	 
    	 
    	 
    }
}
