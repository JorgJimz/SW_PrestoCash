package report;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.Logger;
import common.MySQLConexion;
import controller.ArticuloController;
import controller.ContratoController;
import controller.PrestamoController;
import controller.VentaController;
import model.EContrato;
import model.Prestamo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.Principal;

@SuppressWarnings({ "serial", "deprecation" })
public class Reporteria extends JInternalFrame {

	private JPanel contenedor;
	private JPanel pnlTPrestamo;
	private JButton btnGenerar;
	private JPanel pnlEContrato;
	private JPanel pnlFiltro;
	private JButton btnReporteSeparacion;
	private JButton btnReporteRemates;
	private JButton btnReporteContrato;
	private JButton btnReporteVitrina;

	public Reporteria() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(1146, 467));
		this.setBounds(0, 0, 1146, 467);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1144, 443);
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

		pnlFiltro = new JPanel();
		contenedor.add(pnlFiltro);
		pnlFiltro.setBounds(295, 12, 650, 300);

		pnlTPrestamo = new JPanel(new GridLayout(3, 2, 10, 10));
		pnlFiltro.add(pnlTPrestamo);
		pnlTPrestamo.setPreferredSize(new java.awt.Dimension(650, 130));
		pnlTPrestamo.setBackground(new java.awt.Color(128, 255, 128));
		pnlTPrestamo.setOpaque(false);

		pnlEContrato = new JPanel(new GridLayout(3, 2, 10, 10));
		pnlFiltro.add(pnlEContrato);
		pnlEContrato.setPreferredSize(new java.awt.Dimension(650, 130));
		pnlEContrato.setBackground(new java.awt.Color(255, 255, 128));
		pnlEContrato.setOpaque(false);

		btnGenerar = new JButton();
		pnlFiltro.add(btnGenerar);
		btnGenerar.setText("PROCESAR");
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
			pnlTPrestamo.add(chk);
		});
	}

	public void MostrarReporteContratos() {
		try {
			List<String> lc = new ArrayList<String>();
			for (Component c : pnlEContrato.getComponents()) {
				if (c instanceof JCheckBox) {
					if (((JCheckBox) c).isSelected()) {
						lc.add(((JCheckBox) c).getText());
					}
				}
			}

			List<String> lp = new ArrayList<String>();
			for (Component c : pnlTPrestamo.getComponents()) {
				if (c instanceof JCheckBox) {
					if (((JCheckBox) c).isSelected()) {
						lp.add(((JCheckBox) c).getText());
					}
				}
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("SEDE", Principal.SEDE.getDescripcion());
			params.put("ESTADOS", lc);
			params.put("PRESTAMOS", lp);
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/reporte_contratos.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte, params, MySQLConexion.getConexion());
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.show();
			viewer.toFront();
		} catch (JRException e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}

	}

	public void MostrarReporteRemate() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptVentas.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(new VentaController().ListarVentas()));
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
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/reporte_separaciones.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(new VentaController().ListarSeparaciones()));
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
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/reporte_vitrina.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(new ArticuloController().CargarReporteVitrina()));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}

}
