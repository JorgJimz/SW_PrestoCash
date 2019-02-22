package model;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.*;

import common.Utiles;

import java.util.List;

@Entity
@Table(name = "estado_articulo")
@NamedQuery(name = "EstadoArticulo.findAll", query = "SELECT e FROM EstadoArticulo e")
public class EstadoArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int ACTIVO = 1;
	public static final int SEPARADO = 2;
	public static final int REMATADO = 3;
	public static final int VITRINA = 5;
	public static final int LIBRE = 6;
	public static final int BAJA = 8;
	public static final int SIN_PRECIO = 9;
	public static final int FUNDIDO = 11;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String valor;

	@Transient
	private Color background;

	@Transient
	private Color foreground;

	// bi-directional many-to-one association to Articulo
	@OneToMany(mappedBy = "estadoArticulo")
	private List<Articulo> articulos;

	public EstadoArticulo() {
	}

	public EstadoArticulo(int id) {
		this.id = id;
	}

	public EstadoArticulo(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	@PostLoad
	public void procesarCamposCalculados() {
		int[] v = Utiles.mapToInt(valor.split(","));
		background = new Color(v[0], v[1], v[2]);
		foreground = new Color(v[3], v[4], v[5]);
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
		articulo.setEstadoArticulo(this);

		return articulo;
	}

	public Articulo removeArticulo(Articulo articulo) {
		getArticulos().remove(articulo);
		articulo.setEstadoArticulo(null);

		return articulo;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	@Override
	public String toString() {
		return descripcion;
	}

}