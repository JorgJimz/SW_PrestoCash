package common;

public class Header {
	private boolean status;
	private String clase;
	private String mensaje;
	private Object causa;
	private Object detalle;

	public Header(String msg) {
		status = Constantes.OK;
		mensaje = msg;
	}

	public Header(Exception e) {
		status = Constantes.ERROR;
		clase = e.getClass().getName();
		mensaje = e.getMessage();
		causa = e.getCause();
		detalle = e.getStackTrace();
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public Object getCausa() {
		return causa;
	}

	public void setCausa(Object causa) {
		this.causa = causa;
	}

	public Object getDetalle() {
		return detalle;
	}

	public void setDetalle(Object detalle) {
		this.detalle = detalle;
	}

}
