package common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Predicate;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import model.Asistencia;
import model.Pago;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class Constantes {

	public static BigDecimal PRIMERA_MORA = new BigDecimal(0.30).setScale(2,
			RoundingMode.HALF_UP);
	public static BigDecimal SEGUNDA_MORA = new BigDecimal(0.50);
	public static BigDecimal MORA_SOLES = new BigDecimal(150);
	public static BigDecimal MORA_CERO = BigDecimal.ZERO;

	public static ComboBoxModel DistritoModel = new DefaultComboBoxModel(
			new String[] { "CERCADO DE LIMA", "ATE", "BARRANCO", "BRE�A",
					"COMAS", "CHORRILLOS", "EL AGUSTINO", "JESUS MARIA",
					"LA MOLINA", "LA VICTORIA", "LINCE", "MAGDALENA",
					"MIRAFLORES", "PUEBLO LIBRE", "PTE.PIEDRA", "RIMAC",
					"SAN ISIDRO", "INDEPENDENCIA", "SJM", "SAN LUIS", "SMP",
					"SAN MIGUEL", "SURCO", "SURQUILLO", "VMT", "SJL",
					"SANTA ROSA", "LOS OLIVOS", "SAN BORJA", "VES",
					"SANTA ANITA", "CALLAO" });

	public static ComboBoxModel CategoriaModel = new DefaultComboBoxModel(
			new String[] { "BUENO", "MOROSO", "PROBLEMATICO" });

	public static ComboBoxModel MonedaModel = new DefaultComboBoxModel(
			new String[] { "SOLES", "DOLARES" });

	public static DefaultTableModel ContratoModel = new DefaultTableModel(null,
			new String[] { "C�DIGO", "DESCRIPCI�N", "MARCA", "MODELO",
					"OBSERVACIONES", "TASACI�N" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (colIndex == 0) {
				return false;
			}
			return true;
		}
	};

	public static DefaultTableModel ArticuloModel = new DefaultTableModel(null,
			new String[] { "C�DIGO", "CANTIDAD", "DESCRIPCI�N", "MARCA",
					"MODELO", "OBSERVACIONES", "COVER", "TASACI�N" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel PagoModel = new DefaultTableModel(null,
			new String[] { "FECHA VCTO.", "FECHA PAGO", "CONCEPTO", "INTERES",
					"MORA", "TOTAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel IngresoModel = new DefaultTableModel(null,
			new String[] { "DESCRIPCI�N", "TIPO", "CAPITAL", "GANANCIA",
					"OTROS", "NETO" }) {
		private final Class[] columnClass = new Class[] { String.class,
				String.class, BigDecimal.class, BigDecimal.class,
				BigDecimal.class, BigDecimal.class };

		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}

		public Class<?> getColumnClass(int columnIndex) {
			return columnClass[columnIndex];
		}
	};
	public static DefaultTableModel EgresoModel = new DefaultTableModel(null,
			new String[] { "DESCRIPCI�N", "TIPO", "MONTO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel CargoModel = new DefaultTableModel(null,
			new String[] { "FECHA", "TRANSPORTISTA", "ARTICULO", "SEDE" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel SeguimientoModel = new DefaultTableModel(
			null, new String[] { "TIPO", "FECHA LLAMADA", "RESULTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel ActualizacionModel = new DefaultTableModel(
			null, new String[] { "N�MERO DE CONTRATO", "INICIO", "VENCIMIENTO",
					"REMATE", "ESTADO ACTUAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}

		@Override
		public Class getColumnClass(int columna) {
			return Integer.class;
		}
	};

	public static DefaultComboBoxModel<ComboItem> PrestamoModel = new DefaultComboBoxModel();

	public static DateTimeFormatter formatoCaja = DateTimeFormatter
			.ofPattern("  EEEE, dd MMMM yyyy");
	public static DateTimeFormatter formatoLocal = DateTimeFormatter
			.ofPattern("dd-MMM-yyyy");
	public static DateTimeFormatter formatoSQL = DateTimeFormatter
			.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter formatoMes = DateTimeFormatter
			.ofPattern("MMMM");
	public static SimpleDateFormat formatoDate = new SimpleDateFormat(
			"dd/MM/yyyy");

	public static Comparator<Pago> PagoComparator = new Comparator<Pago>() {
		@Override
		public int compare(Pago p1, Pago p2) {
			return LocalDate.parse(p1.getFechaPago()).compareTo(
					LocalDate.parse(p2.getFechaPago()));
		}
	};

	public static Predicate<Asistencia> predicadoAsistencia = new Predicate<Asistencia>() {
		@Override
		public boolean test(Asistencia t) {
			return LocalDate.parse(t.getFecha()).compareTo(LocalDate.now()) == 0;
		}
	};

}
