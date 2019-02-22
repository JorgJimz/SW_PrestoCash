package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import common.Exclude;

@Entity
@Table(name = "detalle_contrato")
@NamedQuery(name = "DetalleContrato.findAll", query = "SELECT d FROM DetalleContrato d")
public class DetalleContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private BigDecimal tasacion;

	// bi-directional many-to-one association to Articulo
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Articulo articulo;

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	private Contrato contrato;

	@Transient
	private String articuloJasper;

	@Transient
	private String observacionArticuloJasper;

	@Transient
	private String alphaId;

	public DetalleContrato() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTasacion() {
		return this.tasacion;
	}

	public void setTasacion(BigDecimal tasacion) {
		this.tasacion = tasacion;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getArticuloJasper() {
		return articuloJasper;
	}

	public void setArticuloJasper(String articuloJasper) {
		this.articuloJasper = articuloJasper;
	}

	public void setObservacionArticuloJasper(String observacionArticuloJasper) {
		this.observacionArticuloJasper = observacionArticuloJasper;
	}

	public String getObservacionArticuloJasper() {
		return observacionArticuloJasper;
	}

	public String getAlphaId() {
		return alphaId;
	}

	public void setAlphaId(String alphaId) {
		this.alphaId = alphaId;
	}

}