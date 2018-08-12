package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="egreso")
@NamedQuery(name="Egreso.findAll", query="SELECT e FROM Egreso e")
public class Egreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String descripcion;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal importe;

	@Column(nullable=false)
	private int tipo;

	//bi-directional many-to-one association to LibroCaja
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIBRO_CAJA_ID", nullable=false)
	private LibroCaja libroCaja;

	public Egreso() {
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

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public LibroCaja getLibroCaja() {
		return this.libroCaja;
	}

	public void setLibroCaja(LibroCaja libroCaja) {
		this.libroCaja = libroCaja;
	}

}