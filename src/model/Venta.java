package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fecha;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	private BigDecimal importe;

	private String obs;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	// bi-directional many-to-one association to Articulo
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Articulo articulo;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@Transient
	private String modalidad;

	@PostLoad
	public void procesarCamposCalculados() {
		modalidad = "VENTA DIRECTA";
	}

	public Venta() {
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

	public String getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

}