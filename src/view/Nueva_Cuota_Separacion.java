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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Beans.Ingreso;
import Beans.Separacion;
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
public class Nueva_Cuota_Separacion extends JInternalFrame {
	private int nro_contrato;
	private JLabel lblP;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel lblDniCliente;
	private JLabel jLabel8;
	private JButton btnSalir;
	private JButton btnGrabar;
	private JLabel lblResta;
	private JLabel jLabel7;
	private JTextField txtNuevaCuota;
	private JLabel jLabel6;
	private JLabel lblValorActual;
	private JScrollPane jScrollPane1;
	private JTable tbHistorialCuotas;
	private JLabel lblPrecioVenta;
	private JLabel lblCapital;
	private JLabel lblDescripcionArticulo;
	private JLabel lblCodigoArticulo;
	private JLabel lblNumeroContrato;
	private JLabel jLabel1;
	private JPanel contenedor;
	private int cantidad;
	DefaultTableModel modeloCuotas = new DefaultTableModel(null, new String[] {
			"FECHA", "MONTO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Nueva_Cuota_Separacion(int contrato) {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(959, 681);
		nro_contrato = contrato;

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1279, 876);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("NRO.CONTRATO");
		jLabel1.setBounds(22, 23, 203, 31);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("ARTÍCULO SEPARADO");
		jLabel2.setBounds(363, 20, 234, 31);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("CAPITAL");
		jLabel3.setBounds(413, 77, 110, 31);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("P.VENTA");
		jLabel4.setBounds(677, 77, 118, 31);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		lblNumeroContrato = new JLabel();
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(211, 20, 131, 32);
		lblNumeroContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblNumeroContrato.setBackground(new java.awt.Color(255, 255, 255));
		lblNumeroContrato.setOpaque(true);
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));

		lblCodigoArticulo = new JLabel();
		contenedor.add(lblCodigoArticulo);
		lblCodigoArticulo.setBounds(602, 20, 82, 32);
		lblCodigoArticulo.setBackground(new java.awt.Color(255, 255, 255));
		lblCodigoArticulo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCodigoArticulo.setOpaque(true);
		lblCodigoArticulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

		lblDescripcionArticulo = new JLabel();
		contenedor.add(lblDescripcionArticulo);
		lblDescripcionArticulo.setBounds(690, 20, 230, 32);
		lblDescripcionArticulo.setOpaque(true);
		lblDescripcionArticulo.setBorder(BorderFactory.createMatteBorder(1, 1,
				1, 1, new java.awt.Color(0, 0, 0)));
		lblDescripcionArticulo.setBackground(new java.awt.Color(255, 255, 255));
		lblDescripcionArticulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

		lblCapital = new JLabel();
		contenedor.add(lblCapital);
		lblCapital.setBounds(523, 77, 127, 32);
		lblCapital.setBackground(new java.awt.Color(255, 255, 255));
		lblCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblCapital.setOpaque(true);
		lblCapital.setFont(new java.awt.Font("Segoe UI", 1, 22));

		lblPrecioVenta = new JLabel();
		contenedor.add(lblPrecioVenta);
		lblPrecioVenta.setBounds(793, 77, 127, 32);
		lblPrecioVenta.setBackground(new java.awt.Color(255, 255, 255));
		lblPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblPrecioVenta.setOpaque(true);
		lblPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(22, 131, 898, 322);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("VALOR ACTUAL");
		jLabel5.setBounds(24, 472, 175, 31);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		lblValorActual = new JLabel();
		contenedor.add(lblValorActual);
		lblValorActual.setBounds(211, 472, 155, 32);
		lblValorActual.setBackground(new java.awt.Color(255, 255, 255));
		lblValorActual.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblValorActual.setOpaque(true);
		lblValorActual.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("NUEVA CUOTA");
		jLabel6.setBounds(23, 517, 194, 31);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		txtNuevaCuota = new JTextField();
		contenedor.add(txtNuevaCuota);
		txtNuevaCuota.setBounds(212, 516, 155, 32);
		txtNuevaCuota.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtNuevaCuota.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtNuevaCuota.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					double monto_actual = Double.parseDouble(lblValorActual
							.getText());
					double nueva_cuota = Double.parseDouble(txtNuevaCuota
							.getText());
					lblResta.setText((monto_actual - nueva_cuota) + "");
				}
			}
		});

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("RESTA");
		jLabel7.setBounds(23, 560, 86, 31);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		lblResta = new JLabel();
		contenedor.add(lblResta);
		lblResta.setBounds(212, 562, 156, 32);
		lblResta.setBackground(new java.awt.Color(255, 255, 255));
		lblResta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblResta.setOpaque(true);
		lblResta.setFont(new java.awt.Font("Segoe UI", 1, 22));

		btnGrabar = new JButton();
		contenedor.add(btnGrabar);
		btnGrabar.setText("GRABAR");
		btnGrabar.setBounds(386, 470, 308, 124);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtNuevaCuota.getText().equalsIgnoreCase("") && Integer.parseInt(txtNuevaCuota.getText())>0 && !lblResta.getText().equalsIgnoreCase("")){
					registrarNuevaCuotaSeparacion();	
				}else {
					JOptionPane.showMessageDialog(null, "Complete el importe que se abonará luego presione ENTER para calcular el saldo restante.");
					txtNuevaCuota.setBackground(Color.RED);
					lblResta.setOpaque(true);
					lblResta.setBackground(Color.RED);
				}
				
			}
		});

		btnSalir = new JButton();
		contenedor.add(btnSalir);
		btnSalir.setText("SALIR");
		btnSalir.setBounds(706, 465, 214, 129);
		btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("SEPARADO POR");
		jLabel8.setBounds(22, 77, 196, 31);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		lblDniCliente = new JLabel();
		contenedor.add(lblDniCliente);
		lblDniCliente.setBounds(210, 77, 185, 32);
		lblDniCliente.setOpaque(true);
		lblDniCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblDniCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		lblDniCliente.setFont(new java.awt.Font("Segoe UI", 1, 22));

		lblP = new JLabel();
		contenedor.add(lblP);
		lblP.setBounds(34, 61, 10, 10);
		lblP.setVisible(false);

		tbHistorialCuotas = new JTable();
		tbHistorialCuotas.setRowHeight(30);
		tbHistorialCuotas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbHistorialCuotas.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbHistorialCuotas.getTableHeader().setForeground(new Color(181, 0, 0));
		jScrollPane1.setViewportView(tbHistorialCuotas);		
		cargarSeparacion(validarDobleAsociacion(contrato).toString().split("-")[0]);

	}

	public void cargarSeparacion(String cod_articulo) {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_separacion s INNER JOIN tb_articulo a ON s.tb_articulo_cod_art = a.cod_art WHERE s.id_con_asoc=? AND s.tb_articulo_cod_art=? AND est_sep=1";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nro_contrato);
			pst.setInt(2, Integer.parseInt(cod_articulo));
			ResultSet rs = pst.executeQuery();			
			while (rs.next()) {
				modeloCuotas.addRow(new Object[] {
						new SimpleDateFormat("dd-MMM-yyyy").format(
								rs.getDate("fec_sep")).toUpperCase(),
						rs.getDouble("cta_sep") });
				lblNumeroContrato.setText(rs.getInt("id_con_asoc") + "");
				lblCodigoArticulo
						.setText(rs.getInt("tb_articulo_cod_art") + "");
				lblCapital.setText(rs.getDouble("cap_sep") + "");
				lblPrecioVenta.setText(rs.getDouble("pre_sep") + "");
				lblDescripcionArticulo.setText(rs.getString("des_art") + " "
						+ rs.getString("mar_art"));
				lblDniCliente.setText(rs.getString("tb_cliente_doc_cli"));
				lblP.setText(rs.getString("p_con_asoc"));
			}
			tbHistorialCuotas.setModel(modeloCuotas);
			calcularDeudaActual();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void calcularDeudaActual() {
		double pagos = 0.00;
		for (int i = 0; i < modeloCuotas.getRowCount(); i++) {
			pagos += Double.parseDouble(modeloCuotas.getValueAt(i, 1)
					.toString());
		}
		double monto_deuda = Double.parseDouble(lblPrecioVenta.getText())
				- pagos;
		lblValorActual.setText(monto_deuda + "");
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
	
	public void registrarNuevaCuotaSeparacion() {
		Connection con = MySQLConexion.getConexion();
		try {
			Separacion nueva_cuota = new Separacion(
					Integer.parseInt(lblNumeroContrato.getText()),
					Integer.parseInt(lblCodigoArticulo.getText()),
					Double.parseDouble(lblCapital.getText()),
					Double.parseDouble(lblPrecioVenta.getText()),
					Double.parseDouble(txtNuevaCuota.getText()),
					Double.parseDouble(lblResta.getText()),
					lblDniCliente.getText(),
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 1);
			nueva_cuota.registrarSeparacion();
			JOptionPane.showMessageDialog(null,
					"Nueva cuota registrada con éxito.");
			Ingreso ingreso = new Ingreso(Principal.id_libro_caja,
					lblP.getText()+"-"+lblNumeroContrato.getText(), "SEP N°"
							+ (modeloCuotas.getRowCount() + 1), 0.00, 0.00,
					Double.parseDouble(txtNuevaCuota.getText()));
			ingreso.registrarIngreso();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void cerrar() {
		this.dispose();
	}
}
