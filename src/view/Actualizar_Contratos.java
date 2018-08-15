package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import model.Contrato;
import model.EContrato;

import common.Constantes;
import common.RenderCC;

import controller.ContratoController;

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

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1271, 800);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 41, 1226, 615);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.setModel(Constantes.ActualizacionModel);
		tbContratos.setDefaultRenderer(Object.class, new RenderCC());
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.setRowHeight(35);
		tbContratos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				Constantes.ActualizacionModel);
		tbContratos.setRowSorter(sorter);
		btnActualizar = new JButton(new ImageIcon("img/update_contratos.png"));
		contenedor.add(btnActualizar);
		btnActualizar.setText("ACTUALIZAR CONTRATOS");
		btnActualizar.setBounds(12, 673, 728, 71);
		btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnActualizar.setBackground(new java.awt.Color(192, 192, 192));
		dobar = new JProgressBar();
		contenedor.add(dobar);
		dobar.setBounds(752, 673, 486, 71);
		dobar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		lblTotalContrato = new JLabel("TOTAL: " + l.stream().count()
				+ " CONTRATOS VIGENTES.");
		contenedor.add(lblTotalContrato);
		lblTotalContrato.setBounds(752, 6, 486, 27);
		lblTotalContrato.setForeground(new java.awt.Color(0, 128, 0));
		lblTotalContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		lblTotalContrato.setHorizontalAlignment(SwingConstants.RIGHT);

		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Runnable miRunnable = new Runnable() {
					public void run() {
						actualizarContratos();
					}
				};
				btnActualizar.setEnabled(false);
				btnActualizar
						.setText("PROCESANDO LA PETICIÓN, ESPERE POR FAVOR...");
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
			Constantes.ActualizacionModel.addRow(new Object[] {
					c.getFlag() + "-" + c.getNumero(), c.getFechaContrato(),
					c.getFechaVencimiento(), c.getFechaRemate(),
					c.getEContrato().getDescripcion() });
		}
		tbContratos.setModel(Constantes.ActualizacionModel);
	}

	public void actualizarContratos() {
		try {
			int k = 0;
			Date hoy = Constantes.formatoSQL.parse(Constantes.formatoSQL
					.format(new Date()));
			GregorianCalendar gcPre = new GregorianCalendar();
			GregorianCalendar gcPost = new GregorianCalendar();
			GregorianCalendar gcRem = new GregorianCalendar();

			for (Contrato c : l) {
				gcPre.setTime(Constantes.formatoSQL.parse(c
						.getFechaVencimiento()));
				gcPost.setTime(Constantes.formatoSQL.parse(c
						.getFechaVencimiento()));
				gcRem.setTime(Constantes.formatoSQL.parse(c.getFechaRemate()));

				gcPre.add(Calendar.MONTH, 1);
				gcPre.add(Calendar.DATE, -5);
				gcPost.add(Calendar.MONTH, 1);
				gcPost.add(Calendar.DATE, 15);

				Date periodoPreRiesgo = gcPre.getTime();
				Date periodoPostRiesgo = gcPost.getTime();

				/*
				 * Al siguiente día de cumplirse el plazo POST, el contrato
				 * entra a estado de VITRINA(Antes:RIESGO)
				 */
				if (hoy.after(periodoPostRiesgo)) {
					c.setEContrato(new EContrato(13, "VITRINA (SP)"));
					k++;
				}

				else if (hoy.after(gcRem.getTime())
						&& hoy.before(periodoPostRiesgo)
						|| hoy.equals(periodoPostRiesgo)) {
					c.setEContrato(new EContrato(7, "POST"));
					k++;
				}

				else if (hoy.after(periodoPreRiesgo)
						|| hoy.equals(periodoPreRiesgo)
						&& hoy.before(gcRem.getTime()) || hoy.equals(gcRem)) {
					c.setEContrato(new EContrato(4, "PRE"));
					k++;
				}

				else if (hoy.after(Constantes.formatoSQL.parse(c
						.getFechaVencimiento()))
						&& !hoy.equals(Constantes.formatoSQL.parse(c
								.getFechaVencimiento()))) {
					c.setEContrato(new EContrato(2, "VENCIDO"));
					k++;
				}

				else {
					c.setEContrato(new EContrato(1, "ACTIVO"));					
				}
			}
			new ContratoController().ActualizarContratos(l);
			ListarContratosVigentes();
			dobar.setIndeterminate(false);
			JOptionPane.showMessageDialog(null,
					"<html><h2>Contratos actualizados: " + k
							+ "</h2></html>");
			btnActualizar.setEnabled(true);
			btnActualizar.setText("ACTUALIZAR CONTRATOS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
