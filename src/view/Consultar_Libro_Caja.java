package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTitledSeparator;

import Utils.Constantes;
import Utils.Redondeo;

import com.toedter.calendar.JDateChooser;

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
public class Consultar_Libro_Caja extends JInternalFrame{
	private JPanel contenedor;
	private JLabel lblAmanece;
	private JDateChooser dchFecha;
	private JLabel jLabel10;
	private JButton btnSalir;
	private JLabel lblCerrarAmanece;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel lblTotalEgresos;
	private JLabel lbltotalIngresos;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JScrollPane jScrollPane2;
	private JLabel jLabel2;
	private JLabel lblTotalEgreso;
	private JLabel lblTotalEmpenios;
	private JLabel lblTotalNeto;
	private JLabel lblTotalGanacia;
	private JTable tbEgresos;
	private JTable tbIngresos;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;
	private JLabel lblFecha;	
	Connection con = MySQLConexion.getConexion();
	private JXTitledSeparator jSeparator1;
	private JButton btnCerrarCaja;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private DecimalFormat df = new DecimalFormat("######.00");
	
	DefaultTableModel modeloIngreso = new DefaultTableModel(null,new String[]{"DESCRIPCIÓN","TIPO","CAPITAL",
			"GANANCIA","OTROS","NETO"}){
				public boolean isCellEditable(int rowIndex, int colIndex){
					return false;
				}
	};
	DefaultTableModel modeloEgreso = new DefaultTableModel(null,new String[]{"DESCRIPCIÓN","TIPO","MONTO"}){
				public boolean isCellEditable(int rowIndex, int colIndex){
					return false;
				}
	};
	 public Consultar_Libro_Caja() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1296, 713);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		this.setPreferredSize(new java.awt.Dimension(1285, 825));
		this.setBounds(0, 0, 1285, 825);		
		{
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1284, 801);
			contenedor.setBackground(new java.awt.Color(255,200,147));
			{
				lblFecha = new JLabel();
				contenedor.add(lblFecha);
				lblFecha.setText(new SimpleDateFormat("EEEEE, dd MMMM yyyy").format(new Date()).toUpperCase());
				lblFecha.setBounds(0, 0, 1284, 66);
				lblFecha.setFont(new java.awt.Font("Segoe UI",1,36));
				lblFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblFecha.setBackground(new java.awt.Color(0,128,128));
				lblFecha.setForeground(new java.awt.Color(255,255,255));
				lblFecha.setOpaque(true);
				lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel1 = new JLabel();
				contenedor.add(jLabel1);
				jLabel1.setText("AMANECE (S/.)");
				jLabel1.setBounds(318, 108, 186, 38);
				jLabel1.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel1.setForeground(new java.awt.Color(0,128,0));
			}
			{
				lblAmanece = new JLabel();
				contenedor.add(lblAmanece);
				lblAmanece.setBounds(504, 108, 124, 38);
				lblAmanece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblAmanece.setFont(new java.awt.Font("Segoe UI",1,24));
				lblAmanece.setOpaque(true);
				lblAmanece.setBackground(new java.awt.Color(0,128,128));
				lblAmanece.setForeground(new java.awt.Color(255,255,255));
				lblAmanece.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jScrollPane1 = new JScrollPane();
				contenedor.add(jScrollPane1);
				jScrollPane1.setBounds(15, 158, 724, 357);
				jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				jScrollPane1.setBackground(new java.awt.Color(255,255,255));

				tbIngresos = new JTable();
				jScrollPane1.setViewportView(tbIngresos);
				tbIngresos.setModel(modeloIngreso);
				tbIngresos.setRowHeight(30);
				tbIngresos.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbIngresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
				tbIngresos.getTableHeader().setForeground(new Color(181,0,0));
				
			}
			{
				jScrollPane2 = new JScrollPane();
				contenedor.add(jScrollPane2);
				jScrollPane2.setBounds(751, 158, 502, 357);
				jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				jScrollPane2.setBackground(new java.awt.Color(255,255,255));

				tbEgresos = new JTable();
				jScrollPane2.setViewportView(tbEgresos);
				tbEgresos.setModel(modeloEgreso);
				tbEgresos.setRowHeight(30);
				tbEgresos.setFont(new Font("Segoe UI", Font.BOLD, 18));
				tbEgresos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
				tbEgresos.getTableHeader().setForeground(new Color(181,0,0));
				
			}
			{
				lblTotalGanacia = new JLabel();
				contenedor.add(lblTotalGanacia);
				lblTotalGanacia.setBounds(390, 527, 119, 46);
				lblTotalGanacia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblTotalGanacia.setFont(new java.awt.Font("Segoe UI",1,24));
				lblTotalGanacia.setOpaque(true);
				lblTotalGanacia.setForeground(new java.awt.Color(255,255,255));
				lblTotalGanacia.setBackground(new java.awt.Color(0,128,128));
				lblTotalGanacia.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalNeto = new JLabel();
				contenedor.add(lblTotalNeto);
				lblTotalNeto.setBounds(515, 527, 113, 46);
				lblTotalNeto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblTotalNeto.setFont(new java.awt.Font("Segoe UI",1,24));
				lblTotalNeto.setOpaque(true);
				lblTotalNeto.setForeground(new java.awt.Color(255,255,255));
				lblTotalNeto.setBackground(new java.awt.Color(0,128,128));
				lblTotalNeto.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEmpenios = new JLabel();
				contenedor.add(lblTotalEmpenios);
				lblTotalEmpenios.setBounds(751, 527, 113, 53);
				lblTotalEmpenios.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblTotalEmpenios.setFont(new java.awt.Font("Segoe UI",1,48));
				lblTotalEmpenios.setOpaque(true);
				lblTotalEmpenios.setForeground(new java.awt.Color(255,255,255));
				lblTotalEmpenios.setBackground(new java.awt.Color(255,0,0));
				lblTotalEmpenios.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEgreso = new JLabel();
				contenedor.add(lblTotalEgreso);
				lblTotalEgreso.setBounds(1114, 534, 139, 46);
				lblTotalEgreso.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblTotalEgreso.setFont(new java.awt.Font("Segoe UI",1,24));
				lblTotalEgreso.setOpaque(true);
				lblTotalEgreso.setForeground(new java.awt.Color(255,255,255));
				lblTotalEgreso.setBackground(new java.awt.Color(0,128,128));
				lblTotalEgreso.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel2 = new JLabel();
				contenedor.add(jLabel2);
				jLabel2.setText("TOTAL GANANCIA Y NETO (S/.)");
				jLabel2.setBounds(15, 536, 375, 29);
				jLabel2.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel2.setForeground(new java.awt.Color(0,128,0));
			}		
			{
				jLabel3 = new JLabel();
				contenedor.add(jLabel3);
				jLabel3.setText("EMP.");
				jLabel3.setBounds(870, 544, 62, 29);
				jLabel3.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel3.setForeground(new java.awt.Color(0,128,0));
			}
			{
				jLabel4 = new JLabel();
				contenedor.add(jLabel4);
				jLabel4.setText("TOTAL (S/.)");
				jLabel4.setBounds(968, 544, 140, 29);
				jLabel4.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel4.setForeground(new java.awt.Color(0,128,0));
			}
			{
				btnCerrarCaja = new JButton(new ImageIcon("img/imprimirContrato.png"));
				contenedor.add(btnCerrarCaja);
				btnCerrarCaja.setText("IMPRIMIR REPORTE");
				btnCerrarCaja.setBounds(597, 641, 439, 103);
				btnCerrarCaja.setFont(new java.awt.Font("Segoe UI",1,28));
				btnCerrarCaja.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				btnCerrarCaja.setBackground(new java.awt.Color(0,128,192));
				btnCerrarCaja.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						mostrarReporteCajaAnterior();						
					}
				});
			}
			{
				jSeparator1 = new JXTitledSeparator("RESUMEN DE CAJA");
				contenedor.add(jSeparator1);
				jSeparator1.setBounds(12, 585, 1238, 50);
				jSeparator1.setFont(new java.awt.Font("Segoe UI",1,24));
				jSeparator1.setForeground(new java.awt.Color(128,0,0));
			}
			{
				jLabel5 = new JLabel();
				contenedor.add(jLabel5);
				jLabel5.setText("INGRESOS");
				jLabel5.setBounds(12, 647, 129, 29);
				jLabel5.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel5.setForeground(new java.awt.Color(0,128,0));
			}
			{
				jLabel6 = new JLabel();
				contenedor.add(jLabel6);
				jLabel6.setText("EGRESOS");
				jLabel6.setBounds(244, 647, 129, 29);
				jLabel6.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel6.setForeground(new java.awt.Color(0,128,0));
			}
			{
				lbltotalIngresos = new JLabel();
				contenedor.add(lbltotalIngresos);
				lbltotalIngresos.setBounds(12, 698, 113, 46);
				lbltotalIngresos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lbltotalIngresos.setOpaque(true);
				lbltotalIngresos.setFont(new java.awt.Font("Segoe UI",1,24));
				lbltotalIngresos.setForeground(new java.awt.Color(255,255,255));
				lbltotalIngresos.setBackground(new java.awt.Color(0,128,128));
				lbltotalIngresos.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblTotalEgresos = new JLabel();
				contenedor.add(lblTotalEgresos);
				lblTotalEgresos.setBounds(244, 698, 113, 46);
				lblTotalEgresos.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				lblTotalEgresos.setOpaque(true);
				lblTotalEgresos.setFont(new java.awt.Font("Segoe UI",1,24));
				lblTotalEgresos.setForeground(new java.awt.Color(255,255,255));
				lblTotalEgresos.setBackground(new java.awt.Color(0,128,128));
				lblTotalEgresos.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel7 = new JLabel();
				contenedor.add(jLabel7);
				jLabel7.setText("-");
				jLabel7.setBounds(169, 665, 40, 88);
				jLabel7.setFont(new java.awt.Font("Segoe UI",1,72));
				jLabel7.setVerticalAlignment(SwingConstants.TOP);
				jLabel7.setForeground(new java.awt.Color(128,0,0));
			}
			{
				jLabel8 = new JLabel();
				contenedor.add(jLabel8);
				jLabel8.setText("=");
				jLabel8.setBounds(379, 656, 77, 88);
				jLabel8.setFont(new java.awt.Font("Segoe UI",1,72));
				jLabel8.setForeground(new java.awt.Color(128,0,0));
			}
			{
				jLabel9 = new JLabel();
				contenedor.add(jLabel9);
				jLabel9.setText("AMANECE");
				jLabel9.setBounds(447, 647, 129, 29);
				jLabel9.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel9.setForeground(new java.awt.Color(0,128,0));
			}
			{
				lblCerrarAmanece = new JLabel();
				contenedor.add(lblCerrarAmanece);
				lblCerrarAmanece.setBounds(447, 698, 113, 46);
				lblCerrarAmanece.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				lblCerrarAmanece.setOpaque(true);
				lblCerrarAmanece.setFont(new java.awt.Font("Segoe UI",1,24));
				lblCerrarAmanece.setForeground(new java.awt.Color(255,255,255));
				lblCerrarAmanece.setBackground(new java.awt.Color(0,128,128));
				lblCerrarAmanece.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				btnSalir = new JButton(new ImageIcon("img/salir.png"));
				contenedor.add(btnSalir);
				btnSalir.setText("SALIR");
				btnSalir.setBounds(1042, 640, 208, 104);
				btnSalir.setFont(new java.awt.Font("Segoe UI",1,28));
				btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				btnSalir.setBackground(new java.awt.Color(0,128,192));
				btnSalir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						cerrar();
					}
				});
			}
			{
				jLabel10 = new JLabel();
				contenedor.add(jLabel10);
				jLabel10.setText("FECHA");
				jLabel10.setBounds(15, 108, 123, 38);
				jLabel10.setFont(new java.awt.Font("Segoe UI",1,24));
				jLabel10.setForeground(new java.awt.Color(0,128,0));
			}
			{
				dchFecha = new JDateChooser();
				contenedor.add(dchFecha);
				dchFecha.setBounds(106, 106, 182, 34);
				dchFecha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				dchFecha.setFont(new java.awt.Font("Dialog",1,18));
				dchFecha.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e){
						if(e.getKeyCode() == KeyEvent.VK_ENTER){
							cargarEgresos(cargarInfoCaja(new SimpleDateFormat("yyyy-MM-dd").format(dchFecha.getDate())));				
							totalEmpenios();
							totalEgresos();
							cargarIngresos(cargarInfoCaja(new SimpleDateFormat("yyyy-MM-dd").format(dchFecha.getDate())));
							totalGanancias();
							totalNeto();
							calcularIngresosVsEgresos();
						}
					} 
				});
			}
		}		
				
	 }
	
	 
	public void cargarEgresos(int id_libro_caja) {
			try {			
				String sql = "SELECT * FROM tb_egreso WHERE tb_libro_caja_id_caja=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id_libro_caja);
				ResultSet rs = pst.executeQuery();
				modeloEgreso.setRowCount(0);
				while (rs.next()) {
					double cambio = (rs.getString("tip_egr").equalsIgnoreCase("EMP.($)"))? obtenerCambio():1;				
					modeloEgreso.addRow(new Object[] { rs.getString("desc_egr"),
							rs.getString("tip_egr"),
							df.format(rs.getDouble("mon_egr")*cambio).replace(',', '.') });
				}
				tbEgresos.setModel(modeloEgreso);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	 
	public void totalEmpenios() {
		int empenios = 0;
		for (int i = 0; i <= modeloEgreso.getRowCount() - 1; i++) {
			String tipo = modeloEgreso.getValueAt(i, 1).toString();
			if (tipo.equalsIgnoreCase("EMP.") || tipo.equalsIgnoreCase("EMP.($)")) {
				empenios++;
			}
		}
		lblTotalEmpenios.setText(empenios + "");
	}
	
	public void totalEgresos(){
		double egresos=0;
		for(int i=0; i<=modeloEgreso.getRowCount()-1;i++){
				double monto = Double.parseDouble(modeloEgreso.getValueAt(i, 2).toString().replace(',', '.'));
				egresos+=monto;			
		}
		lblTotalEgreso.setText(df.format(egresos));
		lblTotalEgresos.setText(df.format(egresos));
	} 
	
	public void totalGanancias(){
		double ganancias=0;
		for(int i=0; i<=modeloIngreso.getRowCount()-1;i++){
				double monto = Double.parseDouble(modeloIngreso.getValueAt(i, 3).toString());
				ganancias+=monto;			
		}lblTotalGanacia.setText(df.format(ganancias));
	}
	
	public void totalNeto(){
		double neto=0;
		double amanece = Double.parseDouble(lblAmanece.getText());
		for(int i=0; i<=modeloIngreso.getRowCount()-1;i++){
				double monto = Double.parseDouble(modeloIngreso.getValueAt(i, 5).toString());
				neto+=monto;			
		}
		lblTotalNeto.setText(df.format(neto+amanece));
		lbltotalIngresos.setText(df.format(neto+amanece));
	} 
	
	 public void cargarIngresos(int id_libro_caja){
			try {
				String sql = "SELECT * FROM tb_ingreso WHERE tb_libro_caja_id_caja=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id_libro_caja);				
				ResultSet rs = pst.executeQuery();
				modeloIngreso.setRowCount(0);
				while(rs.next()){
					double neto = rs.getDouble("cap_ing") + rs.getDouble("gan_ing")+rs.getDouble("oto_ing");	
					if(rs.getString("tip_ing").equalsIgnoreCase("S-REM.")){
						neto = rs.getDouble("oto_ing");
					}
					modeloIngreso.addRow(new Object[]{rs.getString("desc_ing"),rs.getString("tip_ing"),rs.getDouble("cap_ing"),rs.getDouble("gan_ing"),rs.getDouble("oto_ing"),neto});
				}tbIngresos.setModel(modeloIngreso);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	 
	 public int cargarInfoCaja(String fecha){
		 int id_caja = 0;
		 try {
			String sql = "SELECT * FROM tb_libro_caja WHERE fec_caja=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fecha);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lblAmanece.setText(rs.getDouble("ama_caja")+"");
				id_caja =  rs.getInt("id_caja");
			}else{
				JOptionPane.showMessageDialog(null, "Error al traer datos de Caja");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	return id_caja;
	 }
	 
	 public void calcularIngresosVsEgresos(){
		 double ingresos = Double.parseDouble(lbltotalIngresos.getText().replace(',','.'));		 
		 double egresos = Double.parseDouble(lblTotalEgresos.getText().replace(',','.'));		 
		 lblCerrarAmanece.setText(Redondeo.redondearCentimos(String.valueOf(df.format(ingresos-egresos).replace(',','.'))));		 
	 }
	 
	 public void cerrar(){
			int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION){
			this.dispose();
			}
	 }
	 
	 public void cerrarCaja(){
		 try {
			String sql = "UPDATE tb_libro_caja SET est_caja=0, cer_caja=? WHERE id_caja=?";
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setDouble(1, Double.parseDouble(lblCerrarAmanece.getText().replace(',', '.')));
			pst.setInt(2, Principal.id_libro_caja);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Caja cerrada.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void mostrarReporteCajaAnterior() {
			Connection con = MySQLConexion.getConexion();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("p", Constantes.SUCURSAL);
			parametros.put("fecha_master", new SimpleDateFormat("yyyy-MM-dd").format(dchFecha.getDate()));			
			try {
				JasperReport reporte = (JasperReport) JRLoader
						.loadObject("reporte_caja_diaria.jasper");
				JasperPrint print = JasperFillManager.fillReport(reporte,
						parametros, con);
				JasperViewer viewer = new JasperViewer(print, false);
				viewer.show();
				viewer.toFront();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 public double obtenerCambio(){
			Connection con = MySQLConexion.getConexion();
			double cambio = 1;
			try {
				String sql = "select ven_cam from tb_cambio where fec_cam=date_Format(now(),'%y-%m-%d')";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				if(rs.next()){
					cambio = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {				
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				return cambio;
		}
}	
	

