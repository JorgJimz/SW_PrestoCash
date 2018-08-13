package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "mora")
@NamedQuery(name = "Mora.findAll", query = "SELECT m FROM Mora m")
public class Mora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "FECHA_MORA", nullable = false, length = 10)
	private String fechaMora;

	@Column(name = "FECHA_VENCIMIENTO", nullable = false, length = 10)
	private String fechaVencimiento;

	@Column(nullable = false, precision = 10, scale = 2)
	private double importe;

	@Column(nullable = false, length = 1)
	private String status;

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRATO_ID", nullable = false)
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}