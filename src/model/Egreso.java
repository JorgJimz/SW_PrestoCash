package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e")
public class Egreso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Egreso DEFAULT = null;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;

	@Expose
	private String descripcion;

	@Expose
	private BigDecimal importe;

	@Expose
	private String tipo;

	@Expose private String moneda;

	@Transient
	private String importeTexto;

	@Transient
	private String comprobante;

	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIBRO_CAJA_ID")
	private LibroCaja libroCaja;

	public Egreso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LibroCaja getLibroCaja() {
		return this.libroCaja;
	}

	public void setLibroCaja(LibroCaja libroCaja) {
		this.libroCaja = libroCaja;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getImporteTexto() {
		return importeTexto;
	}

	public void setImporteTexto(String importeTexto) {
		this.importeTexto = importeTexto;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

}