package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

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
	private BigDecimal arqCapital;

	@Column(name="ARQ_INTERES", precision=10, scale=2)
	private BigDecimal arqInteres;

	@Column(length=10)
	private String fecha;

	@Column(name="NEO_CAPITAL", precision=10, scale=2)
	private BigDecimal neoCapital;

	@Column(name="NEO_INTERES", precision=10, scale=2)
	private BigDecimal neoInteres;

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