package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.lang3.RandomStringUtils;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

import common.ComboItem;
import common.EditorDS;
import common.JIconTextField;
import common.RenderCA;
import common.RenderDS;
import common.Utiles;
import controller.ArticuloController;
import controller.CargoController;
import controller.ContratoController;
import model.Articulo;
import model.Cargo;
import model.Contrato;
import model.DetalleCargo;
import model.DetalleContrato;
import model.Sede;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings({ "serial", "deprecation" })
public class Multi_Cargo extends JInternalFrame {
	private JPanel contenedor;
	private JButton btnNuevoCargo;
	private JButton btnMenos;
	private JButton btnMas;
	private JButton btnImprimir;
	private JXSearchField txtNumeroContrato;
	private JXSearchField txtNumeroCargo;
	private JScrollPane spArticulos;
	private JTable tbArticulos;
	private JScrollPane spCargo;
	private JIconTextField txtObservaciones;
	private JIconTextField txtTransportista;
	private JButton btnGrabar;
	private JTable tbCargo;
	Cargo cargo;
	List<DetalleCargo> detalle = new ArrayList<DetalleCargo>();
	JInternalFrame frame;

	TableColumn ArticuloId_Column;
	TableColumn ContratoId_Column;

	public DefaultTableModel ArticulosMultiCargoModel = new DefaultTableModel(
			null,
			new String[] { "CID", "CONTRATO", "AID", "ARTÍCULO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public DefaultTableModel MultiCargoModel = new DefaultTableModel(null,
			new String[] { "ID", "CONTRATO", "ARTÍCULO", "DESTINO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (colIndex == 3) {
				return true;
			}
			return false;
		}
	};

