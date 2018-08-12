package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Beans.Asistencia;
import Beans.Hilo;
import Conexion.MySQLConexion;

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
public class Control_Asistencia extends JInternalFrame {
	private JLabel lblHoy;
	private JTextField txtDni;
	private JButton btnGrabar;
	private JLabel jLabel2;
	private JLabel lblHora;
	private JButton btnSalir;
	private JLabel jLabel1;
	private JPanel contenedor;
	
	public Control_Asistencia() {

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(644, 330);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(629, 316));
		this.setBounds(0, 0, 629, 316);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 628, 292);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		lblHoy = new JLabel();
		contenedor.add(lblHoy);
		lblHoy.setBounds(12, 18, 582, 55);
		lblHoy.setFont(new java.awt.Font("Gisha", 1, 36));
		lblHoy.setForeground(new java.awt.Color(128, 0, 0));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("NÚMERO DE D.N.I.");
		jLabel1.setBounds(24, 154, 372, 37);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		txtDni = new JTextField();
		contenedor.add(txtDni);
		txtDni.setBounds(260, 154, 329, 37);		
		txtDni.setText(Principal.user[2]);
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(313, 203, 276, 63);
		btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 26));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});

		lblHora = new JLabel();
		contenedor.add(lblHora);
		lblHora.setBounds(327, 90, 265, 52);
		lblHora.setFont(new java.awt.Font("Gisha", 1, 36));
		lblHora.setForeground(new java.awt.Color(128, 0, 0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("SU HORA DE SALIDA ES: ");
		jLabel2.setBounds(21, 106, 313, 24);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		btnGrabar = new JButton();
		contenedor.add(btnGrabar);
		btnGrabar.setText("GRABAR");
		btnGrabar.setBounds(21, 203, 276, 63);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 26));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validarUsuario(txtDni.getText());
			}
		});

		hoy();
		hora();
	}

	public void hoy() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, dd MMMM yyyy");
		lblHoy.setText(sdf.format(d).toUpperCase());
	}

	public void hora() {
		Hilo h = new Hilo(lblHora);
		h.start();
	}

	public static void main(String[] args) {
		new Control_Asistencia();
	}

	public void validarUsuario(String dni) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_usuario WHERE dni_usu=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int opcion = JOptionPane.showConfirmDialog(null,
						"¿Eres " + rs.getString(2) + " " + rs.getString(3)
								+ " " + rs.getString(4) + "?",
						"Autenticación de Usuario", JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					determinarSalida(rs.getString(1));
					internalClose();
				} else {
					JOptionPane.showMessageDialog(null, "Operación Cancelada.");
					txtDni.setText("");
					txtDni.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Número de documento incorrecto, no existe o no apto.");
				txtDni.setText("");
				txtDni.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void determinarSalida(String dni) {
		Connection con = MySQLConexion.getConexion();
		try {
			String hoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String sql = "SELECT * FROM tb_asistencia a INNER JOIN tb_usuario u ON a.tb_usuario_dni_usu = u.dni_usu WHERE tb_usuario_dni_usu=? AND fec_asi=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setString(2, hoy);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Asistencia asistencia = new Asistencia(rs.getString(2), hoy,
						new SimpleDateFormat("HH:mm:ss").format(rs
								.getDate("ent_usu")), new SimpleDateFormat(
								"HH:mm:ss").format(new Date().getTime()),
						new SimpleDateFormat("HH:mm:ss").format(new Date()),
						"", "", null);
				asistencia.registrarSalida();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void internalClose() {
		this.dispose();
	}

	public String[] calcularMinutosRetraso(String entrada, String ingreso) {
		Connection con = MySQLConexion.getConexion();
		String[] retraso = null;
		try {
			String sql = "select case when timestampdiff(second,time(?),time(?)) > 0 then sec_to_time(timestampdiff(second,time(?),time(?))) else time('00:00:00') end as 'retraso'";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, entrada);
			pst.setString(2, ingreso);
			pst.setString(3, entrada);
			pst.setString(4, ingreso);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String condicion = (!rs.getString("retraso").equalsIgnoreCase(
						"00:00:00")) ? "TARDANZA" : "ASISTIO";
				retraso = new String[] { condicion, rs.getString("retraso") };
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retraso;
	}

	public String obtenerHoraEntrada(String dni) {
		Connection con = MySQLConexion.getConexion();
		String entrada = "";
		try {
			String sql = "SELECT ent_usu FROM tb_usuario WHERE dni_usu=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				entrada = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entrada;
	}
}
