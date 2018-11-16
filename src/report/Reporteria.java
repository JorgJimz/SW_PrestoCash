package report;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import common.Logger;
import common.MySQLConexion;
import common.Utiles;
import controller.ArticuloController;
import controller.ContratoController;
import controller.PrestamoController;
import controller.VentaController;
import model.EContrato;
import model.Prestamo;
import model.Separacion;
import model.Venta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.Principal;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings({ "serial", "deprecation" })
public class Reporteria extends JInternalFrame {

	private JPanel contenedor;
	private JPanel pnlTPrestamo;
	private DatePicker dcFin;
	private DatePicker dcInicio;
	private JPanel pnlFecha;
	private JButton btnGenerar;
	private JPanel pnlEContrato;
	private JButton btnReporteSeparacion;
	private JButton btnReporteRemates;
	private JButton btnReporteContrato;
	private JButton btnReporteVitrina;

	public Reporteria() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(1205, 467));
		this.setBounds(0, 0, 1205, 467);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1197, 443);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnReporteVitrina = new JButton(new ImageIcon("img/report.png"));
		contenedor.add(btnReporteVitrina);
		btnReporteVitrina.setText("REPORTE DE VITRINA");
		btnReporteVitrina.setOpaque(false);
		btnReporteVitrina.setBorderPainted(false);
		btnReporteVitrina.setContentAreaFilled(false);
		btnReporteVitrina.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteVitrina.setBounds(12, 93, 272, 70);
		btnReporteVitrina.setFont(new java.awt.Font("Segoe UI", 1, 12));
		btnReporteVitrina.setHorizontalAlignment(SwingConstants.LEFT);
		btnReporteVitrina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MostrarReporteVitrina();
			}
		});

		btnReporteContrato = new JButton(new ImageIcon("img/profit.png"));
		contenedor.add(btnReporteContrato);
		btnReporteContrato.setText("REPORTE DE CONTRATOS");
		btnReporteContrato.setOpaque(false);
		btnReporteContrato.setBorderPainted(false);
		btnReporteContrato.setContentAreaFilled(false);
		btnReporteContrato.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteContrato.setBounds(12, 12, 272, 70);
		btnReporteContrato.setFont(new java.awt.Font("Segoe UI", 1, 12));
		btnReporteContrato.setHorizontalAlignment(SwingConstants.LEFT);
		btnReporteContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlEContrato.setVisible(true);
			}
		});

		btnReporteRemates = new JButton(new ImageIcon("img/vender.png"));
		contenedor.add(btnReporteRemates);
		btnReporteRemates.setText("REPORTE DE REMATE");
		btnReporteRemates.setBounds(12, 174, 272, 70);
		btnReporteRemates.setOpaque(false);
		btnReporteRemates.setBorderPainted(false);
		btnReporteRemates.setContentAreaFilled(false);
		btnReporteRemates.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteRemates.setFont(new java.awt.Font("Segoe UI", 1, 12));
		btnReporteRemates.setHorizontalAlignment(SwingConstants.LEFT);
		btnReporteRemates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MostrarReporteRemate();
			}
		});

		btnReporteSeparacion = new JButton(new ImageIcon("img/separar.png"));
		contenedor.add(btnReporteSeparacion);
		btnReporteSeparacion.setText("REPORTE DE SEPARACIONES");
		btnReporteSeparacion.setBounds(12, 255, 272, 70);
		btnReporteSeparacion.setOpaque(false);
		btnReporteSeparacion.setBorderPainted(false);
		btnReporteSeparacion.setContentAreaFilled(false);
		btnReporteSeparacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 12));
		btnReporteSeparacion.setHorizontalAlignment(SwingConstants.LEFT);

		pnlTPrestamo = new JPanel(new GridLayout(3, 2, 10, 10));
		contenedor.add(pnlTPrestamo);
		pnlTPrestamo.setOpaque(false);
		pnlTPrestamo.setBounds(295, 116, 231, 209);
		pnlTPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		pnlTPrestamo.setBorder(BorderFactory.createTitledBorder(null, "FILTRADO POR TIPO DE CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		pnlEContrato = new JPanel(new GridLayout(3, 2, 10, 10));
		contenedor.add(pnlEContrato);
		pnlEContrato.setOpaque(false);
		pnlEContrato.setBounds(538, 116, 638, 209);
		pnlEContrato.setBorder(BorderFactory.createTitledBorder(null, "FILTRADO POR ESTADO CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		pnlFecha = new JPanel();
		contenedor.add(pnlFecha);
		pnlFecha.setBounds(295, 12, 881, 98);
		pnlFecha.setBorder(BorderFactory.createTitledBorder(null, "FILTRADO POR FECHA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		pnlFecha.setOpaque(false);
		pnlFecha.setLayout(null);
		DatePickerSettings dps = new DatePickerSettings();
		

		dcFin = new DatePicker(dps);
		pnlFecha.add(dcFin);
		dcFin.setFont(new java.awt.Font("Segoe UI", 1, 14));
		dcFin.setBounds(283, 29, 257, 56);
		dcFin.setBorder(BorderFactory.createTitledBorder(null, "FIN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 64, 128)));
		dcFin.setOpaque(false);
		dcFin.setForeground(new java.awt.Color(0, 0, 0));

		dcInicio = new DatePicker();
		dcInicio.setDate(LocalDate.now());
		dcInicio.setFont(new java.awt.Font("Segoe UI", 1, 14));
		pnlFecha.add(dcInicio);
		dcInicio.setBounds(16, 29, 257, 56);
		dcInicio.setBorder(BorderFactory.createTitledBorder(null, "INICIO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 64, 128)));
		dcInicio.setOpaque(false);
		dcInicio.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if ("date".equals(arg0.getPropertyName())) {
					dcFin.clear();
					dps.setDateRangeLimits(dcInicio.getDate(),LocalDate.now());
				}
			}
		});
		
		dps.setDateRangeLimits(null, LocalDate.now());

		btnGenerar = new JButton(new ImageIcon("img/settings.png"));
		contenedor.add(btnGenerar);
		btnGenerar.setOpaque(false);
		btnGenerar.setBorderPainted(false);
		btnGenerar.setToolTipText("GENERAR");
		btnGenerar.setContentAreaFilled(false);
		btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGenerar.setBounds(1106, 331, 70, 70);
		btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MostrarReporteContratos();
			}
		});

		btnReporteSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MostrarReporteSeparacion();
			}
		});

		CargarEstadosContrato();
		CargarPrestamos();

	}

	public void CargarEstadosContrato() {
		List<EContrato> l = new ContratoController().ListarEstadosContrato();
		l.forEach(ec -> {
			JCheckBox chk = new JCheckBox();
			chk.setFont(new java.awt.Font("Segoe UI", 1, 14));
			chk.setText(ec.getDescripcion());
			chk.setSize(80, 20);
			chk.setOpaque(false);
			pnlEContrato.add(chk);
		});
	}

	public void CargarPrestamos() {
		List<Prestamo> l = new PrestamoController().ListarPrestamos();
		l.forEach(ec -> {
			JCheckBox chk = new JCheckBox();
			chk.setFont(new java.awt.Font("Segoe UI", 1, 14));
			chk.setText(ec.getDescripcion());
			chk.setSize(80, 20);
			chk.setOpaque(false);
			pnlTPrestamo.add(chk);
		});
	}

	public Object[] ValidarEstadosSeleccionados() {
		boolean flag = false;
		List<String> lc = new ArrayList<String>();
		for (Component c : pnlEContrato.getComponents()) {
			if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					lc.add(((JCheckBox) c).getText());
					flag = true;
				}
			}
		}
		return new Object[] { flag, lc };
	}

	public Object[] ValidarPrestamosSeleccionados() {
		boolean flag = false;
		List<String> lp = new ArrayList<String>();
		for (Component c : pnlTPrestamo.getComponents()) {
			if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					lp.add(((JCheckBox) c).getText());
					flag = true;
				}
			}
		}
		return new Object[] { flag, lp };
	}

	public void MostrarReporteContratos() {
		try {
			Object[] o1 = ValidarEstadosSeleccionados();
			Object[] o2 = ValidarPrestamosSeleccionados();
			if (Boolean.parseBoolean(String.valueOf(o1[0])) && Boolean.parseBoolean(String.valueOf(o2[0]))) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("SEDE", Principal.SEDE.getDescripcion());
				params.put("ESTADOS", o1[1]);
				params.put("PRESTAMOS", o2[1]);
				params.put("FECHAS", GenerarFiltroFechas());
				JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptContrato.jasper");
				JasperPrint print = JasperFillManager.fillReport(reporte, params, MySQLConexion.getConexion());
				JasperViewer viewer = new JasperViewer(print, false);
				viewer.show();
				viewer.toFront();
			} else {
				Utiles.Mensaje(
						"Seleccione como mínimo un <b> Estado Contrato / Préstamo </b> para la emisión del reporte.",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JRException e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}

	public String GenerarFiltroFechas() {
		String criteria = null;
		if (Objects.nonNull(dcInicio.getDate()) && Objects.isNull(dcFin.getDate())) {
			criteria = " AND FECHA_CONTRATO = '" + String.valueOf(dcInicio.getDate()) + "'";
		} else if (Objects.nonNull(dcInicio.getDate()) && Objects.nonNull(dcFin.getDate())) {
			criteria = " AND FECHA_CONTRATO BETWEEN '" + String.valueOf(dcInicio.getDate()) + "' AND '"
					+ String.valueOf(dcFin.getDate()) + "'";
		} else if (Objects.isNull(dcInicio.getDate()) && Objects.nonNull(dcFin.getDate())) {
			Utiles.Mensaje("Selecciona correctamente la(s) fechas.", JOptionPane.WARNING_MESSAGE);
		} else {
			criteria = " ";
		}
		return criteria;
	}

	public void MostrarReporteRemate() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());

		List<Venta> remates = new VentaController().ListarVentas(dcInicio.getDate(), dcFin.getDate());
		List<Venta> separacionesFinalizadas = new VentaController()
				.ListarSeparaciones(Separacion.FINALIZADA, dcInicio.getDate(), dcFin.getDate()).stream().map(s -> {
					Venta v = new Venta();
					v.setArticulo(s.getArticulo());
					v.setFecha(s.getFechaCreacion());
					v.setCliente(s.getCliente());
					v.setImporte(s.getPrecioVenta());
					v.setModalidad("SEPARACIÓN FINALIZADA");
					return v;
				}).collect(Collectors.toList());

		HashSet<Object> seen = new HashSet<>();
		separacionesFinalizadas.removeIf(e -> !seen.add(e.getArticulo().getId()));

		remates.addAll(separacionesFinalizadas);

		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptVentas.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(remates));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}

	public void MostrarReporteSeparacion() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptSeparaciones.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params, new JRBeanCollectionDataSource(
					new VentaController().ListarSeparaciones(Separacion.ACTIVA, dcInicio.getDate(), dcFin.getDate())));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}

	public void MostrarReporteVitrina() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("DS_VITRINA", new JRBeanCollectionDataSource(new ArticuloController().CargarReporteVitrina()));
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptVitrina.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params, new JREmptyDataSource());
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}

}
