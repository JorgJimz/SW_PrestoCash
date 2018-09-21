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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXSearchField;
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
	private JLabel jLabel10;
	private JTextField txtModelo;
	private JLabel jLabel9;
	private JTextField txtMarca;
	private JLabel jLabel8;
	private JTextField txtDescripcion;
	private JLabel jLabel7;
	private JXTable tbArticulos;
	private JLabel jLabel3;
	private JLabel txtId;
	private JTextField txtPrecioInterno;
	private JLabel jLabel4;
	private JTextField txtSerie;
	private JLabel jLabel1;
	private JTextField txtP;
	private JTextField txtContratoAsociado;
	private JTextField txtCapital;
	private JLabel jLabel11;
	private JLabel jLabel6;
	private JXSearchField sfArticulos;
	private JTextField txtPrecioVenta;
	private JLabel jLabel2;
	private JLabel lblIdSC;
	private JPanel contenedor;
	private JPopupMenu popBusqueda;

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

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("DESCRIPCIÓN");
		jLabel7.setBounds(12, 18, 162, 39);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		txtDescripcion = new JTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(13, 57, 427, 39);
		txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("MARCA");
		jLabel8.setBounds(453, 18, 247, 39);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		txtMarca = new JTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(453, 57, 247, 39);
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("MODELO");
		jLabel9.setBounds(713, 18, 247, 39);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		txtModelo = new JTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(713, 57, 271, 39);
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("OBSERVACIONES");
		jLabel10.setBounds(13, 102, 858, 39);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		txtObs = new JTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(12, 141, 1230, 39);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtObs.setForeground(new java.awt.Color(0, 64, 128));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(1261, 32, 70, 64);
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
		btnActualizar.setBounds(1261, 116, 70, 64);
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
		btnEliminar.setBounds(1261, 205, 70, 64);
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
		jScrollPane1.setBounds(12, 281, 1319, 633);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbArticulos = new JXTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(35);
		tbArticulos.setModel(Constantes.MtoArticuloModel);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 24));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 24));
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

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("PRECIO VENTA");
		jLabel2.setBounds(12, 190, 176, 39);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		txtPrecioVenta = new JTextField();
		contenedor.add(txtPrecioVenta);
		txtPrecioVenta.setBounds(12, 229, 192, 39);
		txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioVenta.setForeground(new java.awt.Color(0, 64, 128));

		sfArticulos = new JXSearchField();
		contenedor.add(sfArticulos);
		sfArticulos.setFindPopupMenu(popBusqueda);
		sfArticulos.setBounds(889, 230, 353, 39);
		sfArticulos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		sfArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		sfArticulos.setForeground(new java.awt.Color(0, 64, 128));
		sfArticulos.setPrompt("BUSCAR");

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CONTRATO");
		jLabel6.setBounds(432, 186, 142, 39);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("CAPITAL");
		jLabel11.setBounds(691, 186, 192, 39);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		txtCapital = new JTextField();
		contenedor.add(txtCapital);
		txtCapital.setBounds(691, 230, 192, 39);
		txtCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtCapital.setForeground(new java.awt.Color(0, 64, 128));

		txtContratoAsociado = new JTextField();
		contenedor.add(txtContratoAsociado);
		txtContratoAsociado.setBounds(517, 230, 162, 39);
		txtContratoAsociado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtContratoAsociado.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtContratoAsociado.setForeground(new java.awt.Color(0, 64, 128));

		txtP = new JTextField();
		contenedor.add(txtP);
		txtP.setBounds(432, 231, 66, 39);
		txtP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtP.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtP.setForeground(new java.awt.Color(0, 64, 128));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("-");
		jLabel3.setBounds(498, 230, 19, 39);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("SERIE");
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel1.setBounds(996, 18, 247, 39);

		txtSerie = new JTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBounds(996, 57, 247, 39);

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("PRECIO INTERNO");
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel4.setBounds(222, 190, 192, 39);

		txtPrecioInterno = new JTextField();
		contenedor.add(txtPrecioInterno);
		txtPrecioInterno.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtPrecioInterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioInterno.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioInterno.setBounds(222, 228, 192, 39);

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
