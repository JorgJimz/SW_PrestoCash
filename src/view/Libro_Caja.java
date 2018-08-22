package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTitledSeparator;

import common.Constantes;
import controller.LibroCajaController;
import model.Egreso;
import model.Ingreso;
import model.LibroCaja;

@SuppressWarnings({ "serial" })
public class Libro_Caja extends JInternalFrame {
	private JPanel contenedor;
	private JLabel lblAmanece;
	private JButton btnRefresh;
	private JButton btnSalir;
	private JLabel lblCierre;
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
	private JLabel lblNeto;
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

	LibroCaja caja;

	public Libro_Caja() {
		caja = new LibroCajaController().ObtenerLibroCaja();

		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1296, 854));
		this.setBounds(0, 0, 1296, 854);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1295, 825);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		lblFecha = new JLabel(Constantes.formatoLocal.format(LocalDate.parse(caja.getFechaApertura())));
		contenedor.add(lblFecha);
		lblFecha.setBounds(0, 0, 1295, 66);
		lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFecha.setBackground(new java.awt.Color(0, 128, 128));
		lblFecha.setForeground(new java.awt.Color(255, 255, 255));
		lblFecha.setOpaque(true);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("AMANECE (S/.)");
		jLabel1.setBounds(355, 78, 186, 38);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		lblAmanece = new JLabel(String.valueOf(caja.getAmanece()));
		contenedor.add(lblAmanece);
		lblAmanece.setBounds(535, 78, 124, 38);
		lblAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblAmanece.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblAmanece.setOpaque(true);
		lblAmanece.setBackground(new java.awt.Color(0, 128, 128));
		lblAmanece.setForeground(new java.awt.Color(255, 255, 255));
		lblAmanece.setHorizontalAlignment(SwingConstants.CENTER);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 128, 710, 404);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbIngresos = new JTable();
		jScrollPane1.setViewportView(tbIngresos);
		tbIngresos.setModel(Constantes.IngresoModel);
		tbIngresos.setRowHeight(40);
		tbIngresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbIngresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbIngresos.getTableHeader().setForeground(new Color(181, 0, 0));

		jScrollPane2 = new JScrollPane();
		contenedor.add(jScrollPane2);
		jScrollPane2.setBounds(734, 78, 538, 454);
		jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

		tbEgresos = new JTable();
		jScrollPane2.setViewportView(tbEgresos);
		tbEgresos.setModel(Constantes.EgresoModel);
		tbEgresos.setRowHeight(40);
		tbEgresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbEgresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbEgresos.getTableHeader().setForeground(new Color(181, 0, 0));
		TableColumn columna = tbEgresos.getColumn("TIPO");
		columna.setPreferredWidth(200);

		lblTotalGanacia = new JLabel(String.valueOf(caja.getTotalGanancia()));
		contenedor.add(lblTotalGanacia);
		lblTotalGanacia.setBounds(390, 544, 119, 46);
		lblTotalGanacia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalGanacia.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalGanacia.setOpaque(true);
		lblTotalGanacia.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalGanacia.setBackground(new java.awt.Color(0, 128, 128));
		lblTotalGanacia.setHorizontalAlignment(SwingConstants.CENTER);

		lblNeto = new JLabel(String.valueOf(caja.getTotalNeto()));
		contenedor.add(lblNeto);
		lblNeto.setBounds(609, 544, 113, 46);
		lblNeto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblNeto.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNeto.setOpaque(true);
		lblNeto.setForeground(new java.awt.Color(255, 255, 255));
		lblNeto.setBackground(new java.awt.Color(0, 128, 128));
		lblNeto.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEmpenios = new JLabel(String.valueOf(caja.getTotalEmpenos()));
		contenedor.add(lblTotalEmpenios);
		lblTotalEmpenios.setBounds(734, 544, 118, 71);
		lblTotalEmpenios.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalEmpenios.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblTotalEmpenios.setOpaque(true);
		lblTotalEmpenios.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalEmpenios.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalEmpenios.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEgreso = new JLabel(String.valueOf(caja.getTotalEgresos()));
		contenedor.add(lblTotalEgreso);
		lblTotalEgreso.setBounds(1105, 544, 165, 46);
		lblTotalEgreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgreso.setOpaque(true);
		lblTotalEgreso.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgreso.setBackground(new java.awt.Color(0, 128, 128));
		lblTotalEgreso.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("TOTAL GANANCIA Y NETO (S/.)");
		jLabel2.setBounds(15, 553, 375, 29);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("EMPEÑOS");
		jLabel3.setBounds(858, 560, 122, 29);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("TOTAL");
		jLabel4.setBounds(1013, 560, 86, 29);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		btnCerrarCaja = new JButton();
		contenedor.add(btnCerrarCaja);
		btnCerrarCaja.setText("CERRAR CAJA");
		btnCerrarCaja.setBounds(1036, 666, 231, 52);
		btnCerrarCaja.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnCerrarCaja.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnCerrarCaja.setBackground(new java.awt.Color(0, 128, 192));
		btnCerrarCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"<html><h2>Si cierra la caja no podrá realizar ninguna operación hasta las 00:00 hrs. del día siguiente.¿Desea continuar?</h2></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					// cerrarCaja();
				} else {
					JOptionPane.showMessageDialog(null, "<html><h2>No se realizó ninguna operación</h2></html>");
				}

			}
		});

		jSeparator1 = new JXTitledSeparator("RESUMEN DE CAJA");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(14, 610, 1260, 50);
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("INGRESOS");
		jLabel5.setBounds(75, 671, 129, 29);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("EGRESOS");
		jLabel6.setBounds(426, 671, 129, 29);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		lbltotalIngresos = new JLabel(String.valueOf(caja.getAmanece().add(caja.getTotalNeto())));
		contenedor.add(lbltotalIngresos);
		lbltotalIngresos.setBounds(12, 706, 257, 81);
		lbltotalIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lbltotalIngresos.setOpaque(true);
		lbltotalIngresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
		lbltotalIngresos.setForeground(new java.awt.Color(255, 255, 255));
		lbltotalIngresos.setBackground(new java.awt.Color(0, 128, 128));
		lbltotalIngresos.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEgresos = new JLabel(String.valueOf(caja.getTotalEgresos()));
		contenedor.add(lblTotalEgresos);
		lblTotalEgresos.setBounds(343, 706, 257, 81);
		lblTotalEgresos.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblTotalEgresos.setOpaque(true);
		lblTotalEgresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
		lblTotalEgresos.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgresos.setBackground(new java.awt.Color(0, 128, 128));
		lblTotalEgresos.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("-");
		jLabel7.setBounds(289, 690, 40, 75);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 72));
		jLabel7.setForeground(new java.awt.Color(128, 0, 0));
		jLabel7.setVerticalTextPosition(SwingConstants.TOP);

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("=");
		jLabel8.setBounds(612, 706, 58, 75);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 72));
		jLabel8.setForeground(new java.awt.Color(128, 0, 0));

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("AMANECE");
		jLabel9.setBounds(792, 671, 129, 29);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		lblCierre = new JLabel(String.valueOf(caja.getCierre()));
		contenedor.add(lblCierre);
		lblCierre.setBounds(676, 706, 334, 81);
		lblCierre.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblCierre.setOpaque(true);
		lblCierre.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblCierre.setForeground(new java.awt.Color(255, 255, 255));
		lblCierre.setBackground(new java.awt.Color(0, 128, 128));
		lblCierre.setHorizontalAlignment(SwingConstants.CENTER);

		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(1036, 735, 231, 52);
		btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnSalir.setBackground(new java.awt.Color(0, 128, 192));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cerrar();
			}
		});

		btnRefresh = new JButton(new ImageIcon("img/refresh.png"));
		contenedor.add(btnRefresh);
		btnRefresh.setBounds(671, 78, 52, 39);
		btnRefresh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// refrescarCaja();
			}
		});

		CargarIngresos();
		CargarEgresos();

		/*
		 * totalEmpenios(); totalEgresos(); totalGanancias(); totalNeto();
		 * calcularIngresosVsEgresos();
		 */

	}

	/*
	 * if (rs.getString("tip_ing").equalsIgnoreCase("S-REM.")) { neto =
	 * rs.getDouble("oto_ing"); }
	 */
	public void CargarIngresos() {
		Constantes.IngresoModel.setRowCount(0);
		for (Ingreso i : caja.getIngresos()) {
			Constantes.IngresoModel.addRow(new Object[] { i.getDescripcion(), i.getTipo(), i.getCapital(),
					i.getGanancia(), i.getOtro(), i.getCapital().add(i.getGanancia()).add(i.getOtro()) });
		}
		tbIngresos.setModel(Constantes.IngresoModel);
	}

	/*
	 * double cambio = (rs.getString("tip_egr").equalsIgnoreCase("EMP.($)")) ?
	 * obtenerCambio() : 1;
	 */
	public void CargarEgresos() {
		Constantes.EgresoModel.setRowCount(0);
		for (Egreso e : caja.getEgresos()) {
			Constantes.EgresoModel.addRow(new Object[] { e.getDescripcion(), e.getTipo(), e.getImporte() });
		}
		tbEgresos.setModel(Constantes.EgresoModel);
	}

	public void Cerrar() {
		this.dispose();
	}

	/*
	 * 
	 */
	/*
	 * 
	 * 
	 * public void cerrarCaja() { Connection con = MySQLConexion.getConexion(); try
	 * { String sql =
	 * "UPDATE tb_libro_caja SET est_caja=0, cer_caja=?, cie_caja=? WHERE id_caja=?"
	 * ; PreparedStatement pst = con.prepareStatement(sql); pst.setDouble(1,
	 * Double.parseDouble(lblCierre.getText().replace(',', '.')));
	 * pst.setString(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new
	 * Date())); pst.setInt(3, Principal.id_libro_caja); pst.executeUpdate();
	 * determinarSalida(Principal.user[2]); JOptionPane.showMessageDialog(null,
	 * "<html><h1>CAJA CERRADA, INSERTE PAPEL PARA IMPRIMIR REPORTE DE CAJA DIARIA</h1></html>"
	 * ); imprimirReporteCaja(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { try { con.close(); } catch (SQLException e) { e.printStackTrace();
	 * } } }
	 * 
	 * public void determinarSalida(String dni) { Connection con =
	 * MySQLConexion.getConexion(); try { String hoy = new
	 * SimpleDateFormat("yyyy-MM-dd").format(new Date()); String sql =
	 * "SELECT * FROM tb_asistencia a INNER JOIN tb_usuario u ON a.tb_usuario_dni_usu = u.dni_usu WHERE tb_usuario_dni_usu=? AND fec_asi=?"
	 * ; PreparedStatement pst = con.prepareStatement(sql); pst.setString(1, dni);
	 * pst.setString(2, hoy); ResultSet rs = pst.executeQuery(); if (rs.next()) {
	 * Asistencia asistencia = new Asistencia(rs.getString(2), hoy, new
	 * SimpleDateFormat("HH:mm:ss").format(rs.getDate("ent_usu")), new
	 * SimpleDateFormat("HH:mm:ss").format(new Date().getTime()), new
	 * SimpleDateFormat("HH:mm:ss").format(new Date()), "", "", null);
	 * asistencia.registrarSalida(); JOptionPane.showMessageDialog(null,
	 * "SE GRABÓ SU HORA DE SALIDA ... ¡HASTA MAÑANA!."); } } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { con.close(); } catch (SQLException e)
	 * { e.printStackTrace(); } } }
	 * 
	 * public void imprimirReporteCaja() { Connection con =
	 * MySQLConexion.getConexion(); HashMap<String, Object> parametros = new
	 * HashMap<String, Object>(); parametros.put("fecha_master", new
	 * SimpleDateFormat("yyyy-MM-dd").format(new Date())); parametros.put("p",
	 * Constantes.SUCURSAL); try { JasperReport reporte = (JasperReport)
	 * JRLoader.loadObject("reporte_caja_diaria.jasper"); JasperPrint print =
	 * JasperFillManager.fillReport(reporte, parametros, con); JasperViewer viewer =
	 * new JasperViewer(print, true); viewer.show(); viewer.toFront(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * public double obtenerCambio() { Connection con = MySQLConexion.getConexion();
	 * double cambio = 1; try { String sql =
	 * "select ven_cam from tb_cambio where fec_cam=date_Format(now(),'%y-%m-%d')";
	 * PreparedStatement pst = con.prepareStatement(sql); ResultSet rs =
	 * pst.executeQuery(); if (rs.next()) { cambio = rs.getDouble(1); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { try { con.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } return cambio; }
	 */
}
