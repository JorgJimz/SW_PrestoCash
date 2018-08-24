package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import common.Constantes;
import common.EditorIM;
import common.RenderIM;
import common.RenderIO;
import common.Utiles;
import controller.ContratoController;
import model.Abono;
import model.Contrato;
import model.DetalleContrato;
import model.EContrato;
import model.Ingreso;
import model.Mora;
import model.Pago;
import model.Seguimiento;

@SuppressWarnings({ "serial" })
public class Gestion_Contrato extends JInternalFrame {
	private JLabel lblEstado;
	private JLabel jLabel4;
	private JTabbedPane tpContrato;
	private JTable tbArticulos;
	private JLabel lblTotalMora;
	private JLabel jLabel15;
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
	private JLabel jLabel23;
	private JLabel jLabel22;
	private JLabel jLabel21;
	private JRadioButton rbSaliente;
	private JRadioButton rbEntrante;
	private ButtonGroup rbgSeguimiento;
	private JButton btnRegresar;
	private JButton btnGrabarSeguimiento;
	private JTextArea txtDetalleSeguimiento;
	private JLayeredPane pnlOperacionContainer;
	private JPanel pnlSeguimientoContainer;
	private JLabel lblMoraProrrateo;
	private JLabel jLabel19;
	private JLabel lblInteresTotal;
	private JLabel jLabel18;
	private JButton btnSeguimiento;
	private JTextField txtAbono;
	private JRadioButton rbPagoMora;
	private JRadioButton rbAbonarCapital;
	private JLabel jLabel17;
	private JLabel jLabel9;
	private JLabel jLabel5;
	private JLabel jLabel1;
	private JLabel lblProrrateo;
	private JLabel jLabel14;
	private JLabel lblInteresMensual;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JPanel pnlArticulos;
	private JPanel pnlMoras;
	private JPanel pnlPagos;
	private JPanel pnlCargos;
	private JPanel pnlSeguimiento;
	private JLabel lblRemate;
	private JLabel lblVencimiento;
	private JLabel lblInicio;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel lblNumeroContrato;
	private JPanel contenedor;
	private JRadioButton rbCancelarContrato;
	private JRadioButton rbPagoInteresMora;
	private ButtonGroup rbgOpcionPago;
	private JRadioButton rbPagoInteres;
	private JLabel jLabel20;
	private JLabel lblP;
	private JLabel lblTipoMoneda;
	private JTable tbAbonos;
	private JScrollPane spAbonos;
	private JTable tbIntereses;
	private JScrollPane spIntereses;
	private JLabel lblTotalAPagar;
	private JSeparator jSeparator1;
	private JTable tbMoras;
	private JScrollPane spArticulos;
	private JScrollPane spPagos;
	private JScrollPane spMoras;
	private JScrollPane spCargos;
	private JScrollPane spSeguimiento;
	private JTable tbCargos;
	private JTable tbSeguimiento;
	private JButton btnPagar;
	private JLabel lblInteresDiario;
	private JLabel jLabel16;
	private JTable tbPagos;
	private JLabel lblCliente;

	Contrato contrato;

