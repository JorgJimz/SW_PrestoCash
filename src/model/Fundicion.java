package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fundicion database table.
 * 
 */
@Entity
@Table(name="fundicion")
@NamedQuery(name="Fundicion.findAll", query="SELECT f FROM Fundicion f")
public class Fundicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=10)
	private String fecha;

	//bi-directional many-to-one association to Articulo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTICULO_ID", nullable=false)
	private Articulo articulo;

	public Fundicion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}