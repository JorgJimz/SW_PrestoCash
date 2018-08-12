package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import common.Constantes;

@Entity
@Table(name = "contrato")
@NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal capital;

	@Column(name = "FECHA_CONTRATO", nullable = false, length = 10)
	private String fechaContrato;

	@Column(name = "FECHA_CREACION", length = 10)
	private String fechaCreacion;

	@Column(name = "FECHA_MODIFICACION", length = 10)
	private String fechaModificacion;

	@Column(name = "FECHA_REMATE", nullable = false, length = 10)
	private String fechaRemate;

	@Column(name = "FECHA_VENCIMIENTO", nullable = false, length = 10)
	private String fechaVencimiento;

	@Column(nullable = false, length = 1)
	private String flag;

	@Column(nullable = false, precision = 10, scale = 2)
	private double interes;

	@Column(nullable = false, length = 8)
	private String moneda;

	@Column(nullable = false)
	private int numero;

	@Column(length = 200)
	private String obs;

	@Column(nullable = false)
	private int status;

	@Column(name = "USUARIO_CREACION", length = 45)
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION", length = 45)
	private String usuarioModificacion;

	// bi-directional many-to-one association to Abono
	@OneToMany(mappedBy = "contrato")
	private List<Abono> abonos;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	private Cliente cliente;

	// bi-directional many-to-one association to Prestamo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRESTAMO_ID", nullable = false)
	private Prestamo prestamo;

	// bi-directional many-to-one association to DetalleCargo
	@OneToMany(mappedBy = "contrato")
	private List<DetalleCargo> detalleCargos;

	// bi-directional many-to-one association to DetalleContrato
	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
	private List<DetalleContrato> detalleContratos;

	// bi-directional many-to-one association to Mora
	@OneToMany(mappedBy = "contrato")
	private List<Mora> moras;

	// bi-directional many-to-one association to Pago
	@OneToMany(mappedBy = "contrato")
	private List<Pago> pagos;

	// bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy = "contrato")
	private List<Seguimiento> seguimientos;

	@Transient
	private double interesDiario;

	@Transient
	private double diasExcedidos;

	@Transient
	private int diaFinal;

	@Transient
	private int cuotas;

	public Contrato() {
		detalleContratos = new ArrayList<DetalleContrato>();
	}

	@PostLoad
	public void procesarCamposCalculados() {
		try {
			cuotas = 0;
			interesDiario = interes / 30;
			Calendar v_v = Calendar.getInstance();
			v_v.setTime(Constantes.formatoSQL.parse(fechaVencimiento));
			diaFinal = v_v.getActualMaximum(Calendar.DAY_OF_MONTH);
			long v = v_v.getTimeInMillis();
			long h = Calendar.getInstance().getTimeInMillis();
			long resta = h - v;
			diasExcedidos = (resta < 0) ? 0 : resta / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCapital() {
		return this.capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getFechaContrato() {
		return this.fechaContrato;
	}

	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
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

	public String getFechaRemate() {
		return this.fechaRemate;
	}

	public void setFechaRemate(String fechaRemate) {
		this.fechaRemate = fechaRemate;
	}

	public String getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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

	public List<Abono> getAbonos() {
		return this.abonos;
	}

	public void setAbonos(List<Abono> abonos) {
		this.abonos = abonos;
	}

	public Abono addAbono(Abono abono) {
		getAbonos().add(abono);
		abono.setContrato(this);

		return abono;
	}

	public Abono removeAbono(Abono abono) {
		getAbonos().remove(abono);
		abono.setContrato(null);

		return abono;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Prestamo getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public List<DetalleCargo> getDetalleCargos() {
		return this.detalleCargos;
	}

	public void setDetalleCargos(List<DetalleCargo> detalleCargos) {
		this.detalleCargos = detalleCargos;
	}

	public DetalleCargo addDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().add(detalleCargo);
		detalleCargo.setContrato(this);

		return detalleCargo;
	}

	public DetalleCargo removeDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().remove(detalleCargo);
		detalleCargo.setContrato(null);

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
		detalleContrato.setContrato(this);

		return detalleContrato;
	}

	public DetalleContrato removeDetalleContrato(DetalleContrato detalleContrato) {
		getDetalleContratos().remove(detalleContrato);
		detalleContrato.setContrato(null);

		return detalleContrato;
	}

	public List<Mora> getMoras() {
		return this.moras;
	}

	public void setMoras(List<Mora> moras) {
		this.moras = moras;
	}

	public Mora addMora(Mora mora) {
		getMoras().add(mora);
		mora.setContrato(this);

		return mora;
	}

	public Mora removeMora(Mora mora) {
		getMoras().remove(mora);
		mora.setContrato(null);

		return mora;
	}

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setContrato(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setContrato(null);

		return pago;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setContrato(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setContrato(null);

		return seguimiento;
	}

	@Transient
	public double getInteresDiario() {
		return interesDiario;
	}

	@Transient
	public double getDiasExcedidos() {
		return diasExcedidos;
	}

	@Transient
	public int getDiaFinal() {
		return diaFinal;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

}