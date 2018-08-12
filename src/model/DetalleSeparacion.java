package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_separacion database table.
 * 
 */
@Entity
@Table(name="detalle_separacion")
@NamedQuery(name="DetalleSeparacion.findAll", query="SELECT d FROM DetalleSeparacion d")
public class DetalleSeparacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=10)
	private String fecha;

	@Column(precision=10, scale=2)
	private BigDecimal importe;

	//bi-directional many-to-one association to Separacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEPARACION_ID", nullable=false)
	private Separacion separacion;

	public DetalleSeparacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Separacion getSeparacion() {
		return this.separacion;
	}

	public void setSeparacion(Separacion separacion) {
		this.separacion = separacion;
	}

}