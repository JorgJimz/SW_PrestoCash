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

import common.Utiles;

@Entity
@Table(name = "e_contrato")
@NamedQuery(name = "EContrato.findAll", query = "SELECT e FROM EContrato e")
public class EContrato implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int VITRINA_SP = 13;
	public static final int POST = 7;
	public static final int PRE = 4;
	public static final int VENCIDO = 2;
	public static final int ACTIVO = 1;
	public static final int CANCELADO = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String valor;

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "EContrato")
	private List<Contrato> contratos;

	@Transient
	private Color background;

	@Transient
	private Color foreground;

	public EContrato() {
	}

	public EContrato(int id) {
		this.id = id;
	}

	public EContrato(int id, String descripcion) {
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
		contrato.setEContrato(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEContrato(null);

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