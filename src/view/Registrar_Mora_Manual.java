package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Beans.Mora;
import Beans.Pago_Contrato;

import com.toedter.calendar.JDateChooser;

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
public class Registrar_Mora_Manual extends JInternalFrame{
	private JLabel jLabel1;
	private JLabel jLabel5;
	private JTextField txtValorMora;
	private JTable tbPagos;
	private JScrollPane jScrollPane1;
	private JButton btnGrabar;
	private JLabel lblContrato;
	private int numero_contrato;
	private JPanel contenedor;
	ArrayList<Mora> moras = new ArrayList<Mora>();
	DefaultTableModel modeloMoras = new DefaultTableModel(null,new String[]{"VALOR MORA","ESTADO"}){
				public boolean isCellEditable(int rowIndex, int colIndex){
					return false;
				}
	};

	public Registrar_Mora_Manual(int contrato) {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(929, 398);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(966, 261));
		this.setBounds(0, 0, 966, 261);
		numero_contrato = contrato;
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 964, 239);
		contenedor.setBackground(new java.awt.Color(255,200,147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CONTRATO");
			jLabel1.setBounds(21, 12, 142, 32);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("VALOR MORA");
			jLabel5.setBounds(21, 84, 168, 32);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel5.setForeground(new java.awt.Color(0,128,0));
		}
		{
			lblContrato = new JLabel();
			contenedor.add(lblContrato);
			lblContrato.setBounds(182, 17, 184, 32);
			lblContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblContrato.setBackground(new java.awt.Color(255,255,255));
			lblContrato.setOpaque(true);
			lblContrato.setFont(new java.awt.Font("Segoe UI",1,22));
			lblContrato.setForeground(new java.awt.Color(0,64,128));
			lblContrato.setText(numero_contrato+"");
		}
		{
			btnGrabar = new JButton();
			contenedor.add(btnGrabar);
			btnGrabar.setText("PROCESAR");
			btnGrabar.setEnabled(false);
			btnGrabar.setBounds(24, 155, 342, 47);	
			btnGrabar.setFont(new java.awt.Font("Segoe UI",1,24));
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						grabarMora();
						limpiarCajas();
				
				}
			});
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(398, 13, 554, 189);						
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			tbPagos = new JTable();
			jScrollPane1.setViewportView(tbPagos);		
			tbPagos.setModel(modeloMoras);
			tbPagos.setRowHeight(30);
			tbPagos.setFont(new Font("Segoe UI", Font.BOLD, 18));
			tbPagos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbPagos.getTableHeader().setForeground(new Color(181,0,0));				
			
		}
		{
			txtValorMora = new JTextField();
			contenedor.add(txtValorMora);
			txtValorMora.setBounds(182, 84, 184, 32);
			txtValorMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			txtValorMora.setFont(new java.awt.Font("Segoe UI",1,22));
			txtValorMora.setForeground(new java.awt.Color(0,64,128));
			txtValorMora.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						agregarMora();
						listarMoras();
						btnGrabar.setEnabled(true);
					}
				}
			});
		}

	}
	
	public void agregarMora(){
		Mora mora = new Mora(Integer.parseInt(lblContrato.getText()),Double.parseDouble(txtValorMora.getText()));
		moras.add(mora);		
		txtValorMora.setText("");
	}
	
	public void listarMoras(){
		modeloMoras.setRowCount(0);
		for(Mora m : moras){
			modeloMoras.addRow(new Object[]{m.getValor_mora(),"ACTIVA"});
		}tbPagos.setModel(modeloMoras);
	}	
	
	public void grabarMora(){
		for(Mora m: moras){			
			m.grabarMora();
		}
		clousureIntempestivo();
	}
	
	public void limpiarCajas(){		
		txtValorMora.setText("");	
	}
	
	public void cerrar(){
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
		this.dispose();
		}
	}
	
	public void clousureIntempestivo(){
		this.dispose();
	}
}
