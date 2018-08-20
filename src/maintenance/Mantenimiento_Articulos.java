package maintenance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Articulo;

import org.jdesktop.swingx.JXSearchField;

import com.toedter.calendar.JDateChooser;
import common.Constantes;

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
	private JTable tbArticulos;
	private JLabel jLabel1;
	private Connection con = MySQLConexion.getConexion();
	private JLabel jLabel3;
	private JTextField txtP;
	private JTextField txtContratoAsociado;
	private JTextField txtCapital;
	private JLabel jLabel11;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JDateChooser txtFechaPrecioInternet;
	private JTextField txtPrecioInternet;
	private JLabel jLabel4;
	private JXSearchField sfArticulos;
	private JTextField txtPrecioVenta;
	private JLabel jLabel2;
	private JLabel lblIdSC;
	private JLabel lblCodigo;
	private JPanel contenedor;
	private String filtro = "des_art";
	private JPopupMenu popBusqueda;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DefaultTableModel modeloArticulos = new DefaultTableModel(null,
			new String[] { "N°C.", "ID", "DESCRIPCIÓN", "MARCA", "MODELO",
					"OBSERVACIONES", "CAPITAL", "P.VENTA", "P.WEB", "FECHA" }) {
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
		this.setPreferredSize(new java.awt.Dimension(1278, 971));
		this.setBounds(0, 0, 1278, 971);
		{
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1276, 946);
			contenedor.setBackground(new java.awt.Color(255, 200, 147));
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CÓDIGO:");
			jLabel1.setBounds(12, 22, 108, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("DESCRIPCIÓN:");
			jLabel7.setBounds(299, 22, 162, 39);
			jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel7.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtDescripcion = new JTextField();
			contenedor.add(txtDescripcion);
			txtDescripcion.setBounds(461, 21, 517, 39);
			txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("MARCA:");
			jLabel8.setBounds(301, 74, 89, 39);
			jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel8.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtMarca = new JTextField();
			contenedor.add(txtMarca);
			txtMarca.setBounds(461, 73, 517, 39);
			txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtMarca.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel9 = new JLabel();
			contenedor.add(jLabel9);
			jLabel9.setText("MODELO:");
			jLabel9.setBounds(12, 74, 113, 39);
			jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel9.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtModelo = new JTextField();
			contenedor.add(txtModelo);
			txtModelo.setBounds(123, 73, 166, 39);
			txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtModelo.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel10 = new JLabel();
			contenedor.add(jLabel10);
			jLabel10.setText("OBSV:");
			jLabel10.setBounds(13, 135, 89, 39);
			jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel10.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtObs = new JTextField();
			contenedor.add(txtObs);
			txtObs.setBounds(120, 134, 858, 39);
			txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtObs.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtObs.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			btnGrabar = new JButton(new ImageIcon("img/registrarProducto.png"));
			contenedor.add(btnGrabar);
			btnGrabar.setText(" GRABAR");
			btnGrabar.setBounds(1001, 21, 242, 77);
			btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (validador(contenedor)) {
							grabarArticulo();
						} else {
							JOptionPane.showMessageDialog(null,
									"Complete los campos.");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		{
			btnActualizar = new JButton(new ImageIcon("img/editarProducto.png"));
			contenedor.add(btnActualizar);
			btnActualizar.setText(" EDITAR");
			btnActualizar.setBounds(1001, 114, 242, 77);
			btnActualizar.setEnabled(false);
			btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnActualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						actualizarArticulo();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
		{
			btnEliminar = new JButton(new ImageIcon("img/retirarProducto.png"));
			contenedor.add(btnEliminar);
			btnEliminar.setText(" RETIRAR");
			btnEliminar.setEnabled(false);
			btnEliminar.setBounds(1001, 210, 242, 77);
			btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					/* retirarArticulo(); */
				}
			});
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(12, 310, 1231, 604);
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
			{
				tbArticulos = new JTable();
				jScrollPane1.setViewportView(tbArticulos);
				tbArticulos.setRowHeight(35);
				tbArticulos.setModel(modeloArticulos);
				tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 24));
				tbArticulos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 24));
				tbArticulos.getTableHeader()
						.setForeground(new Color(181, 0, 0));
				tbArticulos.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							int fila = tbArticulos.getSelectedRow();
							lblCodigo.setText(tbArticulos.getValueAt(fila, 1)
									.toString());
							estadoArticulo(Integer.parseInt(lblCodigo.getText()));
							txtDescripcion.setText(tbArticulos.getValueAt(fila,
									2).toString());
							txtMarca.setText(tbArticulos.getValueAt(fila, 3)
									.toString());
							txtModelo.setText(tbArticulos.getValueAt(fila, 4)
									.toString());
							txtObs.setText(tbArticulos.getValueAt(fila, 5)
									.toString());
							txtPrecioVenta.setText(tbArticulos.getValueAt(fila,
									7).toString());
							txtPrecioInternet.setText(tbArticulos.getValueAt(
									fila, 8).toString());
							txtContratoAsociado.setText(tbArticulos.getValueAt(
									fila, 0).toString());
							txtCapital.setText(tbArticulos.getValueAt(fila, 6)
									.toString());
							txtP.setText(Constantes.ELECTRO);
							try {
								txtFechaPrecioInternet
										.setDate(new SimpleDateFormat(
												"yyyy-MM-dd")
												.parse(tbArticulos.getValueAt(
														fila, 9).toString()));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							btnActualizar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
					}
				});
			}
		}
		{
			lblCodigo = new JLabel();
			contenedor.add(lblCodigo);
			lblCodigo.setBounds(124, 23, 164, 39);
			lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCodigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
			lblCodigo.setOpaque(true);
			lblCodigo.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblIdSC = new JLabel();
			lblIdSC.setBackground(Color.red);
			contenedor.add(lblIdSC);
			lblIdSC.setBounds(12, 301, 43, 16);
			lblIdSC.setVisible(false);
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("PRECIO:");
			jLabel2.setBounds(12, 193, 108, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtPrecioVenta = new JTextField();
			contenedor.add(txtPrecioVenta);
			txtPrecioVenta.setBounds(120, 192, 174, 39);
			txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			txtPrecioVenta.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			JMenuItem mniCodArt = new JMenuItem("POR CÓDIGO");
			mniCodArt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtro = "cod_art";
				}
			});
			JMenuItem mniDesArt = new JMenuItem("POR DESCRIPCIÓN");
			mniDesArt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtro = "des_art";
				}
			});
			JMenuItem mniMarArt = new JMenuItem("POR MARCA");
			mniMarArt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtro = "mar_art";
				}
			});
			JMenuItem mniModArt = new JMenuItem("POR MODELO");
			mniModArt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtro = "mod_art";
				}
			});
			JMenuItem mniConArt = new JMenuItem("POR NÚMERO DE CONTRATO");
			mniConArt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtro = "id_con_asoc";
				}
			});
			popBusqueda = new JPopupMenu();
			popBusqueda.add(mniCodArt);
			popBusqueda.add(mniDesArt);
			popBusqueda.add(mniMarArt);
			popBusqueda.add(mniModArt);
			popBusqueda.add(mniConArt);
			sfArticulos = new JXSearchField();
			contenedor.add(sfArticulos);
			sfArticulos.setFindPopupMenu(popBusqueda);
			sfArticulos.setBounds(683, 255, 295, 39);
			sfArticulos.setFont(new java.awt.Font("Segoe UI", 1, 24));
			sfArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			sfArticulos.setForeground(new java.awt.Color(0, 64, 128));
			sfArticulos.setPrompt("BUSCAR");
			sfArticulos.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					buscarArticulos(sfArticulos.getText());
				}
			});
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("PRECIO WEB:");
			jLabel4.setBounds(314, 193, 212, 39);
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtPrecioInternet = new JTextField();
			contenedor.add(txtPrecioInternet);
			txtPrecioInternet.setBounds(469, 192, 192, 39);
			txtPrecioInternet.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			txtPrecioInternet.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtPrecioInternet.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			txtFechaPrecioInternet = new JDateChooser();
			contenedor.add(txtFechaPrecioInternet);
			txtFechaPrecioInternet.setBounds(771, 193, 207, 39);
			txtFechaPrecioInternet
					.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtFechaPrecioInternet.setBorder(BorderFactory.createMatteBorder(1,
					1, 1, 1, new java.awt.Color(0, 0, 0)));
			txtFechaPrecioInternet
					.setForeground(new java.awt.Color(0, 64, 128));
			txtFechaPrecioInternet.setDateFormatString("dd-MMM-yyyy");
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("FECHA:");
			jLabel5.setBounds(683, 193, 88, 39);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		}

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("CONTRATO:");
		jLabel6.setBounds(319, 256, 142, 39);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("CAPITAL:");
		jLabel11.setBounds(12, 256, 108, 39);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		txtCapital = new JTextField();
		contenedor.add(txtCapital);
		txtCapital.setBounds(120, 256, 174, 39);
		txtCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtCapital.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtCapital.setForeground(new java.awt.Color(0, 64, 128));

		txtContratoAsociado = new JTextField();
		contenedor.add(txtContratoAsociado);
		txtContratoAsociado.setBounds(541, 255, 119, 39);
		txtContratoAsociado.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, new java.awt.Color(0, 0, 0)));
		txtContratoAsociado.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtContratoAsociado.setForeground(new java.awt.Color(0, 64, 128));

		txtP = new JTextField();
		contenedor.add(txtP);
		txtP.setBounds(468, 256, 58, 39);
		txtP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtP.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtP.setForeground(new java.awt.Color(0, 64, 128));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("-");
		jLabel3.setBounds(526, 256, 15, 39);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		corelativo();
		listarArticulos();
	}

	public void grabarArticulo() throws SQLException {
		Articulo a = new Articulo(Integer.parseInt(lblCodigo.getText()),
				txtDescripcion.getText(), txtMarca.getText(),
				txtModelo.getText(), txtObs.getText(),
				Double.parseDouble(txtPrecioVenta.getText()), 3,
				Double.parseDouble(txtPrecioInternet.getText()),
				new SimpleDateFormat("yyyy-MM-dd")
						.format(txtFechaPrecioInternet.getDate()),
				Integer.parseInt(txtContratoAsociado.getText()),
				Double.parseDouble(txtCapital.getText()), txtP.getText()
						.toUpperCase());
		a.grabarArticuloMTO();
		listarArticulos();
		limpiarCajas();
		corelativo();
	}

	public void actualizarArticulo() throws SQLException {
		Articulo a = new Articulo(Integer.parseInt(lblCodigo.getText()),
				txtDescripcion.getText(), txtMarca.getText(),
				txtModelo.getText(), txtObs.getText(),
				Double.parseDouble(txtPrecioVenta.getText()), 3,
				Double.parseDouble(txtPrecioInternet.getText()),
				new SimpleDateFormat("yyyy-MM-dd")
						.format(txtFechaPrecioInternet.getDate()),
				Integer.parseInt(txtContratoAsociado.getText()),
				Double.parseDouble(txtCapital.getText()), txtP.getText()
						.toUpperCase());
		a.actualizar();
		listarArticulos();
		actualizarEstadoContrato();
		limpiarCajas();
		corelativo();
		
	}

	public String[] arrayTablas(int id_contrato) {
		Connection con = MySQLConexion.getConexion();
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
					return arrayTablas = new String[] { "tb_contrato_manual",
							"tb_detalle_contrato_manual" };
				} else {
					String sql2 = "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_contrato);
					ResultSet rs2 = pst2.executeQuery();
					if (rs2.next()) {
						return arrayTablas = new String[] { "tb_contrato_oro",
								"tb_detalle_contrato_oro" };
					} else {
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrayTablas;
	}

	public void actualizarEstadoContrato() {
		try {
			Connection cnx = MySQLConexion.getConexion();
			String a = arrayTablas(Integer.parseInt(txtContratoAsociado.getText()))[0];
			String sql = "UPDATE "+	a + " SET tb_estado_contrato_id_est = 9 WHERE id_con=?";
			PreparedStatement pst = cnx.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txtContratoAsociado.getText()));
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Se envió a VITRINA");
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void corelativo() {
		try {
			String sql = "SELECT ai_articulo FROM tb_correlativo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblCodigo.setText(String.format("%05d", rs.getInt(1) + 1));
			} else {
				lblCodigo.setText(String.format("%05d", rs.getString(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpiarCajas() {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setText("");
				((JTextField) o).setBackground(Color.WHITE);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setDate(null);
				((JDateChooser) o).getDateEditor().getUiComponent()
						.setBackground(Color.WHITE);
			}
		}
	}

	public void listarArticulos() {
		try {

			String sql = "SELECT dc.tb_contrato_id_con,a.* FROM tb_articulo a INNER JOIN tb_detalle_contrato dc "
					+ "ON a.cod_art = dc.tb_articulo_cod_art UNION SELECT dc.tb_contrato_manual_id_con,a.* FROM tb_articulo a "
					+ "INNER JOIN tb_detalle_contrato_manual dc ON a.cod_art = dc.tb_articulo_cod_art UNION "
					+ "SELECT dc.tb_contrato_oro_id_con,a.* FROM tb_articulo a INNER JOIN tb_detalle_contrato_oro dc "
					+ "ON a.cod_art = dc.tb_articulo_cod_art";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while (rs.next()) {
				modeloArticulos.addRow(new Object[] { rs.getInt(1),
						rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),
						rs.getDouble("cap_art"), rs.getDouble(7),
						rs.getDouble(9), rs.getString(10) });
			}
			tbArticulos.setModel(modeloArticulos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validador(JPanel pnl) {
		boolean val = true;

		for (Object o : pnl.getComponents()) {
			if (o instanceof JTextField && !(o instanceof JXSearchField)) {
				if (((JTextField) o).getText().equals("")) {
					((JTextField) o).setBackground(Color.RED);
					((JTextField) o).requestFocus();
					return val = false;
				}
			}
			if (o instanceof JDateChooser) {
				if (((JDateChooser) o).getDate() == null) {
					((JDateChooser) o).getDateEditor().getUiComponent()
							.setBackground(Color.RED);
					return val = false;
				}
			}

		}
		return val;
	}

	public void estadoArticulo(int codigo) {
		try {

			String sql = "SELECT tb_estado_articulo_cod_est FROM tb_articulo WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 1) {
					txtPrecioVenta.setEnabled(true);
					txtPrecioInternet.setEnabled(true);
					txtContratoAsociado.setText("0000");
					txtCapital.setText("0.00");
					txtContratoAsociado.setEnabled(true);
					txtCapital.setEnabled(true);
				} else {
					txtPrecioVenta.setEnabled(false);
					txtPrecioInternet.setEnabled(false);
					txtContratoAsociado.setEnabled(false);
					txtCapital.setEnabled(false);
					txtContratoAsociado.setText("0000");
					txtCapital.setText("0.00");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String obtenerTablaDetalle(int id_articulo) {
		Connection con = MySQLConexion.getConexion();
		String tabla = "";
		try {
			String sql = "SELECT * FROM tb_detalle_contrato WHERE tb_articulo_cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_articulo);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return tabla = "tb_detalle_contrato";
			} else {
				String sql1 = "SELECT * FROM tb_contrato_manual WHERE id_con=?";
				PreparedStatement pst1 = con.prepareStatement(sql1);
				pst1.setInt(1, id_articulo);
				ResultSet rs1 = pst1.executeQuery();
				if (rs1.next()) {
					return tabla = "tb_detalle_contrato_manual";
				} else {
					String sql2 = "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_articulo);
					ResultSet rs2 = pst2.executeQuery();
					if (rs2.next()) {
						return tabla = "tb_detalle_contrato_oro";
					} else {
						JOptionPane.showMessageDialog(null,
								"Ese contrato NO existe.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tabla;
	}

	public void buscarArticulos(String criteria) {
		try {
			String sql = "SELECT dc.tb_contrato_id_con,a.* FROM tb_articulo a INNER JOIN tb_detalle_contrato dc "
					+ "ON a.cod_art = dc.tb_articulo_cod_art WHERE "
					+ filtro
					+ " LIKE ? UNION SELECT dc.tb_contrato_manual_id_con,a.* FROM tb_articulo a "
					+ "INNER JOIN tb_detalle_contrato_manual dc ON a.cod_art = dc.tb_articulo_cod_art WHERE "
					+ filtro
					+ " LIKE ? UNION "
					+ "SELECT dc.tb_contrato_oro_id_con,a.* FROM tb_articulo a INNER JOIN tb_detalle_contrato_oro dc "
					+ "ON a.cod_art = dc.tb_articulo_cod_art WHERE "
					+ filtro
					+ " LIKE ? UNION SELECT id_con_asoc,a.* FROM tb_articulo a WHERE "
					+ filtro + " LIKE ? and id_con_asoc <> 0";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "%" + criteria + "%");
			pst.setString(2, "%" + criteria + "%");
			pst.setString(3, "%" + criteria + "%");
			pst.setString(4, "%" + criteria + "%");
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while (rs.next()) {
				modeloArticulos.addRow(new Object[] { rs.getInt(1),
						rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),
						rs.getDouble("cap_art"), rs.getDouble(7),
						rs.getDouble(9), rs.getString(10) });
			}
			tbArticulos.setModel(modeloArticulos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
