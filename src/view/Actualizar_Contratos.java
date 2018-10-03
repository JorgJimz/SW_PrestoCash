package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.Constantes;
import common.RenderCC;
import controller.ContratoController;
import model.Contrato;
import model.DetalleContrato;
import model.EContrato;

@SuppressWarnings("serial")
public class Actualizar_Contratos extends JInternalFrame {

	private JPanel contenedor;
	private JLabel lblTotalContrato;
	private JScrollPane jScrollPane1;
	private JProgressBar dobar;

	private JButton btnActualizar;
	private JTable tbContratos;

	List<Contrato> l;

	public Actualizar_Contratos() {
		l = new ContratoController().ListarContratosVigentes();

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1287, 800);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(1282, 597));
		this.setBounds(0, 0, 1282, 597);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1280, 572);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 21, 1226, 452);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.setModel(Constantes.ActualizacionModel);
		tbContratos.setDefaultRenderer(Object.class, new RenderCC());
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.setRowHeight(25);
		tbContratos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Constantes.ActualizacionModel);
		tbContratos.setRowSorter(sorter);
		btnActualizar = new JButton(new ImageIcon("img/actualizar_contratos.png"));
		contenedor.add(btnActualizar);
		btnActualizar.setText("ACTUALIZAR CONTRATOS");
		btnActualizar.setBounds(12, 479, 282, 64);
		btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 16));
		btnActualizar.setBackground(new java.awt.Color(192, 192, 192));
		btnActualizar.setOpaque(false);
		btnActualizar.setBorderPainted(false);
		btnActualizar.setContentAreaFilled(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnActualizar.setHorizontalAlignment(SwingConstants.LEFT);

		dobar = new JProgressBar();
		contenedor.add(dobar);
		dobar.setBounds(300, 479, 938, 31);
		dobar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		lblTotalContrato = new JLabel("TOTAL: " + l.stream().count() + " CONTRATOS VIGENTES.");
		contenedor.add(lblTotalContrato);
		lblTotalContrato.setBounds(300, 516, 938, 27);
		lblTotalContrato.setForeground(new java.awt.Color(0, 128, 0));
		lblTotalContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblTotalContrato.setHorizontalAlignment(SwingConstants.RIGHT);

		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable miRunnable = new Runnable() {
					public void run() {
						ActualizarContratos();
					}
				};
				btnActualizar.setEnabled(false);
				btnActualizar.setText("PROCESANDO LA PETICIÓN, ESPERE POR FAVOR...");
				Thread hilo = new Thread(miRunnable);
				hilo.start();
				dobar.setIndeterminate(true);
			}
		});

		ListarContratosVigentes();
	}

	public void ListarContratosVigentes() {
		Constantes.ActualizacionModel.setRowCount(0);
		for (Contrato c : l) {
			Constantes.ActualizacionModel.addRow(new Object[] { c.getFlag() + "-" + c.getNumero(), c.getFechaContrato(),
					c.getFechaVencimiento(), c.getFechaRemate(), c.getEContrato().getDescripcion() });
		}
		tbContratos.setModel(Constantes.ActualizacionModel);
	}

	public void ActualizarContratos() {
		try {
			for (Contrato c : l) {
				DetectarEstado(c);
			}
			new ContratoController().ActualizarContratos(l);
			ListarContratosVigentes();
			dobar.setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "<html><h2>Contratos actualizados.</h2></html>");
			btnActualizar.setEnabled(true);
			btnActualizar.setText("ACTUALIZAR CONTRATOS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void DetectarEstado(Contrato c) {
		LocalDate hoy = LocalDate.now();
		LocalDate gcPre = LocalDate.parse(c.getFechaVencimiento()).plusMonths(1).minusDays(5);
		LocalDate gcPost = LocalDate.parse(c.getFechaVencimiento()).plusMonths(1).plusDays(15);
		LocalDate gcRem = LocalDate.parse(c.getFechaRemate());

		if (hoy.isAfter(gcPost)) {
			c.setEContrato(new EContrato(13, "VITRINA (SP)"));
			for (DetalleContrato dc : c.getDetalleContratos()) {
				dc.getArticulo().getEArticulo().setId(5);
				dc.getArticulo().setContrato(c.getFlag() + "-" + c.getNumero());
				dc.getArticulo().setCapitalContrato(c.getCapital());
				dc.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
				dc.getArticulo().setUsuarioModificacion("UPDATING");
			}
		}

		else if (hoy.isAfter(gcRem) && hoy.isBefore(gcPost) || hoy.isEqual(gcPost)) {
			c.setEContrato(new EContrato(7, "POST"));
		}

		else if (hoy.isAfter(gcPre) || hoy.isEqual(gcPre) && hoy.isBefore(gcRem) || hoy.isEqual(gcRem)) {
			c.setEContrato(new EContrato(4, "PRE"));
		}

		else if (hoy.isAfter(LocalDate.parse(c.getFechaVencimiento()))
				&& !hoy.isEqual(LocalDate.parse(c.getFechaVencimiento()))) {
			c.setEContrato(new EContrato(2, "VENCIDO"));
		}

		else {
			c.setEContrato(new EContrato(1, "ACTIVO"));
		}
	}

}
