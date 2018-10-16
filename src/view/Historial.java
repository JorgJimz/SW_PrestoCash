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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import common.Constantes;
import common.JFilterComboBox;
import common.RenderHC;
import common.Utiles;
import controller.ClienteController;
import controller.ContratoController;
import model.Cliente;

@SuppressWarnings({ "serial", "rawtypes" })
public class Historial extends JInternalFrame {

	private JPanel contenedor;
	private JButton btnBuscarContratos;
	private JLabel lblConteo;
	private JButton btnConsultar;
	private JScrollPane jScrollPane1;
	private JComboBox cboCliente;
	private JTable tbContratos;

	public Historial() {
		Constantes.HistorialModel.setRowCount(0);

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1223, 471);
		this.setPreferredSize(new java.awt.Dimension(1223, 721));
		this.setBounds(0, 0, 1223, 721);
		this.setClosable(true);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1221, 697);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

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
					Gestion_Contrato gc = new Gestion_Contrato(new ContratoController()
							.CargarContrato(contrato.split("-")[0], Integer.parseInt(contrato.split("-")[1])));
					Principal.dskPrincipal.add(gc);
					Principal.dskPrincipal.moveToFront(gc);
				} catch (Exception e) {
					e.printStackTrace();
					Utiles.Mensaje("Error al traer datos del contrato.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 90, 1176, 574);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbContratos = new JTable();
		jScrollPane1.setViewportView(tbContratos);
		tbContratos.setRowHeight(25);
		tbContratos.setDefaultRenderer(Object.class, new RenderHC());
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.setModel(Constantes.HistorialModel);
		tbContratos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));

		cboCliente = new JFilterComboBox(new ClienteController().FiltrarClientes());
		contenedor.add(cboCliente);
		cboCliente.setBounds(12, 18, 1008, 60);
		cboCliente.setBorder(BorderFactory.createTitledBorder(null, "DATOS DEL CLIENTE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		cboCliente.setBackground(new java.awt.Color(255, 255, 255));		
		cboCliente.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboCliente.setForeground(new java.awt.Color(0, 64, 128));
		cboCliente.requestFocus();
		cboCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CargarHistorial();
				}
			}
		});

		btnBuscarContratos = new JButton(new ImageIcon("img/buscar_historial.png"));
		contenedor.add(btnBuscarContratos);
		btnBuscarContratos.setContentAreaFilled(false);
		btnBuscarContratos.setBorderPainted(false);
		btnBuscarContratos.setToolTipText("Regresar");
		btnBuscarContratos.setOpaque(false);
		btnBuscarContratos.setBounds(1032, 9, 70, 70);
		btnBuscarContratos.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblConteo = new JLabel();
		contenedor.add(lblConteo);
		lblConteo.setBounds(917, 694, 271, 41);
		lblConteo.setFont(new java.awt.Font("Segoe UI", 1, 22));
		lblConteo.setForeground(new java.awt.Color(0, 128, 0));
		lblConteo.setHorizontalAlignment(SwingConstants.RIGHT);

		btnBuscarContratos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CargarHistorial();
			}
		});

	}

	public void CargarHistorial() {
		Cliente c = (Cliente) cboCliente.getSelectedItem();
		new ContratoController().BuscarContratosPorCliente(c.getId(), false, "", 0);
		tbContratos.setModel(Constantes.HistorialModel);
		lblConteo.setText("TOTAL DE CONTRATOS ENCONTRADOS: " + Constantes.HistorialModel.getRowCount());
	}
}
