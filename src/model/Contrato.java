package model;

import java.awt.Color;
import java.io.Serializable;

import javax.persistence.*;

import common.Constantes;

import java.util.Calendar;
import java.util.List;

@Entity
@NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double capital;

	@Column(name = "FECHA_CONTRATO")
	private String fechaContrato;

	@Column(name = "FECHA_CREACION")
	private String fechaCreacion;

	@Column(name = "FECHA_MODIFICACION")
	private String fechaModificacion;

	@Column(name = "FECHA_REMATE")
	private String fechaRemate;

	@Column(name = "FECHA_VENCIMIENTO")
	private String fechaVencimiento;

	private String flag;

	@Column(name = "INTERES_MENSUAL")
	private double interesMensual;

	private String moneda;

	private int numero;

	private String obs;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	// bi-directional many-to-one association to Abono
	@OneToMany(mappedBy = "contrato")
	private List<Abono> abonos;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	// bi-directional many-to-one association to EContrato
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "E_CONTRATO_ID")
	private EContrato EContrato;

	// bi-directional many-to-one association to Prestamo
	@ManyToOne(fetch = FetchType.LAZY)
	private Prestamo prestamo;

	// bi-directional many-to-one association to DetalleCargo
	@OneToMany(mappedBy = "contrato")
	private List<DetalleCargo> detalleCargos;

	// bi-directional many-to-one association to DetalleContrato
	@OneToMany(mappedBy = "contrato")
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

	@Transient
	private int diasResiduo;

	@Transient
	private String moraRespuesta;

	@Transient
	private double moraPorcentaje;

	@Transient
	private Color moraColor;

	@Transient
	private double interesTotal;

	@Transient
	private double moraActual;

	@Transient
	private double moraAnterior;

	@Transient
	private double moraTotal;

	@Transient
	private double prorrateo;

	public Contrato() {
	}

	@PostLoad
	public void procesarCamposCalculados() {
		try {
			interesDiario = interesMensual / 30;
			Calendar v_v = Calendar.getInstance();
			v_v.setTime(Constantes.formatoSQL.parse(fechaVencimiento));
			diaFinal = v_v.getActualMaximum(Calendar.DAY_OF_MONTH);
			long v = v_v.getTimeInMillis();
			long h = Calendar.getInstance().getTimeInMillis();
			long resta = h - v;
			diasExcedidos = (resta < 0) ? 1 : resta / (24 * 60 * 60 * 1000);
			cuotas = (int) Math.ceil(diasExcedidos / diaFinal);
			diasResiduo = (int) diasExcedidos % diaFinal;
			prorrateo = interesDiario * diasResiduo;

			if (prestamo.getTMora().equals("%")) {
				if (cuotas == 1 && diasResiduo > 5) {
					moraRespuesta = "SÍ";
					moraActual = interesMensual * Constantes.PRIMERA_MORA;
					moraPorcentaje = Constantes.PRIMERA_MORA;
					moraColor = Color.RED;
				} else if (cuotas == 2 && diasResiduo == 0) {
					moraRespuesta = "SÍ";
					moraActual = interesMensual * Constantes.PRIMERA_MORA;
					moraPorcentaje = Constantes.PRIMERA_MORA;
					moraColor = Color.RED;
				} else if (cuotas == 2 && diasResiduo > 0) {
					moraRespuesta = "SÍ";
					moraActual = (interesMensual * 2) * Constantes.SEGUNDA_MORA;
					moraPorcentaje = Constantes.SEGUNDA_MORA;
					moraColor = Color.RED;
				} else if (cuotas >= 2) {
					moraRespuesta = "SÍ";
					moraActual = (interesMensual * cuotas)
							* Constantes.SEGUNDA_MORA;
					moraPorcentaje = Constantes.SEGUNDA_MORA;
					moraColor = Color.RED;
				} else {
					moraRespuesta = "NO";
					moraActual = 0;
					moraPorcentaje = Constantes.MORA_CERO;
					moraColor = new Color(0, 128, 0);
				}
			} else {
				// Si es soles
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCapital() {
		return this.capital;
	}

	public void setCapital(double capital) {
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

	public double getInteresMensual() {
		return this.interesMensual;
	}

	public void setInteresMensual(double interesMensual) {
		this.interesMensual = interesMensual;
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

	public EContrato getEContrato() {
		return this.EContrato;
	}

	public void setEContrato(EContrato EContrato) {
		this.EContrato = EContrato;
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

	public double getInteresDiario() {
		return interesDiario;
	}

	public void setInteresDiario(double interesDiario) {
		this.interesDiario = interesDiario;
	}

	public double getDiasExcedidos() {
		return diasExcedidos;
	}

	public void setDiasExcedidos(double diasExcedidos) {
		this.diasExcedidos = diasExcedidos;
	}

	public int getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(int diaFinal) {
		this.diaFinal = diaFinal;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public int getDiasResiduo() {
		return diasResiduo;
	}

	public void setDiasResiduo(int diasResiduo) {
		this.diasResiduo = diasResiduo;
	}

	public String getMoraRespuesta() {
		return moraRespuesta;
	}

	public void setMoraRespuesta(String moraRespuesta) {
		this.moraRespuesta = moraRespuesta;
	}

	public double getMoraPorcentaje() {
		return moraPorcentaje;
	}

	public void setMoraPorcentaje(double moraPorcentaje) {
		this.moraPorcentaje = moraPorcentaje;
	}

	public Color getMoraColor() {
		return moraColor;
	}

	public void setMoraColor(Color moraColor) {
		this.moraColor = moraColor;
	}

	public double getInteresTotal() {
		return interesTotal;
	}

	public void setInteresTotal(double interesTotal) {
		this.interesTotal = interesTotal;
	}

	public double getMoraActual() {
		return moraActual;
	}

	public void setMoraActual(double moraActual) {
		this.moraActual = moraActual;
	}

	public double getMoraAnterior() {
		return moraAnterior;
	}

	public void setMoraAnterior(double moraAnterior) {
		this.moraAnterior = moraAnterior;
	}

	public double getMoraTotal() {
		return moraTotal;
	}

	public void setMoraTotal(double moraTotal) {
		this.moraTotal = moraTotal;
	}

	public double getProrrateo() {
		return prorrateo;
	}

	public void setProrrateo(double prorrateo) {
		this.prorrateo = prorrateo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}