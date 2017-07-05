package mx.com.beo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:/application.properties")
public class Urls {

	@Value("${cambioContrasena}")
	private String cambioContrasena;

	@Value("${consultaDatosBasicos}")
	private String consultaDatosBasicos;

	@Value("${consultaServiciosContratados}")
	private String consultaServiciosContratados;

	@Value("${consultaPerfiles}")
	private String consultaPerfiles;
	
	@Value("${validacontrato}")
	private String validacontrato;


	public String getConsultaDatosBasicos() {
		return consultaDatosBasicos;
	}

	public void setConsultaDatosBasicos(String consultaDatosBasicos) {
		this.consultaDatosBasicos = consultaDatosBasicos;
	}

	public String getCambioContrasena() {
		return cambioContrasena;
	}

	public void setCambioContrasena(String cambioContrasena) {
		this.cambioContrasena = cambioContrasena;
	}

	public String getConsultaServiciosContratados() {
		return consultaServiciosContratados;
	}

	public void setConsultaServiciosContratados(String consultaServiciosContratados) {
		this.consultaServiciosContratados = consultaServiciosContratados;
	}

	public String getConsultaPerfiles() {
		return consultaPerfiles;
	}

	public void setConsultaPerfiles(String consultaPerfiles) {
		this.consultaPerfiles = consultaPerfiles;
	}

	public String getValidacontrato() {
		return validacontrato;
	}

	public void setValidacontrato(String validacontrato) {
		this.validacontrato = validacontrato;
	}

	

	


}
