package maintenance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class Mantenimiento_Cambio extends JInternalFrame{
	
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnSalir;
	private JButton btnActualizar;
	private JLabel lblFecha;
	private JTextField txtVenta;
	private JTextField txtCompra;
	private JPanel contenedor;
	Connection con = MySQLConexion.getConexion();
	private Date d = new Date();
	SimpleDateFormat formatoLocal = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
	
	public Mantenimiento_Cambio() {
		this.setVisible(true);
		this.setTitle("ACTUALIZACIÓN DEL TIPO DE CAMBIO");
		this.setLayout(null);
		this.setPreferredSize(new java.awt.Dimension(669, 256));
		this.setBounds(0, 0, 669, 256);
		this.setBackground(new java.awt.Color(255,200,147));
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);		
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1273, 640);
		contenedor.setBackground(new java.awt.Color(255,184,113));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("COMPRA =");
			jLabel1.setBounds(12, 12, 126, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("VENTA =");
			jLabel2.setBounds(11, 62, 115, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtCompra = new JTextField();
			contenedor.add(txtCompra);
			txtCompra.setBounds(138, 12, 216, 39);
			txtCompra.setFont(new java.awt.Font("Gisha",1,12));
			txtCompra.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			txtVenta = new JTextField();
			contenedor.add(txtVenta);
			txtVenta.setBounds(138, 63, 216, 39);
			txtVenta.setFont(new java.awt.Font("Gisha",1,12));
			txtVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("TIPO:");
			jLabel3.setBounds(12, 113, 84, 39);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel3.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("FECHA:");
			jLabel4.setBounds(12, 164, 88, 39);
			jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel4.setForeground(new java.awt.Color(0,128,0));
		}
		{
			lblFecha = new JLabel(formatoLocal.format(d));
			contenedor.add(lblFecha);
			lblFecha.setBounds(138, 164, 178, 39);
			lblFecha.setFont(new java.awt.Font("Segoe UI",1,22));
			lblFecha.setForeground(new java.awt.Color(128,0,0));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("BANCARIO");
			jLabel5.setBounds(138, 113, 134, 39);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel5.setForeground(new java.awt.Color(128,0,0));
		}
		{
			btnActualizar = new JButton(new ImageIcon("img/actualizarCambio.png"));
			contenedor.add(btnActualizar);
			btnActualizar.setText(" ACTUALIZAR");
			btnActualizar.setBounds(366, 12, 272, 90);
			btnActualizar.setFont(new java.awt.Font("Segoe UI",1,26));
			btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnActualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(!txtCompra.getText().equals("") || !txtVenta.getText().equals("")){
						actualizarCambio();
					}else{ JOptionPane.showMessageDialog(null, "Faltan datos. Completar los campos, porfavor."); }				
				}
			});
		}
		{
			btnSalir = new JButton(new ImageIcon("img/salir.png"));
			contenedor.add(btnSalir);
			btnSalir.setText(" SALIR");
			btnSalir.setBounds(366, 113, 272, 90);
			btnSalir.setFont(new java.awt.Font("Segoe UI",1,26));
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();					
				}
			});
		}
			
	}	
	public void actualizarCambio(){
		try {
			String sql = "UPDATE tb_cambio SET com_cam=?, ven_cam=? WHERE fec_cam=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, Double.parseDouble(txtCompra.getText()));
			pst.setDouble(2, Double.parseDouble(txtVenta.getText()));
			pst.setString(3, formatoSQL.format(d));
			pst.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Tipo de Cambio modificado satisfactoriamente.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un monto correcto");
		}
	}
	
	public void cerrar(){
		this.dispose();
	}
}
