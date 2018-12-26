package maintenance;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.search.SearchFactory;

import common.JIconTextField;
import common.Utiles;
import controller.ArticuloController;
import model.Articulo;
import model.EArticulo;
import view.Principal;

@SuppressWarnings("serial")
public class Mantenimiento_Articulos extends JInternalFrame {

	private JScrollPane jScrollPane1;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnGrabar;
	private JXTable tbArticulos;
	private JLabel txtId;
	private JIconTextField txtObs;
	private JIconTextField txtModelo;
	private JIconTextField txtMarca;
	private JIconTextField txtDescripcion;
	private JIconTextField txtPrecioInterno;
	private JIconTextField txtSerie;
	private JIconTextField txtP;
	private JIconTextField txtContratoAsociado;
	private JIconTextField txtCapital;
	private JIconTextField txtPrecioVenta;
	private JLabel lblIdSC;
	private JPanel contenedor;
	private JButton btnBuscar;

	public DefaultTableModel MtoArticuloModel = new DefaultTableModel(null,
			new String[] { "ID", "DESCRIPCIÓN", "MARCA", "MODELO", "SERIE",
					"OBSERVACIONES", "CONTRATO", "CAPITAL", "P.VENTA",
					"P.INTERNO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Mantenimiento_Articulos() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE ARTICULOS");
		this.setSize(520, 528);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1369, 721));
		this.setBounds(0, 0, 1369, 721);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1368, 696);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtDescripcion = new JIconTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(14, 12, 286, 50);
		txtDescripcion.setBorder(BorderFactory.createTitledBorder(null,
				"DESCRIPCIÓN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));

		txtMarca = new JIconTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(317, 12, 223, 50);
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));
		txtMarca.setBorder(BorderFactory.createTitledBorder(null, "MARCA",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtModelo = new JIconTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(552, 12, 261, 50);
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));
		txtModelo.setBorder(BorderFactory.createTitledBorder(null, "MODELO",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtObs = new JIconTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(12, 78, 483, 50);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtObs.setForeground(new java.awt.Color(0, 64, 128));
		txtObs.setBorder(BorderFactory.createTitledBorder(null,
				"OBSERVACIONES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(1109, 68, 70, 64);
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnGrabar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Utiles.Validar(contenedor)) {
						Articulo a = new Articulo();
						a.setDescripcion(txtDescripcion.getText());
						a.setMarca(txtMarca.getText());
						a.setModelo(txtModelo.getText());
						a.setSerie(txtSerie.getText());
						a.setObs(txtObs.getText());
						a.setPrecioVenta(new BigDecimal(txtPrecioVenta
								.getText()));
						a.setPrecioInterno(new BigDecimal(txtPrecioInterno
								.getText()));
						a.setFlagContrato(txtP.getText());
						a.setNumeroContrato(Integer.parseInt(txtContratoAsociado.getText()));
						a.setCapitalContrato(new BigDecimal(txtCapital
								.getText()));
						a.setEArticulo(new EArticulo(5));
						a.setFechaCreacion(String.valueOf(LocalDate.now()));
						a.setUsuarioCreacion(Principal.LOGGED.getLogin());
						new ArticuloController().RegistrarArticulo(a);
						ListarArticulos();
						Utiles.Limpiar(contenedor);
						Utiles.Mensaje("Artículo registrado.",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						Utiles.Mensaje("Complete el formulario.",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					Utiles.Mensaje("Ingrese un número correcto.",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					Utiles.Mensaje("Error al registrar nuevo artículo.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnActualizar = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnActualizar);
		btnActualizar.setBounds(1186, 68, 70, 64);
		btnActualizar.setEnabled(false);
		btnActualizar.setOpaque(false);
		btnActualizar.setBorderPainted(false);
		btnActualizar.setContentAreaFilled(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnActualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Utiles.Validar(contenedor)) {
						Articulo a = new Articulo();
						a.setId(Integer.parseInt(txtId.getText()));
						a.setDescripcion(txtDescripcion.getText());
						a.setMarca(txtMarca.getText());
						a.setModelo(txtModelo.getText());
						a.setSerie(txtSerie.getText());
						a.setObs(txtObs.getText());
						a.setPrecioVenta(new BigDecimal(txtPrecioVenta
								.getText()));
						a.setPrecioInterno(new BigDecimal(txtPrecioInterno
								.getText()));
						a.setFlagContrato(txtP.getText());
						a.setNumeroContrato(Integer.parseInt(txtContratoAsociado.getText()));
						a.setCapitalContrato(new BigDecimal(txtCapital
								.getText()));
						a.setFechaModificacion(String.valueOf(LocalDate.now()));
						a.setUsuarioModificacion(Principal.LOGGED.getLogin());
						a.setEArticulo(new EArticulo(5));
						new ArticuloController().RegistrarArticulo(a);
						ListarArticulos();
						Utiles.Limpiar(contenedor);
						Utiles.Mensaje("Artículo actualizado.",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						Utiles.Mensaje("Complete el formulario.",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					Utiles.Mensaje("Ingrese un número correcto.",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					Utiles.Mensaje("Error al registrar nuevo artículo.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/eliminar.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(1264, 68, 70, 64);
		btnEliminar.setOpaque(false);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Articulo a = new Articulo();
				a.setId(Integer.parseInt(txtId.getText()));
				a.setEArticulo(new EArticulo(8));
				new ArticuloController().RegistrarArticulo(a);
			}
		});

		btnBuscar = new JButton();
		contenedor.add(btnBuscar);
		btnBuscar.setIcon(new ImageIcon("img/barcode.png"));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnBuscar.setOpaque(false);
		btnBuscar.setBounds(1032, 68, 70, 64);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFactory.getInstance().showFindDialog(tbArticulos,
						tbArticulos.getSearchable());
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 144, 1322, 522);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbArticulos = new JXTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setModel(MtoArticuloModel);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbArticulos.getSelectedRow();
					txtId.setText(tbArticulos.getValueAt(fila, 0).toString());
					txtDescripcion.setText(tbArticulos.getValueAt(fila, 1)
							.toString());
					txtMarca.setText(tbArticulos.getValueAt(fila, 2).toString());
					txtModelo.setText(tbArticulos.getValueAt(fila, 3)
							.toString());
					txtSerie.setText(tbArticulos.getValueAt(fila, 4).toString());
					txtObs.setText(tbArticulos.getValueAt(fila, 5).toString());
					txtP.setText(String
							.valueOf(tbArticulos.getValueAt(fila, 6))
							.split("-")[0]);
					txtContratoAsociado.setText(String.valueOf(
							tbArticulos.getValueAt(fila, 6)).split("-")[1]);
					txtCapital.setText(tbArticulos.getValueAt(fila, 7)
							.toString());
					txtPrecioVenta.setText(tbArticulos.getValueAt(fila, 8)
							.toString());
					txtPrecioInterno.setText(tbArticulos.getValueAt(fila, 9)
							.toString());
					btnActualizar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
			}
		});

		lblIdSC = new JLabel();
		lblIdSC.setBackground(Color.red);
		contenedor.add(lblIdSC);
		lblIdSC.setBounds(12, 301, 43, 16);
		lblIdSC.setVisible(false);

		txtPrecioVenta = new JIconTextField();
		contenedor.add(txtPrecioVenta);
		txtPrecioVenta.setBounds(503, 78, 169, 50);
		txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtPrecioVenta.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioVenta.setBorder(BorderFactory.createTitledBorder(null,
				"PRECIO VENTA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtCapital = new JIconTextField();
		contenedor.add(txtCapital);
		txtCapital.setBounds(853, 78, 169, 50);
		txtCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtCapital.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtCapital.setForeground(new java.awt.Color(0, 64, 128));
		txtCapital.setBorder(BorderFactory.createTitledBorder(null, "CAPITAL",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtContratoAsociado = new JIconTextField();
		contenedor.add(txtContratoAsociado);
		txtContratoAsociado.setBounds(1170, 12, 164, 50);
		txtContratoAsociado.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, new java.awt.Color(0, 0, 0)));
		txtContratoAsociado.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtContratoAsociado.setForeground(new java.awt.Color(0, 64, 128));
		txtContratoAsociado.setBorder(BorderFactory.createTitledBorder(null,
				"NÚMERO CONTRATO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtP = new JIconTextField();
		contenedor.add(txtP);
		txtP.setBounds(1100, 12, 64, 50);
		txtP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtP.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtP.setForeground(new java.awt.Color(0, 64, 128));
		txtP.setBorder(BorderFactory.createTitledBorder(null, "LETRA",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtSerie = new JIconTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBounds(827, 12, 261, 50);
		txtSerie.setBorder(BorderFactory.createTitledBorder(null, "SERIE",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtPrecioInterno = new JIconTextField();
		contenedor.add(txtPrecioInterno);
		txtPrecioInterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPrecioInterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtPrecioInterno.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioInterno.setBounds(678, 78, 169, 50);
		txtPrecioInterno.setBorder(BorderFactory.createTitledBorder(null,
				"PRECIO INTERNO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setVisible(false);
		txtId.setBounds(12, 5, 10, 10);

		ListarArticulos();
	}

	public void ListarArticulos() {
		MtoArticuloModel.setRowCount(0);
		for (Articulo a : new ArticuloController().ListarArticulos()) {
			MtoArticuloModel.addRow(new Object[] { a.getId(),
					a.getDescripcion(), a.getMarca(), a.getModelo(),
					a.getSerie(), a.getObs(),
					a.getFlagContrato() + "-" + a.getNumeroContrato(),
					a.getCapitalContrato(), a.getPrecioVenta(),
					a.getPrecioInterno(), a.getEArticulo().getDescripcion() });
		}
		tbArticulos.setModel(MtoArticuloModel);
	}

}
