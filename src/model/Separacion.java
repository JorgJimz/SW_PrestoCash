package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Separacion.findAll", query = "SELECT s FROM Separacion s")
public class Separacion implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int LIBERADA = 0;
	public static final int ACTIVA = 1;
	public static final int FINALIZADA = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fecha;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "PRECIO_VENTA")
	private BigDecimal precioVenta;

	private int status;

	private BigDecimal importe;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "FECHA_LIBERACION")
	private String fechaLiberacion;

	@Column(name = "USUARIO_LIBERACION")
	private String usuarioLiberacion;

	// bi-directional many-to-one association to Articulo
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Articulo articulo;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	public Separacion() {
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

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getFechaLiberacion() {
		return fechaLiberacion;
	}

	public void setFechaLiberacion(String fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	public String getUsuarioLiberacion() {
		return usuarioLiberacion;
	}

	public void setUsuarioLiberacion(String usuarioLiberacion) {
		this.usuarioLiberacion = usuarioLiberacion;
	}

}