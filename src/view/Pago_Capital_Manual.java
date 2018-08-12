package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Beans.Ingreso;
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
public class Pago_Capital_Manual extends JInternalFrame {
	
	private JDateChooser dchFechaPago;
	private JLabel jLabel9;
	private JLabel lblPorcentajeInteres;
	private JLabel jLabel8;
	private JButton btnSalir;
	private JTextField txtAbono;
	private JLabel jLabel7;
	private JLabel lblNuevoInteres;
	private JLabel lblNuevoCapital;
	private JLabel jLabel5;
	private JLabel lblInteresActual;
	private JLabel lblCapitalActual;
	private JLabel lblNroContrato;
	private JButton btnGrabar;
	private JLabel jLabel6;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private int nro_contrato;
	private String[] arreglo_tablas;
	public Pago_Capital_Manual(int contrato) throws SQLException {
		this.setVisible(true);
		this.setLayout(null);
		getContentPane().add(getJLabel1());
		getContentPane().add(getJLabel2());
		getContentPane().add(getJLabel3());
		getContentPane().add(getJLabel5());
		getContentPane().add(getJLabel6());
		getContentPane().add(getBtnGrabar());
		getContentPane().add(getLblNroContrato());
		getContentPane().add(getLblCapitalActual());
		getContentPane().add(getLblInteresActual());
		getContentPane().add(getLblNuevoCapital());
		getContentPane().add(getLblNuevoInteres());
		getContentPane().add(getJLabel7());
		getContentPane().add(getTxtAbono());
		getContentPane().add(getBtnSalir());
		getContentPane().add(getJLabel8());
		getContentPane().add(getLblPorcentajeInteres());
		getContentPane().add(getJLabel9());
		getContentPane().add(getDchFechaPago());
		this.setSize(483, 533);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(483, 494));
		this.setBounds(0, 0, 483, 494);
		this.nro_contrato = contrato;
		arreglo_tablas = arrayTablas(nro_contrato);
		validarCondicion(arreglo_tablas, nro_contrato);
	}

	/*public Date ultimoPago(int codigo) {
		Date ultimoPago = null;
		try {
			String sql = "SELECT MAX(fec_pag) FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				ultimoPago = rs.getDate(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoPago;
	}*/
	
	public void validarCondicion(String[] array, int numero) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT id_con,cap_con,int_con,int_pre FROM " + array[0]
					+ " c INNER JOIN tb_prestamo p ON c.tb_prestamo_cod_pre=p.cod_pre WHERE id_con=?";/* AND tb_estado_contrato_id_est=1";*/
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				/*Date ultimo = ultimoPago(rs.getInt(1));
				Calendar last = Calendar.getInstance();
				last.setTime(ultimo);
				long up = last.getTimeInMillis();		
				long hoy = Calendar.getInstance().getTimeInMillis();
				long resta = (hoy-up) / (24 * 60 * 60 * 1000);
				if(resta < 6){*/
					lblNroContrato.setText(rs.getInt(1)+"");
					lblCapitalActual.setText(rs.getDouble(2)+"");
					lblInteresActual.setText(rs.getDouble(3)+"");
					lblPorcentajeInteres.setText(rs.getDouble(4)+"");
					/*lblUltimoPago.setText(new SimpleDateFormat("dd-MMM-yyyy").format(ultimoPago(rs.getInt(1))).toUpperCase());*/
				/*}else{
					JOptionPane.showMessageDialog(null, "No se puede concretar el Abono a Capital - Motivo: Fuera de Tiempo. - Plazo máximo: 5 días - Cantidad: "+resta+" días");
				}								
			} else {
				JOptionPane
						/*.showMessageDialog(*/
							/*	null,
								"Este Contrato NO cumple con las condiciones para proceder con un abono al capital.");
			}*/

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void calcularNuevosImportes(){
		double capitalActual = Double.parseDouble(lblCapitalActual.getText());
		double abono = Double.parseDouble(txtAbono.getText());
		double resta = capitalActual-abono;
		double percent = Double.parseDouble(lblPorcentajeInteres.getText())/100;
		lblNuevoCapital.setText(resta+"");
		double neo_interes = ((resta*percent) < 10 )? 10:resta*percent; 
		lblNuevoInteres.setText(neo_interes+"");		
	}

	public void grabarPagoCapital() throws SQLException{
		Beans.Pago_Capital pago_capital = new Beans.Pago_Capital(Integer.parseInt(lblNroContrato.getText()), new SimpleDateFormat("yyyy-MM-dd").format(dchFechaPago.getDate()), Double.parseDouble(lblCapitalActual.getText()), Double.parseDouble(lblInteresActual.getText()), Double.parseDouble(lblNuevoCapital.getText()), Double.parseDouble(lblNuevoInteres.getText()));		
		pago_capital.registrarAbonoAlCapital();
		actualizarContrato(arreglo_tablas[0], pago_capital.getId_contrato());
		closed();
	}
	
	public void actualizarContrato(String tabla, int contrato) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE "+tabla+" SET cap_con=?, int_con=? WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, Double.parseDouble(lblNuevoCapital.getText()));
			pst.setDouble(2, Double.parseDouble(lblNuevoInteres.getText()));
			pst.setInt(3, contrato);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Contrato actualizado.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	
	public String[] arrayTablas(int id_contrato) throws SQLException {
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
						JOptionPane.showMessageDialog(null,
								"Ese contrato NO existe.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return arrayTablas;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("N° CONTRATO");
			jLabel1.setBounds(15, 12, 157, 36);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel1;
	}
	
	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("CAPITAL ACTUAL");
			jLabel2.setBounds(14, 103, 193, 36);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel2;
	}
	
	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("INTERES ACTUAL");
			jLabel3.setBounds(16, 148, 206, 36);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel3.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel3;
	}

	private JLabel getJLabel5() {
		if(jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("NUEVO CAPITAL");
			jLabel5.setBounds(12, 243, 195, 36);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel5.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel5;
	}
	
	private JLabel getJLabel6() {
		if(jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("NUEVO INTERES");
			jLabel6.setBounds(13, 289, 211, 36);
			jLabel6.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel6.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel6;
	}
	
	private JButton getBtnGrabar() {
		if(btnGrabar == null) {
			btnGrabar = new JButton();
			btnGrabar.setText("GUARDAR");
			btnGrabar.setBounds(12, 384, 251, 63);
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnGrabar.setFont(new java.awt.Font("Segoe UI",1,22));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						grabarPagoCapital();	
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		return btnGrabar;
	}
	
	private JLabel getLblNroContrato() {
		if(lblNroContrato == null) {
			lblNroContrato = new JLabel();
			lblNroContrato.setBounds(263, 11, 166, 36);
			lblNroContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblNroContrato.setOpaque(true);
			lblNroContrato.setBackground(new java.awt.Color(255,255,255));
			lblNroContrato.setFont(new java.awt.Font("Segoe UI",1,24));
			lblNroContrato.setForeground(new java.awt.Color(0,64,128));
		}
		return lblNroContrato;
	}
	
	private JLabel getLblCapitalActual() {
		if(lblCapitalActual == null) {
			lblCapitalActual = new JLabel();
			lblCapitalActual.setBounds(263, 101, 166, 36);
			lblCapitalActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblCapitalActual.setBackground(new java.awt.Color(255,255,255));
			lblCapitalActual.setOpaque(true);
			lblCapitalActual.setFont(new java.awt.Font("Segoe UI",1,24));
			lblCapitalActual.setForeground(new java.awt.Color(0,64,128));
		}
		return lblCapitalActual;
	}
	
	private JLabel getLblInteresActual() {
		if(lblInteresActual == null) {
			lblInteresActual = new JLabel();
			lblInteresActual.setBounds(263, 146, 166, 36);
			lblInteresActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblInteresActual.setBackground(new java.awt.Color(255,255,255));
			lblInteresActual.setOpaque(true);
			lblInteresActual.setFont(new java.awt.Font("Segoe UI",1,24));
			lblInteresActual.setForeground(new java.awt.Color(0,64,128));
		}
		return lblInteresActual;
	}

	private JLabel getLblNuevoCapital() {
		if(lblNuevoCapital == null) {
			lblNuevoCapital = new JLabel();
			lblNuevoCapital.setBounds(262, 239, 166, 36);
			lblNuevoCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblNuevoCapital.setBackground(new java.awt.Color(255,255,255));
			lblNuevoCapital.setOpaque(true);
			lblNuevoCapital.setFont(new java.awt.Font("Segoe UI",1,24));
			lblNuevoCapital.setForeground(new java.awt.Color(128,0,0));
		}
		return lblNuevoCapital;
	}
	
	private JLabel getLblNuevoInteres() {
		if(lblNuevoInteres == null) {
			lblNuevoInteres = new JLabel();
			lblNuevoInteres.setBounds(262, 285, 166, 36);
			lblNuevoInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblNuevoInteres.setBackground(new java.awt.Color(255,255,255));
			lblNuevoInteres.setOpaque(true);
			lblNuevoInteres.setFont(new java.awt.Font("Segoe UI",1,24));
			lblNuevoInteres.setForeground(new java.awt.Color(128,0,0));
		}
		return lblNuevoInteres;
	}
	
	private JLabel getJLabel7() {
		if(jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("VALOR ABONO (S/.)");
			jLabel7.setBounds(12, 197, 224, 36);
			jLabel7.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel7.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel7;
	}
	
	private JTextField getTxtAbono() {
		if(txtAbono == null) {
			txtAbono = new JTextField();
			txtAbono.setBounds(262, 194, 166, 36);
			txtAbono.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtAbono.setFont(new java.awt.Font("Segoe UI",1,24));
			txtAbono.setForeground(new java.awt.Color(0,64,128));
			txtAbono.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e){
					calcularNuevosImportes();
				}
			});
		}
		return txtAbono;
	}
	
	private JButton getBtnSalir() {
		if(btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setText("SALIR");
			btnSalir.setBounds(269, 384, 160, 63);
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnSalir.setFont(new java.awt.Font("Segoe UI",1,22));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();					
				}
			});
		}
		return btnSalir;
	}
	
	private JLabel getJLabel8() {
		if(jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("PORCENTAJE INTERES");
			jLabel8.setBounds(12, 57, 245, 36);
			jLabel8.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel8.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel8;
	}
	
	private JLabel getLblPorcentajeInteres() {
		if(lblPorcentajeInteres == null) {
			lblPorcentajeInteres = new JLabel();
			lblPorcentajeInteres.setBounds(263, 56, 166, 36);
			lblPorcentajeInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblPorcentajeInteres.setOpaque(true);
			lblPorcentajeInteres.setBackground(new java.awt.Color(255,255,255));
			lblPorcentajeInteres.setFont(new java.awt.Font("Segoe UI",1,24));
			lblPorcentajeInteres.setForeground(new java.awt.Color(255,0,0));
		}
		return lblPorcentajeInteres;
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void closed(){
		this.dispose();
	}
	
	private JLabel getJLabel9() {
		if(jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("FECHA ABONO");
			jLabel9.setBounds(13, 336, 163, 36);
			jLabel9.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel9.setForeground(new java.awt.Color(0,128,0));
		}
		return jLabel9;
	}
	
	private JDateChooser getDchFechaPago() {
		if(dchFechaPago == null) {
			dchFechaPago = new JDateChooser();
			dchFechaPago.setBounds(262, 330, 166, 36);
			dchFechaPago.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			dchFechaPago.setFont(new java.awt.Font("Segoe UI",1,22));
		}
		return dchFechaPago;
	}
}
