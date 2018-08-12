package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTitledSeparator;

import Utils.EditorFEC;
import Utils.RenderFEC;
import Beans.Articulo;
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
public class Remate_Contrato extends JInternalFrame{
	private JButton btnRematarContrato;
	private JLabel lblMontoDiasExcedidos;
	private JLabel jLabel4;
	private JButton btnSalir;
	private JLabel lblSiNoMora;
	private JLabel lblMoraActual;
	private JLabel jLabel17;
	private JComboBox cboInteresMoratorio;
	private JLabel jLabel16;
	private JLabel jLabel15;
	private JLabel lblInteresDiario;
	private JLabel lblDiasExcedidos;
	private JLabel jLabel11;
	private JLabel jLabel2;
	private JLabel lblEstado;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel lblRemate;
	private JLabel jLabel5;
	private JLabel lblVencimiento;
	private JLabel lblInicio;
	private JLabel lblIdContrato;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel lblCliente;
	private JLabel lblIdentidad;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JXTitledSeparator jSeparator2;
	private JPanel contenedor;
	private String identificador;	
	private JTabbedPane tpContrato;
	private JPanel pnlArticulos;
	private JPanel pnlMoras;
	private JPanel pnlPagos;
	private JTable tbHistorial;
	private JTable tbMoras;
	private JTable tbArticulos;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();	
	DecimalFormat fd; 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date inicio; 
	Date vcto;
	Date remate;
	String[] a = null;
	
	@SuppressWarnings("serial")
	DefaultTableModel modeloDetalleContrato = new DefaultTableModel(null,new String[]{"CÓDIGO","DESC.","MARCA",
			"MODELO","OBS.","CAPITAL","P.VENTA","P.WEB","FECHA WEB"}){
				public boolean isCellEditable(int rowIndex, int colIndex){
					if(colIndex == 5){
						return false;
					}
					return true;
				}
	};	
	
	@SuppressWarnings("serial")
	DefaultTableModel modeloHistorial = new DefaultTableModel(null,new String[]{"RENOVACIÓN","FECHA","CAPITAL","INTERES","MORA"}){
		public boolean isCellEditable(int rowIndex, int colIndex){
			return false;
		}
	};	
	@SuppressWarnings("serial")
	DefaultTableModel modeloMora = new DefaultTableModel(null,new String[]{"ID","MONTO","ESTADO"}){
		public boolean isCellEditable(int rowIndex, int colIndex){
			return false;
		}
	};
	
	public Remate_Contrato(String numero) throws Exception {
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(930, 447);
		identificador = numero;
		simbolo.setDecimalSeparator('.');
		fd = new DecimalFormat("######.00",simbolo);
		
		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1301, 724);
		contenedor.setBackground(new java.awt.Color(255,200,147));
	
	{
		jSeparator2 = new JXTitledSeparator("DATOS GLOBALES DEL CONTRATO");
		contenedor.add(jSeparator2);
		jSeparator2.setBounds(12, 12, 1318, 32);
		jSeparator2.setFont(new java.awt.Font("Arial Black",1,24));
		jSeparator2.setForeground(new java.awt.Color(181,0,0));
	}
	{
		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("CLIENTE:");
		jLabel1.setBounds(628, 116, 110, 30);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel1.setForeground(new java.awt.Color(0,128,0));
	}
	{
		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("DOC.ID:");
		jLabel3.setBounds(325, 116, 89, 30);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel3.setForeground(new java.awt.Color(0,128,0));
	}
	{
		lblIdentidad = new JLabel();
		contenedor.add(lblIdentidad);
		lblIdentidad.setBounds(420, 116, 175, 32);
		lblIdentidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblIdentidad.setFont(new java.awt.Font("Segoe UI",1,24));
		lblIdentidad.setBackground(new java.awt.Color(255,255,255));
		lblIdentidad.setOpaque(true);
	}

