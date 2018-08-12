package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the maestro database table.
 * 
 */
@Entity
@Table(name="maestro")
@NamedQuery(name="Maestro.findAll", query="SELECT m FROM Maestro m")
public class Maestro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String categoria;

	@Column(nullable=false, length=45)
	private String descripcion;

	@Column(length=1)
	private String status;

	@Column(nullable=false, length=45)
	private String valor;

	public Maestro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}