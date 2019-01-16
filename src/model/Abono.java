package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Abono.findAll", query = "SELECT a FROM Abono a")
public class Abono implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final Abono DEFAULT = null;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ARQ_CAPITAL")
	private BigDecimal arqCapital;

	@Column(name = "ARQ_INTERES")
	private BigDecimal arqInteres;

	private String fecha;

	@Column(name = "NEO_CAPITAL")
	private BigDecimal neoCapital;

	@Column(name = "NEO_INTERES")
	private BigDecimal neoInteres;

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY)
	private Contrato contrato;

	public Abono() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getArqCapital() {
		return this.arqCapital;
	}

	public void setArqCapital(BigDecimal arqCapital) {
		this.arqCapital = arqCapital;
	}

	public BigDecimal getArqInteres() {
		return this.arqInteres;
	}

	public void setArqInteres(BigDecimal arqInteres) {
		this.arqInteres = arqInteres;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getNeoCapital() {
		return this.neoCapital;
	}

	public void setNeoCapital(BigDecimal neoCapital) {
		this.neoCapital = neoCapital;
	}

	public BigDecimal getNeoInteres() {
		return this.neoInteres;
	}

	public void setNeoInteres(BigDecimal neoInteres) {
		this.neoInteres = neoInteres;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}