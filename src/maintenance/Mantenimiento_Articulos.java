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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXTable;

import common.Constantes;
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
	private JTextField txtObs;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtDescripcion;
	private JXTable tbArticulos;
	private JLabel txtId;
	private JTextField txtPrecioInterno;
	private JTextField txtSerie;
	private JTextField txtP;
	private JTextField txtContratoAsociado;
	private JTextField txtCapital;
	private JTextField txtPrecioVenta;
	private JLabel lblIdSC;
	private JPanel contenedor;

	public Mantenimiento_Articulos() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE ARTICULOS");
		this.setSize(520, 528);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1369, 971));
		this.setBounds(0, 0, 1369, 971);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1368, 946);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtDescripcion = new JTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(14, 12, 427, 60);
		txtDescripcion.setBorder(BorderFactory.createTitledBorder(null, "DESCRIPCIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDescripcion.setFont(new java.awt.Font("Segoe UI",1,16));
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));

		txtMarca = new JTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(448, 12, 247, 60);
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMarca.setFont(new java.awt.Font("Segoe UI",1,16));
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));
		txtMarca.setBorder(BorderFactory.createTitledBorder(null, "MARCA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtModelo = new JTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(701, 12, 271, 60);
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtModelo.setFont(new java.awt.Font("Segoe UI",1,16));
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));
		txtModelo.setBorder(BorderFactory.createTitledBorder(null, "MODELO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtObs = new JTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(12, 78, 1213, 60);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI",1,16));
		txtObs.setForeground(new java.awt.Color(0, 64, 128));
		txtObs.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIONES",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(882, 144, 70, 64);
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
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
						a.setPrecioVenta(new BigDecimal(txtPrecioVenta.getText()));
						a.setPrecioInterno(new BigDecimal(txtPrecioInterno.getText()));
						a.setContrato(txtP.getText() + "-" + txtContratoAsociado.getText());
						a.setCapitalContrato(new BigDecimal(txtCapital.getText()));
						a.setEArticulo(new EArticulo(5));
						a.setFechaCreacion(String.valueOf(LocalDate.now()));
						a.setUsuarioCreacion(Principal.LOGGED.getLogin());
						new ArticuloController().RegistrarArticulo(a);
						ListarArticulos();
						Utiles.Limpiar(contenedor);
						Utiles.Mensaje("Artículo registrado.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					Utiles.Mensaje("Ingrese un número correcto.", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					Utiles.Mensaje("Error al registrar nuevo artículo.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnActualizar = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnActualizar);
		btnActualizar.setBounds(964, 144, 70, 64);
		btnActualizar.setEnabled(false);
		btnActualizar.setOpaque(false);
		btnActualizar.setBorderPainted(false);
		btnActualizar.setContentAreaFilled(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
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
						a.setPrecioVenta(new BigDecimal(txtPrecioVenta.getText()));
						a.setPrecioInterno(new BigDecimal(txtPrecioInterno.getText()));
						a.setContrato(txtP.getText() + "-" + txtContratoAsociado.getText());
						a.setCapitalContrato(new BigDecimal(txtCapital.getText()));
						a.setFechaModificacion(String.valueOf(LocalDate.now()));
						a.setUsuarioModificacion(Principal.LOGGED.getLogin());
						a.setEArticulo(new EArticulo(5));
						new ArticuloController().RegistrarArticulo(a);
						ListarArticulos();
						Utiles.Limpiar(contenedor);
						Utiles.Mensaje("Artículo actualizado.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					Utiles.Mensaje("Ingrese un número correcto.", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					Utiles.Mensaje("Error al registrar nuevo artículo.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/eliminar.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(1046, 144, 70, 64);
		btnEliminar.setOpaque(false);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Articulo a = new Articulo();
				a.setId(Integer.parseInt(txtId.getText()));
				a.setEArticulo(new EArticulo(8));
				new ArticuloController().RegistrarArticulo(a);
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 216, 1322, 513);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbArticulos = new JXTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setModel(Constantes.MtoArticuloModel);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbArticulos.getSelectedRow();
					txtId.setText(tbArticulos.getValueAt(fila, 0).toString());
					txtDescripcion.setText(tbArticulos.getValueAt(fila, 1).toString());
					txtMarca.setText(tbArticulos.getValueAt(fila, 2).toString());
					txtModelo.setText(tbArticulos.getValueAt(fila, 3).toString());
					txtSerie.setText(tbArticulos.getValueAt(fila, 4).toString());
					txtObs.setText(tbArticulos.getValueAt(fila, 5).toString());
					txtP.setText(String.valueOf(tbArticulos.getValueAt(fila, 6)).split("-")[0]);
					txtContratoAsociado.setText(String.valueOf(tbArticulos.getValueAt(fila, 6)).split("-")[1]);
					txtCapital.setText(tbArticulos.getValueAt(fila, 7).toString());
					txtPrecioVenta.setText(tbArticulos.getValueAt(fila, 8).toString());
					txtPrecioInterno.setText(tbArticulos.getValueAt(fila, 9).toString());
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

		txtPrecioVenta = new JTextField();
		contenedor.add(txtPrecioVenta);
		txtPrecioVenta.setBounds(12, 145, 192, 60);
		txtPrecioVenta.setFont(new java.awt.Font("Segoe UI",1,16));
		txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioVenta.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioVenta.setBorder(BorderFactory.createTitledBorder(null, "PRECIO VENTA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtCapital = new JTextField();
		contenedor.add(txtCapital);
		txtCapital.setBounds(678, 144, 192, 60);
		txtCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtCapital.setFont(new java.awt.Font("Segoe UI",1,16));
		txtCapital.setForeground(new java.awt.Color(0, 64, 128));
		txtCapital.setBorder(BorderFactory.createTitledBorder(null, "CAPITAL",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtContratoAsociado = new JTextField();
		contenedor.add(txtContratoAsociado);
		txtContratoAsociado.setBounds(504, 144, 162, 50);
		txtContratoAsociado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtContratoAsociado.setFont(new java.awt.Font("Segoe UI",1,16));
		txtContratoAsociado.setForeground(new java.awt.Color(0, 64, 128));
		txtContratoAsociado.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtP = new JTextField();
		contenedor.add(txtP);
		txtP.setBounds(426, 144, 66, 60);
		txtP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtP.setFont(new java.awt.Font("Segoe UI",1,16));
		txtP.setForeground(new java.awt.Color(0, 64, 128));
		txtP.setBorder(BorderFactory.createTitledBorder(null, "LETRA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtSerie = new JTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI",1,16));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBounds(978, 12, 247, 60);
		txtSerie.setBorder(BorderFactory.createTitledBorder(null, "SERIE",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtPrecioInterno = new JTextField();
		contenedor.add(txtPrecioInterno);
		txtPrecioInterno.setFont(new java.awt.Font("Segoe UI",1,16));
		txtPrecioInterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioInterno.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioInterno.setBounds(222, 144, 192, 60);
		txtPrecioInterno.setBorder(BorderFactory.createTitledBorder(null, "PRECIO INTERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setVisible(false);
		txtId.setBounds(12, 5, 10, 10);

		ListarArticulos();
	}

	public void ListarArticulos() {
		Constantes.MtoArticuloModel.setRowCount(0);
		for (Articulo a : new ArticuloController().ListarArticulos()) {
			Constantes.MtoArticuloModel.addRow(new Object[] { a.getId(), a.getDescripcion(), a.getMarca(),
					a.getModelo(), a.getSerie(), a.getObs(), a.getContrato(), a.getCapitalContrato(),
					a.getPrecioVenta(), a.getPrecioInterno(), a.getEArticulo().getDescripcion() });
		}
		tbArticulos.setModel(Constantes.MtoArticuloModel);
	}

}
