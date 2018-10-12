package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfil_aplicacion database table.
 * 
 */
@Entity
@Table(name="perfil_aplicacion")
@NamedQuery(name="PerfilAplicacion.findAll", query="SELECT p FROM PerfilAplicacion p")
public class PerfilAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="FECHA_CREACION")
	private String fechaCreacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to Aplicacion
	@ManyToOne(fetch = FetchType.LAZY)
	private Aplicacion aplicacion;

	//bi-directional many-to-one association to Perfil
	@ManyToOne(fetch = FetchType.LAZY)
	private Perfil perfil;

	public PerfilAplicacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}