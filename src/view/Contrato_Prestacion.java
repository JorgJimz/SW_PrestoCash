package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXTitledSeparator;

import common.ComboItem;
import common.Constantes;
import common.JIconTextField;
import common.MyFocusTraversalPolicy;
import common.Utiles;
import controller.ArticuloController;
import controller.ContratoController;
import controller.PrestamoController;
import maintenance.Mantenimiento_Clientes;
import model.Articulo;
import model.Cliente;
import model.Contrato;
import model.DetalleContrato;
import model.EArticulo;
import model.EContrato;
import model.Egreso;
import model.Prestamo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Contrato_Prestacion extends JInternalFrame {
	private JLabel jLabel2;
	private JLabel lblFechaRemate;
	private JLabel lblFechaVencimiento;
	private JTable tbContratos;
	private JScrollPane spContrato;
	private JLabel lblFechaContrato;
	private JLabel lblInteres;
	private JComboBox cboTipoPrestamo;
	private JXTitledSeparator jSeparator2;
	private JIconTextField txtEn;
	private JIconTextField txtObservaciones;
	private JIconTextField txtCantidad;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel txtDni;
	private JLabel lblEstado;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JSeparator jSeparator1;
	private JLabel lblTotal;
	private JIconTextField txtTasacion;
	private JButton btnHistorial;
	private JButton btnEditarCliente;
	private JLabel lblReferencia;
	private JIconTextField txtMarca;
	private JButton btnLimpiar;
	private JButton btnMenos;
	private JButton btnMas;
	private JButton btnGrabar;
	private JIconTextField txtModelo;
	private JIconTextField txtDescripcion;
	private JLabel lblNumeroContrato;
	private JComboBox cboTipoMoneda;
	private JLabel jLabel14;
	private JLabel lblInteresCalculado;
	private JLabel lblCapital;
	private JLabel jLabel4;
	private JButton btnRegresar;
	private JIconTextField txtSerie;
	private JLabel lblNombres;
	private JLabel jLabel13;
	private JPanel contenedor;

	Contrato contrato;
	Cliente cliente;
	Prestamo prestamo;
	List<DetalleContrato> detalle = new ArrayList<DetalleContrato>();
	Vector<Component> order = new Vector<Component>(9);

	private JScrollPane spArticulo;
	private JTable tbArticulos;

	public Contrato_Prestacion(Cliente c) throws SQLException {
		Utiles.LimpiarModelos();

		cliente = c;
		prestamo = new Prestamo();
		contrato = new Contrato();
		contrato.setFechaContrato(String.valueOf(LocalDate.now()));
		contrato.setFechaVencimiento(String.valueOf(LocalDate.now().plusMonths(1)));
		contrato.setFechaRemate(String.valueOf(LocalDate.now().plusMonths(2)));

		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new java.awt.Dimension(1300, 710));
		this.setPreferredSize(new java.awt.Dimension(1229, 710));
		this.setBounds(0, 0, 1229, 710);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1228, 686);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		contenedor.setFocusTraversalPolicy(getFocusTraversalPolicy());

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("PRÉSTAMO");
		jLabel2.setBounds(12, 100, 170, 20);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		lblNumeroContrato = new JLabel();
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(12, 44, 176, 50);
		lblNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 48));
		lblNumeroContrato.setForeground(new java.awt.Color(0, 0, 255));
		lblNumeroContrato.setHorizontalAlignment(SwingConstants.CENTER);

		cboTipoPrestamo = new JComboBox();
		contenedor.add(cboTipoPrestamo);
		new PrestamoController().CargarPrestamos().forEach(ci -> cboTipoPrestamo.addItem(ci));
		cboTipoPrestamo.setBounds(12, 131, 176, 30);
		cboTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboTipoPrestamo.setEditable(true);
		cboTipoPrestamo.setEditable(false);
		cboTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboTipoPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnHistorial.setEnabled(true);
				ComboItem k = (ComboItem) cboTipoPrestamo.getSelectedItem();
				prestamo.setId(k.getId());
				prestamo.setDescripcion(k.getDescripcion());
				prestamo.setInteres(new BigDecimal(String.valueOf(k.getValor())));
				prestamo.setFlag(String.valueOf(k.getExtraValor()));
				lblInteres.setText(k.getValor() + "%");
				lblNumeroContrato.setText(String.valueOf(k.getExtraValor()) + "-" + String
						.valueOf(new ContratoController().ObtenerCorrelativo(String.valueOf(k.getExtraValor()))));

			}
		});

		lblInteres = new JLabel();
		contenedor.add(lblInteres);
		lblInteres.setBounds(340, 43, 116, 50);
		lblInteres.setFont(new java.awt.Font("Segoe UI", 1, 30));
		lblInteres.setForeground(new java.awt.Color(0, 128, 0));

		lblFechaContrato = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaContrato())).toUpperCase());
		contenedor.add(lblFechaContrato);
		lblFechaContrato.setBounds(462, 70, 160, 50);
		lblFechaContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblFechaContrato.setBackground(new java.awt.Color(240, 145, 4));
		lblFechaContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaContrato.setForeground(Color.WHITE);
		lblFechaContrato.setOpaque(true);
		lblFechaContrato.setHorizontalAlignment(SwingConstants.CENTER);

		lblFechaVencimiento = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaVencimiento())).toUpperCase());
		contenedor.add(lblFechaVencimiento);
		lblFechaVencimiento.setBounds(628, 70, 160, 50);
		lblFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblFechaVencimiento.setForeground(Color.WHITE);
		lblFechaVencimiento.setBackground(new java.awt.Color(200, 68, 4));
		lblFechaVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaVencimiento.setOpaque(true);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.CENTER);

		lblFechaRemate = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaRemate())).toUpperCase());
		contenedor.add(lblFechaRemate);
		lblFechaRemate.setBounds(794, 70, 160, 50);
		lblFechaRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaRemate.setFont(new java.awt.Font("Segoe UI", 1, 20));
		lblFechaRemate.setBackground(new java.awt.Color(255, 0, 0));
		lblFechaRemate.setForeground(Color.WHITE);
		lblFechaRemate.setOpaque(true);
		lblFechaRemate.setHorizontalAlignment(SwingConstants.CENTER);

		btnMas = new JButton(new ImageIcon("img/mas.png"));
		contenedor.add(btnMas);
		btnMas.setText(" AGREGAR ARTÍCULO");
		btnMas.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnMas.setOpaque(false);
		btnMas.setBorderPainted(false);
		btnMas.setContentAreaFilled(false);
		btnMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMas.setBounds(965, 193, 228, 64);
		btnMas.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnMas.setHorizontalAlignment(SwingConstants.LEFT);
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Utiles.Validar(contenedor)) {
					AgregarDetalle();
					ListarDetalle();
					Utiles.Limpiar(contenedor);
					spContrato.setVisible(true);
					spArticulo.setVisible(false);
					txtCantidad.requestFocus();
				}
			}
		});

		btnMenos = new JButton(new ImageIcon("img/menos.png"));
		contenedor.add(btnMenos);
		btnMenos.setBounds(1133, 398, 64, 64);
		btnMenos.setOpaque(false);
		btnMenos.setBorderPainted(false);
		btnMenos.setContentAreaFilled(false);
		btnMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMenos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnMenos.setHorizontalAlignment(SwingConstants.LEFT);
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuitarDetalle();
				ListarDetalle();
			}
		});

		btnLimpiar = new JButton(new ImageIcon("img/limpiar.png"));
		contenedor.add(btnLimpiar);
		btnLimpiar.setBounds(1133, 474, 64, 64);
		btnLimpiar.setOpaque(false);
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setContentAreaFilled(false);
		btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnLimpiar.setHorizontalAlignment(SwingConstants.LEFT);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contrato.getDetalleContratos().clear();
				Utiles.Limpiar(contenedor);
				ListarDetalle();
			}
		});

		spContrato = new JScrollPane();
		contenedor.add(spContrato);
		spContrato.setBounds(12, 347, 1164, 229);
		spContrato.setFont(new java.awt.Font("Dialog", 1, 12));
		spContrato.setForeground(new java.awt.Color(255, 0, 0));
		spContrato.setBackground(new java.awt.Color(255, 255, 255));
		spContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
		spContrato.setViewportView(tbContratos);
		tbContratos.setModel(Constantes.ContratoModel);
		tbContratos.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int fila = tbContratos.getSelectedRow();
					int codigo = Integer.parseInt(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 0)));
					for (DetalleContrato dc : detalle) {
						if (dc.getId() == codigo) {
							dc.getArticulo()
									.setDescripcion(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 1)));
							dc.getArticulo().setMarca(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 2)));
							dc.getArticulo().setModelo(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 3)));
							dc.getArticulo().setObs(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 4)));
							dc.setTasacion(
									new BigDecimal(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 5))));
						}
					}
					ListarDetalle();
				}
			}
		});

		jSeparator2 = new JXTitledSeparator("DATOS GENERALES DEL CONTRATO");
		contenedor.add(jSeparator2);
		jSeparator2.setBounds(12, 12, 1193, 32);
		jSeparator2.setFont(new java.awt.Font("Arial Black", 1, 16));
		jSeparator2.setForeground(new java.awt.Color(181, 0, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("CLIENTE");
		jLabel13.setBounds(340, 96, 79, 29);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		btnEditarCliente = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnEditarCliente);
		btnEditarCliente.setBounds(966, 37, 228, 64);
		btnEditarCliente.setOpaque(false);
		btnEditarCliente.setBorderPainted(false);
		btnEditarCliente.setText(" ACTUALIZAR CLIENTE");
		btnEditarCliente.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnEditarCliente.setContentAreaFilled(false);
		btnEditarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditarCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEditarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mantenimiento_Clientes mc = new Mantenimiento_Clientes(txtDni.getText());
				Principal.dskPrincipal.add(mc);
			}
		});

		lblNombres = new JLabel(c.getNombres() + " " + c.getPaterno() + " " + c.getMaterno());
		contenedor.add(lblNombres);
		lblNombres.setBounds(462, 131, 492, 30);
		lblNombres.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblNombres.setForeground(new java.awt.Color(0, 64, 128));
		lblNombres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblNombres.setBackground(new java.awt.Color(255, 255, 255));
		lblNombres.setOpaque(true);
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);

		lblCapital = new JLabel();
		contenedor.add(lblCapital);
		lblCapital.setBounds(12, 608, 170, 55);
		lblCapital.setText("00.00");
		lblCapital.setFont(new java.awt.Font("Dialog", 1, 36));
		lblCapital.setForeground(new java.awt.Color(255, 255, 255));
		lblCapital.setBackground(new java.awt.Color(0, 64, 128));
		lblCapital.setOpaque(true);
		lblCapital.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapital.setHorizontalAlignment(SwingConstants.CENTER);

		lblInteresCalculado = new JLabel();
		contenedor.add(lblInteresCalculado);
		lblInteresCalculado.setBounds(188, 608, 170, 55);
		lblInteresCalculado.setText("00.00");
		lblInteresCalculado.setFont(new java.awt.Font("Dialog", 1, 36));
		lblInteresCalculado.setForeground(new java.awt.Color(255, 255, 255));
		lblInteresCalculado.setBackground(new java.awt.Color(0, 64, 128));
		lblInteresCalculado.setOpaque(true);
		lblInteresCalculado.setHorizontalTextPosition(SwingConstants.CENTER);
		lblInteresCalculado.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("MONEDA");
		jLabel14.setBounds(194, 100, 121, 20);
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));

		cboTipoMoneda = new JComboBox();
		contenedor.add(cboTipoMoneda);
		cboTipoMoneda.setBounds(194, 131, 140, 30);
		cboTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cboTipoMoneda.setModel(Constantes.MonedaModel);
		cboTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		txtDescripcion = new JIconTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(69, 199, 326, 60);
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));
		txtDescripcion.setBorder(BorderFactory.createTitledBorder(null, "DESCRIPCIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtModelo = new JIconTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(587, 199, 180, 60);
		txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));
		txtModelo.setBorder(BorderFactory.createTitledBorder(null, "MODELO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtMarca = new JIconTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(401, 199, 180, 60);
		txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));
		txtMarca.setBorder(BorderFactory.createTitledBorder(null, "MARCA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		lblReferencia = new JLabel();
		contenedor.add(lblReferencia);
		lblReferencia.setBounds(290, 79, 10, 10);
		lblReferencia.setVisible(false);

		txtTasacion = new JIconTextField();
		contenedor.add(txtTasacion);
		txtTasacion.setBounds(773, 275, 180, 60);
		txtTasacion.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtTasacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTasacion.setForeground(new java.awt.Color(0, 64, 128));
		txtTasacion.setBorder(BorderFactory.createTitledBorder(null, "TASACIÓN", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));
		txtTasacion.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Utiles.Validar(contenedor)) {
						AgregarDetalle();
						ListarDetalle();
						Utiles.Limpiar(contenedor);
						spContrato.setVisible(true);
						spArticulo.setVisible(false);
						txtCantidad.requestFocus();
					}
				}
			}
		});

		lblTotal = new JLabel();
		contenedor.add(lblTotal);
		lblTotal.setText("00.00");
		lblTotal.setBounds(364, 608, 170, 55);
		lblTotal.setFont(new java.awt.Font("Dialog", 1, 36));
		lblTotal.setForeground(new java.awt.Color(255, 255, 255));
		lblTotal.setBackground(new java.awt.Color(0, 64, 128));
		lblTotal.setOpaque(true);
		lblTotal.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);

		btnGrabar = new JButton(new ImageIcon("img/grabarContrato.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setText("GENERAR CONTRATO");
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnGrabar.setBounds(965, 272, 228, 64);		
		btnGrabar.setBackground(new java.awt.Color(128, 255, 255));
		btnGrabar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tbContratos.getRowCount() > 0) {
					try {
						contrato.setCapital(new BigDecimal(lblCapital.getText()));
						contrato.setInteresMensual(new BigDecimal(lblInteresCalculado.getText()));
						contrato.setPrestamo(prestamo);
						contrato.setFlag(prestamo.getFlag());
						contrato.setMoneda(String.valueOf(cboTipoMoneda.getSelectedItem()));
						contrato.setCliente(cliente);
						contrato.setNumero(Integer.parseInt(lblNumeroContrato.getText().split("-")[1]));
						contrato.setEContrato(new EContrato(1));
						contrato.setFechaCreacion(String.valueOf(LocalDate.now()));
						contrato.setUsuarioCreacion(Principal.LOGGED.getLogin());
						for (DetalleContrato dc : detalle) {
							dc.setContrato(contrato);
							dc.setCantidad(dc.getCantidad());
							dc.setTasacion(dc.getTasacion());
							contrato.getDetalleContratos().add(dc);
						}
						Egreso egreso = new Egreso();
						egreso.setLibroCaja(Principal.LIBRO_CAJA);
						egreso.setDescripcion(contrato.getFlag() + "-" + contrato.getNumero());
						egreso.setImporte(contrato.getCapital());
						egreso.setTipo("EMP");
						egreso.setMoneda(String.valueOf(cboTipoMoneda.getSelectedItem()));
						new ContratoController().GenerarContrato(contrato, egreso);
						Utiles.LimpiarModelos();
						Utiles.Mensaje("¡Contrato generado!", JOptionPane.INFORMATION_MESSAGE);
						ImprimirFormato();
					} catch (Exception e) {
						e.printStackTrace();
						Utiles.Mensaje("Error. No se pudo completar la operación.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Utiles.Mensaje("Se requiere como mínimo una prenda para generar el contrato.",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		lblEstado = new JLabel();
		contenedor.add(lblEstado);
		lblEstado.setText("ACTIVO");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(194, 44, 140, 50);
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 30));
		lblEstado.setBackground(new java.awt.Color(0, 128, 0));
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setOpaque(true);

		txtDni = new JLabel(c.getDocumento());
		contenedor.add(txtDni);
		txtDni.setBounds(340, 131, 116, 30);
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDni.setForeground(new java.awt.Color(0, 64, 128));
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDni.setBackground(new java.awt.Color(255, 255, 255));
		txtDni.setOpaque(true);
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("CAPITAL");
		jLabel9.setBounds(12, 582, 170, 20);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("INTERES");
		jLabel10.setBounds(188, 582, 170, 20);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("TOTAL");
		jLabel11.setBounds(364, 582, 170, 20);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		txtCantidad = new JIconTextField();
		contenedor.add(txtCantidad);
		txtCantidad.setBounds(11, 199, 52, 60);
		txtCantidad.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtCantidad.setForeground(new java.awt.Color(0, 64, 128));
		txtCantidad.setBorder(BorderFactory.createTitledBorder(null, "Q", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtObservaciones = new JIconTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(11, 275, 570, 60);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtObservaciones.setForeground(new java.awt.Color(0, 64, 128));
		txtObservaciones.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtEn = new JIconTextField();
		contenedor.add(txtEn);
		txtEn.setBounds(587, 275, 180, 60);
		txtEn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtEn.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtEn.setForeground(new java.awt.Color(0, 64, 128));
		txtEn.setBorder(BorderFactory.createTitledBorder(null, "COVER", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		btnHistorial = new JButton(new ImageIcon("img/historial.png"));
		contenedor.add(btnHistorial);
		btnHistorial.setEnabled(false);
		btnHistorial.setText(" HISTORIAL PRENDAS");
		btnHistorial.setBorderPainted(false);
		btnHistorial.setContentAreaFilled(false);
		btnHistorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnHistorial.setBounds(966, 115, 228, 64);
		btnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnHistorial.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnHistorial.setHorizontalAlignment(SwingConstants.LEFT);
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new ArticuloController().ObtenerHistorial(Constantes.ArticuloModel, cliente.getId())
						.getRowCount() != 0) {
					btnHistorial.setVisible(false);
					btnRegresar.setVisible(true);
					spContrato.setVisible(false);
					spArticulo.setVisible(true);
					tbArticulos.setModel(Constantes.ArticuloModel);
				} else {
					Utiles.Mensaje("Sin historial.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		txtSerie = new JIconTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtSerie.setBounds(773, 199, 180, 60);
		txtSerie.setBorder(BorderFactory.createTitledBorder(null, "SERIE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		tbArticulos = new JTable();
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.setModel(Constantes.ArticuloModel);
		tbArticulos.setRowHeight(30);
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbArticulos.getSelectedRow();
					Articulo articulo = new Articulo();
					articulo.setId(Integer.parseInt(String.valueOf(tbArticulos.getValueAt(fila, 0))));
					articulo.setDescripcion(String.valueOf(tbArticulos.getValueAt(fila, 1)));
					articulo.setMarca(String.valueOf(tbArticulos.getValueAt(fila, 2)));
					articulo.setModelo(String.valueOf(tbArticulos.getValueAt(fila, 3)));
					articulo.setSerie("POR COMPLETAR");
					articulo.setEArticulo(new EArticulo(1));
					articulo.setObs(String.valueOf(tbArticulos.getValueAt(fila, 4)));

					DetalleContrato detalle_contrato = new DetalleContrato();
					detalle_contrato.setId(new Random().nextInt(100));
					detalle_contrato.setContrato(contrato);
					detalle_contrato.setArticulo(articulo);
					detalle_contrato.setArticuloJasper(
							articulo.getDescripcion() + " " + articulo.getMarca() + " " + articulo.getModelo());
					detalle_contrato.setObservacionArticuloJasper(articulo.getObs() + " " + txtEn.getText());
					detalle_contrato.setCantidad(1);
					detalle_contrato.setTasacion(new BigDecimal(String.valueOf(tbArticulos.getValueAt(fila, 5))));

					detalle.add(detalle_contrato);

					btnRegresar.setVisible(false);
					btnHistorial.setVisible(true);
					spContrato.setVisible(true);
					spArticulo.setVisible(false);
					ListarDetalle();
				}
			}
		});

		spArticulo = new JScrollPane();
		contenedor.add(spArticulo);
		spArticulo.setBounds(12, 471, 1198, 223);
		spArticulo.setVisible(false);
		spArticulo.setBackground(new java.awt.Color(255, 255, 255));
		spArticulo.setViewportView(tbArticulos);

		btnRegresar = new JButton(new ImageIcon("img/regresar.png"));
		contenedor.add(btnRegresar);
		btnRegresar.setVisible(false);
		btnRegresar.setOpaque(false);
		btnRegresar.setBorderPainted(false);
		btnRegresar.setContentAreaFilled(false);
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setBounds(960, 312, 295, 64);
		btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnRegresar.setBounds(960, 312, 295, 64);
		btnRegresar.setText(" REGRESAR");
		btnRegresar.setHorizontalAlignment(SwingConstants.LEFT);
		{
			jSeparator1 = new JSeparator();
			contenedor.add(jSeparator1);
			jSeparator1.setBounds(12, 178, 941, 9);
		}
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("FECHA CONTRATO");
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
			jLabel1.setBounds(462, 38, 160, 29);
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("FEC. VENCIMIENTO");
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
			jLabel3.setBounds(628, 38, 160, 29);
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("FECHA REMATE");
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16));
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
			jLabel4.setBounds(794, 38, 160, 29);
		}
		btnRegresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnHistorial.setVisible(true);
				btnRegresar.setVisible(false);
				spArticulo.setVisible(false);
				spContrato.setVisible(true);
			}
		});

		order.add(txtCantidad);
		order.add(txtDescripcion);
		order.add(txtMarca);
		order.add(txtModelo);
		order.add(txtObservaciones);
		order.add(txtSerie);
		order.add(txtEn);
		order.add(txtTasacion);
		order.add(btnMas);

		this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(order));

	}

	public void AgregarDetalle() {
		try {
			Articulo articulo = new Articulo();
			articulo.setDescripcion(txtDescripcion.getText().toUpperCase());
			articulo.setMarca(txtMarca.getText().toUpperCase());
			articulo.setModelo(txtModelo.getText().toUpperCase());
			articulo.setSerie(txtSerie.getText().toUpperCase());
			articulo.setObs(txtObservaciones.getText().toUpperCase());
			articulo.setEArticulo(new EArticulo(1));
			articulo.setFechaCreacion(String.valueOf(LocalDate.now()));
			articulo.setUsuarioCreacion(Principal.LOGGED.getLogin());

			DetalleContrato detalle_contrato = new DetalleContrato();
			detalle_contrato.setId(new Random().nextInt(100));
			detalle_contrato.setContrato(contrato);
			detalle_contrato.setArticulo(articulo);
			detalle_contrato.setArticuloJasper(
					articulo.getDescripcion() + " " + articulo.getMarca() + " " + articulo.getModelo());
			detalle_contrato.setObservacionArticuloJasper(articulo.getObs() + " " + txtEn.getText());
			detalle_contrato.setCantidad(Integer.parseInt(txtCantidad.getText()));
			detalle_contrato.setTasacion(new BigDecimal(txtTasacion.getText()));

			detalle.add(detalle_contrato);
		} catch (NumberFormatException e) {
			Utiles.Mensaje("Ingrese un valor numérico válido.", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void QuitarDetalle() {
		int fila = tbContratos.getSelectedRow();
		int codigo = Integer.parseInt(String.valueOf(tbContratos.getValueAt(fila, 0)));
		for (DetalleContrato dc : detalle) {
			if (dc.getId() == codigo) {
				detalle.remove(dc);
				break;
			}
		}
	}

	public void ListarDetalle() {
		Constantes.ContratoModel.setRowCount(0);
		BigDecimal capital = BigDecimal.ZERO;

		for (DetalleContrato dc : detalle) {
			Constantes.ContratoModel
					.addRow(new Object[] { dc.getId(), dc.getArticulo().getDescripcion(), dc.getArticulo().getMarca(),
							dc.getArticulo().getModelo(), dc.getArticulo().getObs(), dc.getTasacion() });
			capital = capital.add(dc.getTasacion());
		}

		BigDecimal porcentajeInteres = prestamo.getInteres().divide(new BigDecimal(100));

		BigDecimal interes = (capital.multiply(porcentajeInteres).compareTo(BigDecimal.TEN) <= 0) ? BigDecimal.TEN
				: capital.multiply(porcentajeInteres).setScale(2, RoundingMode.HALF_UP);
		BigDecimal total = capital.add(interes);

		tbContratos.setRowHeight(100);
		tbContratos.setModel(Constantes.ContratoModel);
		lblCapital.setText(String.valueOf(capital.setScale(2, RoundingMode.HALF_UP)));
		lblInteresCalculado.setText(String.valueOf(interes));
		lblTotal.setText(String.valueOf(total));
	}

	public void ConfirmarCerrar() {
		int n = JOptionPane.showConfirmDialog(null, "<html><h3>¿Salir sin guardar?</h3></html>", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void Cerrar() {
		this.dispose();
	}

	public void ImprimirFormato() {
		contrato.setClienteJasper(contrato.getCliente().getNombreCompleto());
		ArrayList<Contrato> arreglo_contrato = new ArrayList<Contrato>();
		arreglo_contrato.add(contrato);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("direccion_cliente", cliente.getDireccion());
		parametros.put("telefonos_cliente", cliente.getTlf1() + " / " + cliente.getTlf2());
		parametros.put("fecha_contrato", lblFechaContrato.getText().toUpperCase());
		parametros.put("fecha_vencimiento", lblFechaVencimiento.getText().toUpperCase());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/contrato.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
					new JRBeanCollectionDataSource(arreglo_contrato));
			JasperPrintManager.printReport(jasperPrint, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
