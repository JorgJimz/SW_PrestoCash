package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Beans.Cambio;
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
public class Tipo_Cambio extends JInternalFrame{
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton btnGrabar;
	private JLabel lblFecHor;
	private JLabel lblTipoCambio;
	private JTextField txtVenta;
	private JTextField txtCompra;
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Connection con = MySQLConexion.getConexion();
	
	public Tipo_Cambio() {
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("COMPRA =");
			jLabel1.setBounds(13, 124, 131, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("VENTA =");
			jLabel2.setBounds(12, 175, 114, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		{
			txtCompra = new JTextField();
			getContentPane().add(txtCompra);
			txtCompra.setBounds(144, 123, 216, 39);
			txtCompra.setFont(new java.awt.Font("Segoe UI",1,24));
			txtCompra.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			txtVenta = new JTextField();
			getContentPane().add(txtVenta);
			txtVenta.setBounds(144, 174, 216, 39);
			txtVenta.setFont(new java.awt.Font("Segoe UI",1,24));
			txtVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("TIPO:");
			jLabel3.setBounds(12, 19, 73, 39);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		{
			lblTipoCambio = new JLabel("BANCARIO");
			getContentPane().add(lblTipoCambio);
			lblTipoCambio.setBounds(144, 20, 174, 39);
			lblTipoCambio.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");			
			lblFecHor = new JLabel(sdf1.format(d));
			getContentPane().add(lblFecHor);
			lblFecHor.setBounds(144, 58, 216, 39);		
			lblFecHor.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		{
			btnGrabar = new JButton();
			getContentPane().add(btnGrabar);
			btnGrabar.setText("GRABAR");
			btnGrabar.setBounds(378, 121, 272, 90);
			btnGrabar.setFont(new java.awt.Font("Segoe UI",1,26));
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(txtCompra.getText().equalsIgnoreCase("") || txtVenta.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "El registro diario del tipo de cambio es OBLIGATORIO.");
					}else{
						grabarCambio();																		
						cerrar();
					}
				}
			});
		}
		{
			jLabel4 = new JLabel();
			getContentPane().add(jLabel4);
			jLabel4.setText("FECHA:");
			jLabel4.setBounds(12, 63, 96, 39);
			jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		this.setSize(672, 252);
		this.setPreferredSize(new java.awt.Dimension(688, 273));
		this.setBounds(0, 0, 688, 273);
		ultimoCambio();
	}
	
	public void cerrar(){
		this.dispose();
	}
	
	public void ultimoCambio(){
		try {
			String sql = "SELECT MAX(id_cam),ven_cam, com_cam FROM tb_cambio";
			PreparedStatement pst = con.prepareStatement(sql);			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				txtVenta.setText(rs.getString(2));
				txtCompra.setText(rs.getString(3));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void grabarCambio(){	
		try {
			if(!txtCompra.getText().equalsIgnoreCase("") || !txtVenta.getText().equalsIgnoreCase("")){
				Cambio c = new Cambio(Double.parseDouble(txtVenta.getText()),Double.parseDouble(txtCompra.getText()), lblTipoCambio.getText(), sdf.format(d));
				c.registrar();	
			}	
			cerrar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un monto correcto.");
		}
		
	}	
}
