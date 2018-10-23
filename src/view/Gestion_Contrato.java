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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import common.ComboItem;
import common.Constantes;
import common.EditorC;
import common.EditorIM;
import common.JIconTextField;
import common.JTranslucentPane;
import common.RenderC;
import common.RenderHC;
import common.RenderIM;
import common.RenderIO;
import common.Utiles;
import controller.ArticuloController;
import controller.CargoController;
import controller.ContratoController;
import model.Abono;
import model.Articulo;
import model.Cargo;
import model.Contrato;
import model.DetalleCargo;
import model.DetalleContrato;
import model.EArticulo;
import model.EContrato;
import model.Ingreso;
import model.Mora;
import model.Pago;
import model.Sede;
import model.Seguimiento;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Gestion_Contrato extends JInternalFrame {
	private JLabel lblEstado;
	private JTabbedPane tpContrato;
	private JTable tbContrato;
	private JLabel lblTotalMora;
	private JLabel jLabel15;
	private JLabel lblMoraActual;
	private JLabel lblMoraAnterior;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel lblDiasExcedidos;
	private JLabel jLabel11;
	private JLabel lblMoraSiNo;
	private JLabel cboMora;
	private JLabel jLabel10;
	private JLabel lblCapital;
	private JComboBox cboAlmacen;
	private JButton btnCargo;
	private JLabel lblId3;
	private JLabel lblPorcentajeInteres;
	private JLabel jLabel2;
	private JLabel lblId2;
	private JLabel lblId1;
	private JRadioButton rbSaliente;
	private JRadioButton rbEntrante;
	private ButtonGroup rbgSeguimiento;
	private JButton btnRegresar;
	private JButton btnGrabarSeguimiento;
	private JTextArea txtDetalleSeguimiento;
	private JLayeredPane pnlOperacionContainer;
	private JPanel pnlSeguimientoContainer;
	private JPanel pnlCargoContainer;
	private JLabel lblMoraProrrateo;
	private JLabel jLabel19;
	private JLabel lblInteresTotal;
	private JLabel jLabel18;
	private JButton btnSeguimiento;
	private JTextField txtAbono;
	private JRadioButton rbPagoMora;
	private JRadioButton rbAbonarCapital;
	private JLabel jLabel17;
	private JLabel jLabel5;
	private JLabel lblProrrateo;
	private JLabel jLabel14;
	private JLabel lblInteresMensual;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JPanel pnlArticulos;
	private JPanel pnlMoras;
	private JPanel pnlPagos;
	private JPanel pnlCargos;
	private JPanel pnlSeguimiento;
	private JLabel lblRemate;
	private JLabel lblVencimiento;
	private JLabel lblInicio;
	private JLabel lblNumeroContrato;
	private JPanel contenedor;
	private JRadioButton rbCancelarContrato;
	private JRadioButton rbPagoInteresMora;
	private ButtonGroup rbgOpcionPago;
	private JRadioButton rbPagoInteres;
	private JLabel lblTipoMoneda;
	private JTable tbAbonos;
	private JScrollPane spAbonos;
	private JLabel lblId;
	private JPanel pnlCalculos;
	private JEditorPane edpMensajeSuceso;
	private JScrollPane spDetalleSeguimiento;
	private JButton btnRegresar2;
	private JButton btnGrabarCargo;
	private JTextArea txtObsCargo;
	private JLabel lblId5;
	private JScrollPane spDetalleCargo;
	private JIconTextField txtTransportista;
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
	private JPanel pnlHistorial;
	private JScrollPane spHistorial;
	private JTable tbHistorial;

	TableColumn cargoColumn;

	Contrato contrato;

	DefaultTableModel MoraModel = new DefaultTableModel(null,
			new String[] { "ID", "FECHA VENCIMIENTO", "FECHA MORA", "MONTO", "�ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbMoras.getSelectedRow() && colIndex == 4) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel InteresModel = new DefaultTableModel(null, new String[] { "MES", "MONTO", "�ACTIVO?" }) {
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
	private JTranslucentPane pnlSucesos;
	private JTextField txtTipoPrestamo;

	public Gestion_Contrato() {
		try {
			String c = JOptionPane.showInputDialog(null,
					"<html><h3>Ingrese n�mero del Contrato a renovar ...</h3></html>");
			if (Objects.nonNull(c)) {
				String flag = String.valueOf(c.split("-")[0]);
				int numero = Integer.parseInt(String.valueOf(c.split("-")[1]));
				Contrato contrato = new ContratoController().CargarContrato(flag.toUpperCase(), numero);
				if (Objects.nonNull(contrato)) {
					Principal.dskPrincipal.add(new Gestion_Contrato(contrato));
				} else {
					Utiles.Mensaje("Contrato no existe. Verifique.", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Gestion_Contrato(Contrato c) throws ParseException {
		contrato = c;
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new java.awt.Dimension(1300, 710));
		this.setBounds(0, 0, 1276, 721);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1275, 696);
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
		lblEstado.setBounds(259, 11, 191, 32);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 18));
		lblEstado.setOpaque(true);
		lblEstado.setBackground(contrato.getEContrato().getBackground());
		lblEstado.setForeground(contrato.getEContrato().getForeground());
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

		lblNumeroContrato = new JLabel(contrato.getFlag() + "-" + String.valueOf(contrato.getNumero()));
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(12, 11, 235, 70);
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 60));
		lblNumeroContrato.setOpaque(true);
		lblNumeroContrato.setForeground(new java.awt.Color(0, 0, 255));
		lblNumeroContrato.setHorizontalAlignment(SwingConstants.CENTER);

		lblInicio = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaContrato())).toUpperCase());
		contenedor.add(lblInicio);
		lblInicio.setBounds(462, 11, 185, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInicio.setBackground(Color.WHITE);
		lblInicio.setOpaque(true);
		lblInicio.setBackground(new java.awt.Color(240, 145, 4));
		lblInicio.setForeground(new java.awt.Color(255, 255, 255));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);

		lblVencimiento = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaVencimiento())).toUpperCase());
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(659, 11, 185, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblVencimiento.setBackground(Color.WHITE);
		lblVencimiento.setBackground(new java.awt.Color(200, 68, 4));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setForeground(new java.awt.Color(255, 255, 255));
		lblVencimiento.setHorizontalAlignment(SwingConstants.CENTER);

		lblRemate = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaRemate())).toUpperCase());
		contenedor.add(lblRemate);
		lblRemate.setBounds(856, 11, 185, 32);
		lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblRemate.setBackground(new java.awt.Color(255, 0, 0));
		lblRemate.setOpaque(true);
		lblRemate.setForeground(new java.awt.Color(255, 255, 255));
		lblRemate.setHorizontalAlignment(SwingConstants.CENTER);

		tpContrato = new JTabbedPane();
		contenedor.add(tpContrato);
		tpContrato.setBounds(12, 110, 1226, 223);
		tpContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));
		tpContrato.setForeground(new java.awt.Color(0, 0, 0));

		pnlArticulos = new JPanel();
		spArticulos = new JScrollPane();
		spArticulos.setPreferredSize(new java.awt.Dimension(1200, 170));
		pnlArticulos.setPreferredSize(new java.awt.Dimension(1224, 189));
		tbContrato = new JTable();
		tbContrato.setDefaultRenderer(Object.class, new RenderC());
		tbContrato.setDefaultEditor(Object.class, new EditorC());
		tbContrato.setRowHeight(25);
		tbContrato.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContrato.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContrato.getTableHeader().setForeground(new Color(181, 0, 0));
		tbContrato.setModel(Constantes.DetalleContratoModel);
		spArticulos.setViewportView(tbContrato);
		pnlArticulos.add(spArticulos);
		cargoColumn = tbContrato.getColumnModel().getColumn(6);
		tbContrato.getColumnModel().removeColumn(cargoColumn);
		tpContrato.addTab("ART�CULOS", null, pnlArticulos, null);

		pnlInteres = new JPanel();
		spIntereses = new JScrollPane();
		tbIntereses = new JTable();
		tbIntereses.setDefaultRenderer(Object.class, new RenderIM());
		tbIntereses.setDefaultEditor(Object.class, new EditorIM());
		tbIntereses.setRowHeight(25);
		tbIntereses.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbIntereses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbIntereses.setModel(InteresModel);
		tbIntereses.getTableHeader().setForeground(new Color(181, 0, 0));
		spIntereses.setPreferredSize(new java.awt.Dimension(1200, 170));
		spIntereses.setViewportView(tbIntereses);
		pnlInteres.add(spIntereses);
		tpContrato.addTab("INTERESES", null, pnlInteres, null);

		pnlPagos = new JPanel();
		spPagos = new JScrollPane();
		tbPagos = new JTable();
		tbPagos.setRowHeight(25);
		tbPagos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbPagos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbPagos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbPagos.setModel(Constantes.PagoModel);
		spPagos.setPreferredSize(new java.awt.Dimension(1200, 170));
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
		tbMoras.setRowHeight(25);
		tbMoras.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbMoras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbMoras.getTableHeader().setForeground(new Color(181, 0, 0));
		spMoras.setPreferredSize(new java.awt.Dimension(1200, 170));
		spMoras.setViewportView(tbMoras);
		pnlMoras.add(spMoras);
		tpContrato.addTab("MORAS", null, pnlMoras, null);

		pnlAbonos = new JPanel();
		spAbonos = new JScrollPane();
		tbAbonos = new JTable();
		tbAbonos.setModel(Constantes.AbonoModel);
		tbAbonos.setRowHeight(25);
		tbAbonos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbAbonos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbAbonos.getTableHeader().setForeground(new Color(181, 0, 0));
		spAbonos.setPreferredSize(new java.awt.Dimension(1200, 170));
		spAbonos.setViewportView(tbAbonos);
		pnlAbonos.add(spAbonos);
		tpContrato.addTab("ABONOS", null, pnlAbonos, null);

		pnlCargos = new JPanel();
		spCargos = new JScrollPane();
		tbCargos = new JTable();
		tbCargos.setModel(Constantes.CargoModel);
		tbCargos.setRowHeight(25);
		tbCargos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbCargos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
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
		tbSeguimiento.setRowHeight(25);
		tbSeguimiento.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbSeguimiento.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbSeguimiento.getTableHeader().setForeground(new Color(181, 0, 0));
		spSeguimiento.setPreferredSize(new java.awt.Dimension(1204, 229));
		spSeguimiento.setViewportView(tbSeguimiento);
		pnlSeguimiento.add(spSeguimiento);
		tpContrato.addTab("SEGUIMIENTO", null, pnlSeguimiento, null);

		pnlHistorial = new JPanel();
		spHistorial = new JScrollPane();
		spHistorial.setPreferredSize(new java.awt.Dimension(1200, 170));
		pnlHistorial.setPreferredSize(new java.awt.Dimension(1224, 264));
		tbHistorial = new JTable();
		tbHistorial.setDefaultRenderer(Object.class, new RenderHC());
		tbHistorial.setRowHeight(25);
		tbHistorial.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbHistorial.getTableHeader().setForeground(new Color(181, 0, 0));
		tbHistorial.setModel(Constantes.HistorialModel);
		spHistorial.setViewportView(tbHistorial);
		pnlHistorial.add(spHistorial);
		tpContrato.addTab("HISTORIAL", null, pnlHistorial, null);

		pnlOperacionContainer = new JLayeredPane();
		pnlOperacionContainer.setVisible(true);
		contenedor.add(pnlOperacionContainer);
		pnlOperacionContainer.setLayout(null);
		pnlOperacionContainer.setBounds(555, 339, 683, 285);

		pnlSucesos = new JTranslucentPane();
		pnlSucesos.setAlpha(0.85f);
		pnlSucesos.setBounds(0, 0, 682, 277);
		pnlSucesos.setBackground(new Color(0, 0, 0, 140));
		pnlSucesos.setVisible(false);
		pnlSucesos.setLayout(null);
		pnlOperacionContainer.add(pnlSucesos);

		pnlCalculos = new JPanel();
		contenedor.add(pnlCalculos);
		pnlCalculos.setLayout(null);
		pnlCalculos.setOpaque(false);
		pnlCalculos.setBounds(0, 0, 1275, 685);

		jLabel8 = new JLabel();
		pnlCalculos.add(jLabel8);
		jLabel8.setText("CAPITAL");
		jLabel8.setBounds(12, 339, 131, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblCapital = new JLabel(String.valueOf(contrato.getCapital()));
		pnlCalculos.add(lblCapital);
		lblCapital.setBounds(125, 339, 120, 32);
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblCapital.setBackground(Color.WHITE);
		lblCapital.setOpaque(true);
		lblCapital.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		pnlCalculos.add(jLabel10);
		jLabel10.setText("�GENERA MORA?");
		jLabel10.setBounds(264, 339, 162, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraSiNo = new JLabel(contrato.getMoraRespuesta());
		pnlCalculos.add(lblMoraSiNo);
		lblMoraSiNo.setBounds(426, 339, 120, 32);
		lblMoraSiNo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraSiNo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblMoraSiNo.setBackground(Color.WHITE);
		lblMoraSiNo.setOpaque(true);

		cboMora = new JLabel(
				contrato.getMoraPorcentaje().multiply(new BigDecimal(100).setScale(0, RoundingMode.HALF_UP)) + "%");
		pnlCalculos.add(cboMora);
		cboMora.setBounds(264, 385, 282, 77);
		cboMora.setOpaque(true);
		cboMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboMora.setFont(new java.awt.Font("Segoe UI", 1, 60));
		cboMora.setForeground(Color.WHITE);
		cboMora.setBackground(Color.WHITE);
		cboMora.setForeground(new java.awt.Color(0, 64, 128));
		cboMora.setHorizontalAlignment(SwingConstants.CENTER);

		lblMoraActual = new JLabel(String.valueOf(contrato.getMoraActual()));
		pnlCalculos.add(lblMoraActual);
		lblMoraActual.setBounds(426, 474, 120, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblMoraActual.setBackground(Color.WHITE);
		lblMoraActual.setOpaque(true);
		lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));

		jLabel13 = new JLabel();
		pnlCalculos.add(jLabel13);
		jLabel13.setText("MORA ACTUAL");
		jLabel13.setBounds(264, 480, 123, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		jLabel12 = new JLabel();
		pnlCalculos.add(jLabel12);
		jLabel12.setText("MORA PASADA");
		jLabel12.setBounds(264, 529, 123, 32);
		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel12.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraAnterior = new JLabel("0.00");
		pnlCalculos.add(lblMoraAnterior);
		lblMoraAnterior.setBounds(426, 523, 120, 32);
		lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblMoraAnterior.setBackground(Color.WHITE);
		lblMoraAnterior.setOpaque(true);
		lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));

		lblTotalMora = new JLabel();
		pnlCalculos.add(lblTotalMora);
		lblTotalMora.setText("0.00");
		lblTotalMora.setBounds(426, 576, 120, 32);
		lblTotalMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalMora.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblTotalMora.setBackground(Color.WHITE);
		lblTotalMora.setOpaque(true);
		lblTotalMora.setForeground(new java.awt.Color(0, 64, 128));

		jLabel15 = new JLabel();
		pnlCalculos.add(jLabel15);
		jLabel15.setText("MORA TOTAL");
		jLabel15.setBounds(264, 578, 123, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel15.setForeground(new java.awt.Color(0, 128, 0));

		jLabel19 = new JLabel();
		pnlCalculos.add(jLabel19);
		jLabel19.setText("MORA PRORRATEO");
		jLabel19.setForeground(new java.awt.Color(0, 128, 0));
		jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel19.setBounds(264, 627, 156, 32);

		lblMoraProrrateo = new JLabel(String.valueOf(contrato.getProrrateoMora()));
		pnlCalculos.add(lblMoraProrrateo);
		lblMoraProrrateo.setOpaque(true);
		lblMoraProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblMoraProrrateo.setBackground(Color.WHITE);
		lblMoraProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblMoraProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblMoraProrrateo.setBounds(426, 627, 120, 32);

		jLabel7 = new JLabel();
		pnlCalculos.add(jLabel7);
		jLabel7.setText("INTER�S");
		jLabel7.setBounds(12, 431, 131, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresMensual = new JLabel(String.valueOf(contrato.getInteresMensual()));
		pnlCalculos.add(lblInteresMensual);
		lblInteresMensual.setBounds(125, 431, 120, 32);
		lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblInteresMensual.setBackground(Color.WHITE);
		lblInteresMensual.setOpaque(true);
		lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));

		lblInteresTotal = new JLabel(String.valueOf(contrato.getInteresMensual().multiply(contrato.getCuotas())));
		pnlCalculos.add(lblInteresTotal);
		lblInteresTotal.setOpaque(true);
		lblInteresTotal.setForeground(new java.awt.Color(0, 64, 128));
		lblInteresTotal.setBackground(Color.WHITE);
		lblInteresTotal.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblInteresTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresTotal.setBounds(125, 480, 120, 32);

		jLabel18 = new JLabel();
		pnlCalculos.add(jLabel18);
		jLabel18.setText("TOTAL");
		jLabel18.setForeground(new java.awt.Color(0, 128, 0));
		jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel18.setBounds(12, 480, 131, 32);

		jLabel11 = new JLabel();
		pnlCalculos.add(jLabel11);
		jLabel11.setText("DIAS EXTRA");
		jLabel11.setBounds(12, 529, 131, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		lblDiasExcedidos = new JLabel(String.valueOf(contrato.getDiasResiduo()));
		pnlCalculos.add(lblDiasExcedidos);
		lblDiasExcedidos.setBounds(125, 529, 120, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblDiasExcedidos.setBackground(Color.WHITE);
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));

		lblInteresDiario = new JLabel(String.valueOf(contrato.getInteresDiario()));
		pnlCalculos.add(lblInteresDiario);
		lblInteresDiario.setBounds(125, 578, 120, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblInteresDiario.setBackground(Color.WHITE);
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		pnlCalculos.add(jLabel16);
		jLabel16.setText("% DIARIO");
		jLabel16.setBounds(12, 578, 131, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		jLabel14 = new JLabel();
		pnlCalculos.add(jLabel14);
		jLabel14.setText("PRORRATEO");
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));
		jLabel14.setBounds(12, 627, 131, 32);

		lblProrrateo = new JLabel(String.valueOf(contrato.getProrrateo()));
		pnlCalculos.add(lblProrrateo);
		lblProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblProrrateo.setBackground(Color.WHITE);
		lblProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblProrrateo.setOpaque(true);
		lblProrrateo.setBounds(125, 627, 120, 32);

		jLabel2 = new JLabel();
		pnlCalculos.add(jLabel2);
		jLabel2.setText("% MENSUAL");
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		jLabel2.setBounds(12, 387, 113, 32);

		lblPorcentajeInteres = new JLabel(String.valueOf(contrato.getPrestamo().getInteres().intValue()));
		pnlCalculos.add(lblPorcentajeInteres);
		lblPorcentajeInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblPorcentajeInteres.setBackground(Color.WHITE);
		lblPorcentajeInteres.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblPorcentajeInteres.setForeground(new java.awt.Color(0, 64, 128));
		lblPorcentajeInteres.setOpaque(true);
		lblPorcentajeInteres.setBounds(125, 385, 120, 32);

		lblId = new JLabel(String.valueOf(contrato.getId()));
		contenedor.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblId.setBackground(Color.WHITE);
		lblId.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblId.setForeground(new java.awt.Color(0, 64, 128));
		lblId.setOpaque(true);
		lblId.setBounds(1104, 10, 134, 32);

		lblTipoMoneda = new JLabel(contrato.getMoneda());
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(1063, 50, 175, 32);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("ID");
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		jLabel5.setBounds(1063, 13, 35, 30);

		lblMoraSiNo.setForeground(contrato.getMoraColor());

		lblCliente = new JLabel(contrato.getCliente().getNombres() + " " + contrato.getCliente().getPaterno() + " "
				+ contrato.getCliente().getMaterno());
		contenedor.add(lblCliente);
		lblCliente.setBounds(659, 50, 382, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblCliente.setBackground(Color.WHITE);
		lblCliente.setOpaque(true);
		lblCliente.setForeground(new java.awt.Color(0, 64, 128));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 94, 1226, 10);

		txtAbono = new JTextField();
		pnlOperacionContainer.add(txtAbono);
		pnlOperacionContainer.moveToBack(txtAbono);
		txtAbono.setVisible(false);
		txtAbono.setBounds(222, 57, 330, 128);
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

		txtTipoPrestamo = new JTextField(contrato.getPrestamo().getDescripcion());
		contenedor.add(txtTipoPrestamo);
		txtTipoPrestamo.setBounds(259, 49, 191, 32);
		txtTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTipoPrestamo.setForeground(new java.awt.Color(0, 64, 128));
		txtTipoPrestamo.setOpaque(true);
		txtTipoPrestamo.setEnabled(false);
		txtTipoPrestamo.setHorizontalAlignment(SwingConstants.CENTER);

		rbgOpcionPago = new ButtonGroup();
		rbgSeguimiento = new ButtonGroup();

		jLabel17 = new JLabel(contrato.getCliente().getDocumento());
		contenedor.add(jLabel17);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel17.setBackground(Color.WHITE);
		jLabel17.setForeground(new java.awt.Color(0, 64, 128));
		jLabel17.setOpaque(true);
		jLabel17.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jLabel17.setBounds(462, 50, 185, 32);
		jLabel17.setHorizontalAlignment(SwingConstants.CENTER);

		pnlSeguimientoContainer = new JPanel();
		contenedor.add(pnlSeguimientoContainer);
		pnlSeguimientoContainer.setBounds(555, 339, 683, 285);
		pnlSeguimientoContainer.setLayout(null);
		pnlSeguimientoContainer.setVisible(false);

		pnlCargoContainer = new JPanel();
		contenedor.add(pnlCargoContainer);
		pnlCargoContainer.setBounds(555, 339, 683, 285);
		pnlCargoContainer.setLayout(null);
		pnlCargoContainer.setOpaque(false);
		pnlCargoContainer.setVisible(false);

		edpMensajeSuceso = new JEditorPane("text/html", "");
		pnlSucesos.add(edpMensajeSuceso);
		edpMensajeSuceso.setOpaque(false);
		edpMensajeSuceso.setEditable(false);
		edpMensajeSuceso.setBackground(null);
		edpMensajeSuceso.setBounds(0, 0, 682, 277);
		edpMensajeSuceso.setFont(new java.awt.Font("Segoe UI", 1, 42));
		edpMensajeSuceso.setForeground(Color.WHITE);

		cboAlmacen = new JComboBox();
		pnlCargoContainer.add(cboAlmacen);
		new ArticuloController().ListarSedes("ALMACEN").forEach(ci -> cboAlmacen.addItem(ci));
		cboAlmacen.setBounds(316, 34, 331, 30);
		cboAlmacen.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboAlmacen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		txtTransportista = new JIconTextField();
		pnlCargoContainer.add(txtTransportista);
		txtTransportista.setBounds(12, 18, 297, 50);
		txtTransportista.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTransportista.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTransportista.setBorder(BorderFactory.createTitledBorder(null, "TRANSPORTISTA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnGrabarCargo = new JButton(new ImageIcon("img/grabar.png"));
		pnlCargoContainer.add(btnGrabarCargo);
		btnGrabarCargo.setOpaque(false);
		btnGrabarCargo.setBorderPainted(false);
		btnGrabarCargo.setToolTipText("Grabar Cargo");
		btnGrabarCargo.setContentAreaFilled(false);
		btnGrabarCargo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabarCargo.setBounds(603, 97, 70, 70);
		btnGrabarCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utiles.Validar(pnlCargoContainer)) {
					tbContrato.getCellEditor().stopCellEditing();
					Cargo cargo = new Cargo();
					cargo.setFecha(String.valueOf(LocalDate.now()));
					cargo.setTransportista(txtTransportista.getText().toUpperCase());
					cargo.setObs(txtObsCargo.getText());
					cargo.setUsuarioCreacion(Principal.LOGGED.getLogin());
					cargo.setFechaCreacion(String.valueOf(LocalDate.now()));
					for (int i = 0; i <= Constantes.DetalleContratoModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(Constantes.DetalleContratoModel.getValueAt(i, 6).toString());
						if (estado == 99) {
							DetalleCargo detalle_cargo = new DetalleCargo();
							detalle_cargo.setContrato(contrato);
							Articulo articulo = new ArticuloController().ObtenerArticulo(
									Integer.parseInt(Constantes.DetalleContratoModel.getValueAt(i, 0).toString()));
							articulo.setEArticulo(new EArticulo(4, "CON CARGO"));
							detalle_cargo.setArticulo(articulo);
							Sede sede = new Sede();
							sede.setId(((ComboItem) cboAlmacen.getSelectedItem()).getId());
							sede.setDescripcion(((ComboItem) cboAlmacen.getSelectedItem()).getDescripcion());
							detalle_cargo.setSede(sede);
							detalle_cargo.setCargo(cargo);
							cargo.addDetalleCargo(detalle_cargo);
						}
					}
					if (cargo.getDetalleCargos().size() > 0) {
						new CargoController().GenerarCargo(cargo);
						Utiles.Mensaje(
								"Cargo registrado. Favor de colocar papel en la impresora para la impresi�n de la constancia.",
								JOptionPane.INFORMATION_MESSAGE);
						ImprimirCargo(cargo);
					} else {
						Utiles.Mensaje("Seleccione por lo menos un (01) art�culo.", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					Utiles.Mensaje("Completa el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnRegresar2 = new JButton(new ImageIcon("img/regresar.png"));
		pnlCargoContainer.add(btnRegresar2);
		btnRegresar2.setBounds(603, 162, 70, 70);
		btnRegresar2.setOpaque(false);
		btnRegresar2.setBorderPainted(false);
		btnRegresar2.setToolTipText("Regresar");
		btnRegresar2.setContentAreaFilled(false);
		btnRegresar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOperacionContainer.setVisible(true);
				pnlCargoContainer.setVisible(false);
				tbContrato.getColumnModel().removeColumn(cargoColumn);
			}
		});

		spDetalleCargo = new JScrollPane();
		pnlCargoContainer.add(spDetalleCargo);
		spDetalleCargo.setBounds(17, 80, 630, 183);
		spDetalleCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		txtObsCargo = new JTextArea();
		spDetalleCargo.setViewportView(txtObsCargo);
		txtObsCargo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtObsCargo.setForeground(Color.BLACK);

		lblId5 = new JLabel();
		pnlCargoContainer.add(lblId5);
		lblId5.setText("DESTINO");
		lblId5.setFont(new java.awt.Font("Segoe UI", 1, 12));
		lblId5.setForeground(new java.awt.Color(0, 128, 0));
		lblId5.setBounds(316, 12, 91, 20);

		btnGrabarSeguimiento = new JButton(new ImageIcon("img/grabar.png"));
		pnlSeguimientoContainer.add(btnGrabarSeguimiento);
		pnlSeguimientoContainer.setOpaque(false);
		btnGrabarSeguimiento.setOpaque(false);
		btnGrabarSeguimiento.setBorderPainted(false);
		btnGrabarSeguimiento.setToolTipText("Registrar llamada");
		btnGrabarSeguimiento.setContentAreaFilled(false);
		btnGrabarSeguimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabarSeguimiento.setBounds(594, 92, 85, 70);
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
		btnRegresar.setOpaque(false);
		btnRegresar.setBorderPainted(false);
		btnRegresar.setContentAreaFilled(false);
		btnRegresar.setToolTipText("Regresar");
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlSeguimientoContainer.add(btnRegresar);
		btnRegresar.setBounds(597, 173, 85, 70);
		btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnRegresar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOperacionContainer.setVisible(true);
				pnlSeguimientoContainer.setVisible(false);
			}
		});

		spDetalleSeguimiento = new JScrollPane();
		pnlSeguimientoContainer.add(spDetalleSeguimiento);
		spDetalleSeguimiento.setBounds(5, 59, 637, 222);
		txtDetalleSeguimiento = new JTextArea();
		pnlSeguimientoContainer.add(txtDetalleSeguimiento);
		spDetalleSeguimiento.setViewportView(txtDetalleSeguimiento);
		spDetalleSeguimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDetalleSeguimiento.setFont(new java.awt.Font("Segoe UI", 0, 20));

		rbEntrante = new JRadioButton();
		pnlSeguimientoContainer.add(rbEntrante);
		rbgSeguimiento.add(rbEntrante);
		rbEntrante.setText("ENTRANTE");
		rbEntrante.setOpaque(false);
		rbEntrante.setBounds(346, 13, 158, 32);
		rbEntrante.setActionCommand("I");
		rbEntrante.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbEntrante.setForeground(new java.awt.Color(0, 128, 0));
		rbEntrante.setHorizontalAlignment(SwingConstants.CENTER);

		rbSaliente = new JRadioButton();
		rbSaliente.setOpaque(false);
		pnlSeguimientoContainer.add(rbSaliente);
		rbgSeguimiento.add(rbSaliente);
		rbSaliente.setText("SALIENTE");
		rbSaliente.setActionCommand("O");
		rbSaliente.setBounds(525, 13, 158, 32);
		rbSaliente.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbSaliente.setForeground(new java.awt.Color(0, 128, 0));
		rbSaliente.setHorizontalAlignment(SwingConstants.CENTER);

		lblId3 = new JLabel();
		pnlSeguimientoContainer.add(lblId3);
		lblId3.setText("RESULTADO DE LA LLAMADA");
		lblId3.setForeground(new java.awt.Color(0, 128, 255));
		lblId3.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblId3.setBounds(5, 12, 329, 32);

		rbPagoInteres = new JRadioButton();
		pnlOperacionContainer.add(rbPagoInteres);
		rbPagoInteres.setText("SOLO INTERESES");
		rbPagoInteres.setBounds(13, 44, 197, 30);
		rbPagoInteres.setOpaque(false);
		rbPagoInteres.setFont(new java.awt.Font("Segoe UI", 1, 16));
		rbPagoInteres.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteres.setActionCommand("I");
		rbPagoInteres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR S�LO INTERESES");
				if (contrato.getCuotas().compareTo(BigDecimal.ZERO) == 0) {
					contrato.setInteresTotal(contrato.getInteresMensual());
				}
				lblTotalAPagar.setText(String.valueOf(contrato.getInteresTotal()));
				btnPagar.setEnabled(true);
			}
		});
		rbgOpcionPago.add(rbPagoInteres);

		rbPagoInteresMora = new JRadioButton();
		pnlOperacionContainer.add(rbPagoInteresMora);
		rbPagoInteresMora.setOpaque(false);
		rbPagoInteresMora.setText("INTER�S + MORA");
		rbPagoInteresMora.setBounds(14, 92, 197, 30);
		rbPagoInteresMora.setFont(new java.awt.Font("Segoe UI", 1, 16));
		rbPagoInteresMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteresMora.setActionCommand("IM");
		rbPagoInteresMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR INTERESES + MORA");
				if (contrato.getCuotas().compareTo(BigDecimal.ZERO) == 0) {
					contrato.setInteresTotal(contrato.getInteresMensual());
				}
				BigDecimal total = contrato.getInteresTotal().add(contrato.getMoraTotal());
				lblTotalAPagar.setText(String.valueOf(total));
				btnPagar.setEnabled(true);
			}
		});
		rbgOpcionPago.add(rbPagoInteresMora);

		rbPagoMora = new JRadioButton();
		pnlOperacionContainer.add(rbPagoMora);
		rbPagoMora.setOpaque(false);
		rbPagoMora.setText("SOLO MORAS");
		rbPagoMora.setActionCommand("M");
		rbPagoMora.setFont(new java.awt.Font("Segoe UI", 1, 16));
		rbPagoMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoMora.setBounds(12, 140, 197, 30);
		rbPagoMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR S�LO MORA");
				lblTotalAPagar.setText(String.valueOf(contrato.getMoraTotal()));
				btnPagar.setEnabled(true);
			}
		});
		rbgOpcionPago.add(rbPagoMora);

		rbAbonarCapital = new JRadioButton();
		pnlOperacionContainer.add(rbAbonarCapital);
		rbAbonarCapital.setText("ABONAR AL CAPITAL");
		rbAbonarCapital.setOpaque(false);
		rbAbonarCapital.setActionCommand("ABN");
		rbAbonarCapital.setFont(new java.awt.Font("Segoe UI", 1, 16));
		rbAbonarCapital.setForeground(new java.awt.Color(128, 0, 128));
		rbAbonarCapital.setBounds(12, 188, 197, 30);
		rbAbonarCapital.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int e = arg0.getStateChange();
				if (e == ItemEvent.SELECTED) {
					lblTotalAPagar.setVisible(false);
					txtAbono.setVisible(true);
					txtAbono.requestFocus();
					btnPagar.setEnabled(true);
				} else {
					lblCapital.setText(String.valueOf(contrato.getCapital()));
					lblInteresMensual.setText(String.valueOf(contrato.getInteresMensual()));
					lblTotalAPagar.setVisible(true);
					txtAbono.setVisible(false);
					txtAbono.setText("");
					btnPagar.setEnabled(true);
				}
			}
		});
		rbAbonarCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("ABONAR AL CAPITAL");
				btnPagar.setEnabled(true);
			}
		});
		rbgOpcionPago.add(rbAbonarCapital);

		rbCancelarContrato = new JRadioButton();
		pnlOperacionContainer.add(rbCancelarContrato);
		rbCancelarContrato.setText("CANCELAR TODO");
		rbCancelarContrato.setOpaque(false);
		rbCancelarContrato.setBounds(13, 236, 197, 30);
		rbCancelarContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));
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
				btnPagar.setEnabled(true);
			}
		});
		rbgOpcionPago.add(rbCancelarContrato);

		lblTotalAPagar = new JLabel();
		pnlOperacionContainer.add(lblTotalAPagar);
		pnlOperacionContainer.moveToBack(lblTotalAPagar);
		lblTotalAPagar.setBounds(222, 57, 330, 128);
		lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalAPagar.setVerticalTextPosition(SwingConstants.TOP);
		lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalAPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalAPagar.setOpaque(true);
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTotalAPagar.setBackground(new java.awt.Color(234, 255, 255));

		btnSeguimiento = new JButton(new ImageIcon("img/seguimiento.png"));
		pnlOperacionContainer.add(btnSeguimiento);
		btnSeguimiento.setOpaque(false);
		btnSeguimiento.setBorderPainted(false);
		btnSeguimiento.setContentAreaFilled(false);
		btnSeguimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSeguimiento.setText("NUEVA LLAMADA");
		btnSeguimiento.setBounds(222, 191, 218, 75);
		btnSeguimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnSeguimiento.setFont(new java.awt.Font("Segoe UI", 1, 16));
		btnSeguimiento.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeguimiento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOperacionContainer.setVisible(false);
				pnlSeguimientoContainer.setVisible(true);
			}
		});

		lblId1 = new JLabel();
		pnlOperacionContainer.add(lblId1);
		lblId1.setText("TOTAL A PAGAR");
		lblId1.setForeground(new java.awt.Color(0, 128, 255));
		lblId1.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblId1.setBounds(222, 10, 329, 30);

		btnPagar = new JButton(new ImageIcon("img/pagar.png"));
		pnlOperacionContainer.add(btnPagar);
		btnPagar.setBounds(551, 57, 128, 128);
		btnPagar.setEnabled(false);
		btnPagar.setOpaque(false);
		btnPagar.setToolTipText("Pagar importe");
		btnPagar.setBorderPainted(false);
		btnPagar.setContentAreaFilled(false);
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPagar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnPagar.setHorizontalAlignment(SwingConstants.LEFT);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null, "<html><h2>�Confirma <strong style='color:red'>"
						+ contrato.getOperacion() + "</strong>?</h2></html>", "Confirmaci�n",
						JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					Pagar();
				}
			}
		});

		btnCargo = new JButton(new ImageIcon("img/cargo.png"));
		pnlOperacionContainer.add(btnCargo, JLayeredPane.DEFAULT_LAYER);
		btnCargo.setText("NUEVO CARGO");
		btnCargo.setBounds(452, 191, 218, 75);
		btnCargo.setOpaque(false);
		btnCargo.setBorderPainted(false);
		btnCargo.setContentAreaFilled(false);
		btnCargo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCargo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		btnCargo.setHorizontalAlignment(SwingConstants.LEFT);

		lblId2 = new JLabel();
		pnlOperacionContainer.add(lblId2, JLayeredPane.DEFAULT_LAYER);
		lblId2.setText("OPERACI�N");
		lblId2.setForeground(new java.awt.Color(0, 128, 255));
		lblId2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblId2.setBounds(16, 10, 194, 30);

		btnCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tbContrato.getColumnModel().addColumn(cargoColumn);
				pnlOperacionContainer.setVisible(false);
				pnlCargoContainer.setVisible(true);
			}
		});

		CargarInformacionContrato();
		if (contrato.getEContrato().getId() == 6) {
			ActivarPerspectivaCancelado(contrato);
		}
	}

	public void CargarInformacionContrato() {
		CargarDetalleContrato();
		CalcularInteres();
		CalcularMoras();
		CargarPagos();
		CargarAbonos();
		CargarSeguimiento();
		CargarCargos();
		CargarHistorial();
	}

	public void ActivarPerspectivaCancelado(Contrato k) {
		pnlSucesos.setVisible(true);
		Pago cancelacion = k.getPagos().stream().filter(Constantes.predicadoPago).findFirst().orElse(Pago.DEFAULT);
		BigDecimal montoCancelacion = cancelacion.getCapital().add(cancelacion.getInteres()).add(cancelacion.getMora());
		edpMensajeSuceso.setText("<html>" + "<center>" + "<h1 style='color:red'>CANCELADO</h1>" + "<h3>D�A: "
				+ Constantes.formatoLocal.format(LocalDate.parse(cancelacion.getFechaPago())) + " </h3>"
				+ "<h3>IMPORTE: " + montoCancelacion + "</h3>" + "</center>" + "</html>");
		tpContrato.setEnabledAt(1, false);
		tpContrato.setEnabledAt(3, false);
		tpContrato.setDisabledIconAt(1, new ImageIcon("img/deshabilitar.png"));
		tpContrato.setDisabledIconAt(3, new ImageIcon("img/deshabilitar.png"));
		Arrays.stream(pnlCalculos.getComponents()).filter(Constantes.predicadoCajaTexto)
				.forEach(t -> ((JLabel) t).setText("0.00"));
	}

	public void CargarCargos() {
		Constantes.CargoModel.setRowCount(0);
		for (DetalleCargo dc : contrato.getDetalleCargos()) {
			Constantes.CargoModel
					.addRow(new Object[] { Constantes.formatoLocal.format(LocalDate.parse(dc.getCargo().getFecha())),
							dc.getCargo().getTransportista(), dc.getArticulo().getDescripcion(),
							dc.getSede().getDescripcion(), dc.getCargo().getObs() });
		}
	}

	public void CargarDetalleContrato() {
		Constantes.DetalleContratoModel.setRowCount(0);
		for (DetalleContrato dc : contrato.getDetalleContratos()) {
			Constantes.DetalleContratoModel.addRow(new Object[] { dc.getArticulo().getId(),
					dc.getArticulo().getDescripcion(), dc.getArticulo().getMarca(), dc.getArticulo().getModelo(),
					dc.getArticulo().getObs(), dc.getTasacion().setScale(2, RoundingMode.HALF_UP),
					dc.getArticulo().getEArticulo().getId() });
		}
	}

	public void CargarSeguimiento() {
		Constantes.SeguimientoModel.setRowCount(0);
		for (Seguimiento s : contrato.getSeguimientos()) {
			Constantes.SeguimientoModel.addRow(new Object[] { s.getTipo(),
					Constantes.formatoLocal.format(LocalDate.parse(s.getFecha())), s.getObs() });
		}
		tbSeguimiento.setModel(Constantes.SeguimientoModel);
	}

	public void CargarHistorial() {
		Constantes.HistorialModel.setRowCount(0);
		int alertas = new ContratoController().BuscarContratosPorCliente(contrato.getCliente().getId(), true,
				contrato.getFlag(), contrato.getNumero());
		tbHistorial.setModel(Constantes.HistorialModel);
		if (alertas > 0) {
			tpContrato.setIconAt(7, new ImageIcon("img/alerta.png"));
			tpContrato.setTitleAt(7, tpContrato.getTitleAt(7) + " (" + alertas + ")");
		}
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
		Constantes.AbonoModel.setRowCount(0);
		for (Abono a : contrato.getAbonos()) {
			Constantes.AbonoModel
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
						m.setFechaPago(String.valueOf(LocalDate.now()));
					}
				}
			}
		}
	}

	public void ImprimirCargo(Cargo c) {
		ArrayList<Cargo> arreglo_cargo = new ArrayList<Cargo>();
		arreglo_cargo.add(c);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/cargo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
					new JRBeanCollectionDataSource(arreglo_cargo));
			// JasperPrintManager.printReport(jasperPrint, true);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			viewer.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Pagar() {
		try {
			boolean proceed = false;

			Pago pago = new Pago();

			Ingreso ingreso = new Ingreso();
			ingreso.setLibroCaja(Principal.LIBRO_CAJA);
			ingreso.setDescripcion(contrato.getFlag() + "-" + contrato.getNumero());
			ingreso.setMoneda(lblTipoMoneda.getText());

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

				Utiles.DetectarEstado(contrato);

				Utiles.Mensaje("Pr�ximo Vencimiento : " + Constantes.formatoLocal.format(nuevo_vencimiento),
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

				Utiles.DetectarEstado(contrato);

				Utiles.Mensaje("Pr�ximo Vencimiento : " + Constantes.formatoLocal.format(nuevo_vencimiento),
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

						Utiles.Mensaje("Nuevo Capital: " + abono.getNeoCapital() + "<br/>" + "Nuevo Inter�s : "
								+ abono.getNeoInteres(), JOptionPane.INFORMATION_MESSAGE);

						proceed = true;
					} else {
						Utiles.Mensaje(
								"Fuera de plazo. <b>5 d�as</b> m�ximo desde �ltima fecha de pago. Han transcurrido "
										+ limite + " d�as",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					Utiles.Mensaje("Ingrese el importe.", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				pago.setDescripcion("CANCELACI�N");
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
				btnPagar.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
