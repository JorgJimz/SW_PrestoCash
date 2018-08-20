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
public class Reporte_Contratos_X_Tipo extends JInternalFrame {

	private JPanel contenedor;
	private JComboBox cboTipos;
	private JComboBox cboEstado;
	private JButton btnOk;
	private JLabel jLabel1;
	private DefaultComboBoxModel modelo = new DefaultComboBoxModel(
			new String[] { "ELECTRO", "ORO", "AUTO", "MOTO", "CASA" });
	private DefaultComboBoxModel modelo_estado = new DefaultComboBoxModel();

	public Reporte_Contratos_X_Tipo() {
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

		cboTipos = new JComboBox(modelo);
		contenedor.add(cboTipos);
		cboTipos.setBounds(200, 19, 165, 29);
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
		btnOk.setBounds(554, 20, 143, 29);
		btnOk.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnOk.setFont(new java.awt.Font("Segoe UI", 1, 18));

		cboEstado = new JComboBox();
		contenedor.add(cboEstado);

		cboEstado.setBounds(377, 19, 165, 29);
		cboEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		cboEstado.setFont(new java.awt.Font("Segoe UI", 1, 20));

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});
		cargarEstados();
	}

	public void mostrarReporte() {
		try {
			String criteria;
			if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("ELECTRO")) {
				criteria = "ELECTRO%";
			} else if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("ORO")) {
				criteria = "ORO%";
			} else if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("AUTO")) {
				criteria = "AUTO%";
			} else if (cboTipos.getSelectedItem().toString()
					.equalsIgnoreCase("MOTO")) {
				criteria = "MOTO%";
			} else {
				criteria = "HIPOTECA%";
			}
			Connection con = MySQLConexion.getConexion();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("tipos", criteria);
			parametros.put("estado", ((ComboItem)cboEstado.getSelectedItem()).getValue_int());
			parametros.put("p", Constantes.SUCURSAL);
			try {
				JasperReport reporte = (JasperReport) JRLoader
						.loadObject((((ComboItem)cboEstado.getSelectedItem()).getValue_int() != 0)? "reporte_contrato_x_tipo.jasper":"reporte_contrato_x_tipo_global.jasper");
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

	public void cargarEstados() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT id_est,des_est FROM tb_estado_contrato";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			ComboItem cit = new ComboItem(0,"TODOS");
			modelo_estado.addElement(cit);
			while (rs.next()) {
				ComboItem ci = new ComboItem(rs.getInt(1), rs.getString(2));
				modelo_estado.addElement(ci);
			}
			cboEstado.setModel(modelo_estado);
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

}
