package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import common.Constantes;
import common.RenderLCE;
import common.RenderLCI;
import common.Utiles;
import controller.LibroCajaController;
import model.Egreso;
import model.Ingreso;
import model.LibroCaja;

@SuppressWarnings({ "serial" })
public class Libro_Caja extends JInternalFrame {
	private JPanel contenedor;
	private JLabel lblAmanece;
	private JLabel lblCierre;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel lblTotalEgresos;
	private JLabel lbltotalIngresos;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JScrollPane spEgresos;
	private JLabel jLabel2;
	private JLabel lblTotalEgreso;
	private JLabel lblTotalEmpenios;
	private JLabel lblNeto;
	private JLabel lblTotalGanacia;
	private JTable tbEgresos;
	private JTable tbIngresos;
	private JScrollPane spIngresos;
	private JLabel jLabel1;
	private JLabel lblFecha;
	private JXTitledSeparator jSeparator1;
	private JButton btnCerrarCaja;
	private JLabel jLabel3;
	private JButton btnBuscarCaja;
	private JDateChooser dpFecha;

	LibroCaja caja;

	public Libro_Caja(LibroCaja c) {
		caja = c;

		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1296, 854));
		this.setBounds(0, 0, 1296, 854);
		this.setClosable(true);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1295, 825);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnBuscarCaja = new JButton(new ImageIcon("img/search.png"));
		btnBuscarCaja.setOpaque(false);
		btnBuscarCaja.setBorderPainted(false);
		btnBuscarCaja.setContentAreaFilled(false);
		btnBuscarCaja.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contenedor.add(btnBuscarCaja);
		btnBuscarCaja.setBounds(1200, 9, 64, 64);
		btnBuscarCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LocalDate nueva_fecha = dpFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LibroCaja nueva_caja = new LibroCajaController().ObtenerLibroCaja(String.valueOf(nueva_fecha));
				if (Objects.nonNull(nueva_caja)) {
					Principal.dskPrincipal.add(new Libro_Caja(nueva_caja));
					Cerrar();
				} else {
					Utiles.Mensaje(
							"No hay registros de caja para el día " + Constantes.formatoLocal.format(nueva_fecha),
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		dpFecha = new JDateChooser();
		contenedor.add(dpFecha);
		dpFecha.setBounds(920, 26, 262, 38);
		dpFecha.setDate(
				Date.from(LocalDate.parse(caja.getFechaApertura()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		dpFecha.setFont(new java.awt.Font("Segoe UI", 1, 20));

		lblFecha = new JLabel(Constantes.formatoCaja.format(LocalDate.parse(caja.getFechaApertura())).toUpperCase());
		contenedor.add(lblFecha);
		lblFecha.setBounds(0, 0, 1294, 79);
		lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFecha.setBackground(new java.awt.Color(0, 128, 128));
		lblFecha.setForeground(new java.awt.Color(255, 255, 255));
		lblFecha.setOpaque(true);
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("AMANECE (S/.)");
		jLabel1.setBounds(349, 88, 186, 38);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		lblAmanece = new JLabel(String.valueOf(caja.getAmanece()));
		contenedor.add(lblAmanece);
		lblAmanece.setBounds(535, 88, 186, 38);
		lblAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblAmanece.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblAmanece.setOpaque(true);
		lblAmanece.setBackground(new java.awt.Color(255, 255, 128));
		lblAmanece.setForeground(new java.awt.Color(0, 0, 0));
		lblAmanece.setHorizontalAlignment(SwingConstants.CENTER);

		spIngresos = new JScrollPane();
		contenedor.add(spIngresos);
		spIngresos.setBounds(12, 138, 710, 394);
		spIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spIngresos.setBackground(new java.awt.Color(255, 255, 255));

		tbIngresos = new JTable();
		spIngresos.setViewportView(tbIngresos);
		tbIngresos.setModel(Constantes.IngresoModel);
		tbIngresos.setDefaultRenderer(Object.class, new RenderLCI());
		tbIngresos.setRowHeight(40);
		tbIngresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbIngresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbIngresos.getTableHeader().setForeground(new Color(181, 0, 0));

		spEgresos = new JScrollPane();
		contenedor.add(spEgresos);
		spEgresos.setBounds(739, 91, 538, 441);
		spEgresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spEgresos.setBackground(new java.awt.Color(255, 255, 255));
		tbEgresos = new JTable();
		spEgresos.setViewportView(tbEgresos);
		tbEgresos.setModel(Constantes.EgresoModel);
		tbEgresos.setRowHeight(40);
		tbEgresos.setDefaultRenderer(Object.class, new RenderLCE());
		tbEgresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbEgresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbEgresos.getTableHeader().setForeground(new Color(181, 0, 0));

		lblTotalGanacia = new JLabel(String.valueOf(caja.getTotalGanancia()));
		contenedor.add(lblTotalGanacia);
		lblTotalGanacia.setBounds(368, 532, 119, 46);
		lblTotalGanacia.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalGanacia.setOpaque(true);
		lblTotalGanacia.setForeground(new java.awt.Color(0, 0, 0));
		lblTotalGanacia.setBackground(new java.awt.Color(170, 213, 255));
		lblTotalGanacia.setHorizontalAlignment(SwingConstants.CENTER);

		lblNeto = new JLabel(String.valueOf(caja.getTotalNeto()));
		contenedor.add(lblNeto);
		lblNeto.setBounds(609, 532, 113, 46);
		lblNeto.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNeto.setOpaque(true);
		lblNeto.setForeground(new java.awt.Color(0, 0, 0));
		lblNeto.setBackground(new java.awt.Color(170, 213, 255));
		lblNeto.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEmpenios = new JLabel(String.valueOf(caja.getTotalEmpenos()));
		contenedor.add(lblTotalEmpenios);
		lblTotalEmpenios.setBounds(964, 530, 118, 68);
		lblTotalEmpenios.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalEmpenios.setFont(new java.awt.Font("Segoe UI", 1, 60));
		lblTotalEmpenios.setOpaque(true);
		lblTotalEmpenios.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalEmpenios.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalEmpenios.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEgreso = new JLabel(String.valueOf(caja.getTotalEgresos()));
		contenedor.add(lblTotalEgreso);
		lblTotalEgreso.setBounds(1168, 531, 109, 46);
		lblTotalEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgreso.setOpaque(true);
		lblTotalEgreso.setForeground(new java.awt.Color(0, 0, 0));
		lblTotalEgreso.setBackground(new java.awt.Color(170, 213, 255));
		lblTotalEgreso.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("SUMA TOTAL");
		jLabel2.setBounds(13, 532, 357, 44);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("EMPEÑOS");
		jLabel3.setBounds(842, 533, 122, 65);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		btnCerrarCaja = new JButton(new ImageIcon("img/cerrar_caja.png"));
		contenedor.add(btnCerrarCaja);
		btnCerrarCaja.setText("CERRAR CAJA");
		btnCerrarCaja.setEnabled((caja.getStatus() == 1) ? true : false);
		btnCerrarCaja.setOpaque(false);
		btnCerrarCaja.setBorderPainted(false);
		btnCerrarCaja.setContentAreaFilled(false);
		btnCerrarCaja.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCerrarCaja.setBounds(1028, 705, 246, 82);
		btnCerrarCaja.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnCerrarCaja.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnCerrarCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"<html><h2>Si cierra la caja no podrá realizar ninguna operación hasta el día siguiente. ¿Continuar?</h2></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {					
					caja.setStatus(0);
					caja.setCierre(caja.getAmanece().add(caja.getTotalNeto()).subtract(caja.getTotalEgresos()));
					caja.setFechaCierre(String.valueOf(LocalDate.now()));
					List<String> msg = new LibroCajaController().CerrarLibroCaja(caja);
					Utiles.Mensaje(msg.get(0), Integer.parseInt(msg.get(1)));
				}
			}
		});

		jSeparator1 = new JXTitledSeparator("RESUMEN DE CAJA");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(15, 598, 1259, 50);
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("INGRESOS");
		jLabel5.setBounds(70, 660, 129, 29);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("EGRESOS");
		jLabel6.setBounds(414, 660, 129, 29);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		lbltotalIngresos = new JLabel(String.valueOf(caja.getAmanece().add(caja.getTotalNeto())));
		contenedor.add(lbltotalIngresos);
		lbltotalIngresos.setBounds(12, 706, 257, 81);
		lbltotalIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lbltotalIngresos.setOpaque(true);
		lbltotalIngresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
		lbltotalIngresos.setForeground(new java.awt.Color(255, 255, 255));
		lbltotalIngresos.setBackground(new java.awt.Color(0, 128, 0));
		lbltotalIngresos.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEgresos = new JLabel(String.valueOf(caja.getTotalEgresos()));
		contenedor.add(lblTotalEgresos);
		lblTotalEgresos.setBounds(343, 706, 257, 81);
		lblTotalEgresos.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblTotalEgresos.setOpaque(true);
		lblTotalEgresos.setFont(new java.awt.Font("Segoe UI", 1, 50));
		lblTotalEgresos.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgresos.setBackground(new java.awt.Color(255, 0, 0));
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
		jLabel9.setText("CIERRE");
		jLabel9.setBounds(780, 665, 129, 29);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		lblCierre = new JLabel(String.valueOf(caja.getCierre()));
		contenedor.add(lblCierre);
		lblCierre.setBounds(676, 706, 334, 81);
		lblCierre.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblCierre.setOpaque(true);
		lblCierre.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblCierre.setForeground(new java.awt.Color(0, 0, 0));
		lblCierre.setBackground(new java.awt.Color(255, 255, 128));
		lblCierre.setHorizontalAlignment(SwingConstants.CENTER);

		CargarIngresos();
		CargarEgresos();

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
