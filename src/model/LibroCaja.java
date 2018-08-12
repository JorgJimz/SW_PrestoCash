package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the libro_caja database table.
 * 
 */
@Entity
@Table(name="libro_caja")
@NamedQuery(name="LibroCaja.findAll", query="SELECT l FROM LibroCaja l")
public class LibroCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(precision=10, scale=2)
	private BigDecimal amanece;

	@Column(precision=10, scale=2)
	private BigDecimal cierre;

	@Column(name="FECHA_APERTURA", length=10)
	private String fechaApertura;

	@Column(name="FECHA_CIERRE", length=10)
	private String fechaCierre;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Egreso
	@OneToMany(mappedBy="libroCaja")
	private List<Egreso> egresos;

	//bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy="libroCaja")
	private List<Ingreso> ingresos;

	public LibroCaja() {
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

}