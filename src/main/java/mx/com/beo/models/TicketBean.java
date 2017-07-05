package mx.com.beo.models;

public class TicketBean {
	
	private String idUser;
	private String idCreds;
	
	public TicketBean() { 
	}
	
	
	public TicketBean(String idUser, String idCreds) {
		super();
		this.idUser = idUser;
		this.idCreds = idCreds;
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
	
	

}
