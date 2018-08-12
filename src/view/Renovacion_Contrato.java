package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

import model.Contrato;
import model.DetalleContrato;
import model.Pago;
import common.Constantes;
import common.EditorIM;
import common.Redondeo;
import common.RenderIM;
import controller.ContratoController;

@SuppressWarnings({"deprecation", "serial"})
public class Renovacion_Contrato extends JInternalFrame {
	private JLabel jLabel1;
	private JLabel lblEstado;
	private JLabel jLabel4;
	private JTabbedPane tpContrato;
	private JTable tbArticulos;
	private JLabel lblTotalMora;
	private JLabel lblTotalInteres;
	private JLabel jLabel15;
	private JLabel jLabel14;
	private JLabel lblMoraActual;
	private JLabel lblMoraAnterior;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel lblDiasExcedidos;
	private JLabel jLabel11;
	private JLabel lblMoraSiNo;
	private JTextField cboMora;
	private JLabel jLabel10;
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
	private JTextArea lblEstadoDetalle;
	private JLabel jLabel20;
	private JLabel lblP;
	private JSeparator jSeparator2;
	private JLabel lblTipoMoneda;
	private JLabel jLabel5;
	private JLabel jLabel17;
	private JTable tbAbonos;
	private JScrollPane jScrollPane5;
	private JTable tbIntereses = new JTable();
	private JScrollPane jScrollPane4;
	private JLabel lblTotalAPagar;
	private JSeparator jSeparator1;
	private JTable tbMoras;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JButton btnInteresConMora;
	private JButton btnSoloInteres;
	private JLabel lblInteresDiario;
	private JLabel jLabel16;
	private JTable tbHistorial;
	private JLabel lblCliente;
	private JLabel lblIdentidad;
	private DecimalFormat fd;
	
	private DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	private Date proximo_vencimiento;
	String sd = "";
	
	Contrato contrato;
		
	DefaultTableModel MoraModel = new DefaultTableModel(null, new String[] {
			"ID", "MONTO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbMoras.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel InteresModel = new DefaultTableModel(null, new String[] {
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
	private JPanel pnlInteres;
	private JPanel pnlAbonos;
	public int nro_cuotas_global = 0;
	public int nro_dias_global = 0;
	String moras_permitidas[] = new String[] { "50%", "30%"/* , "10%", "0%" */};
	private JTextField txtTipoPrestamo;
	private JLabel txtMoraAsignada;
	private JLabel jLabel19;

	public Renovacion_Contrato(String c) throws ParseException {
		String flag = String.valueOf(c.split("-")[0]);
		int numero = Integer.parseInt(String.valueOf(c.split("-")[1]));
		contrato = new ContratoController().CargarContrato(flag, numero);
		
		fd = new DecimalFormat("#####0.00", simbolo);

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(897, 426);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1265, 765));
		this.setBounds(0, 0, 1265, 765);
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1264, 741);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		simbolo.setDecimalSeparator('.');
		

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("NRO°");
		jLabel1.setBounds(12, 13, 64, 32);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		lblEstado = new JLabel(String.valueOf(contrato.getStatus()));
		contenedor.add(lblEstado);
		lblEstado.setBounds(12, 58, 290, 50);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblEstado.setOpaque(true);
		lblEstado.setBackground(Color.WHITE);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

