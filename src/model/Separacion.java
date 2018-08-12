package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the separacion database table.
 * 
 */
@Entity
@Table(name="separacion")
@NamedQuery(name="Separacion.findAll", query="SELECT s FROM Separacion s")
public class Separacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=10)
	private String fecha;

	@Column(name="FECHA_CREACION", length=10)
	private String fechaCreacion;

	@Column(name="PRECIO_VENTA", nullable=false, precision=10, scale=2)
	private BigDecimal precioVenta;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="USUARIO_CREACION", length=45)
	private String usuarioCreacion;

	//bi-directional many-to-one association to DetalleSeparacion
	@OneToMany(mappedBy="separacion")
	private List<DetalleSeparacion> detalleSeparacions;

	//bi-directional many-to-one association to Articulo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTICULO_ID", nullable=false)
	private Articulo articulo;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLIENTE_ID", nullable=false)
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public List<DetalleSeparacion> getDetalleSeparacions() {
		return this.detalleSeparacions;
	}

	public void setDetalleSeparacions(List<DetalleSeparacion> detalleSeparacions) {
		this.detalleSeparacions = detalleSeparacions;
	}

	public DetalleSeparacion addDetalleSeparacion(DetalleSeparacion detalleSeparacion) {
		getDetalleSeparacions().add(detalleSeparacion);
		detalleSeparacion.setSeparacion(this);

		return detalleSeparacion;
	}

	public DetalleSeparacion removeDetalleSeparacion(DetalleSeparacion detalleSeparacion) {
		getDetalleSeparacions().remove(detalleSeparacion);
		detalleSeparacion.setSeparacion(null);

		return detalleSeparacion;
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

}