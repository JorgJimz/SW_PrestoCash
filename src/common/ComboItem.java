package common;

public class ComboItem {

	private int id;
	private String descripcion;
	private Object valor;
	private Object extraValor;

	public ComboItem() {
	}

	public ComboItem(int id, String descripcion, Object valor) {
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
	}

	public ComboItem(int id, String descripcion, Object valor, Object extraValor) {
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
		this.extraValor = extraValor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Object getExtraValor() {
		return extraValor;
	}

	public void setExtraValor(Object extraValor) {
		this.extraValor = extraValor;
	}

	@Override
	public String toString() {
		return descripcion;
	}
	/*
	 * private String value; private int value_int; private String display;
	 * private double interes; private StringBuilder value_builder;
	 * 
	 * public ComboItem(String value, String display) {
	 * 
	 * this.value = value; this.display = display; }
	 * 
	 * public ComboItem(int value_int, String display) {
	 * 
	 * this.value_int = value_int; this.display = display; }
	 * 
	 * public ComboItem(String value, String display, double interes) {
	 * 
	 * this.value = value; this.display = display; this.interes = interes; }
	 * 
	 * public int getValue_int() { return value_int; }
	 * 
	 * public StringBuilder getValue_builder() { return value_builder; }
	 * 
	 * public void setValue_builder(StringBuilder value_builder) {
	 * this.value_builder = value_builder; }
	 * 
	 * public void setValue_int(int value_int) { this.value_int = value_int; }
	 * 
	 * 
	 * public String getValue() { return value; }
	 * 
	 * public void setValue(String value) { this.value = value; }
	 * 
	 * public String getDisplay() { return display; }
	 * 
	 * public void setDisplay(String display) { this.display = display; }
	 * 
	 * public double getInteres() { return interes; }
	 * 
	 * public void setInteres(double interes) { this.interes = interes; }
	 * 
	 * public String toString() { return display; }
	 */
}
