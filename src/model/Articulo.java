package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="articulo")
@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="CAPITAL_CONTRATO", precision=10, scale=2)
	private BigDecimal capitalContrato;

	@Column(length=10)
	private String contrato;

	@Column(nullable=false, length=45)
	private String descripcion;

	@Column(name="FECHA_CREACION", length=10)
	private String fechaCreacion;

	@Column(name="FECHA_MODIFICACION", length=10)
	private String fechaModificacion;

	@Column(nullable=false, length=45)
	private String marca;

	@Column(nullable=false, length=45)
	private String modelo;

	@Column(length=45)
	private String obs;

	@Column(name="PRECIO_INTERNO", precision=10, scale=2)
	private BigDecimal precioInterno;

	@Column(name="PRECIO_VENTA", precision=10, scale=2)
	private BigDecimal precioVenta;

	@Column(nullable=false, length=45)
	private String serie;

	@Column(nullable=false)
	private int status;

	@Column(name="USUARIO_CREACION", length=45)
	private String usuarioCreacion;

	@Column(name="USUARIO_MODIFICACION", length=45)
	private String usuarioModificacion;
	
	//bi-directional many-to-one association to DetalleCargo
	@OneToMany(mappedBy="articulo")
	private List<DetalleCargo> detalleCargos;

	//bi-directional many-to-one association to DetalleContrato
	@OneToMany(mappedBy="articulo", cascade = CascadeType.ALL)
	private List<DetalleContrato> detalleContratos;

	//bi-directional many-to-one association to Fundicion
	@OneToMany(mappedBy="articulo")
	private List<Fundicion> fundicions;

	//bi-directional many-to-one association to Separacion
	@OneToMany(mappedBy="articulo")
	private List<Separacion> separacions;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="articulo")
	private List<Venta> ventas;

	public Articulo() {
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

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
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

}