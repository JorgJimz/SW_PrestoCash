package common;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Predicate;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import model.Abono;
import model.Articulo;
import model.Asistencia;
import model.DetalleContrato;
import model.EArticulo;
import model.EContrato;
import model.Egreso;
import model.Pago;
import model.Separacion;
import view.Principal;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class Constantes {

	public static final String URI_WS = "http://localhost:8090";

	public static BigDecimal PRIMERA_MORA = new BigDecimal(0.30).setScale(2, RoundingMode.HALF_UP);
	public static BigDecimal SEGUNDA_MORA = new BigDecimal(0.50);
	public static BigDecimal MORA_SOLES = new BigDecimal(150);
	public static BigDecimal MORA_CERO = BigDecimal.ZERO;

	public static final int RANGO_PRE = 5;
	public static final int RANGO_POST = 15;

	public static final String[] ESTADOS_ALERTA = new String[] { "VENCIDO", "PRE", "POST", "VITRINA", "VITRINA (SP)" };

	public static final Integer[] ESTADOS_INACTIVIDAD_CONTRATO = new Integer[] { EContrato.EN_PROCESO,
			EContrato.FUNDIDO, EContrato.CANCELADO, EContrato.VITRINA, 10, EContrato.REMATADO, EContrato.USO_OFICINA,
			EContrato.VITRINA_SP, EContrato.ANULADO };

	public static final Integer[] ESTADOS_NO_VIGENCIA_CONTRATO = new Integer[] { EContrato.EN_PROCESO,
			EContrato.FUNDIDO, EContrato.CANCELADO, EContrato.VITRINA, 10, EContrato.REMATADO, EContrato.USO_OFICINA,
			EContrato.ANULADO };

	public static final Integer[] ESTADOS_INACTIVIDAD_ARTICULO = new Integer[] { EArticulo.BAJA, EArticulo.FUNDIDO,
			EArticulo.LIBRE, EArticulo.REMATADO, EArticulo.SEPARADO };

	public static DefaultTableModel HistorialModel = new DefaultTableModel(null,
			new String[] { "CONTRATO", "INICIO", "VENCIMIENTO", "REMATE", "ESTADO", "ARTICULO", "TIPO", "VALOR" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel ClienteModel = new DefaultTableModel(null,
			new String[] { "ID", "DOC.IDENTIDAD", "NOMBRE", "A.PATERNO", "A.MATERNO", "E-MAIL", "TLF 1", "TLF 2",
					"DIRECCIÓN", "DISTRITO", "CATEGORIA", "DETALLE", "STATUS" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel PrestamoModel = new DefaultTableModel(null,
			new String[] { "ID", "DESCRIPCIÓN", "INTERES (%)", "TIPO MORA", "MORA (%)", "TIPO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel UsuarioModel = new DefaultTableModel(null,
			new String[] { "ID", "LOGIN", "A.PATERNO", "A.MATERNO", "NOMBRE", "CLAVE", "TIPO", "INGRESO", "STATUS" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel VitrinaModel = new DefaultTableModel(null, new String[] { "CONTRATO", "CÓDIGO",
			"DESCRIPCIÓN", "MARCA", "MODELO", "OBSERVACIONES", "CAPITAL", "PRECIO VENTA", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static ComboBoxModel DistritoModel = new DefaultComboBoxModel(new String[] { "CERCADO DE LIMA", "ATE",
			"BARRANCO", "BREÑA", "COMAS", "CHORRILLOS", "EL AGUSTINO", "JESUS MARIA", "LA MOLINA", "LA VICTORIA",
			"LINCE", "MAGDALENA", "MIRAFLORES", "PUEBLO LIBRE", "PTE.PIEDRA", "RIMAC", "SAN ISIDRO", "INDEPENDENCIA",
			"SJM", "SAN LUIS", "SMP", "SAN MIGUEL", "SURCO", "SURQUILLO", "VMT", "SJL", "SANTA ROSA", "LOS OLIVOS",
			"SAN BORJA", "VES", "SANTA ANITA", "CALLAO" });

	public static ComboBoxModel CategoriaModel = new DefaultComboBoxModel(
			new String[] { "BUENO", "MOROSO", "PROBLEMATICO" });

	public static ComboBoxModel TipoMoraModel = new DefaultComboBoxModel(new String[] { "%", "M" });

	public static ComboBoxModel TipoDocumentoModel = new DefaultComboBoxModel(new String[] { "DNI", "CEX", "PAS" });

	public static ComboBoxModel MonedaModel = new DefaultComboBoxModel(new String[] { "SOLES", "DÓLARES" });

	public static DefaultTableModel AbonoModel = new DefaultTableModel(null, new String[] { "FECHA PAGO",
			"CAPITAL ANTERIOR", "INTERES ANTERIOR", "IMPORTE ABONADO", "NUEVO CAPITAL", "NUEVO INTERES" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel PagoModel = new DefaultTableModel(null,
			new String[] { "ID", "FECHA VCTO.", "FECHA PAGO", "CONCEPTO", "INTERES", "MORA", "TOTAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel CargoModel = new DefaultTableModel(null,
			new String[] { "FECHA", "TRANSPORTISTA", "ARTICULO", "SEDE", "OBS" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel SeguimientoModel = new DefaultTableModel(null,
			new String[] { "TIPO", "FECHA LLAMADA", "RESULTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public static DefaultTableModel ActualizacionModel = new DefaultTableModel(null,
			new String[] { "NÚMERO DE CONTRATO", "INICIO", "VENCIMIENTO", "REMATE", "ESTADO ACTUAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}

		@Override
		public Class getColumnClass(int columna) {
			return Integer.class;
		}
	};

	public static DateTimeFormatter formatoCaja = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
	public static DateTimeFormatter formatoLocal = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	public static DateTimeFormatter formatoSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("MMMM");
	public static DateTimeFormatter formatoDiaHora = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

	public static SimpleDateFormat formatoSQL_2 = new SimpleDateFormat("yyyy-MM-dd");

	public static Comparator<Pago> PagoComparator = new Comparator<Pago>() {
		@Override
		public int compare(Pago p1, Pago p2) {
			return LocalDate.parse(p1.getFechaPago()).compareTo(LocalDate.parse(p2.getFechaPago()));
		}
	};

	public static Comparator<DetalleContrato> UltimoContratoComparator = new Comparator<DetalleContrato>() {
		@Override
		public int compare(DetalleContrato c1, DetalleContrato c2) {
			return LocalDate.parse(c1.getContrato().getFechaContrato())
					.compareTo(LocalDate.parse(c2.getContrato().getFechaContrato()));
		}
	};

	public static Predicate<Asistencia> predicadoAsistencia(String fechaCaja) {
		return t -> LocalDate.parse(t.getFecha()).compareTo(LocalDate.parse(fechaCaja)) == 0;
	};

	public static Predicate<Articulo> predicadoArticulo(String contrato) {
		return p -> p.getFlagContrato().concat("-").concat(String.valueOf(p.getNumeroContrato())).contains(contrato);
	};

	public static Predicate<Egreso> predicadoAnularEgreso(String contrato) {
		return e -> e.getDescripcion().startsWith(contrato) && e.getTipo().equalsIgnoreCase("EMP")
				&& e.getLibroCaja().getId() == Principal.LIBRO_CAJA.getId();
	};

	public static Predicate<Pago> predicadoPago = new Predicate<Pago>() {
		@Override
		public boolean test(Pago p) {
			return p.getDescripcion().equalsIgnoreCase("CANCELACIÓN");
		}
	};

	public static Predicate<DetalleContrato> predicadoConversorVitrina = new Predicate<DetalleContrato>() {
		@Override
		public boolean test(DetalleContrato dc) {
			return dc.getArticulo().getEArticulo().getId() == EArticulo.VITRINA;
		}
	};

	public static Predicate<DetalleContrato> predicadoConversorRemSep = new Predicate<DetalleContrato>() {
		@Override
		public boolean test(DetalleContrato dc) {
			return dc.getArticulo().getEArticulo().getId() == EArticulo.SEPARADO
					|| dc.getArticulo().getEArticulo().getId() == EArticulo.REMATADO;
		}
	};

	public static Predicate<Separacion> predicadoSeparacionFinalizada = new Predicate<Separacion>() {
		@Override
		public boolean test(Separacion s) {
			return s.getStatus() == 2;
		}
	};

	public static Predicate<Component> predicadoCajaTexto = new Predicate<Component>() {
		@Override
		public boolean test(Component p) {
			return (p instanceof JLabel && ((JLabel) p).getBackground() == Color.WHITE) ? true : false;
		}
	};

	public static Predicate<Pago> predicadoBuscarPago(int id) {
		return p -> p.getId() == id;
	};

	public static Predicate<Abono> predicadoBuscarAbono(int id) {
		return a -> a.getId() == id;
	};

}
