package mx.com.beo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.ValidaContratoBean;

import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Service
public class AutenticacionServicioImpl implements AutenticacionServicio{




	public JSONObject servicioCredenciales(CambioContrasenaBean bean)  {

		String output=null;
		String resultado="";

		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try {

			URL url = new URL(bean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String inputs=

					"{ \"idPersona\": \""+bean.getIdPersona()+"\", "
							+ "\"ticket\": {"
							+ "  \"id_user\": \" "+bean.getTicketbean().getIdUser()+" \","
							+ " \"id_creds\": \" "+bean.getTicketbean().getIdCreds()+" \" "
							+ "},"
							+ " \"canal\": \" "+bean.getCanal()+" \","
							+ "\"otp\": \" "+bean.getOtp()+" \","
							+ "\"oldPassword\": \" "+bean.getOldPassword()+" \","
							+ " \"newPassword\": \" "+bean.getNewPassword()+"  \","
							+ "\"confirmNewPassword\": \" "+bean.getConfirmNewPassword()+" \""
							+ "}"; 


			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				System.out.println("Buffered  "+br);

				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}
			} 

			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);

			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return json;
	}



	/**
	 * netodo que consume el servicio consultaDatosBasicos 
	 * @return
	 */



	@SuppressWarnings("unchecked")
	public JSONObject consultaDatosBasicos(ConsultaDatoBasicoBean basicoBean )  {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;
		JSONObject jsonObject2=null;

		try { 
			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs= 
					" {"
							+ "\"idPersona\": \""+basicoBean.getIdPersona()+"\","
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}

			} 
			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);



			JSONArray array=(JSONArray) json.get("listaTelefonos");

			//			Creación de JSON  que se va a regresar 

			jsonObject2=new JSONObject();


			jsonObject2.put("listaTelefonos",array.get(0));
			jsonObject2.put("nombreRazonSocial",json.get("nombre"));
			jsonObject2.put("responseStatus", json.get("responseStatus"));
			jsonObject2.put("responseError", json.get("responseError"));


			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return jsonObject2;
	}




	//-----------------------------------------------------------------------------------------

	public JSONObject consultaServiciosContratados(ConsultaServicioContratadoBean basicoBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;   


		try { 
			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\": \""+basicoBean.getIdPersona()+"\","
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) { 
					resultado=resultado+output;
				}

			} 
			jsonParser = new JSONParser();
			json = (JSONObject) jsonParser.parse(resultado);

			json.remove("responseError");
			json.remove("responseStatus"); 


			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return json;
	}
	//	------------------------------------------------------------------------------------------------------
	//	----------------------------------Envio de Notificaciones---------------------------------------------
	//	------------------------------------------------------------------------------------------------------


	public JSONObject envioNotificaciones(EnvioNotificacioneBean envioNotificacioneBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try { 
			URL url = new URL("http://200.39.24.141/BEO/envioNotificaciones");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\": \""+envioNotificacioneBean.getIdPersona()+"\","
							+ "\"ticket\": {"
							+ "\"id_user\": \""+envioNotificacioneBean.getTicketBean().getIdUser()+"\","
							+ "\"id_creds\": \""+envioNotificacioneBean.getTicketBean().getIdCreds()+"\""
							+ " },"
							+ "\"tipoMensaje\": \""+envioNotificacioneBean.getTipoMensaje()+"\","
							+ "\"tipoNotificacion\": \""+envioNotificacioneBean.getTipoMensaje()+"\","
							+ " \"from\": \""+envioNotificacioneBean.getFrom()+"\","
							+ " \"to\": \""+envioNotificacioneBean.getTo()+"\","
							+ " \"mapParameters\": {"
							+ "\"key\":  \""+envioNotificacioneBean.getKey()+"\""
							+ " },"
							+ "  \"canal\": \"1\""
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}

			} 
			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);


			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return json;
	}



	public JSONObject cambioBandera(ValidaContratoBean bean)  {

		String output=null;
		String resultado="";

		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try {

			URL url = new URL(bean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String inputs=

					"{"
							+ " \"Usuario\": "+bean.getUsuario()+","
							+ " \"ContratoAceptado\": "+bean.getContratoAceptado()+""
							+ "}"; 


			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));


				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}
			} 

			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);

			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return json;
	}


	public JSONObject consultaPerfiles(PerfilBean basicoBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;   


		try { 
			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\": \""+basicoBean.getIdPersona()+"\","
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" ,"
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"nombreSistema\": \" "+basicoBean.getNombreSistema()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) { 
					resultado=resultado+output;
				}

			} 
			jsonParser = new JSONParser();
			json = (JSONObject) jsonParser.parse(resultado);




			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()); 

		} catch (IOException e) { 
			System.out.println(e.getMessage());

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return json;
	}



	@SuppressWarnings("unchecked")
	public JSONObject getValidaHeaders(RequestEntity<Object> requestEntity) {

		if(!requestEntity.getHeaders().getFirst("iv-user").equalsIgnoreCase("")){ 
			if(!requestEntity.getHeaders().getFirst("iv-creds").equalsIgnoreCase("")){ 
				if(!requestEntity.getHeaders().getFirst("iv-groups").equalsIgnoreCase("")){ 
					if(!requestEntity.getHeaders().getFirst("numero-cliente").equalsIgnoreCase("")){ 
						if(!requestEntity.getHeaders().getFirst("nombre-completo").equalsIgnoreCase("")){ 
							if(!requestEntity.getHeaders().getFirst("tipo-authenticacion").equalsIgnoreCase("")){ 
								if(!requestEntity.getHeaders().getFirst("contratoAceptado").equalsIgnoreCase("")){ 
									if(!requestEntity.getHeaders().getFirst("fechaUltimoAcceso").equalsIgnoreCase("")){ 
										if(!requestEntity.getHeaders().getFirst("Tipocanal").equalsIgnoreCase("")){ 
											if(!requestEntity.getHeaders().getFirst("mail").equalsIgnoreCase("")){  
												long resStatus=200;
												JSONObject jsonError=new JSONObject(); 
												jsonError.put("respuestaStatus",resStatus);   
												return  jsonError;
											}else{
												return error403();
											}
										}else{
											return error403();
										} 
									}else{
										return error403();
									}

								}else{
									return error403();
								}

							}else{
								return error403();
							}

						}else{
							return error403();
						}

					}else{
						return error403();
					}

				}else{
					return error403();
				}

			}else{
				return error403();
			}

		}else{
			return error403();
		}

	}



	/**
	 * 
	 * @param requestEntity
	 * @param beanEnvioNot
	 * @param basicoBean
	 * @param consultaServicioContratadoBean
	 * @param perfil
	 * @return  Merodo que se ejecuta en caso de que el contrato ya este aceptado
	 */

	@SuppressWarnings("unchecked") 
	public Map<String, Object> getContratoAceptado(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot, ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean, PerfilBean perfil) {
		try{
			Map<String,Object> jsonResponse=new LinkedHashMap<String,Object>();
			//				---------------------------Datos de respuesta----------------------------------------------------------
			jsonResponse.put("fechaUltimoAcceso",requestEntity.getHeaders().getFirst("fechaUltimoAcceso")); 
			jsonResponse.put("nombreUsuario",requestEntity.getHeaders().getFirst("nombre-completo"));
			jsonResponse.put("medioAcceso",requestEntity.getHeaders().getFirst("Tipocanal"));
			jsonResponse.put("mail",requestEntity.getHeaders().getFirst("mail"));
			//				----------------------------------------------------------------------------------------- 

			//				-----------------------------EJECUCION DE METODO---------------------------------------------
			JSONObject jsonEnvioNot= envioNotificaciones(beanEnvioNot);

			String envioNotStatus=jsonEnvioNot.get("responseStatus")+"";
			if(envioNotStatus.equals("200")){

				JSONObject varConsultas=getConsultas(perfil,basicoBean,consultaServicioContratadoBean);

				jsonResponse.putAll(varConsultas);

				return jsonResponse;

			}else{ 
				return  error403();

			}

		}catch (Exception e) {

			return  error403();
		}




	}

	@SuppressWarnings("unchecked")
	@Async
	private JSONObject getConsultas(PerfilBean perfil ,ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean) {

		JSONObject jsonResponse=new JSONObject();

		JSONObject jsonperfiles= consultaPerfiles(perfil);
		JSONObject jsonObject= consultaDatosBasicos(basicoBean);

		jsonResponse.put("nombreRazonSocial", jsonObject.get("nombreRazonSocial"));
		jsonResponse.put("responseStatus", jsonObject.get("responseStatus"));
		jsonResponse.put("responseError", jsonObject.get("responseError"));
		jsonResponse.put("listaTelefonos", jsonObject.get("listaTelefonos")); 

		JSONObject jsonObjectservicios= consultaServiciosContratados( consultaServicioContratadoBean );
		jsonResponse.put("consultaServiciosContratados", jsonObjectservicios);

		jsonperfiles.remove("responseError");
		jsonperfiles.remove("responseStatus");

		jsonResponse.putAll(jsonperfiles);

		return jsonResponse;


	}



	/**
	 * 
	 * @param requestEntity
	 * @param beanEnvioNot
	 * @param basicoBean
	 * @param consultaServicioContratadoBean
	 * @param perfil
	 * @return 
	 * @Description Metodo que se ejecuta en caso de que el contrato aun no este aceptado consume un servicio que 
	 * realiza el cambio de bandera como aceptado. 
	 */

	@SuppressWarnings("unchecked") 
	public Map<String,Object> banderaAcceso(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot,
			ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean,
			PerfilBean perfil,
			ValidaContratoBean validaContrato){


		try{

			Map<String,Object> jsonResponse=new LinkedHashMap<String,Object>();
			JSONObject jsonObject=null;
			Map<String, Object> map = new HashMap<String, Object>(); 
			map = (Map<String, Object>) requestEntity.getBody();

			if(map.get("banderaAcceso").toString().equalsIgnoreCase("1")){

				//				---------------------------Datos de respuesta----------------------------------------------------------
				jsonResponse.put("fechaUltimoAcceso",requestEntity.getHeaders().getFirst("fechaUltimoAcceso")); 
				jsonResponse.put("nombreUsuario",requestEntity.getHeaders().getFirst("nombre-completo"));
				jsonResponse.put("medioAcceso",requestEntity.getHeaders().getFirst("Tipocanal"));
				jsonResponse.put("mail",requestEntity.getHeaders().getFirst("mail"));
				//				---------------------------------------------------------------------------------------------

				//				-----------------------------EJECUCION DE METODO---------------------------------------------
				JSONObject jsonEnvioNotElse= envioNotificaciones(beanEnvioNot);
				String envioNotiStatus=jsonEnvioNotElse.get("responseStatus")+"";
				if(envioNotiStatus.equals("200")){

 

					JSONObject jsonResCambioBandera= cambioBandera(validaContrato);
					String varCambioBandera=jsonResCambioBandera.get("CodigoRespuesta")+"";
					//						 ---------------------------------------------------------------------------------------- 
					if(varCambioBandera.equals("0")){
						//-----------------------------Valida que el cambio de vandera de cambioBandera sea igual a 0---------------------


						JSONObject varConsultas=getConsultas(perfil,basicoBean,consultaServicioContratadoBean);

						jsonResponse.putAll(varConsultas);

						return jsonResponse;

					} else{ 
						return  error403();
					}

				}else{
					return  error403();
				}

			}else if(map.get("banderaAcceso").toString().equalsIgnoreCase("")){

				long resStatus=200;
				jsonObject=new JSONObject(); 
				jsonObject.put("mostrarContrato",true);
				jsonObject.put("respuestaStatus",resStatus);
				jsonObject.put("respuestaError",""); 
				return  jsonObject;
			}else {
				long resStatus=200;
				jsonObject=new JSONObject();  
				jsonObject.put("respuestaStatus",resStatus);
				jsonObject.put("respuestaError",""); 
				return  jsonObject;
			}

		}catch (Exception e) {    
			return  error403();
		}
	}

	/**
	 * 
	 * @return
	 * @Description Metodo donde manejamos el error 403. Este error se ejecuta en caso de que se produsca un error al consumir uno de los servicios.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject error403(){
		long resStatus=403;
		JSONObject jsonError=new JSONObject(); 
		jsonError.put("respuestaStatus",resStatus);
		jsonError.put("respuestaError","Por Definir");   
		return  jsonError;
	}



}
