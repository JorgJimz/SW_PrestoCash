package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.Cliente;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import controller.ClienteController;

@SuppressWarnings("serial")
public class Compra_Oro extends JInternalFrame {
	private JPanel contenedor;
	private JXSearchField txtDni;
	private JScrollPane jScrollPane1;
	private JButton btnFinalizar;
	private JTextArea txtDetalle;
	private JTextField txtTotal;
	private JTextField txtPesoBruto;
	private JXTitledSeparator jXTitledSeparator1;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtNombres;
	private JXTitledSeparator jSeparator1;

	public Compra_Oro() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(881, 519);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(881, 621));
		this.setBounds(0, 0, 881, 621);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 884, 597);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jSeparator1 = new JXTitledSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(12, 12, 843, 29);
		jSeparator1.setTitle("COMPRA DE ORO");
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		txtDni = new JXSearchField();
		contenedor.add(txtDni);
		txtDni.setBounds(12, 53, 213, 60);
		txtDni.setPrompt("INGRESE NÚMERO");
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 14));
		txtDni.setForeground(new java.awt.Color(0, 64, 128));
		txtDni.setBorder(BorderFactory.createTitledBorder(null, "D.N.I.",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					BuscarCliente(txtDni.getText());
				}
			}
		});

		txtNombres = new JTextField();
		contenedor.add(txtNombres);
		txtNombres.setBounds(237, 53, 618, 60);
		txtNombres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtNombres.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtNombres.setForeground(new java.awt.Color(0, 64, 128));
		txtNombres.setBorder(BorderFactory.createTitledBorder(null, "CLIENTE",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNombres.setEnabled(false);

		txtTelefono = new JTextField();
		contenedor.add(txtTelefono);
		txtTelefono.setBounds(12, 138, 213, 60);
		txtTelefono.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtTelefono.setForeground(new java.awt.Color(0, 64, 128));
		txtTelefono.setEnabled(false);
		txtTelefono.setBorder(BorderFactory.createTitledBorder(null,
				"TELÉFONO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtDireccion = new JTextField();
		contenedor.add(txtDireccion);
		txtDireccion.setBounds(237, 138, 618, 60);
		txtDireccion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtDireccion.setForeground(new java.awt.Color(0, 64, 128));
		txtDireccion.setEnabled(false);
		txtDireccion.setBorder(BorderFactory.createTitledBorder(null,
				"DIRECCIÓN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		jXTitledSeparator1 = new JXTitledSeparator();
		contenedor.add(jXTitledSeparator1);
		jXTitledSeparator1.setForeground(new java.awt.Color(128, 0, 0));
		jXTitledSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jXTitledSeparator1.setTitle("DETALLE DE LA COMPRA");
		jXTitledSeparator1.setBounds(12, 221, 843, 29);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 343, 843, 213);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		txtDetalle = new JTextArea();
		jScrollPane1.setViewportView(txtDetalle);
		txtDetalle.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtDetalle.setForeground(new java.awt.Color(0, 64, 128));
		txtDetalle.setPreferredSize(new java.awt.Dimension(608, 194));

		txtPesoBruto = new JTextField();
		contenedor.add(txtPesoBruto);
		txtPesoBruto.setBounds(12, 270, 269, 60);
		txtPesoBruto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtPesoBruto.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtPesoBruto.setForeground(new java.awt.Color(0, 64, 128));
		txtPesoBruto.setBorder(BorderFactory.createTitledBorder(null,
				"PESO BRUTO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtTotal = new JTextField();
		contenedor.add(txtTotal);
		txtTotal.setBounds(293, 270, 269, 60);
		txtTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtTotal.setForeground(new java.awt.Color(0, 64, 128));
		txtTotal.setBorder(BorderFactory.createTitledBorder(null, "TOTAL",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",
						Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnFinalizar = new JButton();
		contenedor.add(btnFinalizar);
		btnFinalizar.setBorderPainted(false);
		btnFinalizar.setText("PROCESAR COMPRA");
		btnFinalizar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnFinalizar.setContentAreaFilled(false);
		btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnFinalizar.setHorizontalAlignment(SwingConstants.LEFT);
		btnFinalizar.setBounds(574, 262, 281, 70);
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registrarCompra();
			}
		});

	}

	public void BuscarCliente(String dni) {
		try {
			Cliente c = new ClienteController().BuscarCliente(dni);
			if (Objects.nonNull(c)) {
				txtNombres.setText(c.getNombreCompleto());
				txtDireccion.setText(c.getDireccion() + " " + c.getDistrito());
				txtTelefono.setText(c.getTlf1() + "/" + c.getTlf2());
			} else {
				int o = JOptionPane
						.showConfirmDialog(
								null,
								"Este Cliente NO está registrado. ¿Desea proceder con el registro correspondiente?",
								"ALERTA", JOptionPane.YES_NO_OPTION);
				if (o == JOptionPane.YES_OPTION) {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void registrarCompra() {
		try {
			// Beans.Compra_Oro compra = new Beans.Compra_Oro(0, new
			// SimpleDateFormat("yyyy-MM-dd").format(new Date()),
			// txtDetalle.getText(), Double.parseDouble(txtPesoBruto.getText()),
			// Double.parseDouble(txtTotal.getText()), txtDni.getText());
			// compra.grabarCompra();
			JOptionPane.showMessageDialog(null, "Compra Registrada con Éxito!");
			// Egreso e = new Egreso(Principal.id_libro_caja, "COMPRA DE ORO",
			// "OTG", Double.parseDouble(txtTotal.getText()));
			// e.registrarEgreso();
			btnFinalizar.setEnabled(false);
			imprimirBoleta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void imprimirBoleta() {
		// Connection con = MySQLConexion.getConexion();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dni", txtDni.getText());
		parametros.put("nombre", txtNombres.getText());
		parametros.put("telefono", txtTelefono.getText());
		parametros.put("direccion", txtDireccion.getText());
		parametros.put("detalle", txtDetalle.getText());
		parametros.put("peso", txtPesoBruto.getText());
		parametros.put("total", txtTotal.getText());
		try {
			/*
			 * JasperReport reporte = (JasperReport) JRLoader
			 * .loadObject("boleta_compra_oro.jasper"); JasperPrint jasperPrint
			 * = JasperFillManager.fillReport(reporte, parametros,con);
			 * JasperPrintManager.printReport(jasperPrint, true);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
