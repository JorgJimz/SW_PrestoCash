package maintenance;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXTable;

import common.Constantes;
import common.Utiles;
import controller.ArticuloController;
import controller.PrestamoController;
import model.Prestamo;
import model.Sede;
import view.Principal;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Mantenimiento_Prestamos extends JInternalFrame {
	private JTextField txtTipo;
	private JScrollPane spPrestamo;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnGrabar;
	private JXTable tbPrestamos;
	private JTextField txtInteres;
	private JTextField txtDescripcion;
	private JPanel contenedor;
	private JComboBox cboSede;
	private JComboBox cboTipoMora;

	public Mantenimiento_Prestamos() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE PRÉSTAMOS");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(520, 241);
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(1221, 382));
		this.setBounds(0, 0, 1221, 382);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1219, 353);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtTipo = new JTextField();
		contenedor.add(txtTipo);
		txtTipo.setBounds(20, 90, 213, 66);
		txtTipo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtTipo.setOpaque(false);
		txtTipo.setBorder(BorderFactory.createTitledBorder(null, "TIPO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16),
				new java.awt.Color(0, 128, 0)));

		txtDescripcion = new JTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(20, 12, 432, 66);
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtDescripcion.setOpaque(false);
		txtDescripcion.setBorder(BorderFactory.createTitledBorder(null, "DESCRIPCIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 16), new java.awt.Color(0, 128, 0)));

		txtInteres = new JTextField();
		contenedor.add(txtInteres);
		txtInteres.setBounds(20, 168, 213, 66);
		txtInteres.setFont(new java.awt.Font("Segoe UI", 1, 20));
		txtInteres.setOpaque(false);
		txtInteres.setBorder(BorderFactory.createTitledBorder(null, "INTERÉS (%)", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16),
				new java.awt.Color(0, 128, 0)));

		cboTipoMora = new JComboBox();
		contenedor.add(cboTipoMora);
		cboTipoMora.setBounds(239, 168, 213, 66);
		cboTipoMora.setModel(Constantes.TipoMoraModel);
		cboTipoMora.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboTipoMora.setOpaque(false);
		cboTipoMora.setBorder(BorderFactory.createTitledBorder(null, "TIPO MORA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16),
				new java.awt.Color(0, 128, 0)));
		cboTipoMora.setBackground(new java.awt.Color(255, 255, 255));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setToolTipText("REGISTRAR PRÉSTAMO");
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(72, 252, 64, 64);	
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 26));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Utiles.Validar(contenedor)) {
					BigDecimal mora = (String.valueOf(cboTipoMora.getSelectedItem()).equals("%")) ? Constantes.MORA_CERO : Constantes.MORA_SOLES;
					Prestamo prestamo = new Prestamo();
					prestamo.setDescripcion(txtDescripcion.getText().toUpperCase());
					prestamo.setInteres(new BigDecimal(txtInteres.getText()));
					prestamo.setSede(((Sede) cboSede.getSelectedItem()));
					prestamo.setFlag(txtTipo.getText().toUpperCase());
					prestamo.setTMora(String.valueOf(cboTipoMora.getSelectedItem()));
					prestamo.setMora(String.valueOf(mora));
					prestamo.setFechaCreacion(String.valueOf(LocalDate.now()));
					prestamo.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new PrestamoController().RegistrarPrestamo(prestamo);
					Utiles.Mensaje("Préstamo registrado.", JOptionPane.INFORMATION_MESSAGE);
					Utiles.Limpiar(contenedor);
					ListarPrestamos();
				}else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnEditar = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnEditar);
		btnEditar.setBorderPainted(false);
		btnEditar.setToolTipText("ACTUALIZAR PRÉSTAMO");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEditar.setBounds(202, 252, 64, 64);
		btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 26));
		btnEditar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Utiles.Validar(contenedor)) {
					BigDecimal mora = (String.valueOf(cboTipoMora.getSelectedItem()).equals("%")) ? Constantes.MORA_CERO : Constantes.MORA_SOLES;
					Prestamo prestamo = new Prestamo();
					prestamo.setDescripcion(txtDescripcion.getText().toUpperCase());
					prestamo.setInteres(new BigDecimal(txtInteres.getText()));
					prestamo.setSede(((Sede) cboSede.getSelectedItem()));
					prestamo.setFlag(txtTipo.getText().toUpperCase());
					prestamo.setTMora(String.valueOf(cboTipoMora.getSelectedItem()));
					prestamo.setMora(String.valueOf(mora));
					prestamo.setFechaCreacion(String.valueOf(LocalDate.now()));
					prestamo.setUsuarioCreacion(Principal.LOGGED.getLogin());
					new PrestamoController().ActualizarPrestamo(prestamo);
					Utiles.Mensaje("Préstamo registrado.", JOptionPane.INFORMATION_MESSAGE);
					Utiles.Limpiar(contenedor);
					ListarPrestamos();
				}else {
					Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnEliminar = new JButton(new ImageIcon("img/eliminar.png"));
		contenedor.add(btnEliminar);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setToolTipText("ELIMINAR PRÉSTAMO");
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnEliminar.setBounds(333, 252, 64, 64);
		btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 26));
		btnEliminar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		spPrestamo = new JScrollPane();
		contenedor.add(spPrestamo);
		spPrestamo.setBounds(464, 12, 710, 304);
		spPrestamo.setBackground(new java.awt.Color(255, 255, 255));
		spPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		cboSede = new JComboBox();
		contenedor.add(cboSede);
		new ArticuloController().CargarSedes("CASA").forEach(s -> cboSede.addItem(s));
		cboSede.setFont(new java.awt.Font("Segoe UI", 1, 20));
		cboSede.setBorder(BorderFactory.createTitledBorder(null, "SEDE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16),
				new java.awt.Color(0, 128, 0)));
		cboSede.setOpaque(false);
		cboSede.setBounds(239, 90, 213, 66);
		cboSede.setBackground(new java.awt.Color(255, 255, 255));

		tbPrestamos = new JXTable();
		tbPrestamos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbPrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbPrestamos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbPrestamos.setRowHeight(35);
		spPrestamo.setViewportView(tbPrestamos);
		tbPrestamos.setPreferredSize(new java.awt.Dimension(1133, 271));
		tbPrestamos.setModel(Constantes.PrestamoModel);
		tbPrestamos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbPrestamos.getSelectedRow();
					
					txtDescripcion.setText(tbPrestamos.getValueAt(fila, 1).toString());
					txtInteres.setText(tbPrestamos.getValueAt(fila, 2).toString());
					cboTipoMora.setSelectedItem(tbPrestamos.getValueAt(fila, 3).toString());
					txtTipo.setText(tbPrestamos.getValueAt(fila, 5).toString());	
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
			}
		});

		ListarPrestamos();
	}

	public void ListarPrestamos() {
		Constantes.PrestamoModel.setRowCount(0);
		for (Prestamo p : new PrestamoController().ListarPrestamos()) {
			Constantes.PrestamoModel.addRow(
					new Object[] { p.getId(), p.getDescripcion(), p.getInteres(), p.getTMora(), p.getMora(), p.getFlag()

					});
		}
		tbPrestamos.setModel(Constantes.PrestamoModel);
	}
}
