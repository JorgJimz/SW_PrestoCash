package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

import controller.ContratoController;
import controller.UsuarioController;
import model.Abono;
import model.Asistencia;
import model.Contrato;
import model.Usuario;

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
		txtUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*Usuario u = new UsuarioController().Login(new Usuario(txtUsuario.getText(), txtPassword.getText()));
				if (u != null) {
					Asistencia a = new Asistencia();
					a.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					a.setHoraContrato(u.getHoraIngreso());
					a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setHoraSalida(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setObs("");
					a.setUsuario(u);
					new UsuarioController().MarcarAsistencia(u, a);
					new Principal(u);
					Cerrar();
				} else {
					JOptionPane.showMessageDialog(null,
							" Acceso Denegado. Comuníquese con el Administrador del Sistema.");
				}*/
			}
		});

		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword);
		txtPassword.setBounds(100, 379, 383, 64);
		txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtPassword.setForeground(new java.awt.Color(128, 0, 0));
		txtPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*Usuario u = new UsuarioController().Login(new Usuario(txtUsuario.getText(), txtPassword.getText()));
				if (u != null) {
					Asistencia a = new Asistencia();
					a.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					a.setHoraContrato(u.getHoraIngreso());
					a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setHoraSalida(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setObs("");
					a.setUsuario(u);
					new UsuarioController().MarcarAsistencia(u, a);
					new Principal(u);
					Cerrar();
				} else {
					JOptionPane.showMessageDialog(null,
							" Acceso Denegado. Comuníquese con el Administrador del Sistema.");
				}*/
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
		btnIngreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = new UsuarioController().Login(new Usuario(txtUsuario.getText(), txtPassword.getText()));
				Contrato x  = new ContratoController().CargarContrato("A", 8611);
				
				
				List<Abono> ao =  x.getAbonos();
				
				
				System.out.println(ao.size());
				new UsuarioController().MarcarAsistencia(u, null);
				
				if (u != null) {

					List<Asistencia> ax = u.getAsistencias();
					
					System.out.println(ax);
					
					Predicate<Asistencia> p = new Predicate<Asistencia>() {
						
						@Override
						public boolean test(Asistencia t) {
							return LocalDate.parse(t.getFecha()).compareTo(LocalDate.now()) == 0;
						}
					};
					Asistencia kk =  u.getAsistencias().stream().filter(p).findFirst().orElse(null);
					System.out.println(kk);

					Asistencia a = new Asistencia();
					a.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					a.setHoraContrato(u.getHoraIngreso());
					a.setHoraIngreso(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setHoraSalida(new SimpleDateFormat("HH:mm:ss").format(new Date()));
					a.setObs("");
					a.setUsuario(u);
					//new UsuarioController().MarcarAsistencia(/* u, */a);
					new Principal(u);
					Cerrar();
				} else {
					JOptionPane.showMessageDialog(null,
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
		imgUser.setOpaque(false);
		imgUser.setBounds(24, 299, 64, 64);

		imgKey = new JLabel(new ImageIcon("img/key.png"));
		getContentPane().add(imgKey);
		imgKey.setOpaque(false);
		imgKey.setBounds(24, 379, 64, 64);

		this.setSize(680, 510);
		this.setLocationRelativeTo(null);

	}

	public void Cerrar() {
		this.dispose();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
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
