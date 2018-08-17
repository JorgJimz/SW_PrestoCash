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

@Entity
@NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal capital;

	private String descripcion;

	private BigDecimal ganancia;

	private BigDecimal otro;

	private String tipo;

	// bi-directional many-to-one association to LibroCaja
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIBRO_CAJA_ID")
	private LibroCaja libroCaja;

	public Ingreso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCapital() {
		return this.capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getGanancia() {
		return this.ganancia;
	}

	public void setGanancia(BigDecimal ganancia) {
		this.ganancia = ganancia;
	}

	public BigDecimal getOtro() {
		return this.otro;
	}

	public void setOtro(BigDecimal otro) {
		this.otro = otro;
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

}