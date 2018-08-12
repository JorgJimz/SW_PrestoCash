package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

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
public class Bitacora extends JInternalFrame {
	private JPanel contenedor;
	private JButton btnLimpiar;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JDateChooser dchFecha;
	private JComboBox cboPresto;
	private JTextField txtEmpeno;
	private JTextField txtGanancia;
	private JTextField txtAmanece;
	private JLabel jLabel1;
	private JXTitledSeparator jSeparator1;
	private JButton btnGrabar;

	public Bitacora() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(477, 388);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(481, 422));
		this.setBounds(0, 0, 481, 422);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 479, 398);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnGrabar = new JButton();
		contenedor.add(btnGrabar);
		btnGrabar.setBounds(12, 310, 438, 54);
		btnGrabar.setText("GRABAR");
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBackground(new java.awt.Color(0, 255, 255));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registrarBitacora();
			}
		});

		btnLimpiar = new JButton();
		contenedor.add(btnLimpiar);
		btnLimpiar.setBounds(308, 62, 142, 175);
		btnLimpiar.setText("LIMPIAR");
		btnLimpiar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnLimpiar.setBackground(new java.awt.Color(0, 255, 255));

		jSeparator1 = new JXTitledSeparator("REGISTRAR BITACORA DIARIA");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 12, 438, 37);
		jSeparator1.setFont(new java.awt.Font("Segoe UI",1,20));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("AMANECE =");
		jLabel1.setBounds(12, 105, 133, 29);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("GANANCIA =");
		jLabel2.setBounds(12, 154, 133, 29);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("EMPEÑOS =");
		jLabel3.setBounds(12, 208, 133, 29);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel3.setForeground(new java.awt.Color(0,128,0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("AGENCIA = ");
		jLabel4.setBounds(12, 258, 133, 29);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel4.setForeground(new java.awt.Color(0,128,0));

		txtAmanece = new JTextField();
		contenedor.add(txtAmanece);
		txtAmanece.setBounds(163, 109, 133, 29);
		txtAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtAmanece.setFont(new java.awt.Font("Segoe UI",1,20));
		txtAmanece.setForeground(new java.awt.Color(0,64,128));

		txtGanancia = new JTextField();
		contenedor.add(txtGanancia);
		txtGanancia.setBounds(163, 158, 133, 29);
		txtGanancia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtGanancia.setFont(new java.awt.Font("Segoe UI",1,20));
		txtGanancia.setForeground(new java.awt.Color(0,64,128));

		txtEmpeno = new JTextField();
		contenedor.add(txtEmpeno);
		txtEmpeno.setBounds(163, 208, 133, 29);
		txtEmpeno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtEmpeno.setFont(new java.awt.Font("Segoe UI",1,20));
		txtEmpeno.setForeground(new java.awt.Color(0,64,128));

		cboPresto = new JComboBox();
		contenedor.add(cboPresto);
		cboPresto.setBounds(163, 258, 287, 29);
		cboPresto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		cboPresto.setFont(new java.awt.Font("Segoe UI",1,20));
		cboPresto.setForeground(new java.awt.Color(0,64,128));
		
		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("FECHA =");
		jLabel5.setBounds(12, 62, 109, 31);
		jLabel5.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel5.setForeground(new java.awt.Color(0,128,0));
	
	
		dchFecha = new JDateChooser();
		contenedor.add(dchFecha);
		dchFecha.setBounds(163, 61, 133, 29);
		dchFecha.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		dchFecha.setFont(new java.awt.Font("Dialog",1,20));

		cboPresto.addItem("ARENALES");
		cboPresto.addItem("PERU");
		cboPresto.addItem("ARICA");
		cboPresto.addItem("HUAYLAS");

		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component c:contenedor.getComponents()){
					if(c instanceof JTextField){
						((JTextField) c).setText("");
					}
				}
				
			}
		});

	}

	public void registrarBitacora() {
		Beans.Bitacora casa = new Beans.Bitacora(1,
				Double.parseDouble(txtAmanece.getText()),
				Double.parseDouble(txtGanancia.getText()),
				Integer.parseInt(txtEmpeno.getText()), cboPresto.getSelectedItem().toString(),new SimpleDateFormat("yyyy-MM-dd").format(dchFecha.getDate()));		
		casa.registrar();
	}
	
	public void cerrar(){
		this.dispose();
	}

}
