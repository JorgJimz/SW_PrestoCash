package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the prestamo database table.
 * 
 */
@Entity
@Table(name="prestamo")
@NamedQuery(name="Prestamo.findAll", query="SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String descripcion;

	@Column(name="FECHA_CREACION", length=10)
	private String fechaCreacion;

	@Column(name="FECHA_MODIFICACION", length=10)
	private String fechaModificacion;

	@Column(nullable=false, length=1)
	private String flag;

	@Column(nullable=false, precision=10, scale=2)
	private double interes;

	@Column(nullable=false, length=45)
	private String mora;

	@Column(name="T_MORA", nullable=false, length=45)
	private String tMora;

	@Column(name="USUARIO_CREACION", length=45)
	private String usuarioCreacion;

	@Column(name="USUARIO_MODIFICACION", length=45)
	private String usuarioModificacion;

	//bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy="prestamo")
	private List<Contrato> contratos;

	//bi-directional many-to-one association to Sede
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEDE_ID", nullable=false)
	private Sede sede;

	public Prestamo() {
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

	public String getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public double getInteres() {
		return this.interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public String getMora() {
		return this.mora;
	}

	public void setMora(String mora) {
		this.mora = mora;
	}

	public String getTMora() {
		return this.tMora;
	}

	public void setTMora(String tMora) {
		this.tMora = tMora;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setPrestamo(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setPrestamo(null);

		return contrato;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}