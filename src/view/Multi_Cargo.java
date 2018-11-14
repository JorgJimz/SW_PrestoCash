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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import common.ComboItem;
import common.EditorDS;
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
import model.EArticulo;
import model.Sede;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("serial")
public class Multi_Cargo extends JInternalFrame {
	private JPanel contenedor;
	private JTextField txtNumeroContrato;
	private JButton btnBuscar;
	private JScrollPane spArticulos;
	private JTable tbArticulos;
	private JScrollPane spCargo;
	private JTextField txtObservaciones;
	private JTextField txtTransportista;
	private JButton btnGrabar;
	private JTable tbCargo;
	private JSeparator jSeparator1;
	Cargo cargo;
	List<DetalleCargo> detalle = new ArrayList<DetalleCargo>();

	public DefaultTableModel ArticulosMultiCargoModel = new DefaultTableModel(null,
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

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1177, 700);
		this.setTitle("GENERAR CARGO");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(839, 686));
		this.setBounds(0, 0, 839, 686);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "<html><h3><b>¿Salir?</b></h3></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 838, 661);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtNumeroContrato = new JTextField();
		contenedor.add(txtNumeroContrato);
		txtNumeroContrato.setBounds(20, 14, 340, 60);
		txtNumeroContrato.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO DE CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		txtNumeroContrato.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ObtenerDetalleContrato(txtNumeroContrato.getText());
				}
			}
		});

		spArticulos = new JScrollPane();
		contenedor.add(spArticulos);
		spArticulos.setBounds(20, 86, 790, 220);
		spArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spArticulos.setBackground(new java.awt.Color(255, 255, 255));
		tbArticulos = new JTable();
		tbArticulos.setModel(ArticulosMultiCargoModel);
		tbArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbArticulos.setDefaultRenderer(Object.class, new RenderCA());
		spArticulos.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbArticulos.getSelectedRow();
					int contratoId = Integer.parseInt(ArticulosMultiCargoModel.getValueAt(fila, 0).toString());
					String flagNumero = ArticulosMultiCargoModel.getValueAt(fila, 1).toString();
					int articuloId = Integer.parseInt(ArticulosMultiCargoModel.getValueAt(fila, 2).toString());
					Contrato cAsociado = new Contrato();
					cAsociado.setId(contratoId);
					cAsociado.setFlag(flagNumero.split("-")[0]);
					cAsociado.setNumero(Integer.parseInt(flagNumero.split("-")[1]));
					Articulo aAsociado = new ArticuloController().ObtenerArticulo(articuloId);
					aAsociado.setExEArticulo(aAsociado.getEArticulo());
					aAsociado.setEArticulo(new EArticulo(4, "CON CARGO"));
					DetalleCargo dcc = new DetalleCargo();
					dcc.setId(new Random().nextInt(100));
					dcc.setContrato(cAsociado);
					dcc.setArticulo(aAsociado);
					dcc.setCargo(cargo);
					Sede ss = new Sede();
					ss.setDescripcion("SELECCIONAR");
					dcc.setSede(ss);
					detalle.add(dcc);
					txtNumeroContrato.setText("");
					txtNumeroContrato.requestFocus();
					ListarCargo();
				}
			}
		});

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(20, 316, 790, 10);

		spCargo = new JScrollPane();
		contenedor.add(spCargo);
		spCargo.setBounds(20, 332, 790, 220);
		spCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
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
					int SelectedId = Integer.parseInt(MultiCargoModel.getValueAt(fila, 0).toString());
					for (DetalleCargo dc : detalle) {
						if (dc.getId() == SelectedId) {
							Sede s = new Sede(((ComboItem) MultiCargoModel.getValueAt(fila, 3)).getId());
							s.setDescripcion(((ComboItem) MultiCargoModel.getValueAt(fila, 3)).getDescripcion());
							dc.setSede(s);
						}
					}
				}
			}
		});
		tbCargo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					int opc = JOptionPane.showConfirmDialog(null,
							"<html><h3>¿Está seguro de retirar el artículo seleccionado del Cargo?</h3></html>",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opc == JOptionPane.YES_OPTION) {
						int fila = tbCargo.getSelectedRow();
						int SelectedId = Integer.parseInt(MultiCargoModel.getValueAt(fila, 0).toString());
						for (DetalleCargo dc : detalle) {
							if (dc.getId() == SelectedId) {
								detalle.remove(dc);
								break;
							}
						}
						ListarCargo();
					}
				}
			}
		});

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setBounds(746, 564, 64, 64);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (detalle.size() > 0) {
					cargo.setFecha(String.valueOf(LocalDate.now()));
					cargo.setTransportista(txtTransportista.getText().toUpperCase());
					cargo.setObs(txtObservaciones.getText().toUpperCase());
					cargo.setUsuarioCreacion(Principal.LOGGED.getLogin());
					cargo.setFechaCreacion(String.valueOf(LocalDate.now()));
					cargo.setDetalleCargos(detalle);
					new CargoController().GenerarCargo(cargo);
					Utiles.Mensaje(
							"Cargo registrado. Favor de colocar papel en la impresora para la impresión de la constancia.",
							JOptionPane.INFORMATION_MESSAGE);
					ImprimirCargo();
					dispose();
				} else {
					Utiles.Mensaje("Agregue como mínimo un artículo al Cargo.", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		txtTransportista = new JTextField();
		contenedor.add(txtTransportista);
		txtTransportista.setBounds(20, 564, 260, 60);
		txtTransportista.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTransportista.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTransportista.setBorder(BorderFactory.createTitledBorder(null, "TRANSPORTADO POR",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtObservaciones = new JTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(292, 564, 442, 60);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtObservaciones.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIONES",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnBuscar = new JButton(new ImageIcon("img/buscar_historial.png"));
		contenedor.add(btnBuscar);
		btnBuscar.setBounds(372, 11, 65, 65);
		btnBuscar.setOpaque(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObtenerDetalleContrato(txtNumeroContrato.getText());
			}
		});

	}

	public void ListarCargo() {
		MultiCargoModel.setRowCount(0);
		for (DetalleCargo dc : detalle) {
			MultiCargoModel
					.addRow(new Object[] { dc.getId(), dc.getContrato().getFlag() + "-" + dc.getContrato().getNumero(),
							dc.getArticulo().getDescripcion(), dc.getSede().getDescripcion() });
		}
		tbCargo.setModel(MultiCargoModel);
	}

	public void ObtenerDetalleContrato(String contrato) {
		if (!contrato.isEmpty()) {
			Contrato cn = new ContratoController().CargarContrato(contrato.split("-")[0],
					Integer.parseInt(contrato.split("-")[1]));
			if (Objects.nonNull(cn)) {
				ArticulosMultiCargoModel.setRowCount(0);
				for (DetalleContrato dc : cn.getDetalleContratos()) {
					ArticulosMultiCargoModel.addRow(new Object[] {
							dc.getContrato().getId(), dc.getContrato().getFlag() + "-" + dc.getContrato().getNumero(),
							dc.getArticulo().getId(), dc.getArticulo().getDescripcion() + " "
									+ dc.getArticulo().getMarca() + " " + dc.getArticulo().getModelo(),
							dc.getArticulo().getEArticulo().getDescripcion() });
				}
				tbArticulos.setModel(ArticulosMultiCargoModel);
			} else {
				Utiles.Mensaje(String.format("El contrato %s no existe. Por favor, verificar.", contrato),
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			Utiles.Mensaje("Ingrese Número de Contrato", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void ImprimirCargo() {
		ArrayList<Cargo> arreglo_cargo = new ArrayList<Cargo>();
		arreglo_cargo.add(cargo);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/cargo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(arreglo_cargo));
			JasperPrintManager.printReport(jasperPrint, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
