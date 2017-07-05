package mx.com.beo.models;

public class PerfilBean {
	
	private String idPersona;
	private String idUser;
	private String idCreds;
	private String canal;
	private String nombreSistema;
	private String url;
	
	 
	
	public PerfilBean( String url,String idUser, String idCreds, String canal, String nombreSistema,String idPersona) {
		super();
		this.idPersona = idPersona;
		this.idUser = idUser;
		this.idCreds = idCreds;
		this.canal = canal;
		this.nombreSistema = nombreSistema;
		this.url = url;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdCreds() {
		return idCreds;
	}
	public void setIdCreds(String idCreds) {
		this.idCreds = idCreds;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getNombreSistema() {
		return nombreSistema;
	}
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
