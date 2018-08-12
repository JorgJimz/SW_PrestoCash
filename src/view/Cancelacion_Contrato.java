package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jdesktop.swingx.JXTitledSeparator;

import Utils.EditorIM;
import Utils.Redondeo;
import Utils.RenderIM;
import Beans.Auditoria;
import Beans.Contrato;
import Beans.Ingreso;
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
public class Cancelacion_Contrato extends JInternalFrame {
	DefaultTableModel modeloDetalleContrato = new DefaultTableModel(null,
			new String[] { "CÓDIGO", "DESCRIPCIÓN", "MARCA", "MODELO",
					"OBSERVACIONES", "TASACIÓN" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	String sd = "";
	private JLabel lblCantidadIntereses;
	private JLabel lblTotalAPagar;
	private JLabel jLabel20;
	private JLabel lblMontoDiasExcedidos;
	private JLabel jLabel4;
	private JButton btnCancelarContrato;
	private JLabel lblMoraTotal;
	private JLabel jLabel19;
	private JLabel lblMoraAnterior;
	private JLabel jLabel18;
	private JButton btnSalir;
	private JLabel lblSiNoMora;
	private JLabel lblMoraActual;
	private JLabel jLabel17;
	private JComboBox cboInteresMoratorio;
	private JLabel jLabel15;
	private JLabel lblTotalInteres;
	private JLabel jLabel14;
	private JLabel lblInteresDiario;
	private JLabel lblDiasExcedidos;
	private JLabel jLabel11;
	private JLabel jLabel2;
	private JLabel lblEstado;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel lblInteres;
	private JLabel lblCapital;
	private JLabel jLabel10;
	private JLabel jLabel6;
	private JLabel lblRemate;
	private JLabel jLabel5;
	private JLabel lblVencimiento;
	private JLabel lblInicio;
	private JLabel lblIdContrato;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel lblCliente;
	private JLabel lblIdentidad;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JXTitledSeparator jSeparator2;
	private JXTitledSeparator jSeparator3;
	private JPanel contenedor;
	private String identificador;
	private JTabbedPane tpContrato;
	private JPanel pnlArticulos;
	private JPanel pnlMoras;
	private JPanel pnlPagos;
	private JTable tbHistorial;
	private JTable tbMoras;
	private JTable tbArticulos;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JPanel pnlInteres;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private JTable tbAbonos;
	private JPanel pnlAbonos;
	private JLabel lblMoneda;
	private JTable tbIntereses = new JTable();
	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	DecimalFormat fd;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date inicio;
	Date vcto;
	Date remate;
	DefaultTableModel modeloHistorial = new DefaultTableModel(null,
			new String[] { "FECHA VENCIMIENTO", "FECHA PAGO", "INTERES",
					"MORA", "TOTAL" }) {
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
			return false;
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
	private JSeparator jSeparator50;
	private JLabel lblP;
	private JLabel jLabel21;
	private JTextArea lblEstadoDetalle;
	public int nro_cuotas_global = 0;
	public int nro_dias_global = 0;
	String moras_permitidas[] = new String[] { "50%", "30%"/* , "10%", "0%" */};
	private JLabel jLabel16;
	private JLabel txtMoraAsignada;
	private JTextField cboMora;

	public Cancelacion_Contrato(String numero) {

		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(930, 447);
		identificador = numero;
		simbolo.setDecimalSeparator('.');
		fd = new DecimalFormat("######.00", simbolo);
		a = arrayTablas(Integer.parseInt(identificador));

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1279, 784);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		{
			jSeparator2 = new JXTitledSeparator("DATOS GLOBALES DEL CONTRATO");
			contenedor.add(jSeparator2);
			jSeparator2.setBounds(12, 12, 1225, 32);
			jSeparator2.setFont(new java.awt.Font("Arial Black", 1, 24));
			jSeparator2.setForeground(new java.awt.Color(181, 0, 0));
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CLIENTE:");
			jLabel1.setBounds(512, 117, 110, 30);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("MONEDA:");
			jLabel3.setBounds(321, 117, 104, 30);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblIdentidad = new JLabel();
			contenedor.add(lblIdentidad);
			lblIdentidad.setBounds(609, 116, 147, 32);
			lblIdentidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblIdentidad.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblIdentidad.setBackground(new java.awt.Color(255, 255, 255));
			lblIdentidad.setOpaque(true);
			lblIdentidad.setForeground(new java.awt.Color(0, 64, 128));
		}
		jSeparator3 = new JXTitledSeparator("PAGOS");
		contenedor.add(jSeparator3);
		jSeparator3.setBounds(12, 412, 1224, 32);
		jSeparator3.setFont(new java.awt.Font("Arial Black", 1, 24));
		jSeparator3.setForeground(new java.awt.Color(181, 0, 0));

		{
			lblCliente = new JLabel();
			contenedor.add(lblCliente);
			lblCliente.setBounds(762, 116, 475, 32);
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCliente.setBackground(new java.awt.Color(255, 255, 255));
			lblCliente.setOpaque(true);
			lblCliente.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("CONTRATO");
			jLabel5.setBounds(12, 64, 115, 30);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("INICIO:");
			jLabel7.setBounds(281, 64, 85, 30);
			jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel7.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("VENCE:");
			jLabel8.setBounds(526, 64, 81, 30);
			jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel8.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel9 = new JLabel();
			contenedor.add(jLabel9);
			jLabel9.setText("REMATE:");
			jLabel9.setBounds(769, 62, 103, 34);
			jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel9.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblIdContrato = new JLabel();
			contenedor.add(lblIdContrato);
			lblIdContrato.setBounds(184, 63, 85, 32);
			lblIdContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblIdContrato.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblIdContrato.setBackground(new java.awt.Color(255, 255, 255));
			lblIdContrato.setOpaque(true);
			lblIdContrato.setForeground(new java.awt.Color(0, 64, 128));
			lblIdContrato.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			lblInicio = new JLabel();
			contenedor.add(lblInicio);
			lblInicio.setBounds(358, 63, 150, 32);
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
			lblVencimiento.setBounds(607, 63, 150, 32);
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
			lblRemate.setBounds(878, 63, 150, 32);
			lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblRemate.setBackground(new java.awt.Color(255, 255, 255));
			lblRemate.setOpaque(true);
			lblRemate.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setText("CAPITAL");
			jLabel6.setBounds(11, 593, 102, 30);
			jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel6.setForeground(new java.awt.Color(128, 0, 255));
		}
		{
			jLabel10 = new JLabel();
			contenedor.add(jLabel10);
			jLabel10.setText("% MENSUAL");
			jLabel10.setBounds(13, 453, 188, 30);
			jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel10.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblCapital = new JLabel();
			contenedor.add(lblCapital);
			lblCapital.setBounds(105, 593, 147, 30);
			lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCapital.setBackground(new java.awt.Color(255, 255, 255));
			lblCapital.setOpaque(true);
			lblCapital.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblInteres = new JLabel();
			contenedor.add(lblInteres);
			lblInteres.setBounds(196, 453, 145, 30);
			lblInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblInteres.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblInteres.setBackground(new java.awt.Color(255, 255, 255));
			lblInteres.setOpaque(true);
			lblInteres.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel12 = new JLabel();
			contenedor.add(jLabel12);
			jLabel12.setBounds(144, 536, 10, 10);
		}
		{
			jLabel13 = new JLabel();
			contenedor.add(jLabel13);
			jLabel13.setBounds(144, 494, 10, 10);
		}
		{
			lblEstado = new JLabel();
			contenedor.add(lblEstado);
			lblEstado.setBounds(12, 107, 290, 50);
			lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 36));
			lblEstado.setOpaque(true);
			lblEstado.setBackground(Color.WHITE);
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("DÍAS EXCEDIDOS");
			jLabel2.setBounds(13, 489, 177, 32);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel11 = new JLabel();
			contenedor.add(jLabel11);
			jLabel11.setText("% X DIA");
			jLabel11.setBounds(13, 533, 118, 32);
			jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel11.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblDiasExcedidos = new JLabel();
			contenedor.add(lblDiasExcedidos);
			lblDiasExcedidos.setBounds(196, 489, 143, 32);
			lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblDiasExcedidos.setBackground(new java.awt.Color(255, 255, 255));
			lblDiasExcedidos.setOpaque(true);
			lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblInteresDiario = new JLabel();
			contenedor.add(lblInteresDiario);
			lblInteresDiario.setBounds(196, 527, 143, 32);
			lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
			lblInteresDiario.setOpaque(true);
			lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel14 = new JLabel();
			contenedor.add(jLabel14);
			jLabel14.setText("INTERES TOTAL");
			jLabel14.setBounds(270, 593, 166, 30);
			jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel14.setForeground(new java.awt.Color(128, 0, 255));
		}
		{
			lblTotalInteres = new JLabel();
			contenedor.add(lblTotalInteres);
			lblTotalInteres.setBounds(436, 593, 147, 30);
			lblTotalInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblTotalInteres.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblTotalInteres.setBackground(new java.awt.Color(255, 255, 255));
			lblTotalInteres.setOpaque(true);
			lblTotalInteres.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel15 = new JLabel();
			contenedor.add(jLabel15);
			jLabel15.setText("¿MORA?");
			jLabel15.setBounds(376, 452, 123, 32);
			jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel15.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			cboMora = new JTextField("0%");
			contenedor.add(cboMora);
			cboMora.setBounds(376, 496, 261, 65);
			cboMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			cboMora.setBackground(new java.awt.Color(128, 0, 128));
			cboMora.setFont(new java.awt.Font("Segoe UI", 1, 65));
			cboMora.setForeground(new java.awt.Color(255, 255, 128));
			cboMora.setHorizontalAlignment(SwingConstants.CENTER);
			cboMora.setEnabled(false);
			cboMora.setEditable(false);
			cboMora.setEnabled(false);
			cboMora.setEnabled(false);
		}
		{
			jLabel17 = new JLabel();
			contenedor.add(jLabel17);
			jLabel17.setText("M.ACTUAL");
			jLabel17.setBounds(653, 489, 117, 32);
			jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel17.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblMoraActual = new JLabel();
			contenedor.add(lblMoraActual);
			lblMoraActual.setBounds(799, 489, 106, 32);
			lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblMoraActual.setBackground(new java.awt.Color(255, 255, 255));
			lblMoraActual.setOpaque(true);
			lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));
			lblMoraActual.setText("0.00");
		}
		{
			lblSiNoMora = new JLabel("NO");
			contenedor.add(lblSiNoMora);
			lblSiNoMora.setBounds(495, 450, 143, 32);
			lblSiNoMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblSiNoMora.setFont(new java.awt.Font("Segoe UI", 1, 26));
			lblSiNoMora.setBackground(new java.awt.Color(255, 255, 255));
			lblSiNoMora.setForeground(new java.awt.Color(0, 128, 0));
			lblSiNoMora.setOpaque(true);
		}
		{
			btnSalir = new JButton();
			contenedor.add(btnSalir);
			btnSalir.setText("SALIR");
			btnSalir.setBounds(1141, 534, 95, 89);
			btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 22));
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnSalir.setBackground(new java.awt.Color(128, 255, 255));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();

				}
			});
		}
		{
			jLabel18 = new JLabel();
			contenedor.add(jLabel18);
			jLabel18.setText("M.ANTERIOR");
			jLabel18.setBounds(652, 527, 135, 32);
			jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel18.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblMoraAnterior = new JLabel();
			contenedor.add(lblMoraAnterior);
			lblMoraAnterior.setBounds(799, 527, 106, 32);
			lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblMoraAnterior.setBackground(new java.awt.Color(255, 255, 255));
			lblMoraAnterior.setOpaque(true);
			lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));
			lblMoraAnterior.setText("0.00");
		}
		{
			jLabel19 = new JLabel();
			contenedor.add(jLabel19);
			jLabel19.setText("MORA TOTAL");
			jLabel19.setBounds(605, 593, 147, 30);
			jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel19.setForeground(new java.awt.Color(128, 0, 255));
		}
		{
			lblMoraTotal = new JLabel();
			contenedor.add(lblMoraTotal);
			lblMoraTotal.setBounds(758, 593, 147, 30);
			lblMoraTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblMoraTotal.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblMoraTotal.setBackground(new java.awt.Color(255, 255, 255));
			lblMoraTotal.setOpaque(true);
			lblMoraTotal.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			tpContrato = new JTabbedPane();
			contenedor.add(tpContrato);
			tpContrato.setBounds(13, 178, 1221, 222);
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
			jScrollPane4.setPreferredSize(new java.awt.Dimension(1182, 150));
			jScrollPane4.setViewportView(tbIntereses);
			tbIntereses.setModel(modeloInteres);
			jScrollPane5.setPreferredSize(new java.awt.Dimension(1189, 150));
			jScrollPane1 = new JScrollPane();
			pnlArticulos.add(jScrollPane1);
			jScrollPane1.setPreferredSize(new java.awt.Dimension(1187, 150));
			tbArticulos = new JTable();
			jScrollPane1.setViewportView(tbArticulos);
			tbArticulos.setModel(modeloDetalleContrato);
			tbArticulos.setRowHeight(30);
			tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 18));
			tbArticulos.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
			tpContrato.addTab("PAGOS", null, pnlPagos, null);
			jScrollPane2 = new JScrollPane();
			pnlPagos.add(jScrollPane2);
			jScrollPane2.setPreferredSize(new java.awt.Dimension(1182, 150));
			tbHistorial = new JTable();
			jScrollPane2.setViewportView(tbHistorial);
			tbHistorial.setModel(modeloHistorial);
			tbHistorial.setRowHeight(30);
			tbHistorial.setFont(new Font("Segoe UI", Font.BOLD, 18));
			tbHistorial.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbHistorial.getTableHeader().setForeground(new Color(181, 0, 0));
			tpContrato.addTab("MORAS", null, pnlMoras, null);
			jScrollPane3 = new JScrollPane();
			pnlMoras.add(jScrollPane3);
			jScrollPane3.setPreferredSize(new java.awt.Dimension(1181, 150));
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
			tbIntereses.getTableHeader().setForeground(new Color(181, 0, 0));
			modeloInteres.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent tme) {
					if (tme.getType() == TableModelEvent.UPDATE) {
						double total = 0;
						for (int i = 0; i <= modeloInteres.getRowCount() - 1; i++) {
							int estado = Integer.parseInt(modeloInteres
									.getValueAt(i, 2).toString());
							if (estado == 1) {
								double monto = Double.parseDouble(modeloInteres
										.getValueAt(i, 1).toString());
								total += monto;
							}
						}
						lblTotalInteres.setText(total + "");
						calcularTotalIntereses();

					}
				}
			});
			modeloMora.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent tm) {
					if (tm.getType() == TableModelEvent.UPDATE) {
						double total = 0;
						for (int i = 0; i <= modeloMora.getRowCount() - 1; i++) {
							int estado = Integer.parseInt(modeloMora
									.getValueAt(i, 2).toString());
							if (estado == 1) {
								double monto = Double.parseDouble(modeloMora
										.getValueAt(i, 1).toString());
								total += monto;
							}
						}
						lblMoraTotal.setText(total + "");
						calcularTotalIntereses();

					}
				}
			});
		}
		{
			btnCancelarContrato = new JButton();
			contenedor.add(btnCancelarContrato);
			btnCancelarContrato.setText("CANCELAR");
			btnCancelarContrato.setBounds(923, 534, 212, 89);
			btnCancelarContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
			btnCancelarContrato.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			btnCancelarContrato
					.setBackground(new java.awt.Color(128, 255, 255));
			btnCancelarContrato.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int opcion = JOptionPane.showConfirmDialog(null,
							"Seguro de dar por cancelado el Contrato?",
							"Alerta", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						if (lblMoneda.getText().equalsIgnoreCase("DOLARES")) {
							JOptionPane
									.showMessageDialog(
											null,
											"<html><h2>Tipo de moneda actual: DOLARES - Se procederá a la conversión del tipo de moneda a SOLES (RECUERDE: Registrar egreso por el mismo monto)</h2></html>");
							grabarPagoCancelacionContratoDOLARES();
							cancelarContratoDOLARES(a[0]);
							retirarMora(
									Integer.parseInt(lblIdContrato.getText()),
									a[0]);
						} else {
							grabarPagoCancelacionContrato();
							cancelarContrato(a[0]);
							retirarMora(
									Integer.parseInt(lblIdContrato.getText()),
									a[0]);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Operación Cancelada");
					}

				}
			});
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("EXCESO S/.");
			jLabel4.setBounds(653, 452, 134, 32);
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblMontoDiasExcedidos = new JLabel();
			contenedor.add(lblMontoDiasExcedidos);
			lblMontoDiasExcedidos.setBounds(799, 452, 106, 32);
			lblMontoDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblMontoDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1,
					1, 1, 1, new java.awt.Color(0, 0, 0)));
			lblMontoDiasExcedidos.setBackground(new java.awt.Color(255, 255,
					255));
			lblMontoDiasExcedidos.setOpaque(true);
			lblMontoDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel20 = new JLabel();
			contenedor.add(jLabel20);
			jLabel20.setBounds(923, 443, 313, 85);
			jLabel20.setBorder(BorderFactory
					.createTitledBorder("TOTAL A PAGAR"));
		}
		{
			lblTotalAPagar = new JLabel();
			contenedor.add(lblTotalAPagar);
			lblTotalAPagar.setBounds(935, 459, 292, 57);
			lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 65));
			lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		}
		{
			lblCantidadIntereses = new JLabel("1");
			contenedor.add(lblCantidadIntereses);
			lblCantidadIntereses.setBounds(16, 626, 10, 10);
			lblCantidadIntereses.setVisible(false);
		}

		lblMoneda = new JLabel();
		contenedor.add(lblMoneda);
		lblMoneda.setBounds(36, 528, 10, 10);
		lblMoneda.setVisible(false);

		lblTipoMoneda = new JLabel();
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(437, 107, 58, 50);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		this.setSize(1365, 639);
		this.setPreferredSize(new java.awt.Dimension(1280, 808));
		this.setBounds(0, 0, 1280, 808);

		jSeparator50 = new JSeparator();
		contenedor.add(jSeparator50);
		jSeparator50.setBounds(12, 577, 894, 10);

		lblP = new JLabel();
		contenedor.add(lblP);
		lblP.setBounds(129, 63, 38, 32);
		lblP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblP.setFont(new java.awt.Font("Segoe UI", 1, 22));
		lblP.setOpaque(true);
		lblP.setBackground(new java.awt.Color(255, 255, 255));
		lblP.setForeground(new java.awt.Color(0, 64, 128));
		lblP.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel21 = new JLabel();
		contenedor.add(jLabel21);
		jLabel21.setText("-");
		jLabel21.setBounds(167, 58, 25, 31);
		jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 36));

		lblEstadoDetalle = new JTextArea();
		contenedor.add(lblEstadoDetalle);
		lblEstadoDetalle.setEditable(false);
		lblEstadoDetalle.setBounds(12, 635, 1224, 122);
		lblEstadoDetalle.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblEstadoDetalle.setForeground(new java.awt.Color(255, 255, 255));
		lblEstadoDetalle.setBackground(new java.awt.Color(0, 64, 128));
		lblEstadoDetalle.setOpaque(true);
		lblEstadoDetalle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		txtMoraAsignada = new JLabel();
		contenedor.add(txtMoraAsignada);
		txtMoraAsignada.setBounds(1155, 63, 82, 32);
		txtMoraAsignada.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMoraAsignada.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtMoraAsignada.setBackground(new java.awt.Color(255, 255, 255));
		txtMoraAsignada.setOpaque(true);
		txtMoraAsignada.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("MORA(%)");
		jLabel16.setBounds(1046, 62, 103, 34);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		cargarContrato(identificador, a[0]);

	}

	public void cargarHistoriaContrato() {
		Connection con = MySQLConexion.getConexion();

		StringBuffer cadena = new StringBuffer();
		try {
			String sql = "SELECT CONCAT(des_art,space(1),mar_art) as 'a',fec_sep, SUM(cta_sep) as 'c', pre_sep FROM tb_separacion s INNER JOIN tb_articulo a ON s.tb_articulo_cod_art = a.cod_art WHERE a.id_con_asoc=? GROUP BY tb_articulo_cod_art";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(identificador));
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String a = rs.getString("a");
				String d = new SimpleDateFormat("dd-MMM-yyyy").format(rs
						.getDate("fec_sep"));
				cadena.append("SEPARADO: " + a + " EL DÍA " + d
						+ " MONTO TOTAL S/." + rs.getString("c") + " DE S/."
						+ rs.getString("pre_sep"));
				cadena.append(System.getProperty("line.separator"));
			}

			String sql1 = "SELECT CONCAT(des_art,space(1),mar_art) as 'a',fec_ven,val_ven,id_con_asoc FROM tb_venta v INNER JOIN tb_articulo a	ON a.cod_art = v.tb_articulo_cod_art WHERE a.id_con_asoc=?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, Integer.parseInt(identificador));
			ResultSet rs1 = pst1.executeQuery();
			while (rs1.next()) {
				String d1 = new SimpleDateFormat("dd-MMM-yyyy").format(rs1
						.getDate("fec_ven"));
				cadena.append("REMATADO: " + rs1.getString("a") + "EL DÍA" + d1
						+ " PRECIO VENTA: S/." + rs1.getDouble("val_ven"));
				cadena.append(System.getProperty("line.separator"));
			}

			String sql2 = "SELECT MAX(fec_car) as 'f',des_car FROM tb_cargo c INNER JOIN tb_detalle_cargo dc ON c.id_car=dc.id_car WHERE nro_con=? GROUP BY c.id_car";
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, Integer.parseInt(identificador));
			ResultSet rs2 = pst2.executeQuery();
			while (rs2.next()) {
				System.out.println("CARGO: " + rs2.getString("f")
						+ ".::. ALMACEN ACTUAL: " + rs2.getString(2));
				cadena.append(System.getProperty("line.separator"));
			}

			System.out.println(cadena);
			lblEstadoDetalle.setText("");
			lblEstadoDetalle.setText(String.valueOf(cadena));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void correspondeMora(int diasExcedidos) {
		System.out.println("usando nuevo algoritmo de cálculo de moras.");
		if (nro_cuotas_global == 1 && nro_dias_global > 5) {
			lblSiNoMora.setText("SI");
			lblSiNoMora.setForeground(Color.RED);
			electivoMora(1, 1);
		} else if (nro_cuotas_global == 2
				&& Integer.parseInt(lblDiasExcedidos.getText()) == 0) {
			lblSiNoMora.setText("SI");
			lblSiNoMora.setForeground(Color.RED);
			electivoMora2(1, 1);
		} else if (nro_cuotas_global == 2
				&& Integer.parseInt(lblDiasExcedidos.getText()) > 0) {
			lblSiNoMora.setText("SI");
			lblSiNoMora.setForeground(Color.RED);
			electivoMora(0, 2);
		} else if (nro_cuotas_global >= 2) {
			lblSiNoMora.setText("SI");
			lblSiNoMora.setForeground(Color.RED);
			electivoMora(0, nro_cuotas_global);
		} else {
			lblSiNoMora.setText("NO");
			lblSiNoMora.setForeground(new java.awt.Color(0, 128, 0));
		}

	}

	public void electivoMora(int n, int m) {
		try {
			double totalAPagar = Double.parseDouble(lblTotalInteres.getText());
			double mora = 0.00;
			if (txtMoraAsignada.getText().equalsIgnoreCase("REG")) {
				cboMora.setText(moras_permitidas[n]);
				String[] opcion = cboMora.getText().split("%");
				double percent = Double.parseDouble(opcion[0]) / 100;
				mora = totalAPagar * percent;
			} else if (txtMoraAsignada.getText().equalsIgnoreCase("150")) {
				System.out.println("150");

				if (!sd.equalsIgnoreCase("SOLES")) {
					System.out.println("DOLARES");
					cboMora.setText("$ 50.00");

					mora = 50 * m;

				} else {
					System.out.println("SOLES");
					cboMora.setText("S/.150");

					mora = 150 * m;

				}

			} else {
				cboMora.setText(txtMoraAsignada.getText().concat("%"));
				String[] opcion = cboMora.getText().split("%");
				double percent = Double.parseDouble(opcion[0]) / 100;
				mora = totalAPagar * percent;
			}
			lblMoraActual.setText(Redondeo.redondearCentimos(fd.format(mora)));
			calcularTotalMora();
			calcularTotalCancelacion();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void electivoMora2(int n, int m) {
		try {
			double totalAPagar = Double.parseDouble(lblTotalInteres.getText());
			double mora = 0.00;
			if (txtMoraAsignada.getText().equalsIgnoreCase("REG")) {
				cboMora.setText(moras_permitidas[n]);
				String[] opcion = cboMora.getText().split("%");
				double percent = Double.parseDouble(opcion[0]) / 100;
				mora = totalAPagar * percent;
			} else if (txtMoraAsignada.getText().equalsIgnoreCase("150")) {
				System.out.println("150");

				if (!sd.equalsIgnoreCase("SOLES")) {
					System.out.println("DOLARES");
					cboMora.setText("$ 50.00");

					mora = 50.00 * m;

				} else {
					System.out.println("SOLES");
					cboMora.setText("S/.150");

					mora = 150 * m;

				}
			} else {
				cboMora.setText(txtMoraAsignada.getText().concat("%"));
				String[] opcion = cboMora.getText().split("%");
				double percent = Double.parseDouble(opcion[0]) / 100;
				mora = totalAPagar * percent;
			}
			System.out.println(totalAPagar);
			System.out.println(mora);
			lblMoraActual.setText(Redondeo.redondearCentimos(fd.format(mora)));
			calcularTotalMora();
			calcularTotalCancelacion();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarContrato(String numero, String tabla) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM " + tabla + " WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(numero));
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cargarHistoriaContrato();
				cargarDatosCliente(rs.getString(2));
				cargarDetalleContrato(rs.getInt(1), a[0], a[1]);
				cargarHistorialPagos(rs.getInt(1));
				cargarHistorialMoras(rs.getInt(1));
				cargarHistorialAbonos(rs.getInt(1));
				determinarAccion(lblEstado.getText().toUpperCase());
				calcularTotalCancelacion();
				sd = rs.getString("mon_con");
				String tipo = (rs.getString("mon_con")
						.equalsIgnoreCase("SOLES")) ? "S/." : "$";
				lblTipoMoneda.setText(tipo);
			} else {
				JOptionPane.showMessageDialog(null,
						"No existe tal número de Contrato");
				this.dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No existe tal número de Contrato");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

	public void cargarDatosCliente(String documento) {
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void cargarDetalleContrato(int codigo, String tabla, String tabla2) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM "
					+ tabla
					+ " c INNER JOIN "
					+ tabla2
					+ " dc ON c.id_con= dc."
					+ tabla
					+ "_id_con INNER JOIN tb_articulo a ON dc.tb_articulo_cod_art=a.cod_art INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est INNER JOIN tb_prestamo p ON c.tb_prestamo_cod_pre=p.cod_pre WHERE "
					+ tabla + "_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			modeloDetalleContrato.setRowCount(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				lblMoneda.setText(rs.getString("mon_con"));
				inicio = sdf.parse(rs.getString("fec_con"));
				vcto = sdf.parse(rs.getString("fec_ven_con"));
				remate = sdf.parse(rs.getString("fec_rem_con"));
				modeloDetalleContrato.addRow(new Object[] {
						rs.getInt("cod_art"), rs.getString("des_art"),
						rs.getString("mar_art"), rs.getString("mod_art"),
						rs.getString("obs_art"), rs.getDouble("tas_det_con") });
				lblIdContrato.setText(rs.getInt("id_con") + "".trim());
				lblInicio.setText(new SimpleDateFormat("dd-MMM-yyyy").format(
						inicio).toUpperCase());
				lblVencimiento.setText(new SimpleDateFormat("dd-MMM-yyyy")
						.format(vcto).toUpperCase());
				lblRemate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(
						remate).toUpperCase());
				lblCapital.setText(rs.getDouble("cap_con") + "");
				lblInteres.setText(rs.getDouble("int_con") + "");
				lblEstado.setText(rs.getString("des_est"));
				lblTotalInteres.setText(rs.getDouble("int_con") + "");
				lblInteresDiario.setText(fd.format(Double
						.parseDouble(lblInteres.getText()) / 30));
				lblP.setText(rs.getString("p"));
				txtMoraAsignada.setText((rs.getInt("mor_pre") == 0) ? "REG"
						: String.valueOf(rs.getInt("mor_pre")));
				// desactivarBotones();
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
	}

	public void desactivarBotones() {
		if (lblEstado.getText().equalsIgnoreCase("VITRINA (SP)")) {
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(false);
		}
	}

	public void cargarHistorialPagos(int numero) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				modeloHistorial.addRow(new Object[] {
						new SimpleDateFormat("dd-MMM-yyyy").format(rs
								.getDate("ven_con")),
						new SimpleDateFormat("dd-MMM-yyyy").format(rs
								.getDate("fec_pag")),
						fd.format(rs.getDouble("mon_int_pag")),
						fd.format(rs.getDouble("mon_mor_pag")),
						fd.format(rs.getDouble("mon_int_pag")
								+ rs.getDouble("mon_mor_pag")) });
			}
			tbHistorial.setModel(modeloHistorial);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void calcularDiasExcedidos(Date fecha_vcto) {
		Calendar vcto = Calendar.getInstance();
		vcto.setTime(fecha_vcto);
		vcto.add(Calendar.MONTH, -1);
		Calendar hoy = Calendar.getInstance();
		long vctoM = vcto.getTimeInMillis();
		long hoyM = hoy.getTimeInMillis();
		long resta = hoyM - vctoM;
		Number diasExcedidos = (resta < 0) ? 0 : resta / (24 * 60 * 60 * 1000);
		lblDiasExcedidos.setText("");
		lblDiasExcedidos.setText(((diasExcedidos.intValue()/* - 1 */< 0) ? 0
				: diasExcedidos.intValue()/* - 1 */) + "");
	}

	public void determinarAccion(String estado) {
		if (estado.equalsIgnoreCase("ACTIVO")) {
			activo();
		} else if (estado.equalsIgnoreCase("VENCIDO")) {
			vencido();
		} else if (estado.equalsIgnoreCase("PRE")) {
			pre();
		} else if (estado.equalsIgnoreCase("POST")) {
			post();
		} else if (estado.equalsIgnoreCase("VITRINA")) {
			vitrina();
		} else if (estado.equalsIgnoreCase("SEPARADO")) {
			separado();
		} else if (estado.equalsIgnoreCase("REMATADO")) {
			rematado();
		} else if (estado.equalsIgnoreCase("USO OFICINA")) {
			uso_oficina();
		} else if (estado.equalsIgnoreCase("CANCELADO")) {
			cancelado();
		} else {
			remate();
		}
	}

	public void activo() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 128, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularDiasExcedidos(vcto);
				calcularMontoDiasExcedidos();
				calcularTotalInteresesII();
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				calcularTotalMora();
				System.out.println("con pagos");
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				calcularTotalMora();
				System.out.println("sin pagos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vencido() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(240, 80, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remate() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(200, 0, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pre() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(255, 0, 128));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void post() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(128, 0, 255));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vitrina() {
		try {
			lblEstado.setForeground(Color.BLACK);
			lblEstado.setBackground(new Color(255, 255, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void separado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 128, 192));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(true);
			cargarDetalleEstado(lblIdContrato.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rematado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(255, 0, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 0, 160));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(true);
			cargarDetalleEstado(lblIdContrato.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uso_oficina() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 0, 0));
			if (verificarPagosAnteriores(Integer.parseInt(identificador))) {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			} else {
				calcularCuotas();
				calcularMontoDiasExcedidos();
				calcularTotalIntereses();
				correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
				calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
				/* activarMorosidad(); */
				calcularTotalMora();
				calcularTotalCancelacion();
			}
			btnCancelarContrato.setEnabled(false);
			// btnImprimir.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void calcularCuotas() {
		try {
			double total = 0;
			Calendar h = Calendar.getInstance();
			Calendar v = Calendar.getInstance();
			v.setTime(vcto);
			int days = v.getActualMaximum(Calendar.DAY_OF_MONTH);
			long vencimiento = v.getTimeInMillis();
			long hoy = h.getTimeInMillis();
			long resta = hoy - vencimiento;
			double diasExcedidos = (resta < 0) ? 0 : resta
					/ (24 * 60 * 60 * 1000);
			Number nro_cuotas = diasExcedidos / days;
			Number dias_residuo = diasExcedidos % days;
			for (int i = 0; i <= nro_cuotas.intValue(); i++) {
				modeloInteres.addRow(new Object[] {
						new SimpleDateFormat("MMMM").format(v.getTime())
								.toUpperCase(), lblInteres.getText(), 1 });
				total += Double.parseDouble(lblInteres.getText());
				v.add(Calendar.MONTH, 1);
				nro_cuotas_global++;
			}
			nro_dias_global = dias_residuo.intValue();
			tbIntereses.setModel(modeloInteres);
			lblTotalInteres.setText(total + "");
			lblDiasExcedidos.setText(dias_residuo.intValue() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void calcularMontoDiasExcedidos() {
		int diasExcedidos = Integer.parseInt(lblDiasExcedidos.getText());
		System.out.println(diasExcedidos);
		double interesDiario = Double.parseDouble(lblInteresDiario.getText());
		System.err.println(interesDiario);
		lblMontoDiasExcedidos.setText(fd.format(interesDiario * diasExcedidos));
		System.out.println(fd.format(interesDiario * diasExcedidos));
	}

	public void calcularInteresDiario(double interesMensual) {
		double interesDiario = interesMensual / 30;
		lblInteresDiario.setText(fd.format(interesDiario));
	}

	public void calcularTotalIntereses() {
		double interesFijo = Double.parseDouble(lblTotalInteres.getText());
		double montoDiasExcedidos = Double.parseDouble(lblMontoDiasExcedidos
				.getText());
		lblTotalInteres.setText(fd.format(interesFijo + montoDiasExcedidos));
	}

	public void calcularTotalInteresesII() {
		double montoDiasExcedidos = Double.parseDouble(lblMontoDiasExcedidos
				.getText());
		lblTotalInteres.setText(fd.format(montoDiasExcedidos));
	}

	public void calcularTotalCancelacion() {
		double interes = Double.parseDouble(lblTotalInteres.getText());
		double mora = Double.parseDouble(lblMoraTotal.getText());
		double capital = Double.parseDouble(lblCapital.getText());
		lblTotalAPagar.setText(Redondeo.redondearCentimos(fd.format(capital
				+ interes + mora)));
	}

	public double calcularMoraAnterior(int contrato) {
		Connection con = MySQLConexion.getConexion();
		double c = 0.00;
		try {

			String sql = "SELECT * FROM tb_mora WHERE id_con=? AND est_mora=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				c += rs.getDouble("val_mora");
			}
			lblMoraAnterior.setText(c + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	public void calcularTotalMora() {
		try {
			double actual = Double.parseDouble(lblMoraActual.getText());
			double anterior = Double.parseDouble(lblMoraAnterior.getText());
			lblMoraTotal.setText(fd.format(actual + anterior));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public double obtenerCambio() {
		Connection con = MySQLConexion.getConexion();
		double monto = 0.00;
		try {
			String sql = "SELECT ven_cam FROM prestocash.tb_cambio where fec_cam=date_format(now(),'%y-%m-%d')";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				monto = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}

	public void grabarPagoCancelacionContrato() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "INSERT INTO tb_pago_contrato VALUES(null,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
			pst.setString(2, new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd-MMM-yyyy")
							.parse(lblVencimiento.getText())));
			pst.setString(3,
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			pst.setDouble(4, Double.parseDouble(lblCapital.getText()));
			pst.setDouble(5, Double.parseDouble(lblTotalInteres.getText()));
			pst.setDouble(6, Double.parseDouble(lblMoraTotal.getText()));
			pst.executeUpdate();
			btnCancelarContrato.setEnabled(false);
			if (lblIdContrato.getText().equalsIgnoreCase("1407")) {
				btnCancelarContrato.setEnabled(true);
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
	}

	public void grabarPagoCancelacionContratoDOLARES() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "INSERT INTO tb_pago_contrato VALUES(null,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
			pst.setString(2, new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd-MMM-yyyy")
							.parse(lblVencimiento.getText())));
			pst.setString(3,
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			pst.setDouble(4, Double.parseDouble(lblCapital.getText())
					* obtenerCambio());
			pst.setDouble(5, Double.parseDouble(lblTotalInteres.getText())
					* obtenerCambio());
			pst.setDouble(6, Double.parseDouble(lblMoraTotal.getText())
					* obtenerCambio());
			pst.executeUpdate();
			btnCancelarContrato.setEnabled(false);
			if (lblIdContrato.getText().equalsIgnoreCase("1407")) {
				btnCancelarContrato.setEnabled(true);
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
	}

	public void cancelarContrato(String tabla) {
		Connection con = MySQLConexion.getConexion();
		try {
			String total = Redondeo.redondearCentimos(fd.format(Double
					.parseDouble(lblTotalInteres.getText())
					+ Double.parseDouble(lblMoraTotal.getText())));
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=6 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
			pst.executeUpdate();
			Ingreso ingresoCancelacion = new Ingreso(Principal.id_libro_caja,
					lblP.getText() + "-" + lblIdContrato.getText(), "PAG",
					Double.parseDouble(lblCapital.getText()),
					Double.parseDouble(total), 0.00);
			ingresoCancelacion.registrarIngreso();
			btnCancelarContrato.setEnabled(false);
			if (lblIdContrato.getText().equalsIgnoreCase("1407")) {
				btnCancelarContrato.setEnabled(true);
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

	}

	public void cancelarContratoDOLARES(String tabla) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=6 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
			pst.executeUpdate();
			Ingreso ingresoCancelacion = new Ingreso(Principal.id_libro_caja,
					lblP.getText() + "-" + lblIdContrato.getText(), "PAG $",
					Double.parseDouble(lblCapital.getText()) * obtenerCambio(),
					Double.parseDouble(Redondeo
							.redondearCentimos(lblTotalInteres.getText()))
							* obtenerCambio()
							+ Double.parseDouble(Redondeo
									.redondearCentimos(lblMoraTotal.getText()))
							* obtenerCambio(), 0.00);
			ingresoCancelacion.registrarIngreso();
			btnCancelarContrato.setEnabled(false);
			if (lblIdContrato.getText().equalsIgnoreCase("1407")) {
				btnCancelarContrato.setEnabled(true);
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

	}

	public void cargarDetalleEstado(String numero) {
		String estado = lblEstado.getText();
		Connection con = MySQLConexion.getConexion();
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
					lblEstadoDetalle.setText("CANCELADO EL "
							+ new SimpleDateFormat("dd-MMM-yyyy").format(
									rs.getDate("fec_caja")).toUpperCase()
							+ " CON S/."
							+ Redondeo.redondearCentimos(String.valueOf(fd
									.format(sum))));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (estado.equalsIgnoreCase("SEPARADO")) {
			try {
				String sql = "select distinct s.tb_articulo_cod_art, concat(des_art,space(1),mar_art) as 'articulo', max(fec_sep) as 'fecha', sum(s.cta_sep) as 'acum',s.cap_sep from tb_separacion s inner join tb_articulo a on s.tb_articulo_cod_art=a.cod_art where s.id_con_asoc=? group by articulo";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					lblEstadoDetalle.setText("SEPARADO EL "
							+ rs.getString("fecha")
							+ " - "
							+ rs.getString("articulo")
							+ " - VA S/."
							+ Redondeo.redondearCentimos(String.valueOf(fd
									.format(rs.getDouble("acum")))) + " DE S/."
							+ rs.getDouble("cap_sep"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				String sql = "SELECT * FROM prestocash.tb_cargo c INNER JOIN tb_detalle_cargo dc ON c.id_car = dc.id_car WHERE nro_con=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					lblEstadoDetalle.setText("CON CARGO EL "
							+ new SimpleDateFormat("dd-MMM-yyyy").format(
									rs.getDate("fec_car")).toUpperCase()
							+ " OBS:" + rs.getString("obs_car") + " DESTINO: "
							+ rs.getString("des_car"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

	public void retirarMora(int contrato, String tabla) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_mora SET est_mora=0 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean verificarPagosAnteriores(int nro_contrato)
			throws SQLException {
		Connection con = MySQLConexion.getConexion();
		boolean tienePagos = false;
		try {
			String sql = "SELECT * FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nro_contrato);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tienePagos = true;
			} else {
				tienePagos = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return tienePagos;
	}

	public void actualizarAlmacen(int contrato, String tabla, String tabla2) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE " + tabla2
					+ " SET tb_estado_articulo_cod_est=5 WHERE " + tabla
					+ "_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Detalle Actualizado.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeInternal() {
		this.dispose();
	}

	public String[] arrayTablas(int id_contrato) {
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayTablas;
	}

	public void imprimirVoucherPago() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("contrato", lblIdContrato.getText());
		parametros.put("cliente", lblCliente.getText());
		parametros.put("monto", lblTotalAPagar.getText());
		parametros.put("articulos", detalleArticulos());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("voucher_cancelacion.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros, MySQLConexion.getConexion());
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			// Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
			// + " en Contrato_Prestacion - imprimirVoucher()",
			// " Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
			// new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
			// .format(new Date()));
			// a.registrarAuditoria();
			e.printStackTrace();
		}
	}

	public String detalleArticulos() {
		StringBuilder articulos = new StringBuilder();
		for (int i = 0; i <= modeloDetalleContrato.getRowCount() - 1; i++) {
			articulos.append(modeloDetalleContrato.getValueAt(i, 1).toString()
					+ " " + modeloDetalleContrato.getValueAt(i, 2).toString()
					+ "+");
		}
		return articulos.toString();
	}

}
