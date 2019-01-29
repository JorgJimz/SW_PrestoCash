package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import model.Articulo;
import model.Cliente;
import model.Contrato;
import model.EArticulo;
import model.EContrato;
import model.Ingreso;
import model.Separacion;
import model.Venta;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.search.SearchFactory;

import common.Constantes;
import common.JIconTextField;
import common.NumberToLetter;
import common.Utiles;
import controller.ArticuloController;
import controller.ClienteController;
import controller.ContratoController;
import controller.VentaController;

@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
public class Venta_Articulos extends JInternalFrame {

	private JScrollPane spArticulos;
	private JXTable tbArticulos;
	private JLabel lblCapital;
	private JButton btnLiberarSeparacion;
	private JTable tbHistorialSeparacion;
	private JButton btnBuscar;
	private JLabel txtPrecioVenta;
	private JScrollPane spHistorialSeparacion;
	private JLabel lblTelefono;
	private JLabel lblContratoAsociado;
	private JTextField txtDni;
	private JLabel lblLetra;
	private JLabel lblDirCli;
	private JLabel lblCliente;
	private JButton btnRegistrarCliente;
	private JButton btnCancelarSeparacion;
	private JButton btnFinSeparacion;
	private JLabel txtTotalFecha;
	private JIconTextField txtTlf2;
	private JButton btnGrabar;
	private JComboBox cboDistrito;
	private JIconTextField txtDireccion;
	private JIconTextField txtTlf1;
	private JIconTextField txtEmail;
	private JIconTextField txtMaterno;
	private JIconTextField txtNombre;
	private JIconTextField txtPaterno;
	private JXTitledSeparator jXTitledSeparator1;
	private JComboBox cboTipoDocumento;
	private JIconTextField txtDniCliente;
	private JPanel pnlRegistro;
	private JTextField txtMonto;
	private JButton btnCancelar;
	private JPanel pnlSeparacion;
	private JTextArea txtObservaciones;
	private JScrollPane spObservaciones;
	private JLabel jLabel1;
	private JLayeredPane pnlRemate;
	private JButton btnSeparar;
	private JButton btnRematar;
	private JXTitledSeparator jSeparator2;
	private JButton btnVender;
	private JXTitledSeparator jSeparator1;
	private JLabel lblIdSC;
	private JLayeredPane contenedor;

	JInternalFrame iFrame;

	private Cliente cliente;
	private Articulo articulo;
	private boolean MOUSECLICKED;
	private BigDecimal totalFecha = BigDecimal.ZERO;

