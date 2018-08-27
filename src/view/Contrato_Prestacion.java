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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXTitledSeparator;

import common.ComboItem;
import common.Constantes;
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
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "deprecation" })
public class Contrato_Prestacion extends JInternalFrame {
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel lblFechaRemate;
	private JLabel lblFechaVencimiento;
	private JTable tbContratos;
	private JScrollPane spContrato;
	private JLabel lblFechaContrato;
	private JLabel lblInteres;
	private JComboBox cboTipoPrestamo;
	private JXTitledSeparator jSeparator2;
	private JLabel jLabel17;
	private JTextField txtEn;
	private JTextField txtObservaciones;
	private JTextField txtCantidad;
	private JLabel jLabel18;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel txtDni;
	private JLabel lblEstado;
	private JButton btnSalir;
	private JLabel lblTotal;
	private JTextField txtTasacion;
	private JLabel jLabel20;
	private JLabel jLabel15;
	private JLabel lblP;
	private JButton btnHistorial;
	private JButton btnEditarCliente;
	private JLabel lblReferencia;
	private JTextField txtMarca;
	private JButton btnLimpiar;
	private JButton btnMenos;
	private JButton btnMas;
	private JButton btnGrabar;
	private JLabel jLabel19;

	private JTextField txtModelo;
	private JTextField txtDescripcion;
	private JLabel jLabel16;
	private JLabel jLabel6;
	private JLabel lblNumeroContrato;
	private JComboBox cboTipoMoneda;
	private JLabel jLabel14;
	private JLabel lblInteresCalculado;
	private JLabel lblCapital;
	private JTextField txtSerie;
	private JLabel jLabel1;
	private JLabel lblNombres;
	private JLabel jLabel13;
	private JPanel contenedor;

