package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
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
import javax.swing.border.TitledBorder;

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
		txtUsuario.setBounds(24, 180, 304, 60);
		txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtUsuario.setForeground(new java.awt.Color(128, 0, 0));
		txtUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBorder(BorderFactory.createTitledBorder(null, "USUARIO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		order.add(txtUsuario);
		txtUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IniciarSesion();
			}
		});

		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword);
		txtPassword.setBounds(24, 245, 304, 60);
		txtPassword.setOpaque(false);
		txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPassword.setForeground(new java.awt.Color(128, 0, 0));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBorder(BorderFactory.createTitledBorder(null, "CONTRASEÑA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		order.add(txtPassword);
		txtPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IniciarSesion();
			}
		});

		btnIngreso = new JButton(new ImageIcon("img/singin.png"));
		getContentPane().add(btnIngreso);
		btnIngreso.setOpaque(false);
		btnIngreso.setBorderPainted(false);
		btnIngreso.setContentAreaFilled(false);
		btnIngreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIngreso.setBounds(340, 210, 64, 64);
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
		lblLogo.setBounds(15, 12, 380, 152);

		this.setSize(445, 350);
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
			UIManager.put("TitledBorder.border", new LineBorder(new Color(0, 128, 0), 2));
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
