package maintenance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Cliente;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.Principal;
import common.Constantes;
import common.Utiles;
import controller.ClienteController;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Mantenimiento_Clientes extends JInternalFrame {

	private JLabel jLabel1;
	private JTextField txtNombre;
	private JLabel jLabel5;
	private JScrollPane jScrollPane1;
	private JLabel jLabel7;
	private JXSearchField sfBuscar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnGrabar;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel6;
	private JTextField txtEmail;
	private JLabel jLabel14;
	private JTextField txtDireccion;
	private JLabel jLabel13;
	private JTextField txtMaterno;
	private JTextField txtPaterno;
	private JTextField txtObs;
	private JLabel jLabel11;
	private JComboBox cboCategoriaCliente;
	private JLabel jLabel10;
	private JComboBox cboDistrito;
	private JLabel jLabel4;
	private JTextField txtMovil2;
	private JTextField txtMovil1;
	private JTextField txtFijo2;
	private JTextField txtFijo1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JTable tbClientes;
	private JLabel lblIdUbigeo;
	private JTextField txtDni;
	private JLabel txtId;
	private JLabel jLabel17;
	private JPanel contenedor;
	private JPopupMenu popBusqueda;

	private String filtro = "documento";

	DefaultTableModel modeloClientes = new DefaultTableModel(null,
			new String[] { "ID", "DOC.IDENTIDAD", "NOMBRE", "A.PATERNO",
					"A.MATERNO", "E-MAIL", "TLF 1", "TLF 2", "DIRECCIÓN",
					"DISTRITO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Mantenimiento_Clientes(String documento) {
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 470);
		this.setTitle("MANTENIMIENTO DE CLIENTES");
		this.setPreferredSize(new java.awt.Dimension(1275, 694));
		this.setBounds(0, 0, 1275, 694);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1273, 670);
		contenedor.setBackground(new java.awt.Color(255, 184, 113));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("D.IDENTIDAD:");
		jLabel1.setBounds(12, 24, 195, 32);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		txtNombre = new JTextField();
		contenedor.add(txtNombre);
		txtNombre.setBounds(172, 76, 254, 32);
		txtNombre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtPaterno = new JTextField();
		contenedor.add(txtPaterno);
		txtPaterno.setBounds(573, 24, 254, 32);
		txtPaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtPaterno.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtMaterno = new JTextField();
		contenedor.add(txtMaterno);
		txtMaterno.setBounds(980, 24, 254, 32);
		txtMaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMaterno.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("FIJO 1:");
		jLabel5.setBounds(444, 76, 85, 32);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("FIJO 2:");
		jLabel6.setBounds(844, 76, 101, 32);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("MÓVIL 1:");
		jLabel8.setBounds(12, 128, 111, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("MÓVIL 2 :");
		jLabel9.setBounds(445, 128, 103, 32);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("DIRECCIÓN:");
		jLabel13.setBounds(12, 185, 148, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		txtDireccion = new JTextField();
		contenedor.add(txtDireccion);
		txtDireccion.setBounds(172, 185, 654, 32);
		txtDireccion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("E-MAIL:");
		jLabel14.setBounds(844, 128, 117, 32);
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));

		txtEmail = new JTextField();
		contenedor.add(txtEmail);
		txtEmail.setBounds(980, 124, 254, 32);
		txtEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 22));

		btnGrabar = new JButton(new ImageIcon("img/registrarCliente.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setText(" REGISTRAR");
		btnGrabar.setBounds(12, 554, 301, 75);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor)
						&& btnGrabar.getText().equalsIgnoreCase(" REGISTRAR")) {
					Cliente c = new Cliente();
					c.setTDocumento("DNI");
					c.setDocumento(txtDni.getText());
					c.setNombres(txtNombre.getText().toUpperCase());
					c.setPaterno(txtPaterno.getText().toUpperCase());
					c.setMaterno(txtMaterno.getText().toUpperCase());
					c.setEmail(txtEmail.getText().toUpperCase());
					c.setTlf1(txtFijo1.getText());
					c.setTlf2(txtMovil1.getText());
					c.setDireccion(txtDireccion.getText().toUpperCase());
					c.setDistrito(cboDistrito.getSelectedItem().toString()
							.toUpperCase());
					c.setCategoriaId("1");
					c.setStatus(1);
					c.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date()));
					c.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new ClienteController().RegistrarCliente(c);
					ListarClientes();
					Utiles.Mensaje("Cliente registrado.");

				} else if (btnGrabar.getText().equalsIgnoreCase(" CONTRATO")) {
					Utiles.MostrarOperaciones();
				} else {
					JOptionPane.showMessageDialog(null,
							"Faltan datos. Complete los campos.");
				}

			}
		});

		btnEditar = new JButton(new ImageIcon("img/editarCliente.png"));
		contenedor.add(btnEditar);
		btnEditar.setText(" EDITAR");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(626, 554, 301, 75);
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cliente c = new Cliente();
				c.setId(Integer.parseInt(txtId.getText()));
				c.setDocumento(txtDni.getText());
				c.setNombres(txtNombre.getText().toUpperCase());
				c.setPaterno(txtPaterno.getText().toUpperCase());
				c.setMaterno(txtMaterno.getText().toUpperCase());
				c.setEmail(txtEmail.getText().toUpperCase());
				c.setTlf1(txtFijo1.getText());
				c.setTlf2(txtMovil1.getText());
				c.setDireccion(txtDireccion.getText().toUpperCase());
				c.setDistrito(cboDistrito.getSelectedItem().toString()
						.toUpperCase());
				c.setCategoriaId("1");
				c.setStatus(1);
				c.setFechaModificacion(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				c.setUsuarioModificacion(Principal.LOGGED.getLogin());
				new ClienteController().ActualizarCliente(c);
				txtDni.setEnabled(true);
				btnEditar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGrabar.setText(" REGISTRAR");
				Utiles.Limpiar(contenedor);
				ListarClientes();
				Utiles.Mensaje("Cliente actualizado.");				
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/retirarCliente.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setText(" RETIRAR");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(319, 554, 301, 75);
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente c = new Cliente();
					c.setId(Integer.parseInt(txtId.getText()));
					c.setDocumento(txtDni.getText());
					c.setNombres(txtNombre.getText().toUpperCase());
					c.setPaterno(txtPaterno.getText().toUpperCase());
					c.setMaterno(txtMaterno.getText().toUpperCase());
					c.setEmail(txtEmail.getText().toUpperCase());
					c.setTlf1(txtFijo1.getText());
					c.setTlf2(txtMovil1.getText());
					c.setDireccion(txtDireccion.getText().toUpperCase());
					c.setDistrito(cboDistrito.getSelectedItem().toString()
							.toUpperCase());
					c.setCategoriaId("1");
					c.setStatus(0);
					c.setFechaModificacion(new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date()));
					c.setUsuarioModificacion(Principal.LOGGED.getLogin());
					new ClienteController().ActualizarCliente(c);
					btnEliminar.setEnabled(false);
					btnEditar.setEnabled(false);
					btnGrabar.setText(" REGISTRAR");
					Utiles.Limpiar(contenedor);
					ListarClientes();
					Utiles.Mensaje("Cliente eliminado.");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		btnCancelar = new JButton(new ImageIcon("img/salir.png"));
		contenedor.add(btnCancelar);
		btnCancelar.setText(" SALIR");
		btnCancelar.setBounds(933, 554, 301, 75);
		btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cerrar();
			}
		});

		JMenuItem mniDni = new JMenuItem("Por D.N.I.");
		mniDni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				filtro = "documento";
			}
		});
		JMenuItem mniPaterno = new JMenuItem("Por Apellido");
		mniPaterno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				filtro = "concat(paterno,' ',materno)";
			}
		});
		popBusqueda = new JPopupMenu();
		popBusqueda.add(mniDni);
		popBusqueda.add(mniPaterno);

		sfBuscar = new JXSearchField();
		contenedor.add(sfBuscar);
		sfBuscar.setText("");
		sfBuscar.setBounds(980, 246, 254, 32);
		sfBuscar.setFindPopupMenu(popBusqueda);
		sfBuscar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		sfBuscar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		sfBuscar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new ClienteController().BuscarClientes(filtro, txtDni
							.getText().trim(), modeloClientes);
				}
			}
		});

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("BÚSQUEDA:");
		jLabel7.setBounds(843, 246, 136, 32);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel17 = new JLabel();
		contenedor.add(jLabel17);
		jLabel17.setText("NOMBRE:");
		jLabel17.setBounds(12, 76, 118, 32);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel17.setForeground(new java.awt.Color(0, 128, 0));

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setBounds(172, 24, 254, 32);
		txtId.setVisible(false);

		txtDni = new JTextField(documento);
		contenedor.add(txtDni);
		txtDni.setBounds(172, 24, 254, 32);
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtDni.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				Cliente c = new ClienteController().BuscarCliente(txtDni
						.getText());
				if (c != null) {
					int q = JOptionPane
							.showConfirmDialog(
									null,
									"Este Cliente ya se encuentra registrado, ¿Desea traer los registros del mismo?",
									"Coincidencia de Datos",
									JOptionPane.YES_NO_OPTION);
					if (q == JOptionPane.YES_OPTION) {
						txtDni.setText(c.getDocumento());
						txtNombre.setText(c.getNombres());
						txtPaterno.setText(c.getPaterno());
						txtMaterno.setText(c.getMaterno());
						txtEmail.setText(c.getEmail());
						txtFijo1.setText(c.getTlf1());
						txtMovil1.setText(c.getTlf2());
						txtDireccion.setText(c.getDireccion());
						cboDistrito.setSelectedItem(c.getDistrito());
						cboCategoriaCliente.setSelectedItem(c.getCategoriaId());
						txtObs.setText(c.getObs());
						txtDni.setEnabled(false);
						btnEditar.setEnabled(true);
						btnGrabar.setEnabled(false);
						btnGrabar.setText(" CONTRATO");
						txtDni.setEnabled(false);
					} else {
						txtDni.setText("");
						txtDni.requestFocus();
					}
				}
			}
		});

		lblIdUbigeo = new JLabel();
		contenedor.add(lblIdUbigeo);
		lblIdUbigeo.setBounds(592, 320, 61, 25);
		lblIdUbigeo.setVisible(false);

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("A.PATERNO:");
		jLabel2.setBounds(445, 24, 141, 32);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("A.MATERNO:");
		jLabel3.setBounds(844, 24, 142, 32);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		txtFijo1 = new JTextField();
		contenedor.add(txtFijo1);
		txtFijo1.setBounds(572, 76, 254, 32);
		txtFijo1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtFijo1.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtFijo2 = new JTextField();
		contenedor.add(txtFijo2);
		txtFijo2.setBounds(980, 76, 254, 32);
		txtFijo2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtFijo2.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtMovil1 = new JTextField();
		contenedor.add(txtMovil1);
		txtMovil1.setBounds(172, 128, 254, 32);
		txtMovil1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMovil1.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtMovil2 = new JTextField();
		contenedor.add(txtMovil2);
		txtMovil2.setBounds(572, 128, 254, 32);
		txtMovil2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtMovil2.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("DISTRITO:");
		jLabel4.setBounds(844, 185, 129, 32);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		cboDistrito = new JComboBox();
		contenedor.add(cboDistrito);
		cboDistrito.setEditable(true);
		cboDistrito.setModel(Constantes.DistritoModel);
		cboDistrito.setBounds(980, 186, 254, 32);
		cboDistrito.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboDistrito.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		AutoCompleteDecorator.decorate(cboDistrito);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 297, 1222, 245);
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("CATEGORIA:");
		jLabel10.setBounds(12, 246, 140, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboCategoriaCliente = new JComboBox();
		contenedor.add(cboCategoriaCliente);
		cboCategoriaCliente.setModel(Constantes.CategoriaModel);
		cboCategoriaCliente.setBounds(172, 246, 254, 32);
		cboCategoriaCliente.setBackground(new java.awt.Color(255, 255, 255));
		cboCategoriaCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, new java.awt.Color(0, 0, 0)));
		cboCategoriaCliente.setFont(new java.awt.Font("Segoe UI", 1, 20));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DETALLE:");
		jLabel11.setBounds(443, 246, 128, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		txtObs = new JTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(571, 246, 254, 32);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI", 1, 22));

		tbClientes = new JTable();
		tbClientes.setRowHeight(25);
		tbClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbClientes.getTableHeader()
				.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbClientes.getTableHeader().setForeground(new Color(181, 0, 0));
		jScrollPane1.setViewportView(tbClientes);
		tbClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Utiles.Limpiar(contenedor);
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
					txtDni.setEnabled(false);
					int fila = tbClientes.getSelectedRow();
					txtId.setText(tbClientes.getValueAt(fila, 0).toString());
					txtDni.setText(tbClientes.getValueAt(fila, 1).toString()
							.split("-")[1].trim());
					txtNombre
							.setText(tbClientes.getValueAt(fila, 2).toString());
					txtPaterno.setText(tbClientes.getValueAt(fila, 3)
							.toString());
					txtMaterno.setText(tbClientes.getValueAt(fila, 4)
							.toString());
					txtEmail.setText(tbClientes.getValueAt(fila, 5).toString());
					txtFijo1.setText(tbClientes.getValueAt(fila, 6).toString());
					txtMovil1
							.setText(tbClientes.getValueAt(fila, 7).toString());

					txtDireccion.setText(tbClientes.getValueAt(fila, 8)
							.toString());
					cboDistrito.setSelectedItem(tbClientes.getValueAt(fila, 9)
							.toString());
					btnGrabar.setText("");
					btnGrabar.setText(" CONTRATO");
				}
			}
		});

		ListarClientes();
	}

	public void ListarClientes() {
		modeloClientes.setRowCount(0);
		modeloClientes = new ClienteController().ListarClientes(modeloClientes);
		tbClientes.setModel(modeloClientes);
	}

	public void Cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Salir?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}
}