		lblNumeroContrato = new JLabel(String.valueOf(contrato.getNumero()));
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(131, 12, 83, 32);
		lblNumeroContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNumeroContrato.setBackground(new java.awt.Color(255, 255, 255));
		lblNumeroContrato.setOpaque(true);
		lblNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		lblNumeroContrato.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("INICIO:");
		jLabel2.setBounds(226, 16, 81, 32);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("VENCE:");
		jLabel3.setBounds(487, 13, 81, 32);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("REMATE:");
		jLabel4.setBounds(749, 13, 96, 32);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		lblInicio = new JLabel(Constantes.formatoLocal.format(Constantes.formatoSQL.parse(contrato.getFechaContrato())));
		contenedor.add(lblInicio);
		lblInicio.setBounds(307, 12, 168, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInicio.setBackground(new java.awt.Color(255, 255, 255));
		lblInicio.setOpaque(true);
		lblInicio.setForeground(new java.awt.Color(0, 64, 128));

		lblVencimiento = new JLabel(Constantes.formatoLocal.format(Constantes.formatoSQL.parse(contrato.getFechaVencimiento())));
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(568, 12, 169, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblVencimiento.setBackground(new java.awt.Color(255, 255, 255));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setForeground(new java.awt.Color(0, 64, 128));

		lblRemate = new JLabel(Constantes.formatoLocal.format(Constantes.formatoSQL.parse(contrato.getFechaRemate())));
		contenedor.add(lblRemate);
		lblRemate.setBounds(845, 13, 168, 32);
		lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblRemate.setBackground(new java.awt.Color(255, 255, 255));
		lblRemate.setOpaque(true);
		lblRemate.setForeground(new java.awt.Color(0, 64, 128));

		tpContrato = new JTabbedPane();
		contenedor.add(tpContrato);
		tpContrato.setBounds(12, 140, 1221, 213);
		tpContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
		tpContrato.setForeground(new java.awt.Color(0, 0, 255));
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
		tbAbonos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbAbonos.getTableHeader().setForeground(new Color(181, 0, 0));
		jScrollPane5.setViewportView(tbAbonos);
		jScrollPane4 = new JScrollPane();
		pnlInteres.add(jScrollPane4);
		jScrollPane4.setPreferredSize(new java.awt.Dimension(1182, 150));
		jScrollPane4.setViewportView(tbIntereses);
		tbIntereses.setModel(InteresModel);
		jScrollPane5.setPreferredSize(new java.awt.Dimension(1189, 150));
		jScrollPane1 = new JScrollPane();
		pnlArticulos.add(jScrollPane1);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(1187, 150));
		tbArticulos = new JTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setModel(Constantes.ContratoModel);
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
		tbHistorial.setRowHeight(30);
		tbHistorial.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbHistorial.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbHistorial.getTableHeader().setForeground(new Color(181, 0, 0));

		tbHistorial.setModel(Constantes.PagoModel);
		tpContrato.addTab("MORAS", null, pnlMoras, null);
		pnlMoras.setPreferredSize(new java.awt.Dimension(1216, 172));
		jScrollPane3 = new JScrollPane();
		pnlMoras.add(jScrollPane3);
		jScrollPane3.setPreferredSize(new java.awt.Dimension(1181, 150));
		tbMoras = new JTable();
		jScrollPane3.setViewportView(tbMoras);
		tbMoras.setModel(MoraModel);
		tbMoras.setDefaultRenderer(Object.class, new RenderIM());
		tbMoras.setDefaultEditor(Object.class, new EditorIM());
		tbMoras.setRowHeight(30);
		tbMoras.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbMoras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbMoras.getTableHeader().setForeground(new Color(181, 0, 0));
		tbIntereses.setDefaultRenderer(Object.class, new RenderIM());
		tbIntereses.setDefaultEditor(Object.class, new EditorIM());
		tbIntereses.setRowHeight(30);
		tbIntereses.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbIntereses.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbIntereses.getTableHeader().setForeground(new Color(181, 0, 0));
		InteresModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					double total = 0;
					for (int i = 0; i <= InteresModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(InteresModel.getValueAt(
								i, 2).toString());
						if (estado == 1) {
							double monto = Double.parseDouble(InteresModel
									.getValueAt(i, 1).toString());
							total += monto;
						}
					}
					lblTotalInteres.setText(total + "");
					//calcularTotalAPagar();

				}
			}
		});
		MoraModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent tm) {
				if (tm.getType() == TableModelEvent.UPDATE) {
					double total = 0;
					for (int i = 0; i <= MoraModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(MoraModel.getValueAt(i,
								2).toString());
						if (estado == 1) {
							double monto = Double.parseDouble(MoraModel
									.getValueAt(i, 1).toString());
							total += monto;

						}
					}
					lblTotalMora.setText(total
							+ Double.parseDouble(lblMoraActual.getText()) + "");
					lblMoraAnterior.setText(total + "");
					//calcularTotalAPagar();

				}
			}
		});

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CLIENTE:");
		jLabel6.setBounds(520, 67, 87, 32);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("% MENSUAL");
		jLabel7.setBounds(290, 365, 133, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("CAPITAL");
		jLabel8.setBounds(12, 365, 81, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresMensual = new JLabel(String.valueOf(contrato.getInteres()));
		contenedor.add(lblInteresMensual);
		lblInteresMensual.setBounds(432, 365, 120, 32);
		lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresMensual.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresMensual.setOpaque(true);
		lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));

		lblCapital = new JLabel(String.valueOf(contrato.getCapital()));
		contenedor.add(lblCapital);
		lblCapital.setBounds(140, 365, 120, 32);
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCapital.setBackground(new java.awt.Color(255, 255, 255));
		lblCapital.setOpaque(true);
		lblCapital.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("¿MORA?");
		jLabel10.setBounds(12, 442, 81, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboMora = new JTextField("0%");
		contenedor.add(cboMora);
		cboMora.setBounds(290, 442, 261, 71);
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

		lblMoraSiNo = new JLabel();
		contenedor.add(lblMoraSiNo);
		lblMoraSiNo.setBounds(140, 441, 120, 32);
		lblMoraSiNo.setText("NO");
		lblMoraSiNo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraSiNo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraSiNo.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraSiNo.setOpaque(true);

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DIAS EX.");
		jLabel11.setBounds(290, 403, 117, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		lblDiasExcedidos = new JLabel(String.valueOf(contrato.getDiasExcedidos()));
		contenedor.add(lblDiasExcedidos);
		lblDiasExcedidos.setText("0");
		lblDiasExcedidos.setBounds(432, 403, 120, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblDiasExcedidos.setBackground(new java.awt.Color(255, 255, 255));
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));

		jLabel12 = new JLabel();
		contenedor.add(jLabel12);
		jLabel12.setText("M.PASADA");
		jLabel12.setBounds(11, 526, 131, 32);
		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel12.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("M.ACTUAL");
		jLabel13.setBounds(12, 482, 103, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraAnterior = new JLabel("0.00");
		contenedor.add(lblMoraAnterior);
		lblMoraAnterior.setBounds(138, 525, 120, 32);
		lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraAnterior.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraAnterior.setOpaque(true);
		lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));

		lblMoraActual = new JLabel();
		contenedor.add(lblMoraActual);
		lblMoraActual.setText("0.00");
		lblMoraActual.setBounds(139, 481, 120, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraActual.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraActual.setOpaque(true);
		lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("PAGO CON INTERESES");
		jLabel14.setBounds(602, 365, 304, 32);
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));
		jLabel14.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("M.TOTAL");
		jLabel15.setBounds(290, 526, 97, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel15.setForeground(new java.awt.Color(0, 128, 0));

		lblTotalInteres = new JLabel(String.valueOf(contrato.getInteres()));	
		contenedor.add(lblTotalInteres);
		lblTotalInteres.setBounds(602, 403, 304, 95);
		lblTotalInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalInteres.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalInteres.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalInteres.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalInteres.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalMora = new JLabel();
		contenedor.add(lblTotalMora);
		lblTotalMora.setText("0.00");
		lblTotalMora.setBounds(431, 525, 120, 32);
		lblTotalMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalMora.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalMora.setOpaque(true);
		lblTotalMora.setForeground(new java.awt.Color(0, 64, 128));

		lblIdentidad = new JLabel(contrato.getCliente().getDocumento());
		contenedor.add(lblIdentidad);
		lblIdentidad.setBounds(619, 67, 143, 32);
		lblIdentidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblIdentidad.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblIdentidad.setBackground(new java.awt.Color(255, 255, 255));
		lblIdentidad.setOpaque(true);
		lblIdentidad.setForeground(new java.awt.Color(0, 64, 128));

		lblCliente = new JLabel(contrato.getCliente().getNombres() + " " + contrato.getCliente().getPaterno() + " " + contrato.getCliente().getMaterno());
		contenedor.add(lblCliente);
		lblCliente.setBounds(774, 67, 460, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblCliente.setOpaque(true);
		lblCliente.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("% X DIA");
		jLabel16.setBounds(12, 403, 87, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresDiario = new JLabel(String.valueOf(contrato.getInteresDiario()));
		contenedor.add(lblInteresDiario);
		lblInteresDiario.setBounds(140, 403, 120, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));

		btnSoloInteres = new JButton();
		contenedor.add(btnSoloInteres);
		btnSoloInteres.setText("PAGAR INTERES");
		btnSoloInteres.setBounds(602, 504, 304, 65);
		btnSoloInteres.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnSoloInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnSoloInteres.setBackground(new java.awt.Color(0, 255, 255));
		btnSoloInteres.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane
						.showConfirmDialog(
								null,
								"<html><h2>¿Confirmar pago <strong style='color:red'>SOLO INTERES</strong>?</h2></html>",
								"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					//renovarContratoSoloInteres(Integer.parseInt(lblNumeroContrato.getText()), a[0]);
				} else {
					JOptionPane.showMessageDialog(null,
							"<html><h2>Operación Cancelada</h2></html>");
				}

			}
		});

		btnInteresConMora = new JButton();
		contenedor.add(btnInteresConMora);
		btnInteresConMora.setText("PAGAR INTERES Y MORA");
		btnInteresConMora.setBounds(930, 504, 304, 65);
		btnInteresConMora.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnInteresConMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnInteresConMora.setBackground(new java.awt.Color(0, 255, 255));
		btnInteresConMora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane
						.showConfirmDialog(
								null,
								"<html><h2>¿Confirmar pago <strong style='color:red'>INTERES + MORA</strong>?</h2></html>",
								"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					//renovarContratoInteresMora(Integer.parseInt(lblNumeroContrato.getText()), a[0]);
				} else {
					JOptionPane.showMessageDialog(null,
							"<html><h2>Operación Cancelada</h2></html>");
				}
			}
		});

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 124, 1222, 10);

		lblTotalAPagar = new JLabel();
		contenedor.add(lblTotalAPagar);
		lblTotalAPagar.setBounds(930, 403, 304, 95);
		lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalAPagar.setVerticalTextPosition(SwingConstants.TOP);
		lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalAPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setHorizontalTextPosition(SwingConstants.CENTER);

		jLabel17 = new JLabel();
		contenedor.add(jLabel17);
		jLabel17.setText("PAGO INTERES + MORA");
		jLabel17.setBounds(930, 365, 303, 32);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel17.setForeground(new java.awt.Color(0, 128, 0));
		jLabel17.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("MONEDA:");
		jLabel5.setBounds(320, 67, 98, 32);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		lblTipoMoneda = new JLabel(contrato.getMoneda());
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(440, 58, 58, 50);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		jSeparator2 = new JSeparator();
		contenedor.add(jSeparator2);
		jSeparator2.setBounds(12, 581, 1221, 10);

		lblP = new JLabel(contrato.getFlag());
		contenedor.add(lblP);
		lblP.setBounds(75, 12, 33, 32);
		lblP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblP.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblP.setOpaque(true);
		lblP.setBackground(new java.awt.Color(255, 255, 255));
		lblP.setForeground(new java.awt.Color(0, 64, 128));
		lblP.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel20 = new JLabel();
		contenedor.add(jLabel20);
		jLabel20.setText("-");
		jLabel20.setBounds(108, 12, 43, 29);
		jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 36));

		lblEstadoDetalle = new JTextArea(
				"NO SE ENCONTRÓ INFORMACIÓN HISTÓRICA PARA MOSTRAR.");
		contenedor.add(lblEstadoDetalle);
		lblEstadoDetalle.setEditable(false);
		lblEstadoDetalle.setBounds(12, 591, 1221, 121);
		lblEstadoDetalle.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblEstadoDetalle.setForeground(new java.awt.Color(255, 255, 255));
		lblEstadoDetalle.setBackground(new java.awt.Color(0, 64, 128));
		lblEstadoDetalle.setOpaque(true);
		lblEstadoDetalle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		jLabel19 = new JLabel();
		contenedor.add(jLabel19);
		jLabel19.setText("MORA:");
		jLabel19.setBounds(1025, 13, 96, 32);
		jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel19.setForeground(new java.awt.Color(0, 128, 0));

		txtMoraAsignada = new JLabel(contrato.getPrestamo().getMora());
		contenedor.add(txtMoraAsignada);
		txtMoraAsignada.setBounds(1098, 13, 48, 32);
		txtMoraAsignada.setOpaque(true);
		txtMoraAsignada.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMoraAsignada.setBackground(new java.awt.Color(255, 255, 255));
		txtMoraAsignada.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtMoraAsignada.setForeground(new java.awt.Color(0, 64, 128));
		txtMoraAsignada.setHorizontalAlignment(SwingConstants.CENTER);

		txtTipoPrestamo = new JTextField(contrato.getPrestamo().getDescripcion());
		contenedor.add(txtTipoPrestamo);
		txtTipoPrestamo.setBounds(1151, 13, 83, 32);
		txtTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTipoPrestamo.setForeground(new java.awt.Color(0, 64, 128));
		txtTipoPrestamo.setEditable(false);
		
		CargarInformacionContrato();

	}

	/*CARGAR HISTORIA CONTRATO*/
	//Cargar Separaciones
	//Cargar Ventas
	//Cargar Cargos
	
	public void CargarInformacionContrato(){
		CargarDetalleContrato();
		CargarPagosRealizados();
		CalcularCuotas();
		
	}
	
	/*public void cargarContrato(String numero, String tabla, String detalle) {
		try {
		
				
				
				
				cargarHistorialPagos(rs.getInt("id_con"));
				cargarHistorialMoras(rs.getInt("id_con"));
				cargarHistorialAbonos(rs.getInt("id_con"));
				//determinarAccion(lblEstado.getText().toUpperCase());
				lblTotalAPagar.setText(Redondeo.redondearCentimos(fd
						.format(Double.parseDouble(lblTotalInteres.getText())
								+ Double.parseDouble(lblTotalMora.getText()))));
				
			} else {
				JOptionPane.showMessageDialog(null,
						"No existe tal número de Contrato");
				this.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	

	public void CargarDetalleContrato() {
		Constantes.ContratoModel.setRowCount(0);
		for(DetalleContrato dc : contrato.getDetalleContratos()){
			Constantes.ContratoModel.addRow(new Object[]{
					dc.getArticulo().getId(),
					dc.getArticulo().getDescripcion(),
					dc.getArticulo().getMarca(),
					dc.getArticulo().getModelo(),
					dc.getArticulo().getObs(),
					dc.getTasacion()						
			});				
		}			
		tbArticulos.setModel(Constantes.ContratoModel);		
	}
	
	public void CargarPagosRealizados() {
		Constantes.PagoModel.setRowCount(0);
		for(Pago p : contrato.getPagos()){
			Constantes.PagoModel.addRow(new Object[]{
					p.getFechaVencimiento(),
					p.getFechaPago(),
					p.getInteres(),
					p.getMora(),
					p.getInteres().add(p.getMora())
			});
		}		
		tbHistorial.setModel(Constantes.PagoModel);	
	}
	
	

	public void CalcularCuotas() {
		try {
			
			double total = 0;
			Calendar v = Calendar.getInstance();
			v.setTime(Constantes.formatoSQL.parse(contrato.getFechaVencimiento()));
			Number nro_cuotas = contrato.getDiasExcedidos() / contrato.getDiaFinal();
			Number dias_residuo = contrato.getDiasExcedidos() % contrato.getDiaFinal();
			
			for (int i = 0; i <= nro_cuotas.intValue(); i++) {
				InteresModel
						.addRow(new Object[] {
								new SimpleDateFormat("MMMM").format(v.getTime()).toUpperCase(),
								contrato.getInteres(), 
								1 });
				total += contrato.getInteres();
				v.add(Calendar.MONTH, 1);
				contrato.getCuotas()++;
			}
			nro_dias_global = dias_residuo.intValue();
			tbIntereses.setModel(InteresModel);
			lblTotalInteres.setText(fd.format(total));
			lblDiasExcedidos.setText(dias_residuo.intValue() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void cargarDetalleEstado(String numero) {
		String estado = lblEstado.getText();
		if (estado.equalsIgnoreCase("CANCELADO")) {
			//Validar si está cancelado. Print en la consola.
			
		} else if (estado.equalsIgnoreCase("SEPARADO")) {
			//Si está separaado.Print en la consola.
		} else {
			//Está en otra sede
		}
	}*/

	/*public void determinarAccion(String estado) {
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
	}*/

	

	public void correspondeMora(int diasExcedidos) {
		System.out.println("usando nuevo algoritmo de cálculo de moras.");
		if (nro_cuotas_global == 1 && nro_dias_global > 5) {
			lblMoraSiNo.setText("SI");
			lblMoraSiNo.setForeground(Color.RED);
			electivoMora(1, 1);
		} else if (nro_cuotas_global == 2
				&& Integer.parseInt(lblDiasExcedidos.getText()) == 0) {
			lblMoraSiNo.setText("SI");
			lblMoraSiNo.setForeground(Color.RED);
			electivoMora2(1, 1);
		} else if (nro_cuotas_global == 2
				&& Integer.parseInt(lblDiasExcedidos.getText()) > 0) {
			lblMoraSiNo.setText("SI");
			lblMoraSiNo.setForeground(Color.RED);
			electivoMora(0, 2);
		} else if (nro_cuotas_global >= 2) {
			lblMoraSiNo.setText("SI");
			lblMoraSiNo.setForeground(Color.RED);
			electivoMora(0, nro_cuotas_global);
		} else {
			lblMoraSiNo.setText("NO");
			lblMoraSiNo.setForeground(new java.awt.Color(0, 128, 0));
		}

	}

	public void electivoMora(int n, int m) {
		try {
			double totalAPagar = Double.parseDouble(lblTotalInteres.getText());
			double mora = 0.00;
			if (txtMoraAsignada.getText().equalsIgnoreCase("REG")) {
				System.out.println("Regular");
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
			//calcularMoraTotal();
			//calcularTotalAPagar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void electivoMora2(int n, int m) {
		System.out.println("Electivo Mora 2");
		try {
			double totalAPagar = Double
					.parseDouble(lblInteresMensual.getText());
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
			//calcularMoraTotal();
			//calcularTotalAPagar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void calcularMoraAnterior(int contrato) {
		double c = 0.00;
		try {

			String sql = "SELECT * FROM tb_mora WHERE id_con=? AND est_mora=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				c += rs.getDouble("val_mora");
			}
			lblMoraAnterior.setText(Redondeo.redondearCentimos(fd.format(c)));
		} catch (Exception e) {
			e.printStackTrace();
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
	}*/

	/*public void calcularMoraTotal() {
		try {
			double actual = Double.parseDouble(lblMoraActual.getText());
			double anterior = Double.parseDouble(lblMoraAnterior.getText());
			lblTotalMora.setText(Redondeo.redondearCentimos(fd
					.format((actual + anterior))));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 
	public void activo() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 128, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vencido() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(240, 80, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remate() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(200, 0, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pre() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(255, 0, 128));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void post() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(128, 0, 255));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vitrina() {
		try {
			lblEstado.setForeground(Color.BLACK);
			lblEstado.setBackground(new Color(255, 255, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
			btnSoloInteres.setEnabled(false);
			btnInteresConMora.setEnabled(false);
			if (lblNumeroContrato.getText().equalsIgnoreCase("1407")) {
				btnSoloInteres.setEnabled(true);
				btnInteresConMora.setEnabled(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void separado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 128, 192));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
			btnSoloInteres.setEnabled(false);
			btnInteresConMora.setEnabled(false);
			if (lblNumeroContrato.getText().equalsIgnoreCase("1407")) {
				btnSoloInteres.setEnabled(true);
				btnInteresConMora.setEnabled(true);
			}
			cargarDetalleEstado(lblNumeroContrato.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rematado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(255, 0, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			btnSoloInteres.setEnabled(false);
			btnInteresConMora.setEnabled(false);
			if (lblNumeroContrato.getText().equalsIgnoreCase("1407")) {
				btnSoloInteres.setEnabled(true);
				btnInteresConMora.setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelado() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 0, 160));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			btnSoloInteres.setEnabled(false);
			btnInteresConMora.setEnabled(false);
			if (lblNumeroContrato.getText().equalsIgnoreCase("1407")) {
				btnSoloInteres.setEnabled(true);
				btnInteresConMora.setEnabled(true);
			}
			cargarDetalleEstado(lblNumeroContrato.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uso_oficina() {
		try {
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0, 0, 0));
			calcularCuotas();
			correspondeMora(Integer.parseInt(lblDiasExcedidos.getText()));
			calcularMoraAnterior(Integer.parseInt(lblNumeroContrato.getText()));
			calcularMoraTotal();
			cboMora.setEnabled(true);
			btnSoloInteres.setEnabled(false);
			btnInteresConMora.setEnabled(false);
			if (lblNumeroContrato.getText().equalsIgnoreCase("1407")) {
				btnSoloInteres.setEnabled(true);
				btnInteresConMora.setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*

	public void renovarContratoSoloInteres(int numeroContrato, String tabla) {
		try {
			Calendar fcvto = Calendar.getInstance();
			Calendar frem = Calendar.getInstance();
			fcvto.setTime(vcto);
			frem.setTime(remate);
			fcvto.add(Calendar.MONTH, calcularRenovacion());
			frem.add(Calendar.MONTH, calcularRenovacion());
			proximo_vencimiento = fcvto.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date hoy = new Date();
			String sql = "UPDATE " + tabla
					+ " SET fec_ven_con=?, fec_rem_con=? WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sdf.format(fcvto.getTime()));
			pst.setString(2, sdf.format(frem.getTime()));
			pst.setInt(3, numeroContrato);
			pst.executeUpdate();
			Pago_Contrato pago = new Pago_Contrato(
					Integer.parseInt(lblNumeroContrato.getText()),
					sdf.format(vcto), sdf.format(hoy), 0.00,
					Double.parseDouble(Redondeo.redondearCentimos(fd
							.format(Double.parseDouble(lblTotalInteres
									.getText())))), 0.00);
			pago.grabarPago();
			if (Double.parseDouble(lblTotalMora.getText()) != 0.00) {
				Mora mora = new Mora(Integer.parseInt(lblNumeroContrato
						.getText()),
						Double.parseDouble(lblMoraActual.getText()));
				mora.grabarMora();
			}
			registrarIngresoCaja();
			actualizarContratos(fcvto.getTime());
			JOptionPane.showMessageDialog(
					null,
					"<html><h1 style='color: red;'>Próximo Vencimiento : "
							+ new SimpleDateFormat("dd-MMMM-yyyy").format(
									proximo_vencimiento).toUpperCase()
							+ "</h1></html>");
			internalClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renovarContratoInteresMora(int numeroContrato, String tabla) {

		try {
			Calendar fcvto = Calendar.getInstance();
			Calendar frem = Calendar.getInstance();
			fcvto.setTime(vcto);
			frem.setTime(remate);
			fcvto.add(Calendar.MONTH, calcularRenovacion());
			frem.add(Calendar.MONTH, calcularRenovacion());
			proximo_vencimiento = fcvto.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date hoy = new Date();
			String sql = "UPDATE " + tabla
					+ " SET fec_ven_con=?, fec_rem_con=? WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sdf.format(fcvto.getTime()));
			pst.setString(2, sdf.format(frem.getTime()));
			pst.setInt(3, numeroContrato);
			pst.executeUpdate();
			Pago_Contrato pago = new Pago_Contrato(
					Integer.parseInt(lblNumeroContrato.getText()),
					sdf.format(vcto), sdf.format(hoy), 0.00,
					Double.parseDouble(Redondeo.redondearCentimos(fd
							.format(Double.parseDouble(lblTotalInteres
									.getText())))), Double.parseDouble(Redondeo
							.redondearCentimos(fd.format(Double
									.parseDouble(lblTotalMora.getText())))));
			pago.grabarPago();
			retirarMora(numeroContrato);
			registrarIngresoInteresMoraCaja();
			actualizarContratos(fcvto.getTime());
			JOptionPane.showMessageDialog(
					null,
					"<html><h1 style='color: red;'>Próximo Vencimiento : "
							+ new SimpleDateFormat("dd-MMMM-yyyy").format(
									proximo_vencimiento).toUpperCase()
							+ "</h1></html>");
			internalClose();
		} catch (Exception e) {
			e.printStackTrace();
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

	public void retirarMora(int contrato) {
		try {
			for (int i = 0; i < modeloMora.getRowCount(); i++) {
				int estado = Integer.parseInt(modeloMora.getValueAt(i, 2)
						.toString());
				if (estado == 1) {
					String sql = "UPDATE tb_mora SET est_mora=0 WHERE id_con=? AND id_mora=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, contrato);
					pst.setInt(2, Integer.parseInt(modeloMora.getValueAt(i, 0)
							.toString()));
					pst.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void internalClose() {
		this.dispose();
	}

	public void cargarHistorialMoras(int contrato) {
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
		}
	}

	public void registrarIngresoCaja() {
		double cambio = (lblTipoMoneda.getText().equalsIgnoreCase("$")) ? obtenerCambio()
				: 1;
		String moneda = (cambio == 1) ? "" : ">($)";
		Ingreso interes = new Ingreso(Principal.id_libro_caja, lblP.getText()
				+ "-" + lblNumeroContrato.getText() + moneda,
				calcularCantidadCuotasPagadas() + "%", 0.00,
				Double.parseDouble(Redondeo.redondearCentimos(fd.format(Double
						.parseDouble(lblTotalInteres.getText())))) * cambio,
				0.00);
		interes.registrarIngreso();
	}

	public void registrarIngresoInteresMoraCaja() {
		double cambio = (lblTipoMoneda.getText().equalsIgnoreCase("$")) ? obtenerCambio()
				: 1;
		String moneda = (cambio == 1) ? "" : ">($)";
		Ingreso mora = new Ingreso(Principal.id_libro_caja, lblP.getText()
				+ "-" + lblNumeroContrato.getText() + moneda,
				calcularCantidadCuotasPagadas() + "% + M", 0.00,
				(Double.parseDouble(Redondeo.redondearCentimos(fd.format(Double
						.parseDouble(lblTotalInteres.getText())))) + Double
						.parseDouble(Redondeo.redondearCentimos(fd
								.format(Double.parseDouble(lblTotalMora
										.getText())))))
						* cambio, 0.00);
		mora.registrarIngreso();
	}

	public String[] arrayTablas(int id_contrato) {
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
					System.out.println("encontrado en contrato manual");
					return arrayTablas = new String[] { "tb_contrato_manual",
							"tb_detalle_contrato_manual" };

				} else {
					String sql2 = "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_contrato);
					ResultSet rs2 = pst2.executeQuery();
					if (rs2.next()) {
						System.out.println("encontrado en contrato orol");
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
		}
		return arrayTablas;
	}

	public int calcularCantidadCuotasPagadas() {
		int cc = 0;
		for (int i = 0; i <= modeloInteres.getRowCount() - 1; i++) {
			int estado = Integer.parseInt(modeloInteres.getValueAt(i, 2)
					.toString());
			if (estado == 1) {
				cc++;
			}
		}
		return cc;
	}

	public int calcularRenovacion() {
		int dias = 0;
		for (int i = 0; i <= modeloInteres.getRowCount() - 1; i++) {
			int estado = Integer.parseInt(modeloInteres.getValueAt(i, 2)
					.toString());
			if (estado == 1) {
				dias += 1;
			}
		}
		return dias;
	}

	public void calcularTotalAPagar() {
		double interes = Double.parseDouble(lblTotalInteres.getText());
		double mora = Double.parseDouble(lblTotalMora.getText());
		lblTotalAPagar.setText("");
		lblTotalAPagar.setText(Redondeo.redondearCentimos(fd.format(interes
				+ mora)));
	}*/

	/*public void actualizarContratos(Date fecha_vencimiento) {
		try {
			System.out.println(fecha_vencimiento);
			Date hoy = new Date();
			GregorianCalendar gcPre = new GregorianCalendar();
			GregorianCalendar gcPost = new GregorianCalendar();

			gcPre.setTime(fecha_vencimiento);
			gcPost.setTime(fecha_vencimiento);
			gcPre.add(Calendar.MONTH, 1);
			gcPre.add(Calendar.DATE, -5);
			gcPost.add(Calendar.MONTH, 1);
			gcPost.add(Calendar.DATE, 16);
			Date periodoPreRiesgo = gcPre.getTime();
			Date periodoPostRiesgo = gcPost.getTime();
			if (hoy.after(periodoPostRiesgo)) {
				String tabla[] = arrayTablas(Integer.parseInt(lblNumeroContrato
						.getText()));
				//A REMATE
				cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()),
						tabla[0], 13);
			} else if (periodoPostRiesgo.getTime() == hoy.getTime()) {
				String tabla[] = arrayTablas(Integer.parseInt(lblNumeroContrato
						.getText()));
				// A POST
				cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()),
						tabla[0], 7);
			} else if (periodoPreRiesgo.getTime() == hoy.getTime()
					&& hoy.getTime() < fecha_vencimiento.getTime()) {
				String tabla[] = arrayTablas(Integer.parseInt(lblNumeroContrato
						.getText()));
				// A PRE 
				cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()),
						tabla[0], 4);
			} else if (hoy.after(fecha_vencimiento)) {
				String tabla[] = arrayTablas(Integer.parseInt(lblNumeroContrato
						.getText()));
				// A VENCIDO 
				cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()),
						tabla[0], 2);
			} else {
				String tabla[] = arrayTablas(Integer.parseInt(lblNumeroContrato
						.getText()));
				// A ACTIVO
				cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()),
						tabla[0], 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*public void cargarHistorialAbonos(int contrato) throws SQLException {
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

	public void cambiarEstado(int codigo, String tabla, int nuevo_estado) {
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=? WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nuevo_estado);
			pst.setInt(2, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
