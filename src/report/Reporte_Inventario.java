package report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Utils.ComboItem;
import Utils.Constantes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Beans.Contrato_Inventario;
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
public class Reporte_Inventario extends JInternalFrame {

	private JPanel contenedor;
	private JComboBox cboTipos;
	private JComboBox cboEstado;
	private JButton btnOk;
	private JLabel jLabel1;
	private DefaultComboBoxModel modelo = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel();
	String filtro="";
	ComboItem i1 = new ComboItem("","ELECTRO");
	ComboItem i2 = new ComboItem("","ORO");
	
	public Reporte_Inventario() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(601, 113);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(784, 99));
		this.setBounds(0, 0, 784, 99);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 782, 75);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		
		cboEstado = new JComboBox();
		cboEstado.setBounds(373, 19, 227, 29);
		cboEstado.setFont(new java.awt.Font("Segoe UI",1,20));
		cboEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		cboTipos = new JComboBox(modelo);
		contenedor.add(cboTipos);
		cboTipos.setBounds(200, 19, 161, 29);
		cboTipos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboTipos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
				
		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("SELECCIONE TIPO:");
		jLabel1.setBounds(12, 19, 207, 29);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		btnOk = new JButton();
		contenedor.add(btnOk);
		contenedor.add(cboEstado);
		btnOk.setText("BUSCAR");
		btnOk.setBounds(622, 20, 143, 29);
		btnOk.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnOk.setFont(new java.awt.Font("Segoe UI", 1, 18));
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarReporte();
			}
		});
		cargarEstados();
		modelo.addElement(i1);
		modelo.addElement(i2);
	}
	
	public void cargarEstados(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_estado_contrato WHERE id_est NOT IN(6,8,9,10,11,12)";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			ComboItem c0 = new ComboItem("","TODOS");
			modeloEstado.addElement(c0);
			while(rs.next()){
				ComboItem c = new ComboItem(" AND ec.id_est ="+rs.getInt("id_est"), rs.getString("des_est"));
				modeloEstado.addElement(c);
			}
			cboEstado.setModel(modeloEstado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Contrato_Inventario> listado(){
		Connection con = MySQLConexion.getConexion();
		List<Contrato_Inventario> lista = new ArrayList<Contrato_Inventario>();
		filtro = ((ComboItem)modeloEstado.getSelectedItem()).getValue();
		i1.setValue("SELECT c.id_con,c.p,ec.des_est,c.cap_con, dc.cantidad, CONCAT(a.des_art,SPACE(1),a.mar_art) AS 'ARTICULO',dc.tas_det_con FROM tb_contrato c INNER JOIN tb_detalle_contrato dc ON c.id_con = dc.tb_contrato_id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art INNER JOIN tb_estado_contrato ec ON ec.id_est = c.tb_estado_contrato_id_est WHERE tb_prestamo_cod_pre NOT IN ('C') AND ec.id_est NOT IN(6,8,9,10,11,12) "+filtro+" UNION SELECT c.id_con,c.p, ec.des_est,c.cap_con, dc.cantidad, CONCAT(a.des_art,SPACE(1),a.mar_art) AS 'ARTICULO',dc.tas_det_con FROM tb_contrato_manual c INNER JOIN tb_detalle_contrato_manual dc ON c.id_con = dc.tb_contrato_manual_id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art INNER JOIN tb_estado_contrato ec ON ec.id_est = c.tb_estado_contrato_id_est WHERE tb_prestamo_cod_pre NOT IN ('C') AND ec.id_est NOT IN(6,8,9,10,11,12) "+filtro);
		i2.setValue("SELECT c.id_con,c.p,ec.des_est,c.cap_con, dc.cantidad,CONCAT(a.des_art,SPACE(1),a.mar_art) AS 'ARTICULO',dc.tas_det_con FROM tb_contrato_oro c INNER JOIN tb_detalle_contrato_oro dc ON c.id_con = dc.tb_contrato_oro_id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art INNER JOIN tb_estado_contrato ec ON ec.id_est = c.tb_estado_contrato_id_est WHERE tb_prestamo_cod_pre IN ('C') AND ec.id_est NOT IN(6,8,9,10,11,12) "+filtro+" UNION SELECT c.id_con,c.p, ec.des_est,c.cap_con, dc.cantidad, CONCAT(a.des_art,SPACE(1),a.mar_art) AS 'ARTICULO',dc.tas_det_con FROM tb_contrato_manual c INNER JOIN tb_detalle_contrato_manual dc ON c.id_con = dc.tb_contrato_manual_id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art INNER JOIN tb_estado_contrato ec ON ec.id_est = c.tb_estado_contrato_id_est WHERE tb_prestamo_cod_pre IN ('C') AND ec.id_est NOT IN(6,8,9,10,11,12) "+filtro);
		
		try {
			String sql = ((ComboItem)cboTipos.getSelectedItem()).getValue();
			System.out.println(sql);
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Contrato_Inventario c = new Contrato_Inventario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getDouble(7));
				lista.add(c);
			}
			Collections.sort(lista);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return lista;
	}

	public void mostrarReporte() {
		try {			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("p", Constantes.SUCURSAL);
			try {
				JasperReport reporte = (JasperReport) JRLoader
						.loadObject("reporte_inventario.jasper");
				JasperPrint print = JasperFillManager.fillReport(reporte,
						parametros, new JRBeanCollectionDataSource(listado()));
				JasperViewer viewer = new JasperViewer(print, false);
				viewer.show();
				viewer.toFront();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