	{
		lblCliente = new JLabel();
		contenedor.add(lblCliente);
		lblCliente.setBounds(731, 116, 521, 32);
		lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblCliente.setFont(new java.awt.Font("Segoe UI",1,24));
		lblCliente.setBackground(new java.awt.Color(255,255,255));
		lblCliente.setOpaque(true);
	}
	{
		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("N° CONTRATO:");
		jLabel5.setBounds(12, 64, 161, 30);
		jLabel5.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel5.setForeground(new java.awt.Color(0,128,0));
	}
	{
		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("INICIO:");
		jLabel7.setBounds(325, 63, 85, 30);
		jLabel7.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel7.setForeground(new java.awt.Color(0,128,0));
	}
	{
		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("VENCE:");
		jLabel8.setBounds(628, 65, 81, 30);
		jLabel8.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel8.setForeground(new java.awt.Color(0,128,0));
	}
	{
		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("REMATE:");
		jLabel9.setBounds(969, 63, 103, 34);
		jLabel9.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel9.setForeground(new java.awt.Color(0,128,0));
	}
	{
		lblIdContrato = new JLabel();
		contenedor.add(lblIdContrato);
		lblIdContrato.setBounds(165, 63, 138, 32);
		lblIdContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblIdContrato.setFont(new java.awt.Font("Segoe UI",1,24));
		lblIdContrato.setBackground(new java.awt.Color(255,255,255));
		lblIdContrato.setOpaque(true);
	}
	{
		lblInicio = new JLabel();
		contenedor.add(lblInicio);
		lblInicio.setBounds(423, 63, 175, 32);
		lblInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblInicio.setFont(new java.awt.Font("Segoe UI",1,24));
		lblInicio.setBackground(new java.awt.Color(255,255,255));
		lblInicio.setOpaque(true);
	}
	{
		lblVencimiento = new JLabel();
		contenedor.add(lblVencimiento);
		lblVencimiento.setBounds(731, 63, 175, 32);
		lblVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblVencimiento.setFont(new java.awt.Font("Segoe UI",1,24));
		lblVencimiento.setBackground(new java.awt.Color(255,255,255));
		lblVencimiento.setOpaque(true);
	}
	{
		lblRemate = new JLabel();
		contenedor.add(lblRemate);
		lblRemate.setBounds(1078, 63, 175, 32);
		lblRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblRemate.setFont(new java.awt.Font("Segoe UI",1,24));
		lblRemate.setBackground(new java.awt.Color(255,255,255));
		lblRemate.setOpaque(true);
	}
	{
		jLabel12 = new JLabel();
		contenedor.add(jLabel12);
		jLabel12.setBounds(144, 536, 10, 10);
	}
	{
		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setBounds(144, 494, 10, 10);
	}
	{
		lblEstado = new JLabel();
		contenedor.add(lblEstado);
		lblEstado.setBounds(12, 107, 290, 50);
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblEstado.setFont(new java.awt.Font("Segoe UI",1,36));
		lblEstado.setOpaque(true);
		lblEstado.setBackground(Color.WHITE);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
	}
	{
		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("DÍAS EXCEDIDOS:");
		jLabel2.setBounds(16, 448, 177, 32);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel2.setForeground(new java.awt.Color(0,128,0));
		jLabel2.setVisible(false);
	}
	{
		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("INTERES DIARIO:");
		jLabel11.setBounds(16, 492, 174, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel11.setForeground(new java.awt.Color(0,128,0));
		jLabel11.setVisible(false);
	}
	{
		lblDiasExcedidos = new JLabel();
		contenedor.add(lblDiasExcedidos);
		lblDiasExcedidos.setBounds(274, 448, 143, 32);
		lblDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblDiasExcedidos.setFont(new java.awt.Font("Segoe UI",1,22));
		lblDiasExcedidos.setBackground(new java.awt.Color(255,255,255));
		lblDiasExcedidos.setOpaque(true);
		lblDiasExcedidos.setVisible(false);
	}
	{
		lblInteresDiario = new JLabel();
		contenedor.add(lblInteresDiario);
		lblInteresDiario.setBounds(274, 491, 143, 32);
		lblInteresDiario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblInteresDiario.setFont(new java.awt.Font("Segoe UI",1,22));
		lblInteresDiario.setBackground(new java.awt.Color(255,255,255));
		lblInteresDiario.setOpaque(true);
		lblInteresDiario.setVisible(false);
	}
	{
		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("¿CORRESPONDE MORA?");
		jLabel15.setBounds(472, 400, 231, 32);
		jLabel15.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel15.setForeground(new java.awt.Color(0,128,0));
		jLabel15.setVisible(false);
	}
	{
		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("PORCENTAJE MORATORIO:");
		jLabel16.setBounds(472, 447, 265, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel16.setForeground(new java.awt.Color(0,128,0));
		jLabel16.setVisible(false);
	}
	{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ComboBoxModel cboInteresMoratorioModel = 
				new DefaultComboBoxModel(
						new String[] { "30%", "50%","0%"});
		cboInteresMoratorio = new JComboBox();
		contenedor.add(cboInteresMoratorio);
		cboInteresMoratorio.setModel(cboInteresMoratorioModel);
		cboInteresMoratorio.setBounds(763, 447, 143, 32);
		cboInteresMoratorio.setFont(new java.awt.Font("Segoe UI",1,22));
		cboInteresMoratorio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		cboInteresMoratorio.setVisible(false);
				
	}
	{
		jLabel17 = new JLabel();
		contenedor.add(jLabel17);
		jLabel17.setText("MORA ACTUAL:");
		jLabel17.setBounds(472, 492, 231, 32);
		jLabel17.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel17.setForeground(new java.awt.Color(0,128,0));
		jLabel17.setVisible(false);
	}
	{
		lblMoraActual = new JLabel();
		contenedor.add(lblMoraActual);
		lblMoraActual.setBounds(763, 492, 143, 32);
		lblMoraActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblMoraActual.setFont(new java.awt.Font("Segoe UI",1,22));
		lblMoraActual.setBackground(new java.awt.Color(255,255,255));
		lblMoraActual.setOpaque(true);
		lblMoraActual.setVisible(false);
		lblMoraActual.setText("0.00");
	}
	{
		lblSiNoMora = new JLabel();
		contenedor.add(lblSiNoMora);
		lblSiNoMora.setBounds(763, 398, 143, 32);
		lblSiNoMora.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblSiNoMora.setFont(new java.awt.Font("Segoe UI",1,26));
		lblSiNoMora.setBackground(new java.awt.Color(255,255,255));
		lblSiNoMora.setOpaque(true);
		lblSiNoMora.setVisible(false);
	}
	{
		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(1050, 600, 202, 76);
		btnSalir.setFont(new java.awt.Font("Segoe UI",1,22));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
				
			}
		});
	}
	{
		tpContrato = new JTabbedPane();
		contenedor.add(tpContrato);
		tpContrato.setBounds(12, 180, 1240, 408);			
		tpContrato.setFont(new java.awt.Font("Segoe UI",1,22));
		pnlArticulos = new JPanel();
		pnlMoras = new JPanel();
		pnlPagos = new JPanel();
		tpContrato.addTab("ARTÍCULOS", null, pnlArticulos, null);			
		jScrollPane1 = new JScrollPane();
		pnlArticulos.add(jScrollPane1);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(1213, 342));			
		tbArticulos = new JTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setModel(modeloDetalleContrato);
		tbArticulos.setDefaultEditor(Object.class, new EditorFEC());
		tbArticulos.setDefaultRenderer(Object.class, new RenderFEC());
		tbArticulos.setRowHeight(30);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbArticulos.getTableHeader().setForeground(new Color(181,0,0));					
		tpContrato.addTab("PAGOS", null, pnlPagos, null);			
		jScrollPane2 = new JScrollPane();
		pnlPagos.add(jScrollPane2);
		jScrollPane2.setPreferredSize(new java.awt.Dimension(890, 159));					
		tbHistorial = new JTable();
		jScrollPane2.setViewportView(tbHistorial);
		tbHistorial.setModel(modeloHistorial);
		tbHistorial.setRowHeight(30);
		tbHistorial.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbHistorial.getTableHeader().setForeground(new Color(181,0,0));			
		tpContrato.addTab("MORAS", null, pnlMoras, null);			
		jScrollPane3 = new JScrollPane();
		pnlMoras.add(jScrollPane3);
		jScrollPane3.setPreferredSize(new java.awt.Dimension(890, 161));			
		tbMoras = new JTable();
		jScrollPane3.setViewportView(tbMoras);
		tbMoras.setModel(modeloMora);
		tbMoras.setRowHeight(30);
		tbMoras.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbMoras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbMoras.getTableHeader().setForeground(new Color(181,0,0));	
	}
	{
		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("MONTO DIAS EXCEDIDOS");
		jLabel4.setBounds(16, 536, 258, 32);
		jLabel4.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel4.setForeground(new java.awt.Color(0,128,0));
		jLabel4.setVisible(false);
	}
	{
		lblMontoDiasExcedidos = new JLabel();
		contenedor.add(lblMontoDiasExcedidos);
		lblMontoDiasExcedidos.setBounds(274, 535, 143, 32);
		lblMontoDiasExcedidos.setFont(new java.awt.Font("Segoe UI",1,24));
		lblMontoDiasExcedidos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		lblMontoDiasExcedidos.setBackground(new java.awt.Color(255,255,255));
		lblMontoDiasExcedidos.setOpaque(true);
		lblMontoDiasExcedidos.setVisible(false);
	}
	{
		btnRematarContrato = new JButton();
		contenedor.add(btnRematarContrato);
		btnRematarContrato.setText("ENVIAR A VITRINA");
		btnRematarContrato.setBounds(12, 600, 363, 76);
		btnRematarContrato.setFont(new java.awt.Font("Segoe UI",1,22));
		btnRematarContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnRematarContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					finalizarRemate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		this.setSize(1365, 639);
		this.setPreferredSize(new java.awt.Dimension(1315, 748));
		this.setBounds(0, 0, 1315, 748);		
		a = arrayTablas(Integer.parseInt(identificador));
		validarEstadoContrato(Integer.parseInt(identificador),a[0]);
		
	}
	
	public void cargarContrato(String numero,String tabla) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM "+tabla+" WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(numero));
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				cargarDatosCliente(rs.getString(2));
				cargarDetalleContrato(rs.getInt(1),a[0],a[1]);		
				cargarHistorialPagos(rs.getInt(1));
				determinarAccion(lblEstado.getText().toUpperCase());
			}else{
				JOptionPane.showMessageDialog(null, "No existe tal número de Contrato");
				this.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void cargarDatosCliente(String documento) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, documento);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lblIdentidad.setText(rs.getString(1));
				lblCliente.setText(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
		
	public void cargarDetalleContrato(int codigo,String tabla,String tabla2) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM "+tabla+" c INNER JOIN "+tabla2+" dc ON c.id_con= dc."+tabla+"_id_con INNER JOIN tb_articulo a ON dc.tb_articulo_cod_art=a.cod_art INNER JOIN tb_estado_contrato ec ON c.tb_estado_contrato_id_est=ec.id_est WHERE "+tabla+"_id_con=?";			
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			modeloDetalleContrato.setRowCount(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()){
				inicio=sdf.parse(rs.getString("fec_con")); 
				vcto=sdf.parse(rs.getString("fec_ven_con"));
				remate=sdf.parse(rs.getString("fec_rem_con"));
				modeloDetalleContrato.addRow(new Object[]{rs.getInt("cod_art"),rs.getString("des_art"),rs.getString("mar_art"),rs.getString("mod_art"),rs.getString("obs_art"),rs.getDouble("tas_det_con"),rs.getDouble("pre_ven_art"),rs.getDouble("pre_int_art"),rs.getDate("fec_pre_int_art")});
				lblIdContrato.setText(String.format("%07d", rs.getInt("id_con")));
				lblInicio.setText(new SimpleDateFormat("dd-MMM-yyyy").format(inicio).toUpperCase());
				lblVencimiento.setText(new SimpleDateFormat("dd-MMM-yyyy").format(vcto).toUpperCase());
				lblRemate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(remate).toUpperCase());
				lblEstado.setText(rs.getString("des_est"));
				
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void cargarHistorialPagos(int numero) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			int c = 0;
			String sql = "SELECT * FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				c++;
				modeloHistorial.addRow(new Object[]{"N°"+c,rs.getString("fec_pag"),fd.format(rs.getDouble("mon_cap_pag")),fd.format(rs.getDouble("mon_int_pag")),fd.format(rs.getDouble("mon_mor_pag"))});
														
			}tbHistorial.setModel(modeloHistorial);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void calcularDiasExcedidos(Date fecha_vcto){		
		Calendar vcto = Calendar.getInstance();
		vcto.setTime(fecha_vcto);
		Calendar hoy = Calendar.getInstance();
		long vctoM = vcto.getTimeInMillis();		
		long hoyM = hoy.getTimeInMillis();		
		long resta = hoyM-vctoM;				
		double diasExcedidos = (resta<0)? 0 : resta / (24*60*60*1000);		
		lblDiasExcedidos.setText((Math.round(diasExcedidos))+"");		
	}
	
	public void determinarAccion(String estado) throws Exception{
		if(estado.equalsIgnoreCase("ACTIVO"))
		{
			activo();
			JOptionPane.showMessageDialog(null, "activo");		
		}
		else if(estado.equalsIgnoreCase("VENCIDO"))
		{
			vencido();
		}		
		else if(estado.equalsIgnoreCase("PRE"))
		{
			pre();
		}
		else if(estado.equalsIgnoreCase("POST"))
		{
			post();
		}
		else{			
			remate();
		}
	}
	
	public void activo() throws Exception{
		if(pagosAnteriores(Integer.parseInt(lblIdContrato.getText()))){
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0,128,0));
			Calendar cal = Calendar.getInstance();
			cal.setTime(vcto);
			cal.add(Calendar.DATE, -30);
			Date ultimo_vencimiento = cal.getTime();
			JOptionPane.showMessageDialog(null, ultimo_vencimiento);
			calcularDiasExcedidos(ultimo_vencimiento);			
			lblSiNoMora.setText("NO");
			lblSiNoMora.setForeground(new Color(0,128,0));
			cboInteresMoratorio.setEnabled(false);
			calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));
			
		}else{
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setBackground(new Color(0,128,0));
			calcularDiasExcedidos(vcto);		
			calcularMontoDiasExcedidos();			
			calcularMoraAnterior(Integer.parseInt(lblIdContrato.getText()));			
			JOptionPane.showMessageDialog(null, "here1");
		}		
	}
	
	public void vencido() throws Exception{
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(240,80,0));		 
		Date hoy = new Date();
		try {
			hoy = sdf.parse(sdf.format(hoy));
			if(hoy.after(vcto)){
			
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	public void pre() throws Exception{
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(255,0,128));		 
		Date hoy = new Date();
		try {
			hoy = sdf.parse(sdf.format(hoy));
			if(hoy.after(vcto)){
				calcularDiasExcedidos(vcto);				
			
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void post() throws Exception{
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(128,0,255));
		Date fecha_vcto = vcto; 
		Date hoy = new Date();
		try {
			hoy = sdf.parse(sdf.format(hoy));
			if(hoy.after(fecha_vcto)){
				calcularDiasExcedidos(fecha_vcto);				
				
				if(Integer.parseInt(lblDiasExcedidos.getText())>=30){					
					int dex = Integer.parseInt(lblDiasExcedidos.getText())-30;
					double interesDiario = Double.parseDouble(lblInteresDiario.getText());					
					double x = dex*interesDiario;
					lblDiasExcedidos.setText(dex+"");						
				}				
				calcularMontoDiasExcedidos();				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	public void remate() throws Exception{
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBackground(new Color(200, 0, 0));
		Date fecha_vcto = vcto;
		Date hoy = new Date();
		try {
			hoy = sdf.parse(sdf.format(hoy));
			if (hoy.after(fecha_vcto)) {
				calcularDiasExcedidos(fecha_vcto);			
				if (Integer.parseInt(lblDiasExcedidos.getText()) >= 30) {
				}
			}			
			lblDiasExcedidos.setText(5+"");
			
			lblMontoDiasExcedidos.setText("0.00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void calcularMontoDiasExcedidos(){
		int diasExcedidos = Integer.parseInt(lblDiasExcedidos.getText());
		double interesDiario = Double.parseDouble(lblInteresDiario.getText());
		lblMontoDiasExcedidos.setText((interesDiario*diasExcedidos)+"");
	}
	

	public double calcularMoraAnterior(int contrato) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		double c = 0.00;
		try {
			
			String sql = "SELECT * FROM tb_mora WHERE id_con=? AND est_mora=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				c += rs.getDouble("val_mora");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
			return c;
	}
	
	
	public void cerrar(){
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
		this.dispose();
		}
	}
	
	
	
	public void cancelarContrato(String tabla) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE "+tabla+" SET tb_estado_contrato_id_est=6 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(lblIdContrato.getText()));
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Contrato Cancelado.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		
	}
	
	public void retirarMora(int contrato) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_mora SET est_mora=0 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contrato);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Mora pagada.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}	
	
	public void closeInternal(){
		this.dispose();
	}

	public boolean pagosAnteriores(int contrato) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		boolean flag = false;
		try {
			
			String sql = "SELECT * FROM tb_pago_contrato WHERE id_con=?";
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setInt(1, contrato);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				flag=true;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return flag;		
	}	
	
	public void validarEstadoContrato(int numero,String tabla) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM "+tabla+" WHERE id_con=? AND tb_estado_contrato_id_est=5";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				cargarContrato(identificador,a[0]);
			}else{
				JOptionPane.showMessageDialog(null, "Este Contrato NO está en una etapa de REMATE, gestionarlo de otra forma.");
				closeInternal();
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void finalizarRemate() throws Exception{
		for(int i=0; i<modeloDetalleContrato.getRowCount(); i++){
			String fecha = new SimpleDateFormat("yyyy-MM-dd").format(modeloDetalleContrato.getValueAt(i, 8));
			Articulo a = new Articulo(Integer.parseInt(modeloDetalleContrato.getValueAt(i, 0).toString()), Double.parseDouble(modeloDetalleContrato.getValueAt(i, 6).toString()), Integer.parseInt(lblIdContrato.getText()), Double.parseDouble(modeloDetalleContrato.getValueAt(i, 7).toString()), fecha,Double.parseDouble(modeloDetalleContrato.getValueAt(i, 5).toString()));
			a.asignarPrecioVenta();			
		}
		liquidarContrato(Integer.parseInt(lblIdContrato.getText()));
		JOptionPane.showMessageDialog(null, "Se dió por finalizado el Contrato");
	}
	
	public void liquidarContrato(int id_contrato){		
		Connection con = MySQLConexion.getConexion();
		try {
			String[] a = arrayTablas(id_contrato);
			String sql = "UPDATE "+a[0]+" SET tb_estado_contrato_id_est=9 WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_contrato);
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
		
}
