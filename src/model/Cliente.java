package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c ORDER BY c.id DESC")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "CATEGORIA_ID")
	private String categoriaId;

	private String direccion;

	private String distrito;

	private String documento;

	private String email;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "FECHA_MODIFICACION")
	private String fechaModificacion;

	private String materno;

	private String nombres;

	private String obs;

	private String paterno;

	private int status;

	@Column(name = "T_DOCUMENTO")
	private String tDocumento;

	private String tlf1;

	private String tlf2;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	// bi-directional many-to-one association to Compra
	@OneToMany(mappedBy = "cliente")
	private List<Compra> compras;

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "cliente")
	private List<Contrato> contratos;

	// bi-directional many-to-one association to Separacion
	@OneToMany(mappedBy = "cliente")
	private List<Separacion> separacions;

	// bi-directional many-to-one association to Venta
	@OneToMany(mappedBy = "cliente")
	private List<Venta> ventas;

	@Transient
	private String nombreCompleto;

	public Cliente() {
	}

	@PostLoad
	public void procesarCamposCalculados() {
		nombreCompleto = paterno + " " + materno + " " + nombres;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaId() {
		return this.categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDistrito() {
		return this.distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTDocumento() {
		return this.tDocumento;
	}

	public void setTDocumento(String tDocumento) {
		this.tDocumento = tDocumento;
	}

	public String getTlf1() {
		return this.tlf1;
	}

	public void setTlf1(String tlf1) {
		this.tlf1 = tlf1;
	}

	public String getTlf2() {
		return this.tlf2;
	}

	public void setTlf2(String tlf2) {
		this.tlf2 = tlf2;
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

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setCliente(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setCliente(null);

		return compra;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setCliente(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setCliente(null);

		return contrato;
	}

	public List<Separacion> getSeparacions() {
		return this.separacions;
	}

	public void setSeparacions(List<Separacion> separacions) {
		this.separacions = separacions;
	}

	public Separacion addSeparacion(Separacion separacion) {
		getSeparacions().add(separacion);
		separacion.setCliente(this);

		return separacion;
	}

	public Separacion removeSeparacion(Separacion separacion) {
		getSeparacions().remove(separacion);
		separacion.setCliente(null);

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
		venta.setCliente(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setCliente(null);
		return venta;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	@Override
	public String toString() {
		return documento + " - " + nombreCompleto;
	}

}