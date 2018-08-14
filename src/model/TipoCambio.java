package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tipo_cambio database table.
 * 
 */
@Entity
@Table(name="tipo_cambio")
@NamedQuery(name="TipoCambio.findAll", query="SELECT t FROM TipoCambio t")
public class TipoCambio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal compra;

	private String fecha;

	private BigDecimal venta;

	public TipoCambio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCompra() {
		return this.compra;
	}

	public void setCompra(BigDecimal compra) {
		this.compra = compra;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getVenta() {
		return this.venta;
	}

	public void setVenta(BigDecimal venta) {
		this.venta = venta;
	}

}