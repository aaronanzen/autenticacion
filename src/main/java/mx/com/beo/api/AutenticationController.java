package mx.com.beo.api;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.MapParameterBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.TicketBean;
import mx.com.beo.models.Urls;
import mx.com.beo.models.ValidaContratoBean;
import mx.com.beo.services.AutenticacionServicio; 

@RestController          
@RequestMapping(value = "/autenticacion")
public class AutenticationController {

	// Inyeccion de dependencias del servicio
	@Autowired
	private AutenticacionServicio nameService;

	@Autowired
	private Urls urls;


	/**
	 * Servicio que realiza el cambio de contraseña
	 * @param requestEntity
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cambioContrasena")
	public ResponseEntity<Object> cambioContrasena(RequestEntity<Object> requestEntity) {


		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) requestEntity.getBody();
		JSONObject jsonResponse=null;
		try{
			TicketBean ticketBean=new  TicketBean(requestEntity.getHeaders().getFirst("id_user").toString(),
					requestEntity.getHeaders().getFirst("id_creds").toString());

			if(!requestEntity.getHeaders().getFirst("idPersona").equals("")){
				if(!requestEntity.getHeaders().getFirst("canal").equals("")){
					if(!requestEntity.getHeaders().getFirst("id_user").equals("")){
						if(!requestEntity.getHeaders().getFirst("id_creds").equals("")){ 
							CambioContrasenaBean bean =new CambioContrasenaBean( 
									map.get("otp").toString(), 
									map.get("oldPassword").toString(),
									map.get("newPassword").toString(),
									map.get("confirmNewPassword").toString(),
									urls.getCambioContrasena(),
									ticketBean, 
									requestEntity.getHeaders().getFirst("idPersona"), 
									requestEntity.getHeaders().getFirst("canal")); 

							if(map.get("newPassword").toString().equalsIgnoreCase(map.get("confirmNewPassword").toString())){

								JSONObject jsonObject= nameService.servicioCredenciales(bean); 
								return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);

							}else{

								jsonResponse=new JSONObject();
								jsonResponse.put("CodigoRespuesta","234");
								jsonResponse.put("DescRespuesta","Las contraseñas son diferentes");
								return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);

							}
						}else{
							return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
						}

					}else{
						return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
					}

				}else{
					return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
				}

			}else{
				return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
			}

		}catch (Exception e) {
			return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
		}

	}

	/**
	 * Tetodo de accesoCliente
	 * Descripción Recibe cono headers 
	 * - iv-user
	 * - iv-creds
	 * - iv-groups
	 * - numero-cliente
	 * - nombre-completo
	 * - tipo-authenticacion
	 * - contratoAceptado
	 * - fechaUltimoAcceso
	 * - Tipocanal
	 * - mail
	 * @param requestEntity
	 * @return
	 */


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/accesoCliente")
	public ResponseEntity<Object> accesoCliente(RequestEntity<Object> requestEntity){

 
		Map<String, Object> map = new HashMap<String, Object>(); 
		map = (Map<String, Object>) requestEntity.getBody();   

		try{

			JSONObject varRestValidaHeaders=nameService.getValidaHeaders(requestEntity);
			String varRes=varRestValidaHeaders.get("respuestaStatus")+"";
			if(varRes.equals("200")){

				//		TODO----------------Llenado de datos en--ENVIO DE NOTIFICACION-------------------------------------------------------------
				TicketBean ticketBean=new TicketBean(requestEntity.getHeaders().getFirst("iv-user"),requestEntity.getHeaders().getFirst("iv-creds"));

				MapParameterBean mapParameterBean=new MapParameterBean("1");

				EnvioNotificacioneBean beanEnvioNot=new EnvioNotificacioneBean(
						requestEntity.getHeaders().getFirst("numero-cliente"),
						ticketBean,
						requestEntity.getHeaders().getFirst("Tipocanal"),
						requestEntity.getHeaders().getFirst("tipo-authenticacion"),
						requestEntity.getHeaders().getFirst("mail"),
						//        TODO-------------que datos se van a pasar---------------------------------------------------------------------------								
						"r@i.com", mapParameterBean, "1");

				//--------------------------Llenar datos de ConsultaDatoBasicoBean-------------------- 
				ConsultaDatoBasicoBean  basicoBean=new ConsultaDatoBasicoBean(
						urls.getConsultaDatosBasicos(),
						requestEntity.getHeaders().getFirst("numero-cliente"),
						requestEntity.getHeaders().getFirst("iv-user"), 
						requestEntity.getHeaders().getFirst("iv-creds"), 
						requestEntity.getHeaders().getFirst("Tipocanal")
						);

				//			------------------------------Llenado de datos  ConsultaServicioContratadoBean-----------------------------------
				ConsultaServicioContratadoBean consultaServicioContratadoBean=new ConsultaServicioContratadoBean(
						requestEntity.getHeaders().getFirst("numero-cliente"), 
						requestEntity.getHeaders().getFirst("iv-user"),
						requestEntity.getHeaders().getFirst("iv-creds"), 
						requestEntity.getHeaders().getFirst("Tipocanal"), 
						urls.getConsultaServiciosContratados());


				//		---------------------Creación de Objeto perfiles----------------------------------- 
				PerfilBean perfil=new PerfilBean(
						urls.getConsultaPerfiles(),
						basicoBean.getIdUser(), 
						basicoBean.getIdCreds(), 
						basicoBean.getCanal(),
						"MULTIVANET",
						basicoBean.getIdPersona());


				System.out.println("valida: "+urls.getValidacontrato());
				//					 -----------------------------Llenado de datos en-Valida contrato-----------------------------------------------------
				ValidaContratoBean validaContrato=new ValidaContratoBean(
						requestEntity.getHeaders().getFirst("numero-cliente"),
						map.get("banderaAcceso").toString(),
						urls.getValidacontrato());


				//			------------------------condición que nos valida si el contrato ya esta aceptado------------------------------------------

				if(requestEntity.getHeaders().getFirst("contratoAceptado").equalsIgnoreCase("1")){

					/**
					 * contratoAceptado trae 1.
					 * Eso quiere decur que el contrato ya esta aceptado
					 * El siguiente metodo consume los servicios que nos trarn la informacióin.
					 */ 
					Map<String,Object> jsonResp=nameService.getContratoAceptado(requestEntity, beanEnvioNot, basicoBean,
							consultaServicioContratadoBean,perfil);   
					return new ResponseEntity<Object>(jsonResp, HttpStatus.CREATED);


				}else{ 
					Map<String,Object> jsonResp=nameService.banderaAcceso(requestEntity, beanEnvioNot, basicoBean,
							consultaServicioContratadoBean,perfil,validaContrato);  
					return new ResponseEntity<Object>(jsonResp, HttpStatus.CREATED);

				}

			}else{

				return new ResponseEntity<Object>(varRestValidaHeaders, HttpStatus.CREATED);
			}


		}catch (Exception e) {
			return new ResponseEntity<Object>(nameService.error403(), HttpStatus.CREATED);
		}

	}


}
