package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTitledSeparator;

import Utils.Constantes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Conexion.MySQLConexion;

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
public class Consultar_Cargo_Anterior extends JInternalFrame {

	private JPanel contenedor;
	private JTable tbCargos;
	private JScrollPane jScrollPane1;
	private JButton btnReporteCargo;
	private JButton btnConsultar;
	private JTextField txtNumero;
	private JLabel jLabel1;
	private JXTitledSeparator jSeparator1;
	DefaultTableModel modeloCargo = new DefaultTableModel(null, new String[] {
			"N° CRG", "FECHA", "N° CNT", "ARTICULO", "DESTINO", "E/S" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Consultar_Cargo_Anterior() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(662, 436);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(855, 744));
		this.setBounds(0, 0, 855, 744);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 853, 719);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jSeparator1 = new JXTitledSeparator("CONSULTAR CARGO ANTERIOR");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 12, 829, 39);
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("NUMERO DE CONTRATO:");
		jLabel1.setBounds(12, 63, 252, 30);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		txtNumero = new JTextField();
		contenedor.add(txtNumero);
		txtNumero.setBounds(261, 62, 299, 31);
		txtNumero.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtNumero.setFont(new java.awt.Font("Segoe UI",1,22));
		txtNumero.setForeground(new java.awt.Color(0,64,128));

		btnConsultar = new JButton(new ImageIcon("img/buscar_cargo.png"));
		contenedor.add(btnConsultar);
		btnConsultar.setBounds(570, 54, 61, 49);
		btnConsultar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnConsultar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listarCargos();				
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 111, 829, 590);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		btnReporteCargo = new JButton(new ImageIcon("img/ojo.png"));
		contenedor.add(btnReporteCargo);
		btnReporteCargo.setText("VER CARGO");
		btnReporteCargo.setBounds(637, 54, 204, 49);
		btnReporteCargo.setFont(new java.awt.Font("Segoe UI",1,20));
		btnReporteCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnReporteCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = tbCargos.getSelectedRow();
				mostrarReporteCargo(Integer.parseInt(modeloCargo.getValueAt(row, 0).toString()));
			}
		});

		tbCargos = new JTable(modeloCargo);
		jScrollPane1.setViewportView(tbCargos);
		tbCargos.setRowHeight(30);
		tbCargos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbCargos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbCargos.getTableHeader().setForeground(new Color(181, 0, 0));

	}

	public void listarCargos() {
		Connection con = MySQLConexion.getConexion();
		try {			
			String sql = "SELECT c.id_car, fec_car, nro_con, CONCAT(des_art,SPACE(1),mar_art) as 'des', des_car FROM tb_cargo c INNER JOIN tb_detalle_cargo dc ON c.id_car =dc.id_car INNER JOIN tb_articulo a ON dc.cod_art = a.cod_art WHERE nro_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txtNumero.getText()));
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String condicion = (rs.getString("des_car").equalsIgnoreCase(Constantes.NOMBRE_SUCURSAL))? "ENTRADA":"SALIDA";
				modeloCargo.addRow(new Object[]{rs.getInt("id_car"),new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("fec_car")),rs.getInt("nro_con"),rs.getString("des"),rs.getString("des_car"),condicion});
			}
			tbCargos.setModel(modeloCargo);
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}

	public void mostrarReporteCargo(int id) {
		Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("p", Constantes.NOMBRE_SUCURSAL);
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("reporte_cargo_anterior.jasper");
			JasperPrint print = JasperFillManager.fillReport(reporte,
					parametros, con);
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