	public DefaultTableModel HistorialSeparacionModel = new DefaultTableModel(null,
			new String[] { "FECHA", "IMPORTE", "CLIENTE" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Venta_Articulos() {
		this("");
	}

	public Venta_Articulos(String documento) {
		iFrame = this;
		MOUSECLICKED = true;
		new ArticuloController().CargarVitrina();
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("Venta de Artículos");
		this.setSize(1025, 720);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "<html><h3><b>¿Salir?</b></h3></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		contenedor = new JLayeredPane();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1360, 720);
		contenedor.setOpaque(true);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		btnRematar = new JButton(new ImageIcon("img/vender.png"));
		contenedor.add(btnRematar);
		contenedor.moveToFront(btnRematar);
		btnRematar.setToolTipText("REMATE");
		btnRematar.setOpaque(false);
		btnRematar.setBorderPainted(false);
		btnRematar.setContentAreaFilled(false);
		btnRematar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRematar.setBounds(840, 46, 70, 70);
		btnRematar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tbArticulos.getSelectedRow() >= 0 && lblCliente.getText().length() > 0) {
					articulo = new ArticuloController().ObtenerArticulo(
							Integer.parseInt(String.valueOf(tbArticulos.getValueAt(tbArticulos.getSelectedRow(), 1))));
					spArticulos.setSize(spArticulos.getWidth(), 210);
					pnlRemate.setVisible(true);
					btnRematar.setEnabled(false);
					btnSeparar.setEnabled(false);
					btnBuscar.setEnabled(false);
					btnRegistrarCliente.setEnabled(false);
					tbArticulos.setEnabled(false);
					MOUSECLICKED = false;
				} else {
					Utiles.Mensaje("Seleccione Cliente y/o Artículo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSeparar = new JButton(new ImageIcon("img/separar.png"));
		contenedor.add(btnSeparar);
		contenedor.moveToFront(btnSeparar);
		btnSeparar.setToolTipText("SEPARACIÓN");
		btnSeparar.setOpaque(false);
		btnSeparar.setBorderPainted(false);
		btnSeparar.setContentAreaFilled(false);
		btnSeparar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSeparar.setBounds(765, 46, 70, 70);
		btnSeparar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tbArticulos.getSelectedRow() >= 0 && lblCliente.getText().length() > 0) {
					totalFecha = BigDecimal.ZERO;
					articulo = new ArticuloController().ObtenerArticulo(
							Integer.parseInt(String.valueOf(tbArticulos.getValueAt(tbArticulos.getSelectedRow(), 1))));
					pnlSeparacion.setVisible(true);
					txtPrecioVenta.setText(String.valueOf(articulo.getPrecioVenta()));
					spArticulos.setSize(spArticulos.getWidth(), 125);
					pnlSeparacion.setVisible(true);
					btnRematar.setEnabled(false);
					btnSeparar.setEnabled(false);
					btnBuscar.setEnabled(false);
					btnRegistrarCliente.setEnabled(false);
					tbArticulos.setEnabled(false);
					ListarHistorial(articulo.getSeparacions());
					MOUSECLICKED = false;
				} else {
					Utiles.Mensaje("Seleccione Cliente y/o Artículo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		spArticulos = new JScrollPane();
		contenedor.add(spArticulos);
		contenedor.moveToBack(spArticulos);
		spArticulos.setBounds(16, 159, 969, 500);
		spArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spArticulos.setBackground(new java.awt.Color(255, 255, 255));
		spArticulos.setEnabled(false);

		tbArticulos = new JXTable();
		spArticulos.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setModel(Constantes.VitrinaModel);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.removeColumn(tbArticulos.getColumnModel().getColumn(8));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (MOUSECLICKED) {
					int estado = Integer.parseInt(
							String.valueOf(tbArticulos.getModel().getValueAt(tbArticulos.getSelectedRow(), 8)));
					if (estado == 2) {
						btnRematar.setEnabled(false);
					} else {
						btnRematar.setEnabled(true);
						btnSeparar.setEnabled(true);
						btnBuscar.setEnabled(true);
					}
				}
			}
		});

		lblIdSC = new JLabel();
		lblIdSC.setBackground(Color.red);
		contenedor.add(lblIdSC);
		lblIdSC.setBounds(12, 301, 43, 16);
		lblIdSC.setVisible(false);

		jSeparator1 = new JXTitledSeparator("ARTÍCULOS EN VENTA");
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(18, 114, 967, 39);
		jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jSeparator1.setForeground(new java.awt.Color(128, 0, 0));

		jSeparator2 = new JXTitledSeparator("DATOS DEL COMPRADOR");
		contenedor.add(jSeparator2);
		jSeparator2.setBounds(18, 12, 967, 39);
		jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jSeparator2.setForeground(new java.awt.Color(128, 0, 0));

		lblCliente = new JLabel();
		contenedor.add(lblCliente);
		lblCliente.setBounds(250, 51, 434, 50);
		lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblCliente.setBackground(new java.awt.Color(255, 255, 255));
		lblCliente.setBorder(BorderFactory.createTitledBorder(null, "NOMBRE COMPLETO DEL CLIENTE",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtDni = new JTextField(documento);
		contenedor.add(txtDni);
		txtDni.setBounds(18, 52, 226, 50);
		txtDni.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO DOCUMENTO IDENTIDAD",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ObtenerCliente();
				}
			}
		});

