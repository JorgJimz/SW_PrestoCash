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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Entity
@Table(name = "libro_caja")
@NamedQuery(name = "LibroCaja.findAll", query = "SELECT l FROM LibroCaja l")
public class LibroCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal amanece;

	private BigDecimal cierre;

	@Column(name = "FECHA_APERTURA")
	private String fechaApertura;

	@Column(name = "HORA_APERTURA")
	private String horaApertura;

	@Column(name = "FECHA_CIERRE")
	private String fechaCierre;

	@Column(name = "HORA_CIERRE")
	private String horaCierre;

	private int status;

	@Column(name = "AMANECE_DOLARES")
	private BigDecimal amaneceDolares;

	@Column(name = "CIERRE_DOLARES")
	private BigDecimal cierreDolares;

	// bi-directional many-to-one association to Egreso
	@OneToMany(mappedBy = "libroCaja", fetch = FetchType.EAGER)
	private List<Egreso> egresos;

	// bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy = "libroCaja", fetch = FetchType.EAGER)
	private List<Ingreso> ingresos;

	@Transient
	private BigDecimal totalGanancia;

	@Transient
	private BigDecimal totalGananciaDolares;

	@Transient
	private BigDecimal totalNeto;

	@Transient
	private BigDecimal totalNetoDolares;

	@Transient
	private BigDecimal totalEgresos;

	@Transient
	private BigDecimal totalEgresosDolares;

	@Transient
	private int totalEmpenos;

	public LibroCaja() {
	}

	@PostLoad
	public void procesarCamposCalculados() {

		totalGanancia = BigDecimal.ZERO;
		totalGananciaDolares = BigDecimal.ZERO;

		totalNeto = BigDecimal.ZERO;
		totalNetoDolares = BigDecimal.ZERO;

		totalEgresos = BigDecimal.ZERO;
		totalEgresosDolares = BigDecimal.ZERO;

		totalEmpenos = 0;

		cierre = BigDecimal.ZERO;
		cierreDolares = BigDecimal.ZERO;

		for (Ingreso i : ingresos) {
			if (i.getMoneda().equalsIgnoreCase("SOLES")) {
				totalGanancia = totalGanancia.add(i.getGanancia());

				BigDecimal SepR = (i.getTipo().startsWith("SEP") && i.getTipo().endsWith("(R)")) ? i.getOtro()
						: i.getCapital().add(i.getGanancia()).add(i.getOtro());

				totalNeto = totalNeto.add(SepR);
			} else {
				totalGananciaDolares = totalGananciaDolares.add(i.getGanancia());
				totalNetoDolares = totalNetoDolares.add(i.getCapital()).add(i.getGanancia()).add(i.getOtro());
			}
		}

		for (Egreso e : egresos) {
			if (e.getMoneda().equalsIgnoreCase("SOLES")) {
				totalEgresos = totalEgresos.add(e.getImporte());
			} else {
				totalEgresosDolares = totalEgresosDolares.add(e.getImporte());
			}
			if (e.getTipo().equalsIgnoreCase("EMP"))
				totalEmpenos++;
		}

		cierre = amanece.add(totalNeto).subtract(totalEgresos);
		cierreDolares = amaneceDolares.add(totalNetoDolares).subtract(totalEgresosDolares);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmanece() {
		return this.amanece;
	}

	public void setAmanece(BigDecimal amanece) {
		this.amanece = amanece;
	}

	public BigDecimal getCierre() {
		return this.cierre;
	}

	public void setCierre(BigDecimal cierre) {
		this.cierre = cierre;
	}

	public String getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Egreso> getEgresos() {
		return this.egresos;
	}

	public void setEgresos(List<Egreso> egresos) {
		this.egresos = egresos;
	}

	public Egreso addEgreso(Egreso egreso) {
		getEgresos().add(egreso);
		egreso.setLibroCaja(this);

		return egreso;
	}

	public Egreso removeEgreso(Egreso egreso) {
		getEgresos().remove(egreso);
		egreso.setLibroCaja(null);

		return egreso;
	}

	public List<Ingreso> getIngresos() {
		return this.ingresos;
	}

	public void setIngresos(List<Ingreso> ingresos) {
		this.ingresos = ingresos;
	}

	public Ingreso addIngreso(Ingreso ingreso) {
		getIngresos().add(ingreso);
		ingreso.setLibroCaja(this);

		return ingreso;
	}

	public Ingreso removeIngreso(Ingreso ingreso) {
		getIngresos().remove(ingreso);
		ingreso.setLibroCaja(null);

		return ingreso;
	}

	public BigDecimal getTotalGanancia() {
		return totalGanancia;
	}

	public void setTotalGanancia(BigDecimal totalGanancia) {
		this.totalGanancia = totalGanancia;
	}

	public BigDecimal getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}

	public BigDecimal getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(BigDecimal totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public int getTotalEmpenos() {
		return totalEmpenos;
	}

	public void setTotalEmpenos(int totalEmpenos) {
		this.totalEmpenos = totalEmpenos;
	}

	public JRDataSource getIngresosJasper() {
		return new JRBeanCollectionDataSource(ingresos);
	}

	public JRDataSource getEgresosJasper() {
		return new JRBeanCollectionDataSource(egresos);
	}

	public BigDecimal getAmaneceDolares() {
		return amaneceDolares;
	}

	public void setAmaneceDolares(BigDecimal amaneceDolares) {
		this.amaneceDolares = amaneceDolares;
	}

	public BigDecimal getCierreDolares() {
		return cierreDolares;
	}

	public void setCierreDolares(BigDecimal cierreDolares) {
		this.cierreDolares = cierreDolares;
	}

	public BigDecimal getTotalNetoDolares() {
		return totalNetoDolares;
	}

	public void setTotalNetoDolares(BigDecimal totalNetoDolares) {
		this.totalNetoDolares = totalNetoDolares;
	}

	public BigDecimal getTotalGananciaDolares() {
		return totalGananciaDolares;
	}

	public void setTotalGananciaDolares(BigDecimal totalGananciaDolares) {
		this.totalGananciaDolares = totalGananciaDolares;
	}

	public BigDecimal getTotalEgresosDolares() {
		return totalEgresosDolares;
	}

	public void setTotalEgresosDolares(BigDecimal totalEgresosDolares) {
		this.totalEgresosDolares = totalEgresosDolares;
	}

}