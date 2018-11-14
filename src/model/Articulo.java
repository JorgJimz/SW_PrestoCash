package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "CAPITAL_CONTRATO")
	private BigDecimal capitalContrato;

	private String contrato;

	private String descripcion;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "FECHA_MODIFICACION")
	private String fechaModificacion;

	private String marca;

	private String modelo;

	private String obs;

	@Column(name = "PRECIO_INTERNO")
	private BigDecimal precioInterno;

	@Column(name = "PRECIO_VENTA")
	private BigDecimal precioVenta;

	private String serie;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	@Transient
	private String fechaContrato;

	@Transient
	private String fechaVencimiento;

	@Transient
	private String fechaRemate;

	@Transient
	private String documentoCliente;

	@Transient
	private EArticulo exEArticulo;

	// bi-directional many-to-one association to EArticulo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "E_ARTICULO_ID")
	private EArticulo EArticulo;

	// bi-directional many-to-one association to DetalleCargo
	@OneToMany(mappedBy = "articulo")
	private List<DetalleCargo> detalleCargos;

	// bi-directional many-to-one association to DetalleContrato
	@OneToMany(mappedBy = "articulo")
	private List<DetalleContrato> detalleContratos;

	// bi-directional many-to-one association to Fundicion
	@OneToMany(mappedBy = "articulo")
	private List<Fundicion> fundicions;

	// bi-directional many-to-one association to Separacion
	@OneToMany(mappedBy = "articulo")
	private List<Separacion> separacions;

	// bi-directional many-to-one association to Venta
	@OneToMany(mappedBy = "articulo")
	private List<Venta> ventas;

	public Articulo() {
	}

	public Articulo(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCapitalContrato() {
		return this.capitalContrato;
	}

	public void setCapitalContrato(BigDecimal capitalContrato) {
		this.capitalContrato = capitalContrato;
	}

	public String getContrato() {
		return this.contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
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

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal getPrecioInterno() {
		return this.precioInterno;
	}

	public void setPrecioInterno(BigDecimal precioInterno) {
		this.precioInterno = precioInterno;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
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

	public EArticulo getEArticulo() {
		return this.EArticulo;
	}

	public void setEArticulo(EArticulo EArticulo) {
		this.EArticulo = EArticulo;
	}

	public List<DetalleCargo> getDetalleCargos() {
		return this.detalleCargos;
	}

	public void setDetalleCargos(List<DetalleCargo> detalleCargos) {
		this.detalleCargos = detalleCargos;
	}

	public DetalleCargo addDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().add(detalleCargo);
		detalleCargo.setArticulo(this);

		return detalleCargo;
	}

	public DetalleCargo removeDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().remove(detalleCargo);
		detalleCargo.setArticulo(null);

		return detalleCargo;
	}

	public List<DetalleContrato> getDetalleContratos() {
		return this.detalleContratos;
	}

	public void setDetalleContratos(List<DetalleContrato> detalleContratos) {
		this.detalleContratos = detalleContratos;
	}

	public DetalleContrato addDetalleContrato(DetalleContrato detalleContrato) {
		getDetalleContratos().add(detalleContrato);
		detalleContrato.setArticulo(this);

		return detalleContrato;
	}

	public DetalleContrato removeDetalleContrato(DetalleContrato detalleContrato) {
		getDetalleContratos().remove(detalleContrato);
		detalleContrato.setArticulo(null);

		return detalleContrato;
	}

	public List<Fundicion> getFundicions() {
		return this.fundicions;
	}

	public void setFundicions(List<Fundicion> fundicions) {
		this.fundicions = fundicions;
	}

	public Fundicion addFundicion(Fundicion fundicion) {
		getFundicions().add(fundicion);
		fundicion.setArticulo(this);

		return fundicion;
	}

	public Fundicion removeFundicion(Fundicion fundicion) {
		getFundicions().remove(fundicion);
		fundicion.setArticulo(null);

		return fundicion;
	}

	public List<Separacion> getSeparacions() {
		return this.separacions;
	}

	public void setSeparacions(List<Separacion> separacions) {
		this.separacions = separacions;
	}

	public Separacion addSeparacion(Separacion separacion) {
		getSeparacions().add(separacion);
		separacion.setArticulo(this);

		return separacion;
	}

	public Separacion removeSeparacion(Separacion separacion) {
		getSeparacions().remove(separacion);
		separacion.setArticulo(null);

		return separacion;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setArticulo(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setArticulo(null);

		return venta;
	}

	public String getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFechaRemate() {
		return fechaRemate;
	}

	public void setFechaRemate(String fechaRemate) {
		this.fechaRemate = fechaRemate;
	}

	public String getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public EArticulo getExEArticulo() {
		return exEArticulo;
	}

	public void setExEArticulo(EArticulo exEArticulo) {
		this.exEArticulo = exEArticulo;
	}

}