package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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
import common.Redondeo;
import common.RenderIM;

import controller.ContratoController;

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
	private JScrollPane jScrollPane5;
	private JTable tbIntereses = new JTable();
	private JScrollPane jScrollPane4;
	private JLabel lblTotalAPagar;
	private JSeparator jSeparator1;
	private JTable tbMoras;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JButton btnSoloInteres;
	private JLabel lblInteresDiario;
	private JLabel jLabel16;
	private JTable tbHistorial;
	private JLabel lblCliente;
	private DecimalFormat fd;
	private DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	Contrato contrato;

	DefaultTableModel MoraModel = new DefaultTableModel(null, new String[] {
			"ID", "FECHA VENCIMIENTO", "FECHA MORA", "MONTO", "¿ACTIVO?" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (rowIndex == tbMoras.getSelectedRow() && colIndex == 2) {
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

		fd = new DecimalFormat("#####0.00", simbolo);

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(897, 426);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1265, 613));
		this.setBounds(0, 0, 1265, 613);
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1264, 589);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		simbolo.setDecimalSeparator('.');

		lblEstado = new JLabel(String.valueOf(contrato.getStatus()));
		contenedor.add(lblEstado);
		lblEstado.setBounds(295, 54, 290, 58);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblEstado.setOpaque(true);
		lblEstado.setBackground(Color.WHITE);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

		lblNumeroContrato = new JLabel(String.valueOf(contrato.getNumero()));
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(104, 12, 179, 62);	
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 72));		
		lblNumeroContrato.setOpaque(true);
		lblNumeroContrato.setForeground(new java.awt.Color(128,0,0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("INICIO:");
		jLabel2.setBounds(597, 47, 81, 24);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("VENCE:");
		jLabel3.setBounds(815, 47, 81, 24);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("REMATE:");
		jLabel4.setBounds(1033, 47, 96, 24);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		lblInicio = new JLabel(
				Constantes.formatoLocal.format(Constantes.formatoSQL
						.parse(contrato.getFechaContrato())));
		contenedor.add(lblInicio);
		lblInicio.setBounds(597, 78, 206, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInicio.setBackground(new java.awt.Color(255, 255, 255));
		lblInicio.setOpaque(true);
		lblInicio.setForeground(new java.awt.Color(0, 64, 128));

		lblVencimiento = new JLabel(
				Constantes.formatoLocal.format(Constantes.formatoSQL
						.parse(contrato.getFechaVencimiento())));
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(815, 78, 206, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblVencimiento.setBackground(new java.awt.Color(255, 255, 255));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setForeground(new java.awt.Color(0, 64, 128));

		lblRemate = new JLabel(
				Constantes.formatoLocal.format(Constantes.formatoSQL
						.parse(contrato.getFechaRemate())));
		contenedor.add(lblRemate);
		lblRemate.setBounds(1033, 78, 206, 32);
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
		tpContrato.setForeground(new java.awt.Color(0,128,255));
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
		tbAbonos.setModel(AbonoModel);
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
					contrato.setInteresTotal(total);
					rbPagoInteres.doClick();
					// lblTotalInteres.setText(total + "");
				}
			}
		});
		MoraModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent tm) {
				if (tm.getType() == TableModelEvent.UPDATE) {
					double total = 0;
					for (int i = 0; i <= MoraModel.getRowCount() - 1; i++) {
						int estado = Integer.parseInt(MoraModel
								.getValueAt(i, 2).toString());
						if (estado == 1) {
							double monto = Double.parseDouble(MoraModel
									.getValueAt(i, 1).toString());
							total += monto;

						}
					}
					lblTotalMora.setText(total
							+ Double.parseDouble(lblMoraActual.getText()) + "");
					lblMoraAnterior.setText(total + "");
					// calcularTotalAPagar();

				}
			}
		});

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CLIENTE:");
		jLabel6.setBounds(295, 7, 87, 35);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("% MENSUAL");
		jLabel7.setBounds(12, 405, 136, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("CAPITAL");
		jLabel8.setBounds(12, 365, 136, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresMensual = new JLabel(String.valueOf(contrato
				.getInteresMensual()));
		contenedor.add(lblInteresMensual);
		lblInteresMensual.setBounds(154, 406, 120, 32);
		lblInteresMensual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresMensual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresMensual.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresMensual.setOpaque(true);
		lblInteresMensual.setForeground(new java.awt.Color(0, 64, 128));

		lblCapital = new JLabel(String.valueOf(contrato.getCapital()));
		contenedor.add(lblCapital);
		lblCapital.setBounds(154, 365, 120, 32);
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCapital.setBackground(new java.awt.Color(255, 255, 255));
		lblCapital.setOpaque(true);
		lblCapital.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("¿MORA?");
		jLabel10.setBounds(286, 365, 81, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboMora = new JTextField(contrato.getMoraPorcentaje() * 100 + "%");
		contenedor.add(cboMora);
		cboMora.setBounds(414, 405, 120, 32);
		cboMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		cboMora.setBackground(new java.awt.Color(128, 0, 128));
		cboMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		cboMora.setForeground(new java.awt.Color(255,255,255));
		cboMora.setHorizontalAlignment(SwingConstants.CENTER);
		cboMora.setEnabled(false);
		cboMora.setEditable(false);
		cboMora.setEnabled(false);
		cboMora.setEnabled(false);

		lblMoraSiNo = new JLabel(contrato.getMoraRespuesta());
		contenedor.add(lblMoraSiNo);
		lblMoraSiNo.setBounds(414, 364, 120, 32);
		lblMoraSiNo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraSiNo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraSiNo.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraSiNo.setForeground(contrato.getMoraColor());
		lblMoraSiNo.setOpaque(true);

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DIAS EXTRA");
		jLabel11.setBounds(12, 449, 136, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		lblDiasExcedidos = new JLabel(String.valueOf(contrato.getDiasResiduo()));
		contenedor.add(lblDiasExcedidos);
		lblDiasExcedidos.setBounds(154, 447, 120, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblDiasExcedidos.setBackground(new java.awt.Color(255, 255, 255));
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setForeground(new java.awt.Color(0, 64, 128));

		jLabel12 = new JLabel();
		contenedor.add(jLabel12);
		jLabel12.setText("M.PASADA");
		jLabel12.setBounds(286, 487, 131, 32);
		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel12.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("M.ACTUAL");
		jLabel13.setBounds(286, 449, 103, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		lblMoraAnterior = new JLabel("0.00");
		contenedor.add(lblMoraAnterior);
		lblMoraAnterior.setBounds(415, 487, 120, 32);
		lblMoraAnterior.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraAnterior.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraAnterior.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraAnterior.setOpaque(true);
		lblMoraAnterior.setForeground(new java.awt.Color(0, 64, 128));

		lblMoraActual = new JLabel(String.valueOf(contrato.getMoraActual()));
		contenedor.add(lblMoraActual);
		lblMoraActual.setBounds(414, 446, 120, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblMoraActual.setBackground(new java.awt.Color(255, 255, 255));
		lblMoraActual.setOpaque(true);
		lblMoraActual.setForeground(new java.awt.Color(0, 64, 128));

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("M.TOTAL");
		jLabel15.setBounds(286, 531, 97, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel15.setForeground(new java.awt.Color(0, 128, 0));

		lblTotalMora = new JLabel();
		contenedor.add(lblTotalMora);
		lblTotalMora.setText("0.00");
		lblTotalMora.setBounds(415, 529, 120, 32);
		lblTotalMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalMora.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalMora.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalMora.setOpaque(true);
		lblTotalMora.setForeground(new java.awt.Color(0, 64, 128));

		lblCliente = new JLabel(contrato.getCliente().getDocumento() + "-"
				+ contrato.getCliente().getNombres() + " "
				+ contrato.getCliente().getPaterno() + " "
				+ contrato.getCliente().getMaterno());
		contenedor.add(lblCliente);
		lblCliente.setBounds(382, 9, 651, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblCliente.setOpaque(true);
		lblCliente.setForeground(new java.awt.Color(0, 64, 128));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("% DIARIO");
		jLabel16.setBounds(12, 487, 136, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		lblInteresDiario = new JLabel(String.valueOf(contrato
				.getInteresDiario()));
		contenedor.add(lblInteresDiario);
		lblInteresDiario.setBounds(154, 488, 120, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblInteresDiario.setBackground(new java.awt.Color(255, 255, 255));
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setForeground(new java.awt.Color(0, 64, 128));

		btnSoloInteres = new JButton();
		contenedor.add(btnSoloInteres);
		btnSoloInteres.setText("PAGAR");
		btnSoloInteres.setBounds(564, 496, 584, 65);
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
					// renovarContratoSoloInteres(Integer.parseInt(lblNumeroContrato.getText()),
					// a[0]);
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
		lblTotalAPagar.setBounds(780, 365, 368, 122);
		lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalAPagar.setVerticalTextPosition(SwingConstants.TOP);
		lblTotalAPagar.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalAPagar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setHorizontalTextPosition(SwingConstants.CENTER);

		lblTipoMoneda = new JLabel(contrato.getMoneda());
		contenedor.add(lblTipoMoneda);
		lblTipoMoneda.setBounds(1045, 9, 194, 32);
		lblTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTipoMoneda.setForeground(new java.awt.Color(0, 64, 128));
		lblTipoMoneda.setBackground(new java.awt.Color(0, 255, 255));
		lblTipoMoneda.setOpaque(true);
		lblTipoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMoneda.setHorizontalTextPosition(SwingConstants.CENTER);

		lblP = new JLabel(contrato.getFlag());
		contenedor.add(lblP);
		lblP.setBounds(12, 12, 57, 62);		
		lblP.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblP.setOpaque(true);
		lblP.setHorizontalAlignment(SwingConstants.LEFT);
		lblP.setForeground(new java.awt.Color(128,0,0));

		jLabel20 = new JLabel();
		contenedor.add(jLabel20);
		jLabel20.setText("-");
		jLabel20.setBounds(70, 0, 110, 67);
		jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 72));
		jLabel20.setVerticalAlignment(SwingConstants.CENTER);
		jLabel20.setForeground(new java.awt.Color(128,0,0));

		txtTipoPrestamo = new JTextField(contrato.getPrestamo()
				.getDescripcion());
		contenedor.add(txtTipoPrestamo);
		txtTipoPrestamo.setBounds(12, 80, 271, 32);
		txtTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTipoPrestamo.setForeground(new java.awt.Color(0, 64, 128));
		txtTipoPrestamo.setOpaque(true);

		rbgOpcionPago = new ButtonGroup();

		rbPagoInteres = new JRadioButton();
		contenedor.add(rbPagoInteres);
		rbPagoInteres.setText("SOLO INTERESES");
		rbPagoInteres.setBounds(564, 365, 198, 32);
		rbPagoInteres.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteres.setForeground(new java.awt.Color(0, 128, 0));
		rbPagoInteres.setActionCommand("I");
		rbPagoInteres.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblTotalAPagar.setText(String.valueOf(contrato
						.getInteresTotal()));
			}
		});

		rbPagoInteresMora = new JRadioButton();
		contenedor.add(rbPagoInteresMora);
		rbPagoInteresMora.setText("INTERÉS + MORA");
		rbPagoInteresMora.setBounds(564, 407, 198, 32);
		rbPagoInteresMora.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbPagoInteresMora.setForeground(new java.awt.Color(0, 128, 0));
		rbPagoInteresMora.setActionCommand("IM");
		rbPagoInteresMora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double total = contrato.getInteresTotal() + contrato.getMoraTotal();
				lblTotalAPagar.setText(String.valueOf(total));
			}
		});

		rbCancelarContrato = new JRadioButton();
		contenedor.add(rbCancelarContrato);
		rbCancelarContrato.setText("TODO");
		rbCancelarContrato.setBounds(564, 455, 199, 32);
		rbCancelarContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		rbCancelarContrato.setForeground(new java.awt.Color(0, 128, 0));
		rbCancelarContrato.setActionCommand("PAG");
		rbCancelarContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					double total = contrato.getCapital();
							
			}
		});

		rbgOpcionPago.add(rbPagoInteres);
		rbgOpcionPago.add(rbPagoInteresMora);
		rbgOpcionPago.add(rbCancelarContrato);

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("PRORRATEO");
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));
		jLabel14.setBounds(12, 525, 136, 32);

		lblProrrateo = new JLabel(String.valueOf(contrato.getProrrateo()));
		contenedor.add(lblProrrateo);
		lblProrrateo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblProrrateo.setBackground(new java.awt.Color(255, 255, 255));
		lblProrrateo.setForeground(new java.awt.Color(0, 64, 128));
		lblProrrateo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblProrrateo.setOpaque(true);
		lblProrrateo.setBounds(154, 529, 120, 32);

		jLabel1 = new JLabel("% MORA");
		contenedor.add(jLabel1);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setBounds(286, 405, 103, 32);

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
		CargarPagosRealizados();
		CargarAbonos();
	}

	public void CargarDetalleContrato() {
		Constantes.ContratoModel.setRowCount(0);
		for (DetalleContrato dc : contrato.getDetalleContratos()) {
			Constantes.ContratoModel.addRow(new Object[] {
					dc.getArticulo().getId(),
					dc.getArticulo().getDescripcion(),
					dc.getArticulo().getMarca(), dc.getArticulo().getModelo(),
					dc.getArticulo().getObs(), dc.getTasacion() });
		}
		tbArticulos.setModel(Constantes.ContratoModel);
	}

	public void CargarPagosRealizados() {
		Constantes.PagoModel.setRowCount(0);
		for (Pago p : contrato.getPagos()) {
			Constantes.PagoModel.addRow(new Object[] { p.getFechaVencimiento(),
					p.getFechaPago(), p.getInteres(), p.getMora(),
					p.getInteres() + p.getMora() });
		}
		tbHistorial.setModel(Constantes.PagoModel);
	}

	public void CalcularInteres() {
		try {
			double total_interes = 0;
			Calendar v = Calendar.getInstance();
			v.setTime(Constantes.formatoSQL.parse(contrato
					.getFechaVencimiento()));
			for (int i = 0; i < contrato.getCuotas(); i++) {
				InteresModel
						.addRow(new Object[] {
								Constantes.formatoMes.format(v.getTime())
										.toUpperCase(),
								contrato.getInteresMensual(), 1 });
				v.add(Calendar.MONTH, 1);
				total_interes += contrato.getInteresMensual();
			}
			contrato.setInteresTotal(total_interes);
			tbIntereses.setModel(InteresModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CalcularMoras() {
		double mora_anterior = 0.00;
		try {
			for (Mora m : contrato.getMoras()) {
				if (m.getStatus() == "1") {
					MoraModel.addRow(new Object[] { m.getId(),
							m.getFechaVencimiento(), m.getFechaMora(),
							m.getImporte(), m.getStatus() });
					mora_anterior += m.getImporte();
				}
			}
			contrato.setMoraAnterior(mora_anterior);
			contrato.setMoraTotal(contrato.getMoraActual() + contrato.getMoraAnterior());
			lblMoraAnterior.setText(Redondeo.redondearCentimos(String
					.valueOf(mora_anterior)));
			lblTotalMora.setText(Redondeo.redondearCentimos(String.valueOf(contrato.getMoraTotal())));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CargarAbonos() {
		AbonoModel.setRowCount(0);
		for (Abono a : contrato.getAbonos()) {
			AbonoModel.addRow(new Object[] {

			Constantes.formatoLocal.format(a.getFecha()).toUpperCase(),
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

	public void PagarInteres(int numeroContrato, String tabla) {
		try {
			int meses_renovar = CalcularRenovacion();
			Calendar nuevo_vencimiento = Calendar.getInstance();
			Calendar nuevo_remate = Calendar.getInstance();
			nuevo_vencimiento.setTime(Constantes.formatoSQL.parse(contrato
					.getFechaVencimiento()));
			nuevo_remate.setTime(Constantes.formatoSQL.parse(contrato
					.getFechaRemate()));
			nuevo_vencimiento.add(Calendar.MONTH, meses_renovar);
			nuevo_remate.add(Calendar.MONTH, meses_renovar);

			contrato.setFechaVencimiento(Constantes.formatoSQL
					.format(nuevo_vencimiento.getTime()));
			contrato.setFechaRemate((Constantes.formatoSQL.format(nuevo_remate
					.getTime())));

			Pago pago = new Pago();
			pago.setFechaVencimiento(contrato.getFechaVencimiento());
			pago.setFechaPago(Constantes.formatoSQL.format(new Date()));
			pago.setDescripcion(meses_renovar + "%");
			pago.setCapital(0);
			pago.setInteres(Double
					.parseDouble(/* lblTotalInteres.getText() */"0"));
			pago.setMora(0);
			pago.setContrato(contrato);

			Ingreso ingreso = new Ingreso();
			ingreso.setLibroCaja(Principal.LIBRO_CAJA);
			ingreso.setDescripcion(contrato.getFlag() + "-"
					+ contrato.getNumero());
			ingreso.setCapital(0);
			ingreso.setGanancia(pago.getInteres());
			ingreso.setOtro(0);
			ingreso.setTipo(1);

			new ContratoController().RenovarContrato(contrato, pago, ingreso);

			// actualizarContratos(fcvto.getTime());
			JOptionPane.showMessageDialog(
					null,
					"<html><h1 style='color: red;'>Próximo Vencimiento : "
							+ Constantes.formatoLocal.format(nuevo_vencimiento
									.getTime()) + "</h1></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void renovarContratoInteresMora(int numeroContrato, String tabla)
	 * { try { Calendar fcvto = Calendar.getInstance(); Calendar frem =
	 * Calendar.getInstance(); fcvto.setTime(vcto); frem.setTime(remate);
	 * fcvto.add(Calendar.MONTH, calcularRenovacion()); frem.add(Calendar.MONTH,
	 * calcularRenovacion()); proximo_vencimiento = fcvto.getTime();
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date hoy = new
	 * Date(); String sql = "UPDATE " + tabla +
	 * " SET fec_ven_con=?, fec_rem_con=? WHERE id_con=?"; PreparedStatement pst
	 * = con.prepareStatement(sql); pst.setString(1,
	 * sdf.format(fcvto.getTime())); pst.setString(2,
	 * sdf.format(frem.getTime())); pst.setInt(3, numeroContrato);
	 * pst.executeUpdate(); Pago_Contrato pago = new Pago_Contrato(
	 * Integer.parseInt(lblNumeroContrato.getText()), sdf.format(vcto),
	 * sdf.format(hoy), 0.00, Double.parseDouble(Redondeo.redondearCentimos(fd
	 * .format(Double.parseDouble(lblTotalInteres .getText())))),
	 * Double.parseDouble(Redondeo .redondearCentimos(fd.format(Double
	 * .parseDouble(lblTotalMora.getText()))))); pago.grabarPago();
	 * retirarMora(numeroContrato); registrarIngresoInteresMoraCaja();
	 * actualizarContratos(fcvto.getTime()); JOptionPane.showMessageDialog(
	 * null, "<html><h1 style='color: red;'>Próximo Vencimiento : " + new
	 * SimpleDateFormat("dd-MMMM-yyyy").format(
	 * proximo_vencimiento).toUpperCase() + "</h1></html>"); internalClose(); }
	 * catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void retirarMora(int contrato) { try { for (int i = 0; i <
	 * modeloMora.getRowCount(); i++) { int estado =
	 * Integer.parseInt(modeloMora.getValueAt(i, 2) .toString()); if (estado ==
	 * 1) { String sql =
	 * "UPDATE tb_mora SET est_mora=0 WHERE id_con=? AND id_mora=?";
	 * PreparedStatement pst = con.prepareStatement(sql); pst.setInt(1,
	 * contrato); pst.setInt(2, Integer.parseInt(modeloMora.getValueAt(i, 0)
	 * .toString())); pst.executeUpdate(); } } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * public void internalClose() { this.dispose(); }
	 * 
	 * public void registrarIngresoInteresMoraCaja() { double cambio =
	 * (lblTipoMoneda.getText().equalsIgnoreCase("$")) ? obtenerCambio() : 1;
	 * String moneda = (cambio == 1) ? "" : ">($)"; Ingreso mora = new
	 * Ingreso(Principal.id_libro_caja, lblP.getText() + "-" +
	 * lblNumeroContrato.getText() + moneda, calcularCantidadCuotasPagadas() +
	 * "% + M", 0.00,
	 * (Double.parseDouble(Redondeo.redondearCentimos(fd.format(Double
	 * .parseDouble(lblTotalInteres.getText())))) + Double
	 * .parseDouble(Redondeo.redondearCentimos(fd
	 * .format(Double.parseDouble(lblTotalMora .getText()))))) cambio, 0.00);
	 * mora.registrarIngreso(); }
	 * 
	 * public void actualizarContratos(Date fecha_vencimiento) { try {
	 * System.out.println(fecha_vencimiento); Date hoy = new Date();
	 * GregorianCalendar gcPre = new GregorianCalendar(); GregorianCalendar
	 * gcPost = new GregorianCalendar();
	 * 
	 * gcPre.setTime(fecha_vencimiento); gcPost.setTime(fecha_vencimiento);
	 * gcPre.add(Calendar.MONTH, 1); gcPre.add(Calendar.DATE, -5);
	 * gcPost.add(Calendar.MONTH, 1); gcPost.add(Calendar.DATE, 16); Date
	 * periodoPreRiesgo = gcPre.getTime(); Date periodoPostRiesgo =
	 * gcPost.getTime(); if (hoy.after(periodoPostRiesgo)) { // A REMATE
	 * cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()), tabla[0],
	 * 13); } else if (periodoPostRiesgo.getTime() == hoy.getTime()) { // A POST
	 * cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()), tabla[0],
	 * 7); } else if (periodoPreRiesgo.getTime() == hoy.getTime() &&
	 * hoy.getTime() < fecha_vencimiento.getTime()) { // A PRE
	 * cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()), tabla[0],
	 * 4); } else if (hoy.after(fecha_vencimiento)) { // A VENCIDO
	 * cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()), tabla[0],
	 * 2); } else { // A ACTIVO
	 * cambiarEstado(Integer.parseInt(lblNumeroContrato.getText()), tabla[0],
	 * 1); } } catch (Exception e) { e.printStackTrace(); } }
	 */
}
