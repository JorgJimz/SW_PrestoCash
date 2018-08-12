package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.record.CalcCountRecord;

import Beans.Pago_Contrato;
import Conexion.MySQLConexion;

import com.toedter.calendar.JDateChooser;

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
public class Registrar_Pago_Manual extends JInternalFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JCheckBox chkDias;
	private JTable tbPagos;
	private JScrollPane jScrollPane1;
	private JTextField txtMora;
	private JButton btnGrabar;
	private JTextField txtInteres;
	private JDateChooser dchFechaPago;
	private JDateChooser dchFechaVencimiento;
	private JLabel lblContrato;
	private JLabel jLabel6;
	private int numero_contrato;
	private JPanel contenedor;
	int c = 0;
	ArrayList<Pago_Contrato> pagos = new ArrayList<Pago_Contrato>();
	DefaultTableModel modeloPagos = new DefaultTableModel(null, new String[] {
			"FEC.VENC.", "FEC.PAGO", "INTERES", "MORA", "TOTAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Registrar_Pago_Manual(int contrato) {
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setSize(929, 398);
		this.setPreferredSize(new java.awt.Dimension(984, 422));
		this.setBounds(0, 0, 984, 422);
		numero_contrato = contrato;

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 982, 397);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("CONTRATO");
			jLabel1.setBounds(21, 12, 142, 32);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("FECHA PAGO");
			jLabel2.setBounds(22, 120, 142, 32);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("FECHA VCTO.");
			jLabel3.setBounds(21, 63, 256, 32);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("INTERES");
			jLabel5.setBounds(22, 178, 142, 32);
			jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel5.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setText("MORA");
			jLabel6.setBounds(22, 230, 142, 32);
			jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel6.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblContrato = new JLabel();
			contenedor.add(lblContrato);
			lblContrato.setBounds(182, 17, 184, 32);
			lblContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblContrato.setBackground(new java.awt.Color(255, 255, 255));
			lblContrato.setOpaque(true);
			lblContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblContrato.setForeground(new java.awt.Color(0, 64, 128));
			lblContrato.setText(numero_contrato + "");
		}
		{
			dchFechaVencimiento = new JDateChooser();
			contenedor.add(dchFechaVencimiento);
			dchFechaVencimiento.setBounds(183, 68, 184, 32);
			dchFechaVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			dchFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 22));
			dchFechaVencimiento.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			dchFechaPago = new JDateChooser();
			contenedor.add(dchFechaPago);
			dchFechaPago.setBounds(182, 120, 184, 32);
			dchFechaPago.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			dchFechaPago.setFont(new java.awt.Font("Segoe UI", 1, 22));
			dchFechaPago.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			txtInteres = new JTextField();
			contenedor.add(txtInteres);
			txtInteres.setBounds(182, 178, 184, 32);
			txtInteres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtInteres.setFont(new java.awt.Font("Segoe UI", 1, 22));
			txtInteres.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			txtMora = new JTextField();
			contenedor.add(txtMora);
			txtMora.setBounds(182, 230, 184, 32);
			txtMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			txtMora.setFont(new java.awt.Font("Segoe UI", 1, 22));
			txtMora.setForeground(new java.awt.Color(0, 64, 128));
			txtMora.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						agregarPago();
						listarPagos();
						btnGrabar.setEnabled(true);
						limpiarCajas();
					}
				}
			});
		}
		{
			btnGrabar = new JButton();
			contenedor.add(btnGrabar);
			btnGrabar.setText("PROCESAR");
			btnGrabar.setEnabled(false);
			btnGrabar.setBounds(24, 330, 342, 47);
			btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 24));
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(chkDias.isSelected()){
						impactarFechas();
					}
					grabarPagos();
					limpiarCajas();

				}
			});
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(398, 13, 554, 364);
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			tbPagos = new JTable();
			jScrollPane1.setViewportView(tbPagos);
			tbPagos.setModel(modeloPagos);
			tbPagos.setRowHeight(30);
			tbPagos.setFont(new Font("Segoe UI", Font.BOLD, 18));
			tbPagos.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbPagos.getTableHeader().setForeground(new Color(181, 0, 0));

		}

		chkDias = new JCheckBox();
		contenedor.add(chkDias);
		chkDias.setText("CON IMPACTO EN FECHA");
		chkDias.setBounds(22, 281, 344, 32);
		chkDias.setFont(new java.awt.Font("Segoe UI", 1, 22));
		chkDias.setOpaque(false);
		chkDias.setForeground(new java.awt.Color(0, 128, 0));

	}

	public void agregarPago() {
		Pago_Contrato pago = new Pago_Contrato(Integer.parseInt(lblContrato
				.getText()),
				new SimpleDateFormat("yyyy-MM-dd").format(dchFechaVencimiento
						.getDate()),
				new SimpleDateFormat("yyyy-MM-dd").format(dchFechaPago
						.getDate()), 0.00, Double.parseDouble(txtInteres
						.getText()), Double.parseDouble(txtMora.getText()));
		pagos.add(pago);
		c++;
	}

	public void impactarFechas() {
		try {
			Connection con = MySQLConexion.getConexion();
			String[] a = arrayTablas(numero_contrato);
			String sql = "SELECT * FROM " + a[0] + " WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero_contrato);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Date dv = rs.getDate("fec_ven_con");
				Date dr = rs.getDate("fec_rem_con");
				Calendar cv = Calendar.getInstance();
				Calendar cr = Calendar.getInstance();
				cv.setTime(dv);
				cr.setTime(dr);
				cv.add(Calendar.MONTH, c);
				cr.add(Calendar.MONTH, c);
				String sql1 = "UPDATE " + a[0]
						+ " SET fec_ven_con=?, fec_rem_con=? WHERE id_con=?";
				PreparedStatement pst1 = con.prepareStatement(sql1);
				pst1.setString(1,
						new SimpleDateFormat("yyyy-MM-dd").format(cv.getTime()));
				pst1.setString(2,
						new SimpleDateFormat("yyyy-MM-dd").format(cr.getTime()));
				pst1.setInt(3, numero_contrato);
				pst1.executeUpdate();
				JOptionPane.showConfirmDialog(null, "Contrato Actualizado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] arrayTablas(int id_contrato) {
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayTablas;
	}

	public void listarPagos() {
		modeloPagos.setRowCount(0);
		for (Pago_Contrato pc : pagos) {
			try {
				Date vcto = new SimpleDateFormat("yyyy-MM-dd").parse(pc
						.getVencimiento_contrato());
				Date pago = new SimpleDateFormat("yyyy-MM-dd").parse(pc
						.getFecha_pago());
				modeloPagos.addRow(new Object[] {
						new SimpleDateFormat("dd-MMM-yyyy").format(vcto),
						new SimpleDateFormat("dd-MMM-yyyy").format(pago),
						pc.getMonto_interes(), pc.getMonto_mora(),
						pc.getMonto_interes() + pc.getMonto_mora() });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		tbPagos.setModel(modeloPagos);
	}

	public void grabarPagos() {
		for (Pago_Contrato c : pagos) {
			c.grabarPago();
		}
		clousureIntempestivo();
	}

	public void limpiarCajas() {
		txtInteres.setText("");
		txtMora.setText("");

	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void clousureIntempestivo() {
		this.dispose();
	}
}
