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

import common.Logger;
import common.MySQLConexion;
import controller.ArticuloController;
import controller.ContratoController;
import model.EContrato;
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
		this.setPreferredSize(new java.awt.Dimension(1146, 234));
		this.setBounds(0, 0, 1146, 234);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1144, 209);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		pnlEContrato = new JPanel(new GridLayout(3, 2, 10, 10));
		contenedor.add(pnlEContrato);
		pnlEContrato.setBounds(439, 12, 693, 185);
		pnlEContrato.setVisible(false);

		btnReporteVitrina = new JButton(new ImageIcon("img/report.png"));
		contenedor.add(btnReporteVitrina);
		btnReporteVitrina.setText("VITRINA");
		btnReporteVitrina.setOpaque(false);
		btnReporteVitrina.setBorderPainted(false);
		btnReporteVitrina.setContentAreaFilled(false);
		btnReporteVitrina.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteVitrina.setBounds(12, 12, 198, 70);
		btnReporteVitrina.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnReporteVitrina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MostrarReporteVitrina();
			}
		});

		btnReporteContrato = new JButton(new ImageIcon("img/profit.png"));
		contenedor.add(btnReporteContrato);
		btnReporteContrato.setText("CONTRATOS");
		btnReporteContrato.setOpaque(false);
		btnReporteContrato.setBorderPainted(false);
		btnReporteContrato.setContentAreaFilled(false);
		btnReporteContrato.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteContrato.setBounds(11, 87, 198, 70);
		btnReporteContrato.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnReporteContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlEContrato.setVisible(true);
			}
		});

		btnReporteRemates = new JButton(new ImageIcon("img/vender.png"));
		contenedor.add(btnReporteRemates);
		btnReporteRemates.setText("REMATE");
		btnReporteRemates.setBounds(215, 12, 198, 70);
		btnReporteRemates.setOpaque(false);
		btnReporteRemates.setBorderPainted(false);
		btnReporteRemates.setContentAreaFilled(false);
		btnReporteRemates.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteRemates.setFont(new java.awt.Font("Segoe UI", 1, 14));

		btnReporteSeparacion = new JButton(new ImageIcon("img/separar.png"));
		contenedor.add(btnReporteSeparacion);
		btnReporteSeparacion.setText("SEPARACIONES");
		btnReporteSeparacion.setBounds(214, 87, 198, 70);
		btnReporteSeparacion.setOpaque(false);
		btnReporteSeparacion.setBorderPainted(false);
		btnReporteSeparacion.setContentAreaFilled(false);
		btnReporteSeparacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 14));

		CargarEstadosContrato();

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
		JButton btn = new JButton("GENERAR");
		pnlEContrato.add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MostrarReporteContratos();
			}
		});
	}

	public void MostrarReporteContratos() {
		try {
			List<String> l = new ArrayList<String>();
			for (Component c : pnlEContrato.getComponents()) {
				if (c instanceof JCheckBox) {
					if (((JCheckBox) c).isSelected()) {
						l.add(((JCheckBox) c).getText());
					}
				}
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ESTADOS", l);
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
