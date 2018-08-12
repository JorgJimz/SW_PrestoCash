package view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JMonthChooser;

import Utils.ComboItem;
import net.sf.jasperreports.data.mondrian.MondrianDataAdapterImpl;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
public class Reporte_Asistencia extends JInternalFrame{
	private JPanel contenedor;
	private JButton btnProcesar;
	DefaultComboBoxModel modeloUsuario = new DefaultComboBoxModel();
	DefaultTableModel modeloRemate = new DefaultTableModel(null, new String[] {
			"NÚMERO DE CONTRATO", "DESCRIPCIÓN", "CAPITAL", "PRECIO VENTA" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	private JComboBox cboUsuario;
	private JMonthChooser mchMes;
	private JLabel jLabel2;
	private JLabel jLabel1;

	public Reporte_Asistencia() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(736, 107);
		this.setTitle("REPORTE DE REMATES MENSUAL");
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(860, 105));
		this.setBounds(0, 0, 860, 105);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 858, 80);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnProcesar = new JButton();
		contenedor.add(btnProcesar);
		btnProcesar.setText("PROCESAR");
		btnProcesar.setBounds(637, 12, 196, 39);
		btnProcesar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnProcesar.setFont(new java.awt.Font("Segoe UI",1,22));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("USUARIO:");
		jLabel1.setBounds(18, 12, 150, 39);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		cboUsuario = new JComboBox();
		contenedor.add(cboUsuario);
		cboUsuario.setBounds(134, 12, 212, 39);
		cboUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		cboUsuario.setFont(new java.awt.Font("Segoe UI",1,20));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("MES:");
		jLabel2.setBounds(357, 12, 226, 39);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		mchMes = new JMonthChooser();
		BoxLayout cboMesLayout = new BoxLayout(mchMes, javax.swing.BoxLayout.X_AXIS);
		mchMes.setLayout(cboMesLayout);
		contenedor.add(mchMes);		
		mchMes.setBounds(419, 12, 204, 39);	
		
		mchMes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		mchMes.getComboBox().setFont(new java.awt.Font("Segoe UI",1,20));
		mchMes.getComboBox().setBackground(new java.awt.Color(255,255,255));

		btnProcesar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte(((ComboItem)cboUsuario.getSelectedItem()).getValue());
			}
		});
		
		cargarUsuarios();

	}
	
	public String calcularRetraso(String dni){
		Connection con = MySQLConexion.getConexion();
		String retraso = "";
		try {
			String sql = "SELECT time_format(sum(tar_asi),'%h:%i:%s') FROM tb_asistencia WHERE tb_usuario_dni_usu=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				retraso = rs.getString(1);
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
		return retraso;
	}

	public void mostrarReporte(String dni) {
		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dni", dni);
		parametros.put("retraso_acumulado", calcularRetraso(dni));
		parametros.put("m", mchMes.getMonth()+1);
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_asistencia.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);			
			viewer.show();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void cargarUsuarios(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT dni_usu,CONCAT(nom_usu,SPACE(1),pat_usu) as 'Nombres' FROM tb_usuario";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				modeloUsuario.addElement(new ComboItem(rs.getString(1), rs.getString("Nombres")));
			}
			cboUsuario.setModel(modeloUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
