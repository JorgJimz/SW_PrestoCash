package common;

public class Header {
	private boolean Status;
	private String Mensaje;
	private String Detalle;

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public String getDetalle() {
		return Detalle;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

}
