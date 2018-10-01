package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import common.ComboItem;
import common.Constantes;
import common.EditorLCE;
import common.EditorLCI;
import common.JEditableTable;
import common.JIconTextField;
import common.RenderLCE;
import common.RenderLCI;
import common.Utiles;
import controller.LibroCajaController;
import controller.UsuarioController;
import model.Asistencia;
import model.Egreso;
import model.Ingreso;
import model.LibroCaja;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings({ "serial", "deprecation" })
public class Libro_Caja extends JInternalFrame {
	private JPanel contenedor;
	private JIconTextField lblAmanece;
	private JLabel jLabel9;
	private JIconTextField lblTotalEgresos;
	private JIconTextField lbltotalIngresos;
	private JIconTextField lblCierre;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JScrollPane spEgresos;
	private JLabel jLabel2;
	private JLabel lblTotalEmpenios;
	private JIconTextField lblNeto;
	private JIconTextField lblTotalGanacia;
	private JIconTextField lblTotalGanaciaDolares;
	private JEditableTable tbEgresos;
	private JEditableTable tbIngresos;
	private JScrollPane spIngresos;
	private JXTitledSeparator jSeparator1;
	private JButton btnCerrarCaja;
	private JLabel jLabel3;
	private JIconTextField lblTotalEgreso;
	private JIconTextField lblTotalEgresoDolares;
	private JIconTextField lblNetoDolares;
	private JLabel jLabel1;
	private JIconTextField lblCierreDolares;
	private JIconTextField lblTotalEgresosDolares;
	private JIconTextField lbltotalIngresosDolares;
	private JIconTextField lblAmaneceDolares;
	private JButton btnImprimir;
	private JButton btnSalir;
	private JButton btnGrabarEgreso;
	private JButton btnGrabarIngreso;
	private JButton btnNuevoEgreso;
	private JButton btnNuevoIngreso;
	private JButton btnBuscarCaja;
	private JDateChooser dpFecha;

	LibroCaja caja;

