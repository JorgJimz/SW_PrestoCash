package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Actualizar_Contratos extends JInternalFrame {

	private JPanel contenedor;
	private JScrollPane jScrollPane1;	
	private JProgressBar dobar;
	DefaultTableModel modeloContrato = new DefaultTableModel(null,
			new String[] { "NÚMERO DE CONTRATO", "INICIO", "VENCIMIENTO",
					"REMATE", "ESTADO ACTUAL" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
		@Override
		public Class getColumnClass(int columna){				
			return Integer.class;
		}		
	};
	private JButton btnActualizar;
	private JTable tbContratos;

	public Actualizar_Contratos() {
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
		jScrollPane1.setBounds(12, 12, 1226, 644);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.setModel(modeloContrato);
		tbContratos.setDefaultRenderer(Object.class, new RenderCC());
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.setRowHeight(35);
		tbContratos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloContrato);
		tbContratos.setRowSorter(sorter);		
		btnActualizar = new JButton(new ImageIcon("img/update_contratos.png"));
		contenedor.add(btnActualizar);
		btnActualizar.setText("ACTUALIZAR CONTRATOS");
		btnActualizar.setBounds(12, 673, 728, 71);
		btnActualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnActualizar.setBackground(new java.awt.Color(192,192,192));
		dobar = new JProgressBar();
		contenedor.add(dobar);
		dobar.setBounds(752, 673, 486, 71);
		dobar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Runnable miRunnable = new Runnable() {
					public void run() {
						actualizarContratos();						
					}
				};
				btnActualizar.setEnabled(false);
				btnActualizar.setText("PROCESANDO LA PETICIÓN, ESPERE POR FAVOR...");
				Thread hilo = new Thread(miRunnable);
				hilo.start();				
				dobar.setIndeterminate(true);
			}
		});
		cargarContratos();
	}

	/*public void cargarContratos() {
		try {
			String sql = "SELECT * FROM tb_contrato_oro c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12) UNION ALL SELECT * FROM tb_contrato_manual c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12) UNION ALL SELECT * FROM tb_contrato c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12)";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			modeloContrato.setRowCount(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			while (rs.next()) {
				modeloContrato.addRow(new Object[] { rs.getInt("id_con"),
						sdf.format(rs.getDate("fec_con")).toUpperCase(),
						sdf.format(rs.getDate("fec_ven_con")).toUpperCase(),
						sdf.format(rs.getDate("fec_rem_con")).toUpperCase(),
						rs.getString("des_est") });
			}			
			tbContratos.setModel(modeloContrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public String[] arrayTablas(int id_contrato) {
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
		}
		return arrayTablas;
	}

	public void cambiarToVencido(int codigo, String tabla) {
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=2 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cambiarToActivo(int codigo, String tabla) {
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=1 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cambiarToRemate(int codigo, String tabla, String tabla2) {
		try {
			/*ANTES EST_ART=2 y EST_CON=5*/
			String sql = "UPDATE tb_articulo a INNER JOIN "
					+ tabla2
					+ " dc ON a.cod_art = dc.tb_articulo_cod_art INNER JOIN "
					+ tabla
					+ " c ON c.id_con=dc."
					+ tabla
					+ "_id_con SET tb_estado_articulo_cod_est=3, tb_estado_contrato_id_est=13, id_con_asoc="+tabla+"_id_con WHERE "
					+ tabla + "_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cambiarToPRERiesgo(int codigo, String tabla) {
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=4 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cambiarToPOSTRiesgo(int codigo, String tabla) {
		try {
			String sql = "UPDATE " + tabla
					+ " SET tb_estado_contrato_id_est=7 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizarContratos() {
		try {
			int contador = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String cadena = sdf.format(new Date());
			Date hoy = sdf.parse(cadena);
			String sql = "SELECT * FROM tb_contrato c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12)   UNION ALL SELECT * FROM tb_contrato_manual c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12)   UNION ALL SELECT * FROM tb_contrato_oro c INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE tb_estado_contrato_id_est NOT IN(6,9,10,11,12)  ";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			GregorianCalendar gcPre = new GregorianCalendar();
			GregorianCalendar gcPost = new GregorianCalendar();
			GregorianCalendar gcRem = new GregorianCalendar();
			while (rs.next()) {
				/* RANGO DE RIESGO */
				gcPre.setTime(sdf.parse(rs.getString(/*"fec_rem_con"*/"fec_ven_con")));
				gcPost.setTime(sdf.parse(rs.getString(/*"fec_rem_con"*/"fec_ven_con")));
				gcRem.setTime(sdf.parse(rs.getString(/*"fec_rem_con"*/"fec_rem_con")));
				if(rs.getInt("id_con")==3171){System.out.println(sdf.parse(rs.getString(/*"fec_rem_con"*/"fec_ven_con")));}
				gcPre.add(Calendar.MONTH, 1);
				gcPre.add(Calendar.DATE, -5);
				gcPost.add(Calendar.MONTH, 1);
				gcPost.add(Calendar.DATE, 15);
				if(rs.getInt("id_con")==3171){System.out.println(gcPre.getTime());}
				if(rs.getInt("id_con")==3171){System.out.println(gcPost.getTime());}
				Date periodoPreRiesgo = gcPre.getTime();
				Date periodoPostRiesgo = gcPost.getTime();
				
				/*
				 * Al siguiente día de cumplirse el plazo POST, el contrato entra a estado de
				 * VITRINA(Antes:RIESGO)
				 */
				if (hoy.after(periodoPostRiesgo)) {
					String tabla[] = arrayTablas(rs.getInt("id_con"));
					cambiarToRemate(rs.getInt("id_con"), tabla[0], tabla[1]);
					// JOptionPane.showMessageDialog(null, "to remate");
					contador++;
				}			
				
				/*
				 * 15 días después de su remate, el contrato entra a estado de
				 * Antes:POST- riesgo
				 */
				
				else if (hoy.after(gcRem.getTime()) && hoy.before(periodoPostRiesgo) || hoy.equals(periodoPostRiesgo)
						/*&& hoy.getTime() > sdf.parse(
								rs.getString("fec_rem_con")).getTime()*/) {
					String tabla[] = arrayTablas(rs.getInt("id_con"));
					cambiarToPOSTRiesgo(rs.getInt("id_con"), tabla[0]);
					contador++;
					// JOptionPane.showMessageDialog(null, "to post");
				}				

				/*
				 * 5 días antes de su remate, el contrato entra a estado de PRE
				 * - riesgo
				 */
				else if (hoy.after(periodoPreRiesgo) || hoy.equals(periodoPreRiesgo) && hoy.before(gcRem.getTime()) || hoy.equals(gcRem)
						/*&& hoy.getTime() < sdf.parse(
								rs.getString("fec_rem_con")).getTime()*/) {
					String tabla[] = arrayTablas(rs.getInt("id_con"));
					cambiarToPRERiesgo(rs.getInt("id_con"), tabla[0]);
					contador++;
					// JOptionPane.showMessageDialog(null, "to pre");
				}
				
				else if (hoy.after(sdf.parse(rs.getString("fec_ven_con")))) {
					String tabla[] = arrayTablas(rs.getInt("id_con"));
					cambiarToVencido(rs.getInt("id_con"), tabla[0]);
					contador++;
					// JOptionPane.showMessageDialog(null, "vencido");
				}
				
				else {
					String tabla[] = arrayTablas(rs.getInt("id_con"));
					cambiarToActivo(rs.getInt("id_con"), tabla[0]);
					contador++;
				}							
			}
			cargarContratos();
			dobar.setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "<html><h2>Contratos actualizados: "
					+ contador+"</h2></html>");
			btnActualizar.setEnabled(true);
			btnActualizar.setText("ACTUALIZAR CONTRATOS");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
