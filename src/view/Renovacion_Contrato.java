package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Abono;
import model.Contrato;
import model.DetalleContrato;
import model.Ingreso;
import model.Mora;
import model.Pago;

import common.Constantes;
import common.EditorIM;
import common.RenderIM;

import controller.ContratoController;

@SuppressWarnings({ "serial" })
public class Renovacion_Contrato extends JInternalFrame {
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
	private JButton btnPagar;
	private JLabel lblInteresDiario;
	private JLabel jLabel16;
	private JTable tbPagos;
	private JLabel lblCliente;

	Contrato contrato;

	DefaultTableModel MoraModel = new DefaultTableModel(null, new String[] {
			"ID", "FECHA VENCIMIENTO", "FECHA MORA", "MONTO", "¿ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbMoras.getSelectedRow() && colIndex == 4) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel InteresModel = new DefaultTableModel(null, new String[] {
			"MES", "MONTO", "¿ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbIntereses.getSelectedRow() && colIndex == 2) {
				return true;
			} else {
				return false;
			}
		}
	};
	DefaultTableModel AbonoModel = new DefaultTableModel(null, new String[] {
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
	private JPanel pnlInteres;
	private JPanel pnlAbonos;
	private JTextField txtTipoPrestamo;

	public Renovacion_Contrato(String c) throws ParseException {
		String flag = String.valueOf(c.split("-")[0]);
		int numero = Integer.parseInt(String.valueOf(c.split("-")[1]));
		contrato = new ContratoController().CargarContrato(flag.toUpperCase(),
				numero);
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(897, 426);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1265, 732));
		this.setBounds(0, 0, 1265, 732);
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1264, 702);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		InteresModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					BigDecimal total = BigDecimal.ZERO;
					for (int i = 0; i <= InteresModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(InteresModel.getValueAt(
								i, 2).toString());
						if (estado == 1) {
							total = total.add(new BigDecimal(String
									.valueOf(InteresModel.getValueAt(i, 1))));
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
						int estado = Integer.parseInt(MoraModel
								.getValueAt(i, 4).toString());
						if (estado == 1) {
							total = total.add(new BigDecimal(String
									.valueOf(MoraModel.getValueAt(i, 3))));
						}
					}
					contrato.setMoraTotal(total.add(contrato.getMoraActual()));
					lblTotalMora.setText(String.valueOf(total.add(contrato
							.getMoraActual())));
					lblMoraAnterior.setText(String.valueOf(total));
					rbPagoMora.doClick();
				}
			}
		});

		lblEstado = new JLabel(String.valueOf(contrato.getEContrato()
				.getDescripcion()));
		contenedor.add(lblEstado);
		lblEstado.setBounds(320, 11, 232, 32);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
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

		lblInicio = new JLabel(Constantes.formatoLocal.format(
				LocalDate.parse(contrato.getFechaContrato())).toUpperCase());
		contenedor.add(lblInicio);
		lblInicio.setBounds(564, 49, 218, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInicio.setBackground(new java.awt.Color(255, 255, 255));
		lblInicio.setOpaque(true);
		lblInicio.setForeground(new java.awt.Color(0, 64, 128));

		lblVencimiento = new JLabel(Constantes.formatoLocal.format(
				LocalDate.parse(contrato.getFechaVencimiento())).toUpperCase());
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(793, 49, 218, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblVencimiento.setBackground(new java.awt.Color(255, 255, 255));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setForeground(new java.awt.Color(0, 64, 128));

		lblRemate = new JLabel(Constantes.formatoLocal.format(
				LocalDate.parse(contrato.getFechaRemate())).toUpperCase());
		contenedor.add(lblRemate);
		lblRemate.setBounds(1023, 49, 218, 32);
		lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblRemate.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblRemate.setBackground(new java.awt.Color(255, 255, 255));
		lblRemate.setOpaque(true);
		lblRemate.setForeground(new java.awt.Color(0, 64, 128));

		tpContrato = new JTabbedPane();
		contenedor.add(tpContrato);
		tpContrato.setBounds(12, 186, 1229, 284);
		tpContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		tpContrato.setForeground(new java.awt.Color(0, 0, 0));

		pnlArticulos = new JPanel();
		spArticulos = new JScrollPane();
		tbArticulos = new JTable();
		tbArticulos.setModel(Constantes.ContratoModel);
		tbArticulos.setRowHeight(30);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbArticulos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));

		spArticulos.setPreferredSize(new java.awt.Dimension(1202, 227));
		spArticulos.setViewportView(tbArticulos);
		pnlArticulos.add(spArticulos);
		tpContrato.addTab("ARTÍCULOS", null, pnlArticulos, null);
		pnlArticulos.setPreferredSize(new java.awt.Dimension(1224, 264));

		pnlInteres = new JPanel();
		spIntereses = new JScrollPane();
		tbIntereses = new JTable();
		tbIntereses.setDefaultRenderer(Object.class, new RenderIM());
		tbIntereses.setDefaultEditor(Object.class, new EditorIM());
		tbIntereses.setRowHeight(30);
		tbIntereses.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbIntereses.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
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

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CLIENTE");
		jLabel6.setBounds(292, 87, 87, 35);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("% MENSUAL");
		jLabel7.setBounds(13, 517, 131, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("CAPITAL");
		jLabel8.setBounds(13, 477, 131, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresMensual = new JLabel(String.valueOf(contrato
				.getInteresMensual()));
		contenedor.add(lblInteresMensual);
		lblInteresMensual.setBounds(155, 518, 120, 32);
		lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresMensual.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresMensual.setOpaque(true);
		lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));

		lblCapital = new JLabel(String.valueOf(contrato.getCapital()));
		contenedor.add(lblCapital);
		lblCapital.setBounds(155, 477, 120, 32);
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCapital.setBackground(new java.awt.Color(255, 255, 255));
		lblCapital.setOpaque(true);
		lblCapital.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("¿MORA?");
		jLabel10.setBounds(287, 477, 123, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboMora = new JTextField(contrato.getMoraPorcentaje().multiply(
				new BigDecimal(100).setScale(0, RoundingMode.HALF_UP))
				+ "%");
		contenedor.add(cboMora);
		cboMora.setBounds(415, 517, 120, 32);
		cboMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
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
		lblMoraSiNo.setBounds(415, 476, 120, 32);
		lblMoraSiNo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraSiNo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraSiNo.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraSiNo.setForeground(contrato.getMoraColor());
		lblMoraSiNo.setOpaque(true);

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DIAS EXTRA");
		jLabel11.setBounds(13, 561, 131, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		lblDiasExcedidos = new JLabel(String.valueOf(contrato.getDiasResiduo()));
		contenedor.add(lblDiasExcedidos);
		lblDiasExcedidos.setBounds(155, 559, 120, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblDiasExcedidos.setBackground(new java.awt.Color(255, 255, 255));
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));

		jLabel12 = new JLabel();
		contenedor.add(jLabel12);
		jLabel12.setText("M.PASADA");
		jLabel12.setBounds(287, 601, 123, 32);
		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel12.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("M.ACTUAL");
		jLabel13.setBounds(287, 560, 123, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraAnterior = new JLabel("0.00");
		contenedor.add(lblMoraAnterior);
		lblMoraAnterior.setBounds(416, 599, 120, 32);
		lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraAnterior.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraAnterior.setOpaque(true);
		lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));

		lblMoraActual = new JLabel(String.valueOf(contrato.getMoraActual()));
		contenedor.add(lblMoraActual);
		lblMoraActual.setBounds(415, 558, 120, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraActual.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraActual.setOpaque(true);
		lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("M.TOTAL");
		jLabel15.setBounds(287, 643, 123, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel15.setForeground(new java.awt.Color(0, 128, 0));

		lblTotalMora = new JLabel();
		contenedor.add(lblTotalMora);
		lblTotalMora.setText("0.00");
		lblTotalMora.setBounds(416, 641, 120, 32);
		lblTotalMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalMora.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalMora.setOpaque(true);
		lblTotalMora.setForeground(new java.awt.Color(0, 64, 128));

		lblCliente = new JLabel(contrato.getCliente().getNombres() + " "
				+ contrato.getCliente().getPaterno() + " "
				+ contrato.getCliente().getMaterno());
		contenedor.add(lblCliente);
		lblCliente.setBounds(292, 122, 725, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblCliente.setOpaque(true);
		lblCliente.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("% DIARIO");
		jLabel16.setBounds(12, 601, 131, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresDiario = new JLabel(String.valueOf(contrato
				.getInteresDiario()));
		contenedor.add(lblInteresDiario);
		lblInteresDiario.setBounds(155, 600, 120, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));

		btnPagar = new JButton();
		contenedor.add(btnPagar);
		btnPagar.setText("PAGAR");
		btnPagar.setBounds(805, 601, 331, 75);
		btnPagar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"<html><h2>¿Confirma <strong style='color:red'>"
								+ contrato.getOperacion()
								+ "</strong>?</h2></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					Pagar();
				}
			}
		});

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 166, 1222, 10);

		lblTotalAPagar = new JLabel();
		contenedor.add(lblTotalAPagar);
		lblTotalAPagar.setBounds(804, 478, 332, 111);
		lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalAPagar.setVerticalTextPosition(SwingConstants.TOP);
		lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalAPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalAPagar.setOpaque(true);
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setHorizontalTextPosition(SwingConstants.CENTER);

		txtAbono = new JTextField();
		contenedor.add(txtAbono);
		txtAbono.setVisible(false);
		txtAbono.setBounds(804, 478, 332, 111);
		txtAbono.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtAbono.setForeground(new java.awt.Color(255, 0, 0));
		txtAbono.setFont(new java.awt.Font("Segoe UI", 1, 72));
		txtAbono.setBackground(new Color(255, 255, 128));
		txtAbono.setHorizontalAlignment(SwingConstants.CENTER);

		lblTipoMoneda = new JLabel(contrato.getMoneda());
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(1029, 122, 212, 32);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
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

		txtTipoPrestamo = new JTextField(contrato.getPrestamo()
				.getDescripcion());
		contenedor.add(txtTipoPrestamo);
		txtTipoPrestamo.setBounds(320, 49, 232, 32);
		txtTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTipoPrestamo.setForeground(new java.awt.Color(0, 64, 128));
		txtTipoPrestamo.setOpaque(true);
		txtTipoPrestamo.setEnabled(false);
		txtTipoPrestamo.setHorizontalAlignment(SwingConstants.CENTER);

		rbgOpcionPago = new ButtonGroup();

		rbPagoInteres = new JRadioButton();
		contenedor.add(rbPagoInteres);
		rbPagoInteres.setText("SOLO INTERESES");
		rbPagoInteres.setBounds(564, 478, 229, 32);
		rbPagoInteres.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteres.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteres.setActionCommand("I");
		rbPagoInteres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR SÓLO INTERESES");
				lblTotalAPagar.setText(String.valueOf(contrato
						.getInteresTotal()));
			}
		});

		rbPagoInteresMora = new JRadioButton();
		contenedor.add(rbPagoInteresMora);
		rbPagoInteresMora.setText("INTERÉS + MORA");
		rbPagoInteresMora.setBounds(564, 518, 229, 32);
		rbPagoInteresMora.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteresMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoInteresMora.setActionCommand("IM");
		rbPagoInteresMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR INTERESES + MORA");
				BigDecimal total = contrato.getInteresTotal().add(
						contrato.getMoraTotal());
				lblTotalAPagar.setText(String.valueOf(total));
			}
		});

		rbPagoMora = new JRadioButton();
		contenedor.add(rbPagoMora);
		rbPagoMora.setText("SOLO MORAS");
		rbPagoMora.setActionCommand("M");
		rbPagoMora.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoMora.setForeground(new java.awt.Color(128, 0, 128));
		rbPagoMora.setBounds(564, 557, 229, 32);
		rbPagoMora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR SÓLO MORA");
				lblTotalAPagar.setText(String.valueOf(contrato.getMoraTotal()));

			}
		});

		rbCancelarContrato = new JRadioButton();
		contenedor.add(rbCancelarContrato);
		rbCancelarContrato.setText("CANCELAR TODO");
		rbCancelarContrato.setBounds(565, 644, 229, 32);
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
			}
		});
		rbCancelarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("PAGAR TODO");
				CalcularInteres();
				CalcularMoras();
				BigDecimal total = contrato.getCapital()
						.add(contrato.getInteresTotal())
						.add(contrato.getMoraTotal())
						.add(contrato.getProrrateo())
						.add(contrato.getProrrateoMora());
				lblTotalAPagar.setText(String.valueOf(total));
			}
		});

		rbAbonarCapital = new JRadioButton();
		contenedor.add(rbAbonarCapital);
		rbAbonarCapital.setText("ABONAR AL CAPITAL");
		rbAbonarCapital.setActionCommand("ABN");
		rbAbonarCapital.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbAbonarCapital.setForeground(new java.awt.Color(128, 0, 128));
		rbAbonarCapital.setBounds(565, 602, 229, 32);
		rbAbonarCapital.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int e = arg0.getStateChange();
				if (e == ItemEvent.SELECTED) {
					lblTotalAPagar.setVisible(false);
					txtAbono.setVisible(true);
					txtAbono.requestFocus();
				} else {
					lblTotalAPagar.setVisible(true);
					txtAbono.setVisible(false);
					txtAbono.setText("");
				}
			}
		});
		rbAbonarCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.setOperacion("ABONAR");

			}
		});

		rbgOpcionPago.add(rbPagoInteres);
		rbgOpcionPago.add(rbPagoInteresMora);
		rbgOpcionPago.add(rbPagoMora);
		rbgOpcionPago.add(rbCancelarContrato);
		rbgOpcionPago.add(rbAbonarCapital);

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("PRORRATEO");
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));
		jLabel14.setBounds(12, 643, 131, 32);

		lblProrrateo = new JLabel(String.valueOf(contrato.getProrrateo()));
		contenedor.add(lblProrrateo);
		lblProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblProrrateo.setBackground(new java.awt.Color(255, 255, 255));
		lblProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblProrrateo.setOpaque(true);
		lblProrrateo.setBounds(155, 641, 120, 32);

		jLabel1 = new JLabel("% MORA");
		contenedor.add(jLabel1);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setBounds(287, 518, 123, 32);

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
		jLabel17.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		jLabel17.setBounds(12, 122, 268, 32);

		CargarInformacionContrato();

	}

	/* CARGAR HISTORIA CONTRATO */
	// Cargar Separaciones
	// Cargar Ventas
	// Cargar Cargos

	public void CargarInformacionContrato() {
		CargarDetalleContrato();
		CalcularInteres();
		CalcularMoras();
		CargarPagos();
		CargarAbonos();
	}

	public void CargarDetalleContrato() {
		Constantes.ContratoModel.setRowCount(0);
		for (DetalleContrato dc : contrato.getDetalleContratos()) {
			Constantes.ContratoModel.addRow(new Object[] {
					dc.getArticulo().getId(),
					dc.getArticulo().getDescripcion(),
					dc.getArticulo().getMarca(), dc.getArticulo().getModelo(),
					dc.getArticulo().getObs(),
					dc.getTasacion().setScale(2, RoundingMode.HALF_UP) });
		}
		tbArticulos.setModel(Constantes.ContratoModel);
	}

	public void CargarPagos() {
		Constantes.PagoModel.setRowCount(0);
		for (Pago p : contrato.getPagos()) {
			Constantes.PagoModel.addRow(new Object[] {
					Constantes.formatoLocal.format(
							LocalDate.parse(p.getFechaVencimiento()))
							.toUpperCase(),
					Constantes.formatoLocal.format(
							LocalDate.parse(p.getFechaPago())).toUpperCase(),
					p.getDescripcion(), p.getInteres(), p.getMora(),
					p.getInteres().add(p.getMora()) });
		}
		tbPagos.setModel(Constantes.PagoModel);
	}

	public void CalcularInteres() {
		try {
			BigDecimal total_interes = BigDecimal.ZERO;
			LocalDate fecha_vencimiento = LocalDate.parse(contrato
					.getFechaVencimiento());
			InteresModel.setRowCount(0);
			for (int i = 0; i < contrato.getCuotas().intValue(); i++) {
				InteresModel
						.addRow(new Object[] {
								Constantes.formatoMes.format(fecha_vencimiento)
										.toUpperCase(),
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
			BigDecimal mora_anterior = BigDecimal.ZERO.setScale(2,
					RoundingMode.HALF_UP);
			MoraModel.setRowCount(0);
			for (Mora m : contrato.getMoras()) {
				if (m.getStatus() == 1) {
					MoraModel.addRow(new Object[] {
							m.getId(),
							Constantes.formatoLocal.format(
									LocalDate.parse(m.getFechaVencimiento()))
									.toUpperCase(),
							Constantes.formatoLocal.format(
									LocalDate.parse(m.getFechaMora()))
									.toUpperCase(), m.getImporte(),
							m.getStatus() });
					mora_anterior = mora_anterior.add(m.getImporte());
				}
			}
			contrato.setMoraAnterior(mora_anterior);
			contrato.setMoraTotal(contrato.getMoraActual().add(
					contrato.getMoraAnterior()));
			lblMoraAnterior.setText(String.valueOf(mora_anterior));
			lblTotalMora.setText(String.valueOf(contrato.getMoraTotal()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CargarAbonos() {
		AbonoModel.setRowCount(0);
		for (Abono a : contrato.getAbonos()) {
			AbonoModel.addRow(new Object[] {
					Constantes.formatoLocal.format(
							LocalDate.parse(a.getFecha())).toUpperCase(),
					a.getArqCapital(), a.getArqInteres(),
					a.getArqCapital() - a.getNeoCapital(), a.getNeoCapital(),
					a.getNeoInteres() });
		}
	}

	public int CalcularRenovacion() {
		int meses = 0;
		for (int i = 0; i <= InteresModel.getRowCount() - 1; i++) {
			int estado = Integer.parseInt(InteresModel.getValueAt(i, 2)
					.toString());
			if (estado == 1) {
				meses += 1;
			}
		}
		return meses;
	}

	public void PagarMoras() {
		for (int i = 0; i < MoraModel.getRowCount(); i++) {
			int status = Integer.parseInt(String.valueOf(MoraModel.getValueAt(
					i, 4)));
			if (status == 1) {
				int id = Integer.parseInt(String.valueOf(MoraModel.getValueAt(
						i, 0)));
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
			String msg = "";
			
			Pago pago = new Pago();

			Ingreso ingreso = new Ingreso();
			ingreso.setLibroCaja(Principal.LIBRO_CAJA);
			ingreso.setDescripcion(contrato.getFlag() + "-"
					+ contrato.getNumero());

			int meses_renovar = CalcularRenovacion();

			LocalDate nuevo_vencimiento = LocalDate.parse(
					contrato.getFechaVencimiento()).plusMonths(meses_renovar);
			LocalDate nuevo_remate = LocalDate.parse(contrato.getFechaRemate())
					.plusMonths(meses_renovar);

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
				
				msg = "Próximo Vencimiento : " + Constantes.formatoLocal.format(nuevo_vencimiento); 

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
				
				msg = "Sin modificaciones en el estado del Contrato.";
						
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
				ingreso.setGanancia(contrato.getInteresTotal().add(
						contrato.getMoraTotal()));
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo(meses_renovar + "% + M");

				PagarMoras();

				contrato.setFechaVencimiento(String.valueOf(nuevo_vencimiento));
				contrato.setFechaRemate(String.valueOf(nuevo_remate));

				break;
			case "ABN":
				// Procesos de Abono
				break;
			default:
				pago.setDescripcion("CANCELACIÓN");
				pago.setCapital(contrato.getCapital());
				pago.setInteres(contrato.getInteresTotal().add(
						contrato.getProrrateo()));
				pago.setMora(contrato.getMoraTotal().add(
						contrato.getProrrateoMora()));
				pago.setFechaVencimiento(contrato.getFechaVencimiento());
				pago.setFechaPago(LocalDate.now().toString());
				pago.setContrato(contrato);
				contrato.addPago(pago);

				ingreso.setCapital(contrato.getCapital());
				ingreso.setGanancia(contrato.getInteresTotal().add(
						contrato.getMoraTotal().add(contrato.getProrrateo())
								.add(contrato.getProrrateoMora())));
				ingreso.setOtro(BigDecimal.ZERO);
				ingreso.setTipo("PAG");
				break;
			}

			Actualizar_Contratos.DetectarEstado(contrato);
			new ContratoController().GestionarContrato(contrato, ingreso);

			JOptionPane.showMessageDialog(null,
					"<html><h1 style='color: red;'>" + msg + "</h1></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
