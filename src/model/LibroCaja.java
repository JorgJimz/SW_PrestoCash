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

	@Column(name = "FECHA_CIERRE")
	private String fechaCierre;

	private String status;

	// bi-directional many-to-one association to Egreso
	@OneToMany(mappedBy = "libroCaja", fetch = FetchType.EAGER)
	private List<Egreso> egresos;

	// bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy = "libroCaja", fetch = FetchType.EAGER)
	private List<Ingreso> ingresos;

	@Transient
	private BigDecimal totalGanancia;

	@Transient
	private BigDecimal totalNeto;

	@Transient
	private BigDecimal totalEgresos;

	@Transient
	private int totalEmpenos;

	public LibroCaja() {
	}

	@PostLoad
	public void procesarCamposCalculados() {
		totalGanancia = BigDecimal.ZERO;
		totalNeto = BigDecimal.ZERO;
		totalEgresos = BigDecimal.ZERO;
		totalEmpenos = 0;
		cierre = BigDecimal.ZERO;

		for (Ingreso i : ingresos) {
			totalGanancia = totalGanancia.add(i.getGanancia());
			totalNeto = totalNeto.add(i.getCapital()).add(i.getGanancia()).add(i.getOtro());
		}

		for (Egreso e : egresos) {
			totalEgresos = totalEgresos.add(e.getImporte());
			if (e.getTipo().contains("EMP"))
				totalEmpenos++;
		}
		
		cierre = totalNeto.subtract(totalEgresos);
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
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

}