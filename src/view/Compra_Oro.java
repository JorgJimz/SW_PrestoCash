package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXTitledSeparator;

@SuppressWarnings("serial")
public class Compra_Oro extends JInternalFrame{
	private JPanel contenedor;
	private JTextField txtDni;
	private JScrollPane jScrollPane1;
	private JButton btnFinalizar;
	private JTextArea txtDetalle;
	private JTextField txtTotal;
	private JTextField txtPesoBruto;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JXTitledSeparator jXTitledSeparator1;
	private JTextField txtDireccio;
	private JTextField txtTelefono;
	private JTextField txtNombres;
	private JXTitledSeparator jSeparator1;
	private JLabel jLabel1;

	public Compra_Oro() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(881, 519);
		this.setClosable(true);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 865, 481);
		contenedor.setBackground(new java.awt.Color(255,200,147));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("DOC.CLIENTE:");
		jLabel1.setBounds(12, 53, 143, 34);
		jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel1.setForeground(new java.awt.Color(0,128,0));

		jSeparator1 = new JXTitledSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 12, 812, 29);
		jSeparator1.setTitle("COMPRA DE ORO");
		jSeparator1.setFont(new java.awt.Font("Segoe UI",1,20));
		jSeparator1.setForeground(new java.awt.Color(128,0,0));

		txtDni = new JTextField();
		contenedor.add(txtDni);
		txtDni.setBounds(155, 53, 143, 34);
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtDni.setFont(new java.awt.Font("Segoe UI",1,20));
		txtDni.setForeground(new java.awt.Color(0,64,128));
		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					seleccionarCliente(txtDni.getText());
				}
			}
		});

		txtNombres = new JTextField();
		contenedor.add(txtNombres);
		txtNombres.setBounds(310, 53, 514, 34);
		txtNombres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtNombres.setFont(new java.awt.Font("Segoe UI",1,20));
		txtNombres.setForeground(new java.awt.Color(0,64,128));
		txtNombres.setEnabled(false);

		txtTelefono = new JTextField();
		contenedor.add(txtTelefono);
		txtTelefono.setBounds(12, 99, 143, 34);
		txtTelefono.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtTelefono.setFont(new java.awt.Font("Segoe UI",1,20));
		txtTelefono.setForeground(new java.awt.Color(0,64,128));
		txtTelefono.setEnabled(false);

		txtDireccio = new JTextField();
		contenedor.add(txtDireccio);
		txtDireccio.setBounds(167, 99, 657, 34);
		txtDireccio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtDireccio.setFont(new java.awt.Font("Segoe UI",1,20));
		txtDireccio.setForeground(new java.awt.Color(0,64,128));
		txtDireccio.setEnabled(false);

		jXTitledSeparator1 = new JXTitledSeparator();
		contenedor.add(jXTitledSeparator1);
		jXTitledSeparator1.setForeground(new java.awt.Color(128,0,0));
		jXTitledSeparator1.setFont(new java.awt.Font("Segoe UI",1,20));
		jXTitledSeparator1.setTitle("DETALLE DE LA COMPRA");
		jXTitledSeparator1.setBounds(12, 160, 812, 29);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 201, 662, 235);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

		txtDetalle = new JTextArea();
		jScrollPane1.setViewportView(txtDetalle);
		txtDetalle.setFont(new java.awt.Font("Segoe UI",1,22));
		txtDetalle.setForeground(new java.awt.Color(0,64,128));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("PESO BRUTO");
		jLabel2.setBounds(686, 201, 138, 34);
		jLabel2.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel2.setForeground(new java.awt.Color(0,128,0));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("TOTAL");
		jLabel3.setBounds(686, 281, 138, 34);
		jLabel3.setFont(new java.awt.Font("Segoe UI",1,20));
		jLabel3.setForeground(new java.awt.Color(0,128,0));
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);

		txtPesoBruto = new JTextField();
		contenedor.add(txtPesoBruto);
		txtPesoBruto.setBounds(686, 241, 143, 34);
		txtPesoBruto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtPesoBruto.setFont(new java.awt.Font("Segoe UI",1,20));
		txtPesoBruto.setForeground(new java.awt.Color(0,64,128));

		txtTotal = new JTextField();
		contenedor.add(txtTotal);
		txtTotal.setBounds(686, 321, 143, 34);
		txtTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		txtTotal.setFont(new java.awt.Font("Segoe UI",1,20));
		txtTotal.setForeground(new java.awt.Color(0,64,128));

		btnFinalizar = new JButton();
		contenedor.add(btnFinalizar);
		btnFinalizar.setText("COMPRAR");
		btnFinalizar.setBounds(686, 376, 143, 60);
		btnFinalizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
		btnFinalizar.setFont(new java.awt.Font("Segoe UI",1,20));
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registrarCompra();				
			}
		});

	}
	
	public void seleccionarCliente(String dni) {
		//Connection con = MySQLConexion.getConexion();
		try {
			
			//PreparedStatement pst = con.prepareStatement(sql);
			//pst.setString(1, dni);
			/*ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtNombres.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));
				txtDireccio.setText(rs.getString("dir_cli"));
				if(rs.getString("fi1_cli").trim().equalsIgnoreCase("-") || rs.getString("fi1_cli").trim().equalsIgnoreCase("A")){
					txtTelefono.setText(rs.getString("mo1_cli"));
				}else{
					txtTelefono.setText(rs.getString("fi1_cli"));
				}
			} else {
				int o = JOptionPane
						.showConfirmDialog(
								null,
								"Este Cliente NO está registrado. ¿Desea proceder con el registro correspondiente?",
								"ALERTA", JOptionPane.YES_NO_OPTION);
				if (o == JOptionPane.YES_OPTION) {
					//Mantenimiento_Clientes nuevo_cliente = new Mantenimiento_Clientes(null);
					Principal.dskPrincipal.add(nuevo_cliente);
					this.dispose();
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public void registrarCompra(){		
		try {
			//Beans.Compra_Oro compra = new Beans.Compra_Oro(0, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), txtDetalle.getText(), Double.parseDouble(txtPesoBruto.getText()),  Double.parseDouble(txtTotal.getText()), txtDni.getText());
			//compra.grabarCompra();
			JOptionPane.showMessageDialog(null, "Compra Registrada con Éxito!");
			//Egreso e = new Egreso(Principal.id_libro_caja, "COMPRA DE ORO", "OTG", Double.parseDouble(txtTotal.getText()));
			//e.registrarEgreso();
			btnFinalizar.setEnabled(false);
			imprimirBoleta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirBoleta() {
		//Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dni", txtDni.getText());
		parametros.put("nombre", txtNombres.getText());
		parametros.put("telefono", txtTelefono.getText());
		parametros.put("direccion", txtDireccio.getText());
		parametros.put("detalle", txtDetalle.getText());
		parametros.put("peso", txtPesoBruto.getText());
		parametros.put("total", txtTotal.getText());
		try {
			/*JasperReport reporte = (JasperReport) JRLoader
					.loadObject("boleta_compra_oro.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,con);
			JasperPrintManager.printReport(jasperPrint, true);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
