package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_cargo database table.
 * 
 */
@Entity
@Table(name="detalle_cargo")
@NamedQuery(name="DetalleCargo.findAll", query="SELECT d FROM DetalleCargo d")
public class DetalleCargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Articulo
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
	private Articulo articulo;

	//bi-directional many-to-one association to Cargo
	@ManyToOne(fetch=FetchType.LAZY)
	private Cargo cargo;

	//bi-directional many-to-one association to Contrato
	@ManyToOne(fetch=FetchType.LAZY)
	private Contrato contrato;

	//bi-directional many-to-one association to Sede
	@ManyToOne(fetch=FetchType.LAZY)
	private Sede sede;

	public DetalleCargo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}