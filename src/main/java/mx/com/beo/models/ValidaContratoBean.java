package mx.com.beo.models;

public class ValidaContratoBean {
	
	
	private String usuario;
	private String contratoAceptado; 
	private String url;
	
	 
	
	
	public ValidaContratoBean(String usuario, String contratoAceptado, String url) {
		super();
		this.usuario = usuario;
		this.contratoAceptado = contratoAceptado;
		this.url = url;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContratoAceptado() {
		return contratoAceptado;
	}
	public void setContratoAceptado(String contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
