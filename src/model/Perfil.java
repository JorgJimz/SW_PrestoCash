package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	// bi-directional many-to-one association to PerfilAplicacion
	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
	private List<PerfilAplicacion> perfilAplicacions;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "perfil")
	private List<Usuario> usuarios;

	public Perfil() {
	}

	public Perfil(int id) {
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

	public List<PerfilAplicacion> getPerfilAplicacions() {
		return this.perfilAplicacions;
	}

	public void setPerfilAplicacions(List<PerfilAplicacion> perfilAplicacions) {
		this.perfilAplicacions = perfilAplicacions;
	}

	public PerfilAplicacion addPerfilAplicacion(PerfilAplicacion perfilAplicacion) {
		getPerfilAplicacions().add(perfilAplicacion);
		perfilAplicacion.setPerfil(this);

		return perfilAplicacion;
	}

	public PerfilAplicacion removePerfilAplicacion(PerfilAplicacion perfilAplicacion) {
		getPerfilAplicacions().remove(perfilAplicacion);
		perfilAplicacion.setPerfil(null);

		return perfilAplicacion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPerfil(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPerfil(null);

		return usuario;
	}

}