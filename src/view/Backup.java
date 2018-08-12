package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Beans.BackUp;


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
public class Backup extends JInternalFrame{
	private JPanel contenedor;
	private JButton btnBackup;
	private JButton btnSalir;
	private JButton btnExaminar;
	private JTextField txtRuta;
	private JLabel jLabel1;

	public Backup() {
		this.setVisible(true);
		this.setLayout(null);
		this.setPreferredSize(new java.awt.Dimension(653, 279));
		this.setBounds(0, 0, 653, 279);
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 651, 255);
		contenedor.setBackground(new java.awt.Color(255,200,147));
		
		btnBackup = new JButton();
		contenedor.add(btnBackup);
		btnBackup.setText("PROCESAR");
		btnBackup.setBounds(12, 156, 355, 64);
		btnBackup.setFont(new java.awt.Font("Segoe UI",1,28));
		btnBackup.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnBackup.setBackground(new java.awt.Color(0,128,255));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("BACK-UP");
		jLabel1.setBounds(237, 12, 237, 66);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,48));
		jLabel1.setForeground(new java.awt.Color(0,128,0));
	
	
		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(373, 156, 242, 64);
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnSalir.setFont(new java.awt.Font("Segoe UI",1,36));
		btnSalir.setBackground(new java.awt.Color(0,128,255));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});

		txtRuta = new JTextField();
		contenedor.add(txtRuta);
		txtRuta.setBounds(12, 94, 449, 41);
		txtRuta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtRuta.setFont(new java.awt.Font("Segoe UI",1,24));
		txtRuta.setForeground(new java.awt.Color(0,64,128));

		btnExaminar = new JButton();
		contenedor.add(btnExaminar);
		btnExaminar.setText("EXAMINAR...");
		btnExaminar.setBounds(467, 94, 149, 41);
		btnExaminar.setFont(new java.awt.Font("Segoe UI",1,20));
		btnExaminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnExaminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfcBackup = new JFileChooser();
				jfcBackup.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int sel = jfcBackup.showSaveDialog(contenedor);	
				if(sel == jfcBackup.APPROVE_OPTION){
					String fecha_backup = new SimpleDateFormat("dd-MMM-yyyy").format(new Date()).toUpperCase();
					txtRuta.setText(jfcBackup.getSelectedFile().toString()+"\\"+"BACKUP_PRESTOCASH_"+fecha_backup);					
				}
			}
		});

		btnBackup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BackUp backup = new BackUp();				
				backup.CrearBackup("localhost", "3306", "root", "mysql", "prestocash", txtRuta.getText()+".sql");
			}
		});
		
	}
	
	public void cerrar(){
		this.dispose();
	}
}
