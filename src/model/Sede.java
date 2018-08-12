package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sede database table.
 * 
 */
@Entity
@Table(name="sede")
@NamedQuery(name="Sede.findAll", query="SELECT s FROM Sede s")
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String descripcion;

	@Column(length=100)
	private String direccion;

	@Column(name="FLAG_ELECTRO", nullable=false, length=1)
	private String flagElectro;

	@Column(name="FLAG_ORO", nullable=false, length=1)
	private String flagOro;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="T_SEDE", nullable=false, length=11)
	private String tSede;

	//bi-directional many-to-one association to DetalleCargo
	@OneToMany(mappedBy="sede")
	private List<DetalleCargo> detalleCargos;

	//bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy="sede")
	private List<Prestamo> prestamos;

	public Sede() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFlagElectro() {
		return this.flagElectro;
	}

	public void setFlagElectro(String flagElectro) {
		this.flagElectro = flagElectro;
	}

	public String getFlagOro() {
		return this.flagOro;
	}

	public void setFlagOro(String flagOro) {
		this.flagOro = flagOro;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTSede() {
		return this.tSede;
	}

	public void setTSede(String tSede) {
		this.tSede = tSede;
	}

	public List<DetalleCargo> getDetalleCargos() {
		return this.detalleCargos;
	}

	public void setDetalleCargos(List<DetalleCargo> detalleCargos) {
		this.detalleCargos = detalleCargos;
	}

	public DetalleCargo addDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().add(detalleCargo);
		detalleCargo.setSede(this);

		return detalleCargo;
	}

	public DetalleCargo removeDetalleCargo(DetalleCargo detalleCargo) {
		getDetalleCargos().remove(detalleCargo);
		detalleCargo.setSede(null);

		return detalleCargo;
	}

	public List<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Prestamo addPrestamo(Prestamo prestamo) {
		getPrestamos().add(prestamo);
		prestamo.setSede(this);

		return prestamo;
	}

	public Prestamo removePrestamo(Prestamo prestamo) {
		getPrestamos().remove(prestamo);
		prestamo.setSede(null);

		return prestamo;
	}

}