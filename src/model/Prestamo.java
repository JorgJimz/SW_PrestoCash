package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "FECHA_MODIFICACION")
	private String fechaModificacion;

	private String flag;

	private BigDecimal interes;

	private String mora;

	@Column(name = "T_MORA")
	private String tMora;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "prestamo")
	private List<Contrato> contratos;

	// bi-directional many-to-one association to Sede
	@ManyToOne(fetch = FetchType.LAZY)
	private Sede sede;
	
	@Transient
	private BigDecimal porcentaje;

	public Prestamo() {
	}
	
	@PostLoad
	public void procesarCamposCalculados() {
		porcentaje = interes.divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
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

	public BigDecimal getInteres() {
		return this.interes;
	}

	public void setInteres(BigDecimal interes) {
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

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	

}