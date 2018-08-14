package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e")
public class Egreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private double importe;

	private int tipo;

	// bi-directional many-to-one association to LibroCaja
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

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
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