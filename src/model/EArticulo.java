package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the e_articulo database table.
 * 
 */
@Entity
@Table(name = "e_articulo")
@NamedQuery(name = "EArticulo.findAll", query = "SELECT e FROM EArticulo e")
public class EArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String valor;

	// bi-directional many-to-one association to Articulo
	@OneToMany(mappedBy = "EArticulo")
	private List<Articulo> articulos;

	public EArticulo() {
	}

	public EArticulo(int id) {
		super();
		this.id = id;
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Articulo addArticulo(Articulo articulo) {
		getArticulos().add(articulo);
		articulo.setEArticulo(this);

		return articulo;
	}

	public Articulo removeArticulo(Articulo articulo) {
		getArticulos().remove(articulo);
		articulo.setEArticulo(null);

		return articulo;
	}

}