	DefaultTableModel MoraModel = new DefaultTableModel(null,
			new String[] { "ID", "FECHA VENCIMIENTO", "FECHA MORA", "MONTO", "¿ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbMoras.getSelectedRow() && colIndex == 4) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel InteresModel = new DefaultTableModel(null, new String[] { "MES", "MONTO", "¿ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbIntereses.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel AbonoModel = new DefaultTableModel(null, new String[] { "FECHA PAGO", "CAPITAL ANTERIOR",
			"INTERES ANTERIOR", "IMPORTE ABONADO", "NUEVO CAPITAL", "NUEVO INTERES" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbIntereses.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	private JPanel pnlInteres;
	private JPanel pnlAbonos;
	private JTextField txtTipoPrestamo;

	public Gestion_Contrato(String c) throws ParseException {
		String flag = String.valueOf(c.split("-")[0]);
		int numero = Integer.parseInt(String.valueOf(c.split("-")[1]));
		contrato = new ContratoController().CargarContrato(flag.toUpperCase(), numero);
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(897, 426);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1277, 815));
		this.setBounds(0, 0, 1277, 815);
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1275, 785);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		InteresModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					BigDecimal total = BigDecimal.ZERO;
					for (int i = 0; i <= InteresModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(InteresModel.getValueAt(i, 2).toString());
						if (estado == 1) {
							total = total.add(new BigDecimal(String.valueOf(InteresModel.getValueAt(i, 1))));
						}
					}
					contrato.setInteresTotal(total);
					rbPagoInteres.doClick();
				}
			}
		});
		MoraModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent tm) {
				if (tm.getType() == TableModelEvent.UPDATE) {
					BigDecimal total = BigDecimal.ZERO;
					for (int i = 0; i <= MoraModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(MoraModel.getValueAt(i, 4).toString());
						if (estado == 1) {
							total = total.add(new BigDecimal(String.valueOf(MoraModel.getValueAt(i, 3))));
						}
					}
					contrato.setMoraTotal(total.add(contrato.getMoraActual()));
					lblTotalMora.setText(String.valueOf(total.add(contrato.getMoraActual())));
					lblMoraAnterior.setText(String.valueOf(total));
					rbPagoMora.doClick();
				}
			}
		});

		lblEstado = new JLabel(String.valueOf(contrato.getEContrato().getDescripcion()));
		contenedor.add(lblEstado);
		lblEstado.setBounds(320, 11, 232, 32);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblEstado.setOpaque(true);
		lblEstado.setBackground(contrato.getEContrato().getBackground());
		lblEstado.setForeground(contrato.getEContrato().getForeground());
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

		lblNumeroContrato = new JLabel(String.valueOf(contrato.getNumero()));
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(106, 14, 201, 67);
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 80));
		lblNumeroContrato.setOpaque(true);
		lblNumeroContrato.setForeground(new java.awt.Color(0, 0, 255));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("INICIO");
		jLabel2.setBounds(564, 11, 81, 32);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("VENCE");
		jLabel3.setBounds(793, 11, 81, 32);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("REMATE");
		jLabel4.setBounds(1023, 11, 96, 32);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		lblInicio = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaContrato())).toUpperCase());
		contenedor.add(lblInicio);
		lblInicio.setBounds(564, 49, 218, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInicio.setBackground(new java.awt.Color(255, 255, 255));
		lblInicio.setOpaque(true);
		lblInicio.setForeground(new java.awt.Color(0, 64, 128));

		lblVencimiento = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaVencimiento())).toUpperCase());
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(793, 49, 218, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblVencimiento.setBackground(new java.awt.Color(255, 255, 255));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setForeground(new java.awt.Color(0, 64, 128));

		lblRemate = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaRemate())).toUpperCase());
		contenedor.add(lblRemate);
		lblRemate.setBounds(1023, 49, 218, 32);
		lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblRemate.setBackground(new java.awt.Color(255, 255, 255));
		lblRemate.setOpaque(true);
		lblRemate.setForeground(new java.awt.Color(0, 64, 128));

		tpContrato = new JTabbedPane();
		contenedor.add(tpContrato);
		tpContrato.setBounds(12, 184, 1229, 284);
		tpContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		tpContrato.setForeground(new java.awt.Color(0, 0, 0));

		pnlArticulos = new JPanel();
		spArticulos = new JScrollPane();
		spArticulos.setPreferredSize(new java.awt.Dimension(1202, 229));
		pnlArticulos.setPreferredSize(new java.awt.Dimension(1224, 264));
		tbArticulos = new JTable();
		tbArticulos.setRowHeight(30);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.setModel(Constantes.ContratoModel);
		spArticulos.setViewportView(tbArticulos);
		pnlArticulos.add(spArticulos);
		tpContrato.addTab("ARTÍCULOS", null, pnlArticulos, null);

		pnlInteres = new JPanel();
		spIntereses = new JScrollPane();
		tbIntereses = new JTable();
		tbIntereses.setDefaultRenderer(Object.class, new RenderIM());
		tbIntereses.setDefaultEditor(Object.class, new EditorIM());
		tbIntereses.setRowHeight(30);
		tbIntereses.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbIntereses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbIntereses.setModel(InteresModel);
		tbIntereses.getTableHeader().setForeground(new Color(181, 0, 0));
		spIntereses.setPreferredSize(new java.awt.Dimension(1199, 225));
		spIntereses.setViewportView(tbIntereses);
		pnlInteres.add(spIntereses);
		tpContrato.addTab("INTERESES", null, pnlInteres, null);

		pnlPagos = new JPanel();
		spPagos = new JScrollPane();
		tbPagos = new JTable();
		tbPagos.setRowHeight(30);
		tbPagos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbPagos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbPagos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbPagos.setModel(Constantes.PagoModel);
		spPagos.setPreferredSize(new java.awt.Dimension(1206, 226));
		spPagos.setViewportView(tbPagos);
		pnlPagos.add(spPagos);
		tpContrato.addTab("PAGOS", null, pnlPagos, null);
		pnlPagos.setPreferredSize(new java.awt.Dimension(1224, 246));

		pnlMoras = new JPanel();
		spMoras = new JScrollPane();
		tbMoras = new JTable();
		tbMoras.setModel(MoraModel);
		tbMoras.setDefaultRenderer(Object.class, new RenderIM());
		tbMoras.setDefaultEditor(Object.class, new EditorIM());
		tbMoras.setRowHeight(30);
		tbMoras.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbMoras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbMoras.getTableHeader().setForeground(new Color(181, 0, 0));
		spMoras.setPreferredSize(new java.awt.Dimension(1200, 229));
		spMoras.setViewportView(tbMoras);
		pnlMoras.add(spMoras);
		tpContrato.addTab("MORAS", null, pnlMoras, null);

		pnlAbonos = new JPanel();
		spAbonos = new JScrollPane();
		tbAbonos = new JTable();
		tbAbonos.setModel(AbonoModel);
		tbAbonos.setRowHeight(30);
		tbAbonos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbAbonos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbAbonos.getTableHeader().setForeground(new Color(181, 0, 0));
		spAbonos.setPreferredSize(new java.awt.Dimension(1204, 229));
		spAbonos.setViewportView(tbAbonos);
		pnlAbonos.add(spAbonos);
		tpContrato.addTab("ABONOS", null, pnlAbonos, null);

		pnlCargos = new JPanel();
		spCargos = new JScrollPane();
		tbCargos = new JTable();
		tbCargos.setModel(Constantes.CargoModel);
		tbCargos.setRowHeight(30);
		tbCargos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbCargos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbCargos.getTableHeader().setForeground(new Color(181, 0, 0));
		spCargos.setPreferredSize(new java.awt.Dimension(1204, 229));
		spCargos.setViewportView(tbCargos);
		pnlCargos.add(spCargos);
		tpContrato.addTab("CARGOS", null, pnlCargos, null);

		pnlSeguimiento = new JPanel();
		spSeguimiento = new JScrollPane();
		tbSeguimiento = new JTable();
		tbSeguimiento.setModel(Constantes.SeguimientoModel);
		tbSeguimiento.setDefaultRenderer(Object.class, new RenderIO());
		tbSeguimiento.setRowHeight(36);
		tbSeguimiento.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbSeguimiento.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbSeguimiento.getTableHeader().setForeground(new Color(181, 0, 0));
		spSeguimiento.setPreferredSize(new java.awt.Dimension(1204, 229));
		spSeguimiento.setViewportView(tbSeguimiento);
		pnlSeguimiento.add(spSeguimiento);
		tpContrato.addTab("SEGUIMIENTO", null, pnlSeguimiento, null);

		pnlOperacionContainer = new JLayeredPane();
		pnlOperacionContainer.setVisible(true);
		pnlOperacionContainer.setOpaque(false);
		contenedor.add(pnlOperacionContainer);
		pnlOperacionContainer.setLayout(null);
		pnlOperacionContainer.setBounds(558, 474, 683, 281);

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CLIENTE");
		jLabel6.setBounds(292, 87, 87, 35);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("% MENSUAL");
		jLabel7.setBounds(12, 533, 131, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("CAPITAL");
		jLabel8.setBounds(12, 485, 131, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresMensual = new JLabel(String.valueOf(contrato.getInteresMensual()));
		contenedor.add(lblInteresMensual);
		lblInteresMensual.setBounds(143, 532, 120, 32);
		lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresMensual.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresMensual.setOpaque(true);
		lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));

		lblCapital = new JLabel(String.valueOf(contrato.getCapital()));
		contenedor.add(lblCapital);
		lblCapital.setBounds(143, 485, 120, 32);
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCapital.setBackground(new java.awt.Color(255, 255, 255));
		lblCapital.setOpaque(true);
		lblCapital.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("¿MORA?");
		jLabel10.setBounds(275, 485, 123, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboMora = new JTextField(
				contrato.getMoraPorcentaje().multiply(new BigDecimal(100).setScale(0, RoundingMode.HALF_UP)) + "%");
		contenedor.add(cboMora);
		cboMora.setBounds(426, 531, 120, 32);
		cboMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboMora.setBackground(new java.awt.Color(255, 255, 128));
		cboMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		cboMora.setForeground(new java.awt.Color(255, 255, 255));
		cboMora.setHorizontalAlignment(SwingConstants.CENTER);
		cboMora.setEnabled(false);
		cboMora.setEditable(false);
		cboMora.setEnabled(false);
		cboMora.setEnabled(false);

		lblMoraSiNo = new JLabel(contrato.getMoraRespuesta());
		contenedor.add(lblMoraSiNo);
		lblMoraSiNo.setBounds(426, 485, 120, 32);
		lblMoraSiNo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraSiNo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraSiNo.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraSiNo.setForeground(contrato.getMoraColor());
		lblMoraSiNo.setOpaque(true);

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DIAS EXTRA");
		jLabel11.setBounds(12, 628, 131, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		lblDiasExcedidos = new JLabel(String.valueOf(contrato.getDiasResiduo()));
		contenedor.add(lblDiasExcedidos);
		lblDiasExcedidos.setBounds(143, 628, 120, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblDiasExcedidos.setBackground(new java.awt.Color(255, 255, 255));
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));

		jLabel12 = new JLabel();
		contenedor.add(jLabel12);
		jLabel12.setText("M.PASADA");
		jLabel12.setBounds(281, 628, 123, 32);
		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel12.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("M.ACTUAL");
		jLabel13.setBounds(281, 577, 123, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraAnterior = new JLabel("0.00");
		contenedor.add(lblMoraAnterior);
		lblMoraAnterior.setBounds(426, 626, 120, 32);
		lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraAnterior.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraAnterior.setOpaque(true);
		lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));

		lblMoraActual = new JLabel(String.valueOf(contrato.getMoraActual()));
		contenedor.add(lblMoraActual);
		lblMoraActual.setBounds(426, 577, 120, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraActual.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraActual.setOpaque(true);
		lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("M.TOTAL");
		jLabel15.setBounds(281, 672, 123, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel15.setForeground(new java.awt.Color(0, 128, 0));

		lblTotalMora = new JLabel();
		contenedor.add(lblTotalMora);
		lblTotalMora.setText("0.00");
		lblTotalMora.setBounds(426, 670, 120, 32);
		lblTotalMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalMora.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalMora.setOpaque(true);
		lblTotalMora.setForeground(new java.awt.Color(0, 64, 128));

		lblCliente = new JLabel(contrato.getCliente().getNombres() + " " + contrato.getCliente().getPaterno() + " "
				+ contrato.getCliente().getMaterno());
		contenedor.add(lblCliente);
		lblCliente.setBounds(292, 122, 725, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblCliente.setOpaque(true);
		lblCliente.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("% DIARIO");
		jLabel16.setBounds(12, 672, 131, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresDiario = new JLabel(String.valueOf(contrato.getInteresDiario()));
		contenedor.add(lblInteresDiario);
		lblInteresDiario.setBounds(143, 676, 120, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 166, 1222, 10);

		txtAbono = new JTextField();
		pnlOperacionContainer.add(txtAbono);
		txtAbono.setVisible(false);
		txtAbono.setBounds(222, 57, 404, 128);
		txtAbono.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtAbono.setForeground(new java.awt.Color(255, 0, 0));
		txtAbono.setFont(new java.awt.Font("Segoe UI", 1, 72));
		txtAbono.setBackground(new Color(255, 255, 128));
		txtAbono.setHorizontalAlignment(SwingConstants.LEFT);
		txtAbono.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (Utiles.Validar(contenedor)) {
					BigDecimal abono = new BigDecimal(txtAbono.getText());
					lblCapital.setText(String.valueOf(contrato.getCapital().subtract(abono)));

					BigDecimal neoInteres = contrato.getCapital().subtract(abono)
							.multiply(contrato.getPrestamo().getPorcentaje()).setScale(2, RoundingMode.HALF_UP);

					BigDecimal interesMinimo = (neoInteres.compareTo(BigDecimal.TEN) == 1) ? neoInteres
							: BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);

					lblInteresMensual.setText(String.valueOf(interesMinimo));
				} else {
					lblCapital.setText(String.valueOf(contrato.getCapital()));
					lblInteresMensual.setText(String.valueOf(contrato.getInteresMensual()));
				}
			}
		});

		lblTipoMoneda = new JLabel(contrato.getMoneda());
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(1029, 122, 212, 32);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		lblP = new JLabel(contrato.getFlag());
		contenedor.add(lblP);
		lblP.setBounds(12, 13, 64, 69);
		lblP.setFont(new java.awt.Font("Segoe UI", 1, 80));
		lblP.setOpaque(true);
		lblP.setHorizontalAlignment(SwingConstants.LEFT);
		lblP.setForeground(new java.awt.Color(0, 0, 255));

		jLabel20 = new JLabel();
		contenedor.add(jLabel20);
		jLabel20.setText("-");
		jLabel20.setBounds(76, 14, 103, 67);
		jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 72));
		jLabel20.setVerticalAlignment(SwingConstants.CENTER);
		jLabel20.setForeground(new java.awt.Color(0, 0, 255));

		txtTipoPrestamo = new JTextField(contrato.getPrestamo().getDescripcion());
		contenedor.add(txtTipoPrestamo);
		txtTipoPrestamo.setBounds(320, 49, 232, 32);
		txtTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTipoPrestamo.setForeground(new java.awt.Color(0, 64, 128));
		txtTipoPrestamo.setOpaque(true);
		txtTipoPrestamo.setEnabled(false);
		txtTipoPrestamo.setHorizontalAlignment(SwingConstants.CENTER);

		rbgOpcionPago = new ButtonGroup();
		rbgSeguimiento = new ButtonGroup();

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("PRORRATEO");
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));
		jLabel14.setBounds(12, 724, 131, 32);

		lblProrrateo = new JLabel(String.valueOf(contrato.getProrrateo()));
		contenedor.add(lblProrrateo);
		lblProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblProrrateo.setBackground(new java.awt.Color(255, 255, 255));
		lblProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblProrrateo.setOpaque(true);
		lblProrrateo.setBounds(143, 724, 120, 32);

		jLabel1 = new JLabel("% MORA");
		contenedor.add(jLabel1);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setBounds(275, 533, 123, 32);

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("MONEDA");
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		jLabel5.setBounds(1029, 87, 87, 35);

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("DOCUMENTO");
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));
		jLabel9.setBounds(12, 87, 175, 35);

		jLabel17 = new JLabel(contrato.getCliente().getDocumento());
		contenedor.add(jLabel17);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel17.setBackground(new java.awt.Color(255, 255, 255));
		jLabel17.setForeground(new java.awt.Color(0, 64, 128));
		jLabel17.setOpaque(true);
		jLabel17.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jLabel17.setBounds(12, 122, 268, 32);

		jLabel18 = new JLabel();
		contenedor.add(jLabel18);
		jLabel18.setText("% TOTAL");
		jLabel18.setForeground(new java.awt.Color(0, 128, 0));
		jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel18.setBounds(12, 577, 131, 32);

		lblInteresTotal = new JLabel(String.valueOf(contrato.getInteresMensual().multiply(contrato.getCuotas())));
		contenedor.add(lblInteresTotal);
		lblInteresTotal.setOpaque(true);
		lblInteresTotal.setForeground(new java.awt.Color(0, 64, 128));
		lblInteresTotal.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresTotal.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresTotal.setBounds(143, 580, 120, 32);

		jLabel19 = new JLabel();
		contenedor.add(jLabel19);
		jLabel19.setText("M.PRORRATEO");
		jLabel19.setForeground(new java.awt.Color(0, 128, 0));
		jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel19.setBounds(281, 724, 139, 32);

		lblMoraProrrateo = new JLabel(String.valueOf(contrato.getProrrateoMora()));
		contenedor.add(lblMoraProrrateo);
		lblMoraProrrateo.setOpaque(true);
		lblMoraProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblMoraProrrateo.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraProrrateo.setBounds(426, 724, 120, 32);

		pnlSeguimientoContainer = new JPanel();
		contenedor.add(pnlSeguimientoContainer);
		pnlSeguimientoContainer.setBounds(558, 474, 683, 281);
		pnlSeguimientoContainer.setLayout(null);
		pnlSeguimientoContainer.setVisible(false);

		txtDetalleSeguimiento = new JTextArea();
		pnlSeguimientoContainer.add(txtDetalleSeguimiento);
		txtDetalleSeguimiento.setBounds(5, 59, 437, 222);
		txtDetalleSeguimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDetalleSeguimiento.setFont(new java.awt.Font("Segoe UI", 0, 20));

		btnGrabarSeguimiento = new JButton(new ImageIcon("img/grabar.png"));
		btnGrabarSeguimiento.setText(" GRABAR");
		pnlSeguimientoContainer.add(btnGrabarSeguimiento);
		pnlSeguimientoContainer.setOpaque(false);

		btnGrabarSeguimiento.setOpaque(false);
		btnGrabarSeguimiento.setBorderPainted(false);
		btnGrabarSeguimiento.setContentAreaFilled(false);
		btnGrabarSeguimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabarSeguimiento.setBounds(448, 131, 226, 64);
		btnGrabarSeguimiento.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabarSeguimiento.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrabarSeguimiento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utiles.Validar(pnlSeguimientoContainer)) {
					Seguimiento seguimiento = new Seguimiento();
					seguimiento.setFecha(LocalDate.now().toString());
					seguimiento.setObs(txtDetalleSeguimiento.getText());
					seguimiento.setTipo(rbgSeguimiento.getSelection().getActionCommand());
					seguimiento.setContrato(contrato);
					new ContratoController().GrabarSeguimiento(seguimiento);
				} else {
					Utiles.Mensaje("Ingrese detalle de la llamada.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnRegresar = new JButton(new ImageIcon("img/regresar.png"));
		btnRegresar.setText(" REGRESAR");
		btnRegresar.setOpaque(false);
		btnRegresar.setBorderPainted(false);
		btnRegresar.setContentAreaFilled(false);
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlSeguimientoContainer.add(btnRegresar);
		btnRegresar.setBounds(448, 217, 227, 64);
		btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnRegresar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOperacionContainer.setVisible(true);
				pnlSeguimientoContainer.setVisible(false);
			}
		});

		rbEntrante = new JRadioButton();
		pnlSeguimientoContainer.add(rbEntrante);
		rbgSeguimiento.add(rbEntrante);
		rbEntrante.setText("LLAMADA ENTRANTE");
		rbEntrante.setOpaque(false);
		rbEntrante.setBounds(448, 0, 224, 64);
		rbEntrante.setActionCommand("I");
		rbEntrante.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbEntrante.setForeground(new java.awt.Color(0, 128, 0));
		rbEntrante.setHorizontalAlignment(SwingConstants.CENTER);

		rbSaliente = new JRadioButton();
		rbSaliente.setOpaque(false);
		pnlSeguimientoContainer.add(rbSaliente);
		rbgSeguimiento.add(rbSaliente);
		rbSaliente.setText("LLAMADA SALIENTE");
		rbSaliente.setActionCommand("O");
		rbSaliente.setBounds(448, 71, 224, 64);
		rbSaliente.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbSaliente.setForeground(new java.awt.Color(0, 128, 0));
		rbSaliente.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel23 = new JLabel();
		pnlSeguimientoContainer.add(jLabel23);
		jLabel23.setText("RESULTADO DE LA LLAMADA");
		jLabel23.setForeground(new java.awt.Color(0, 128, 255));
		jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel23.setBounds(5, 12, 437, 32);

		rbPagoInteres = new JRadioButton();
		pnlOperacionContainer.add(rbPagoInteres);
		rbPagoInteres.setText("SOLO INTERESES");
		rbPagoInteres.setBounds(0, 59, 229, 32);
		rbPagoInteres.setOpaque(false);
		rbPagoInteres.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteres.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteres.setActionCommand("I");
		rbPagoInteres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR SÓLO INTERESES");
				lblTotalAPagar.setText(String.valueOf(contrato.getInteresTotal()));
			}
		});
		rbgOpcionPago.add(rbPagoInteres);

		rbPagoInteresMora = new JRadioButton();
		pnlOperacionContainer.add(rbPagoInteresMora);
		rbPagoInteresMora.setOpaque(false);
		rbPagoInteresMora.setText("INTERÉS + MORA");
		rbPagoInteresMora.setBounds(0, 106, 229, 32);
		rbPagoInteresMora.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteresMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteresMora.setActionCommand("IM");
		rbPagoInteresMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR INTERESES + MORA");
				BigDecimal total = contrato.getInteresTotal().add(contrato.getMoraTotal());
				lblTotalAPagar.setText(String.valueOf(total));
			}
		});
		rbgOpcionPago.add(rbPagoInteresMora);

		rbPagoMora = new JRadioButton();
		pnlOperacionContainer.add(rbPagoMora);
		rbPagoMora.setOpaque(false);
		rbPagoMora.setText("SOLO MORAS");
		rbPagoMora.setActionCommand("M");
		rbPagoMora.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoMora.setBounds(0, 154, 229, 32);
		rbPagoMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR SÓLO MORA");
				lblTotalAPagar.setText(String.valueOf(contrato.getMoraTotal()));

			}
		});
		rbgOpcionPago.add(rbPagoMora);

		rbAbonarCapital = new JRadioButton();
		pnlOperacionContainer.add(rbAbonarCapital);
		rbAbonarCapital.setText("ABONAR AL CAPITAL");
		rbAbonarCapital.setOpaque(false);
		rbAbonarCapital.setActionCommand("ABN");
		rbAbonarCapital.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbAbonarCapital.setForeground(new java.awt.Color(128, 0, 128));
		rbAbonarCapital.setBounds(0, 201, 229, 32);
		rbAbonarCapital.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int e = arg0.getStateChange();
				if (e == ItemEvent.SELECTED) {
					lblTotalAPagar.setVisible(false);
					txtAbono.setVisible(true);
					txtAbono.requestFocus();
				} else {
					lblCapital.setText(String.valueOf(contrato.getCapital()));
					lblInteresMensual.setText(String.valueOf(contrato.getInteresMensual()));
					lblTotalAPagar.setVisible(true);
					txtAbono.setVisible(false);
					txtAbono.setText("");
				}
			}
		});
		rbAbonarCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("ABONAR AL CAPITAL");
				btnPagar.repaint();
			}
		});
		rbgOpcionPago.add(rbAbonarCapital);

		rbCancelarContrato = new JRadioButton();
		pnlOperacionContainer.add(rbCancelarContrato);
		rbCancelarContrato.setText("CANCELAR TODO");
		rbCancelarContrato.setOpaque(false);
		rbCancelarContrato.setBounds(0, 249, 229, 32);
		rbCancelarContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbCancelarContrato.setForeground(new java.awt.Color(128, 0, 128));
		rbCancelarContrato.setActionCommand("PAG");
		rbCancelarContrato.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int e = arg0.getStateChange();
				if (e == ItemEvent.SELECTED) {
					tbIntereses.setEnabled(false);
					tbMoras.setEnabled(false);
				} else {
					tbIntereses.setEnabled(true);
					tbMoras.setEnabled(true);
				}

				btnPagar.repaint();
			}
		});
		rbCancelarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR TODO");
				CalcularInteres();
				CalcularMoras();
				BigDecimal total = contrato.getCapital().add(contrato.getInteresTotal()).add(contrato.getMoraTotal())
						.add(contrato.getProrrateo()).add(contrato.getProrrateoMora());
				lblTotalAPagar.setText(String.valueOf(total));
			}
		});
		rbgOpcionPago.add(rbCancelarContrato);

		lblTotalAPagar = new JLabel();
		pnlOperacionContainer.add(lblTotalAPagar);
		lblTotalAPagar.setBounds(222, 57, 404, 128);
		lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalAPagar.setVerticalTextPosition(SwingConstants.TOP);
		lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalAPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalAPagar.setOpaque(true);
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalAPagar.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTotalAPagar.setBackground(new java.awt.Color(234, 255, 255));

		btnSeguimiento = new JButton(new ImageIcon("img/seguimiento.png"));
		pnlOperacionContainer.add(btnSeguimiento);
		btnSeguimiento.setOpaque(false);
		btnSeguimiento.setBorderPainted(false);
		btnSeguimiento.setContentAreaFilled(false);
		btnSeguimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSeguimiento.setText("SEGUIMIENTO");
		btnSeguimiento.setBounds(222, 201, 218, 75);
		btnSeguimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnSeguimiento.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnSeguimiento.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeguimiento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOperacionContainer.setVisible(false);
				pnlSeguimientoContainer.setVisible(true);
			}
		});

		jLabel21 = new JLabel();
		pnlOperacionContainer.add(jLabel21);
		jLabel21.setText("TOTAL A PAGAR");
		jLabel21.setForeground(new java.awt.Color(0, 128, 255));
		jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel21.setBounds(222, 12, 461, 35);

		jLabel22 = new JLabel();
		pnlOperacionContainer.add(jLabel22);
		jLabel22.setText("OPERACIÓN");
		jLabel22.setForeground(new java.awt.Color(0, 128, 255));
		jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel22.setBounds(0, 9, 222, 40);

		btnPagar = new JButton(new ImageIcon("img/pagar.png"));
		pnlOperacionContainer.add(btnPagar);
		pnlOperacionContainer.moveToFront(btnPagar);
		btnPagar.setBounds(554, 57, 128, 128);
		btnPagar.setOpaque(false);
		btnPagar.setBorderPainted(false);
		btnPagar.setContentAreaFilled(false);
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPagar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnPagar.setHorizontalAlignment(SwingConstants.LEFT);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null, "<html><h2>¿Confirma <strong style='color:red'>"
						+ contrato.getOperacion() + "</strong>?</h2></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					Pagar();
				}
			}
		});

		CargarInformacionContrato();

	}

	public void CargarInformacionContrato() {
		CargarDetalleContrato();
		CalcularInteres();
		CalcularMoras();
		CargarPagos();
		CargarAbonos();
		CargarSeguimiento();
	}

	public void CargarDetalleContrato() {
		Constantes.ContratoModel.setRowCount(0);
		for (DetalleContrato dc : contrato.getDetalleContratos()) {
			Constantes.ContratoModel.addRow(new Object[] { dc.getArticulo().getId(), dc.getArticulo().getDescripcion(),
					dc.getArticulo().getMarca(), dc.getArticulo().getModelo(), dc.getArticulo().getObs(),
					dc.getTasacion().setScale(2, RoundingMode.HALF_UP) });
		}
		tbArticulos.setModel(Constantes.ContratoModel);
	}

	public void CargarSeguimiento() {
		Constantes.SeguimientoModel.setRowCount(0);
		for (Seguimiento s : contrato.getSeguimientos()) {
			Constantes.SeguimientoModel.addRow(new Object[] { s.getTipo(),
					Constantes.formatoLocal.format(LocalDate.parse(s.getFecha())), s.getObs() });
		}
		tbSeguimiento.setModel(Constantes.SeguimientoModel);
	}

	public void CargarPagos() {
		Constantes.PagoModel.setRowCount(0);
		for (Pago p : contrato.getPagos()) {
			Constantes.PagoModel.addRow(new Object[] {
					Constantes.formatoLocal.format(LocalDate.parse(p.getFechaVencimiento())).toUpperCase(),
					Constantes.formatoLocal.format(LocalDate.parse(p.getFechaPago())).toUpperCase(), p.getDescripcion(),
					p.getInteres(), p.getMora(), p.getInteres().add(p.getMora()) });
		}
		tbPagos.setModel(Constantes.PagoModel);
	}

	public void CalcularInteres() {
		try {
			BigDecimal total_interes = BigDecimal.ZERO;
			LocalDate fecha_vencimiento = LocalDate.parse(contrato.getFechaVencimiento());
			InteresModel.setRowCount(0);
			for (int i = 0; i < contrato.getCuotas().intValue(); i++) {
				InteresModel.addRow(new Object[] { Constantes.formatoMes.format(fecha_vencimiento).toUpperCase(),
						contrato.getInteresMensual(), 1 });
				fecha_vencimiento = fecha_vencimiento.plusMonths(1);
				total_interes = total_interes.add(contrato.getInteresMensual());
			}
			contrato.setInteresTotal(total_interes);
			tbIntereses.setModel(InteresModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CalcularMoras() {
		try {
			BigDecimal mora_anterior = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
			MoraModel.setRowCount(0);
			for (Mora m : contrato.getMoras()) {
				if (m.getStatus() == 1) {
					MoraModel.addRow(new Object[] { m.getId(),
							Constantes.formatoLocal.format(LocalDate.parse(m.getFechaVencimiento())).toUpperCase(),
							Constantes.formatoLocal.format(LocalDate.parse(m.getFechaMora())).toUpperCase(),
							m.getImporte(), m.getStatus() });
					mora_anterior = mora_anterior.add(m.getImporte());
				}
			}
			contrato.setMoraAnterior(mora_anterior);
			contrato.setMoraTotal(contrato.getMoraActual().add(contrato.getMoraAnterior()));
			lblMoraAnterior.setText(String.valueOf(mora_anterior));
			lblTotalMora.setText(String.valueOf(contrato.getMoraTotal()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CargarAbonos() {
		AbonoModel.setRowCount(0);
		for (Abono a : contrato.getAbonos()) {
			AbonoModel
					.addRow(new Object[] { Constantes.formatoLocal.format(LocalDate.parse(a.getFecha())).toUpperCase(),
							a.getArqCapital(), a.getArqInteres(), a.getArqCapital().subtract(a.getNeoCapital()),
							a.getNeoCapital(), a.getNeoInteres() });
		}
	}

	public int CalcularRenovacion() {
		int meses = 0;
		for (int i = 0; i <= InteresModel.getRowCount() - 1; i++) {
			int estado = Integer.parseInt(InteresModel.getValueAt(i, 2).toString());
			if (estado == 1) {
				meses += 1;
			}
		}
		return meses;
	}

	public void PagarMoras() {
		for (int i = 0; i < MoraModel.getRowCount(); i++) {
			int status = Integer.parseInt(String.valueOf(MoraModel.getValueAt(i, 4)));
			if (status == 1) {
				int id = Integer.parseInt(String.valueOf(MoraModel.getValueAt(i, 0)));
				for (Mora m : contrato.getMoras()) {
					if (m.getId() == id) {
						m.setStatus(0);
					}
				}
			}
		}

	}

	public void Pagar() {
		try {
			boolean proceed = false;

			Pago pago = new Pago();

			Ingreso ingreso = new Ingreso();
			ingreso.setLibroCaja(Principal.LIBRO_CAJA);
			ingreso.setDescripcion(contrato.getFlag() + "-" + contrato.getNumero());

			int meses_renovar = CalcularRenovacion();

			LocalDate nuevo_vencimiento = LocalDate.parse(contrato.getFechaVencimiento()).plusMonths(meses_renovar);
			LocalDate nuevo_remate = LocalDate.parse(contrato.getFechaRemate()).plusMonths(meses_renovar);

			switch (rbgOpcionPago.getSelection().getActionCommand()) {
			case "I":
				pago.setDescripcion(meses_renovar + "%");
				pago.setCapital(BigDecimal.ZERO);
				pago.setInteres(contrato.getInteresTotal());
				pago.setMora(BigDecimal.ZERO);
				pago.setContrato(contrato);
				pago.setFechaVencimiento(contrato.getFechaVencimiento());
				pago.setFechaPago(LocalDate.now().toString());
				contrato.addPago(pago);

				ingreso.setCapital(BigDecimal.ZERO);
				ingreso.setGanancia(contrato.getInteresTotal());
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo(pago.getDescripcion());

				if (contrato.getMoraActual().compareTo(BigDecimal.ZERO) == 1) {
					Mora mora = new Mora();
					mora.setFechaVencimiento(contrato.getFechaVencimiento());
					mora.setFechaMora(String.valueOf(LocalDate.now()));
					mora.setImporte(contrato.getMoraActual());
					mora.setStatus(1);
					mora.setContrato(contrato);
					contrato.addMora(mora);
				}

				contrato.setFechaVencimiento(String.valueOf(nuevo_vencimiento));
				contrato.setFechaRemate(String.valueOf(nuevo_remate));

				Actualizar_Contratos.DetectarEstado(contrato);

				Utiles.Mensaje("Próximo Vencimiento : " + Constantes.formatoLocal.format(nuevo_vencimiento),
						JOptionPane.INFORMATION_MESSAGE);

				proceed = true;

				break;
			case "M":
				PagarMoras();

				pago.setDescripcion("MORA");
				pago.setCapital(BigDecimal.ZERO);
				pago.setInteres(BigDecimal.ZERO);
				pago.setMora(contrato.getMoraTotal());
				pago.setContrato(contrato);
				pago.setFechaVencimiento(contrato.getFechaVencimiento());
				pago.setFechaPago(LocalDate.now().toString());
				contrato.addPago(pago);

				ingreso.setCapital(BigDecimal.ZERO);
				ingreso.setGanancia(contrato.getMoraTotal());
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo("M");

				Utiles.Mensaje("Mora(s) pagada(s). Sin modificaciones en el estado del Contrato.",
						JOptionPane.INFORMATION_MESSAGE);

				proceed = true;

				break;
			case "IM":
				pago.setDescripcion(meses_renovar + "% + MORA");
				pago.setCapital(BigDecimal.ZERO);
				pago.setInteres(contrato.getInteresTotal());
				pago.setMora(contrato.getMoraTotal());
				pago.setFechaVencimiento(contrato.getFechaVencimiento());
				pago.setFechaPago(LocalDate.now().toString());
				pago.setContrato(contrato);
				contrato.addPago(pago);

				ingreso.setCapital(BigDecimal.ZERO);
				ingreso.setGanancia(contrato.getInteresTotal().add(contrato.getMoraTotal()));
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo(meses_renovar + "% + M");

				PagarMoras();

				contrato.setFechaVencimiento(String.valueOf(nuevo_vencimiento));
				contrato.setFechaRemate(String.valueOf(nuevo_remate));

				Actualizar_Contratos.DetectarEstado(contrato);

				Utiles.Mensaje("Próximo Vencimiento : " + Constantes.formatoLocal.format(nuevo_vencimiento),
						JOptionPane.INFORMATION_MESSAGE);

				proceed = true;

				break;
			case "ABN":
				if (Utiles.Validar(contenedor)) {
					Pago ultimoPago = Collections.max(contrato.getPagos(), Constantes.PagoComparator);

					long limite = ChronoUnit.DAYS.between(LocalDate.parse(ultimoPago.getFechaPago()), LocalDate.now());

					if (limite <= 5) {
						Abono abono = new Abono();
						abono.setFecha(String.valueOf(LocalDate.now()));
						abono.setArqCapital(contrato.getCapital());
						abono.setArqInteres(contrato.getInteresMensual());

						abono.setNeoCapital(new BigDecimal(lblCapital.getText()));
						abono.setNeoInteres(new BigDecimal(lblInteresMensual.getText()));
						abono.setContrato(contrato);
						contrato.addAbono(abono);

						ingreso.setCapital(new BigDecimal(txtAbono.getText()));
						ingreso.setGanancia(BigDecimal.ZERO);
						ingreso.setOtro(BigDecimal.ZERO);
						ingreso.setTipo("ABN");

						contrato.setCapital(abono.getNeoCapital());
						contrato.setInteresMensual(abono.getNeoInteres());

						Utiles.Mensaje("Nuevo Capital: " + abono.getNeoCapital() + "<br/>" + "Nuevo Interés : "
								+ abono.getNeoInteres(), JOptionPane.INFORMATION_MESSAGE);

						proceed = true;
					} else {
						Utiles.Mensaje(
								"Fuera de plazo. <b>5 días</b> máximo desde última fecha de pago. Han transcurrido "
										+ limite + " días",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					Utiles.Mensaje("Ingrese el importe.", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				pago.setDescripcion("CANCELACIÓN");
				pago.setCapital(contrato.getCapital());
				pago.setInteres(contrato.getInteresTotal().add(contrato.getProrrateo()));
				pago.setMora(contrato.getMoraTotal().add(contrato.getProrrateoMora()));
				pago.setFechaVencimiento(contrato.getFechaVencimiento());
				pago.setFechaPago(LocalDate.now().toString());
				pago.setContrato(contrato);
				contrato.addPago(pago);

				ingreso.setCapital(contrato.getCapital());
				ingreso.setGanancia(contrato.getInteresTotal()
						.add(contrato.getMoraTotal().add(contrato.getProrrateo()).add(contrato.getProrrateoMora())));
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo("PAG");

				contrato.setEContrato(new EContrato(6));

				Utiles.Mensaje("Contrato cancelado.", JOptionPane.INFORMATION_MESSAGE);

				proceed = true;

				break;
			}

			if (proceed) {
				new ContratoController().GestionarContrato(contrato, ingreso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
