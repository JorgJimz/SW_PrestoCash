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
@SuppressWarnings({ "deprecation", "serial" })
public class Login extends JFrame {

	private JTextField txtUsuario;
	private JButton btnIngreso;
	private JPasswordField txtPassword;
	private JLabel lblLogo;
	private JLabel imgKey;
	private JLabel imgUser;

	public Login() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("SISTEMA DE GESTION ADMINISTRATIVA Y PRENDATARIA PRESTOCASH");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new java.awt.Color(255,213,170));
		this.setIconImage(new ImageIcon(getClass().getClassLoader()
				.getResource("dollar.png")).getImage());

		txtUsuario = new JTextField();
		getContentPane().add(txtUsuario);
		txtUsuario.requestFocus();
		txtUsuario.setBounds(84, 290, 363, 48);
		txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtUsuario.setForeground(new java.awt.Color(128, 0, 0));
		txtUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
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

		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword);
		txtPassword.setBounds(84, 345, 363, 48);
		txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtPassword.setForeground(new java.awt.Color(128, 0, 0));
		txtPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
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

		btnIngreso = new JButton(/* new ImageIcon("img/singin.png") */);
		getContentPane().add(btnIngreso);
		btnIngreso.setText("INGRESAR");
		btnIngreso.setBounds(459, 290, 180, 103);
		btnIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
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

		lblLogo = new JLabel(new ImageIcon("img/logo.png"));
		getContentPane().add(lblLogo);
		lblLogo.setOpaque(true);
		lblLogo.setBounds(24, 12, 615, 261);

		imgUser = new JLabel(new ImageIcon("img/user.png"));
		getContentPane().add(imgUser);
		imgUser.setOpaque(true);
		imgUser.setBounds(24, 290, 48, 48);

		imgKey = new JLabel(new ImageIcon("img/key.png"));
		getContentPane().add(imgKey);
		imgKey.setOpaque(true);
		imgKey.setBounds(24, 344, 48, 48);

		this.setSize(680, 444);
		this.setLocationRelativeTo(null);

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
