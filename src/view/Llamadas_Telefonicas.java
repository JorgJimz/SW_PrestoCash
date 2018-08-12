package view;

import groovyjarjarasm.asm.tree.JumpInsnNode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.plaf.basic.CapsLockSupport;

import Beans.Llamada_Telefonica;
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
public class Llamadas_Telefonicas extends JInternalFrame {

	private JTextArea txtIngObs;
	private JButton btnDetalleContrato;
	private JScrollPane jScrollPane4;
	private JButton btnSalir;
	private JButton btnGrabarLlamada;
	private JLabel lblNumeroMarcado;
	private JLabel jLabel5;
	private JComboBox cboResultado;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JScrollPane jScrollPane2;
	private JTable tbHistorialLLamadas;
	private JRadioButton rdMovil2;
	private JRadioButton rdMovil1;
	private JRadioButton rdFijo2;
	private JRadioButton rdFijo1;
	private JLabel lblNomCli;
	private JLabel lblDocCli;
	SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatoLocal = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat formatoDateTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private JTextArea txtObs;
	private JScrollPane jScrollPane3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel contenedor;
	private JScrollPane jScrollPane1;
	private JTable tbContratos;
	private JXTitledSeparator jSeparator2;
	private JXTitledSeparator jSeparator3;
	private ButtonGroup grupo;
	private int numero_contrato;
	DefaultTableModel modeloContrato = new DefaultTableModel(null,
			new String[] { "NRO. CONTRATO", "FECHA REMATE" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloHistorialLlamadas = new DefaultTableModel(
			null,
			new String[] { "FECHA Y HORA", "NRO. MARCADO", "RESULTADO", "OBS." }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Llamadas_Telefonicas() throws SQLException {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1361, 709);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1345, 671);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 62, 410, 597);
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		tbContratos = new JTable();
		tbContratos.setRowHeight(30);
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbContratos.setModel(modeloContrato);
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						int fila = tbContratos.getSelectedRow();
						int codigo = Integer.parseInt(tbContratos.getValueAt(
								fila, 0).toString());
						System.out.println(codigo);
						numero_contrato = codigo;
						cargarDetalle(numero_contrato);
						cargarHistorialLLamadas(numero_contrato);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		jSeparator2 = new JXTitledSeparator(
				"REPORTE DE CONTRATOS CON RIESGO DE REMATE");
		contenedor.add(jSeparator2);
		jSeparator2.setBounds(12, 12, 1321, 32);
		jSeparator2.setFont(new java.awt.Font("Arial Black", 1, 24));
		jSeparator2.setForeground(new java.awt.Color(181, 0, 0));

		jSeparator3 = new JXTitledSeparator("REGISTRAR NUEVA LLAMADA");
		contenedor.add(jSeparator3);
		jSeparator3.setBounds(446, 328, 887, 32);
		jSeparator3.setFont(new java.awt.Font("Arial Black", 1, 24));
		jSeparator3.setForeground(new java.awt.Color(181, 0, 0));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CLIENTE:");
			jLabel1.setBounds(803, 62, 102, 30);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("D.IDENTIDAD:");
			jLabel2.setBounds(446, 62, 160, 30);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblDocCli = new JLabel();
			contenedor.add(lblDocCli);
			lblDocCli.setBounds(606, 62, 177, 30);
			lblDocCli.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblDocCli.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblDocCli.setBackground(new java.awt.Color(255, 255, 255));
			lblDocCli.setOpaque(true);
		}
		{
			lblNomCli = new JLabel();
			contenedor.add(lblNomCli);
			lblNomCli.setBounds(911, 62, 422, 30);
			lblNomCli.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblNomCli.setFont(new java.awt.Font("Segoe UI", 1, 20));
			lblNomCli.setBackground(new java.awt.Color(255, 255, 255));
			lblNomCli.setOpaque(true);
		}
		{
			rdFijo1 = new JRadioButton();
			contenedor.add(rdFijo1);
			rdFijo1.setBounds(446, 110, 174, 30);
			rdFijo1.setBackground(new java.awt.Color(255, 200, 147));
			rdFijo1.setFont(new java.awt.Font("Segoe UI", 1, 19));
		}
		{
			rdFijo2 = new JRadioButton();
			contenedor.add(rdFijo2);
			rdFijo2.setBounds(911, 109, 174, 32);
			rdFijo2.setBackground(new java.awt.Color(255, 200, 147));
			rdFijo2.setFont(new java.awt.Font("Segoe UI", 1, 19));
		}
		{
			rdMovil1 = new JRadioButton();
			contenedor.add(rdMovil1);
			rdMovil1.setBounds(651, 109, 217, 32);
			rdMovil1.setBackground(new java.awt.Color(255, 200, 147));
			rdMovil1.setFont(new java.awt.Font("Segoe UI", 1, 19));
		}
		{
			rdMovil2 = new JRadioButton();
			contenedor.add(rdMovil2);
			rdMovil2.setBounds(1116, 109, 217, 32);
			rdMovil2.setBackground(new java.awt.Color(255, 200, 147));
			rdMovil2.setFont(new java.awt.Font("Segoe UI", 1, 19));
			grupo = new ButtonGroup();
			grupo.add(rdFijo1);
			rdFijo1.setForeground(new java.awt.Color(0, 128, 0));
			grupo.add(rdFijo2);
			rdFijo2.setForeground(new java.awt.Color(0, 128, 0));
			grupo.add(rdMovil1);
			rdMovil1.setForeground(new java.awt.Color(0, 128, 0));
			grupo.add(rdMovil2);
			rdMovil2.setForeground(new java.awt.Color(0, 128, 0));
			rdFijo1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					capturarNumeroMarcado();
				}
			});
			rdFijo2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					capturarNumeroMarcado();
				}
			});
			rdMovil1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					capturarNumeroMarcado();
				}
			});
			rdMovil2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					capturarNumeroMarcado();
				}
			});
		}
		{
			jScrollPane2 = new JScrollPane();
			contenedor.add(jScrollPane2);
			jScrollPane2.setBounds(446, 161, 598, 146);
			jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
			jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));

			tbHistorialLLamadas = new JTable();
			jScrollPane2.setViewportView(tbHistorialLLamadas);
			tbHistorialLLamadas.setModel(modeloHistorialLlamadas);
			tbHistorialLLamadas.setRowHeight(30);
			tbHistorialLLamadas.setFont(new Font("Segoe UI", Font.BOLD, 20));
			tbHistorialLLamadas.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbHistorialLLamadas.getTableHeader().setForeground(
					new Color(181, 0, 0));
			tbHistorialLLamadas.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					cargarObservacionLlamada();
				}
			});
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("RESULTADO:");
			jLabel3.setBounds(447, 435, 149, 30);
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("OBSERVACIONES DE LA LLAMADA:");
			jLabel4.setBounds(447, 493, 421, 30);
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22));
		}
		{
			ComboBoxModel cboResultadoModel = new DefaultComboBoxModel(
					new String[] { "EFECTIVA", "NO EFECTIVA" });
			cboResultado = new JComboBox();
			contenedor.add(cboResultado);
			cboResultado.setModel(cboResultadoModel);
			cboResultado.setBounds(682, 435, 186, 42);
			cboResultado.setFont(new java.awt.Font("Segoe UI", 1, 20));
			cboResultado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			cboResultado.setBackground(new java.awt.Color(255, 255, 255));
		}
		{
			jScrollPane3 = new JScrollPane();
			contenedor.add(jScrollPane3);
			jScrollPane3.setBounds(1050, 161, 283, 146);

			txtObs = new JTextArea();
			txtObs.setWrapStyleWord(true);
			txtObs.setLineWrap(true);
			jScrollPane3.setViewportView(txtObs);
			txtObs.setEnabled(false);
			txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtObs.setFont(new java.awt.Font("Segoe UI", 1, 20));

		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("NÚMERO MARCADO:");
			jLabel5.setBounds(447, 381, 236, 30);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblNumeroMarcado = new JLabel();
			contenedor.add(lblNumeroMarcado);
			lblNumeroMarcado.setBounds(683, 381, 185, 30);
			lblNumeroMarcado.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			lblNumeroMarcado.setBackground(new java.awt.Color(255, 255, 255));
			lblNumeroMarcado.setOpaque(true);
			lblNumeroMarcado.setFont(new java.awt.Font("Segoe UI", 1, 22));
		}
		{
			btnGrabarLlamada = new JButton();
			contenedor.add(btnGrabarLlamada);
			btnGrabarLlamada.setText("GRABAR LLAMADA");
			btnGrabarLlamada.setBounds(890, 467, 444, 88);
			btnGrabarLlamada.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnGrabarLlamada.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			btnGrabarLlamada.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					grabarLLamada();
				}
			});
		}
		{
			btnSalir = new JButton();
			contenedor.add(btnSalir);
			btnSalir.setText("SALIR");
			btnSalir.setBounds(890, 570, 444, 88);
			btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cerrar();
				}
			});
		}
		{
			jScrollPane4 = new JScrollPane();
			contenedor.add(jScrollPane4);
			jScrollPane4.setBounds(447, 537, 421, 122);
			jScrollPane4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			{
				txtIngObs = new JTextArea();
				jScrollPane4.setViewportView(txtIngObs);
			}
		}
		{
			btnDetalleContrato = new JButton();
			contenedor.add(btnDetalleContrato);
			btnDetalleContrato.setText("DETALLE CONTRATO");
			btnDetalleContrato.setBounds(890, 368, 444, 88);
			btnDetalleContrato.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			btnDetalleContrato.setFont(new java.awt.Font("Segoe UI", 1, 26));
			btnDetalleContrato.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Consultar_Contrato consulta_contrato = new Consultar_Contrato(
								numero_contrato + "");
						Principal.dskPrincipal.add(consulta_contrato);
						Principal.traerAlFrente(consulta_contrato);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}

		cargarContratos();
	}

	public void cargarContratos() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			Date hoy = new Date();
			hoy = formatoSQL.parse(formatoSQL.format(hoy));
			String sql = "SELECT * FROM tb_contrato WHERE tb_estado_contrato_id_est=2 OR tb_estado_contrato_id_est=4 UNION ALL SELECT * FROM tb_contrato_oro WHERE tb_estado_contrato_id_est=2 OR tb_estado_contrato_id_est=4 UNION ALL SELECT * FROM tb_contrato_manual WHERE tb_estado_contrato_id_est=2 OR tb_estado_contrato_id_est=4";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Date fecha_remate = formatoSQL.parse(rs
						.getString("fec_rem_con"));
				/* if(hoy.after(fecha_remate)){ */
				modeloContrato.addRow(new Object[] { rs.getInt("id_con"),
						formatoLocal.format(fecha_remate) });
				/* } */
			}
			tbContratos.setModel(modeloContrato);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cargarDetalle(int contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		String a[] = arrayTablas(contrato);
		try {
			String sql = "SELECT * FROM "
					+ a[0]
					+ " c INNER JOIN tb_cliente cl ON c.tb_cliente_doc_cli = cl.doc_cli WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				lblDocCli.setText(rs.getString("doc_cli"));
				lblNomCli.setText(rs.getString("nom_cli") + " "
						+ rs.getString("pat_cli") + " "
						+ rs.getString("mat_cli"));
				rdFijo1.setText("FIJO 1: " + rs.getString("fi1_cli"));
				rdFijo1.setActionCommand(rs.getString("fi1_cli"));
				rdFijo2.setText("FIJO 2: " + rs.getString("fi2_cli"));
				rdFijo2.setActionCommand(rs.getString("fi2_cli"));
				rdMovil1.setText("MÓVIL 1: " + rs.getString("mo1_cli"));
				rdMovil1.setActionCommand(rs.getString("mo1_cli"));
				rdMovil2.setText("MÓVIL 2: " + rs.getString("mo2_cli"));
				rdMovil2.setActionCommand(rs.getString("mo2_cli"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void capturarNumeroMarcado() {
		lblNumeroMarcado.setText(grupo.getSelection().getActionCommand());
	}

	public void grabarLLamada() {
		Date d = new Date();
		Llamada_Telefonica llamada = new Llamada_Telefonica(numero_contrato,
				formatoDateTime.format(d), cboResultado.getSelectedItem()
						.toString(), txtIngObs.getText().toUpperCase(),
				lblNumeroMarcado.getText());
		llamada.grabarLLamada();
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void cargarHistorialLLamadas(int contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_llamada_telefonica WHERE tb_contrato_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				modeloHistorialLlamadas.addRow(new Object[] {
						rs.getString("fec_llam"), rs.getString("num_mar_llam"),
						rs.getString("res_llam"), rs.getString("obs_llam") });
			}
			tbHistorialLLamadas.setModel(modeloHistorialLlamadas);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cargarObservacionLlamada() {
		int fila = tbHistorialLLamadas.getSelectedRow();
		String observacion = tbHistorialLLamadas.getValueAt(fila, 3).toString();
		txtObs.setText(observacion);
	}

	public String[] arrayTablas(int id_contrato) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		String[] arrayTablas = null;
		try {
			String sql = "SELECT * FROM tb_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_contrato);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return arrayTablas = new String[] { "tb_contrato",
						"tb_detalle_contrato" };
			} else {
				String sql1 = "SELECT * FROM tb_contrato_manual WHERE id_con=?";
				PreparedStatement pst1 = con.prepareStatement(sql1);
				pst1.setInt(1, id_contrato);
				ResultSet rs1 = pst1.executeQuery();
				if (rs1.next()) {
					return arrayTablas = new String[] { "tb_contrato_manual",
							"tb_detalle_contrato_manual" };
				} else {
					String sql2 = "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_contrato);
					ResultSet rs2 = pst2.executeQuery();
					if (rs2.next()) {
						return arrayTablas = new String[] { "tb_contrato_oro",
								"tb_detalle_contrato_oro" };
					} else {
						JOptionPane.showMessageDialog(null,
								"Ese contrato NO existe.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return arrayTablas;
	}
}
