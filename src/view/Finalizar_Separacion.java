package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import Beans.Auditoria;
import Beans.Ingreso;
import Beans.Venta;
import Conexion.MySQLConexion;


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
public class Finalizar_Separacion extends JInternalFrame{
	
	
	private JPanel contenedor;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel lblSaldo;
	private JLabel lblACuenta;
	private JLabel lblPrecioVenta;
	private JLabel lblArticulo;
	private JLabel lblCliente;
	private JLabel lblIdSeparacion;
	private JButton btnGrabar;
	private JLabel jLabel6;
	private JLabel jLabel1;
	private Connection con = MySQLConexion.getConexion();
	private JLabel lblP;
	private JLabel jLabel8;
	private JLabel lblDirCli;
	private JLabel lblTlfCliente;
	private JTextArea txtObs;
	private JScrollPane jScrollPane1;
	private JLabel lblGanancia;
	private JLabel lblCapital;
	private JLabel lblCodigoArticulo;
	private JLabel lblDNICliente;
	private JLabel lblFechaSeparacion;
	private JLabel jLabel7;
	private int id_separacion;
	Venta vt = null;
	public Finalizar_Separacion(String separacion) {
		
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setSize(764, 449);
		this.setPreferredSize(new java.awt.Dimension(834, 432));
		this.setBounds(0, 0, 834, 432);
		this.setTitle("FINALIZAR SEPARACIÓN");
		id_separacion = Integer.parseInt(separacion);
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 832, 407);
		contenedor.setBackground(new java.awt.Color(255,200,147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("Nº CONTRATO");
			jLabel1.setBounds(12, 16, 180, 38);
			jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel1.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("CLIENTE");
			jLabel2.setBounds(13, 214, 107, 38);
			jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel2.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("ARTICULO");
			jLabel3.setBounds(12, 73, 125, 38);
			jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel3.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("P.VENTA");
			jLabel4.setBounds(12, 273, 173, 38);
			jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel4.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel5 = new JLabel();
			contenedor.add(jLabel5);
			jLabel5.setText("A CUENTA");
			jLabel5.setBounds(13, 323, 121, 38);
			jLabel5.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel5.setForeground(new java.awt.Color(0,128,0));
		}
		{
			jLabel6 = new JLabel();
			contenedor.add(jLabel6);
			jLabel6.setBounds(285, 273, 247, 88);
			jLabel6.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel6.setForeground(new java.awt.Color(0,128,0));
			jLabel6.setBorder(BorderFactory.createTitledBorder("TOTAL"));
		}
		{
			btnGrabar = new JButton();
			contenedor.add(btnGrabar);
			btnGrabar.setText("GRABAR");
			btnGrabar.setBounds(544, 273, 262, 93);
			btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			btnGrabar.setFont(new java.awt.Font("Segoe UI",1,26));
			btnGrabar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					finalizarSeparacion();					
				}
			});
		}
		{
			lblIdSeparacion = new JLabel();
			contenedor.add(lblIdSeparacion);
			lblIdSeparacion.setBounds(192, 16, 160, 38);
			lblIdSeparacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblIdSeparacion.setFont(new java.awt.Font("Segoe UI",1,22));
			lblIdSeparacion.setBackground(new java.awt.Color(255,255,255));
			lblIdSeparacion.setOpaque(true);
			lblIdSeparacion.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblCliente = new JLabel();
			contenedor.add(lblCliente);
			lblCliente.setBounds(291, 212, 515, 38);
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblCliente.setFont(new java.awt.Font("Segoe UI",1,22));
			lblCliente.setBackground(new java.awt.Color(255,255,255));
			lblCliente.setOpaque(true);
			lblCliente.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblArticulo = new JLabel();
			contenedor.add(lblArticulo);
			lblArticulo.setBounds(137, 73, 668, 38);
			lblArticulo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblArticulo.setFont(new java.awt.Font("Segoe UI",1,22));
			lblArticulo.setBackground(new java.awt.Color(255,255,255));
			lblArticulo.setOpaque(true);
			lblArticulo.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblPrecioVenta = new JLabel();
			contenedor.add(lblPrecioVenta);
			lblPrecioVenta.setBounds(140, 273, 133, 38);
			lblPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblPrecioVenta.setFont(new java.awt.Font("Segoe UI",1,22));
			lblPrecioVenta.setBackground(new java.awt.Color(255,255,255));
			lblPrecioVenta.setOpaque(true);
			lblPrecioVenta.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblACuenta = new JLabel();
			contenedor.add(lblACuenta);
			lblACuenta.setBounds(140, 323, 133, 38);
			lblACuenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblACuenta.setFont(new java.awt.Font("Segoe UI",1,22));
			lblACuenta.setBackground(new java.awt.Color(255,255,255));
			lblACuenta.setOpaque(true);
			lblACuenta.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblSaldo = new JLabel();
			contenedor.add(lblSaldo);
			lblSaldo.setBounds(295, 290, 226, 60);
			lblSaldo.setFont(new java.awt.Font("Segoe UI",1,65));
			lblSaldo.setBackground(new java.awt.Color(255,255,255));
			lblSaldo.setForeground(new java.awt.Color(255,0,0));
		}
		{
			jLabel7 = new JLabel();
			contenedor.add(jLabel7);
			jLabel7.setText("FECHA DE SEPARACIÓN");
			jLabel7.setBounds(373, 16, 260, 38);
			jLabel7.setFont(new java.awt.Font("Segoe UI",1,22));
			jLabel7.setForeground(new java.awt.Color(0,128,0));
		}
		{
			lblFechaSeparacion = new JLabel();
			contenedor.add(lblFechaSeparacion);
			lblFechaSeparacion.setBounds(632, 16, 173, 38);
			lblFechaSeparacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblFechaSeparacion.setFont(new java.awt.Font("Segoe UI",1,22));
			lblFechaSeparacion.setOpaque(true);
			lblFechaSeparacion.setBackground(new java.awt.Color(255,255,255));
			lblFechaSeparacion.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblDNICliente = new JLabel();
			contenedor.add(lblDNICliente);
			lblDNICliente.setBounds(138, 212, 147, 38);
			lblDNICliente.setFont(new java.awt.Font("Segoe UI",1,22));
			lblDNICliente.setBackground(new java.awt.Color(255,255,255));
			lblDNICliente.setOpaque(true);
			lblDNICliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			lblDNICliente.setForeground(new java.awt.Color(0,64,128));
		}
		{
			lblCodigoArticulo = new JLabel();
			contenedor.add(lblCodigoArticulo);
			lblCodigoArticulo.setBounds(772, 401, 10, 10);
			lblCodigoArticulo.setVisible(false);
		}

		lblCapital = new JLabel();
		contenedor.add(lblCapital);
		lblCapital.setBounds(29, 52, 10, 10);
		lblCapital.setVisible(false);

		lblGanancia = new JLabel();
		contenedor.add(lblGanancia);
		lblGanancia.setBounds(46, 58, 10, 10);
		lblGanancia.setVisible(false);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(138, 117, 667, 89);

		lblTlfCliente = new JLabel();
		contenedor.add(lblTlfCliente);
		lblTlfCliente.setBounds(33, 177, 10, 10);
		lblTlfCliente.setVisible(false);

		lblDirCli = new JLabel();
		contenedor.add(lblDirCli);
		lblDirCli.setBounds(67, 141, 10, 10);
		lblDirCli.setVisible(false);

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setBounds(83, 60, 10, 10);
		jLabel8.setVisible(false);

		lblP = new JLabel();
		contenedor.add(lblP);
		lblP.setBounds(78, 123, 10, 10);
		lblP.setVisible(false);

		txtObs = new JTextArea();
		jScrollPane1.setViewportView(txtObs);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtObs.setFont(new java.awt.Font("Segoe UI",1,22));
		txtObs.setForeground(new java.awt.Color(0,64,128));

		cargarDatosSeparacion(id_separacion,validarDobleAsociacion(id_separacion).toString().split("-")[0]);
	}
	
	public Object validarDobleAsociacion(int numero){
		Connection con = MySQLConexion.getConexion();
		Object seleccion = null;
		try {
			String sql = "SELECT coalesce(tb_articulo_cod_art,'TOTAL') AS 'resultado', count(distinct tb_articulo_cod_art) AS 'conteo',CONCAT(a.cod_art,'-',a.des_art,space(1),a.mar_art) AS 'articulo' FROM tb_separacion s INNER JOIN tb_articulo a ON s.tb_articulo_cod_art = a.cod_art WHERE s.id_con_asoc=? AND est_sep=1 group by tb_articulo_cod_art WITH ROLLUP";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			List<String> listado = new ArrayList<String>();
			while(rs.next()){		
				if(!rs.getString("resultado").equalsIgnoreCase("TOTAL")){listado.add(rs.getString("articulo"));}						
				if(rs.getString("resultado").equalsIgnoreCase("TOTAL") && rs.getInt("conteo")>1){
					
					seleccion = JOptionPane
							.showInputDialog(
									this,
									"<html><h1>La búsqueda retornó más de un resultado según el criterio ingresado, seleccione...</h1></html> ",
									"Coincidencia de Resultados",
									JOptionPane.QUESTION_MESSAGE, null,
									listado.toArray(), "opcion 1");					
				}else{
					seleccion = rs.getObject("articulo");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
			return seleccion;
	}
	
	public void cargarDatosSeparacion(int separación, String articulo){
		try {
			String sql = "SELECT *,sum(cta_sep) AS cta FROM tb_separacion s INNER JOIN tb_articulo a ON s.tb_articulo_cod_art = a.cod_art INNER JOIN tb_cliente c ON s.tb_cliente_doc_cli = c.doc_cli WHERE s.id_con_asoc=? AND s.tb_articulo_cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, separación);
			pst.setString(2, articulo);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lblIdSeparacion.setText(rs.getString("id_con_asoc"));
				lblDNICliente.setText(rs.getString("tb_cliente_doc_cli"));
				lblDirCli.setText(rs.getString("dir_cli"));
				lblCodigoArticulo.setText(rs.getInt("tb_articulo_cod_art")+"");
				lblCliente.setText(rs.getString("nom_cli")+" "+rs.getString("pat_cli")+" "+rs.getString("mat_cli"));
				lblArticulo.setText(rs.getString("des_art")+" "+rs.getString("mar_art")+" "+rs.getString("mod_art"));
				txtObs.setText(rs.getString("obs_art"));
				lblTlfCliente.setText(rs.getString("mo1_cli")+"/"+rs.getString("fi1_cli"));
				lblFechaSeparacion.setText(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("fec_sep")).toUpperCase());
				lblPrecioVenta.setText(rs.getDouble("pre_sep")+"");
				lblACuenta.setText(rs.getDouble("cta")+"");
				lblSaldo.setText((rs.getDouble("pre_sep")-rs.getDouble("cta"))+"");
				lblCapital.setText(rs.getDouble("cap_sep")+"");
				lblGanancia.setText((rs.getDouble("pre_sep")-rs.getDouble("cap_sep"))+"");
				lblP.setText(rs.getString("p_con_asoc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarSeparacion(){
		try {
			String sql = "UPDATE tb_separacion SET est_sep=0 WHERE id_con_asoc=? AND tb_articulo_cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdSeparacion.getText()));
			pst.setInt(2, Integer.parseInt(lblCodigoArticulo.getText()));
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Separación Finalizada");
			Ingreso ingreso = new Ingreso(Principal.id_libro_caja, lblP.getText()+"-"+lblIdSeparacion.getText()+" - "+lblArticulo.getText(), "S-REM.", Double.parseDouble(lblCapital.getText()), Double.parseDouble(lblGanancia.getText()), Double.parseDouble(lblSaldo.getText()));
			ingreso.registrarIngreso();
			Venta venta = new Venta(lblDNICliente.getText(), Integer.parseInt(lblCodigoArticulo.getText()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), Double.parseDouble(lblPrecioVenta.getText()),"REGULAR");			
			actualizarEstadoArticulo(venta.getCodigo_articulo());
			venta.registrarVenta();
			actualizarContrato(arrayTablas(Integer.parseInt(lblIdSeparacion.getText()/*.split("-")[1]*/))[0]);
			vt = venta;
			JOptionPane.showMessageDialog(null, "<html><h2>SE IMPRIMIRÁ LA HOJA MEMBRETADA</h2></html>");
			imprimirBoleta();
			JOptionPane.showMessageDialog(null, "<html><h2>SE IMPRIMIRÁ EL RECIBO INTERNO</h2></html>");
			imprimirRecibo();
			int i = JOptionPane.showConfirmDialog(null, "<html><h2>PREPARANDO PARA IMPRIMIR LA BOLETA DE VENTA, INGRESE EL PAPEL Y PRESIONE \"SÍ\" SI DESEA CONTINUAR ...</h2></html>","ALERTA",JOptionPane.YES_OPTION);
			if(i == JOptionPane.YES_OPTION){
				imprimirReciboLegal();
				this.dispose();
			}else{
				this.dispose();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarContrato(String tabla){
		Connection con = MySQLConexion.getConexion();		
		try {
			String sql = "UPDATE "+tabla+" SET tb_estado_contrato_id_est=11 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdSeparacion.getText()/*lblContratoAsociado.getText().split("-")[1])*/));
			pst.executeUpdate();			
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
	
	public String[] arrayTablas(int id_contrato) {
		Connection con = MySQLConexion.getConexion();
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
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrayTablas;
	}
	
	public void actualizarEstadoArticulo(int codigo){
		try {
			String sql = "UPDATE tb_articulo SET tb_estado_articulo_cod_est=4 WHERE cod_art=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Artículo actualizado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirBoleta() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
 		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", lblIdSeparacion.getText());
 		parametros.put("detalle_articulo",lblArticulo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MMMM-yyyy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblTlfCliente.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - imprimirContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		}
	}
	
	public void imprimirRecibo() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
 		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", lblIdSeparacion.getText());
 		parametros.put("detalle_articulo",lblArticulo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MM-yy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblTlfCliente.getText());
 		parametros.put("monto_recibido", lblSaldo.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta_recibo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - imprimirContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		}
	}	
	
	public void imprimirReciboLegal() {
		ArrayList<Venta> arreglo_venta = new ArrayList<Venta>();
		arreglo_venta.add(vt);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
 		parametros.put("nombre_cliente",lblCliente.getText());
 		parametros.put("nro_contrato", lblIdSeparacion.getText());
 		parametros.put("detalle_articulo",lblArticulo.getText());
 		parametros.put("obs_art",txtObs.getText());
 		parametros.put("fecha_venta", new SimpleDateFormat("dd-MM-yy").format(new Date()).toUpperCase());
 		parametros.put("tlf_cliente", lblDirCli.getText());
 		parametros.put("monto_recibido", lblSaldo.getText());
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_venta_legal.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					parametros,
					new JRBeanCollectionDataSource(arreglo_venta));
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (Exception e) {
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - imprimirContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		}
	}	
	
	
}
