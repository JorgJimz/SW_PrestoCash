package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTitledSeparator;

import common.Constantes;
import common.JIconTextField;
import common.Utiles;
import controller.ClienteController;
import controller.CompraController;
import model.Cliente;
import model.Compra;
import model.Egreso;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Compra_Oro extends JInternalFrame {
	private JPanel contenedor;
	private JXSearchField txtDni;
	private JScrollPane spDetalle;
	private JButton btnFinalizar;
	private JTextArea txtDetalle;
	private JTextField txtTotal;
	private JTextField txtPesoBruto;
	private JXTitledSeparator jXTitledSeparator1;
	private JTextField txtDireccion_F;
	private JTextField txtTelefono_F;
	private JTextField txtCliente_F;
	private JXTitledSeparator jSeparator1;
	private JIconTextField txtTlf2;
	private JButton btnGrabar;
	private JButton btnRegistrarCliente;
	private JComboBox cboDistrito;
	private JIconTextField txtDireccion;
	private JIconTextField txtTlf1;
	private JIconTextField txtEmail;
	private JIconTextField txtMaterno;
	private JIconTextField txtNombre;
	private JIconTextField txtPaterno;
	private JXTitledSeparator jXTitledSeparator2;
	private JComboBox cboTipoDocumento;
	private JIconTextField txtDniCliente;
	private JPanel pnlRegistro;
	private Cliente cliente;

	JInternalFrame iFrame;

	public Compra_Oro() {
		iFrame = this;
		this.setVisible(true);
		this.setLayout(null);
		this.setClosable(true);
		this.setSize(890, 710);
		this.setBounds(0, 0, 890, 710);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1220, 710);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jSeparator1 = new JXTitledSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(10, 10, 845, 29);
		jSeparator1.setTitle("COMPRA DE ORO");
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 14));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		txtDni = new JXSearchField();
		contenedor.add(txtDni);
		txtDni.setBounds(10, 50, 627, 60);
		txtDni.setPrompt("INGRESE NÚMERO");
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 14));
		txtDni.setForeground(new java.awt.Color(0, 64, 128));
		txtDni.setBorder(BorderFactory.createTitledBorder(null, "D.N.I.", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					BuscarCliente(txtDni.getText());
				}
			}
		});

		txtCliente_F = new JTextField();
		contenedor.add(txtCliente_F);
		txtCliente_F.setBounds(10, 126, 845, 60);
		txtCliente_F.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtCliente_F.setFont(new java.awt.Font("Segoe UI", 1, 14));
		txtCliente_F.setForeground(new java.awt.Color(0, 64, 128));
		txtCliente_F.setBorder(BorderFactory.createTitledBorder(null, "CLIENTE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtCliente_F.setEnabled(false);

		txtTelefono_F = new JTextField();
		contenedor.add(txtTelefono_F);
		txtTelefono_F.setBounds(10, 197, 208, 60);
		txtTelefono_F.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTelefono_F.setFont(new java.awt.Font("Segoe UI", 1, 14));
		txtTelefono_F.setForeground(new java.awt.Color(0, 64, 128));
		txtTelefono_F.setEnabled(false);
		txtTelefono_F.setBorder(BorderFactory.createTitledBorder(null, "TELÉFONO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtDireccion_F = new JTextField();
		contenedor.add(txtDireccion_F);
		txtDireccion_F.setBounds(228, 197, 627, 60);
		txtDireccion_F.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDireccion_F.setFont(new java.awt.Font("Segoe UI", 1, 14));
		txtDireccion_F.setForeground(new java.awt.Color(0, 64, 128));
		txtDireccion_F.setEnabled(false);
		txtDireccion_F.setBorder(BorderFactory.createTitledBorder(null, "DIRECCIÓN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		jXTitledSeparator1 = new JXTitledSeparator();
		contenedor.add(jXTitledSeparator1);
		jXTitledSeparator1.setForeground(new java.awt.Color(128, 0, 0));
		jXTitledSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 14));
		jXTitledSeparator1.setTitle("DETALLE DE LA COMPRA");
		jXTitledSeparator1.setBounds(10, 274, 845, 29);

		spDetalle = new JScrollPane();
		contenedor.add(spDetalle);
		spDetalle.setBounds(10, 391, 845, 215);
		spDetalle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		txtDetalle = new JTextArea();
		spDetalle.setViewportView(txtDetalle);
		txtDetalle.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDetalle.setForeground(new java.awt.Color(0, 64, 128));
		txtDetalle.setPreferredSize(new java.awt.Dimension(608, 194));

		txtPesoBruto = new JIconTextField();
		contenedor.add(txtPesoBruto);
		txtPesoBruto.setBounds(10, 320, 278, 60);
		txtPesoBruto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPesoBruto.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtPesoBruto.setForeground(new java.awt.Color(0, 64, 128));
		txtPesoBruto.setBorder(BorderFactory.createTitledBorder(null, "PESO BRUTO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtTotal = new JIconTextField();
		contenedor.add(txtTotal);
		txtTotal.setBounds(298, 320, 278, 60);
		txtTotal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtTotal.setForeground(new java.awt.Color(0, 64, 128));
		txtTotal.setBorder(BorderFactory.createTitledBorder(null, "TOTAL", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		btnFinalizar = new JButton(new ImageIcon("img/coins.png"));
		contenedor.add(btnFinalizar);
		btnFinalizar.setBorderPainted(false);
		btnFinalizar.setText("PROCESAR COMPRA");
		btnFinalizar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnFinalizar.setContentAreaFilled(false);
		btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnFinalizar.setHorizontalAlignment(SwingConstants.LEFT);
		btnFinalizar.setBounds(586, 310, 269, 70);
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor)) {
					RegistrarCompra();
				} else {
					Utiles.Mensaje("Completa el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		pnlRegistro = new JPanel();
		contenedor.add(pnlRegistro, JLayeredPane.DEFAULT_LAYER);
		pnlRegistro.setVisible(false);
		pnlRegistro.setOpaque(false);
		pnlRegistro.setLayout(null);
		pnlRegistro.setBounds(865, 7, 333, 651);

		txtDniCliente = new JIconTextField();
		pnlRegistro.add(txtDniCliente);
		txtDniCliente.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDniCliente.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO DOCUMENTO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDniCliente.setBounds(136, 45, 185, 50);

		cboTipoDocumento = new JComboBox();
		pnlRegistro.add(cboTipoDocumento);
		cboTipoDocumento.setModel(Constantes.TipoDocumentoModel);
		cboTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
		cboTipoDocumento.setFont(new java.awt.Font("Segoe UI", 1, 14));
		cboTipoDocumento.setForeground(new java.awt.Color(0, 64, 128));
		cboTipoDocumento.setBorder(BorderFactory.createTitledBorder(null, "TIPO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		cboTipoDocumento.setBounds(12, 45, 118, 50);
		jXTitledSeparator2 = new JXTitledSeparator("NUEVO CLIENTE");
		pnlRegistro.add(jXTitledSeparator2);

		jXTitledSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jXTitledSeparator2.setForeground(new java.awt.Color(128, 0, 0));
		jXTitledSeparator2.setTitle("NUEVO CLIENTE");
		jXTitledSeparator2.setBounds(12, 0, 309, 39);

		txtPaterno = new JIconTextField();
		pnlRegistro.add(txtPaterno);
		txtPaterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO PATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtPaterno.setBounds(12, 103, 309, 50);

		txtNombre = new JIconTextField();
		pnlRegistro.add(txtNombre);
		txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtNombre.setBorder(BorderFactory.createTitledBorder(null, "NOMBRES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtNombre.setBounds(12, 220, 309, 50);

		txtMaterno = new JIconTextField();
		pnlRegistro.add(txtMaterno);
		txtMaterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtMaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO MATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtMaterno.setBounds(12, 162, 309, 50);

		txtEmail = new JIconTextField();
		pnlRegistro.add(txtEmail);
		txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtEmail.setBorder(BorderFactory.createTitledBorder(null, "E-MAIL", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtEmail.setBounds(12, 396, 309, 50);

		txtTlf1 = new JIconTextField();
		pnlRegistro.add(txtTlf1);
		txtTlf1.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTlf1.setBorder(BorderFactory.createTitledBorder(null, "TELÉFONO 1", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtTlf1.setBounds(12, 279, 309, 50);

		txtDireccion = new JIconTextField();
		pnlRegistro.add(txtDireccion);
		txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDireccion.setBorder(BorderFactory.createTitledBorder(null, "DIRECCIÓN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtDireccion.setBounds(12, 454, 309, 50);

		cboDistrito = new JComboBox();
		pnlRegistro.add(cboDistrito);
		cboDistrito.setModel(Constantes.DistritoModel);
		cboDistrito.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboDistrito.setForeground(new java.awt.Color(0, 64, 128));
		cboDistrito.setBorder(BorderFactory.createTitledBorder(null, "DISTRITO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		cboDistrito.setBounds(12, 513, 309, 50);

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		pnlRegistro.add(btnGrabar);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setBounds(257, 575, 64, 64);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(pnlRegistro)) {
					Cliente c = new Cliente();
					c.setTDocumento(String.valueOf(cboTipoDocumento.getSelectedItem()));
					c.setDocumento(txtDniCliente.getText().trim());
					c.setNombres(txtNombre.getText().toUpperCase().trim());
					c.setPaterno(txtPaterno.getText().toUpperCase().trim());
					c.setMaterno(txtMaterno.getText().toUpperCase().trim());
					c.setEmail(txtEmail.getText().toUpperCase().trim());
					c.setTlf1(txtTlf1.getText().trim());
					c.setTlf2(txtTlf2.getText().trim());
					c.setDireccion(txtDireccion.getText().toUpperCase().trim());
					c.setDistrito(cboDistrito.getSelectedItem().toString().toUpperCase().trim());
					c.setCategoriaId("BUENO");
					c.setObs("NUEVO COMPRADOR");
					c.setStatus(Cliente.ACTIVO);
					c.setFechaCreacion(String.valueOf(LocalDate.now()));
					c.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new ClienteController().RegistrarCliente(c);
					Utiles.Limpiar(pnlRegistro);
					Utiles.Mensaje("¡Cliente Registrado!", JOptionPane.INFORMATION_MESSAGE);
					txtDni.setText(c.getDocumento());
					BuscarCliente(c.getDocumento());
					btnRegistrarCliente.doClick();
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtTlf2 = new JIconTextField();
		pnlRegistro.add(txtTlf2);
		txtTlf2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTlf2.setBorder(BorderFactory.createTitledBorder(null, "TELÉFONO 2", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtTlf2.setBounds(12, 337, 309, 50);

		btnRegistrarCliente = new JButton(new ImageIcon("img/add_user.png"));
		contenedor.add(btnRegistrarCliente, JLayeredPane.DEFAULT_LAYER);
		btnRegistrarCliente.setBounds(647, 45, 208, 70);
		btnRegistrarCliente.setOpaque(false);
		btnRegistrarCliente.setText("NUEVO CLIENTE");
		btnRegistrarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistrarCliente.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnRegistrarCliente.setBorderPainted(false);
		btnRegistrarCliente.setContentAreaFilled(false);
		btnRegistrarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!pnlRegistro.isVisible()) {
					iFrame.setSize(1220, 710);
					pnlRegistro.setVisible(true);
				} else {
					iFrame.setSize(890, 710);
					pnlRegistro.setVisible(false);
				}
			}
		});
	}

	public void BuscarCliente(String dni) {
		try {
			if (!txtDni.getText().isEmpty()) {
				cliente = new ClienteController().BuscarCliente(dni);
				if (Objects.nonNull(cliente)) {
					txtCliente_F.setText(cliente.getNombreCompleto());
					txtDireccion_F.setText(cliente.getDireccion() + " " + cliente.getDistrito());
					txtTelefono_F.setText(cliente.getTlf1() + "/" + cliente.getTlf2());
				} else {
					Utiles.Mensaje("Cliente NO registrado.", JOptionPane.INFORMATION_MESSAGE);
					txtDniCliente.setText(txtDni.getText());
					txtDni.setText("");
					btnRegistrarCliente.doClick();
					txtPaterno.requestFocus();
				}
			} else {
				Utiles.Mensaje("Ingresa un número de D.N.I. válido", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void RegistrarCompra() {
		try {
			Compra cmp = new Compra();
			cmp.setFecha(String.valueOf(LocalDate.now()));
			cmp.setDescripcion("COMPRA DE ORO");
			cmp.setPeso(new BigDecimal(txtPesoBruto.getText()));
			cmp.setImporte(new BigDecimal(txtTotal.getText()));
			cmp.setObs(txtDetalle.getText());
			cmp.setCliente(cliente);
			cmp.setUsuarioCreacion(Principal.LOGGED.getLogin());
			cmp.setFechaCreacion(String.valueOf(LocalDate.now()));
			Egreso e = new Egreso();
			e.setLibroCaja(Principal.LIBRO_CAJA);
			e.setDescripcion("ORO [" + cmp.getPeso() + " g.]");
			e.setImporte(cmp.getImporte());
			e.setTipo("CAU");
			e.setMoneda("SOLES");
			new CompraController().GenerarCompraOro(cmp, e);
			Utiles.Mensaje("Compra realizada con éxito. Se imprimirá comprobante...", JOptionPane.INFORMATION_MESSAGE);
			btnFinalizar.setEnabled(false);
			ImprimirComprobante(cmp);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ImprimirComprobante(Compra c) {
		ArrayList<Compra> arreglo_compra = new ArrayList<Compra>();
		arreglo_compra.add(c);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/compra_oro.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
					new JRBeanCollectionDataSource(arreglo_compra));
			JasperPrintManager.printReport(jasperPrint, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
