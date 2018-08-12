package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Beans.Egreso;
import Beans.Ingreso;

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
public class Registrar_Ingreso extends JInternalFrame{

	private JPanel contenedor;
	private JTextField txtMotivoEgreso;
	private JLabel jLabel2;
	private JXTitledSeparator jSeparator1;
	private JLabel jLabel3;
	private JComboBox cboTipoIngreso;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField txtOtrosIngresos;
	private JTextField txtMontoGanancia;
	private JTextField txtMontoCapital;
	private JButton btnRegistrarIngreso;
	private JButton btnSalir;
	private JLabel jLabel1;

	public Registrar_Ingreso() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(725, 398);
		this.setPreferredSize(new java.awt.Dimension(770, 465));
		this.setBounds(0, 0, 770, 465);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 768, 442);
		contenedor.setBackground(new java.awt.Color(255,200,147));
	
		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("DETALLE");
		jLabel1.setBounds(12, 219, 121, 40);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,128,0));
	
	
		txtMotivoEgreso = new JTextField();
		contenedor.add(txtMotivoEgreso);
		txtMotivoEgreso.setBounds(118, 218, 611, 40);
		txtMotivoEgreso.setFont(new java.awt.Font("Segoe UI",1,24));
		txtMotivoEgreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
	
	
		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("CAPITAL");
		jLabel2.setBounds(12, 161, 99, 40);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(410, 297, 319, 113);
		btnSalir.setFont(new java.awt.Font("Segoe UI",1,24));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
	
	
		ComboBoxModel cboTipoEgresoModel = 
				new DefaultComboBoxModel(
						new String[] { "1%","1% + M","2%","2% + M","M","PAG","ABN","IEX.","VTA", "PET." });
		cboTipoIngreso = new JComboBox();
		contenedor.add(cboTipoIngreso);
		cboTipoIngreso.setModel(cboTipoEgresoModel);
		cboTipoIngreso.setBounds(117, 95, 613, 40);
		cboTipoIngreso.setFont(new java.awt.Font("Segoe UI",1,24));
		cboTipoIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
	
	
		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("MOTIVO");
		jLabel3.setBounds(12, 96, 105, 40);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel3.setForeground(new java.awt.Color(0,128,0));
	
	
		jSeparator1 = new JXTitledSeparator("REGISTRAR INGRESO DIVERSO");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(14, 12, 716, 63);
		jSeparator1.setFont(new java.awt.Font("Segoe UI",1,24));
		jSeparator1.setForeground(new java.awt.Color(128,0,0));
	
	
		btnRegistrarIngreso = new JButton();
		contenedor.add(btnRegistrarIngreso);
		btnRegistrarIngreso.setText("REGISTRAR INGRESO");
		btnRegistrarIngreso.setBounds(12, 298, 379, 113);
		btnRegistrarIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnRegistrarIngreso.setFont(new java.awt.Font("Segoe UI",1,26));
		btnRegistrarIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registrarIngreso();
				limpiarCajas();
			}
		});
		
		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("GANANCIA");
		jLabel4.setBounds(255, 161, 124, 40);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel4.setForeground(new java.awt.Color(0,128,0));
	
	
		txtMontoCapital = new JTextField();
		contenedor.add(txtMontoCapital);
		txtMontoCapital.setBounds(117, 161, 127, 40);
		txtMontoCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtMontoCapital.setFont(new java.awt.Font("Segoe UI",1,24));

		txtMontoGanancia = new JTextField();
		contenedor.add(txtMontoGanancia);
		txtMontoGanancia.setBounds(385, 161, 127, 40);
		txtMontoGanancia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtMontoGanancia.setFont(new java.awt.Font("Segoe UI",1,24));
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("OTROS");
			jLabel5.setBounds(521, 161, 82, 40);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel5.setForeground(new java.awt.Color(0,128,0));
		}
		{
			txtOtrosIngresos = new JTextField();
			contenedor.add(txtOtrosIngresos);
			txtOtrosIngresos.setBounds(603, 161, 127, 40);
			txtOtrosIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtOtrosIngresos.setFont(new java.awt.Font("Segoe UI",1,24));
		}

	}
	
	public void registrarIngreso(){
		Ingreso ingreso = new Ingreso(Principal.id_libro_caja, txtMotivoEgreso.getText()+"-"+cboTipoIngreso.getSelectedItem().toString(), "IM.", Double.parseDouble(txtMontoCapital.getText()), Double.parseDouble(txtMontoGanancia.getText()),Double.parseDouble(txtOtrosIngresos.getText()));
		ingreso.registrarIngreso();
	}
	
	 public void cerrar(){
			int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION){
			this.dispose();
			}
	 }
	 
	 public void limpiarCajas(){
			for(Object o : contenedor.getComponents()){
				if(o instanceof JTextField){
					((JTextField)o).setText("");
					((JTextField)o).setBackground(Color.WHITE);
				}if(o instanceof JDateChooser){
					((JDateChooser)o).setDate(null);
					((JDateChooser)o).getDateEditor().getUiComponent().setBackground(Color.WHITE);           
				}				
			}
		}	
}
