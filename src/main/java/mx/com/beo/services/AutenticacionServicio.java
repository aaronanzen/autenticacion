package mx.com.beo.services;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;

import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.ValidaContratoBean;

public interface AutenticacionServicio {
	
	public JSONObject servicioCredenciales(CambioContrasenaBean bean);
	
	public JSONObject consultaDatosBasicos(ConsultaDatoBasicoBean basicoBean );
	
	
	public JSONObject consultaServiciosContratados(ConsultaServicioContratadoBean basicoBean );
	
	public JSONObject envioNotificaciones(EnvioNotificacioneBean envioNotificacioneBean );
	
	public JSONObject cambioBandera(ValidaContratoBean bean);
	
	public JSONObject consultaPerfiles(PerfilBean basicoBean );
	
	
	public JSONObject getValidaHeaders(RequestEntity<Object> requestEntity);
	
	
	public Map<String, Object> getContratoAceptado(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot, ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean, PerfilBean perfil);
	 
	public Map<String,Object> banderaAcceso(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot,
			ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean,
			PerfilBean perfil,
			ValidaContratoBean validaContrato);
	
	public JSONObject error403();
}
