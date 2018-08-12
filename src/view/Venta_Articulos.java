package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutput;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Beans.Ingreso;
import Beans.Venta;
import Conexion.MySQLConexion;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Venta_Articulos extends JInternalFrame{

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
	private JLabel lblCapital;
	private JLabel lblTelefono;
	private JLabel lblContratoAsociado;
	private JTextField txtDni;
	private JLabel lblLetra;
	private JLabel lblDirCli;
	private JXSearchField sfContrato;
	private JLabel jLabel5;
	private JLabel lblFecha;
	private JLabel lblCliente;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JXTitledSeparator jSeparator2;
	private JTextField txtObs;
	private JButton btnVender;
	private JXTitledSeparator jSeparator1;
	private JTextField txtPrecioVenta;
	private JLabel jLabel2;
	private JLabel lblIdSC;
	private JLabel lblCodigo;	
	private JPanel contenedor;
	Venta vt = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DefaultTableModel modeloArticulos = new DefaultTableModel(null,new String[]{"N° CONTRATO","CÓDIGO","DESCRIPCIÓN","MARCA",
			"MODELO","OBSERVACIONES","CAPITAL","PRECIO VENTA"}){
				public boolean isCellEditable(int rowIndex, int colIndex){
					return false;
				}
	};
	
	public Venta_Articulos(String[] data) {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE ARTICULOS");
		this.setSize(520, 528);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		this.setPreferredSize(new java.awt.Dimension(1062, 698));
		this.setBounds(0, 0, 1062, 698);
		{			
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1060, 673);
			contenedor.setBackground(new java.awt.Color(255,200,147));
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CÓDIGO:");
			jLabel1.setBounds(12, 387, 94, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("DESCRIPCIÓN:");
			jLabel7.setBounds(640, 438, 144, 39);
			jLabel7.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel7.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtDescripcion = new JTextField();
			contenedor.add(txtDescripcion);
			txtDescripcion.setBounds(784, 437, 226, 39);
			txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtDescripcion.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("MARCA:");
			jLabel8.setBounds(342, 438, 89, 39);
			jLabel8.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel8.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtMarca = new JTextField();
			contenedor.add(txtMarca);
			txtMarca.setBounds(427, 437, 201, 39);
			txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtMarca.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel9 = new JLabel();
			contenedor.add(jLabel9);
			jLabel9.setText("MODELO:");
			jLabel9.setBounds(12, 438, 101, 39);
			jLabel9.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel9.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtModelo = new JTextField();
			contenedor.add(txtModelo);
			txtModelo.setBounds(113, 437, 200, 39);
			txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtModelo.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel10 = new JLabel();
			contenedor.add(jLabel10);
			jLabel10.setText("OBS:");
			jLabel10.setBounds(12, 489, 83, 39);
			jLabel10.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel10.setForeground(new java.awt.Color(0,128,0));
		}
		{
			btnCancelar = new JButton(new ImageIcon("img/salir.png"));
			contenedor.add(btnCancelar);
			btnCancelar.setText(" SALIR");
			btnCancelar.setBounds(313, 557, 267, 77);
			btnCancelar.setFont(new java.awt.Font("Segoe UI",1,26));
			btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();
					
				}
			});
		}
		{
			btnVender = new JButton();
			contenedor.add(btnVender);
			btnVender.setText("VENDER");
			btnVender.setBounds(12, 557, 267, 77);
			btnVender.setFont(new java.awt.Font("Segoe UI",1,26));
			btnVender.setEnabled(false);
			btnVender.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnVender.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					venderArticulo(Integer.parseInt(lblCodigo.getText()));					
				}
			});
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(12, 157, 998, 207);
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			jScrollPane1.setBackground(new java.awt.Color(255,255,255));
			jScrollPane1.setEnabled(false);

			tbArticulos = new JTable();
			jScrollPane1.setViewportView(tbArticulos);
			tbArticulos.setRowHeight(35);
			tbArticulos.setModel(modeloArticulos);
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modeloArticulos);
			tbArticulos.setEnabled(false);
			tbArticulos.setRowSorter(sorter);
			tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setForeground(new Color(181,0,0));
			tbArticulos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					try {
						if(e.getClickCount()==2){
							int fila = tbArticulos.getSelectedRow();
							lblContratoAsociado.setText(tbArticulos.getValueAt(fila, 0).toString());
							lblLetra.setText(tbArticulos.getValueAt(fila, 0).toString().split("-")[0]);
							lblCodigo.setText(tbArticulos.getValueAt(fila, 1).toString());						
							txtDescripcion.setText(tbArticulos.getValueAt(fila,2).toString());
							txtMarca.setText(tbArticulos.getValueAt(fila,3).toString());
							txtModelo.setText(tbArticulos.getValueAt(fila, 4).toString());
							txtObs.setText(tbArticulos.getValueAt(fila,5).toString());
							lblCapital.setText(tbArticulos.getValueAt(fila,6).toString());
							txtPrecioVenta.setText(tbArticulos.getValueAt(fila,7).toString());							
							btnVender.setEnabled(true);
							System.out.println(lblLetra.getText());
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Primero asigne un Comprador a la venta.");
					}
				}
			});			
		}
		{
			lblCodigo = new JLabel();
			contenedor.add(lblCodigo);
			lblCodigo.setBounds(112, 387, 201, 39);			
			lblCodigo.setFont(new java.awt.Font("Segoe UI",1,24));
			lblCodigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblCodigo.setBackground(new java.awt.Color(255,255,255));
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
			jLabel2.setText("PRECIO VENTA (S/.)");
			jLabel2.setBounds(640, 387, 202, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtPrecioVenta = new JTextField();
			contenedor.add(txtPrecioVenta);
			txtPrecioVenta.setBounds(839, 386, 171, 39);
			txtPrecioVenta.setFont(new java.awt.Font("Segoe UI",1,24));
			txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			jSeparator1 = new JXTitledSeparator("ARTÍCULOS EN VENTA");
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(12, 108, 726, 39);
			jSeparator1.setFont(new java.awt.Font("Segoe UI",1,22));
			jSeparator1.setForeground(new java.awt.Color(128,0,0));
		}	
		{
			txtObs = new JTextField();
			contenedor.add(txtObs);
			txtObs.setBounds(113, 488, 897, 39);
			txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtObs.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jSeparator2 = new JXTitledSeparator("DATOS DEL COMPRADOR");
			contenedor.add(jSeparator2);
			jSeparator2.setBounds(12, 12, 998, 39);
			jSeparator2.setFont(new java.awt.Font("Segoe UI",1,22));
			jSeparator2.setForeground(new java.awt.Color(128,0,0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("DOC.IDENTIDAD");
			jLabel3.setBounds(18, 57, 167, 39);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel3.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("CLIENTE");
			jLabel4.setBounds(359, 57, 93, 39);
			jLabel4.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel4.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("FECHA:");
			jLabel5.setBounds(342, 387, 73, 39);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel5.setForeground(new java.awt.Color(0,128,0));
		}
		{
			lblCliente = new JLabel(data[1]);
			contenedor.add(lblCliente);
			lblCliente.setBounds(451, 57, 561, 39);
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblCliente.setFont(new java.awt.Font("Segoe UI",1,24));
			lblCliente.setBackground(new java.awt.Color(255,255,255));
			lblCliente.setOpaque(true);
		}
		{
			lblFecha = new JLabel();
			contenedor.add(lblFecha);
			lblFecha.setBounds(427, 387, 201, 39);
			lblFecha.setFont(new java.awt.Font("Segoe UI",1,24));
			lblFecha.setOpaque(true);
			lblFecha.setText(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()).toUpperCase());
			lblFecha.setBackground(new java.awt.Color(255,255,255));
			lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			txtDni = new JTextField(data[0]);
			contenedor.add(txtDni);
			txtDni.setBounds(185, 57, 162, 39);
			txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtDni.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						seleccionarCliente(txtDni.getText());
				}
			});
		}

			listarArticulos();	
			bloquearCajas();
			txtDni.setEnabled(true);
			txtDni.setFont(new java.awt.Font("Segoe UI",1,22));

			lblContratoAsociado = new JLabel();
			contenedor.add(lblContratoAsociado);
			lblContratoAsociado.setBounds(58, 528, 10, 10);
			lblContratoAsociado.setVisible(false);

			lblTelefono = new JLabel();
			contenedor.add(lblTelefono);
			lblTelefono.setBounds(49, 540, 10, 10);
			lblTelefono.setVisible(false);

			lblCapital = new JLabel();
			contenedor.add(lblCapital);
			lblCapital.setBounds(44, 534, 10, 10);
			lblCapital.setVisible(false);

			sfContrato = new JXSearchField();
			contenedor.add(sfContrato);
			sfContrato.setBounds(744, 108, 268, 37);
			sfContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			sfContrato.setPrompt("BUSCAR POR CONTRATO");
			sfContrato.setFont(new java.awt.Font("Segoe UI",1,18));

			lblDirCli = new JLabel();
			contenedor.add(lblDirCli);
			lblDirCli.setBounds(30, 91, 37, 16);
			lblDirCli.setVisible(false);

			lblLetra = new JLabel();
			contenedor.add(lblLetra);
			lblLetra.setBounds(38, 46, 10, 10);
			lblLetra.setVisible(false);

			sfContrato.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e){
					filtrarArticulos();
				}
			});
			validarCliente();

	}
		
	public void cerrar(){
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
		this.dispose();
		}
	}
	
	
	public void limpiarCajas(){
		for(Object o : contenedor.getComponents()){
			if(o instanceof JTextField){			
				((JTextField)o).setText("");
				((JTextField)o).setBackground(Color.WHITE);
			}if(o instanceof JDateChooser){
				((JDateChooser)o).setDate(null);
				((JDateChooser)o).getDateEditor().getUiComponent().setBackground(Color.WHITE);           
			}				
		}
	}	
	
	public void bloquearCajas(){
		for(Object o : contenedor.getComponents()){
			if(o instanceof JTextField){				
				((JTextField)o).setEnabled(false);
				((JTextField)o).setBackground(Color.WHITE);
			}if(o instanceof JTextArea){
				((JTextArea)o).setEnabled(false);
				((JTextArea)o).setBackground(Color.WHITE);
			}if(o instanceof JDateChooser){
				((JDateChooser)o).setDate(null);
				((JDateChooser)o).getDateEditor().getUiComponent().setBackground(Color.WHITE);           
			}				
		}
	}	
	
	public void listarArticulos(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getString("p_con_asoc")+"-"+rs.getInt("id_con_asoc"),rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble("cap_art"),rs.getDouble(6)});
			}tbArticulos.setModel(modeloArticulos);	
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
	
	public void filtrarArticulos(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 AND id_con_asoc=? ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(sfContrato.getText()));
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getString("p_con_asoc")+"-"+rs.getInt("id_con_asoc"),rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble("cap_art"),rs.getDouble(6)});
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
	
	public void venderArticulo(int codigo){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_articulo SET tb_estado_articulo_cod_est=4 WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Articulo Vendido");
			Ingreso ingreso = new Ingreso(Principal.id_libro_caja, /*lblLetra.getText()+"-"+*/lblContratoAsociado.getText(), "REM", Double.parseDouble(lblCapital.getText()), (Double.parseDouble(txtPrecioVenta.getText())-Double.parseDouble(lblCapital.getText())),0.00);
			ingreso.registrarIngreso();		
			registrarVenta();
			listarArticulos();			
			JOptionPane.showMessageDialog(null, "<html><h2>SE IMPRIMIRÁ LA HOJA MEMBRETADA</h2></html>");
			imprimirBoleta();
			JOptionPane.showMessageDialog(null, "<html><h2>SE IMPRIMIRÁ EL RECIBO INTERNO</h2></html>");
			imprimirRecibo();
			int i = JOptionPane.showConfirmDialog(null, "<html><h2>PREPARANDO PARA IMPRIMIR LA BOLETA DE VENTA, INGRESE EL PAPEL Y PRESIONE \"SÍ\" SI DESEA CONTINUAR ...</h2></html>","ALERTA",JOptionPane.YES_OPTION);
			if(i == JOptionPane.YES_OPTION){
				imprimirReciboLegal();
				this.dispose();
			}else{
				this.dispose();
			}		
			actualizarContrato(arrayTablas(Integer.parseInt(lblContratoAsociado.getText().split("-")[1]))[0]);			
		}catch(NullPointerException e){}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actualizarContrato(String tabla){
		Connection con = MySQLConexion.getConexion();		
		try {
			String sql = "UPDATE "+tabla+" SET tb_estado_contrato_id_est=11 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblContratoAsociado.getText().split("-")[1]));
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
	
	public void validarCliente(){
		if(txtDni.getText().equalsIgnoreCase("") || txtDni.getText().equalsIgnoreCase(" ")){
			tbArticulos.setEnabled(false);
		}else {
			tbArticulos.setEnabled(true);
		}
	}
	
	public void seleccionarCliente(String dni) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblCliente.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));
				lblDirCli.setText(rs.getString("dir_cli"));				
				if(rs.getString("fi1_cli").trim().equalsIgnoreCase("-") || rs.getString("fi1_cli").trim().equalsIgnoreCase("A")){
					lblTelefono.setText(rs.getString("mo1_cli"));
				}else{
					lblTelefono.setText(rs.getString("fi1_cli"));
				}
				tbArticulos.setEnabled(true);
			} else {
				int o = JOptionPane
						.showConfirmDialog(
								null,
								"Este Cliente NO está registrado. ¿Desea proceder con el registro correspondiente?",
								"ALERTA", JOptionPane.YES_NO_OPTION);
				if (o == JOptionPane.YES_OPTION) {
					Mantenimiento_Clientes nuevo_cliente = new Mantenimiento_Clientes(null);
					Principal.dskPrincipal.add(nuevo_cliente);
					this.dispose();
				}
			}
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
	
	public void imprimirBoleta() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
 		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", /*lblLetra.getText()+"-"+*/lblContratoAsociado.getText());
 		parametros.put("detalle_articulo",txtDescripcion.getText()+" "+txtMarca.getText()+" "+txtModelo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MMMM-yyyy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblTelefono.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registrarVenta(){
		Venta nueva_venta = new Venta(txtDni.getText(), Integer.parseInt(lblCodigo.getText()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), Double.parseDouble(txtPrecioVenta.getText()),"REGULAR");
		vt = nueva_venta;
		nueva_venta.registrarVenta();
	}
	
	public void imprimirRecibo() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", /*lblLetra.getText()+"-"+*/lblContratoAsociado.getText());
 		parametros.put("detalle_articulo",txtDescripcion.getText()+" "+txtMarca.getText()+" "+txtModelo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MMM-yyyy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblTelefono.getText());
 		parametros.put("monto_recibido", txtPrecioVenta.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta_recibo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void imprimirReciboLegal() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", /*lblLetra.getText()+"-"+*/lblContratoAsociado.getText());
 		parametros.put("detalle_articulo",txtDescripcion.getText()+" "+txtMarca.getText()+" "+txtModelo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MMM-yyyy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblDirCli.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta_legal.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
