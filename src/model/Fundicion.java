package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Fundicion.findAll", query = "SELECT f FROM Fundicion f")
public class Fundicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fecha;

	// bi-directional many-to-one association to Articulo
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Articulo articulo;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
}