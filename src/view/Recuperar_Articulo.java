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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Beans.Articulo;
import Beans.Auditoria;
import Beans.Contrato;
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
public class Recuperar_Articulo extends JInternalFrame{

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
	private Connection con = MySQLConexion.getConexion();
	private JXSearchField sfContrato;
	private JTextField txtPrecioConDescuento;
	private JLabel jLabel6;
	private JLabel lblCapital;
	private JLabel lblTelefono;
	private JLabel lblContratoAsociado;
	private JTextField txtDni;
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
	
	public Recuperar_Articulo(String[] data) {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE ARTICULOS");
		this.setSize(520, 528);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		this.setPreferredSize(new java.awt.Dimension(1062, 752));
		this.setBounds(0, 0, 1062, 752);
		{			
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1060, 727);
			contenedor.setBackground(new java.awt.Color(255,200,147));			
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CÓDIGO:");
			jLabel1.setBounds(12, 387, 154, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("DESCRIPCIÓN:");
			jLabel7.setBounds(325, 387, 154, 39);
			jLabel7.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel7.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtDescripcion = new JTextField();
			contenedor.add(txtDescripcion);
			txtDescripcion.setBounds(486, 386, 524, 39);
			txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtDescripcion.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel8 = new JLabel();
			contenedor.add(jLabel8);
			jLabel8.setText("MARCA:");
			jLabel8.setBounds(325, 438, 154, 39);
			jLabel8.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel8.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtMarca = new JTextField();
			contenedor.add(txtMarca);
			txtMarca.setBounds(420, 437, 212, 39);
			txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtMarca.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel9 = new JLabel();
			contenedor.add(jLabel9);
			jLabel9.setText("MODELO:");
			jLabel9.setBounds(12, 438, 154, 39);
			jLabel9.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel9.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtModelo = new JTextField();
			contenedor.add(txtModelo);
			txtModelo.setBounds(124, 437, 189, 39);
			txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtModelo.setFont(new java.awt.Font("Segoe UI",1,24));
		}
		{
			jLabel10 = new JLabel();
			contenedor.add(jLabel10);
			jLabel10.setText("OBSERVACIÓN:");
			jLabel10.setBounds(12, 488, 168, 39);
			jLabel10.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel10.setForeground(new java.awt.Color(0,128,0));
		}
		{
			btnCancelar = new JButton(new ImageIcon("img/salir.png"));
			contenedor.add(btnCancelar);
			btnCancelar.setText(" SALIR");
			btnCancelar.setBounds(745, 621, 267, 77);
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
			btnVender.setBounds(745, 532, 267, 77);
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
			tbArticulos.setEnabled(false);
			tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbArticulos.getTableHeader().setForeground(new Color(181,0,0));
			tbArticulos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					try {
						if(e.getClickCount()==2){
							int fila = tbArticulos.getSelectedRow();
							lblContratoAsociado.setText(tbArticulos.getValueAt(fila, 0).toString());
							lblCodigo.setText(tbArticulos.getValueAt(fila, 1).toString());						
							txtDescripcion.setText(tbArticulos.getValueAt(fila,2).toString());
							txtMarca.setText(tbArticulos.getValueAt(fila,3).toString());
							txtModelo.setText(tbArticulos.getValueAt(fila, 4).toString());
							txtObs.setText(tbArticulos.getValueAt(fila,5).toString());
							lblCapital.setText(tbArticulos.getValueAt(fila,6).toString());
							txtPrecioVenta.setText(tbArticulos.getValueAt(fila,7).toString());	
							double dcto = Double.parseDouble(txtPrecioVenta.getText())*0.2;									
							txtPrecioConDescuento.setText(Double.parseDouble(txtPrecioVenta.getText())-dcto+"");
							btnVender.setEnabled(true);
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
			lblCodigo.setBounds(114, 387, 199, 39);			
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
			jLabel2.setBounds(12, 538, 352, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		{
			txtPrecioVenta = new JTextField();
			contenedor.add(txtPrecioVenta);
			txtPrecioVenta.setBounds(12, 577, 352, 120);
			txtPrecioVenta.setFont(new java.awt.Font("Segoe UI",1,80));
			txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtPrecioVenta.setForeground(new java.awt.Color(255,0,0));
			txtPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecioVenta.setEditable(false);
		}
		{
			jSeparator1 = new JXTitledSeparator("ARTÍCULOS EN VENTA");
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(12, 108, 689, 39);
			jSeparator1.setFont(new java.awt.Font("Segoe UI",1,22));
			jSeparator1.setForeground(new java.awt.Color(128,0,0));
		}	
		{
			txtObs = new JTextField();
			contenedor.add(txtObs);
			txtObs.setBounds(180, 487, 832, 39);
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
			jLabel5.setBounds(655, 437, 154, 39);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
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
			lblFecha.setBounds(740, 437, 270, 39);
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

			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setText("PRECIO CON DCTO(20%):");
			jLabel6.setBounds(381, 541, 352, 39);
			jLabel6.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel6.setForeground(new java.awt.Color(0,128,0));
			jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel6.setHorizontalTextPosition(SwingConstants.CENTER);

			txtPrecioConDescuento = new JTextField();
			contenedor.add(txtPrecioConDescuento);
			txtPrecioConDescuento.setBounds(381, 577, 352, 120);
			txtPrecioConDescuento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtPrecioConDescuento.setFont(new java.awt.Font("Segoe UI",1,80));
			txtPrecioConDescuento.setForeground(new java.awt.Color(255,0,0));
			txtPrecioConDescuento.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecioConDescuento.setEditable(false);

			sfContrato = new JXSearchField();
			contenedor.add(sfContrato);
			sfContrato.setBounds(713, 108, 297, 37);
			sfContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			sfContrato.setFont(new java.awt.Font("Segoe UI",1,18));
			sfContrato.setPrompt("BUSCAR POR CONTRATO");
			sfContrato.setFont(new java.awt.Font("Segoe UI",1,18));
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
	
	public void filtrarArticulos(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 AND id_con_asoc=? ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(sfContrato.getText()));
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getInt("id_con_asoc"),rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble("cap_art"),rs.getDouble(6)});
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
		try {
			String sql = "SELECT * FROM tb_articulo WHERE tb_estado_articulo_cod_est=3 ORDER BY id_con_asoc ASC";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getInt("id_con_asoc"),rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble("cap_art"),rs.getDouble(6)});
			}tbArticulos.setModel(modeloArticulos);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void venderArticulo(int codigo){
		try {
			String sql = "UPDATE tb_articulo SET tb_estado_articulo_cod_est=4 WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Articulo Vendido");
			Ingreso ingreso = new Ingreso(Principal.id_libro_caja, lblContratoAsociado.getText()+"-"+txtDescripcion.getText().toUpperCase(), "REM.", Double.parseDouble(lblCapital.getText()), (Double.parseDouble(txtPrecioConDescuento.getText())-Double.parseDouble(lblCapital.getText())),0.00);
			ingreso.registrarIngreso();		
			registrarVenta();
			listarArticulos();
			//imprimirBoleta();
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validarCliente(){
		if(txtDni.getText().equalsIgnoreCase("") || txtDni.getText().equalsIgnoreCase(" ")){
			tbArticulos.setEnabled(false);
		}else {
			tbArticulos.setEnabled(true);
		}
	}
	
	public void seleccionarCliente(String dni) {
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblCliente.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));				
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
		}
	}
	
	public void imprimirBoleta() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
 		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", lblContratoAsociado.getText());
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
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - imprimirContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		}
	}
	
	public void registrarVenta(){
		Venta nueva_venta = new Venta(txtDni.getText(), Integer.parseInt(lblCodigo.getText()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), Double.parseDouble(txtPrecioVenta.getText()),"RECUPERACIÓN");
		vt = nueva_venta;
		nueva_venta.registrarVenta();
	}
	
}
