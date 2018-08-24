package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Asistencia DEFAULT = null;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fecha;

	@Column(name = "HORA_CONTRATO")
	private String horaContrato;

	@Column(name = "HORA_INGRESO")
	private String horaIngreso;

	@Column(name = "HORA_SALIDA")
	private String horaSalida;

	private String obs;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public Asistencia() {
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

	public String getHoraContrato() {
		return this.horaContrato;
	}

	public void setHoraContrato(String horaContrato) {
		this.horaContrato = horaContrato;
	}

	public String getHoraIngreso() {
		return this.horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}