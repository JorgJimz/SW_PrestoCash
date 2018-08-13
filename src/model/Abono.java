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
@Table(name="abono")
@NamedQuery(name="Abono.findAll", query="SELECT a FROM Abono a")
public class Abono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="ARQ_CAPITAL", precision=10, scale=2)
	private double arqCapital;

	@Column(name="ARQ_INTERES", precision=10, scale=2)
	private double arqInteres;

	@Column(length=10)
	private String fecha;

	@Column(name="NEO_CAPITAL", precision=10, scale=2)
	private double neoCapital;

	@Column(name="NEO_INTERES", precision=10, scale=2)
	private double neoInteres;

	//bi-directional many-to-one association to Contrato
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRATO_ID", nullable=false)
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