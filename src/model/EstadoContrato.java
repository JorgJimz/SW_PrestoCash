package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import common.Utiles;

@Entity
@Table(name = "estado_contrato")
@NamedQuery(name = "EstadoContrato.findAll", query = "SELECT e FROM EstadoContrato e")
public class EstadoContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int ACTIVO = 1;
	public static final int VENCIDO = 2;
	public static final int EN_PROCESO = 3;
	public static final int PRE = 4;
	public static final int FUNDIDO = 5;
	public static final int CANCELADO = 6;
	public static final int POST = 7;
	public static final int VITRINA = 9;
	public static final int REMATADO = 11;
	public static final int USO_OFICINA = 12;
	public static final int VITRINA_SP = 13;
	public static final int ANULADO = 14;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;

	@Expose
	private String descripcion;

	private String valor;

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "estadoContrato")
	transient private List<Contrato> contratos;

	@Transient
	private Color background;

	@Transient
	private Color foreground;

	public EstadoContrato() {
	}

	public EstadoContrato(int id) {
		this.id = id;
	}

	public EstadoContrato(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	@PostLoad
	public void procesarCamposCalculados() {
		int[] v = Utiles.mapToInt(valor.split(","));
		background = new Color(v[0], v[1], v[2]);
		foreground = new Color(v[3], v[4], v[5]);
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setEstadoContrato(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEstadoContrato(null);

		return contrato;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

}