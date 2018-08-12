package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.JXTitledSeparator;

import Utils.NumberToLetter;

import com.toedter.calendar.JDateChooser;

import Beans.Egreso;
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
public class Registrar_Egreso extends JInternalFrame {

	private JPanel contenedor;
	private JTextField txtMotivoEgreso;
	private JLabel jLabel2;
	private JTextField txtMonto;
	private JXTitledSeparator jSeparator1;
	private JLabel jLabel3;
	private JComboBox cboTipoEgreso;
	private JButton btnSalir;
	private JButton btnRegistrarEgreso;
	private JLabel jLabel1;

	public Registrar_Egreso() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(725, 398);
		this.setPreferredSize(new java.awt.Dimension(826, 385));
		this.setBounds(0, 0, 826, 385);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 824, 360);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("DETALLE");
			jLabel1.setBounds(13, 154, 121, 40);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtMotivoEgreso = new JTextField();
			contenedor.add(txtMotivoEgreso);
			txtMotivoEgreso.setBounds(118, 148, 673, 40);
			txtMotivoEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtMotivoEgreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("MONTO (S/.)");
			jLabel2.setBounds(465, 96, 150, 40);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtMonto = new JTextField();
			contenedor.add(txtMonto);
			txtMonto.setBounds(620, 95, 172, 40);
			txtMonto.setFont(new java.awt.Font("Segoe UI", 1, 24));
			txtMonto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
		}
		{
			btnRegistrarEgreso = new JButton();
			contenedor.add(btnRegistrarEgreso);
			btnRegistrarEgreso.setText("REGISTRAR EGRESO");
			btnRegistrarEgreso.setBounds(12, 217, 484, 113);
			btnRegistrarEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
			btnRegistrarEgreso.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			btnRegistrarEgreso.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					registrarEgreso();					
					if(cboTipoEgreso.getSelectedItem().toString().equalsIgnoreCase("ENV - DESCARGO DE CAJA")){
						JOptionPane.showMessageDialog(null,"<html><h2>Se imprimirá un Recibo Interno, inserte papel ...</h2></html>");
						mostrarReporte();	
					}
					limpiarCajas();
				}
			});
		}
		{
			btnSalir = new JButton();
			contenedor.add(btnSalir);
			btnSalir.setText("SALIR");
			btnSalir.setBounds(508, 217, 283, 113);
			btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 24));
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();
				}
			});
		}
		{
			ComboBoxModel cboTipoEgresoModel = new DefaultComboBoxModel(
					new String[] { "ALQ - ALQUILER", "AGU - AGUA", "LUZ - LUZ",
							"TLF - TELEFONO", "SRV - SERVICIO",
							"PLN - PLANILLA", "DEV - DEVOLUCION DE DINERO",
							"ENV - DESCARGO DE CAJA", "AJS - AJUSTE",
							"OTG - OTROS GASTOS" });
			cboTipoEgreso = new JComboBox();
			contenedor.add(cboTipoEgreso);
			cboTipoEgreso.setModel(cboTipoEgresoModel);
			cboTipoEgreso.setBounds(117, 96, 336, 40);
			cboTipoEgreso.setFont(new java.awt.Font("Segoe UI", 1, 22));
			cboTipoEgreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			cboTipoEgreso.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					txtMotivoEgreso.setText(cboTipoEgreso.getSelectedItem()
							.toString().split(" - ")[1]);
				}
			});
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("MOTIVO");
			jLabel3.setBounds(12, 96, 105, 40);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jSeparator1 = new JXTitledSeparator("REGISTRAR EGRESO DE DINERO");
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(14, 12, 778, 63);
			jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24));
			jSeparator1.setForeground(new java.awt.Color(128, 0, 0));
		}
	}

	public void registrarEgreso() {
		Egreso egreso = new Egreso(Principal.id_libro_caja, txtMotivoEgreso
				.getText().toUpperCase(), cboTipoEgreso.getSelectedItem()
				.toString().split(" - ")[0], Double.parseDouble(txtMonto
				.getText()));
		egreso.registrarEgreso();
		JOptionPane.showMessageDialog(null, "<html><h2>Egreso registrado en Caja.</h2></html>");
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void limpiarCajas() {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setText("");
				((JTextField) o).setBackground(Color.WHITE);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setDate(null);
				((JDateChooser) o).getDateEditor().getUiComponent()
						.setBackground(Color.WHITE);
			}
		}
	}

	public void mostrarReporte() {

		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("monto_egreso", txtMonto.getText());
		parametros.put("monto_egreso_text", new NumberToLetter().convertir(txtMonto.getText(), true));
		parametros.put("detalle", txtMotivoEgreso.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("documento_envio.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
