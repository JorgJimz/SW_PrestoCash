package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Cliente;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import common.Constantes;
import common.RenderHC;
import common.Utiles;
import controller.ClienteController;
import controller.ContratoController;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class Historial extends JInternalFrame {

	private JPanel contenedor;
	private JLabel jLabel2;
	private JButton btnBuscarContratos;
	private JButton btnConsultar;
	private JScrollPane jScrollPane1;
	private JTextField txtCliente;
	private JList lstClientes;
	private JTable tbContratos;

	public Historial() {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1223, 471);
		this.setPreferredSize(new java.awt.Dimension(1223, 793));
		this.setBounds(0, 0, 1223, 793);
		this.setClosable(true);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1221, 764);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("CLIENTE:");
		jLabel2.setBounds(12, 26, 104, 41);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		btnConsultar = new JButton(new ImageIcon("img/binoculares.png"));
		contenedor.add(btnConsultar);
		btnConsultar.setBounds(1118, 9, 70, 70);
		btnConsultar.setOpaque(false);
		btnConsultar.setBorderPainted(false);
		btnConsultar.setToolTipText("Regresar");
		btnConsultar.setContentAreaFilled(false);
		btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConsultar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String contrato = String.valueOf(tbContratos.getValueAt(tbContratos.getSelectedRow(), 0));		
					Gestion_Contrato gc = new Gestion_Contrato(contrato);					
					Principal.dskPrincipal.add(gc);
					Principal.dskPrincipal.moveToFront(gc);
				} catch (Exception e) {
					Utiles.Mensaje("Error al traer datos del contrato.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 94, 1176, 639);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.setRowHeight(35);
		tbContratos.setDefaultRenderer(Object.class, new RenderHC());
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tbContratos.setModel(Constantes.HistorialModel);
		tbContratos.getTableHeader().setFont(
				new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));

		txtCliente = new JTextField();
		contenedor.add(txtCliente);
		txtCliente.setBounds(128, 26, 893, 40);
		txtCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(0, 0, 0)));
		txtCliente.setBackground(new java.awt.Color(255, 255, 255));
		txtCliente.setOpaque(true);
		txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtCliente.setForeground(new java.awt.Color(0, 64, 128));
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CargarHistorial();
				}
			}
		});

		lstClientes = new JList();
		lstClientes.setVisible(false);
		contenedor.add(lstClientes);
		lstClientes.setModel(new ClienteController().FiltrarClientes());
		lstClientes.setBounds(128, 19, 27, 33);

		btnBuscarContratos = new JButton(new ImageIcon("img/buscar_historial.png"));
		contenedor.add(btnBuscarContratos);
		btnBuscarContratos.setContentAreaFilled(false);
		btnBuscarContratos.setBorderPainted(false);
		btnBuscarContratos.setToolTipText("Regresar");
		btnBuscarContratos.setOpaque(false);
		btnBuscarContratos.setBounds(1032, 9, 70, 70);
		btnBuscarContratos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscarContratos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CargarHistorial();
			}
		});

		AutoCompleteDecorator.decorate(lstClientes, txtCliente,
				ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

	}

	public void CargarHistorial() {
		Cliente c = (Cliente) lstClientes.getSelectedValue();
		new ContratoController().BuscarContratosPorCliente(c.getId());
		tbContratos.setModel(Constantes.HistorialModel);
	}
}
