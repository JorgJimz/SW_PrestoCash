package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Abono.findAll", query="SELECT a FROM Abono a")
public class Abono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ARQ_CAPITAL")
	private double arqCapital;

	@Column(name="ARQ_INTERES")
	private double arqInteres;

	private String fecha;

	@Column(name="NEO_CAPITAL")
	private double neoCapital;

	@Column(name="NEO_INTERES")
	private double neoInteres;

	//bi-directional many-to-one association to Contrato
	@ManyToOne(fetch=FetchType.LAZY)
	private Contrato contrato;

	public Abono() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getArqCapital() {
		return this.arqCapital;
	}

	public void setArqCapital(double arqCapital) {
		this.arqCapital = arqCapital;
	}

	public double getArqInteres() {
		return this.arqInteres;
	}

	public void setArqInteres(double arqInteres) {
		this.arqInteres = arqInteres;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getNeoCapital() {
		return this.neoCapital;
	}

	public void setNeoCapital(double neoCapital) {
		this.neoCapital = neoCapital;
	}

	public double getNeoInteres() {
		return this.neoInteres;
	}

	public void setNeoInteres(double neoInteres) {
		this.neoInteres = neoInteres;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}