		txtDni.setEnabled(true);
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 16));

		lblContratoAsociado = new JLabel();
		contenedor.add(lblContratoAsociado);
		lblContratoAsociado.setBounds(58, 528, 10, 10);
		lblContratoAsociado.setVisible(false);

		lblTelefono = new JLabel();
		contenedor.add(lblTelefono);
		lblTelefono.setBounds(49, 540, 10, 10);
		lblTelefono.setVisible(false);

		lblCapital = new JLabel();
		contenedor.add(lblCapital);
		lblCapital.setBounds(44, 534, 10, 10);
		lblCapital.setVisible(false);

		lblDirCli = new JLabel();
		contenedor.add(lblDirCli);
		lblDirCli.setBounds(30, 91, 37, 16);
		lblDirCli.setVisible(false);

		lblLetra = new JLabel();
		contenedor.add(lblLetra);
		lblLetra.setBounds(38, 46, 10, 10);
		lblLetra.setVisible(false);

		pnlRemate = new JLayeredPane();
		contenedor.add(pnlRemate);
		pnlRemate.setBounds(16, 379, 996, 226);
		pnlRemate.setLayout(null);
		pnlRemate.setVisible(false);

		pnlSeparacion = new JPanel();
		contenedor.add(pnlSeparacion);
		pnlSeparacion.setBounds(16, 294, 979, 380);
		pnlSeparacion.setOpaque(false);
		pnlSeparacion.setLayout(null);
		pnlSeparacion.setVisible(false);

		btnRegistrarCliente = new JButton(new ImageIcon("img/add_user.png"));
		contenedor.add(btnRegistrarCliente, JLayeredPane.DEFAULT_LAYER);
		btnRegistrarCliente.setBounds(915, 46, 70, 70);
		btnRegistrarCliente.setToolTipText("NUEVO CLIENTE");
		btnRegistrarCliente.setOpaque(false);
		btnRegistrarCliente.setBorderPainted(false);
		btnRegistrarCliente.setContentAreaFilled(false);
		btnRegistrarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!pnlRegistro.isVisible()) {
					iFrame.setSize(1370, 720);
					pnlRegistro.setVisible(true);
				} else {
					iFrame.setSize(1025, 720);
					pnlRegistro.setVisible(false);
				}
			}
		});

		pnlRegistro = new JPanel();
		contenedor.add(pnlRegistro, JLayeredPane.DEFAULT_LAYER);
		pnlRegistro.setBounds(1006, 12, 333, 651);
		pnlRegistro.setOpaque(false);
		pnlRegistro.setLayout(null);
		pnlRegistro.setVisible(false);

		btnBuscar = new JButton();
		contenedor.add(btnBuscar, JLayeredPane.DEFAULT_LAYER);
		btnBuscar.setIcon(new ImageIcon("img/find.png"));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setToolTipText("SEPARACIÓN");
		btnBuscar.setOpaque(false);
		btnBuscar.setBounds(690, 46, 70, 70);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFactory.getInstance().showFindDialog(tbArticulos, tbArticulos.getSearchable());
			}
		});

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
		cboTipoDocumento.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboTipoDocumento.setForeground(new java.awt.Color(0, 64, 128));
		cboTipoDocumento.setBorder(BorderFactory.createTitledBorder(null, "TIPO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		cboTipoDocumento.setBounds(12, 45, 118, 50);

		jXTitledSeparator1 = new JXTitledSeparator("NUEVO CLIENTE");
		pnlRegistro.add(jXTitledSeparator1);
		jXTitledSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jXTitledSeparator1.setForeground(new java.awt.Color(128, 0, 0));
		jXTitledSeparator1.setBounds(12, 0, 309, 39);

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
		AutoCompleteDecorator.decorate(cboDistrito);

		btnGrabar = new JButton();
		pnlRegistro.add(btnGrabar);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setIcon(new ImageIcon("img/grabar.png"));
		btnGrabar.setBorderPainted(false);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.setBounds(257, 575, 64, 64);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
					Utiles.Mensaje("Comprador Registrado.", JOptionPane.INFORMATION_MESSAGE);
					txtDni.setText(c.getDocumento());
					ObtenerCliente();
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

		btnCancelar = new JButton(new ImageIcon("img/cancelar.png"));
		pnlRemate.add(btnCancelar);
		btnCancelar.setText("CANCELAR");
		btnCancelar.setOpaque(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnCancelar.setBounds(772, 145, 200, 70);
		btnCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utiles.Limpiar(pnlRemate);
				spArticulos.setSize(spArticulos.getWidth(), 500);
				tbArticulos.setEnabled(true);
				btnRematar.setEnabled(true);
				btnSeparar.setEnabled(true);
				btnBuscar.setEnabled(true);
				btnRegistrarCliente.setEnabled(true);
				pnlRemate.setVisible(false);
				MOUSECLICKED = true;
			}
		});

		txtMonto = new JTextField();
		pnlSeparacion.add(txtMonto);
		txtMonto.setBounds(0, 112, 205, 50);
		txtMonto.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtMonto.setBorder(BorderFactory.createTitledBorder(null, "NUEVO IMPORTE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtMonto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					BigDecimal diff = articulo.getPrecioVenta()
							.subtract(totalFecha.add(new BigDecimal(txtMonto.getText())));
					if (diff.compareTo(BigDecimal.ZERO) == 0) {
						Utiles.Mensaje("Última cuota de separación.", JOptionPane.WARNING_MESSAGE);
					} else if (diff.compareTo(BigDecimal.ZERO) == -1) {
						Utiles.Mensaje(
								"Importe excede al saldo restante. (Sólo falta S/."
										+ articulo.getPrecioVenta().subtract(totalFecha) + " para retirar la prenda)",
								JOptionPane.ERROR_MESSAGE);
						txtMonto.setText("");
						txtMonto.requestFocus();
					}

				} catch (NumberFormatException e) {
					Utiles.Mensaje("Ingrese un importe válido", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtTotalFecha = new JLabel();
		pnlSeparacion.add(txtTotalFecha);
		txtTotalFecha.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtTotalFecha.setBounds(0, 56, 205, 50);
		txtTotalFecha.setBorder(BorderFactory.createTitledBorder(null, "TOTAL A LA FECHA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnFinSeparacion = new JButton(new ImageIcon("img/pagar_separacion.png"));
		pnlSeparacion.add(btnFinSeparacion);
		btnFinSeparacion.setText("SEPARAR");
		btnFinSeparacion.setOpaque(false);
		btnFinSeparacion.setBorderPainted(false);
		btnFinSeparacion.setContentAreaFilled(false);
		btnFinSeparacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnFinSeparacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnFinSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnFinSeparacion.setBounds(0, 163, 205, 70);
		btnFinSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(pnlSeparacion)) {
					int nSep = tbHistorialSeparacion.getRowCount() + 1;

					Contrato contrato = new ContratoController().CargarContrato(articulo.getFlagContrato(),
							articulo.getNumeroContrato());

					Separacion separacion = new Separacion();
					separacion.setFecha(String.valueOf(LocalDate.now()));
					separacion.setPrecioVenta(articulo.getPrecioVenta());
					
					separacion.setCliente(cliente);
					separacion.setImporte(new BigDecimal(txtMonto.getText()));
					separacion.setUsuarioCreacion(Principal.LOGGED.getLogin());
					separacion.setFechaCreacion(String.valueOf(LocalDate.now()));

					Ingreso ingreso = new Ingreso();
					ingreso.setLibroCaja(Principal.LIBRO_CAJA);
					ingreso.setDescripcion(String.valueOf(tbArticulos.getValueAt(tbArticulos.getSelectedRow(), 0)));

					BigDecimal diff = articulo.getPrecioVenta().subtract(totalFecha.add(separacion.getImporte()));

					if (diff.compareTo(BigDecimal.ZERO) == 0) {
						separacion.setStatus(Separacion.FINALIZADA);
						articulo.setEArticulo(new EArticulo(3));
						ingreso.setTipo("SEP #" + nSep + " (R)");
						ingreso.setCapital(articulo.getCapitalContrato());
						ingreso.setGanancia(articulo.getPrecioVenta().subtract(articulo.getCapitalContrato()));
						ingreso.setOtro(separacion.getImporte());
						ingreso.setMoneda("SOLES");
						String[] q = new VentaController().ActualizarSeparacion(cliente, articulo,
								Separacion.FINALIZADA);
						Utiles.Mensaje(q[0], Integer.parseInt(q[1]));

						int qDetalle = contrato.getDetalleContratos().size();

						int qRemSep = (int) contrato.getDetalleContratos().stream()
								.filter(Constantes.predicadoConversorRemSep).count();
						if (qDetalle == qRemSep) {
							contrato.setEContrato(new EContrato(EContrato.REMATADO));
						} else {
							contrato.setEContrato(new EContrato(EContrato.EN_PROCESO));
						}
					} else {
						articulo.setEArticulo(new EArticulo(2));
						ingreso.setTipo("SEP #" + nSep);
						ingreso.setCapital(BigDecimal.ZERO);
						ingreso.setGanancia(BigDecimal.ZERO);
						ingreso.setOtro(separacion.getImporte());
						ingreso.setMoneda("SOLES");
						contrato.setEContrato(new EContrato(EContrato.EN_PROCESO));
						separacion.setStatus(Separacion.ACTIVA);
					}

					
					separacion.setArticulo(articulo);
					new VentaController().GenerarSeparacion(separacion, ingreso);
					new ContratoController().ActualizarContrato(contrato);

					Utiles.Limpiar(pnlSeparacion);
					pnlSeparacion.setVisible(false);
					tbArticulos.setEnabled(true);
					btnRematar.setEnabled(true);
					btnSeparar.setEnabled(true);
					btnBuscar.setEnabled(true);
					btnRegistrarCliente.setEnabled(true);
					spArticulos.setSize(spArticulos.getWidth(), 500);
					new ArticuloController().CargarVitrina();
					Utiles.Mensaje("Separación realizada satisfactoriamente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnCancelarSeparacion = new JButton(new ImageIcon("img/regresar.png"));
		pnlSeparacion.add(btnCancelarSeparacion);
		btnCancelarSeparacion.setText("REGRESAR");
		btnCancelarSeparacion.setOpaque(false);
		btnCancelarSeparacion.setBorderPainted(false);
		btnCancelarSeparacion.setContentAreaFilled(false);
		btnCancelarSeparacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelarSeparacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelarSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnCancelarSeparacion.setBounds(0, 303, 205, 70);
		btnCancelarSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utiles.Limpiar(pnlSeparacion);
				spArticulos.setSize(spArticulos.getWidth(), 500);
				tbArticulos.setEnabled(true);
				btnRematar.setEnabled(true);
				btnSeparar.setEnabled(true);
				btnBuscar.setEnabled(true);
				btnRegistrarCliente.setEnabled(true);
				pnlSeparacion.setVisible(false);
				MOUSECLICKED = true;
			}
		});

		spHistorialSeparacion = new JScrollPane();
		pnlSeparacion.add(spHistorialSeparacion);
		spHistorialSeparacion.setBounds(217, 0, 750, 373);
		spHistorialSeparacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		txtPrecioVenta = new JLabel();
		pnlSeparacion.add(txtPrecioVenta);
		txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtPrecioVenta.setBorder(BorderFactory.createTitledBorder(null, "PRECIO VENTA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtPrecioVenta.setBounds(0, 0, 205, 50);

		btnLiberarSeparacion = new JButton(new ImageIcon("img/cancelar.png"));
		pnlSeparacion.add(btnLiberarSeparacion);
		btnLiberarSeparacion.setText("LIBERAR");
		btnLiberarSeparacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnLiberarSeparacion.setBorderPainted(false);
		btnLiberarSeparacion.setContentAreaFilled(false);
		btnLiberarSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnLiberarSeparacion.setOpaque(false);
		btnLiberarSeparacion.setBounds(0, 233, 205, 70);
		btnLiberarSeparacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLiberarSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"<html><h2>Está a punto de eliminar una Separación. ¿Desea continuar?</h2></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					String[] q = new VentaController().ActualizarSeparacion(cliente, articulo, 0);
					Utiles.Mensaje(q[0], Integer.parseInt(q[1]));
				}
			}
		});

		tbHistorialSeparacion = new JTable();
		spHistorialSeparacion.setViewportView(tbHistorialSeparacion);
		tbHistorialSeparacion.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbHistorialSeparacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbHistorialSeparacion.setRowHeight(25);
		tbHistorialSeparacion.getTableHeader().setForeground(new Color(181, 0, 0));
		tbHistorialSeparacion.setModel(HistorialSeparacionModel);

		btnVender = new JButton(new ImageIcon("img/rematar.png"));
		pnlRemate.add(btnVender);
		btnVender.setText("VENDER ARTÍCULO");
		btnVender.setOpaque(false);
		btnVender.setBorderPainted(false);
		btnVender.setContentAreaFilled(false);
		btnVender.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVender.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnVender.setBounds(772, 69, 200, 70);
		btnVender.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnVender.setHorizontalAlignment(SwingConstants.RIGHT);
		btnVender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(pnlRemate)) {
					int row = tbArticulos.getSelectedRow();
					Venta venta = new Venta();
					venta.setFecha(String.valueOf(LocalDate.now()));
					venta.setImporte(new BigDecimal(String.valueOf(tbArticulos.getValueAt(row, 7))));
					venta.setObs(txtObservaciones.getText());
					venta.setCliente(cliente);
					venta.setUsuarioCreacion(Principal.LOGGED.getLogin());
					venta.setFechaCreacion(String.valueOf(LocalDate.now()));
					articulo.setEArticulo(new EArticulo(EArticulo.REMATADO));

					Contrato contrato = new ContratoController().CargarContrato(articulo.getFlagContrato(),
							articulo.getNumeroContrato());

					int qDetalle = contrato.getDetalleContratos().size();

					int qRemSep = (int) contrato.getDetalleContratos().stream()
							.filter(Constantes.predicadoConversorRemSep).count();
					if (qDetalle == qRemSep) {
						contrato.setEContrato(new EContrato(EContrato.REMATADO));
					} else {
						contrato.setEContrato(new EContrato(EContrato.EN_PROCESO));
					}

					venta.setArticulo(articulo);

					Ingreso ingreso = new Ingreso();
					ingreso.setLibroCaja(Principal.LIBRO_CAJA);
					ingreso.setDescripcion(String.valueOf(tbArticulos.getValueAt(row, 0)));
					ingreso.setTipo("REM");

					BigDecimal capital = new BigDecimal(String.valueOf(tbArticulos.getValueAt(row, 6)));
					BigDecimal precio_venta = new BigDecimal(String.valueOf(tbArticulos.getValueAt(row, 7)));

					ingreso.setCapital(capital);
					ingreso.setGanancia(precio_venta.subtract(capital));
					ingreso.setOtro(BigDecimal.ZERO);
					ingreso.setMoneda("SOLES");

					new VentaController().GenerarVenta(venta, ingreso);
					new ContratoController().ActualizarContrato(contrato);

					Utiles.Limpiar(pnlRemate);
					pnlRemate.setVisible(false);
					tbArticulos.setEnabled(true);
					btnRematar.setEnabled(true);
					btnSeparar.setEnabled(true);
					btnBuscar.setEnabled(true);
					btnRegistrarCliente.setEnabled(true);
					spArticulos.setSize(spArticulos.getWidth(), 500);
					ImprimirTicketPago();
					new ArticuloController().CargarVitrina();

					Utiles.Mensaje("Venta realizada satisfactoriamente", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		jLabel1 = new JLabel();
		pnlRemate.add(jLabel1);
		jLabel1.setText("OBSERVACIONES");
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setBounds(0, 6, 167, 30);

		spObservaciones = new JScrollPane();
		pnlRemate.add(spObservaciones);
		spObservaciones.setBounds(0, 36, 766, 190);

		txtObservaciones = new JTextArea();
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 20));
		spObservaciones.setViewportView(txtObservaciones);
		spObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

	}

	public void ListarHistorial(List<Separacion> ls) {
		totalFecha = BigDecimal.ZERO;
		HistorialSeparacionModel.setRowCount(0);
		for (Separacion s : ls) {
			HistorialSeparacionModel
					.addRow(new Object[] { Constantes.formatoLocal.format(LocalDate.parse(s.getFecha())).toUpperCase(),
							s.getImporte(), s.getCliente().getNombreCompleto() });
			totalFecha = totalFecha.add(s.getImporte());
		}
		txtTotalFecha.setText(String.valueOf(totalFecha));
		tbHistorialSeparacion.setModel(HistorialSeparacionModel);
		btnLiberarSeparacion.setEnabled(totalFecha.compareTo(BigDecimal.ZERO) == 1);
	}

	public void ObtenerCliente() {
		if (!txtDni.getText().isEmpty()) {
			cliente = new ClienteController().BuscarCliente(txtDni.getText());
			if (Objects.nonNull(cliente)) {
				txtDni.setText(cliente.getTDocumento() + "-" + cliente.getDocumento());
				lblCliente.setText(cliente.getNombres() + " " + cliente.getPaterno() + " " + cliente.getMaterno());
			} else {
				Utiles.Mensaje("Cliente " + txtDni.getText() + " no existe. Regístrelo primero.",
						JOptionPane.INFORMATION_MESSAGE);
				btnRegistrarCliente.doClick();
			}

		}
	}

	public void ImprimirTicketPago() {
		try {
			StringBuffer output = new StringBuffer();
			output.append("PRESTOCASH " + Principal.SEDE.getDescripcion() + "\n");
			output.append(Principal.SEDE.getDireccion() + "\n");
			output.append("TELÉFONOS:" + Principal.SEDE.getTelefono1() + "/" + Principal.SEDE.getTelefono2() + "\n");
			output.append("-----------------------------------------------------------------------" + "\n");
			output.append("FECHA: " + Constantes.formatoDiaHora.format(LocalDateTime.now()) + "\n");
			output.append("-----------------------------------------------------------------------" + "\n");
			output.append("CLIENTE:\n");
			output.append(cliente.getNombreCompleto() + "\n");
			output.append("D.I.\t: " + cliente.getDocumento() + "\n");
			output.append("TELÉFONO\t: " + cliente.getTlf1() + " / " + cliente.getTlf2() + "\n");
			output.append("-----------------------------------------------------------------------" + "\n");
			output.append("CONTRATO\t: " + articulo.getFlagContrato() + "-" + articulo.getNumeroContrato() + "\n");
			output.append(articulo.getDescripcion() + " " + articulo.getMarca() + " " + articulo.getModelo() + "\n");
			output.append("TOTAL\t: " + articulo.getPrecioVenta() + "\n");
			output.append(
					new NumberToLetter().convertir(String.valueOf(articulo.getPrecioVenta()), true, "SOLES") + "\n");
			int margin = 0;
			JTextPane jtp = new JTextPane();
			jtp.setText(String.valueOf(output));
			jtp.setFont(new Font(Font.SANS_SERIF, 0, 10));
			PrinterJob printerJob = PrinterJob.getPrinterJob();
			PageFormat pageFormat = printerJob.defaultPage();
			Paper paper = new Paper();
			paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
			pageFormat.setPaper(paper);
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			printerJob.setPrintable(jtp.getPrintable(null, null), pageFormat);
			printerJob.printDialog();
			printerJob.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
