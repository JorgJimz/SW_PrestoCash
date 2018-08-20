package maintenance;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.ImageIcon;
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

import org.jdesktop.swingx.JXSearchField;

import com.toedter.calendar.JDateChooser;

import Beans.Cliente;
import Beans.Prestamo;
import Conexion.MySQLConexion;

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
public class Mantenimiento_Prestamos extends JInternalFrame{
	private JLabel jLabel1;
	private JTextField txtTipo;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JScrollPane jScrollPane1;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnGrabar;
	private JTable tbPrestamos;
	private JTextField txtInteres;
	private JTextField txtDescripcion;
	private JPanel contenedor;
	Connection con = MySQLConexion.getConexion();
	private JTextField txtMora;
	DefaultTableModel modeloPrestamos = new DefaultTableModel(null,new String[]{"TIPO","DESCRIPCIÓN","INTERES (%)","MORA (%)"}){
		public boolean isCellEditable(int rowIndex, int colIndex){
			return false;
		}
	};

	public Mantenimiento_Prestamos() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE PRESTAMOS");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(520, 241);	
		this.setPreferredSize(new java.awt.Dimension(887, 451));
		this.setBounds(0, 0, 887, 451);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 886, 427);
		contenedor.setBackground(new java.awt.Color(255,200,147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("TIPO:");
		jLabel1.setBounds(12, 18, 84, 29);	
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		txtTipo = new JTextField();
		contenedor.add(txtTipo);
		txtTipo.setBounds(71, 16, 47, 34);		
		txtTipo.setFont(new java.awt.Font("Segoe UI",1,20));
		txtTipo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("DESCRIPCIÓN:");
		jLabel2.setBounds(130, 18, 171, 29);	
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		txtDescripcion = new JTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(274, 16, 301, 34);	
		txtDescripcion.setFont(new java.awt.Font("Segoe UI",1,20));
		txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("I %");
		jLabel3.setBounds(600, 20, 50, 29);	
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel3.setForeground(new java.awt.Color(0,128,0));

		txtInteres = new JTextField();
		contenedor.add(txtInteres);
		txtInteres.setBounds(650, 18, 66, 34);
		txtInteres.setFont(new java.awt.Font("Segoe UI",1,20));
		txtInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(14, 71, 562, 323);
		jScrollPane1.setBackground(new java.awt.Color(255,255,255));
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnGrabar = new JButton(new ImageIcon("img/registrarPrestamo.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setText(" GRABAR");
		btnGrabar.setBounds(593, 71, 267, 77);
		btnGrabar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validador(contenedor)){
					registrarPrestamo();
				}else{
					JOptionPane.showMessageDialog(null, "Faltan completar datos.");
				}
				
			}
		});
			
		btnEditar = new JButton(new ImageIcon("img/editarPrestamo.png"));
		contenedor.add(btnEditar);
		btnEditar.setText(" EDITAR");
		btnEditar.setBounds(593, 153, 267, 77);	
		btnEditar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualizarPrestamo();
			}
		});
				
		btnEliminar = new JButton(new ImageIcon("img/retirarPrestamo.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setText(" ELIMINAR");
		btnEliminar.setBounds(593, 235, 267, 77);
		btnEliminar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarPrestamo();
			}
		});
				
		btnCancelar = new JButton(new ImageIcon("img/salir.png"));
		contenedor.add(btnCancelar);
		btnCancelar.setText(" SALIR");
		btnCancelar.setBounds(593, 317, 267, 77);
		btnCancelar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("M %");
			jLabel4.setBounds(734, 20, 57, 29);	
			jLabel4.setFont(new java.awt.Font("Segoe UI",1,20));
			jLabel4.setForeground(new java.awt.Color(0,128,0));
		}

		txtMora = new JTextField();
		contenedor.add(txtMora);
		txtMora.setBounds(794, 21, 66, 34);
		txtMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtMora.setFont(new java.awt.Font("Segoe UI",1,20));

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});

		tbPrestamos = new JTable();
		tbPrestamos.setFont(new Font("Segoe UI", Font.BOLD, 24));
		tbPrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 24));
		tbPrestamos.getTableHeader().setForeground(new Color(181,0,0));
		tbPrestamos.setRowHeight(35);
		jScrollPane1.setViewportView(tbPrestamos);		
		tbPrestamos.setModel(modeloPrestamos);
		tbPrestamos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					int fila = tbPrestamos.getSelectedRow();
					txtTipo.setText(tbPrestamos.getValueAt(fila, 0).toString());
					txtDescripcion.setText(tbPrestamos.getValueAt(fila, 1).toString());
					txtInteres.setText(tbPrestamos.getValueAt(fila,2).toString());
					txtMora.setText(tbPrestamos.getValueAt(fila,3).toString());
					txtTipo.setEnabled(false);
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
			}
		});
		
		listarPrestamos();
	}
	
	public void listarPrestamos(){
		try {
			String sql = "SELECT * FROM tb_prestamo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloPrestamos.setRowCount(0);
			while(rs.next()){
				modeloPrestamos.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4)});
			}tbPrestamos.setModel(modeloPrestamos);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mensaje(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	public void cerrar(){
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
		this.dispose();
		}
	}
	
	public void buscarPrestamo(){
		String val = JOptionPane.showInputDialog("Ingrese el tipo de préstamo a modificar.");
		try {
			String sql = "SELECT * FROM tb_prestamo WHERE tip_pre=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, val);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				txtTipo.setText(rs.getString(1));
				txtTipo.setEnabled(false);
				txtDescripcion.setText(rs.getString(2));
				txtInteres.setText(rs.getDouble(3)+"");
				txtMora.setText(rs.getDouble(4)+"");
			}else{
				mensaje("No existe tal tipo de préstamo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registrarPrestamo(){
		Prestamo p = new Prestamo(txtTipo.getText(), txtDescripcion.getText(), Double.parseDouble(txtInteres.getText()),Double.parseDouble(txtMora.getText()));
		if(p.validar()){
			p.registrar();
			mensaje("Nuevo tipo de préstamo registrado con éxito.");
			limpiarCajas();
			listarPrestamos();
			txtTipo.setEnabled(true);
		}else{
			mensaje("El tipo de préstamo que intentas registrar ya existe.");
			limpiarCajas();
			txtTipo.setEnabled(true);
		}
	}
	
	public void actualizarPrestamo(){
		Prestamo p = new Prestamo(txtTipo.getText(), txtDescripcion.getText(), Double.parseDouble(txtInteres.getText()),Double.parseDouble(txtMora.getText()));
		p.actualizar();
		limpiarCajas();
		listarPrestamos();
		txtTipo.setEnabled(true);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);
		mensaje("Tipo de préstamo actualizado con éxito.");
	}
	
	public void eliminarPrestamo(){
		int n = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar este tipo de préstamo?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			try {
				String sql = "DELETE FROM tb_prestamo WHERE cod_pre=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, txtTipo.getText());
				pst.executeUpdate();
				limpiarCajas();
				listarPrestamos();
				mensaje("Se eliminó el tipo de préstamo.");
				btnEditar.setEnabled(false);
				btnEliminar.setEnabled(false);
				txtTipo.setEnabled(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void limpiarCajas(){
		for(Object o : contenedor.getComponents()){
			if(o instanceof JTextField){
				((JTextField)o).setText("");
				((JTextField)o).setBackground(Color.WHITE);
			}if(o instanceof JDateChooser){
				((JDateChooser)o).setDate(null);
			}
				
		}
	}
	
	public boolean validador(JPanel pnl){
		boolean val = true;
		
		for(Object o : pnl.getComponents()){
			if(o instanceof JTextField && !(o instanceof JXSearchField)){
				if(((JTextField) o).getText().equals("")){
					((JTextField)o).setBackground(Color.RED);
					((JTextField)o).requestFocus();
						return val = false;
				}
			}
			if(o instanceof JDateChooser){
				if(((JDateChooser)o).getDate() == null){
					((JDateChooser)o).getDateEditor().getUiComponent().setBackground(Color.RED);
					return val = false;
				}
			}
				
			}
					return val;
	}
	
	
}