	public Libro_Caja(LibroCaja c) {
		caja = c;

		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1772, 907));
		this.setBounds(0, 0, 1772, 907);
		this.setClosable(false);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1770, 877);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnBuscarCaja = new JButton(new ImageIcon("img/search.png"));
		btnBuscarCaja.setOpaque(false);
		btnBuscarCaja.setBorderPainted(false);
		btnBuscarCaja.setContentAreaFilled(false);
		btnBuscarCaja.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contenedor.add(btnBuscarCaja);
		btnBuscarCaja.setBounds(1671, 5, 64, 66);
		btnBuscarCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LocalDate nueva_fecha = dpFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LibroCaja nueva_caja = new LibroCajaController().ObtenerLibroCaja(nueva_fecha);
				if (Objects.nonNull(nueva_caja)) {
					Principal.dskPrincipal.add(new Libro_Caja(nueva_caja));
					Cerrar();
				} else {
					Utiles.Mensaje(
							"No hay registros de caja para el d�a " + Constantes.formatoLocal.format(nueva_fecha),
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		dpFecha = new JDateChooser();
		contenedor.add(dpFecha);
		dpFecha.setBounds(1402, 21, 263, 38);
		dpFecha.setDate(
				Date.from(LocalDate.parse(caja.getFechaApertura()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		dpFecha.setFont(new java.awt.Font("Segoe UI", 1, 20));

		lblAmanece = new JIconTextField();
		lblAmanece.setIcon(new ImageIcon("img/pen.png"));
		lblAmanece.setText(String.valueOf(caja.getAmanece()));
		contenedor.add(lblAmanece);
		lblAmanece.setBounds(1323, 117, 186, 46);
		lblAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblAmanece.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblAmanece.setOpaque(true);
		lblAmanece.setBackground(new java.awt.Color(72, 133, 237));
		lblAmanece.setForeground(new java.awt.Color(255, 255, 255));
		lblAmanece.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmanece.setEditable(false);
		lblAmanece.setOrientation(SwingConstants.RIGHT);

		lblAmaneceDolares = new JIconTextField();
		lblAmaneceDolares.setText(String.valueOf(caja.getAmaneceDolares()));
		lblAmaneceDolares.setIcon(new ImageIcon("img/usd.png"));
		contenedor.add(lblAmaneceDolares);
		lblAmaneceDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmaneceDolares.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblAmaneceDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblAmaneceDolares.setBackground(new java.awt.Color(72, 133, 237));
		lblAmaneceDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblAmaneceDolares.setOpaque(true);
		lblAmaneceDolares.setBounds(1556, 117, 186, 46);
		lblAmaneceDolares.setEditable(false);
		lblAmaneceDolares.setOrientation(SwingConstants.RIGHT);

		spIngresos = new JScrollPane();
		contenedor.add(spIngresos);
		spIngresos.setBounds(12, 66, 710, 670);
		spIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spIngresos.setBackground(new java.awt.Color(255, 255, 255));

		tbIngresos = new JEditableTable();
		spIngresos.setViewportView(tbIngresos);
		tbIngresos.setModel(Constantes.IngresoModel);
		tbIngresos.setDefaultRenderer(Object.class, new RenderLCI());

		tbIngresos.setRowHeight(40);
		tbIngresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbIngresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbIngresos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbIngresos.setDefaultEditor(Object.class, new EditorLCI());
		tbIngresos.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					if (e.getColumn() != 5) {
						BigDecimal capital = new BigDecimal(String.valueOf(tbIngresos.getValueAt(e.getLastRow(), 2)));
						BigDecimal interes = new BigDecimal(String.valueOf(tbIngresos.getValueAt(e.getLastRow(), 3)));
						BigDecimal otro = new BigDecimal(String.valueOf(tbIngresos.getValueAt(e.getLastRow(), 4)));
						tbIngresos.getModel().setValueAt(capital.add(interes).add(otro), e.getLastRow(), 5);
					}
				}
			}
		});
		spEgresos = new JScrollPane();
		contenedor.add(spEgresos);
		spEgresos.setBounds(739, 66, 555, 670);
		spEgresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spEgresos.setBackground(new java.awt.Color(255, 255, 255));
		tbEgresos = new JEditableTable();
		spEgresos.setViewportView(tbEgresos);
		tbEgresos.setModel(Constantes.EgresoModel);
		tbEgresos.setRowHeight(40);
		tbEgresos.setDefaultRenderer(Object.class, new RenderLCE());
		tbEgresos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tbEgresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbEgresos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbEgresos.setDefaultEditor(Object.class, new EditorLCE());

		lblTotalGanacia = new JIconTextField();
		lblTotalGanacia.setText(String.valueOf(caja.getTotalGanancia()));
		lblTotalGanacia.setIcon(new ImageIcon("img/pen.png"));
		contenedor.add(lblTotalGanacia);
		lblTotalGanacia.setBounds(282, 735, 186, 46);
		lblTotalGanacia.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalGanacia.setOpaque(true);
		lblTotalGanacia.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalGanacia.setBackground(new java.awt.Color(130,137,143));
		lblTotalGanacia.setOrientation(SwingConstants.RIGHT);
		lblTotalGanacia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalGanacia.setEditable(false);

		lblNeto = new JIconTextField();
		lblNeto.setText(String.valueOf(caja.getTotalNeto()));
		lblNeto.setIcon(new ImageIcon("img/pen.png"));
		lblNeto.setOrientation(SwingConstants.RIGHT);
		contenedor.add(lblNeto);
		lblNeto.setBounds(505, 735, 186, 46);
		lblNeto.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNeto.setOpaque(true);
		lblNeto.setEditable(false);
		lblNeto.setForeground(new java.awt.Color(255, 255, 255));
		lblNeto.setBackground(new java.awt.Color(130,137,143));
		lblNeto.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTotalEmpenios = new JLabel(String.valueOf(caja.getTotalEmpenos()));
		contenedor.add(lblTotalEmpenios);
		lblTotalEmpenios.setBounds(873, 734, 118, 68);
		lblTotalEmpenios.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblTotalEmpenios.setFont(new java.awt.Font("Segoe UI", 1, 60));
		lblTotalEmpenios.setOpaque(true);
		lblTotalEmpenios.setForeground(new java.awt.Color(255, 0, 0));
		lblTotalEmpenios.setBackground(new java.awt.Color(255, 255, 255));
		lblTotalEmpenios.setHorizontalAlignment(SwingConstants.CENTER);

		lblTotalEgreso = new JIconTextField();
		contenedor.add(lblTotalEgreso);
		lblTotalEgreso.setIcon(new ImageIcon("img/pen.png"));
		lblTotalEgreso.setText(String.valueOf(caja.getTotalEgresos()));
		lblTotalEgreso.setBounds(1009, 735, 186, 46);
		lblTotalEgreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgreso.setOpaque(true);
		lblTotalEgreso.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgreso.setBackground(new java.awt.Color(130,137,143));
		lblTotalEgreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalEgreso.setOrientation(SwingConstants.RIGHT);
		lblTotalEgreso.setEditable(false);
		
		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("SUMA TOTAL");
		jLabel2.setBounds(14, 736, 246, 44);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("EMPE�OS");
		jLabel3.setBounds(739, 737, 122, 65);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		btnCerrarCaja = new JButton(new ImageIcon("img/cerrar_caja.png"));
		contenedor.add(btnCerrarCaja);
		btnCerrarCaja.setText("CERRAR");
		btnCerrarCaja.setVisible((caja.getStatus() == 1) ? true : false);
		btnCerrarCaja.setOpaque(false);
		btnCerrarCaja.setBorderPainted(false);
		btnCerrarCaja.setContentAreaFilled(false);
		btnCerrarCaja.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCerrarCaja.setBounds(1328, 603, 198, 70);
		btnCerrarCaja.setFont(new java.awt.Font("Segoe UI", 1, 18));
		btnCerrarCaja.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"<html><h2>Si cierra la caja no podr� realizar ninguna operaci�n hasta el d�a siguiente. �Continuar?</h2></html>",
						"Confirmaci�n", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					caja.setStatus(0);
					caja.setCierre(caja.getAmanece().add(caja.getTotalNeto()).subtract(caja.getTotalEgresos()));
					caja.setFechaCierre(String.valueOf(LocalDate.now()));
					caja.setHoraCierre(String.valueOf(LocalTime.now()));
					Asistencia a = Principal.LOGGED.getAsistencias().stream().filter(Constantes.predicadoAsistencia)
							.findFirst().orElse(Asistencia.DEFAULT);
					a.setHoraSalida(String.valueOf(LocalTime.now()));
					new UsuarioController().MarcarAsistencia(a);
					List<String> msg = new LibroCajaController().CerrarLibroCaja(caja);
					Utiles.Mensaje(msg.get(0), Integer.parseInt(msg.get(1)));

					ImprimirCaja();
				}
			}
		});

		jSeparator1 = new JXTitledSeparator(
				Constantes.formatoCaja.format(LocalDate.parse(caja.getFechaApertura())).toUpperCase());
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 9, 1387, 50);
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 30));
		jSeparator1.setForeground(new java.awt.Color(0, 0, 160));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("INGRESOS GLOBALES");
		jLabel5.setBounds(1323, 183, 419, 29);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("EGRESOS GLOBALES");
		jLabel6.setBounds(1323, 276, 419, 29);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		lbltotalIngresos = new JIconTextField();
		lbltotalIngresos.setText(String.valueOf(caja.getAmanece().add(caja.getTotalNeto())));
		lbltotalIngresos.setIcon(new ImageIcon("img/pen.png"));
		lbltotalIngresos.setOrientation(SwingConstants.RIGHT);
		contenedor.add(lbltotalIngresos);
		lbltotalIngresos.setBounds(1323, 218, 186, 46);
		lbltotalIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lbltotalIngresos.setOpaque(true);
		lbltotalIngresos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lbltotalIngresos.setForeground(new java.awt.Color(255, 255, 255));
		lbltotalIngresos.setBackground(new java.awt.Color(60, 186, 84));
		lbltotalIngresos.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalIngresos.setEditable(false);

		lblTotalEgresos = new JIconTextField();
		lblTotalEgresos.setText(String.valueOf(caja.getTotalEgresos()));
		lblTotalEgresos.setIcon(new ImageIcon("img/pen.png"));
		lblTotalEgresos.setOrientation(SwingConstants.RIGHT);
		contenedor.add(lblTotalEgresos);
		lblTotalEgresos.setBounds(1323, 311, 186, 46);
		lblTotalEgresos.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblTotalEgresos.setOpaque(true);
		lblTotalEgresos.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgresos.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgresos.setBackground(new java.awt.Color(219, 50, 54));
		lblTotalEgresos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalEgresos.setEditable(false);

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("CIERRE GLOBAL");
		jLabel9.setBounds(1323, 369, 419, 29);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		lblCierre = new JIconTextField();
		lblCierre.setText(String.valueOf(caja.getCierre()));
		lblCierre.setIcon(new ImageIcon("img/pen.png"));
		lblCierre.setOrientation(SwingConstants.RIGHT);
		contenedor.add(lblCierre);
		lblCierre.setBounds(1323, 404, 186, 46);
		lblCierre.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblCierre.setOpaque(true);
		lblCierre.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCierre.setForeground(new java.awt.Color(255, 255, 255));
		lblCierre.setBackground(new java.awt.Color(255, 215, 0));
		lblCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCierre.setEditable(false);

		btnNuevoIngreso = new JButton(new ImageIcon("img/ingreso.png"));
		contenedor.add(btnNuevoIngreso);
		btnNuevoIngreso.setOpaque(false);
		btnNuevoIngreso.setBorderPainted(false);
		btnNuevoIngreso.setContentAreaFilled(false);
		btnNuevoIngreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNuevoIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnNuevoIngreso.setText("INGRESO");
		btnNuevoIngreso.setBounds(1549, 476, 198, 70);
		btnNuevoIngreso.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevoIngreso.setEnabled(btnCerrarCaja.isVisible());
		btnNuevoIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!btnGrabarEgreso.isVisible()) {
					Constantes.IngresoModel.addRow(new Object[] { "", "", "0.00", "0.00", "0.00", "0.00", "" });
					tbIngresos.setCellEditable(true);
					tbIngresos.setModel(Constantes.IngresoModel);
					btnNuevoIngreso.setVisible(false);
					btnGrabarIngreso.setVisible(true);
					btnCerrarCaja.setEnabled(false);
				} else {
					Utiles.Mensaje(
							"Tiene cambios pendientes en el lado de <b>EGRESOS</b>. Guarde la informaci�n primero antes de continuar.",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnNuevoEgreso = new JButton(new ImageIcon("img/egreso.png"));
		contenedor.add(btnNuevoEgreso);
		btnNuevoEgreso.setOpaque(false);
		btnNuevoEgreso.setBorderPainted(false);
		btnNuevoEgreso.setContentAreaFilled(false);
		btnNuevoEgreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNuevoEgreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnNuevoEgreso.setText("EGRESO");
		btnNuevoEgreso.setBounds(1328, 476, 198, 70);
		btnNuevoEgreso.setEnabled(btnCerrarCaja.isVisible());
		btnNuevoEgreso.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevoEgreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!btnGrabarIngreso.isVisible()) {
					Constantes.EgresoModel.addRow(new Object[] { "", "", "0.00", "" });
					tbEgresos.setCellEditable(true);
					tbEgresos.setModel(Constantes.EgresoModel);
					btnNuevoEgreso.setVisible(false);
					btnGrabarEgreso.setVisible(true);
					btnCerrarCaja.setEnabled(false);
				} else {
					Utiles.Mensaje(
							"Tiene cambios pendientes en el lado de <b>INGRESOS</b>. Guarde la informaci�n primero antes de continuar.",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnGrabarIngreso = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabarIngreso);
		btnGrabarIngreso.setVisible(false);
		btnGrabarIngreso.setText("GRABAR");
		btnGrabarIngreso.setOpaque(false);
		btnGrabarIngreso.setBorderPainted(false);
		btnGrabarIngreso.setContentAreaFilled(false);
		btnGrabarIngreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabarIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGrabarIngreso.setFont(new java.awt.Font("Segoe UI", 1, 18));
		btnGrabarIngreso.setBounds(1549, 476, 198, 70);
		btnGrabarIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.ValidarTabla(tbIngresos)) {
					Ingreso ingreso = new Ingreso();
					ingreso.setLibroCaja(Principal.LIBRO_CAJA);
					ingreso.setDescripcion(String
							.valueOf(Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 0)));
					ingreso.setTipo(String
							.valueOf(Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 1)));
					ingreso.setCapital(new BigDecimal(String.valueOf(
							Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 2))));
					ingreso.setGanancia(new BigDecimal(String.valueOf(
							Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 3))));
					ingreso.setOtro(new BigDecimal(String.valueOf(
							Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 4))));
					ingreso.setMoneda(String
							.valueOf(Constantes.IngresoModel.getValueAt(Constantes.IngresoModel.getRowCount() - 1, 6)));
					new LibroCajaController().RegistrarIngreso(ingreso);
					tbIngresos.setCellEditable(false);
					btnGrabarIngreso.setVisible(false);
					btnNuevoIngreso.setVisible(true);
					btnCerrarCaja.setEnabled(true);
					Utiles.Mensaje("Ingreso registrado.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Utiles.Mensaje("Complete los datos", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnGrabarEgreso = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabarEgreso);
		btnGrabarEgreso.setOpaque(false);
		btnGrabarEgreso.setBorderPainted(false);
		btnGrabarEgreso.setContentAreaFilled(false);
		btnGrabarEgreso.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGrabarEgreso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabarEgreso.setFont(new java.awt.Font("Segoe UI", 1, 18));
		btnGrabarEgreso.setVisible(false);
		btnGrabarEgreso.setText("GRABAR");
		btnGrabarEgreso.setBounds(1328, 476, 198, 70);
		btnGrabarEgreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				ComboItem c = (ComboItem)Constantes.EgresoModel.getValueAt(Constantes.EgresoModel.getRowCount() - 1, 1);				
				if (Utiles.ValidarTabla(tbEgresos)) {
					Egreso egreso = new Egreso();
					egreso.setLibroCaja(Principal.LIBRO_CAJA);
					egreso.setDescripcion(String
							.valueOf(Constantes.EgresoModel.getValueAt(Constantes.EgresoModel.getRowCount() - 1, 0)));
					egreso.setTipo(c.getDescripcion());
					egreso.setImporte(new BigDecimal(String
							.valueOf(Constantes.EgresoModel.getValueAt(Constantes.EgresoModel.getRowCount() - 1, 2))));
					egreso.setMoneda(String
							.valueOf(Constantes.EgresoModel.getValueAt(Constantes.EgresoModel.getRowCount() - 1, 3)));
					new LibroCajaController().RegistrarEgreso(egreso);
					tbEgresos.setCellEditable(false);
					btnGrabarEgreso.setVisible(false);
					btnNuevoEgreso.setVisible(true);
					btnCerrarCaja.setEnabled(true);
					Utiles.Mensaje("Egreso registrado.", JOptionPane.INFORMATION_MESSAGE);
					if(c.getValor().equals("PRINTABLE")) {
						
					}
				} else {
					Utiles.Mensaje("Complete los datos", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSalir = new JButton(new ImageIcon("img/salir.png"));
		contenedor.add(btnSalir);
		btnSalir.setOpaque(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.setText("SALIR");
		btnSalir.setBounds(1549, 595, 198, 70);
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);

		btnImprimir = new JButton(new ImageIcon("img/printer.png"));
		contenedor.add(btnImprimir);
		btnImprimir.setText("IMPRIMIR");
		btnImprimir.setVisible(!btnCerrarCaja.isVisible());
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		btnImprimir.setOpaque(false);
		btnImprimir.setBorderPainted(false);
		btnImprimir.setContentAreaFilled(false);
		btnImprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnImprimir.setBounds(1328, 603, 198, 70);
		btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 18));

		lbltotalIngresosDolares = new JIconTextField();
		contenedor.add(lbltotalIngresosDolares);
		lbltotalIngresosDolares.setOrientation(SwingConstants.RIGHT);
		lbltotalIngresosDolares.setText(String.valueOf(caja.getAmaneceDolares().add(caja.getTotalNetoDolares())));
		lbltotalIngresosDolares.setIcon(new ImageIcon("img/usd.png"));
		lbltotalIngresosDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lbltotalIngresosDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalIngresosDolares.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lbltotalIngresosDolares.setBackground(new java.awt.Color(60, 186, 84));
		lbltotalIngresosDolares.setForeground(new java.awt.Color(255, 255, 255));
		lbltotalIngresosDolares.setOpaque(true);
		lbltotalIngresosDolares.setBounds(1556, 218, 186, 46);
		lbltotalIngresosDolares.setEditable(false);

		lblTotalEgresosDolares = new JIconTextField();
		contenedor.add(lblTotalEgresosDolares);
		lblTotalEgresosDolares.setText(String.valueOf(caja.getTotalEgresosDolares()));
		lblTotalEgresosDolares.setOrientation(SwingConstants.RIGHT);
		lblTotalEgresosDolares.setIcon(new ImageIcon("img/usd.png"));
		lblTotalEgresosDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgresosDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalEgresosDolares.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblTotalEgresosDolares.setBackground(new java.awt.Color(219, 50, 54));
		lblTotalEgresosDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgresosDolares.setOpaque(true);
		lblTotalEgresosDolares.setBounds(1556, 311, 186, 46);
		lblTotalEgresosDolares.setEditable(false);

		lblCierreDolares = new JIconTextField();
		contenedor.add(lblCierreDolares);
		lblCierreDolares.setText(String.valueOf(caja.getCierreDolares()));
		lblCierreDolares.setOrientation(SwingConstants.RIGHT);
		lblCierreDolares.setIcon(new ImageIcon("img/usd.png"));
		lblCierreDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblCierreDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCierreDolares.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		lblCierreDolares.setBackground(new java.awt.Color(255, 215, 0));
		lblCierreDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblCierreDolares.setOpaque(true);
		lblCierreDolares.setBounds(1556, 404, 186, 46);
		lblCierreDolares.setEditable(false);

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("AMANECE GLOBAL");
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setBounds(1323, 82, 419, 29);

		lblTotalGanaciaDolares = new JIconTextField();
		contenedor.add(lblTotalGanaciaDolares);
		lblTotalGanaciaDolares.setText(String.valueOf(caja.getTotalGananciaDolares()));
		lblTotalGanaciaDolares.setIcon(new ImageIcon("img/usd.png"));
		lblTotalGanaciaDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalGanaciaDolares.setOrientation(SwingConstants.RIGHT);
		lblTotalGanaciaDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalGanaciaDolares.setBackground(new java.awt.Color(130,137,143));
		lblTotalGanaciaDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalGanaciaDolares.setOpaque(true);
		lblTotalGanaciaDolares.setEditable(false);
		lblTotalGanaciaDolares.setBounds(282, 803, 186, 46);

		lblNetoDolares = new JIconTextField();
		contenedor.add(lblNetoDolares);
		lblNetoDolares.setIcon(new ImageIcon("img/usd.png"));
		lblNetoDolares.setOrientation(SwingConstants.RIGHT);
		lblNetoDolares.setText(String.valueOf(caja.getTotalNetoDolares()));
		lblNetoDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNetoDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNetoDolares.setBackground(new java.awt.Color(130,137,143));
		lblNetoDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblNetoDolares.setOpaque(true);
		lblNetoDolares.setEditable(false);
		lblNetoDolares.setBounds(505, 803, 186, 46);

		lblTotalEgresoDolares = new JIconTextField();
		contenedor.add(lblTotalEgresoDolares);
		lblTotalEgresoDolares.setIcon(new ImageIcon("img/usd.png"));
		lblTotalEgresoDolares.setText(String.valueOf(caja.getTotalEgresosDolares()));
		lblTotalEgresoDolares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalEgresoDolares.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblTotalEgresoDolares.setBackground(new java.awt.Color(130,137,143));
		lblTotalEgresoDolares.setForeground(new java.awt.Color(255, 255, 255));
		lblTotalEgresoDolares.setOpaque(true);
		lblTotalEgresoDolares.setOrientation(SwingConstants.RIGHT);
		lblTotalEgresoDolares.setBounds(1009, 803, 186, 46);
		lblTotalEgresoDolares.setEditable(false);

		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ImprimirCaja();
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnGrabarIngreso.isVisible() || btnGrabarEgreso.isVisible()) {
					int opc = JOptionPane.showConfirmDialog(null,
							"<html><h2>Si cierra esta ventana perder� los cambios pendientes a grabar. �Desea salir de todos modos?</h2></html>",
							"Confirmaci�n", JOptionPane.YES_NO_OPTION);
					if (opc == JOptionPane.YES_OPTION) {
						Cerrar();
					}
				} else {
					Cerrar();
				}
			}
		});

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
			Constantes.IngresoModel
					.addRow(new Object[] { i.getDescripcion(), i.getTipo(), i.getCapital(), i.getGanancia(),
							i.getOtro(), i.getCapital().add(i.getGanancia()).add(i.getOtro()), i.getMoneda() });
		}
		tbIngresos.setModel(Constantes.IngresoModel);
	}

	public void CargarEgresos() {
		Constantes.EgresoModel.setRowCount(0);
		for (Egreso e : caja.getEgresos()) {
			Constantes.EgresoModel
					.addRow(new Object[] { e.getDescripcion(), e.getTipo(), e.getImporte(), e.getMoneda() });
		}
		tbEgresos.setModel(Constantes.EgresoModel);
	}

	public void Cerrar() {
		this.dispose();
	}

	public void ImprimirCaja() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SEDE", Principal.SEDE.getDescripcion());
		ArrayList<LibroCaja> arreglo_caja = new ArrayList<LibroCaja>();
		arreglo_caja.add(caja);
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/reporte_caja_diaria.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
					new JRBeanCollectionDataSource(arreglo_caja));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
