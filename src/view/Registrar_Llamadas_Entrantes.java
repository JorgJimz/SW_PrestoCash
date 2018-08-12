package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTitledSeparator;

import Beans.Llamada_Entrante;

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
public class Registrar_Llamadas_Entrantes extends JInternalFrame{
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JButton btnSalir;
	private JButton btnGrabar;
	private JTextArea txtObs;
	private JLabel lblFecha;
	private JTextField txtNumeroContrato;
	private JXTitledSeparator jSeparator1;
	private JPanel contenedor;

	public Registrar_Llamadas_Entrantes() {
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(new java.awt.Color(255,200,147));
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1276, 946);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("SE COMUNICO POR EL CONTRATO Nº");
			jLabel1.setBounds(12, 68, 365, 37);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("FECHA:");
			jLabel2.setBounds(12, 111, 93, 37);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("DETALLE");
			jLabel3.setBounds(12, 160, 93, 37);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel3.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jSeparator1 = new JXTitledSeparator("NUEVA LLAMADA ENTRANTE");
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(12, 12, 555, 50);
			jSeparator1.setFont(new java.awt.Font("Segoe UI",1,22));
			jSeparator1.setForeground(new java.awt.Color(128,0,0));
		}
		{
			txtNumeroContrato = new JTextField();
			contenedor.add(txtNumeroContrato);
			txtNumeroContrato.setBounds(377, 67, 190, 37);
			txtNumeroContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtNumeroContrato.setFont(new java.awt.Font("Segoe UI",1,22));
			txtNumeroContrato.setForeground(new java.awt.Color(0,64,128));
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(12, 203, 555, 176);
			{
				txtObs = new JTextArea();
				jScrollPane1.setViewportView(txtObs);
				txtObs.setWrapStyleWord(true);
				txtObs.setFont(new java.awt.Font("Segoe UI",1,22));
				txtObs.setForeground(new java.awt.Color(0,64,128));
				txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

			}
		}
		{
			lblFecha = new JLabel(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()).toUpperCase());
			contenedor.add(lblFecha);
			lblFecha.setBounds(96, 111, 226, 37);
			lblFecha.setFont(new java.awt.Font("Segoe UI",1,22));
			lblFecha.setForeground(new java.awt.Color(0,64,128));
			
		}
		{
			btnGrabar = new JButton();
			contenedor.add(btnGrabar);
			btnGrabar.setText("GRABAR");
			btnGrabar.setBounds(12, 391, 326, 73);
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnGrabar.setFont(new java.awt.Font("Segoe UI",1,26));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Llamada_Entrante le = new Llamada_Entrante(Integer.parseInt(txtNumeroContrato.getText()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), txtObs.getText());
					le.grabarLLamadaEntrante();					
				}
			});
		}
		{
			btnSalir = new JButton();
			contenedor.add(btnSalir);
			btnSalir.setText("SALIR");
			btnSalir.setBounds(349, 391, 219, 73);
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnSalir.setFont(new java.awt.Font("Segoe UI",1,26));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();					
				}
			});
		}
		this.setSize(595, 527);
	}
	
	public void cerrar(){
		this.dispose();
	}
}
