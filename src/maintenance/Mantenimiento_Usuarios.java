package maintenance;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXTable;

import common.ComboItem;
import common.Constantes;
import common.RenderMC;
import common.Utiles;
import controller.UsuarioController;
import model.Perfil;
import model.Usuario;
import view.Principal;


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
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Mantenimiento_Usuarios extends JInternalFrame {
	private JTextField txtNombre;
	private JXTable tbUsuarios;
	private JScrollPane spUsuario;
	private JButton btnGrabar;
	private JTextField txtMaterno;
	private JTextField txtPsw;
	private JTextField txtPaterno;
	private JTextField txtLogin;
	private JTextField txtHoraIngreso;
	private JComboBox cboTipoUsuario;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JPanel contenedor;
	private JTextField lblId;

	public Mantenimiento_Usuarios() {
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setTitle("MANTENIMIENTO DE USUARIOS");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(750, 380);
		this.setPreferredSize(new java.awt.Dimension(1324, 607));
		this.setBounds(0, 0, 1324, 607);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1322, 578);
		contenedor.setBackground(new java.awt.Color(255, 184, 113));

		txtLogin = new JTextField();
		contenedor.add(txtLogin);
		txtLogin.setBounds(24, 15, 316, 66);
		txtLogin.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtLogin.setOpaque(false);
		txtLogin.setBorder(BorderFactory.createTitledBorder(null, "USUARIO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtNombre = new JTextField();
		contenedor.add(txtNombre);
		txtNombre.setBounds(684, 93, 316, 66);
		txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtNombre.setOpaque(false);
		txtNombre.setBorder(BorderFactory.createTitledBorder(null, "NOMBRES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtPaterno = new JTextField();
		contenedor.add(txtPaterno);
		txtPaterno.setBounds(26, 93, 316, 66);
		txtPaterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPaterno.setOpaque(false);
		txtPaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO PATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtPsw = new JTextField();
		contenedor.add(txtPsw);
		txtPsw.setBounds(353, 15, 316, 66);
		txtPsw.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPsw.setOpaque(false);
		txtPsw.setBorder(BorderFactory.createTitledBorder(null, "CONTRASEÑA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtMaterno = new JTextField();
		contenedor.add(txtMaterno);
		txtMaterno.setBounds(354, 93, 316, 66);
		txtMaterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtMaterno.setOpaque(false);
		txtMaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO MATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setToolTipText("REGISTRAR USUARIO");
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setBounds(1011, 90, 64, 64);
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor)) {
					Usuario usuario = new Usuario();
					usuario.setLogin(txtLogin.getText().toUpperCase());
					usuario.setPassword(txtPsw.getText());
					usuario.setNombres(txtNombre.getText().toUpperCase());
					usuario.setPaterno(txtPaterno.getText().toUpperCase());
					usuario.setMaterno(txtMaterno.getText().toUpperCase());
					usuario.setHoraIngreso(txtHoraIngreso.getText());
					usuario.setPerfil(new Perfil(((ComboItem) cboTipoUsuario.getSelectedItem()).getId()));
					usuario.setStatus("1");
					usuario.setFechaCreacion(String.valueOf(LocalDate.now()));
					usuario.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new UsuarioController().RegistrarUsuario(usuario);
					ListarUsuarios();
					Utiles.Limpiar(contenedor);
					Utiles.Mensaje("Usuario registrado.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		spUsuario = new JScrollPane();
		contenedor.add(spUsuario);
		spUsuario.setBounds(29, 176, 1244, 365);
		spUsuario.setBackground(new java.awt.Color(255, 255, 255));
		spUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		btnEditar = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnEditar);
		btnEditar.setBorderPainted(false);
		btnEditar.setToolTipText("ACTUALIZAR USUARIO");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEditar.setEnabled(false);
		btnEditar.setBounds(1109, 90, 64, 64);
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor)) {
					Usuario usuario = new Usuario();
					usuario.setId(Integer.parseInt(lblId.getText()));
					usuario.setLogin(txtLogin.getText().toUpperCase());
					usuario.setPassword(txtPsw.getText());
					usuario.setNombres(txtNombre.getText().toUpperCase());
					usuario.setPaterno(txtPaterno.getText().toUpperCase());
					usuario.setMaterno(txtMaterno.getText().toUpperCase());
					usuario.setHoraIngreso(txtHoraIngreso.getText());
					usuario.setPerfil(new Perfil(((ComboItem) cboTipoUsuario.getSelectedItem()).getId()));
					usuario.setStatus("1");
					usuario.setFechaCreacion(String.valueOf(LocalDate.now()));
					usuario.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new UsuarioController().ActualizarUsuario(usuario);
					ListarUsuarios();
					Utiles.Limpiar(contenedor);
					Utiles.Mensaje("Usuario actualizado.", JOptionPane.INFORMATION_MESSAGE);
					btnEditar.setEnabled(false);
					btnEliminar.setEnabled(false);
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/eliminar.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setToolTipText("REGISTRAR PRÉSTAMO");
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(1209, 90, 64, 64);
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor)) {
					Usuario usuario = new Usuario();
					usuario.setId(Integer.parseInt(lblId.getText()));
					usuario.setLogin(txtLogin.getText().toUpperCase());
					usuario.setPassword(txtPsw.getText());
					usuario.setNombres(txtNombre.getText().toUpperCase());
					usuario.setPaterno(txtPaterno.getText().toUpperCase());
					usuario.setMaterno(txtMaterno.getText().toUpperCase());
					usuario.setHoraIngreso(txtHoraIngreso.getText());
					usuario.setPerfil(new Perfil(((ComboItem) cboTipoUsuario.getSelectedItem()).getId()));
					usuario.setStatus("0");
					usuario.setFechaCreacion(String.valueOf(LocalDate.now()));
					usuario.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new UsuarioController().ActualizarUsuario(usuario);
					ListarUsuarios();
					Utiles.Limpiar(contenedor);
					Utiles.Mensaje("Usuario eliminado.", JOptionPane.INFORMATION_MESSAGE);
					btnEditar.setEnabled(false);
					btnEliminar.setEnabled(false);
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		cboTipoUsuario = new JComboBox();
		contenedor.add(cboTipoUsuario);
		cboTipoUsuario.setBounds(1009, 12, 264, 66);
		cboTipoUsuario.setModel(new UsuarioController().CargarPerfiles());
		cboTipoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboTipoUsuario.setOpaque(false);
		cboTipoUsuario.setBorder(BorderFactory.createTitledBorder(null, "PERFIL", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtHoraIngreso = new JTextField();
		contenedor.add(txtHoraIngreso);
		txtHoraIngreso.setBounds(681, 15, 316, 66);
		txtHoraIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtHoraIngreso.setOpaque(false);
		txtHoraIngreso.setBorder(BorderFactory.createTitledBorder(null, "HORA INGRESO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		lblId = new JTextField();
		contenedor.add(lblId);
		lblId.setBounds(19, 443, 10, 10);
		lblId.setVisible(false);

		tbUsuarios = new JXTable();
		spUsuario.setViewportView(tbUsuarios);
		tbUsuarios.setRowHeight(35);
		tbUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbUsuarios.getTableHeader().setForeground(new Color(181, 0, 0));
		tbUsuarios.setDefaultRenderer(Object.class, new RenderMC());
		tbUsuarios.setModel(Constantes.UsuarioModel);
		tbUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbUsuarios.getSelectedRow();
					lblId.setText(String.valueOf(tbUsuarios.getValueAt(fila, 0)));
					txtLogin.setText(tbUsuarios.getValueAt(fila, 1).toString());
					txtPaterno.setText(tbUsuarios.getValueAt(fila, 2).toString());
					txtMaterno.setText(tbUsuarios.getValueAt(fila, 3).toString());
					txtNombre.setText(tbUsuarios.getValueAt(fila, 4).toString());
					txtPsw.setText(tbUsuarios.getValueAt(fila, 5).toString());
					cboTipoUsuario.setSelectedItem(tbUsuarios.getValueAt(fila, 6).toString());
					txtHoraIngreso.setText(tbUsuarios.getValueAt(fila, 7).toString());
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
					txtLogin.setEnabled(false);
				}
			}
		});

		ListarUsuarios();
	}

	public void ListarUsuarios() {
		Constantes.UsuarioModel.setRowCount(0);
		tbUsuarios.setModel(new UsuarioController().ListarUsuarios(Constantes.UsuarioModel));
	}

}
