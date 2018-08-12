package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import Utils.Constantes;
import Utils.EditorIM;
import Utils.Redondeo;
import Utils.RenderIM;
import Beans.Auditoria;
import Beans.Contrato;
import Beans.Ingreso;
import Beans.Mora;
import Beans.Pago_Contrato;
import Beans.Contrato.Detalle_Contrato;
import Conexion.MySQLConexion;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Consultar_Contrato extends JInternalFrame {
	private JLabel jLabel1;
	private JLabel lblEstado;
	private JLabel jLabel4;
	private JTabbedPane tpContrato;
	private JTable tbArticulos;
	private JLabel jLabel5;
	private JLabel lblCapital;
	private JLabel lblInteresMensual;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JPanel pnlArticulos;
	private JPanel pnlMoras;
	private JPanel pnlPagos;
	private JLabel lblRemate;
	private JLabel lblVencimiento;
	private JLabel lblInicio;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel lblNumeroContrato;
	private JPanel contenedor;
	private String identificador;
	private JLabel lblTipo;
	private JSeparator jSeparator1;
	private JTable tbMoras;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JLabel lblInteresDiario;
	private JLabel jLabel16;
	private JTable tbHistorial;
	private JButton btnImprimir;
	private JButton btnAnular;
	private JButton btnCancelar;
	private JButton tbnRenovar;
	private JLabel jLabel15;
	private JLabel lblCliente;
	private JLabel lblIdentidad;
	private DecimalFormat fd;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	private Date inicio;
	private Date vcto;
	private Date remate;
	private JTable tbIntereses;
	private JPanel pnlInteres;
	private JPanel pnlAbonos;
	private JScrollPane jScrollPane5;
	private JTable tbAbonos;
	private JScrollPane jScrollPane4;
	DefaultTableModel modeloDetalleContrato = new DefaultTableModel(null,
			new String[] { "CÓDIGO", "DESCRIPCIÓN", "MARCA", "MODELO",
					"OBSERVACIONES", "TASACIÓN" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloHistorial = new DefaultTableModel(null,
			new String[] { "FECHA VCTO.", "FECHA PAGO", "INTERES", "MORA",
					"TOTAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloMora = new DefaultTableModel(null, new String[] {
			"ID", "MONTO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloInteres = new DefaultTableModel(null, new String[] {
			"MES", "MONTO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbIntereses.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel modeloAbonos = new DefaultTableModel(null, new String[] {
			"FECHA PAGO", "CAPITAL ANTERIOR", "INTERES ANTERIOR",
			"IMPORTE ABONADO", "NUEVO CAPITAL", "NUEVO INTERES" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbIntereses.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	String[] a = null;
	private JLabel lblTipoMoneda;
	private JTextArea lblEstadoDetalle;

	public Consultar_Contrato(String numero) throws Exception {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(897, 426);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1265, 819));
		this.setBounds(0, 0, 1265, 819);
		identificador = numero;
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1264, 794);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		simbolo.setDecimalSeparator('.');
		fd = new DecimalFormat("#####0.00", simbolo);
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("NRO CONTRATO");
			jLabel1.setBounds(12, 13, 167, 32);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel1.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			lblEstado = new JLabel();
			contenedor.add(lblEstado);
			lblEstado.setBounds(12, 54, 290, 50);
			lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 36));
			lblEstado.setOpaque(true);
			lblEstado.setBackground(Color.WHITE);
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			lblNumeroContrato = new JLabel();
			contenedor.add(lblNumeroContrato);
			lblNumeroContrato.setBounds(185, 12, 117, 32);
			lblNumeroContrato.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblNumeroContrato.setBackground(new java.awt.Color(255, 255, 255));
			lblNumeroContrato.setOpaque(true);
			lblNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("INICIO:");
			jLabel2.setBounds(324, 13, 81, 32);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel2.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("VENCE:");
			jLabel3.setBounds(580, 13, 81, 32);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel3.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("REMATE:");
			jLabel4.setBounds(840, 13, 96, 32);
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel4.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			lblInicio = new JLabel();
			contenedor.add(lblInicio);
			lblInicio.setBounds(399, 12, 168, 32);
			lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblInicio.setBackground(new java.awt.Color(255, 255, 255));
			lblInicio.setOpaque(true);
			lblInicio.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblVencimiento = new JLabel();
			contenedor.add(lblVencimiento);
			lblVencimiento.setBounds(656, 12, 169, 32);
			lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblVencimiento.setBackground(new java.awt.Color(255, 255, 255));
			lblVencimiento.setOpaque(true);
			lblVencimiento.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblRemate = new JLabel();
			contenedor.add(lblRemate);
			lblRemate.setBounds(932, 12, 168, 32);
			lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblRemate.setBackground(new java.awt.Color(255, 255, 255));
			lblRemate.setOpaque(true);
			lblRemate.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			{
				tpContrato = new JTabbedPane();
				contenedor.add(tpContrato);
				tpContrato.setBounds(12, 125, 1221, 339);
				tpContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
				pnlArticulos = new JPanel();
				pnlInteres = new JPanel();
				pnlMoras = new JPanel();
				pnlPagos = new JPanel();
				pnlAbonos = new JPanel();
				tpContrato.addTab("ARTÍCULOS", null, pnlArticulos, null);
				tpContrato.addTab("INTERESES", null, pnlInteres, null);
				tpContrato.addTab("ABONOS", null, pnlAbonos, null);
				jScrollPane5 = new JScrollPane();
				pnlAbonos.add(jScrollPane5);
				jScrollPane5.setPreferredSize(new java.awt.Dimension(882, 153));
				tbAbonos = new JTable();
				tbAbonos.setModel(modeloAbonos);
				tbAbonos.setRowHeight(30);
				tbAbonos.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbAbonos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbAbonos.getTableHeader().setForeground(new Color(181, 0, 0));
				jScrollPane5.setViewportView(tbAbonos);
				jScrollPane4 = new JScrollPane();
				pnlInteres.add(jScrollPane4);
				jScrollPane4
						.setPreferredSize(new java.awt.Dimension(1182, 274));
				tbIntereses = new JTable();
				jScrollPane4.setViewportView(tbIntereses);
				tbIntereses.setModel(modeloInteres);
				jScrollPane5
						.setPreferredSize(new java.awt.Dimension(1189, 275));
				jScrollPane1 = new JScrollPane();
				pnlArticulos.add(jScrollPane1);
				jScrollPane1
						.setPreferredSize(new java.awt.Dimension(1187, 278));
				tbArticulos = new JTable();
				jScrollPane1.setViewportView(tbArticulos);
				tbArticulos.setModel(modeloDetalleContrato);
				tbArticulos.setRowHeight(30);
				tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbArticulos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbArticulos.getTableHeader()
						.setForeground(new Color(181, 0, 0));
				tpContrato.addTab("PAGOS", null, pnlPagos, null);
				jScrollPane2 = new JScrollPane();
				pnlPagos.add(jScrollPane2);
				jScrollPane2
						.setPreferredSize(new java.awt.Dimension(1182, 275));
				tbHistorial = new JTable();
				jScrollPane2.setViewportView(tbHistorial);
				tbHistorial.setModel(modeloHistorial);
				tbHistorial.setRowHeight(30);
				tbHistorial.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbHistorial.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbHistorial.getTableHeader()
						.setForeground(new Color(181, 0, 0));
				tpContrato.addTab("MORAS", null, pnlMoras, null);
				jScrollPane3 = new JScrollPane();
				pnlMoras.add(jScrollPane3);
				jScrollPane3
						.setPreferredSize(new java.awt.Dimension(1181, 274));
				tbMoras = new JTable();
				jScrollPane3.setViewportView(tbMoras);
				tbMoras.setModel(modeloMora);
				tbMoras.setDefaultRenderer(Object.class, new RenderIM());
				tbMoras.setDefaultEditor(Object.class, new EditorIM());
				tbMoras.setRowHeight(30);
				tbMoras.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbMoras.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbMoras.getTableHeader().setForeground(new Color(181, 0, 0));
				tbIntereses.setDefaultRenderer(Object.class, new RenderIM());
				tbIntereses.setDefaultEditor(Object.class, new EditorIM());
				tbIntereses.setRowHeight(30);
				tbIntereses.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbIntereses.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbIntereses.getTableHeader()
						.setForeground(new Color(181, 0, 0));
			}
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("MONEDA:");
			jLabel5.setBounds(324, 63, 106, 32);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel5.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setText("CLIENTE:");
			jLabel6.setBounds(500, 63, 87, 32);
			jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel6.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("% MENSUAL");
			jLabel7.setBounds(635, 505, 134, 32);
			jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel7.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("CAPITAL");
			jLabel8.setBounds(18, 502, 133, 32);
			jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel8.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			lblInteresMensual = new JLabel();
			contenedor.add(lblInteresMensual);
			lblInteresMensual.setBounds(769, 504, 143, 32);
			lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblInteresMensual.setBackground(new java.awt.Color(255, 255, 255));
			lblInteresMensual.setOpaque(true);
			lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblCapital = new JLabel();
			contenedor.add(lblCapital);
			lblCapital.setBounds(151, 504, 143, 32);
			lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCapital.setBackground(new java.awt.Color(255, 255, 255));
			lblCapital.setOpaque(true);
			lblCapital.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblIdentidad = new JLabel();
			contenedor.add(lblIdentidad);
			lblIdentidad.setBounds(593, 63, 143, 32);
			lblIdentidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblIdentidad.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblIdentidad.setBackground(new java.awt.Color(255, 255, 255));
			lblIdentidad.setOpaque(true);
			lblIdentidad.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblCliente = new JLabel();
			contenedor.add(lblCliente);
			lblCliente.setBounds(742, 63, 492, 32);
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCliente.setBackground(new java.awt.Color(255, 255, 255));
			lblCliente.setOpaque(true);
			lblCliente.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel16 = new JLabel();
			contenedor.add(jLabel16);
			jLabel16.setText("% DIARIO");
			jLabel16.setBounds(333, 504, 133, 32);
			jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel16.setForeground(new java.awt.Color(0, 64, 0));
		}
		{
			lblInteresDiario = new JLabel();
			contenedor.add(lblInteresDiario);
			lblInteresDiario.setBounds(445, 504, 143, 32);
			lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
			lblInteresDiario.setOpaque(true);
			lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jSeparator1 = new JSeparator();
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(13, 486, 1221, 10);
		}
		{
			lblTipo = new JLabel();
			contenedor.add(lblTipo);
			lblTipo.setBounds(1112, 12, 122, 32);
			lblTipo.setBackground(new java.awt.Color(255, 255, 255));
			lblTipo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblTipo.setOpaque(true);
			lblTipo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblTipo.setForeground(new java.awt.Color(0, 64, 128));
		}

		tbnRenovar = new JButton(new ImageIcon("img/im.png"));
		contenedor.add(tbnRenovar);
		tbnRenovar.setText("   RENOVAR");
		tbnRenovar.setBounds(12, 553, 290, 87);
		tbnRenovar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		tbnRenovar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		tbnRenovar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
				Renovacion_Contrato rc = new Renovacion_Contrato(
						lblNumeroContrato.getText());
				Principal.dskPrincipal.add(rc);
				rc.moveToFront();
			}
		});

		btnCancelar = new JButton(new ImageIcon("img/pag.png"));
		contenedor.add(btnCancelar);
		btnCancelar.setText("   CANCELAR");
		btnCancelar.setBounds(322, 553, 290, 87);
		btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
				Cancelacion_Contrato cc = new Cancelacion_Contrato(
						lblNumeroContrato.getText());
				Principal.dskPrincipal.add(cc);
				cc.moveToFront();
			}
		});

		lblTipoMoneda = new JLabel();
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(430, 54, 58, 50);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		btnAnular = new JButton(new ImageIcon("img/anular.png"));
		contenedor.add(btnAnular);
		btnAnular.setText("   ANULAR");
		btnAnular.setBounds(944, 553, 290, 87);
		btnAnular.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnAnular.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnAnular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				anularContrato(Integer.parseInt(lblNumeroContrato.getText()));
			}
		});

		btnImprimir = new JButton(new ImageIcon("img/print.png"));
		contenedor.add(btnImprimir);
		btnImprimir.setText("   IMPRIMIR");
		btnImprimir.setBounds(633, 553, 290, 87);
		btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnImprimir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				imprimirContrato(Integer.parseInt(lblNumeroContrato.getText()));

			}
		});

		lblEstadoDetalle = new JTextArea(
				"NO SE ENCONTRÓ INFORMACIÓN HISTÓRICA PARA MOSTRAR.");
		contenedor.add(lblEstadoDetalle);
		lblEstadoDetalle.setBounds(12, 652, 1221, 121);
		lblEstadoDetalle.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblEstadoDetalle.setForeground(new java.awt.Color(255, 255, 255));
		lblEstadoDetalle.setBackground(new java.awt.Color(0, 64, 128));
		lblEstadoDetalle.setOpaque(true);
		lblEstadoDetalle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		a = arrayTablas(Integer.parseInt(identificador));
		cargarContrato(identificador, a[0], a[1]);
	}

	public void cargarContrato(String numero, String tabla, String detalle)
			throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM " + tabla + " WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(numero));
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cargarDatosCliente(rs.getString(2));
				cargarDetalleContrato(rs.getInt("id_con"), tabla, detalle);
				cargarHistorialPagos(rs.getInt("id_con"));
				cargarHistorialMoras(rs.getInt("id_con"));
				cargarHistorialAbonos(rs.getInt("id_con"));
				cargarTipoPrestamo(rs.getString("tb_prestamo_cod_pre"));
				determinarAccion(lblEstado.getText().toUpperCase());
				cargarDetalleEstado(String.valueOf(rs.getInt("id_con")));
			} else {
				JOptionPane.showMessageDialog(null,
						"No existe tal número de Contrato");
				this.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cargarDatosCliente(String documento) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, documento);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblIdentidad.setText(rs.getString(1));
				lblCliente.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cargarDetalleContrato(int codigo, String tabla, String detalle)
			throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM "
					+ tabla
					+ " c INNER JOIN "
					+ detalle
					+ " dc ON c.id_con= dc."
					+ tabla
					+ "_id_con INNER JOIN tb_articulo a ON dc.tb_articulo_cod_art=a.cod_art INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE "
					+ tabla + "_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			modeloDetalleContrato.setRowCount(0);
			while (rs.next()) {
				String tipo = (rs.getString("mon_con")
						.equalsIgnoreCase("SOLES")) ? "S/." : "$";
				inicio = sdf.parse(rs.getString("fec_con"));
				vcto = sdf.parse(rs.getString("fec_ven_con"));
				remate = sdf.parse(rs.getString("fec_rem_con"));
				modeloDetalleContrato.addRow(new Object[] {
						rs.getInt("cod_art"), rs.getString("des_art"),
						rs.getString("mar_art"), rs.getString("mod_art"),
						rs.getString("obs_art"),
						fd.format(rs.getDouble("tas_det_con")) });
				lblNumeroContrato.setText(String.format("%07d",
						rs.getInt("id_con")));
				lblInicio.setText(new SimpleDateFormat("dd-MMM-yyyy").format(
						inicio).toUpperCase());
				lblVencimiento.setText(new SimpleDateFormat("dd-MMM-yyyy")
						.format(vcto).toUpperCase());
				lblRemate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(
						remate).toUpperCase());
				lblCapital.setText(fd.format(rs.getDouble("cap_con")));
				lblInteresMensual.setText(fd.format(rs.getDouble("int_con")));
				lblEstado.setText(rs.getString("des_est"));
				lblInteresDiario.setText(fd.format((Double
						.parseDouble(lblInteresMensual.getText()) / 30)));
				lblTipoMoneda.setText(tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void determinarAccion(String estado) throws Exception {
		if (estado.equalsIgnoreCase("ACTIVO")) {
			activo();
		} else if (estado.equalsIgnoreCase("VENCIDO")) {
			vencido();
		} else if (estado.equalsIgnoreCase("PRE")) {
			pre();
		} else if (estado.equalsIgnoreCase("POST")) {
			post();
		} else {
			/* REMATADO */
			remate();
		}
	}

	public void cargarDetalleEstado(String numero) {
		String estado = lblEstado.getText();
		Connection con = MySQLConexion.getConexion();
		StringBuffer cadena = new StringBuffer();
		if (estado.equalsIgnoreCase("CANCELADO")) {
			try {
				String sql = "SELECT i.*,lc.fec_caja FROM tb_ingreso i INNER JOIN tb_libro_caja lc ON i.tb_libro_caja_id_caja = lc.id_caja WHERE desc_ing LIKE ? and tip_ing LIKE ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, "%" + numero + "%");
				pst.setString(2, "%PAG%");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					double sum = rs.getDouble("cap_ing")
							+ rs.getDouble("gan_ing") + rs.getDouble("oto_ing");
					cadena.append("CANCELADO EL "
							+ new SimpleDateFormat("dd-MMM-yyyy").format(
									rs.getDate("fec_caja")).toUpperCase()
							+ " CON S/."
							+ Redondeo.redondearCentimos(String.valueOf(fd
									.format(sum))));
					cadena.append(System.getProperty("line.separator"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (estado.equalsIgnoreCase("SEPARADO")) {
			try {
				String sql = "select distinct s.tb_articulo_cod_art, concat(des_art,space(1),mar_art) as 'articulo', max(fec_sep) as 'fecha', sum(s.cta_sep) as 'acum',s.cap_sep from tb_separacion s inner join tb_articulo a on s.tb_articulo_cod_art=a.cod_art where s.id_con_asoc=? group by articulo";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblNumeroContrato.getText()));
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					cadena.append("SEPARADO EL "
							+ rs.getString("fecha")
							+ " - "
							+ rs.getString("articulo")
							+ " - VA S/."
							+ Redondeo.redondearCentimos(String.valueOf(fd
									.format(rs.getDouble("acum")))) + " DE S/."
							+ rs.getDouble("cap_sep"));
					cadena.append(System.getProperty("line.separator"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				String sql = "SELECT * FROM prestocash.tb_cargo c INNER JOIN tb_detalle_cargo dc ON c.id_car = dc.id_car WHERE nro_con=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblNumeroContrato.getText()));
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					cadena.append("CON CARGO EL "
							+ new SimpleDateFormat("dd-MMM-yyyy").format(
									rs.getDate("fec_car")).toUpperCase()
							+ " OBS:" + rs.getString("obs_car") + " DESTINO: "
							+ rs.getString("des_car"));
					cadena.append(System.getProperty("line.separator"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				lblEstadoDetalle.setText(cadena.toString());
	}

	public void cargarHistorialPagos(int numero) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT ven_con,fec_pag,mon_cap_pag,mon_int_pag,mon_mor_pag FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Date vcto = new SimpleDateFormat("yyyy-MM-dd").parse(rs
						.getString(1));
				Date pago = new SimpleDateFormat("yyyy-MM-dd").parse(rs
						.getString(2));
				modeloHistorial.addRow(new Object[] {
						new SimpleDateFormat("dd-MMM-yyyy").format(vcto)
								.toUpperCase(),
						new SimpleDateFormat("dd-MMM-yyyy").format(pago)
								.toUpperCase(), fd.format(rs.getDouble(4)),
						fd.format(rs.getDouble(5)),
						fd.format(rs.getDouble(4) + rs.getDouble(5)) });
			}
			tbHistorial.setModel(modeloHistorial);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	/* METODOS DE GESTION DE CONTRATOS */

	public void activo() throws Exception {
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(0, 128, 0));
	}

	public void vencido() throws Exception {
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(240, 80, 0));
	}

	public void remate() throws Exception {
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(200, 0, 0));
	}

	public void pre() throws Exception {
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(255, 0, 128));

	}

	public void post() throws Exception {
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(128, 0, 255));
	}

	public void cargarHistorialAbonos(int contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_pago_capital WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			modeloAbonos.setRowCount(0);
			while (rs.next()) {
				modeloAbonos.addRow(new Object[] {
						new SimpleDateFormat("dd-MMM-yyyy").format(
								rs.getDate(3)).toUpperCase(), rs.getDouble(4),
						rs.getDouble(5), rs.getDouble(4) - rs.getDouble(6),
						rs.getDouble(6), rs.getDouble(7) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void actualizarContrato(int contrato, String tabla)
			throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=1 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Contrato Renovado.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void retirarMora(int contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_mora SET est_mora=0 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void internalClose() {
		this.dispose();
	}

	public void cargarHistorialMoras(int contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_mora WHERE id_con=? AND est_mora=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				modeloMora.addRow(new Object[] { rs.getInt("id_mora"),
						rs.getDouble("val_mora"), rs.getInt("est_mora") });
			}
			tbMoras.setModel(modeloMora);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public String[] arrayTablas(int id_contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		String[] arrayTablas = null;
		try {
			String sql = "SELECT * FROM tb_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_contrato);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return arrayTablas = new String[] { "tb_contrato",
						"tb_detalle_contrato" };
			} else {
				String sql1 = "SELECT * FROM tb_contrato_manual WHERE id_con=?";
				PreparedStatement pst1 = con.prepareStatement(sql1);
				pst1.setInt(1, id_contrato);
				ResultSet rs1 = pst1.executeQuery();
				if (rs1.next()) {
					return arrayTablas = new String[] { "tb_contrato_manual",
							"tb_detalle_contrato_manual" };
				} else {
					String sql2 = "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_contrato);
					ResultSet rs2 = pst2.executeQuery();
					if (rs2.next()) {
						return arrayTablas = new String[] { "tb_contrato_oro",
								"tb_detalle_contrato_oro" };
					} else {
						JOptionPane.showMessageDialog(null,
								"Ese contrato NO existe.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return arrayTablas;
	}

	public void cargarTipoPrestamo(String tipo) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT des_pre FROM tb_prestamo WHERE cod_pre=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tipo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				lblTipo.setText(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cerrar() {
		this.dispose();
	}

	public void anularContrato(int contrato) {
		Connection con = MySQLConexion.getConexion();
		try {
			System.out.println(contrato);
			String[] tabla = arrayTablas(contrato);
			String sql = "UPDATE " + tabla[0]
					+ "SET tb_estado_contrato_id_est=14 WHERE id_con=?";
			String sql2 = "UPDATE tb_egreso SET mon_egr=0 WHERE tip_egr=? AND desc_egr LIKE ?";
			PreparedStatement pst = con.prepareStatement(sql);
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst.setInt(1, contrato);
			pst2.setString(1, "EMP.");
			pst2.setString(2, "%" + contrato + "%");
			con.commit();
			JOptionPane
					.showMessageDialog(null, "Se anuló Contrato " + contrato);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	public void imprimirContrato(int numero) {
		try {
			Connection con = MySQLConexion.getConexion();
			String a[] = arrayTablas(numero);
			String sql = "SELECT * FROM " + a[0] + " WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Contrato c = new Contrato(rs.getString("p"),
						rs.getInt("id_con"), rs.getDouble("int_con"),
						rs.getDouble("cap_con"),
						rs.getString("tb_prestamo_cod_pre"),
						rs.getString("tb_cliente_doc_cli"),
						new SimpleDateFormat("dd-MMM-yyyy").format(rs
								.getDate("fec_con")),
						new SimpleDateFormat("dd-MMM-yyyy").format(rs
								.getDate("fec_ven_con")),
						new SimpleDateFormat("dd-MMM-yyyy").format(rs
								.getDate("fec_rem_con")),
						rs.getString("mon_con"),
						rs.getString("tb_usuario_dni_usu"), 1,
						rs.getString("obs_con"), "");
				for (int i = 0; i < modeloDetalleContrato.getRowCount(); i++) {
					int row = i;
					Contrato.Detalle_Contrato dc = c.new Detalle_Contrato(
							Integer.parseInt(modeloDetalleContrato.getValueAt(
									row, 0).toString()), 1,
							String.valueOf(modeloDetalleContrato.getValueAt(
									row, 1)),
							String.valueOf(modeloDetalleContrato.getValueAt(
									row, 3)),
							String.valueOf(modeloDetalleContrato.getValueAt(
									row, 4)),
							String.valueOf(modeloDetalleContrato.getValueAt(
									row, 2)),
							Double.parseDouble(modeloDetalleContrato
									.getValueAt(row, 5).toString()), "");
					c.agregar_detalle(dc);
				}
				ArrayList<Contrato> arreglo_contrato = new ArrayList<Contrato>();
				arreglo_contrato.add(c);
				String[] arrayDatosCliente = validarCliente(lblIdentidad
						.getText());
				HashMap<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("nombre_cliente",
						(arrayDatosCliente[3] + " " + arrayDatosCliente[1]
								+ " " + arrayDatosCliente[2]).toUpperCase());
				parametros.put("direccion_cliente",
						arrayDatosCliente[4].toUpperCase());
				parametros.put("telefonos_cliente", arrayDatosCliente[5]
						+ " / " + arrayDatosCliente[6]);
				parametros.put("fecha_contrato", lblInicio.getText()
						.toUpperCase());
				parametros.put("fecha_vencimiento", lblVencimiento.getText()
						.toUpperCase());
				parametros.put("en", "".toUpperCase());
				try {

					JasperReport reporte = (JasperReport) JRLoader
							.loadObject("contrato.jasper");
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							reporte, parametros,
							new JRBeanCollectionDataSource(arreglo_contrato));
					JasperPrintManager.printReport(jasperPrint, true);
					JRExporter exporter = new JRPdfExporter();

					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE,
							new java.io.File("reportePDF.pdf"));
					exporter.exportReport();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] validarCliente(String dniCliente) {
		Connection con = MySQLConexion.getConexion();
		String[] datosCliente = null;
		try {
			String sql = "SELECT doc_cli,pat_cli,mat_cli,nom_cli,dir_cli,fi1_cli,mo1_cli FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dniCliente);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String[] array = { rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7) };
				datosCliente = array;
			} else {
				datosCliente = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return datosCliente;
	}

}
