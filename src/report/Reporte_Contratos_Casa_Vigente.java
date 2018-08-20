package report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import Utils.ComboItem;
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
public class Reporte_Contratos_Casa_Vigente extends JInternalFrame {

	private JPanel contenedor;
	private JComboBox cboTipos;
	private JButton btnOk;
	private JLabel jLabel1;
	

	public Reporte_Contratos_Casa_Vigente() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(723, 99));
		this.setBounds(0, 0, 723, 99);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 722, 75);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("CLICK EN BUSCAR PARA VISUALIZAR EL REPORTE ->");
		jLabel1.setBounds(12, 19, 530, 29);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		btnOk = new JButton();
		contenedor.add(btnOk);
		btnOk.setText("BUSCAR");
		btnOk.setBounds(554, 20, 143, 29);
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

	public void mostrarReporte() {
		try {			
			Connection con = MySQLConexion.getConexion();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("tipos", "HIPOTECA%");
						parametros.put("p", Constantes.SUCURSAL);
			try {
				JasperReport reporte = (JasperReport) JRLoader
						.loadObject("reporte_contrato_auto_activo.jasper");
				JasperPrint print = JasperFillManager.fillReport(reporte,
						parametros, con);
				JasperViewer viewer = new JasperViewer(print, false);
				viewer.show();
				viewer.toFront();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
