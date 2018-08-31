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

@Entity
@Table(name="detalle_contrato")
@NamedQuery(name="DetalleContrato.findAll", query="SELECT d FROM DetalleContrato d")
public class DetalleContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private BigDecimal tasacion;

	//bi-directional many-to-one association to Articulo
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Articulo articulo;

	//bi-directional many-to-one association to Contrato
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Contrato contrato;

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

}