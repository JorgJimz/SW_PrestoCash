package report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import Utils.ComboItem;
import Utils.Constantes;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.MD5Digest;
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
public class Reporte_Comision extends JInternalFrame{
	private JPanel contenedor;
	private JButton btnProcesar;
	DefaultComboBoxModel modeloUsuario = new DefaultComboBoxModel();
	DefaultTableModel modeloRemate = new DefaultTableModel(null, new String[] {
			"NÚMERO DE CONTRATO", "DESCRIPCIÓN", "CAPITAL", "PRECIO VENTA" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	private JLabel jLabel2;
	private JMonthChooser mchMes;
	private JYearChooser ychAnio;
	private JLabel jLabel1;

	public Reporte_Comision() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(736, 107);
		this.setTitle("REPORTE DE REMATES MENSUAL");
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(736, 105));
		this.setBounds(0, 0, 736, 105);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 720, 80);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnProcesar = new JButton();
		contenedor.add(btnProcesar);
		btnProcesar.setText("PROCESAR");
		btnProcesar.setBounds(461, 20, 216, 39);
		btnProcesar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnProcesar.setFont(new java.awt.Font("Segoe UI",1,22));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("MES");
		jLabel1.setBounds(11, 17, 72, 40);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("AÑO");
		jLabel2.setBounds(240, 19, 72, 40);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		ychAnio = new JYearChooser();
		contenedor.add(ychAnio);
		ychAnio.setBounds(312, 19, 100, 40);
		ychAnio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		ychAnio.setFont(new java.awt.Font("Segoe UI",1,22));

		mchMes = new JMonthChooser();
		contenedor.add(mchMes);
		mchMes.setBounds(71, 17, 148, 40);
		mchMes.getComboBox().setFont(new java.awt.Font("Segoe UI",1,22));
		mchMes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnProcesar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});		

	}
	
	public void mostrarReporte() {
		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("str_mes", obtenerNombreMes(mchMes.getMonth()));
		parametros.put("int_mes", mchMes.getMonth()+1);
		parametros.put("int_anio", ychAnio.getYear());
		parametros.put("p", Constantes.NOMBRE_SUCURSAL);
		parametros.put(JRParameter.REPORT_LOCALE, Locale.US);
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_comision.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);			
			viewer.show();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String obtenerNombreMes(int n){
		String mes = "";
		switch (n) {
		case 0:	mes= "ENERO"; break;
		case 1:	mes= "FEBRERO"; break;
		case 2:	mes= "MARZO"; break;
		case 3:	mes= "ABRIL"; break;
		case 4:	mes= "MAYO"; break;
		case 5:	mes= "JUNIO"; break;
		case 6:	mes= "JULIO"; break;
		case 7:	mes= "AGOSTO"; break;
		case 8:	mes= "SEPTIEMBRE"; break;
		case 9:	mes= "OCTUBRE"; break;
		case 10: mes= "NOVIEMBRE"; break;
		default: mes= "DICIEMBRE"; break;
		}
		return mes;
	}
}