	public Multi_Cargo() {

		cargo = new Cargo();
		frame = this;
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("GENERAR CARGO");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.setSize(710, 700);
		this.setBounds(0, 0, 710, 700);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane.showConfirmDialog(null,
						"<html><h3><b>¿Salir?</b></h3></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1352, 675);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtNumeroContrato = new JXSearchField();
		contenedor.add(txtNumeroContrato);
		txtNumeroContrato.setBounds(693, 22, 384, 60);
		txtNumeroContrato
				.setPrompt("Digite, luego presione ENTER o clic en la LUPA");
		txtNumeroContrato.setBorder(BorderFactory.createTitledBorder(null,
				"NÚMERO DE CONTRATO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		txtNumeroContrato.setSearchMode(SearchMode.REGULAR);
		txtNumeroContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObtenerDetalleContrato(txtNumeroContrato.getText());
			}
		});

		spArticulos = new JScrollPane();
		contenedor.add(spArticulos);
		spArticulos.setBounds(693, 95, 596, 542);
		spArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		spArticulos.setBackground(new java.awt.Color(255, 255, 255));
		tbArticulos = new JTable();
		tbArticulos.setModel(ArticulosMultiCargoModel);
		tbArticulos.setDefaultRenderer(Object.class, new RenderCA());
		ContratoId_Column = tbArticulos.getColumnModel().getColumn(0);
		ArticuloId_Column = tbArticulos.getColumnModel().getColumn(2);
		tbArticulos.getColumnModel().removeColumn(ContratoId_Column);
		tbArticulos.getColumnModel().removeColumn(ArticuloId_Column);
		tbArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		spArticulos.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					AgregarArticulo();
				}
			}
		});

		spCargo = new JScrollPane();
		contenedor.add(spCargo);
		spCargo.setBounds(12, 95, 662, 388);
		spCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		spCargo.setBackground(new java.awt.Color(255, 255, 255));

		tbCargo = new JTable();
		spCargo.setViewportView(tbCargo);
		tbCargo.setModel(MultiCargoModel);
		tbCargo.putClientProperty("terminateEditOnFocusLost", true);
		tbCargo.setDefaultEditor(Object.class, new EditorDS());
		tbCargo.setRowHeight(25);
		tbCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbCargo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbCargo.setDefaultRenderer(Object.class, new RenderDS());
		tbCargo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbCargo.getTableHeader().setForeground(new Color(181, 0, 0));
		tbCargo.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int fila = tbCargo.getSelectedRow();
					int SelectedId = Integer.parseInt(MultiCargoModel
							.getValueAt(fila, 0).toString());
					for (DetalleCargo dc : detalle) {
						if (dc.getId() == SelectedId) {
							Sede s = new Sede(((ComboItem) MultiCargoModel
									.getValueAt(fila, 3)).getId());
							s.setDescripcion(((ComboItem) MultiCargoModel
									.getValueAt(fila, 3)).getDescripcion());
							dc.setSede(s);
						}
					}
				}
			}
		});
		tbCargo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE
						|| e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					QuitarArticulo();
				}
			}
		});

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setVisible(false);
		btnGrabar.setText("GRABAR CARGO");
		btnGrabar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setBounds(466, 12, 208, 70);
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utiles.Validar(contenedor)) {
					if (detalle.size() > 0) {
						if (ValidarDestinos()) {
							cargo.setFecha(String.valueOf(LocalDate.now()));
							cargo.setTransportista(txtTransportista.getText()
									.trim().toUpperCase());
							cargo.setObs(txtObservaciones.getText().trim()
									.toUpperCase());
							cargo.setUsuarioCreacion(Principal.LOGGED
									.getLogin());
							cargo.setFechaCreacion(String.valueOf(LocalDate
									.now()));
							cargo.setDetalleCargos(detalle);
							new CargoController().GenerarCargo(cargo);
							Utiles.Mensaje(
									"Cargo registrado. Favor de colocar papel en la impresora para la impresión de la constancia.",
									JOptionPane.INFORMATION_MESSAGE);
							ImprimirCargo(cargo, true);
							dispose();
						} else {
							Utiles.Mensaje(
									"Una o más prendas no tienen destino. Por favor, verifique.",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						Utiles.Mensaje(
								"Agregue como mínimo un artículo al cargo.",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					Utiles.Mensaje("Complete el formulario.",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtTransportista = new JIconTextField();
		contenedor.add(txtTransportista);
		txtTransportista.setBounds(12, 500, 413, 60);
		txtTransportista.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTransportista.setEnabled(false);
		txtTransportista.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTransportista.setBorder(BorderFactory.createTitledBorder(null,
				"TRANSPORTADO POR", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtObservaciones = new JIconTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(12, 577, 413, 60);
		txtObservaciones.setEnabled(false);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtObservaciones.setBorder(BorderFactory.createTitledBorder(null,
				"OBSERVACIONES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnImprimir = new JButton(new ImageIcon("img/printer.png"));
		contenedor.add(btnImprimir);
		btnImprimir.setBorderPainted(false);
		btnImprimir.setEnabled(false);
		btnImprimir.setText("IMPRIMIR CARGO");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnImprimir.setContentAreaFilled(false);
		btnImprimir.setOpaque(false);
		btnImprimir.setBounds(437, 495, 237, 70);
		btnImprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cargo c = new CargoController().ConsultarCargo(Integer
						.parseInt(txtNumeroCargo.getText()));
				ImprimirCargo(c, false);
			}
		});

		txtNumeroCargo = new JXSearchField();
		contenedor.add(txtNumeroCargo);
		txtNumeroCargo.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtNumeroCargo.setForeground(new java.awt.Color(0, 64, 128));
		txtNumeroCargo.setSearchMode(SearchMode.REGULAR);
		txtNumeroCargo
				.setPrompt("Digite, luego presione ENTER o clic en la LUPA");
		txtNumeroCargo.setBorder(BorderFactory.createTitledBorder(null,
				"NÚMERO DE CARGO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNumeroCargo.setBounds(12, 22, 442, 60);
		txtNumeroCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultarCargo();
			}
		});

		btnMas = new JButton(new ImageIcon("img/mas.png"));
		contenedor.add(btnMas);
		btnMas.setBorderPainted(false);
		btnMas.setContentAreaFilled(false);
		btnMas.setText("AGREGAR PRENDA");
		btnMas.setHorizontalAlignment(SwingConstants.LEFT);
		btnMas.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnMas.setOpaque(false);
		btnMas.setEnabled(false);
		btnMas.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMas.setBounds(1089, 12, 200, 70);
		btnMas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarArticulo();
			}
		});

		btnMenos = new JButton(new ImageIcon("img/menos.png"));
		contenedor.add(btnMenos);
		btnMenos.setBorderPainted(false);
		btnMenos.setContentAreaFilled(false);
		btnMenos.setText("QUITAR PRENDA");
		btnMenos.setHorizontalAlignment(SwingConstants.LEFT);
		btnMenos.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnMenos.setOpaque(false);
		btnMenos.setEnabled(false);
		btnMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMenos.setBounds(437, 579, 237, 70);
		btnMenos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuitarArticulo();
			}
		});

		btnNuevoCargo = new JButton(new ImageIcon("img/clipboard.png"));
		contenedor.add(btnNuevoCargo);
		btnNuevoCargo.setText("NUEVO CARGO");
		btnNuevoCargo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevoCargo.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnNuevoCargo.setContentAreaFilled(false);
		btnNuevoCargo.setBorderPainted(false);
		btnNuevoCargo.setOpaque(false);
		btnNuevoCargo.setBounds(466, 12, 208, 70);
		btnNuevoCargo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNuevoCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNumeroCargo.setText("");
				txtNumeroCargo.setEnabled(false);
				btnGrabar.setVisible(true);
				btnNuevoCargo.setVisible(false);
				btnImprimir.setEnabled(false);
				MultiCargoModel.setRowCount(0);
				frame.setSize(1330, 700);
			}
		});

	}

	public void ListarCargo() {
		MultiCargoModel.setRowCount(0);
		for (DetalleCargo dc : detalle) {
			MultiCargoModel.addRow(new Object[] {
					dc.getAlphaId(),
					dc.getContrato().getFlag() + "-"
							+ dc.getContrato().getNumero(),
					dc.getArticulo().getDescripcion(),
					dc.getSede().getDescripcion() });
		}
		tbCargo.setModel(MultiCargoModel);
		btnMenos.setEnabled(detalle.size() > 0);
		btnGrabar.setEnabled(detalle.size() > 0);
	}

	public void ConsultarCargo() {
		if (!txtNumeroCargo.getText().trim().equalsIgnoreCase("")) {
			MultiCargoModel.setRowCount(0);
			Cargo c = new CargoController().ConsultarCargo(Integer
					.parseInt(txtNumeroCargo.getText()));
			if (Objects.nonNull(c)) {
				btnImprimir.setEnabled(true);
				for (DetalleCargo dc : c.getDetalleCargos()) {
					MultiCargoModel.addRow(new Object[] {
							dc.getId(),
							dc.getContrato().getFlag() + "-"
									+ dc.getContrato().getNumero(),
							dc.getArticulo().getDescripcion(),
							dc.getSede().getDescripcion() });
				}
				tbCargo.setModel(MultiCargoModel);
			} else {
				Utiles.Mensaje("No existe cargo.", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			txtNumeroCargo.requestFocus();
			Utiles.Mensaje("Ingrese un número de cargo.",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void AgregarArticulo() {
		int fila = tbArticulos.getSelectedRow();
		int contratoId = Integer.parseInt(ArticulosMultiCargoModel.getValueAt(
				fila, 0).toString());
		String flagNumero = ArticulosMultiCargoModel.getValueAt(fila, 1)
				.toString();
		int articuloId = Integer.parseInt(ArticulosMultiCargoModel.getValueAt(
				fila, 2).toString());
		boolean exists = detalle.stream().anyMatch(
				t -> t.getArticulo().getId() == articuloId);
		if (!exists) {
			btnMenos.setEnabled(true);
			btnImprimir.setEnabled(false);
			txtObservaciones.setEnabled(true);
			txtTransportista.setEnabled(true);
			Contrato cAsociado = new Contrato();
			cAsociado.setId(contratoId);
			cAsociado.setFlag(flagNumero.split("-")[0]);
			cAsociado.setNumero(Integer.parseInt(flagNumero.split("-")[1]));
			Articulo aAsociado = new ArticuloController()
					.ObtenerArticulo(articuloId);
			DetalleCargo dcc = new DetalleCargo();
			dcc.setAlphaId(RandomStringUtils.randomAlphabetic(5));
			dcc.setContrato(cAsociado);
			dcc.setArticulo(aAsociado);
			dcc.setCargo(cargo);
			Sede ss = new Sede();
			ss.setId(0);
			ss.setDescripcion("SELECCIONAR");
			dcc.setSede(ss);
			detalle.add(dcc);
			txtNumeroContrato.setText("");
			txtNumeroContrato.requestFocus();
			ListarCargo();
		} else {
			Utiles.Mensaje("Esta prenda ya ha sido agregada al cargo actual.",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void QuitarArticulo() {
		int opc = JOptionPane
				.showConfirmDialog(
						null,
						"<html><h3>¿Está seguro de retirar el artículo seleccionado del Cargo?</h3></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
		if (opc == JOptionPane.YES_OPTION) {
			int fila = tbCargo.getSelectedRow();
			String SelectedId = String.valueOf(MultiCargoModel.getValueAt(fila,
					0));
			for (DetalleCargo dc : detalle) {
				if (dc.getAlphaId().equals(SelectedId)) {
					detalle.remove(dc);
					break;
				}
			}
			ListarCargo();
		}
	}

	public boolean ValidarDestinos() {
		boolean flag = true;
		for (DetalleCargo dc : detalle) {
			if (dc.getSede().getId() == 0) {
				flag = false;
			}
		}
		return flag;
	}

	public void ObtenerDetalleContrato(String contrato) {
		if (!contrato.isEmpty()) {
			Contrato cn = new ContratoController().CargarContrato(
					contrato.split("-")[0],
					Integer.parseInt(contrato.split("-")[1]));
			if (Objects.nonNull(cn)) {
				ArticulosMultiCargoModel.setRowCount(0);
				for (DetalleContrato dc : cn.getDetalleContratos()) {
					ArticulosMultiCargoModel.addRow(new Object[] {
							dc.getContrato().getId(),
							dc.getContrato().getFlag() + "-"
									+ dc.getContrato().getNumero(),
							dc.getArticulo().getId(),
							dc.getArticulo().getDescripcion() + " "
									+ dc.getArticulo().getMarca() + " "
									+ dc.getArticulo().getModelo(),
							dc.getArticulo().getEArticulo().getDescripcion() });
				}
				tbArticulos.setModel(ArticulosMultiCargoModel);
				btnMas.setEnabled(true);
			} else {
				Utiles.Mensaje(String.format(
						"El contrato %s no existe. Por favor, verificar.",
						contrato), JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			Utiles.Mensaje("Ingrese Número de Contrato ...",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void ImprimirCargo(Cargo p_cargo, boolean dispose) {
		ArrayList<Cargo> arreglo_cargo = new ArrayList<Cargo>();
		arreglo_cargo.add(p_cargo);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reports/cargo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					params, new JRBeanCollectionDataSource(arreglo_cargo));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
			if (dispose) {
				dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
