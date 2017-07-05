package mx.com.beo.models;

public class EnvioNotificacioneBean {
	
	private String idPersona;
	private TicketBean ticketBean;
	private String tipoMensaje;
	private String tipoNotificacion;
	private String from;
	private String to;
	private MapParameterBean key;
	private String canal;
	
	
	
	
	
	
	public EnvioNotificacioneBean(String idPersona, TicketBean ticketBean, String tipoMensaje, String tipoNotificacion,
			String from, String to, MapParameterBean key, String canal) {
		super();
		this.idPersona = idPersona;
		this.ticketBean = ticketBean;
		this.tipoMensaje = tipoMensaje;
		this.tipoNotificacion = tipoNotificacion;
		this.from = from;
		this.to = to;
		this.key = key;
		this.canal = canal;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public TicketBean getTicketBean() {
		return ticketBean;
	}
	public void setTicketBean(TicketBean ticketBean) {
		this.ticketBean = ticketBean;
	}
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public MapParameterBean getKey() {
		return key;
	}
	public void setKey(MapParameterBean key) {
		this.key = key;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	
	
	

}
