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
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import common.Constantes;
import common.JIconTextField;
import common.RenderMC;
import common.Utiles;
import controller.ClienteController;
import model.Cliente;
import view.Principal;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Mantenimiento_Clientes extends JInternalFrame {

	private JScrollPane jScrollPane1;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnGrabar;
	private JIconTextField txtEmail;
	private JIconTextField txtDireccion;
	private JIconTextField txtMaterno;
	private JIconTextField txtPaterno;
	private JIconTextField txtObs;
	private JIconTextField txtNombre;
	private JIconTextField txtTlf2;
	private JIconTextField txtTlf1;
	private JIconTextField txtDni;
	private JComboBox cboCategoriaCliente;
	private JComboBox cboDistrito;
	private JComboBox cboTipoDocumento;
	private JXTable tbClientes;
	private JLabel lblIdUbigeo;	
	private JLabel txtId;
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
		this.setPreferredSize(new java.awt.Dimension(1258, 726));
		this.setBounds(0, 0, 1258, 726);
		internal = this;

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1256, 701);
		contenedor.setBackground(new java.awt.Color(255, 184, 113));

		txtNombre = new JIconTextField();
		contenedor.add(txtNombre);
		txtNombre.setBounds(957, 12, 260, 50);		
		txtNombre.setFont(new java.awt.Font("Segoe UI",1,16));
		txtNombre.setBorder(BorderFactory.createTitledBorder(null, "NOMBRES",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtPaterno = new JIconTextField();
		contenedor.add(txtPaterno);
		txtPaterno.setBounds(409, 12, 271, 50);		
		txtPaterno.setFont(new java.awt.Font("Segoe UI",1,16));
		txtPaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO PATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtMaterno = new JIconTextField();
		contenedor.add(txtMaterno);
		txtMaterno.setBounds(686, 12, 265, 50);
		txtMaterno.setFont(new java.awt.Font("Segoe UI",1,16));
		txtMaterno.setBorder(BorderFactory.createTitledBorder(null, "APELLIDO MATERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtDireccion = new JIconTextField();
		contenedor.add(txtDireccion);
		txtDireccion.setBounds(16, 136, 931, 50);		
		txtDireccion.setFont(new java.awt.Font("Segoe UI",1,16));
		txtDireccion.setBorder(BorderFactory.createTitledBorder(null, "DIRECCIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtEmail = new JIconTextField();
		contenedor.add(txtEmail);
		txtEmail.setBounds(558, 74, 659, 50);		
		txtEmail.setFont(new java.awt.Font("Segoe UI",1,16));
		txtEmail.setBorder(BorderFactory.createTitledBorder(null, "E-MAIL",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setToolTipText("REGISTRAR CLIENTE");
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(959, 193, 64, 64);
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
		btnEditar.setBounds(1056, 193, 64, 64);
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
		btnEliminar.setBounds(1154, 193, 64, 64);
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

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setBounds(172, 24, 254, 32);
		txtId.setVisible(false);

		txtDni = new JIconTextField();
		contenedor.add(txtDni);
		txtDni.setBounds(178, 12, 225, 50);
		txtDni.setBorder(BorderFactory.createTitledBorder(null, "D.N.I.",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDni.setFont(new java.awt.Font("Segoe UI",1,16));
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
						btnGrabar.setIcon(new ImageIcon("img/acciones.png"));
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

		txtTlf1 = new JIconTextField();
		contenedor.add(txtTlf1);
		txtTlf1.setBounds(16, 74, 267, 50);
		txtTlf1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTlf1.setFont(new java.awt.Font("Segoe UI",1,16));
		txtTlf1.setBorder(BorderFactory.createTitledBorder(null, "TELÉFONO 1",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		
		txtTlf2 = new JIconTextField();
		contenedor.add(txtTlf2);
		txtTlf2.setBounds(289, 74, 263, 50);
		txtTlf2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTlf2.setFont(new java.awt.Font("Segoe UI",1,16));
		txtTlf2.setBorder(BorderFactory.createTitledBorder(null, "TELÉFONO 2",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		cboDistrito = new JComboBox();
		contenedor.add(cboDistrito);
		cboDistrito.setModel(Constantes.DistritoModel);
		cboDistrito.setBounds(959, 137, 259, 50);
		cboDistrito.setForeground(new java.awt.Color(0, 64, 128));
		cboDistrito.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboDistrito.setBorder(BorderFactory.createTitledBorder(null, "DISTRITO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		AutoCompleteDecorator.decorate(cboDistrito);

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 260, 1205, 402);
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		cboCategoriaCliente = new JComboBox();
		contenedor.add(cboCategoriaCliente);
		cboCategoriaCliente.setModel(Constantes.CategoriaModel);
		cboCategoriaCliente.setBounds(17, 198, 160, 50);
		cboCategoriaCliente.setForeground(new java.awt.Color(0, 64, 128));
		cboCategoriaCliente.setBackground(new java.awt.Color(255, 255, 255));
		cboCategoriaCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboCategoriaCliente.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboCategoriaCliente.setBorder(BorderFactory.createTitledBorder(null, "CATEGORIZACIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		
		txtObs = new JIconTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(189, 198, 758, 50);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI",1,16));
		txtObs.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		
		cboTipoDocumento = new JComboBox();
		contenedor.add(cboTipoDocumento);
		cboTipoDocumento.setModel(Constantes.TipoDocumentoModel);
		cboTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
		cboTipoDocumento.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboTipoDocumento.setBounds(15, 12, 157, 50);
		cboTipoDocumento.setForeground(new java.awt.Color(0, 64, 128));
		cboTipoDocumento.setBorder(BorderFactory.createTitledBorder(null, "TIPO DOCUMENTO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		
		tbClientes = new JXTable();
		tbClientes.setRowHeight(25);
		tbClientes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
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
						btnGrabar.setIcon(new ImageIcon("img/acciones.png"));
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

		/*order.add(cboTipoDocumento);
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

		this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(order));*/

		ListarClientes();
	}

	public void ListarClientes() {
		Constantes.ClienteModel.setRowCount(0);
		tbClientes.setModel(new ClienteController().ListarClientes(Constantes.ClienteModel));
	}
}
