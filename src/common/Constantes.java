package common;

import java.text.SimpleDateFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class Constantes {
	
	public static double PRIMERA_MORA = 1.3;
	public static double SEGUNDA_MORA = 1.5;
	public static double MORA_CERO = 0;
	
	public static ComboBoxModel DistritoModel = new DefaultComboBoxModel(new String[] {
			"CERCADO DE LIMA", "ATE", "BARRANCO", "BREÑA", "COMAS",
			"CHORRILLOS", "EL AGUSTINO", "JESUS MARIA", "LA MOLINA",
			"LA VICTORIA", "LINCE", "MAGDALENA", "MIRAFLORES", "PUEBLO LIBRE",
			"PTE.PIEDRA", "RIMAC", "SAN ISIDRO", "INDEPENDENCIA", "SJM",
			"SAN LUIS", "SMP", "SAN MIGUEL", "SURCO", "SURQUILLO", "VMT",
			"SJL", "SANTA ROSA", "LOS OLIVOS", "SAN BORJA", "VES",
			"SANTA ANITA", "CALLAO" });

	public static ComboBoxModel CategoriaModel = new DefaultComboBoxModel(new String[] {
			"BUENO", "MOROSO", "PROBLEMATICO" });
	
	public static ComboBoxModel MonedaModel = new DefaultComboBoxModel(new String[] {
			"SOLES", "DOLARES" });
	
	public static DefaultTableModel ContratoModel = new DefaultTableModel(null,
			new String[] { "CÓDIGO", "DESCRIPCIÓN", "MARCA", "MODELO",
					"OBSERVACIONES", "TASACIÓN" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (colIndex == 0) {
				return false;
			}
			return true;
		}
	};	
	
	public static DefaultTableModel ArticuloModel = new DefaultTableModel(null,
			new String[] { "CÓDIGO", "CANTIDAD", "DESCRIPCIÓN", "MARCA",
					"MODELO", "OBSERVACIONES", "COVER", "TASACIÓN" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	
	public static DefaultTableModel PagoModel = new DefaultTableModel(null,
			new String[] { "FECHA VCTO.", "FECHA PAGO", "INTERES", "MORA",
					"TOTAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	
	public static DefaultComboBoxModel<ComboItem> PrestamoModel = new DefaultComboBoxModel();
	
	public static SimpleDateFormat formatoLocal = new SimpleDateFormat("dd-MMM-yyyy");
	public static SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM");
	
}
