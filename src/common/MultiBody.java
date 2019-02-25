package common;

import com.google.gson.annotations.Expose;

import model.Contrato;
import model.Egreso;

public class MultiBody {
	@Expose
	private Contrato contrato;

	@Expose
	private Egreso egreso;

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

}
