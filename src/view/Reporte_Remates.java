package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Conexion.MySQLConexion;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
public class Reporte_Remates extends JInternalFrame {

	private JPanel contenedor;
	private JYearChooser dycAnio;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JComboBox cboCategoria;
	private JButton btnProcesar;
	private JMonthChooser dmcMes;
	private JLabel jLabel1;
	DefaultTableModel modeloRemate = new DefaultTableModel(null, new String[] {
			"NÚMERO DE CONTRATO", "DESCRIPCIÓN", "CAPITAL", "PRECIO VENTA" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Reporte_Remates() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(736, 107);
		this.setClosable(true);
		this.setTitle("REPORTE DE REMATES MENSUAL");
		this.setPreferredSize(new java.awt.Dimension(1077, 93));
		this.setBounds(0, 0, 1077, 93);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1075, 69);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("MES:");
		jLabel1.setBounds(12, 12, 58, 35);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		dmcMes = new JMonthChooser(false);			
		contenedor.add(dmcMes);
		dmcMes.setBounds(75, 12, 178, 35);		
		dmcMes.getComboBox().setFont(new java.awt.Font("Segoe UI",1,22));

		btnProcesar = new JButton();
		contenedor.add(btnProcesar);
		btnProcesar.setText("PROCESAR");
		btnProcesar.setBounds(822, 13, 216, 30);
		btnProcesar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnProcesar.setFont(new java.awt.Font("Segoe UI",1,22));

		dycAnio = new JYearChooser();
		contenedor.add(dycAnio);
		dycAnio.setBounds(350,12, 100, 30);
		dycAnio.setFont(new java.awt.Font("Segoe UI",1,22));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("AÑO:");
		jLabel2.setForeground(new java.awt.Color(0,128,0));
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setBounds(271, 11, 58, 35);

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("CATEGORIA:");
		jLabel3.setBounds(469, 13, 142, 30);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel3.setForeground(new java.awt.Color(0,128,0));

		cboCategoria = new JComboBox();
		contenedor.add(cboCategoria);
		cboCategoria.setBounds(623, 12, 172, 30);
		cboCategoria.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		cboCategoria.setFont(new java.awt.Font("Segoe UI",1,22));
		cboCategoria.addItem("ELECTRO");
		cboCategoria.addItem("ORO");
		
		btnProcesar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});

	}

	public void mostrarReporte() {
		int rep = cboCategoria.getSelectedIndex();
		String doc = "";
		switch (rep) {
		case 0:
			doc = "reporte_remate.jasper";
			break;
		default:
			doc = "reporte_remate_oro.jasper";
			break;
		}
		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("estado", /*5*/13);
		parametros.put("mes", dmcMes.getMonth() + 1);
		parametros.put("anio", dycAnio.getYear());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject(doc);
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);			
			viewer.show();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
