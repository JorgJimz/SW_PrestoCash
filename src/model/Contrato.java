package model;

import java.awt.Color;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
import javax.persistence.Transient;

import common.Constantes;
import common.Utiles;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Entity
@NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal capital;

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
	private BigDecimal interesMensual;

	private String moneda;

	private int numero;

	private String obs;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	@OneToMany(mappedBy = "contrato")
	private List<Abono> abonos;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "E_CONTRATO_ID")
	private EContrato EContrato;

	@ManyToOne(fetch = FetchType.LAZY)
	private Prestamo prestamo;

	@OneToMany(mappedBy = "contrato")
	private List<DetalleCargo> detalleCargos;

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
	private List<DetalleContrato> detalleContratos;

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
	private List<Mora> moras;

	@OneToMany(mappedBy = "contrato")
	private List<Pago> pagos;

	@OneToMany(mappedBy = "contrato")
	private List<Seguimiento> seguimientos;

	@Transient
	private BigDecimal interesDiario;

	@Transient
	private BigDecimal diasExcedidos;

	@Transient
	private BigDecimal diaFinal;

	@Transient
	private BigDecimal cuotas;

	@Transient
	private BigDecimal diasResiduo;

	@Transient
	private String moraRespuesta;

	@Transient
	private BigDecimal moraPorcentaje;

	@Transient
	private Color moraColor;

	@Transient
	private BigDecimal interesTotal;

	@Transient
	private BigDecimal moraActual;

	@Transient
	private BigDecimal moraAnterior;

	@Transient
	private BigDecimal moraTotal;

	@Transient
	private BigDecimal prorrateo;

	@Transient
	private BigDecimal prorrateoMora;

	@Transient
	private String operacion;

	public Contrato() {
		detalleContratos = new ArrayList<DetalleContrato>();
	}

	@PostLoad
	public void procesarCamposCalculados() {
		try {
			interesMensual = Utiles
					.redondearCentimos(interesMensual);
			interesDiario = Utiles
					.redondearCentimos(interesMensual.divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP));
			LocalDate hoy = LocalDate.now();
			LocalDate vencimiento = LocalDate.parse(fechaVencimiento);
			diaFinal = BigDecimal.valueOf(vencimiento.lengthOfMonth());
			long diff = ChronoUnit.DAYS.between(vencimiento, hoy);
			if (diff <= 0) {
				Pago p = pagos.stream().sorted(Comparator.comparing(Pago::getFechaVencimiento).reversed()).findFirst()
						.orElse(Pago.DEFAULT);
				if (Objects.isNull(p)) {
					diasExcedidos = (diff < 0) ? BigDecimal.ZERO : BigDecimal.valueOf(diff);
					cuotas = BigDecimal.ONE.add(diasExcedidos.divide(diaFinal, 0, RoundingMode.FLOOR));
					diasResiduo = diasExcedidos.remainder(diaFinal);
					prorrateo = interesDiario.multiply(diasResiduo);
				} else {
					diasExcedidos = new BigDecimal(
							ChronoUnit.DAYS.between(LocalDate.parse(p.getFechaVencimiento()), LocalDate.now()));

					diasExcedidos = (diasExcedidos.compareTo(BigDecimal.ZERO) == -1) ? BigDecimal.ZERO : diasExcedidos;

					cuotas = BigDecimal.ZERO;
					diasResiduo = diasExcedidos;
					prorrateo = interesDiario.multiply(diasResiduo);
				}
			} else {
				diasExcedidos = (diff < 0) ? BigDecimal.ZERO : BigDecimal.valueOf(diff);
				cuotas = BigDecimal.ONE.add(diasExcedidos.divide(diaFinal, 0, RoundingMode.FLOOR));
				diasResiduo = diasExcedidos.remainder(diaFinal);
				prorrateo = interesDiario.multiply(diasResiduo);
			}

			if (prestamo.getTMora().equals("%")) {
				if (cuotas.intValue() == 1 && diasResiduo.intValue() > 5) {
					moraRespuesta = "S�";
					moraActual = interesMensual.multiply(Constantes.PRIMERA_MORA).setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = prorrateo.multiply(Constantes.PRIMERA_MORA).setScale(2, RoundingMode.HALF_UP);
					moraPorcentaje = Constantes.PRIMERA_MORA;
					moraColor = Color.RED;
				} else if (cuotas.intValue() == 2 && diasResiduo.intValue() >= 0 && diasResiduo.intValue() <= 5) {
					moraRespuesta = "S�";
					moraActual = interesMensual.multiply(Constantes.PRIMERA_MORA).setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = prorrateo.multiply(Constantes.PRIMERA_MORA).setScale(2, RoundingMode.HALF_UP);
					moraPorcentaje = Constantes.PRIMERA_MORA;
					moraColor = Color.RED;
				} else if (cuotas.intValue() == 2 && diasResiduo.intValue() > 5) {
					moraRespuesta = "S�";
					moraActual = interesMensual.multiply(new BigDecimal(2)).multiply(Constantes.SEGUNDA_MORA)
							.setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = prorrateo.multiply(Constantes.SEGUNDA_MORA).setScale(2, RoundingMode.HALF_UP);
					moraPorcentaje = Constantes.SEGUNDA_MORA;
					moraColor = Color.RED;
				} else if (cuotas.intValue() >= 2) {
					moraRespuesta = "S�";
					moraActual = (interesMensual.multiply(cuotas)).multiply(Constantes.SEGUNDA_MORA).setScale(2,
							RoundingMode.HALF_UP);
					prorrateoMora = prorrateo.multiply(Constantes.SEGUNDA_MORA).setScale(2, RoundingMode.HALF_UP);
					moraPorcentaje = Constantes.SEGUNDA_MORA;
					moraColor = Color.RED;
				} else {
					moraRespuesta = "NO";
					moraActual = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
					moraPorcentaje = Constantes.MORA_CERO;
					moraColor = new Color(0, 128, 0);
				}
			} else {
				// Cuando es Mora SOLES
				if (cuotas.intValue() == 1 && diasResiduo.intValue() > 5) {
					moraRespuesta = "S�";
					moraActual = Constantes.MORA_SOLES;
					prorrateoMora = BigDecimal.ZERO;
					moraPorcentaje = Constantes.MORA_SOLES;
					moraColor = Color.RED;
				} else if (cuotas.intValue() == 2 && diasResiduo.intValue() == 0) {
					moraRespuesta = "S�";
					moraActual = Constantes.MORA_SOLES;
					prorrateoMora = BigDecimal.ZERO;
					moraPorcentaje = Constantes.MORA_SOLES;
					moraColor = Color.RED;
				} else if (cuotas.intValue() == 2 && diasResiduo.intValue() > 0) {
					moraRespuesta = "S�";
					moraActual = Constantes.MORA_SOLES.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = BigDecimal.ZERO;
					moraPorcentaje = Constantes.MORA_SOLES;
					moraColor = Color.RED;
				} else if (cuotas.intValue() >= 2) {
					moraRespuesta = "S�";
					moraActual = Constantes.MORA_SOLES.multiply(cuotas).setScale(2, RoundingMode.HALF_UP);
					prorrateoMora = BigDecimal.ZERO;
					moraPorcentaje = Constantes.MORA_SOLES;
					moraColor = Color.RED;
				} else {
					moraRespuesta = "NO";
					moraActual = BigDecimal.ZERO;
					prorrateoMora = BigDecimal.ZERO;
					moraPorcentaje = Constantes.MORA_CERO;
					moraColor = new Color(0, 128, 0);
				}
			}

			prorrateo = Utiles.redondearCentimos(prorrateo);
			moraActual = Utiles.redondearCentimos(moraActual);
			prorrateoMora = Utiles.redondearCentimos(prorrateoMora);
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

	public BigDecimal getInteresMensual() {
		return this.interesMensual;
	}

	public void setInteresMensual(BigDecimal interesMensual) {
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

	public BigDecimal getInteresDiario() {
		return interesDiario;
	}

	public void setInteresDiario(BigDecimal interesDiario) {
		this.interesDiario = interesDiario;
	}

	public BigDecimal getDiasExcedidos() {
		return diasExcedidos;
	}

	public void setDiasExcedidos(BigDecimal diasExcedidos) {
		this.diasExcedidos = diasExcedidos;
	}

	public BigDecimal getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(BigDecimal diaFinal) {
		this.diaFinal = diaFinal;
	}

	public BigDecimal getCuotas() {
		return cuotas;
	}

	public void setCuotas(BigDecimal cuotas) {
		this.cuotas = cuotas;
	}

	public BigDecimal getDiasResiduo() {
		return diasResiduo;
	}

	public void setDiasResiduo(BigDecimal diasResiduo) {
		this.diasResiduo = diasResiduo;
	}

	public String getMoraRespuesta() {
		return moraRespuesta;
	}

	public void setMoraRespuesta(String moraRespuesta) {
		this.moraRespuesta = moraRespuesta;
	}

	public BigDecimal getMoraPorcentaje() {
		return moraPorcentaje;
	}

	public void setMoraPorcentaje(BigDecimal moraPorcentaje) {
		this.moraPorcentaje = moraPorcentaje;
	}

	public Color getMoraColor() {
		return moraColor;
	}

	public void setMoraColor(Color moraColor) {
		this.moraColor = moraColor;
	}

	public BigDecimal getInteresTotal() {
		return interesTotal;
	}

	public void setInteresTotal(BigDecimal interesTotal) {
		this.interesTotal = interesTotal;
	}

	public BigDecimal getMoraActual() {
		return moraActual;
	}

	public void setMoraActual(BigDecimal moraActual) {
		this.moraActual = moraActual;
	}

	public BigDecimal getMoraAnterior() {
		return moraAnterior;
	}

	public void setMoraAnterior(BigDecimal moraAnterior) {
		this.moraAnterior = moraAnterior;
	}

	public BigDecimal getMoraTotal() {
		return moraTotal;
	}

	public void setMoraTotal(BigDecimal moraTotal) {
		this.moraTotal = moraTotal;
	}

	public BigDecimal getProrrateo() {
		return prorrateo;
	}

	public void setProrrateo(BigDecimal prorrateo) {
		this.prorrateo = prorrateo;
	}

	public BigDecimal getProrrateoMora() {
		return prorrateoMora;
	}

	public void setProrrateoMora(BigDecimal prorrateoMora) {
		this.prorrateoMora = prorrateoMora;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public JRDataSource getDetalleContratoJasper() {
		return new JRBeanCollectionDataSource(detalleContratos);
	}

}