package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTitledSeparator;

import Utils.Constantes;
import Utils.Redondeo;
import Beans.Asistencia;
import Beans.Auditoria;
import Beans.Contrato;
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
public class Libro_Caja extends JInternalFrame {
	private JPanel contenedor;
	private JLabel lblAmanece;
	private JButton btnRefresh;
	private JButton btnSalir;
	private JLabel lblCerrarAmanece;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel lblTotalEgresos;
	private JLabel lbltotalIngresos;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JScrollPane jScrollPane2;
	private JLabel jLabel2;
	private JLabel lblTotalEgreso;
	private JLabel lblTotalEmpenios;
	private JLabel lblTotalNeto;
	private JLabel lblTotalGanacia;
	private JTable tbEgresos;
	private JTable tbIngresos;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;
	private JLabel lblFecha;
	private JXTitledSeparator jSeparator1;
	private JButton btnCerrarCaja;
	private JLabel jLabel4;
	private JLabel jLabel3;
	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	DecimalFormat df;
	DefaultTableModel modeloIngreso = new DefaultTableModel(null, new String[] {
			"DESCRIPCIÓN", "TIPO", "CAPITAL", "GANANCIA", "OTROS", "NETO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloEgreso = new DefaultTableModel(null, new String[] {
			"DESCRIPCIÓN", "TIPO", "MONTO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Libro_Caja() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1296, 713);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1297, 849));
		this.setBounds(0, 0, 1297, 849);
		simbolo.setDecimalSeparator('.');
		df = new DecimalFormat("######.00", simbolo);
		{
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1295, 825);
			contenedor.setBackground(new java.awt.Color(255, 200, 147));
			{
				lblFecha = new JLabel();
				contenedor.add(lblFecha);
				lblFecha.setText(new SimpleDateFormat("EEEEE, dd MMMM yyyy")
						.format(new Date()).toUpperCase());
				lblFecha.setBounds(0, 0, 1295, 66);
				lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 36));
				lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				lblFecha.setBackground(new java.awt.Color(0, 128, 128));
				lblFecha.setForeground(new java.awt.Color(255, 255, 255));
				lblFecha.setOpaque(true);
				lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel1 = new JLabel();
				contenedor.add(jLabel1);
				jLabel1.setText("AMANECE (S/.)");
				jLabel1.setBounds(355, 78, 186, 38);
				jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel1.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				lblAmanece = new JLabel();
				contenedor.add(lblAmanece);
				lblAmanece.setBounds(535, 78, 124, 38);
				lblAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				lblAmanece.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblAmanece.setOpaque(true);
				lblAmanece.setBackground(new java.awt.Color(0, 128, 128));
				lblAmanece.setForeground(new java.awt.Color(255, 255, 255));
				lblAmanece.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jScrollPane1 = new JScrollPane();
				contenedor.add(jScrollPane1);
				jScrollPane1.setBounds(12, 128, 710, 404);
				jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

				tbIngresos = new JTable();
				jScrollPane1.setViewportView(tbIngresos);
				tbIngresos.setModel(modeloIngreso);
				tbIngresos.setRowHeight(40);
				tbIngresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
				tbIngresos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbIngresos.getTableHeader().setForeground(new Color(181, 0, 0));

			}
			{
				jScrollPane2 = new JScrollPane();
				contenedor.add(jScrollPane2);
				jScrollPane2.setBounds(734, 78, 538, 454);
				jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

				tbEgresos = new JTable();
				jScrollPane2.setViewportView(tbEgresos);
				tbEgresos.setModel(modeloEgreso);
				tbEgresos.setRowHeight(40);
				tbEgresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
				tbEgresos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 20));
				tbEgresos.getTableHeader().setForeground(new Color(181, 0, 0));
				TableColumn columna = tbEgresos.getColumn("TIPO");
				columna.setPreferredWidth(200);
			}
			{
				lblTotalGanacia = new JLabel();
				contenedor.add(lblTotalGanacia);
				lblTotalGanacia.setBounds(390, 544, 119, 46);
				lblTotalGanacia.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				lblTotalGanacia.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblTotalGanacia.setOpaque(true);
				lblTotalGanacia
						.setForeground(new java.awt.Color(255, 255, 255));
				lblTotalGanacia.setBackground(new java.awt.Color(0, 128, 128));
				lblTotalGanacia.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalNeto = new JLabel();
				contenedor.add(lblTotalNeto);
				lblTotalNeto.setBounds(609, 544, 113, 46);
				lblTotalNeto.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				lblTotalNeto.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblTotalNeto.setOpaque(true);
				lblTotalNeto.setForeground(new java.awt.Color(255, 255, 255));
				lblTotalNeto.setBackground(new java.awt.Color(0, 128, 128));
				lblTotalNeto.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEmpenios = new JLabel();
				contenedor.add(lblTotalEmpenios);
				lblTotalEmpenios.setBounds(734, 544, 118, 71);
				lblTotalEmpenios.setBorder(BorderFactory.createMatteBorder(1,
						1, 1, 1, new java.awt.Color(0, 0, 0)));
				lblTotalEmpenios.setFont(new java.awt.Font("Segoe UI", 1, 72));
				lblTotalEmpenios.setOpaque(true);
				lblTotalEmpenios.setForeground(new java.awt.Color(255, 0, 0));
				lblTotalEmpenios
						.setBackground(new java.awt.Color(255, 255, 255));
				lblTotalEmpenios.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEgreso = new JLabel();
				contenedor.add(lblTotalEgreso);
				lblTotalEgreso.setBounds(1105, 544, 165, 46);
				lblTotalEgreso.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				lblTotalEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblTotalEgreso.setOpaque(true);
				lblTotalEgreso.setForeground(new java.awt.Color(255, 255, 255));
				lblTotalEgreso.setBackground(new java.awt.Color(0, 128, 128));
				lblTotalEgreso.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel2 = new JLabel();
				contenedor.add(jLabel2);
				jLabel2.setText("TOTAL GANANCIA Y NETO (S/.)");
				jLabel2.setBounds(15, 553, 375, 29);
				jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel2.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel3 = new JLabel();
				contenedor.add(jLabel3);
				jLabel3.setText("EMPEÑOS");
				jLabel3.setBounds(858, 560, 122, 29);
				jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel3.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel4 = new JLabel();
				contenedor.add(jLabel4);
				jLabel4.setText("TOTAL");
				jLabel4.setBounds(1013, 560, 86, 29);
				jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel4.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				btnCerrarCaja = new JButton();
				contenedor.add(btnCerrarCaja);
				btnCerrarCaja.setText("CERRAR CAJA");
				btnCerrarCaja.setBounds(1036, 666, 231, 52);
				btnCerrarCaja.setFont(new java.awt.Font("Segoe UI", 1, 28));
				btnCerrarCaja.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				btnCerrarCaja.setBackground(new java.awt.Color(0, 128, 192));
				btnCerrarCaja.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int opc = JOptionPane.showConfirmDialog(null, "<html><h2>Si cierra la caja no podrá realizar ninguna operación hasta las 00:00 hrs. del día siguiente.¿Desea continuar?</h2></html>","Confirmación",JOptionPane.YES_NO_OPTION);
						if(opc == JOptionPane.YES_OPTION){
							cerrarCaja();	
						}else{
							JOptionPane.showMessageDialog(null, "<html><h2>No se realizó ninguna operación</h2></html>");
						}
						
					}
				});
			}
			{
				jSeparator1 = new JXTitledSeparator("RESUMEN DE CAJA");
				contenedor.add(jSeparator1);
				jSeparator1.setBounds(14, 610, 1260, 50);
				jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jSeparator1.setForeground(new java.awt.Color(128, 0, 0));
			}
			{
				jLabel5 = new JLabel();
				contenedor.add(jLabel5);
				jLabel5.setText("INGRESOS");
				jLabel5.setBounds(75, 671, 129, 29);
				jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel5.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel6 = new JLabel();
				contenedor.add(jLabel6);
				jLabel6.setText("EGRESOS");
				jLabel6.setBounds(426, 671, 129, 29);
				jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel6.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				lbltotalIngresos = new JLabel();
				contenedor.add(lbltotalIngresos);
				lbltotalIngresos.setBounds(12, 706, 257, 81);
				lbltotalIngresos.setBorder(BorderFactory.createMatteBorder(1,
						1, 1, 1, new java.awt.Color(0, 0, 0)));
				lbltotalIngresos.setOpaque(true);
				lbltotalIngresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
				lbltotalIngresos
						.setForeground(new java.awt.Color(255, 255, 255));
				lbltotalIngresos.setBackground(new java.awt.Color(0, 128, 128));
				lbltotalIngresos.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEgresos = new JLabel();
				contenedor.add(lblTotalEgresos);
				lblTotalEgresos.setBounds(343, 706, 257, 81);
				lblTotalEgresos.setBorder(new LineBorder(new java.awt.Color(0,
						0, 0), 1, false));
				lblTotalEgresos.setOpaque(true);
				lblTotalEgresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
				lblTotalEgresos
						.setForeground(new java.awt.Color(255, 255, 255));
				lblTotalEgresos.setBackground(new java.awt.Color(0, 128, 128));
				lblTotalEgresos.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel7 = new JLabel();
				contenedor.add(jLabel7);
				jLabel7.setText("-");
				jLabel7.setBounds(289, 690, 40, 75);
				jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 72));
				jLabel7.setForeground(new java.awt.Color(128, 0, 0));
				jLabel7.setVerticalTextPosition(SwingConstants.TOP);
			}
			{
				jLabel8 = new JLabel();
				contenedor.add(jLabel8);
				jLabel8.setText("=");
				jLabel8.setBounds(612, 706, 58, 75);
				jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 72));
				jLabel8.setForeground(new java.awt.Color(128, 0, 0));
			}
			{
				jLabel9 = new JLabel();
				contenedor.add(jLabel9);
				jLabel9.setText("AMANECE");
				jLabel9.setBounds(792, 671, 129, 29);
				jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24));
				jLabel9.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				lblCerrarAmanece = new JLabel();
				contenedor.add(lblCerrarAmanece);
				lblCerrarAmanece.setBounds(676, 706, 334, 81);
				lblCerrarAmanece.setBorder(new LineBorder(new java.awt.Color(0,
						0, 0), 1, false));
				lblCerrarAmanece.setOpaque(true);
				lblCerrarAmanece.setFont(new java.awt.Font("Segoe UI", 1, 72));
				lblCerrarAmanece
						.setForeground(new java.awt.Color(255, 255, 255));
				lblCerrarAmanece.setBackground(new java.awt.Color(0, 128, 128));
				lblCerrarAmanece.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				btnSalir = new JButton();
				contenedor.add(btnSalir);
				btnSalir.setText("SALIR");
				btnSalir.setBounds(1036, 735, 231, 52);
				btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 28));
				btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				btnSalir.setBackground(new java.awt.Color(0, 128, 192));
				btnSalir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						cerrar();
					}
				});
			}

			btnRefresh = new JButton(new ImageIcon("img/refresh.png"));
			contenedor.add(btnRefresh);
			btnRefresh.setBounds(671, 78, 52, 39);
			btnRefresh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			btnRefresh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					refrescarCaja();
				}
			});

		}
		cargarInfoCaja();
		cargarEgresos(Principal.id_libro_caja);
		totalEmpenios();
		totalEgresos();
		cargarIngresos(Principal.id_libro_caja);
		totalGanancias();
		totalNeto();
		calcularIngresosVsEgresos();
	}

	public void refrescarCaja() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql1 = "SELECT * FROM tb_libro_caja WHERE id_caja=?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, (Principal.id_libro_caja - 1));// 136
			ResultSet rs1 = pst1.executeQuery();
			if (rs1.next()) {
				String sql3 = "UPDATE tb_libro_caja SET ama_caja=? WHERE id_caja=?";
				PreparedStatement pst3 = con.prepareStatement(sql3);
				pst3.setDouble(1, rs1.getDouble("cer_caja"));
				pst3.setInt(2, Principal.id_libro_caja);
				pst3.executeUpdate();
				lblAmanece.setText(String.valueOf(rs1.getDouble("cer_caja")));
				JOptionPane.showMessageDialog(null, "¡Caja Actualizada!");
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

	public void totalEmpenios() {
		int empenios = 0;
		for (int i = 0; i <= modeloEgreso.getRowCount() - 1; i++) {
			String tipo = modeloEgreso.getValueAt(i, 1).toString();
			if (tipo.equalsIgnoreCase("EMP.")
					|| tipo.equalsIgnoreCase("EMP.($)")) {
				empenios++;
			}
		}
		lblTotalEmpenios.setText(empenios + "");
	}

	public void totalEgresos() {
		double egresos = 0;
		for (int i = 0; i <= modeloEgreso.getRowCount() - 1; i++) {
			double monto = Double.parseDouble(modeloEgreso.getValueAt(i, 2)
					.toString());
			egresos += monto;
		}
		lblTotalEgreso.setText(df.format(egresos));
		lblTotalEgresos.setText(df.format(egresos));
	}

	public void totalGanancias() {
		double ganancias = 0;
		for (int i = 0; i <= modeloIngreso.getRowCount() - 1; i++) {
			double monto = Double.parseDouble(modeloIngreso.getValueAt(i, 3)
					.toString());
			ganancias += monto;
		}
		lblTotalGanacia.setText(df.format(ganancias));
	}

	public void totalNeto() {
		double neto = 0;
		double amanece = Double.parseDouble(lblAmanece.getText());
		for (int i = 0; i <= modeloIngreso.getRowCount() - 1; i++) {
			double monto = Double.parseDouble(modeloIngreso.getValueAt(i, 5)
					.toString());
			neto += monto;
		}
		lblTotalNeto.setText(df.format(neto + amanece));
		lbltotalIngresos.setText(df.format(neto + amanece));
	}

	public void cargarIngresos(int id_libro_caja) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_ingreso WHERE tb_libro_caja_id_caja=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_libro_caja);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double neto = rs.getDouble("cap_ing") + rs.getDouble("gan_ing")
						+ rs.getDouble("oto_ing");
				if (rs.getString("tip_ing").equalsIgnoreCase("S-REM.")) {
					neto = rs.getDouble("oto_ing");
				}
				modeloIngreso.addRow(new Object[] {
						rs.getString("desc_ing"),
						rs.getString("tip_ing"),
						Redondeo.redondearCentimos(df.format(rs
								.getDouble("cap_ing"))),
						Redondeo.redondearCentimos(df.format(rs
								.getDouble("gan_ing"))),
						Redondeo.redondearCentimos(df.format(rs
								.getDouble("oto_ing"))),
						Redondeo.redondearCentimos(df.format(neto)) });
			}
			tbIngresos.setModel(modeloIngreso);
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

	public void cargarInfoCaja() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_libro_caja WHERE id_caja=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Principal.id_libro_caja);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblAmanece.setText(rs.getDouble("ama_caja") + "");
			} else {
				JOptionPane.showMessageDialog(null,
						"Error al traer datos de Caja");
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

	public void calcularIngresosVsEgresos() {
		double ingresos = Double.parseDouble(lbltotalIngresos.getText()
				.replace(',', '.'));
		double egresos = Double.parseDouble(lblTotalEgresos.getText().replace(
				',', '.'));
		lblCerrarAmanece.setText(Redondeo.redondearCentimos(df.format(ingresos
				- egresos)));
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void cerrarCaja() {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_libro_caja SET est_caja=0, cer_caja=?, cie_caja=? WHERE id_caja=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(
					1,
					Double.parseDouble(lblCerrarAmanece.getText().replace(',',
							'.')));
			pst.setString(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date()));
			pst.setInt(3, Principal.id_libro_caja);
			pst.executeUpdate();
			determinarSalida(Principal.user[2]);
			JOptionPane
					.showMessageDialog(
							null,
							"<html><h1>CAJA CERRADA, INSERTE PAPEL PARA IMPRIMIR REPORTE DE CAJA DIARIA</h1></html>");
			imprimirReporteCaja();
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
				JOptionPane.showMessageDialog(null,
						"SE GRABÓ SU HORA DE SALIDA ... ¡HASTA MAÑANA!.");
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

	public void imprimirReporteCaja() {
		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fecha_master",
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		parametros.put("p", Constantes.SUCURSAL);
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_caja_diaria.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, true);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double obtenerCambio() {
		Connection con = MySQLConexion.getConexion();
		double cambio = 1;
		try {
			String sql = "select ven_cam from tb_cambio where fec_cam=date_Format(now(),'%y-%m-%d')";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cambio = rs.getDouble(1);
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
		return cambio;
	}

	public void cargarEgresos(int id_libro_caja) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_egreso WHERE tb_libro_caja_id_caja=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_libro_caja);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double cambio = (rs.getString("tip_egr")
						.equalsIgnoreCase("EMP.($)")) ? obtenerCambio() : 1;
				modeloEgreso.addRow(new Object[] { rs.getString("desc_egr"),
						rs.getString("tip_egr"),
						df.format(rs.getDouble("mon_egr") * cambio) });
			}
			tbEgresos.setModel(modeloEgreso);
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

}
