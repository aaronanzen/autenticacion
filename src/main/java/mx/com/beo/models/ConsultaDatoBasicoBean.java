package mx.com.beo.models;

public class ConsultaDatoBasicoBean {
	
	
	private String idPersona;
	private String idUser;
	private String idCreds;
	private String canal;
	private String url;
	
	
	public ConsultaDatoBasicoBean() {
	}
	
	
	
	public ConsultaDatoBasicoBean( String url,String idPersona, String idUser, String idCreds, String canal) {
		super();
		this.idPersona = idPersona;
		this.idUser = idUser;
		this.idCreds = idCreds;
		this.canal = canal;
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
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
	
	
	

}
