package report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Utils.Constantes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
public class Reporte_Vitrina extends JInternalFrame {

	private JPanel contenedor;
	private JComboBox cboTipos;
	private JButton btnOk;
	private JLabel jLabel1;
	private DefaultComboBoxModel modelo = new DefaultComboBoxModel(
			new String[] { "ELECTRO", "ORO" });

	public Reporte_Vitrina() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(586, 99));
		this.setBounds(0, 0, 586, 99);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 585, 75);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		cboTipos = new JComboBox(modelo);
		contenedor.add(cboTipos);
		cboTipos.setBounds(200, 19, 207, 29);
		cboTipos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboTipos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("SELECCIONE TIPO:");
		jLabel1.setBounds(12, 19, 207, 29);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		btnOk = new JButton();
		contenedor.add(btnOk);
		btnOk.setText("BUSCAR");
		btnOk.setBounds(419, 20, 143, 29);
		btnOk.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnOk.setFont(new java.awt.Font("Segoe UI", 1, 18));
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});

	}

	public void mostrarReporte(){
		try {
			String criteria="";
			if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("ELECTRO")) {
				criteria = "reporte_vitrina.jasper";
			} else if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("ORO")) {
				criteria = "reporte_vitrina_oro.jasper";
			}
			Connection con = MySQLConexion.getConexion();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("p", Constantes.SUCURSAL);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(criteria);
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.show();
			viewer.toFront();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
