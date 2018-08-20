package report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

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
public class Reporte_Mensual_Contratos extends JInternalFrame {

	private JPanel contenedor;
	private JMonthChooser cboMes;
	private JYearChooser cboAnio;
	private JButton btnOk;
	private JLabel jLabel1;
	private DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel(new String[]{"TODOS","ACTIVO","VENCIDO","PRE","POST","RIESGO"});

	public Reporte_Mensual_Contratos() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(711, 99));
		this.setBounds(0, 0, 711, 99);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 710, 75);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		cboMes = new JMonthChooser();
		BoxLayout cboMesLayout = new BoxLayout(cboMes, javax.swing.BoxLayout.X_AXIS);
		cboMes.setLayout(cboMesLayout);
		contenedor.add(cboMes);
		cboMes.setBounds(200, 19, 207, 29);
		cboMes.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboMes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("SELECCIONE MES:");
		jLabel1.setBounds(12, 19, 207, 29);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		btnOk = new JButton();
		contenedor.add(btnOk);
		btnOk.setText("BUSCAR");
		btnOk.setBounds(530, 20, 143, 29);
		btnOk.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnOk.setFont(new java.awt.Font("Segoe UI", 1, 18));

		cboAnio = new JYearChooser();
		contenedor.add(cboAnio);
		cboAnio.setBounds(419, 19, 99, 27);
		cboAnio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		cboAnio.setFont(new java.awt.Font("Segoe UI", 1, 22));

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});

	}

	public void mostrarReporte() {
		Connection con = MySQLConexion.getConexion();		
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_mensual_empenios.jasper");
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("a", cboAnio.getYear());
			parametros.put("m", cboMes.getMonth() + 1);
			parametros.put("p", Constantes.NOMBRE_SUCURSAL);			
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
