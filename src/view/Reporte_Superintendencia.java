package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Beans.SuperIntendencia;
import Conexion.MySQLConexion;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
public class Reporte_Superintendencia extends JInternalFrame {

	private JPanel contenedor;
	private JYearChooser dycAnio;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JCheckBox chkDesdeHasta;
	private JCheckBox chkMes;
	private JTextField txtHasta;
	private JTextField txtDesde;
	private JCheckBox chkAll;
	private JButton btnProcesar;
	private JMonthChooser dmcMes;
	DefaultTableModel modeloRemate = new DefaultTableModel(null, new String[] {
			"NÚMERO DE CONTRATO", "DESCRIPCIÓN", "CAPITAL", "PRECIO VENTA" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Reporte_Superintendencia() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(736, 107);
		this.setClosable(true);
		this.setTitle("REPORTE DE REMATES MENSUAL");
		this.setPreferredSize(new java.awt.Dimension(467, 280));
		this.setBounds(0, 0, 467, 280);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 466, 255);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		dmcMes = new JMonthChooser(false);			
		contenedor.add(dmcMes);
		dmcMes.setBounds(131, 65, 95, 36);		
		dmcMes.getComboBox().setFont(new java.awt.Font("Segoe UI",1,22));
		dmcMes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnProcesar = new JButton();
		contenedor.add(btnProcesar);
		btnProcesar.setText("PROCESAR");
		btnProcesar.setBounds(12, 173, 422, 53);
		btnProcesar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnProcesar.setFont(new java.awt.Font("Segoe UI",1,22));

		dycAnio = new JYearChooser();
		contenedor.add(dycAnio);
		dycAnio.setBounds(336, 69, 98, 31);
		dycAnio.setFont(new java.awt.Font("Segoe UI",1,22));
		dycAnio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("AÑO:");
		jLabel2.setForeground(new java.awt.Color(0,128,0));
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setBounds(238, 66, 98, 35);

		chkAll = new JCheckBox();
		contenedor.add(chkAll);
		chkAll.setText("TODOS");
		chkAll.setBounds(12, 13, 170, 35);
		chkAll.setOpaque(false);
		chkAll.setFont(new java.awt.Font("Segoe UI",1,22));
		chkAll.setForeground(new java.awt.Color(0,128,0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("HASTA");
		jLabel4.setBounds(241, 112, 95, 35);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel4.setForeground(new java.awt.Color(0,128,0));

		txtDesde = new JTextField();
		contenedor.add(txtDesde);
		txtDesde.setBounds(131, 113, 98, 33);
		txtDesde.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtDesde.setFont(new java.awt.Font("Segoe UI",1,22));

		txtHasta = new JTextField();
		contenedor.add(txtHasta);
		txtHasta.setBounds(336, 113, 98, 33);
		txtHasta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtHasta.setFont(new java.awt.Font("Segoe UI",1,22));

		chkMes = new JCheckBox();
		contenedor.add(chkMes);
		chkMes.setText("MES:");
		chkMes.setBounds(12, 66, 114, 35);
		chkMes.setOpaque(false);
		chkMes.setFont(new java.awt.Font("Segoe UI",1,22));
		chkMes.setForeground(new java.awt.Color(0,128,0));

		chkDesdeHasta = new JCheckBox();
		contenedor.add(chkDesdeHasta);
		chkDesdeHasta.setText("DESDE:");
		chkDesdeHasta.setBounds(12, 112, 114, 35);
		chkDesdeHasta.setOpaque(false);
		chkDesdeHasta.setFont(new java.awt.Font("Segoe UI",1,22));
		chkDesdeHasta.setForeground(new java.awt.Color(0,128,0));

		btnProcesar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					mostrarReporte();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Seleccione un tipo de filtrado...");
				}
			}
		});

	}

	public void mostrarReporte() throws SQLException {
		int primero=1, segundo=1;
		StringBuilder sql = new StringBuilder("SELECT CONCAT(ct.p,'-',ct.id_con) as 'contrato',"
				+ "DATE_FORMAT(ct.fec_con, '%d-%b-%y') as 'fecha',"
				+ "CONCAT(c.nom_cli,SPACE(1),pat_cli,SPACE(1),mat_cli) as 'cliente', "
				+ "c.doc_cli as 'dni',"
				+ "c.dir_cli as'direccion',"
				+ "c.mo1_cli as'movil',"
				+ "CONCAT(dc.cantidad,SPACE(1),des_art,SPACE(1),mar_art,SPACE(1),mod_art) as 'producto',"
				+ "ct.cap_con as 'capital',"
				+ "int_con as 'interes' FROM tb_contrato ct INNER JOIN tb_cliente c ON ct.tb_cliente_doc_cli=c.doc_cli INNER JOIN tb_detalle_contrato dc ON dc.tb_contrato_id_con=ct.id_con INNER JOIN tb_articulo a ON a.cod_art=dc.tb_articulo_cod_art");
		
		if(chkAll.isSelected()){
			sql.append(" WHERE ?=?");
		}
		if(chkMes.isSelected()){
			sql.append(" WHERE MONTH(ct.fec_con)=? AND YEAR(ct.fec_con)=?");
			primero = dmcMes.getMonth()+1;
			segundo = dycAnio.getYear();
		}
		if(chkDesdeHasta.isSelected()){
			sql.append(" WHERE ct.cap_con BETWEEN ? AND ?");
			primero = Integer.parseInt(txtDesde.getText());
			segundo = Integer.parseInt(txtHasta.getText());
		}		
		Connection con = MySQLConexion.getConexion();		
		try {
			String mysql = sql.toString();
			PreparedStatement pst = con.prepareStatement(mysql);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);
			pst.setInt(1, primero);
			pst.setInt(2, segundo);
			ResultSet rs = pst.executeQuery();
			ArrayList<SuperIntendencia> listado = new ArrayList<SuperIntendencia>();			
			while(rs.next()){
				SuperIntendencia e = new SuperIntendencia(rs.getString("contrato"), rs.getString("fecha"), rs.getString("dni"), rs.getString("movil"), rs.getString("cliente"), rs.getString("direccion"), rs.getString("producto"), rs.getDouble("capital"), rs.getDouble("interes"));
				listado.add(e);
			}			
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_superintendencia.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					null, new JRBeanCollectionDataSource(listado));
			JasperViewer viewer = new JasperViewer(print, false);			
			viewer.show();			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione un tipo de filtrado.");
		}
	}	
}
