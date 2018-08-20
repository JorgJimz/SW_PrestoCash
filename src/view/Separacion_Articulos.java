package view;


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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import maintenance.Mantenimiento_Clientes;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Beans.Ingreso;
import Beans.Separacion;
import Conexion.MySQLConexion;

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
public class Separacion_Articulos extends JInternalFrame {

	private JScrollPane jScrollPane1;
	private JButton btnCancelar;
	private JLabel jLabel10;
	private JTextField txtModelo;
	private JLabel jLabel9;
	private JTextField txtMarca;
	private JLabel jLabel8;
	private JTextField txtDescripcion;
	private JLabel jLabel7;
	private JTable tbArticulos;
	private JLabel jLabel1;
	private JLabel lblCapitalPrestado;
	private JLabel lblCodigoContrato;
	private JTextField txtDni;
	private JXTitledSeparator jSeparator2;
	private JLabel lblFecha;
	private JLabel jLabel11;
	private JLabel lblCliente;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JLabel lblSaldo;
	private JLabel jLabel4;
	private JTextField txtACuenta;
	private JLabel jLabel3;
	private JButton btnSeparar;
	private JXSearchField sfContrato;
	private JLabel txtObs;
	private JXTitledSeparator jSeparator1;
	private JTextField txtPrecioVenta;
	private JLabel jLabel2;
	private JLabel lblIdSC;
	private JLabel lblCodigo;
	private JPanel contenedor;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DefaultTableModel modeloArticulos = new DefaultTableModel(null,
			new String[] { "CONTRATO", "COD.ARTÍCULO", "DESCRIPCIÓN", "MARCA",
					"MODELO", "OBSERVACIONES","PRECIO VENTA" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Separacion_Articulos(String[] data) {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("SEPARACIÓN DE ARTICULOS");
		this.setSize(520, 528);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1292, 679));
		this.setBounds(0, 0, 1292, 679);		
		{
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, -1, 1291, 657);
			contenedor.setBackground(new java.awt.Color(255, 200, 147));
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CÓDIGO:");
			jLabel1.setBounds(14, 419, 94, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("DESCRIPCIÓN:");
			jLabel7.setBounds(260, 419, 144, 39);
			jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel7.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtDescripcion = new JTextField();
			contenedor.add(txtDescripcion);
			txtDescripcion.setBounds(407, 418, 341, 39);
			txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("MARCA:");
			jLabel8.setBounds(761, 419, 80, 39);
			jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel8.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtMarca = new JTextField();
			contenedor.add(txtMarca);
			txtMarca.setBounds(842, 418, 170, 39);
			txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 24));
		}
		{
			jLabel9 = new JLabel();
			contenedor.add(jLabel9);
			jLabel9.setText("MODELO:");
			jLabel9.setBounds(11, 496, 101, 39);
			jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel9.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtModelo = new JTextField();
			contenedor.add(txtModelo);
			txtModelo.setBounds(112, 495, 134, 39);
			txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		}
		{
			jLabel10 = new JLabel();
			contenedor.add(jLabel10);
			jLabel10.setText("OBSERVACIÓN:");
			jLabel10.setBounds(253, 496, 181, 39);
			jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel10.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			btnCancelar = new JButton(new ImageIcon("img/salir.png"));
			contenedor.add(btnCancelar);
			btnCancelar.setText(" SALIR");
			btnCancelar.setBounds(1024, 522, 234, 92);
			btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();

				}
			});
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(12, 173, 1245, 219);
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

			tbArticulos = new JTable();
			jScrollPane1.setViewportView(tbArticulos);
			tbArticulos.setRowHeight(35);
			tbArticulos.setModel(modeloArticulos);
			tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
			tbArticulos.setEnabled(false);
			tbArticulos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						try {
							int fila = tbArticulos.getSelectedRow();
							lblCodigoContrato.setText(tbArticulos.getValueAt(
									fila, 0).toString());
							lblCodigo.setText(tbArticulos.getValueAt(fila, 1)
									.toString());
							txtDescripcion.setText(tbArticulos.getValueAt(fila,
									2).toString());
							txtMarca.setText(tbArticulos.getValueAt(fila, 3)
									.toString());
							txtModelo.setText(tbArticulos.getValueAt(fila, 4)
									.toString());
							txtObs.setText(tbArticulos.getValueAt(fila, 5)
									.toString());
							txtPrecioVenta.setText(tbArticulos.getValueAt(fila,
									6).toString());
							consultarCapital();
						} catch (Exception e2) {
							JOptionPane
									.showMessageDialog(null,
											"No hay cliente seleccionado para iniciar la separación.");
						}

					}
				}
			});
		}
		{
			lblCodigo = new JLabel();
			contenedor.add(lblCodigo);
			lblCodigo.setBounds(105, 419, 143, 39);
			lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCodigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
			lblCodigo.setOpaque(true);
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
			jLabel2.setText("PRECIO VENTA (S/.) =");
			jLabel2.setBounds(14, 575, 214, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtPrecioVenta = new JTextField();
			contenedor.add(txtPrecioVenta);
			txtPrecioVenta.setBounds(228, 574, 168, 39);
			txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
		}
		{
			jSeparator1 = new JXTitledSeparator("ARTÍCULOS EN VENTA");
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(12, 122, 969, 39);
			jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jSeparator1.setForeground(new java.awt.Color(128, 0, 0));
		}
		{
			txtObs = new JLabel();
			contenedor.add(txtObs);
			txtObs.setBounds(404, 496, 608, 39);
			txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtObs.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtObs.setBackground(new java.awt.Color(255, 255, 255));
			txtObs.setOpaque(true);
		}
		{
			btnSeparar = new JButton();
			contenedor.add(btnSeparar);
			btnSeparar.setText("SEPARAR");
			btnSeparar.setBounds(1024, 418, 234, 92);
			btnSeparar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnSeparar.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnSeparar.setEnabled(false);
			btnSeparar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					separarArticulo(Integer.parseInt(lblCodigo.getText()));
				}
			});
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("A CUENTA =");
			jLabel3.setBounds(416, 574, 131, 39);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtACuenta = new JTextField();
			contenedor.add(txtACuenta);
			txtACuenta.setBounds(547, 574, 168, 39);
			txtACuenta.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtACuenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtACuenta.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyChar() == KeyEvent.VK_ENTER) {
						double precioVenta = Double.parseDouble(txtPrecioVenta
								.getText());
						double aCuenta = Double.parseDouble(txtACuenta
								.getText());
						lblSaldo.setText((precioVenta - aCuenta) + "");
						btnSeparar.setEnabled(true);
						if (aCuenta >= precioVenta) {
							JOptionPane
									.showMessageDialog(
											null,
											"El importe a cuenta corresponde una venta directa del artículo y no una separación.");
							txtACuenta.setText("");
							lblSaldo.setText("0.00");
							btnSeparar.setEnabled(false);
							txtACuenta.requestFocus();
						}

					}
				}
			});
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("SALDO =");
			jLabel4.setBounds(733, 574, 100, 39);
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblSaldo = new JLabel();
			contenedor.add(lblSaldo);
			lblSaldo.setBounds(845, 574, 168, 39);
			lblSaldo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblSaldo.setOpaque(true);
			lblSaldo.setBackground(new java.awt.Color(255, 255, 255));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("DOC.IDENTIDAD");
			jLabel5.setBounds(12, 58, 187, 39);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setText("CLIENTE");
			jLabel6.setBounds(382, 58, 119, 39);
			jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel6.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblCliente = new JLabel(data[1]);
			contenedor.add(lblCliente);
			lblCliente.setBounds(481, 58, 476, 39);
			lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 24));
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCliente.setBackground(new java.awt.Color(255, 255, 255));
			lblCliente.setOpaque(true);
		}
		{
			jLabel11 = new JLabel();
			contenedor.add(jLabel11);
			jLabel11.setText("FECHA");
			jLabel11.setBounds(981, 58, 81, 39);
			jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel11.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblFecha = new JLabel();
			contenedor.add(lblFecha);
			lblFecha.setBounds(1062, 58, 199, 39);
			lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblFecha.setOpaque(true);
			lblFecha.setText(new SimpleDateFormat("dd-MMM-yyyy").format(
					new Date()).toUpperCase());
		}
		{
			jSeparator2 = new JXTitledSeparator("DATOS DEL COMPRADOR");
			contenedor.add(jSeparator2);
			jSeparator2.setBounds(12, 12, 1249, 39);
			jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jSeparator2.setForeground(new java.awt.Color(128, 0, 0));
		}
		{
			txtDni = new JTextField(data[0]);
			contenedor.add(txtDni);
			txtDni.setBounds(197, 59, 172, 39);
			txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtDni.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						try {
							seleccionarCliente(txtDni.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
			});
		}

		listarArticulos();
		bloquearCajas();
		txtACuenta.setEnabled(true);
		txtDni.setEnabled(true);
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtDni.setForeground(new java.awt.Color(128, 0, 0));

		lblCodigoContrato = new JLabel();
		contenedor.add(lblCodigoContrato);
		lblCodigoContrato.setBounds(12, 469, 10, 10);
		lblCodigoContrato.setVisible(false);

		lblCapitalPrestado = new JLabel();
		contenedor.add(lblCapitalPrestado);
		lblCapitalPrestado.setBounds(310, 483, 10, 10);
		lblCapitalPrestado.setVisible(false);

		sfContrato = new JXSearchField();
		contenedor.add(sfContrato);
		sfContrato.setBounds(993, 121, 264, 40);
		sfContrato.setPrompt("BUSCAR POR CONTRATO");
		sfContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		sfContrato.setFont(new java.awt.Font("Segoe UI",1,18));
		sfContrato.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				filtrarArticulos();
			}
		});
		validarCliente();
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Salir?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}
	
	public void filtrarArticulos(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 AND id_con_asoc=? ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(sfContrato.getText()));
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getInt("id_con_asoc"),rs.getInt("cod_art"),rs.getString("des_art"),rs.getString("mar_art"),rs.getString("mod_art"),rs.getString("obs_art"),rs.getDouble("pre_ven_art")});
			}tbArticulos.setModel(modeloArticulos);	
		} catch (Exception e) {
			listarArticulos();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void validarCliente(){
		if(txtDni.getText().equalsIgnoreCase("") || txtDni.getText().equalsIgnoreCase(" ")){
			tbArticulos.setEnabled(false);
		}else {
			tbArticulos.setEnabled(true);
		}
	}

	public void seleccionarCliente(String dni) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblCliente.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));	
				tbArticulos.setEnabled(true);
			} else {
				int o = JOptionPane
						.showConfirmDialog(
								null,
								"Este Cliente NO está registrado. ¿Desea proceder con el registro correspondiente?",
								"ALERTA", JOptionPane.YES_NO_OPTION);
				if (o == JOptionPane.YES_OPTION) {
					Mantenimiento_Clientes nuevo_cliente = new Mantenimiento_Clientes(
							null);
					Principal.dskPrincipal.add(nuevo_cliente);
					this.dispose();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
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
		txtObs.setText("");
		lblCliente.setText("");
		lblCodigo.setText("");
		lblSaldo.setText("");
	}

	public void bloquearCajas() {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setEnabled(false);
				((JTextField) o).setBackground(Color.WHITE);
			}
			if (o instanceof JTextArea) {
				((JTextArea) o).setEnabled(false);
				((JTextArea) o).setBackground(Color.WHITE);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setDate(null);
				((JDateChooser) o).getDateEditor().getUiComponent()
						.setBackground(Color.WHITE);
			}
		}
	}

	public void listarArticulos() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while (rs.next()) {
				modeloArticulos.addRow(new Object[]{rs.getInt("id_con_asoc"),rs.getInt("cod_art"),rs.getString("des_art"),rs.getString("mar_art"),rs.getString("mod_art"),rs.getString("obs_art"),rs.getDouble("pre_ven_art")});
			}
			tbArticulos.setModel(modeloArticulos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String obtenerLetra(int codigo){
		Connection con = MySQLConexion.getConexion();
		String letra = "";
		try {
			String sql = "SELECT p_con_asoc FROM tb_articulo WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				letra = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return letra;
	}

	public void separarArticulo(int codigo) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_articulo SET tb_estado_articulo_cod_est=6 WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Articulo Separado.");
			Separacion separacion = new Separacion(
					Integer.parseInt(lblCodigoContrato.getText()),
					Integer.parseInt(lblCodigo.getText()),
					Double.parseDouble(lblCapitalPrestado.getText()),
					Double.parseDouble(txtPrecioVenta.getText()),
					Double.parseDouble(txtACuenta.getText()),
					Double.parseDouble(lblSaldo.getText()), txtDni.getText(),
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 1);
			separacion.registrarSeparacion();
			Ingreso ingreso = new Ingreso(Principal.id_libro_caja,
					obtenerLetra(codigo)+"-"+lblCodigoContrato.getText(),"SEP", 0.00, 0.00,
					Double.parseDouble(txtACuenta.getText()));
			ingreso.registrarIngreso();
			listarArticulos();	
			actualizarContrato(arrayTablas(Integer.parseInt(lblCodigoContrato.getText()))[0]);
			limpiarCajas();	
		}catch(NullPointerException e){
			limpiarCajas();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void consultarCapital() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String[] a = arrayTablas(Integer.parseInt(lblCodigoContrato
					.getText()));
			if (a != null) {
				String sql = "SELECT cap_con FROM " + a[0] + " WHERE id_con=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblCodigoContrato.getText()));
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					lblCapitalPrestado.setText(rs.getDouble(1) + "");
				}
			}else{
				String sql = "SELECT cap_art FROM tb_articulo WHERE cod_art=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(lblCodigo.getText()));
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					lblCapitalPrestado.setText(rs.getDouble(1) + "");
				}
			}		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	public void actualizarContrato(String tabla){
		Connection con = MySQLConexion.getConexion();		
		try {
			String sql = "UPDATE "+tabla+" SET tb_estado_contrato_id_est=10 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblCodigoContrato.getText()));
			pst.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

}
