package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import Utils.Constantes;
import Utils.EditorDS;
import Beans.Auditoria;
import Beans.Cargo;
import Beans.Contrato;
import Beans.Cargo.Detalle_Cargo;
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
public class Crear_Cargo extends JInternalFrame{
	private JPanel contenedor;
	private JTextField txtNumeroContrato;
	private JScrollPane jScrollPane1;
	private JTable tbArticulos;
	private JScrollPane jScrollPane2;
	private JTextField txtObservaciones;
	private JTextField txtTransportado;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JButton btnGrabar;
	private JTable tbCargo;
	private JLabel lblNumeroCargo;
	private JLabel jLabel2;
	private JSeparator jSeparator1;
	private JLabel jLabel1;
	Cargo c = new Cargo();
	DefaultTableModel modeloArticulos = new DefaultTableModel(null, new String[] {
			"NÚMERO CONTRATO", "CÓDIGO ARTÍCULO", "DESCRIPCIÓN","ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};
	DefaultTableModel modeloCargo = new DefaultTableModel(null, new String[] {
			"NÚMERO CONTRATO", "ID ARTÍCULO", "DESCRIPCIÓN","DESTINO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if(colIndex == 3){
				return true;
			}
			return false;
		}
	};

	public Crear_Cargo() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1177, 700);
		this.setTitle("GENERAR CARGO");
		this.setPreferredSize(new java.awt.Dimension(1162, 910));
		this.setBounds(0, 0, 1162, 910);
		this.setClosable(true);
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1161, 885);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("NUMERO DE CONTRATO:");
		jLabel1.setBounds(347, 19, 292, 35);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		txtNumeroContrato = new JTextField();
		contenedor.add(txtNumeroContrato);
		txtNumeroContrato.setBounds(632, 19, 212, 35);
		txtNumeroContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtNumeroContrato.setFont(new java.awt.Font("Segoe UI",1,22));
		txtNumeroContrato.setForeground(new java.awt.Color(0,64,128));
		txtNumeroContrato.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					consultarArticulos(Integer.parseInt(txtNumeroContrato.getText()));
				}
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(20, 70, 1107, 240);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(20, 316, 707, 10);

		jScrollPane2 = new JScrollPane();
		contenedor.add(jScrollPane2);
		jScrollPane2.setBounds(20, 332, 1107, 431);
		jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		tbCargo = new JTable();
		jScrollPane2.setViewportView(tbCargo);
		tbCargo.setModel(modeloCargo);
		tbCargo.setDefaultEditor(Object.class, new EditorDS());

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("N° CARGO:");
		jLabel2.setBounds(20, 19, 137, 35);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel2.setForeground(new java.awt.Color(0,128,0));

		lblNumeroCargo = new JLabel(correlativoCargo()+"");
		contenedor.add(lblNumeroCargo);
		lblNumeroCargo.setBounds(157, 19, 161, 35);
		lblNumeroCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblNumeroCargo.setBackground(new java.awt.Color(255,255,255));
		lblNumeroCargo.setOpaque(true);
		lblNumeroCargo.setFont(new java.awt.Font("Segoe UI",1,22));
		lblNumeroCargo.setForeground(new java.awt.Color(0,64,128));

		btnGrabar = new JButton();
		contenedor.add(btnGrabar);
		btnGrabar.setText("PROCESAR");
		btnGrabar.setBounds(800, 775, 327, 86);
		btnGrabar.setFont(new java.awt.Font("Segoe UI",1,22));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("TRANSPORTADO POR:");
		jLabel3.setBounds(20, 774, 251, 35);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel3.setForeground(new java.awt.Color(0,128,0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("OBSERVACIONES:");
		jLabel4.setBounds(23, 822, 251, 35);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,22));
		jLabel4.setForeground(new java.awt.Color(0,128,0));

		txtTransportado = new JTextField();
		contenedor.add(txtTransportado);
		txtTransportado.setBounds(264, 775, 522, 35);
		txtTransportado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtTransportado.setFont(new java.awt.Font("Segoe UI",1,22));

		txtObservaciones = new JTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(265, 822, 523, 35);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI",1,22));

		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setCodigo_cargo(Integer.parseInt(lblNumeroCargo.getText()));
				c.setFecha_cargo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				c.setTotal_piezas(modeloCargo.getRowCount());
				c.setTransportado_por(txtTransportado.getText().toUpperCase());
				c.setObservacion(txtObservaciones.getText().toUpperCase());
				grabarCargo(c);
				grabarDetalleCargo();
				imprimirCargo();
			}
		});

		tbArticulos = new JTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(30);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 24));
		tbArticulos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 24));
		tbArticulos.getTableHeader().setForeground(
				new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
					int codigo_cargo = Integer.parseInt(lblNumeroCargo.getText());
					int fila = tbArticulos.getSelectedRow();
					int codigo_contrato = Integer.parseInt(modeloArticulos.getValueAt(fila, 0).toString());					
					int codigo_articulo = Integer.parseInt(modeloArticulos.getValueAt(fila, 1).toString());
					String descripcion = modeloArticulos.getValueAt(fila, 2).toString();
					String destino = "NO ESPECIFICADO";
					String estado = modeloArticulos.getValueAt(fila, 3).toString();
					Cargo.Detalle_Cargo producto_cargo = c.new Detalle_Cargo(codigo_cargo, codigo_contrato, codigo_articulo, descripcion ,destino,estado);
					c.getDetalle_cargo().add(producto_cargo);
					listarArticulosCargo();
				}
			}
		});
		tbCargo.setRowHeight(30);
		tbCargo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		tbCargo.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 24));
		tbCargo.getTableHeader().setForeground(
				new Color(181, 0, 0));
		tbCargo.getModel().addTableModelListener(new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE){
					int fila = tbCargo.getSelectedRow();
					int contrato = Integer.parseInt(modeloCargo.getValueAt(fila, 0).toString());
					int articulo = Integer.parseInt(modeloCargo.getValueAt(fila, 1).toString());
					for(Detalle_Cargo dc : c.getDetalle_cargo()){
						if(dc.getCodigo_contrato() == contrato && dc.getCodigo_articulo() == articulo){
							dc.setDestino(modeloCargo.getValueAt(fila, 3).toString());
						}
					}
					
				}		
			}
		});
		tbCargo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					int fila = tbCargo.getSelectedRow();
					int codigo = Integer.parseInt(tbCargo.getValueAt(fila, 1).toString());
					for(int i=0; i<c.getDetalle_cargo().size();i++){
						Cargo.Detalle_Cargo articulo_cargo = c.getDetalle_cargo().get(i);
						if(articulo_cargo.getCodigo_articulo() == codigo){
							c.getDetalle_cargo().remove(i);
							break;
						}
					}
				}
				listarArticulosCargo();
			}
		});
	}
	
	public void listarArticulosCargo(){
		modeloCargo.setRowCount(0);
		for(Detalle_Cargo dc : c.getDetalle_cargo()){
			modeloCargo.addRow(new Object[]{dc.getCodigo_contrato(), dc.getCodigo_articulo(), dc.getDescripcion(),dc.getDestino() });
		}		
		tbCargo.setModel(modeloCargo);
	}
	
	
	public void consultarArticulos(int nro_contrato){
		Connection con = MySQLConexion.getConexion();				
		try {
			String[] a = arrayTablas(nro_contrato);
			String sql = "SELECT "+a[0]+"_id_con,tb_articulo_cod_art,cantidad, "
					+ "CONCAT(des_art,space(1),mar_art,space(1),mod_art) "
					+ "AS 'DESC',ec.des_est FROM "+a[1]+" dc INNER JOIN tb_articulo a "
					+ "ON dc.tb_articulo_cod_art = a.cod_art INNER JOIN "+a[0]+" c ON c.id_con = dc."+a[0]
					+"_id_con INNER JOIN tb_estado_contrato ec ON ec.id_est = c.tb_estado_contrato_id_est"
					+" WHERE "+a[0]+"_id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nro_contrato);
			ResultSet rs = pst.executeQuery();
			modeloArticulos.setRowCount(0);
			while(rs.next()){
				modeloArticulos.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3)+" "+rs.getString(4),rs.getString(5)});
			}tbArticulos.setModel(modeloArticulos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int correlativoCargo(){
		Connection con = MySQLConexion.getConexion();
		int correlativo = 0;
		try {
			String sql = "SELECT ai_cargo FROM tb_correlativo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				correlativo = rs.getInt(1) + 1;
			} else {
				correlativo = 1;
			}
		} catch (Exception e) {
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - correlativoContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return correlativo;
	}
	
	public String[] arrayTablas(int id_contrato) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		String[] arrayTablas = null;
		try {
			String sql= "SELECT * FROM tb_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_contrato);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){				
				return 	arrayTablas = new String[]{"tb_contrato","tb_detalle_contrato"};				
			}else{
				String sql1= "SELECT * FROM tb_contrato_manual WHERE id_con=?";
				PreparedStatement pst1 = con.prepareStatement(sql1);
				pst1.setInt(1, id_contrato);
				ResultSet rs1 = pst1.executeQuery();
				if(rs1.next()){
					return 	arrayTablas = new String[]{"tb_contrato_manual","tb_detalle_contrato_manual"};
				}else{					
					String sql2= "SELECT * FROM tb_contrato_oro WHERE id_con=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1, id_contrato);
					ResultSet rs2 = pst2.executeQuery();
					if(rs2.next()){
						return 	arrayTablas = new String[]{"tb_contrato_oro","tb_detalle_contrato_oro"};	
					}else{
						JOptionPane.showMessageDialog(null, "Ese contrato NO existe.");
					}					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return arrayTablas;
	}
	
	public void grabarCargo(Cargo c){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "INSERT INTO tb_cargo VALUES(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, c.getCodigo_cargo());
			pst.setString(2, c.getFecha_cargo());
			pst.setInt(3, c.getTotal_piezas());
			pst.setString(4, c.getTransportado_por());
			pst.setString(5, c.getObservacion());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "SE GENERÓ CARGO");			
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
	
	public void grabarDetalleCargo(){
		Connection con = MySQLConexion.getConexion();
		try {
			for(Detalle_Cargo dc : c.getDetalle_cargo()){
				String sql = "INSERT INTO tb_detalle_cargo VALUES(?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, dc.getCodigo_cargo());
				pst.setInt(2, dc.getCodigo_articulo());
				pst.setInt(3, dc.getCodigo_contrato());
				pst.setString(4, dc.getDestino());
				pst.executeUpdate();
			}			
			JOptionPane.showMessageDialog(null, "SE GENERÓ DETALLE");		
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
	
	public void imprimirCargo() {
		ArrayList<Cargo> arreglo_cargo = new ArrayList<Cargo>();
		arreglo_cargo.add(c);		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p", Constantes.NOMBRE_SUCURSAL);
		try {
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject("cargo.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					params,
					new JRBeanCollectionDataSource(arreglo_cargo));
			JasperPrintManager.printReport(jasperPrint, true);
		} catch (Exception e) {
			Auditoria a = new Auditoria("Error de tipo: " + e.getClass()
					+ " en Contrato_Prestacion - imprimirContrato()",
					" Motivo: " + e.getCause() + " Detalle: " + e.getMessage(),
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Date()));
			a.registrarAuditoria();
		}
	}
	
	public void cerrar(){
		this.dispose();
	}
}