	Contrato contrato;
	Cliente cliente;
	Prestamo prestamo;
	List<DetalleContrato> detalle = new ArrayList<DetalleContrato>();

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
		this.setSize(1370, 735);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1300, 897));
		this.setBounds(0, 0, 1300, 897);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1299, 867);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		contenedor.setFocusTraversalPolicy(getFocusTraversalPolicy());

		jLabel2 = new JLabel();
		contenedor.add(jLabel2);
		jLabel2.setText("PRÉSTAMO:");
		jLabel2.setBounds(350, 69, 179, 27);
		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel2.setForeground(new java.awt.Color(0, 128, 0));

		lblNumeroContrato = new JLabel();
		contenedor.add(lblNumeroContrato);
		lblNumeroContrato.setBounds(105, 45, 226, 76);
		lblNumeroContrato.setFont(new java.awt.Font("Gisha", 1, 72));
		lblNumeroContrato.setForeground(new java.awt.Color(0, 0, 255));
		lblNumeroContrato.setBackground(new java.awt.Color(255, 255, 255));
		lblNumeroContrato.setHorizontalAlignment(SwingConstants.CENTER);

		cboTipoPrestamo = new JComboBox();
		contenedor.add(cboTipoPrestamo);
		cboTipoPrestamo.setBounds(478, 65, 235, 32);
		cboTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 22));
		cboTipoPrestamo.setEditable(true);
		cboTipoPrestamo.setEditable(false);
		cboTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboTipoPrestamo.setModel(new PrestamoController().CargarPrestamos());
		cboTipoPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnHistorial.setEnabled(true);
				ComboItem k = (ComboItem) cboTipoPrestamo.getSelectedItem();
				prestamo.setId(k.getId());
				prestamo.setDescripcion(k.getDescripcion());
				prestamo.setInteres(new BigDecimal(String.valueOf(k.getValor())));
				prestamo.setFlag(String.valueOf(k.getExtraValor()));
				lblInteres.setText(k.getValor() + "%");
				lblNumeroContrato.setText(
						String.valueOf(new ContratoController().ObtenerCorrelativo(String.valueOf(k.getExtraValor()))));
				lblP.setText(String.valueOf(k.getExtraValor()));
			}
		});

		jLabel3 = new JLabel();
		contenedor.add(jLabel3);
		jLabel3.setText("INTERÉS");
		jLabel3.setBounds(751, 69, 92, 27);
		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel3.setForeground(new java.awt.Color(0, 128, 0));

		lblInteres = new JLabel();
		contenedor.add(lblInteres);
		lblInteres.setBounds(751, 108, 155, 50);
		lblInteres.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblInteres.setForeground(new java.awt.Color(0, 128, 0));

		lblFechaContrato = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaContrato())).toUpperCase());
		contenedor.add(lblFechaContrato);
		lblFechaContrato.setBounds(930, 39, 325, 54);
		lblFechaContrato.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblFechaContrato.setBackground(new java.awt.Color(240, 145, 4));
		lblFechaContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaContrato.setForeground(Color.WHITE);
		lblFechaContrato.setOpaque(true);
		lblFechaContrato.setHorizontalAlignment(SwingConstants.CENTER);

		lblFechaVencimiento = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaVencimiento())).toUpperCase());
		contenedor.add(lblFechaVencimiento);
		lblFechaVencimiento.setBounds(930, 107, 325, 54);
		lblFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblFechaVencimiento.setForeground(Color.WHITE);
		lblFechaVencimiento.setBackground(new java.awt.Color(200, 68, 4));
		lblFechaVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaVencimiento.setOpaque(true);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.CENTER);

		lblFechaRemate = new JLabel(
				Constantes.formatoLocal.format(LocalDate.parse(contrato.getFechaRemate())).toUpperCase());
		contenedor.add(lblFechaRemate);
		lblFechaRemate.setBounds(930, 173, 325, 54);
		lblFechaRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblFechaRemate.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblFechaRemate.setBackground(new java.awt.Color(255, 0, 0));
		lblFechaRemate.setForeground(Color.WHITE);
		lblFechaRemate.setOpaque(true);
		lblFechaRemate.setHorizontalAlignment(SwingConstants.CENTER);

		btnMas = new JButton(new ImageIcon("img/mas.png"));
		contenedor.add(btnMas);
		btnMas.setText(" AGREGAR ARTÍCULO");
		btnMas.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnMas.setOpaque(false);
		btnMas.setBorderPainted(false);
		btnMas.setContentAreaFilled(false);
		btnMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMas.setBounds(960, 394, 295, 64);
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
		btnMenos.setBounds(1191, 676, 64, 64);
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
		btnLimpiar.setBounds(1191, 600, 64, 64);
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
		spContrato.setBounds(12, 471, 1198, 283);
		spContrato.setFont(new java.awt.Font("Dialog", 1, 12));
		spContrato.setForeground(new java.awt.Color(255, 0, 0));
		spContrato.setBackground(new java.awt.Color(255, 255, 255));
		spContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		tbContratos = new JTable();
		tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
		spContrato.setViewportView(tbContratos);
		tbContratos.setModel(Constantes.ContratoModel);
		tbContratos.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int fila = tbContratos.getSelectedRow();
					int codigo = Integer.parseInt(String.valueOf(Constantes.ContratoModel.getValueAt(fila, 0)));
					for (DetalleContrato dc : contrato.getDetalleContratos()) {
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
		jSeparator2.setBounds(12, 12, 1252, 32);
		jSeparator2.setFont(new java.awt.Font("Arial Black", 1, 24));
		jSeparator2.setForeground(new java.awt.Color(181, 0, 0));

		jLabel13 = new JLabel();
		contenedor.add(jLabel13);
		jLabel13.setText("CLIENTE:");
		jLabel13.setBounds(12, 189, 110, 27);
		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel13.setForeground(new java.awt.Color(0, 128, 0));

		btnEditarCliente = new JButton(new ImageIcon("img/edit.png"));
		contenedor.add(btnEditarCliente);
		btnEditarCliente.setBounds(845, 164, 64, 64);
		btnEditarCliente.setOpaque(false);
		btnEditarCliente.setBorderPainted(false);
		btnEditarCliente.setContentAreaFilled(false);
		btnEditarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditarCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnEditarCliente.setBackground(new java.awt.Color(128, 255, 255));
		btnEditarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mantenimiento_Clientes mc = new Mantenimiento_Clientes(txtDni.getText());
				Principal.dskPrincipal.add(mc);
			}
		});

		lblNombres = new JLabel(c.getNombres() + " " + c.getPaterno() + " " + c.getMaterno());
		contenedor.add(lblNombres);
		lblNombres.setBounds(286, 184, 567, 32);
		lblNombres.setFont(new java.awt.Font("Segoe UI", 1, 24));
		lblNombres.setForeground(new java.awt.Color(0, 64, 128));
		lblNombres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblNombres.setBackground(new java.awt.Color(255, 255, 255));
		lblNombres.setOpaque(true);
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);

		lblCapital = new JLabel();
		contenedor.add(lblCapital);
		lblCapital.setBounds(12, 787, 213, 55);
		lblCapital.setText("00.00");
		lblCapital.setFont(new java.awt.Font("Gisha", 1, 60));
		lblCapital.setForeground(new java.awt.Color(255, 255, 255));
		lblCapital.setBackground(new java.awt.Color(0, 64, 128));
		lblCapital.setOpaque(true);
		lblCapital.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapital.setHorizontalAlignment(SwingConstants.CENTER);

		lblInteresCalculado = new JLabel();
		contenedor.add(lblInteresCalculado);
		lblInteresCalculado.setBounds(250, 787, 216, 55);
		lblInteresCalculado.setText("00.00");
		lblInteresCalculado.setFont(new java.awt.Font("Gisha", 1, 60));
		lblInteresCalculado.setForeground(new java.awt.Color(255, 255, 255));
		lblInteresCalculado.setBackground(new java.awt.Color(0, 64, 128));
		lblInteresCalculado.setOpaque(true);
		lblInteresCalculado.setHorizontalTextPosition(SwingConstants.CENTER);
		lblInteresCalculado.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel14 = new JLabel();
		contenedor.add(jLabel14);
		jLabel14.setText("MONEDA:");
		jLabel14.setBounds(12, 132, 127, 27);
		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel14.setForeground(new java.awt.Color(0, 128, 0));

		cboTipoMoneda = new JComboBox();
		contenedor.add(cboTipoMoneda);
		cboTipoMoneda.setBounds(121, 129, 161, 32);
		cboTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 24));
		cboTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		cboTipoMoneda.setModel(Constantes.MonedaModel);

		jLabel6 = new JLabel();
		contenedor.add(jLabel6);
		jLabel6.setText("DESCRIPCIÓN:");
		jLabel6.setBounds(134, 235, 158, 32);
		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel6.setForeground(new java.awt.Color(0, 128, 0));

		jLabel16 = new JLabel();
		contenedor.add(jLabel16);
		jLabel16.setText("MODELO:");
		jLabel16.setBounds(960, 234, 114, 32);
		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel16.setForeground(new java.awt.Color(0, 128, 0));

		txtDescripcion = new JTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(134, 268, 475, 32);
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));

		txtModelo = new JTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(960, 268, 295, 32);
		txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtModelo.setHorizontalAlignment(SwingConstants.CENTER);
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));

		jLabel19 = new JLabel();
		contenedor.add(jLabel19);
		jLabel19.setText("MARCA:");
		jLabel19.setBounds(621, 234, 109, 32);
		jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel19.setForeground(new java.awt.Color(0, 128, 0));

		txtMarca = new JTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(621, 268, 327, 32);
		txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMarca.setHorizontalAlignment(SwingConstants.CENTER);
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));

		lblReferencia = new JLabel();
		contenedor.add(lblReferencia);
		lblReferencia.setBounds(290, 79, 10, 10);
		lblReferencia.setVisible(false);

		jLabel20 = new JLabel();
		contenedor.add(jLabel20);
		jLabel20.setText("TASACIÓN:");
		jLabel20.setBounds(695, 391, 124, 32);
		jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel20.setForeground(new java.awt.Color(0, 128, 0));

		txtTasacion = new JTextField();
		contenedor.add(txtTasacion);
		txtTasacion.setBounds(695, 423, 253, 32);
		txtTasacion.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtTasacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTasacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtTasacion.setForeground(new java.awt.Color(0, 64, 128));
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
		lblTotal.setBounds(493, 787, 216, 55);
		lblTotal.setFont(new java.awt.Font("Gisha", 1, 60));
		lblTotal.setForeground(new java.awt.Color(255, 255, 255));
		lblTotal.setBackground(new java.awt.Color(0, 64, 128));
		lblTotal.setOpaque(true);
		lblTotal.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);

		btnGrabar = new JButton(new ImageIcon("img/grabarContrato.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setText(" GENERAR CONTRATO");
		btnGrabar.setFocusable(false);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		//btnGrabar.setBounds(737, 775, 294, 64);
		btnGrabar.setBounds(960, 287, 294, 64);
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.setBackground(new java.awt.Color(128, 255, 255));
		btnGrabar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					contrato.setCapital(new BigDecimal(lblCapital.getText()));
					contrato.setInteresMensual(new BigDecimal(lblInteresCalculado.getText()));
					contrato.setPrestamo(prestamo);
					contrato.setFlag(prestamo.getFlag());
					contrato.setMoneda(String.valueOf(cboTipoMoneda.getSelectedItem()));
					contrato.setCliente(cliente);
					contrato.setNumero(Integer.parseInt(lblNumeroContrato.getText()));
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
					new ContratoController().GenerarContrato(contrato, egreso);
					Utiles.LimpiarModelos();
					Utiles.Mensaje("¡Contrato generado!", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					Utiles.Mensaje("Error. No se pudo completar la operación.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnSalir = new JButton(new ImageIcon("img/salir.png"));
		contenedor.add(btnSalir);
		btnSalir.setFocusable(false);
		btnSalir.setText(" SALIR");
		btnSalir.setOpaque(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//btnSalir.setBounds(1043, 775, 212, 64);
		btnSalir.setBounds(1057, 221, 212, 64);
		btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnSalir.setBackground(new java.awt.Color(128, 255, 255));
		btnSalir.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfirmarCerrar();
			}
		});

		lblEstado = new JLabel();
		contenedor.add(lblEstado);
		lblEstado.setText("ACTIVO");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(481, 120, 235, 39);
		lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 36));
		lblEstado.setBackground(new java.awt.Color(0, 128, 0));
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setOpaque(true);

		txtDni = new JLabel(c.getDocumento());
		contenedor.add(txtDni);
		txtDni.setBounds(122, 184, 158, 32);
		txtDni.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtDni.setForeground(new java.awt.Color(0, 64, 128));
		txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtDni.setBackground(new java.awt.Color(255, 255, 255));
		txtDni.setOpaque(true);
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel7 = new JLabel();
		contenedor.add(jLabel7);
		jLabel7.setText("ESTADO:");
		jLabel7.setBounds(349, 132, 141, 27);
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel7.setForeground(new java.awt.Color(0, 128, 0));

		jLabel8 = new JLabel();
		contenedor.add(jLabel8);
		jLabel8.setText("OBS:");
		jLabel8.setBounds(12, 307, 109, 32);
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel8.setForeground(new java.awt.Color(0, 128, 0));

		jLabel9 = new JLabel();
		contenedor.add(jLabel9);
		jLabel9.setText("CAPITAL");
		jLabel9.setBounds(12, 755, 129, 32);
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel9.setForeground(new java.awt.Color(0, 128, 0));

		jLabel10 = new JLabel();
		contenedor.add(jLabel10);
		jLabel10.setText("INTERES");
		jLabel10.setBounds(250, 754, 137, 32);
		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel10.setForeground(new java.awt.Color(0, 128, 0));

		jLabel11 = new JLabel();
		contenedor.add(jLabel11);
		jLabel11.setText("TOTAL");
		jLabel11.setBounds(493, 755, 96, 32);
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel11.setForeground(new java.awt.Color(0, 128, 0));

		jLabel18 = new JLabel();
		contenedor.add(jLabel18);
		jLabel18.setText("Q:");
		jLabel18.setBounds(12, 237, 110, 32);
		jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel18.setForeground(new java.awt.Color(0, 128, 0));

		txtCantidad = new JTextField();
		contenedor.add(txtCantidad);
		txtCantidad.setBounds(12, 268, 110, 32);
		txtCantidad.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtCantidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setForeground(new java.awt.Color(0, 64, 128));

		txtObservaciones = new JTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(12, 347, 936, 32);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		txtObservaciones.setForeground(new java.awt.Color(0, 64, 128));

		txtEn = new JTextField();
		contenedor.add(txtEn);
		txtEn.setBounds(351, 423, 327, 32);
		txtEn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtEn.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtEn.setForeground(new java.awt.Color(0, 64, 128));

		jLabel17 = new JLabel();
		contenedor.add(jLabel17);
		jLabel17.setText("COVER:");
		jLabel17.setBounds(351, 391, 77, 32);
		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel17.setForeground(new java.awt.Color(0, 128, 0));

		btnHistorial = new JButton(new ImageIcon("img/historial.png"));
		contenedor.add(btnHistorial);
		btnHistorial.setEnabled(false);
		btnHistorial.setText(" HISTORIAL PRENDAS");
		btnHistorial.setOpaque(false);
		btnHistorial.setBorderPainted(false);
		btnHistorial.setContentAreaFilled(false);
		btnHistorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnHistorial.setBounds(960, 318, 295, 64);
		btnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnHistorial.setBackground(new java.awt.Color(128, 255, 255));
		btnHistorial.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnHistorial.setHorizontalAlignment(SwingConstants.LEFT);
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new ArticuloController().ObtenerHistorial(Constantes.ContratoModel, cliente.getId())
						.getRowCount() != 0) {
					spContrato.setVisible(false);
					spArticulo.setVisible(true);
					tbArticulos.setModel(Constantes.ContratoModel);
				} else {
					Utiles.Mensaje("Sin historial.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		lblP = new JLabel();
		contenedor.add(lblP);
		lblP.setBounds(12, 39, 81, 76);
		lblP.setFont(new java.awt.Font("Segoe UI", 1, 72));
		lblP.setForeground(new java.awt.Color(0, 0, 255));
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setHorizontalTextPosition(SwingConstants.CENTER);

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("-");
		jLabel15.setBounds(93, 34, 43, 76);
		jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 72));
		jLabel15.setForeground(new java.awt.Color(0, 0, 255));

		jLabel1 = new JLabel();
		contenedor.add(jLabel1);
		jLabel1.setText("SERIE:");
		jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
		jLabel1.setBounds(12, 391, 109, 32);

		txtSerie = new JTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 24));
		txtSerie.setHorizontalAlignment(SwingConstants.CENTER);
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtSerie.setBounds(12, 423, 327, 32);

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
					articulo.setObs(String.valueOf(tbArticulos.getValueAt(fila, 4)));

					DetalleContrato detalle_contrato = new DetalleContrato();
					detalle_contrato.setId(new Random().nextInt(100));
					detalle_contrato.setContrato(contrato);
					detalle_contrato.setArticulo(articulo);
					detalle_contrato.setCantidad(1);
					detalle_contrato.setTasacion(new BigDecimal(String.valueOf(tbArticulos.getValueAt(fila, 5))));

					detalle.add(detalle_contrato);
					spContrato.setVisible(true);
					spArticulo.setVisible(false);
					ListarDetalle();
				}
			}
		});

		spArticulo = new JScrollPane();
		contenedor.add(spArticulo);
		spArticulo.setBounds(12, 471, 1198, 283);
		spArticulo.setVisible(false);
		spArticulo.setViewportView(tbArticulos);
	}

	public void CerradoInterno() {
		int op = JOptionPane.showConfirmDialog(null, "¿Salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (op == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void AgregarDetalle() {
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
		detalle_contrato.setCantidad(Integer.parseInt(txtCantidad.getText()));
		detalle_contrato.setTasacion(new BigDecimal(txtTasacion.getText()));

		detalle.add(detalle_contrato);
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
		lblCapital.setText(String.valueOf(capital));
		lblInteresCalculado.setText(String.valueOf(interes));
		lblTotal.setText(String.valueOf(total));
	}

	public void ConfirmarCerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Cancelar la operación?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void Cerrar() {
		this.dispose();
	}

	public void imprimirContrato() {
		ArrayList<Contrato> arreglo_contrato = new ArrayList<Contrato>();
		arreglo_contrato.add(contrato);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombre_cliente", cliente.getNombres() + " " + cliente.getPaterno());
		parametros.put("direccion_cliente", cliente.getDireccion());
		parametros.put("telefonos_cliente", cliente.getTlf1() + " / " + cliente.getTlf2());
		parametros.put("fecha_contrato", lblFechaContrato.getText().toUpperCase());
		parametros.put("fecha_vencimiento", lblFechaVencimiento.getText().toUpperCase());
		parametros.put("en", txtEn.getText().toUpperCase());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObject("contrato.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
					new JRBeanCollectionDataSource(arreglo_contrato));
			JasperPrintManager.printReport(jasperPrint, true);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
