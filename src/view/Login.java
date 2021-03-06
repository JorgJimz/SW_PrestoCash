package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import common.Constantes;
import common.MyFocusTraversalPolicy;
import common.Utiles;
import controller.ContratoController;
import controller.UsuarioController;
import model.Asistencia;
import model.Contrato;
import model.Usuario;

@SuppressWarnings({ "deprecation", "serial" })
public class Login extends JFrame {

	private JTextField txtUsuario;
	private JButton btnIngreso;
	private JPasswordField txtPassword;
	private JLabel lblLogo;
	private JLabel jLabel1;
	private JProgressBar pbActualizacion;

	Thread hilo;
	Vector<Component> order = new Vector<Component>(3);
	Usuario u;
	boolean requiereActualizacion = new UsuarioController().RequiereActualizacion();

	public Login() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(415, 320);
		this.setTitle("SISTEMA DE GESTION ADMINISTRATIVA Y PRENDATARIA PRESTOCASH");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new java.awt.Color(255, 213, 170));
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("dollar.png")).getImage());

		btnIngreso = new JButton(new ImageIcon("img/singin.png"));
		getContentPane().add(btnIngreso);
		btnIngreso.setOpaque(false);
		btnIngreso.setBorderPainted(false);
		btnIngreso.setContentAreaFilled(false);
		btnIngreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIngreso.setBounds(325, 192, 70, 70);
		btnIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Logueo();
			}
		});

		txtUsuario = new JTextField();
		getContentPane().add(txtUsuario);
		txtUsuario.requestFocus();
		txtUsuario.setBounds(12, 176, 307, 45);
		txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtUsuario.setForeground(new java.awt.Color(128, 0, 0));
		txtUsuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBorder(BorderFactory.createTitledBorder(null, "USUARIO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Logueo();
			}
		});

		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword);
		txtPassword.setBounds(12, 227, 307, 45);
		txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPassword.setForeground(new java.awt.Color(128, 0, 0));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBorder(BorderFactory.createTitledBorder(null, "CONTRASE�A", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Logueo();
			}
		});

		lblLogo = new JLabel(new ImageIcon("img/logo.png"));
		getContentPane().add(lblLogo);
		lblLogo.setBounds(15, 12, 380, 152);

		pbActualizacion = new JProgressBar();
		getContentPane().add(pbActualizacion);
		pbActualizacion.setBounds(15, 278, 380, 20);
		pbActualizacion.setVisible(false);

		jLabel1 = new JLabel();
		getContentPane().add(jLabel1);
		jLabel1.setText("ACTUALIZANDO CONTRATOS, POR FAVOR, ESPERE UN MOMENTO ...");
		jLabel1.setBounds(12, 304, 385, 16);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jLabel1.setForeground(new java.awt.Color(255, 0, 0));
		jLabel1.setVisible(false);

		this.setLocationRelativeTo(null);

		order.add(txtUsuario);
		order.add(txtPassword);
		order.add(btnIngreso);

		this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(order));

	}

	public void Logueo() {
		u = IniciarSesion();
		if (Objects.nonNull(u)) {
			if (requiereActualizacion) {
				Runnable miRunnable = new Runnable() {
					public void run() {
						ActualizacionAutomatica();
					}
				};
				hilo = new Thread(miRunnable);
				hilo.start();
			} else {
				DesplegarSistema();
			}
		}
	}

	public Usuario IniciarSesion() {
		Usuario user = new UsuarioController().Login(new Usuario(txtUsuario.getText(), txtPassword.getText()));
		if (Objects.nonNull(user)) {
			Asistencia ga = user.getAsistencias().stream()
					.filter(Constantes.predicadoAsistencia(String.valueOf(LocalDate.now()))).findFirst()
					.orElse(Asistencia.DEFAULT);
			if (Objects.isNull(ga)) {
				Asistencia a = new Asistencia();
				a.setFecha(String.valueOf(LocalDate.now()));
				a.setHoraContrato(user.getHoraIngreso());
				a.setHoraIngreso(String.valueOf(LocalTime.now()));
				a.setObs("");
				a.setUsuario(user);
				Asistencia nuevaAsistencia = new UsuarioController().MarcarAsistencia(a);
				Utiles.Mensaje("Se grab� asistencia.", JOptionPane.INFORMATION_MESSAGE);
				user.getAsistencias().add(nuevaAsistencia);
			}
		} else {
			Utiles.Mensaje("Usuario y/ Contrase�a incorrecto(s)", JOptionPane.WARNING_MESSAGE);
		}
		return user;
	}

	public void DesplegarSistema() {
		pbActualizacion.setIndeterminate(false);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Principal(u);
			}
		});
		dispose();
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

	public void ActualizacionAutomatica() {
		this.setSize(415, 360);
		pbActualizacion.setVisible(true);
		jLabel1.setVisible(true);
		pbActualizacion.setIndeterminate(true);
		List<Contrato> lVigentes = new ContratoController().ListarContratosVigentes();
		for (Contrato c : lVigentes) {
			Utiles.DetectarEstado(c);
		}

		List<Contrato> lNoVigentes = new ContratoController().ListarContratosNoVigentes();
		for (Contrato c : lNoVigentes) {
			Utiles.ActualizacionContrato(c);
		}

		List<Contrato> l = Stream.concat(lVigentes.stream(), lNoVigentes.stream()).collect(Collectors.toList());

		new ContratoController().ActualizarContratos(l);
		DesplegarSistema();
	}

}
