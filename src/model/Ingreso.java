package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double capital;

	private String descripcion;

	private double ganancia;

	private double otro;

	private int tipo;

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

	public double getCapital() {
		return this.capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getGanancia() {
		return this.ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public double getOtro() {
		return this.otro;
	}

	public void setOtro(double otro) {
		this.otro = otro;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public LibroCaja getLibroCaja() {
		return this.libroCaja;
	}

	public void setLibroCaja(LibroCaja libroCaja) {
		this.libroCaja = libroCaja;
	}

}