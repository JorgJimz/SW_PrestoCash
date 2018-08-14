package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Mora.findAll", query = "SELECT m FROM Mora m")
public class Mora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FECHA_MORA")
	private String fechaMora;

	@Column(name = "FECHA_VENCIMIENTO")
	private String fechaVencimiento;

	private double importe;

	private int status;

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY)
	private Contrato contrato;

	public Mora() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaMora() {
		return this.fechaMora;
	}

	public void setFechaMora(String fechaMora) {
		this.fechaMora = fechaMora;
	}

	public String getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}