package maintenance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import common.Constantes;
import common.MyFocusTraversalPolicy;
import common.RenderMC;
import common.Utiles;
import controller.ClienteController;
import model.Cliente;
import view.Principal;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Mantenimiento_Clientes extends JInternalFrame {

	private JLabel jLabel1;
	private JTextField txtNombre;
	private JLabel jLabel5;
	private JScrollPane jScrollPane1;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnGrabar;
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
	private JTextField txtTlf2;
	private JTextField txtTlf1;
	private JLabel jLabel3;
	private JComboBox cboTipoDocumento;
	private JLabel jLabel2;
	private JXTable tbClientes;
	private JLabel lblIdUbigeo;
	private JTextField txtDni;
	private JLabel txtId;
	private JLabel jLabel17;
	private JPanel contenedor;
	private JInternalFrame internal = null;
	Vector<Component> order = new Vector<Component>(11);

	public Mantenimiento_Clientes(String documento) {
		this.setVisible(true);
		this.setClosable(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 470);
		this.setTitle("MANTENIMIENTO DE CLIENTES");
		this.setPreferredSize(new java.awt.Dimension(1258, 842));
		this.setBounds(0, 0, 1258, 842);
		internal = this;

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1256, 812);
		contenedor.setBackground(new java.awt.Color(255, 184, 113));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("TIPO Y NÚMERO DOCUMENTO");
		jLabel1.setBounds(15, 24, 388, 32);
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));

		txtNombre = new JTextField();
		contenedor.add(txtNombre);
		txtNombre.setBounds(957, 62, 260, 32);
		txtNombre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtPaterno = new JTextField();
		contenedor.add(txtPaterno);
		txtPaterno.setBounds(409, 62, 271, 32);
		txtPaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPaterno.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtMaterno = new JTextField();
		contenedor.add(txtMaterno);
		txtMaterno.setBounds(686, 62, 265, 32);
		txtMaterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMaterno.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel5 = new JLabel();
		contenedor.add(jLabel5);
		jLabel5.setText("TELÉFONO 1:");
		jLabel5.setBounds(15, 106, 252, 32);
		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel5.setForeground(new java.awt.Color(0, 128, 0));

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("TELÉFONO 2");
		jLabel6.setBounds(288, 106, 192, 32);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("DIRECCIÓN");
		jLabel13.setBounds(15, 182, 654, 32);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		txtDireccion = new JTextField();
		contenedor.add(txtDireccion);
		txtDireccion.setBounds(15, 220, 931, 32);
		txtDireccion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("E-MAIL");
		jLabel14.setBounds(563, 106, 117, 32);
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));

		txtEmail = new JTextField();
		contenedor.add(txtEmail);
		txtEmail.setBounds(557, 144, 660, 32);
		txtEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 22));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setToolTipText("REGISTRAR CLIENTE");
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(958, 273, 64, 64);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utiles.Validar(contenedor) && btnGrabar.getToolTipText().equalsIgnoreCase("REGISTRAR CLIENTE")) {
					Cliente c = new Cliente();
					c.setTDocumento(String.valueOf(cboTipoDocumento.getSelectedItem()));
					c.setDocumento(txtDni.getText());
					c.setNombres(txtNombre.getText().toUpperCase());
					c.setPaterno(txtPaterno.getText().toUpperCase());
					c.setMaterno(txtMaterno.getText().toUpperCase());
					c.setEmail(txtEmail.getText().toUpperCase());
					c.setTlf1(txtTlf1.getText());
					c.setTlf2(txtTlf2.getText());
					c.setDireccion(txtDireccion.getText().toUpperCase());
					c.setDistrito(cboDistrito.getSelectedItem().toString().toUpperCase());
					c.setCategoriaId(String.valueOf(cboCategoriaCliente.getSelectedItem()));
					c.setObs(txtObs.getText());
					c.setStatus(1);
					c.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					c.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new ClienteController().RegistrarCliente(c);
					ListarClientes();
					btnGrabar.setIcon(new ImageIcon("img/grabar.png"));
					btnGrabar.setToolTipText("REGISTRAR CLIENTE");
					Utiles.Limpiar(contenedor);
					Utiles.Mensaje("Cliente registrado.", JOptionPane.INFORMATION_MESSAGE);
					Utiles.MostrarOperaciones(c.getDocumento(), internal);
				} else if (btnGrabar.getToolTipText().equalsIgnoreCase("GENERAR CONTRATO")) {
					Utiles.MostrarOperaciones(txtDni.getText(), internal);
				} else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnEditar = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnEditar);
		btnEditar.setBorderPainted(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEditar.setEnabled(false);
		btnEditar.setBounds(1055, 273, 64, 64);
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cliente c = new Cliente();
				c.setId(Integer.parseInt(txtId.getText()));
				c.setTDocumento(String.valueOf(cboTipoDocumento.getSelectedItem()));
				c.setDocumento(txtDni.getText());
				c.setNombres(txtNombre.getText().toUpperCase());
				c.setPaterno(txtPaterno.getText().toUpperCase());
				c.setMaterno(txtMaterno.getText().toUpperCase());
				c.setEmail(txtEmail.getText().toUpperCase());
				c.setTlf1(txtTlf1.getText());
				c.setTlf2(txtTlf2.getText());
				c.setDireccion(txtDireccion.getText().toUpperCase());
				c.setDistrito(cboDistrito.getSelectedItem().toString().toUpperCase());
				c.setCategoriaId(String.valueOf(cboCategoriaCliente.getSelectedItem()));
				c.setObs(txtObs.getText());
				c.setStatus(1);
				c.setFechaModificacion(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				c.setUsuarioModificacion(Principal.LOGGED.getLogin());
				new ClienteController().ActualizarCliente(c);				
				btnGrabar.setIcon(new ImageIcon("img/grabar.png"));
				btnGrabar.setToolTipText("REGISTRAR CLIENTE");
				btnGrabar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnEliminar.setEnabled(false);
				Utiles.Limpiar(contenedor);
				ListarClientes();
				Utiles.Mensaje("Cliente actualizado.", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/eliminar.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(1153, 273, 64, 64);
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 28));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente c = new Cliente();
					c.setId(Integer.parseInt(txtId.getText()));
					c.setTDocumento(String.valueOf(cboTipoDocumento.getSelectedItem()));
					c.setDocumento(txtDni.getText());
					c.setNombres(txtNombre.getText().toUpperCase());
					c.setPaterno(txtPaterno.getText().toUpperCase());
					c.setMaterno(txtMaterno.getText().toUpperCase());
					c.setEmail(txtEmail.getText().toUpperCase());
					c.setTlf1(txtTlf1.getText());
					c.setTlf2(txtTlf2.getText());
					c.setDireccion(txtDireccion.getText().toUpperCase());
					c.setDistrito(cboDistrito.getSelectedItem().toString().toUpperCase());
					c.setCategoriaId(String.valueOf(cboCategoriaCliente.getSelectedItem()));
					c.setStatus(0);
					c.setObs(txtObs.getText());
					c.setFechaModificacion(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					c.setUsuarioModificacion(Principal.LOGGED.getLogin());
					new ClienteController().ActualizarCliente(c);
					btnGrabar.setIcon(new ImageIcon("img/grabar.png"));
					btnGrabar.setToolTipText("REGISTRAR CLIENTE");
					btnEliminar.setEnabled(false);
					btnEditar.setEnabled(false);
					Utiles.Limpiar(contenedor);
					ListarClientes();
					Utiles.Mensaje("Cliente eliminado.", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		jLabel17 = new JLabel();
		contenedor.add(jLabel17);
		jLabel17.setText("NOMBRE");
		jLabel17.setBounds(963, 24, 254, 32);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel17.setForeground(new java.awt.Color(0, 128, 0));

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setBounds(172, 24, 254, 32);
		txtId.setVisible(false);

		txtDni = new JTextField();
		contenedor.add(txtDni);
		txtDni.setBounds(178, 62, 225, 32);
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtDni.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				Cliente c = new ClienteController().BuscarCliente(txtDni.getText());
				if (c != null) {
					int q = JOptionPane.showConfirmDialog(null,
							"Este Cliente ya se encuentra registrado, ¿Desea traer los registros del mismo?",
							"Coincidencia de Datos", JOptionPane.YES_NO_OPTION);
					if (q == JOptionPane.YES_OPTION) {
						cboTipoDocumento.setSelectedItem(c.getTDocumento());
						txtDni.setText(c.getDocumento());
						txtNombre.setText(c.getNombres());
						txtPaterno.setText(c.getPaterno());
						txtMaterno.setText(c.getMaterno());
						txtEmail.setText(c.getEmail());
						txtTlf1.setText(c.getTlf1());
						txtTlf2.setText(c.getTlf2());
						txtDireccion.setText(c.getDireccion());
						cboDistrito.setSelectedItem(c.getDistrito());
						cboCategoriaCliente.setSelectedItem(c.getCategoriaId());
						txtObs.setText(c.getObs());
						txtDni.setEnabled(false);
						btnEditar.setEnabled(true);
						btnGrabar.setEnabled(false);
						btnGrabar.setIcon(new ImageIcon("img/grabarContrato.png"));
						btnGrabar.setToolTipText("GENERAR CONTRATO");
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
		jLabel2.setText("A.PATERNO");
		jLabel2.setBounds(426, 24, 254, 32);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("A.MATERNO");
		jLabel3.setBounds(692, 24, 253, 32);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		txtTlf1 = new JTextField();
		contenedor.add(txtTlf1);
		txtTlf1.setBounds(15, 144, 267, 32);
		txtTlf1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTlf1.setFont(new java.awt.Font("Segoe UI", 1, 22));

		txtTlf2 = new JTextField();
		contenedor.add(txtTlf2);
		txtTlf2.setBounds(288, 144, 263, 32);
		txtTlf2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTlf2.setFont(new java.awt.Font("Segoe UI", 1, 22));

		jLabel4 = new JLabel();
		contenedor.add(jLabel4);
		jLabel4.setText("DISTRITO");
		jLabel4.setBounds(963, 182, 129, 32);
		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel4.setForeground(new java.awt.Color(0, 128, 0));

		cboDistrito = new JComboBox();
		contenedor.add(cboDistrito);
		cboDistrito.setEditable(true);
		cboDistrito.setModel(Constantes.DistritoModel);
		cboDistrito.setBounds(958, 221, 259, 32);
		cboDistrito.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboDistrito.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		AutoCompleteDecorator.decorate(cboDistrito);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 349, 1205, 435);
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("CATEGORIA");
		jLabel10.setBounds(15, 267, 257, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		cboCategoriaCliente = new JComboBox();
		contenedor.add(cboCategoriaCliente);
		cboCategoriaCliente.setModel(Constantes.CategoriaModel);
		cboCategoriaCliente.setBounds(12, 305, 160, 32);
		cboCategoriaCliente.setBackground(new java.awt.Color(255, 255, 255));
		cboCategoriaCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboCategoriaCliente.setFont(new java.awt.Font("Segoe UI", 1, 20));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("DETALLE");
		jLabel11.setBounds(184, 267, 128, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		txtObs = new JTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(184, 305, 762, 32);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI", 1, 22));

		cboTipoDocumento = new JComboBox();
		contenedor.add(cboTipoDocumento);
		cboTipoDocumento.setModel(Constantes.TipoDocumentoModel);
		cboTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
		cboTipoDocumento.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboTipoDocumento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboTipoDocumento.setBounds(15, 62, 157, 32);

		tbClientes = new JXTable();
		tbClientes.setRowHeight(25);
		tbClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbClientes.getTableHeader().setForeground(new Color(181, 0, 0));
		tbClientes.setModel(Constantes.ClienteModel);
		tbClientes.setColumnControlVisible(true);
		tbClientes.setSearchable(null);
		tbClientes.setDefaultRenderer(Object.class, new RenderMC());
		jScrollPane1.setViewportView(tbClientes);
		tbClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbClientes.getSelectedRow();
					if(Integer.parseInt(String.valueOf(tbClientes.getModel().getValueAt(fila, 12))) == 1) {
						Utiles.Limpiar(contenedor);				
						txtId.setText(tbClientes.getValueAt(fila, 0).toString());
						cboTipoDocumento.setSelectedItem(tbClientes.getValueAt(fila, 1).toString().split("-")[0].trim());
						txtDni.setText(tbClientes.getValueAt(fila, 1).toString().split("-")[1].trim());
						txtNombre.setText(tbClientes.getValueAt(fila, 2).toString());
						txtPaterno.setText(tbClientes.getValueAt(fila, 3).toString());
						txtMaterno.setText(tbClientes.getValueAt(fila, 4).toString());
						txtEmail.setText(tbClientes.getValueAt(fila, 5).toString());
						txtTlf1.setText(tbClientes.getValueAt(fila, 6).toString());
						txtTlf2.setText(tbClientes.getValueAt(fila, 7).toString());
						txtDireccion.setText(tbClientes.getValueAt(fila, 8).toString());
						cboDistrito.setSelectedItem(tbClientes.getValueAt(fila, 9).toString());
						cboCategoriaCliente.setSelectedItem(tbClientes.getValueAt(fila, 10).toString());
						txtObs.setText(tbClientes.getValueAt(fila, 11).toString());
						btnGrabar.setIcon(new ImageIcon("img/grabarContrato.png"));
						btnGrabar.setToolTipText("GENERAR CONTRATO");
						btnGrabar.setEnabled(true);
						btnEditar.setEnabled(true);
						btnEliminar.setEnabled(true);
					}else {
						txtId.setText(tbClientes.getValueAt(fila, 0).toString());
						cboTipoDocumento.setSelectedItem(tbClientes.getValueAt(fila, 1).toString().split("-")[0].trim());
						txtDni.setText(tbClientes.getValueAt(fila, 1).toString().split("-")[1].trim());
						txtNombre.setText(tbClientes.getValueAt(fila, 2).toString());
						txtPaterno.setText(tbClientes.getValueAt(fila, 3).toString());
						txtMaterno.setText(tbClientes.getValueAt(fila, 4).toString());
						txtEmail.setText(tbClientes.getValueAt(fila, 5).toString());
						txtTlf1.setText(tbClientes.getValueAt(fila, 6).toString());
						txtTlf2.setText(tbClientes.getValueAt(fila, 7).toString());
						txtDireccion.setText(tbClientes.getValueAt(fila, 8).toString());
						cboDistrito.setSelectedItem(tbClientes.getValueAt(fila, 9).toString());
						cboCategoriaCliente.setSelectedItem(tbClientes.getValueAt(fila, 10).toString());
						txtObs.setText(tbClientes.getValueAt(fila, 11).toString());
						btnGrabar.setEnabled(false);
						btnEditar.setEnabled(true);						
						Utiles.Mensaje("Cliente inactivo. Actualizar status.", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		});

		order.add(cboTipoDocumento);
		order.add(txtDni);
		order.add(txtPaterno);
		order.add(txtMaterno);
		order.add(txtNombre);
		order.add(txtTlf1);
		order.add(txtTlf2);
		order.add(txtEmail);
		order.add(txtDireccion);
		order.add(cboDistrito);
		order.add(cboCategoriaCliente);
		order.add(txtObs);

		this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(order));

		ListarClientes();
	}

	public void ListarClientes() {
		Constantes.ClienteModel.setRowCount(0);
		tbClientes.setModel(new ClienteController().ListarClientes(Constantes.ClienteModel));
	}
}
