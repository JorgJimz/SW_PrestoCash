package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Vector;

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
import javax.swing.border.LineBorder;

import common.Constantes;
import common.MyFocusTraversalPolicy;
import common.Utiles;
import controller.UsuarioController;
import model.Asistencia;
import model.Usuario;

@SuppressWarnings({ "deprecation", "serial" })
public class Login extends JFrame {

	private JTextField txtUsuario;
	private JButton btnIngreso;
	private JPasswordField txtPassword;
	private JLabel lblLogo;
	private JLabel imgKey;
	private JLabel imgUser;
	Vector<Component> order = new Vector<Component>(3);

	public Login() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("SISTEMA DE GESTION ADMINISTRATIVA Y PRENDATARIA PRESTOCASH");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new java.awt.Color(255, 213, 170));

		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("dollar.png")).getImage());

		txtUsuario = new JTextField();
		getContentPane().add(txtUsuario);
		txtUsuario.requestFocus();
		txtUsuario.setBounds(100, 299, 383, 64);
		txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtUsuario.setForeground(new java.awt.Color(128, 0, 0));
		txtUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		order.add(txtUsuario);
		txtUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IniciarSesion();
			}
		});

		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword);
		txtPassword.setBounds(100, 379, 383, 64);
		txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtPassword.setForeground(new java.awt.Color(128, 0, 0));
		txtPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		order.add(txtPassword);
		txtPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IniciarSesion();
			}
		});

		btnIngreso = new JButton(new ImageIcon("img/signin.png"));
		getContentPane().add(btnIngreso);
		btnIngreso.setOpaque(false);
		btnIngreso.setBorderPainted(false);
		btnIngreso.setContentAreaFilled(false);
		btnIngreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIngreso.setBounds(495, 299, 144, 144);
		btnIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		order.add(btnIngreso);
		btnIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IniciarSesion();
			}
		});

		lblLogo = new JLabel(new ImageIcon("img/logo.png"));
		getContentPane().add(lblLogo);
		lblLogo.setBounds(24, 12, 615, 261);

		imgUser = new JLabel(new ImageIcon("img/user.png"));
		getContentPane().add(imgUser);
		imgUser.setOpaque(false);
		imgUser.setBounds(24, 299, 64, 64);

		imgKey = new JLabel(new ImageIcon("img/key.png"));
		getContentPane().add(imgKey);
		imgKey.setOpaque(false);
		imgKey.setBounds(24, 379, 64, 64);

		this.setSize(680, 510);
		this.setLocationRelativeTo(null);

		this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(order));

	}

	public void Cerrar() {
		this.dispose();
	}

	public void IniciarSesion() {
		Usuario u = new UsuarioController().Login(new Usuario(txtUsuario.getText(), txtPassword.getText()));
		if (Objects.nonNull(u)) {
			Asistencia ga = u.getAsistencias().stream().filter(Constantes.predicadoAsistencia).findFirst()
					.orElse(Asistencia.DEFAULT);
			if (Objects.isNull(ga)) {
				Asistencia a = new Asistencia();
				a.setFecha(String.valueOf(LocalDate.now()));
				a.setHoraContrato(u.getHoraIngreso());
				a.setHoraIngreso(String.valueOf(LocalTime.now()));
				a.setObs("");
				a.setUsuario(u);
				Utiles.Mensaje(new UsuarioController().MarcarAsistencia(a), JOptionPane.INFORMATION_MESSAGE);
			}
			new Principal(u);
			Cerrar();
		} else {
			JOptionPane.showMessageDialog(null, " Acceso Denegado. Comuníquese con el Administrador del Sistema.");
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
			UIManager.put("TitledBorder.border", new LineBorder(new Color(255, 255, 255), 3));
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

}
