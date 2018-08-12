package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Beans.Usuario;
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
public class Mantenimiento_Usuarios extends JInternalFrame{
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField txtNombre;
	private JLabel jLabel5;
	private JTable tbUsuarios;
	private JScrollPane scp1;
	private JButton btnSalir;
	private JButton btnGrabar;
	private JTextField txtMaterno;
	private JTextField txtPsw;
	private JTextField txtPaterno;
	private JTextField txtDni;
	Connection con = MySQLConexion.getConexion();
	private JFormattedTextField txtHoraIngreso;
	private JLabel jLabel8;
	private JXSearchField sfBuscar;
	private JLabel jLabel7;
	private JComboBox cboTipoUsuario;
	private JLabel jLabel6;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JPopupMenu popBusqueda;
	private JPanel contenedor;
	private String filtro = "dni_usu";
	SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
	DefaultTableModel modeloUsuarios = new DefaultTableModel(null,new String[]{"DNI","A.PATERNO","A.MATERNO","NOMBRE","CLAVE","TIPO","HORA DE INGRESO"}){
		public boolean isCellEditable(int rowIndex, int colIndex){
			return false;
		}
	};
	public Mantenimiento_Usuarios(){
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE USUARIOS");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(750, 380);
		this.setPreferredSize(new java.awt.Dimension(1153, 688));		
		this.setBounds(0, 0, 1153, 688);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1152, 664);
		contenedor.setBackground(new java.awt.Color(255,184,113));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("D.IDENTIDAD:");
		jLabel1.setBounds(12, 15, 158, 39);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,85,0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("NOMBRE:");
		jLabel2.setBounds(585, 22, 122, 39);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setForeground(new java.awt.Color(0,85,0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("A.PATERNO:");
		jLabel3.setBounds(12, 73, 153, 39);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel3.setForeground(new java.awt.Color(0,85,0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("CONTRASEÑA:");
		jLabel4.setBounds(12, 133, 158, 39);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel4.setForeground(new java.awt.Color(0,85,0));

		txtDni = new JTextField();
		contenedor.add(txtDni);
		txtDni.setBounds(199, 21, 336, 39);
		txtDni.setFont(new java.awt.Font("Segoe UI",1,24));
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		txtNombre = new JTextField();
		contenedor.add(txtNombre);
		txtNombre.setBounds(782, 21, 336, 39);
		txtNombre.setFont(new java.awt.Font("Segoe UI",1,24));
		txtNombre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		txtPaterno = new JTextField();
		contenedor.add(txtPaterno);
		txtPaterno.setBounds(199, 72, 336, 39);	
		txtPaterno.setFont(new java.awt.Font("Segoe UI",1,24));
		txtPaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		txtPsw = new JTextField();
		contenedor.add(txtPsw);
		txtPsw.setBounds(199, 132, 336, 39);
		txtPsw.setFont(new java.awt.Font("Segoe UI",1,24));
		txtPsw.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("A.MATERNO:");
		jLabel5.setBounds(585, 73, 177, 39);
		jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel5.setForeground(new java.awt.Color(0,100,0));

		txtMaterno = new JTextField();
		contenedor.add(txtMaterno);
		txtMaterno.setBounds(782, 72, 336, 39);
		txtMaterno.setFont(new java.awt.Font("Segoe UI",1,24));
		txtMaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnGrabar = new JButton(new ImageIcon("img/registrarUsuario.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setText(" REGISTRAR");
		btnGrabar.setBounds(12, 551, 272, 75);
		btnGrabar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validador(contenedor)){
					registrarUsuario();
					limpiarCajas();
				}else{
					JOptionPane.showMessageDialog(null, "Faltan completar datos.");
				}			
			}
		});
		
		btnSalir = new JButton(new ImageIcon("img/salir.png"));
		contenedor.add(btnSalir);
		btnSalir.setText(" SALIR");
		btnSalir.setBounds(846, 551, 272, 75);
		btnSalir.setFont(new java.awt.Font("Segoe UI",1,26));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
		
		JMenuItem mniDni = new JMenuItem("Por D.N.I.");
		mniDni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				filtro = "dni_usu";				
			}
		});
		JMenuItem mniPaterno = new JMenuItem("Por Apellido");
		mniPaterno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				filtro = "pat_usu";				
			}
		});
		popBusqueda = new JPopupMenu();
		popBusqueda.add(mniDni);
		popBusqueda.add(mniPaterno);		
		
		scp1 = new JScrollPane();
		contenedor.add(scp1);
		scp1.setBounds(14, 247, 1104, 282);

		btnEditar = new JButton(new ImageIcon("img/actualizarUsuario.png"));
		contenedor.add(btnEditar);
		btnEditar.setText(" ACTUALIZAR");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(290, 551, 272, 75);
		btnEditar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualizarUsuario();
			}
		});
		
		btnEliminar = new JButton(new ImageIcon("img/retirarUsuario.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setText(" RETIRAR");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(568, 551, 272, 75);
		btnEliminar.setFont(new java.awt.Font("Segoe UI",1,26));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				retirarUsuario();
			}
		});
		
		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("NIVEL-ACCESO:");
		jLabel6.setBounds(585, 134, 185, 39);
		jLabel6.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel6.setForeground(new java.awt.Color(0,85,0));

		ComboBoxModel cboTipoUsuarioModel = 
				new DefaultComboBoxModel(
						new String[] { "USUARIO", "ADMINISTRADOR" });
		cboTipoUsuario = new JComboBox();
		contenedor.add(cboTipoUsuario);
		cboTipoUsuario.setModel(cboTipoUsuarioModel);
		cboTipoUsuario.setBounds(782, 133, 336, 39);
		cboTipoUsuario.setFont(new java.awt.Font("Segoe UI",1,24));
		cboTipoUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("BÚSQUEDA:");
		jLabel7.setBounds(585, 191, 146, 39);
		jLabel7.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel7.setForeground(new java.awt.Color(0,85,0));

		sfBuscar = new JXSearchField();
		contenedor.add(sfBuscar);
		sfBuscar.setBounds(782, 190, 336, 39);
		sfBuscar.setFont(new java.awt.Font("Segoe UI",1,24));
		sfBuscar.setFindPopupMenu(popBusqueda);
		sfBuscar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("HORA ENTRADA:");
		jLabel8.setBounds(14, 191, 197, 39);
		jLabel8.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel8.setForeground(new java.awt.Color(0,64,0));

		txtHoraIngreso = new JFormattedTextField(sdfHora);
		contenedor.add(txtHoraIngreso);
		txtHoraIngreso.setBounds(199, 191, 336, 39);
		txtHoraIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtHoraIngreso.setFont(new java.awt.Font("Segoe UI",1,22));

		sfBuscar.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				buscarUsuarios();
			}
		});
	
		tbUsuarios = new JTable();
		scp1.setViewportView(tbUsuarios);
		tbUsuarios.setRowHeight(35);
		tbUsuarios	.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbUsuarios.getTableHeader().setForeground(new Color(181,0,0));
		tbUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					int fila = tbUsuarios.getSelectedRow();
					txtNombre.setText(tbUsuarios.getValueAt(fila, 3).toString());
					txtDni.setText(tbUsuarios.getValueAt(fila, 0).toString());
					txtPaterno.setText(tbUsuarios.getValueAt(fila, 1).toString());
					txtMaterno.setText(tbUsuarios.getValueAt(fila, 2).toString());
					txtPsw.setText(tbUsuarios.getValueAt(fila, 4).toString());
					cboTipoUsuario.setSelectedItem(tbUsuarios.getValueAt(fila, 5).toString());
					txtHoraIngreso.setText(tbUsuarios.getValueAt(fila, 6).toString());
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);	
					txtDni.setEnabled(false);
				}
			}
		});
		listarUsuarios();
	}
	
	public void listarUsuarios(){
		try {
			String sql = "SELECT * FROM tb_usuario WHERE est_usu=1";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloUsuarios.setRowCount(0);
			while(rs.next()){
				modeloUsuarios.addRow(new Object[]{rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(2),rs.getString(5),rs.getString(7),rs.getString(8)});
			}
			tbUsuarios.setModel(modeloUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registrarUsuario(){
		Usuario u = new Usuario(txtDni.getText(), txtNombre.getText().toUpperCase() , txtPaterno.getText().toUpperCase(), txtMaterno.getText().toUpperCase(), txtPsw.getText(),1,cboTipoUsuario.getSelectedItem().toString(),txtHoraIngreso.getText());
		if(u.validar()){
			u.registrar();
			limpiarCajas();
			listarUsuarios();
		}else{JOptionPane.showMessageDialog(null, "El D.N.I. "+txtDni.getText()+" ya es un usuario registrado.");}	
	}
	
	public void actualizarUsuario(){
		Usuario u = new Usuario(txtDni.getText(), txtNombre.getText().toUpperCase() , txtPaterno.getText().toUpperCase(), txtMaterno.getText().toUpperCase(), txtPsw.getText(),1,cboTipoUsuario.getSelectedItem().toString(),txtHoraIngreso.getText());
		u.actualizar();
		listarUsuarios();
		limpiarCajas();
		txtDni.setEnabled(true);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	public void retirarUsuario(){
		int n = JOptionPane.showConfirmDialog(null, "¿Realmente desea retirar al usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			try {
				String sql = "UPDATE tb_usuario SET est_usu=0 WHERE dni_usu=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, txtDni.getText());
				pst.executeUpdate();	
				JOptionPane.showMessageDialog(null, "Usuario retirado con éxito.");
			} catch (Exception e) {
				e.printStackTrace();
			}			
			listarUsuarios();
			limpiarCajas();
		}	
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
	
	public void buscarUsuarios(){
		try {
			String sql = "SELECT * FROM tb_usuario WHERE "+filtro+" LIKE ? AND est_usu=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sfBuscar.getText()+"%");
			ResultSet rs = pst.executeQuery();
			modeloUsuarios.setRowCount(0);
			while(rs.next()){
				modeloUsuarios.addRow(new Object[]{rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(2),rs.getString(5),rs.getString(7)});
			}
			tbUsuarios.setModel(modeloUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
