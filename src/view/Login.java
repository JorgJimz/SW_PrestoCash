package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.Asistencia;
import model.Usuario;
import controller.UsuarioController;

@SuppressWarnings({ "deprecation", "serial" })
public class Login extends JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField txtUsuario;
	private JButton btnIngreso;
	private JPasswordField txtPassword;
	private JLabel lblLogo;
	private JLabel jLabel3;

	public Login() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("SISTEMA DE GESTION ADMINISTRATIVA Y PRENDATARIA PRESTOCASH");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new java.awt.Color(255, 184, 113));
		this.setIconImage(new ImageIcon(getClass().getClassLoader()
				.getResource("dollar.png")).getImage());
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("USUARIO:");
			jLabel1.setBounds(17, 68, 140, 39);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26));
			jLabel1.setBackground(new java.awt.Color(0, 128, 0));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("CLAVE:");
			jLabel2.setBounds(15, 115, 140, 39);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26));
			jLabel2.setBackground(new java.awt.Color(0, 128, 0));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			txtUsuario = new JTextField();
			getContentPane().add(txtUsuario);
			txtUsuario.requestFocus();
			txtUsuario.setBounds(154, 70, 364, 39);
			txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 22));
			txtUsuario.setForeground(new java.awt.Color(128, 0, 0));
			txtUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtUsuario.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Usuario u = new UsuarioController().Login(new Usuario(
							txtUsuario.getText(), txtPassword.getText()));
					if (u != null) {
						Asistencia a = new Asistencia();
						a.setFecha(new SimpleDateFormat("yyyy-MM-dd")
								.format(new Date()));
						a.setHoraContrato(u.getHoraIngreso());
						a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setHoraSalida(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setObs("");
						a.setUsuario(u);
						new UsuarioController().MarcarAsistencia(u, a);
						new Principal(u);
						Cerrar();
					} else {
						JOptionPane
								.showMessageDialog(null,
										" Acceso Denegado. Comuníquese con el Administrador del Sistema.");
					}
				}
			});
		}
		{
			txtPassword = new JPasswordField();
			getContentPane().add(txtPassword);
			txtPassword.setBounds(155, 117, 365, 39);
			txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 22));
			txtPassword.setForeground(new java.awt.Color(128, 0, 0));
			txtPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtPassword.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Usuario u = new UsuarioController().Login(new Usuario(
							txtUsuario.getText(), txtPassword.getText()));
					if (u != null) {
						Asistencia a = new Asistencia();
						a.setFecha(new SimpleDateFormat("yyyy-MM-dd")
								.format(new Date()));
						a.setHoraContrato(u.getHoraIngreso());
						a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setHoraSalida(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setObs("");
						a.setUsuario(u);
						new UsuarioController().MarcarAsistencia(u, a);
						new Principal(u);
						Cerrar();
					} else {
						JOptionPane
								.showMessageDialog(null,
										" Acceso Denegado. Comuníquese con el Administrador del Sistema.");
					}
				}
			});
		}
		{
			btnIngreso = new JButton(new ImageIcon("img/singin.png"));
			getContentPane().add(btnIngreso);
			btnIngreso.setText("   INGRESAR");
			btnIngreso.setBounds(15, 166, 503, 142);
			btnIngreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
			btnIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnIngreso.setBackground(new java.awt.Color(0, 64, 128));
			btnIngreso.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Usuario u = new UsuarioController().Login(new Usuario(
							txtUsuario.getText(), txtPassword.getText()));
					if (u != null) {
						Asistencia a = new Asistencia();
						a.setFecha(new SimpleDateFormat("yyyy-MM-dd")
								.format(new Date()));
						a.setHoraContrato(u.getHoraIngreso());
						a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setHoraSalida(new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						a.setObs("");
						a.setUsuario(u);
						new UsuarioController().MarcarAsistencia(u, a);
						new Principal(u);
						Cerrar();
					} else {
						JOptionPane
								.showMessageDialog(null,
										" Acceso Denegado. Comuníquese con el Administrador del Sistema.");
					}
				}
			});
		}
		{
			jLabel3 = new JLabel();
			jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(jLabel3);
			jLabel3.setText("::: ENTRAR AL SISTEMA :::");
			jLabel3.setBounds(12, 12, 500, 43);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28));
			jLabel3.setBackground(new java.awt.Color(0, 128, 0));
			jLabel3.setForeground(new java.awt.Color(128, 0, 0));
		}
		{
			lblLogo = new JLabel(new ImageIcon("img/logo.png"));
			getContentPane().add(lblLogo);
			lblLogo.setBounds(545, 12, 656, 266);
		}
		this.setSize(1229, 358);

	}

	public void Cerrar() {
		this.dispose();
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
			new Login();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	/*
	 * public String pedirJustificacion() { String j = JOptionPane
	 * .showInputDialog("Usted ha llegado TARDE, ingrese el motivo ..."); if
	 * (j.equalsIgnoreCase("")) { return j = "NO ESPECIFICADO"; } return
	 * j.toUpperCase(); }
	 */

}
