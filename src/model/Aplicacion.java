package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Aplicacion.findAll", query = "SELECT a FROM Aplicacion a")
public class Aplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String padre;

	private int orden;

	private String url;

	// bi-directional many-to-one association to PerfilAplicacion
	@OneToMany(mappedBy = "aplicacion")
	private List<PerfilAplicacion> perfilAplicacions;

	public Aplicacion() {
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

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PerfilAplicacion> getPerfilAplicacions() {
		return this.perfilAplicacions;
	}

	public void setPerfilAplicacions(List<PerfilAplicacion> perfilAplicacions) {
		this.perfilAplicacions = perfilAplicacions;
	}

	public PerfilAplicacion addPerfilAplicacion(PerfilAplicacion perfilAplicacion) {
		getPerfilAplicacions().add(perfilAplicacion);
		perfilAplicacion.setAplicacion(this);

		return perfilAplicacion;
	}

	public PerfilAplicacion removePerfilAplicacion(PerfilAplicacion perfilAplicacion) {
		getPerfilAplicacions().remove(perfilAplicacion);
		perfilAplicacion.setAplicacion(null);

		return perfilAplicacion;
	}

}