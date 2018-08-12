package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import Utils.ComboItem;
import Utils.Constantes;
import Beans.Articulo;
import Beans.Contrato;
import Beans.Egreso;
import Beans.Contrato.Detalle_Contrato;
import Conexion.MySQLConexion;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("rawtypes")
public class Contrato_Prestacion_Manual extends JInternalFrame {
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTable tbContratos;
	private JScrollPane jScrollPane1;
	private JLabel lblInteres;
	private JComboBox cboTipoPrestamo;
	private JXTitledSeparator jSeparator2;	
	private JTextField txtTotal;
	private JTextField txtInteres;
	private JTextField txtCapital;
	private JDateChooser dchRemate;
	private JDateChooser dchVencimiento;
	private JDateChooser dchInicio;
	private JLabel jLabel23;
	private JLabel jLabel22;
	private JLabel jLabel21;
	private JTextField txtDni;
	private JComboBox cboEstado;
	private JTextField txtNumeroContrato;
	private JLabel jLabel17;
	private JTextField txtEn;
	private JComboBox cboComoSeEntero;
	private JLabel jLabel5;
	private JTextField txtObservaciones;
	private JTextField txtCantidad;
	private JLabel jLabel18;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JButton btnSalir;
	private JTextField txtTasacion;
	private JLabel jLabel20;
	private JLabel lblReferencia;
	private JTextField txtMarca;
	private JButton btnLimpiar;
	private JButton btnMenos;
	private JButton btnMas;
	private JButton btnGrabar;
	private JLabel lblCodArticulo;
	private JLabel jLabel19;
	SimpleDateFormat formatoLocal = new SimpleDateFormat("dd-MMM-yyyy");
	private JTextField txtModelo;
	private JTextField txtDescripcion;
	private JLabel jLabel16;
	private JLabel jLabel6;
	private JLabel jLabel1;
	private JComboBox cboTipoMoneda;
	private JLabel jLabel14;
	private JLabel lblNombres;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JPanel contenedor;
	private Contrato c = new Contrato();
	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	DecimalFormat formatoDecimal;
	SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
	public ArrayList<Articulo> listado_articulos = new ArrayList<Articulo>();
	DefaultComboBoxModel modeloPrestamo = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel();
	private JLabel jLabel15;
	private JTextField lblP;
	private JLabel lblUltimoContrato;
	private JLabel jLabel24;
	DefaultTableModel modeloContrato = new DefaultTableModel(null,
			new String[] { "CÓDIGO", "DESCRIPCIÓN", "MARCA", "MODELO",
					"OBSERVACIONES", "TASACIÓN" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Contrato_Prestacion_Manual() throws Exception {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1370, 735);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1300, 725));
		this.setBounds(0, 0, 1300, 725);
		simbolo.setDecimalSeparator('.');
		formatoDecimal = new DecimalFormat("######.00", simbolo);
		{
			contenedor = new JPanel();
			getContentPane().add(contenedor);
			contenedor.setLayout(null);
			contenedor.setBounds(0, 0, 1299, 700);
			contenedor.setBackground(new java.awt.Color(255, 200, 147));
			contenedor.setFocusTraversalPolicy(getFocusTraversalPolicy());
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("PRÉSTAMO:");
			jLabel2.setBounds(350, 69, 179, 27);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			cboTipoPrestamo = new JComboBox();
			contenedor.add(cboTipoPrestamo);
			cboTipoPrestamo.setBounds(478, 65, 236, 32);
			cboTipoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 24));
			cboTipoPrestamo.setEditable(true);
			cboTipoPrestamo.setEditable(false);
			cboTipoPrestamo.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
					1, new java.awt.Color(0, 0, 0)));
			cboTipoPrestamo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					lblInteres.setText(((ComboItem) cboTipoPrestamo
							.getSelectedItem()).getInteres() + "");

				}
			});
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("INTERÉS");
			jLabel3.setBounds(767, 69, 92, 27);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblInteres = new JLabel();
			contenedor.add(lblInteres);
			lblInteres.setBounds(755, 108, 83, 50);
			lblInteres.setFont(new java.awt.Font("Segoe UI", 1, 36));
			lblInteres.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel4 = new JLabel();
			contenedor.add(jLabel4);
			jLabel4.setText("%");
			jLabel4.setBounds(835, 108, 38, 50);
			jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36));
			jLabel4.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			
			{
				jScrollPane1 = new JScrollPane();
				contenedor.add(jScrollPane1);
				jScrollPane1.setBounds(12, 408, 894, 198);
				jScrollPane1.setFont(new java.awt.Font("Dialog", 1, 12));
				jScrollPane1.setForeground(new java.awt.Color(255, 0, 0));
				jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
				jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				
				tbContratos = new JTable();
				tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 24));
				tbContratos.getTableHeader().setFont(
						new Font("Segoe UI", Font.BOLD, 24));
				tbContratos.getTableHeader()
				.setForeground(new Color(181, 0, 0));
				jScrollPane1.setViewportView(tbContratos);
				tbContratos.setModel(modeloContrato);
				
			}
			{
				jSeparator2 = new JXTitledSeparator(
						"DATOS GENERALES DEL CONTRATO");
				contenedor.add(jSeparator2);
				jSeparator2.setBounds(12, 12, 1238, 32);
				jSeparator2.setFont(new java.awt.Font("Arial Black", 1, 24));
				jSeparator2.setForeground(new java.awt.Color(181, 0, 0));
			}
			{
				jLabel12 = new JLabel();
				contenedor.add(jLabel12);
				jLabel12.setText("D.IDENTIDAD:");
				jLabel12.setBounds(12, 188, 185, 27);
				jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel12.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel13 = new JLabel();
				contenedor.add(jLabel13);
				jLabel13.setText("CLIENTE:");
				jLabel13.setBounds(351, 188, 110, 27);
				jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel13.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				lblNombres = new JLabel();
				contenedor.add(lblNombres);
				lblNombres.setBounds(479, 183, 431, 32);
				lblNombres.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblNombres.setForeground(new java.awt.Color(0, 64, 128));
				lblNombres.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				lblNombres.setBackground(new java.awt.Color(255, 255, 255));
				lblNombres.setOpaque(true);
				lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel14 = new JLabel();
				contenedor.add(jLabel14);
				jLabel14.setText("MONEDA:");
				jLabel14.setBounds(12, 132, 127, 27);
				jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel14.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				cboTipoMoneda = new JComboBox();
				contenedor.add(cboTipoMoneda);
				cboTipoMoneda.setBounds(170, 128, 161, 32);
				cboTipoMoneda.setFont(new java.awt.Font("Segoe UI", 1, 24));
				cboTipoMoneda.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				cboTipoMoneda.addItem("SOLES");
				cboTipoMoneda.addItem("DOLARES");
			}
			{
				jLabel1 = new JLabel();
				contenedor.add(jLabel1);
				jLabel1.setText("CODIGO:");
				jLabel1.setBounds(12, 250, 114, 27);
				jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel1.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel6 = new JLabel();
				contenedor.add(jLabel6);
				jLabel6.setText("DESCRIPCIÓN:");
				jLabel6.setBounds(493, 250, 158, 27);
				jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel6.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel16 = new JLabel();
				contenedor.add(jLabel16);
				jLabel16.setText("MODELO:");
				jLabel16.setBounds(270, 299, 114, 27);
				jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel16.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				txtDescripcion = new JTextField();
				contenedor.add(txtDescripcion);
				txtDescripcion.setBounds(640, 246, 271, 32);
				txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtDescripcion.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				txtDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
				txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));
			}
			{
				txtModelo = new JTextField();
				contenedor.add(txtModelo);
				txtModelo.setBounds(402, 295, 145, 32);
				txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				txtModelo.setHorizontalAlignment(SwingConstants.CENTER);
				txtModelo.setForeground(new java.awt.Color(0, 64, 128));
			}
			{
				jLabel19 = new JLabel();
				contenedor.add(jLabel19);
				jLabel19.setText("MARCA:");
				jLabel19.setBounds(12, 302, 109, 27);
				jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel19.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				lblCodArticulo = new JLabel();
				contenedor.add(lblCodArticulo);
				lblCodArticulo.setBounds(118, 246, 130, 32);
				lblCodArticulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
				lblCodArticulo.setForeground(new java.awt.Color(0, 64, 128));
				lblCodArticulo.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				lblCodArticulo.setBackground(new java.awt.Color(255, 255, 255));
				lblCodArticulo.setOpaque(true);
				lblCodArticulo.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				btnMas = new JButton(new ImageIcon("img/mas.png"));
				contenedor.add(btnMas);
				btnMas.setBounds(12, 618, 57, 43);
				btnMas.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				btnMas.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (validador(contenedor)) {
							agregarDetalle();
							sumatoriaConInteres();
							blanquearCajar();
						}
					}
				});
			}
			{
				btnMenos = new JButton(new ImageIcon("img/menos.png"));
				contenedor.add(btnMenos);
				btnMenos.setBounds(81, 618, 57, 43);
				btnMenos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				btnMenos.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						retirarArticulo();
						removerDetalle();
						listarDetalle();
					}
				});
			}
			{
				btnLimpiar = new JButton(new ImageIcon("img/limpiar.png"));
				contenedor.add(btnLimpiar);
				btnLimpiar.setBounds(155, 618, 57, 43);
				btnLimpiar.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				btnLimpiar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						limpiarDetalle();
						limpiarArticulos();
						/* limpiarCajas(); */
					}
				});
			}
			{
				txtMarca = new JTextField();
				contenedor.add(txtMarca);
				txtMarca.setBounds(118, 298, 131, 32);
				txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				txtMarca.setHorizontalAlignment(SwingConstants.CENTER);
				txtMarca.setForeground(new java.awt.Color(0, 64, 128));
			}
			{
				lblReferencia = new JLabel();
				contenedor.add(lblReferencia);
				lblReferencia.setBounds(290, 79, 10, 10);
				lblReferencia.setVisible(false);
			}
			{
				jLabel20 = new JLabel();
				contenedor.add(jLabel20);
				jLabel20.setText("TASACIÓN:");
				jLabel20.setBounds(580, 299, 152, 27);
				jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel20.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				txtTasacion = new JTextField();
				contenedor.add(txtTasacion);
				txtTasacion.setBounds(730, 295, 179, 32);
				txtTasacion.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtTasacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				txtTasacion.setHorizontalAlignment(SwingConstants.CENTER);
				txtTasacion.setForeground(new java.awt.Color(0, 64, 128));
				
			}
			{
				btnGrabar = new JButton(new ImageIcon("img/grabarContrato.png"));
				contenedor.add(btnGrabar);
				btnGrabar.setText(" GRABAR");
				btnGrabar.setFocusable(false);
				btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 32));
				btnGrabar.setBounds(928, 244, 324, 83);
				btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				btnGrabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (c.getDetalle_contrato().size() == 0) {
							JOptionPane
							.showMessageDialog(null,
									"El Contrato debe contener como mínimo un artículo.");
						} else {
							try {
								fillContrato();
								grabarContrato(c);
								grabarArticulo();
								grabarDetalle();
								guardarSecuencia(Integer.parseInt(txtNumeroContrato.getText()));
								cerrar();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
					}
				});
			}
			{
				btnSalir = new JButton(new ImageIcon("img/salir.png"));
				contenedor.add(btnSalir);
				btnSalir.setFocusable(false);
				btnSalir.setText(" SALIR");
				btnSalir.setBounds(928, 339, 324, 83);
				btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 32));
				btnSalir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cerrar();
					}
				});
			}
			{
				jLabel7 = new JLabel();
				contenedor.add(jLabel7);
				jLabel7.setText("ESTADO:");
				jLabel7.setBounds(349, 132, 141, 27);
				jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel7.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel8 = new JLabel();
				contenedor.add(jLabel8);
				jLabel8.setText("OBS:");
				jLabel8.setBounds(13, 352, 109, 27);
				jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel8.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel9 = new JLabel();
				contenedor.add(jLabel9);
				jLabel9.setText("CAPITAL =");
				jLabel9.setBounds(924, 449, 132, 27);
				jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel9.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel10 = new JLabel();
				contenedor.add(jLabel10);
				jLabel10.setText("INTERES  =");
				jLabel10.setBounds(926, 535, 110, 27);
				jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel10.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel11 = new JLabel();
				contenedor.add(jLabel11);
				jLabel11.setText("TOTAL =");
				jLabel11.setBounds(928, 617, 96, 27);
				jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel11.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel18 = new JLabel();
				contenedor.add(jLabel18);
				jLabel18.setText("CANTIDAD:");
				jLabel18.setBounds(270, 250, 130, 27);
				jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel18.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				txtCantidad = new JTextField();
				contenedor.add(txtCantidad);
				txtCantidad.setBounds(400, 246, 81, 32);
				txtCantidad.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtCantidad.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
						1, new java.awt.Color(0, 0, 0)));
				txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
				txtCantidad.setForeground(new java.awt.Color(0, 64, 128));
			}
			{
				txtObservaciones = new JTextField();
				contenedor.add(txtObservaciones);
				txtObservaciones.setBounds(118, 353, 508, 32);
				txtObservaciones.setBorder(BorderFactory.createMatteBorder(1,
						1, 1, 1, new java.awt.Color(0, 0, 0)));
				txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
				txtObservaciones.setForeground(new java.awt.Color(0, 64, 128));
			}
			{
				jLabel5 = new JLabel();
				contenedor.add(jLabel5);
				jLabel5.setText("¿CÓMO SE ENTERÓ DE PRESTOCASH?");
				jLabel5.setBounds(282, 629, 360, 27);
				jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel5.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				ComboBoxModel cboComoSeEnteroModel = new DefaultComboBoxModel(
						new String[] { "CLIENTE", "INTERNET", "BANNER",
								"REFERENCIA" });
				cboComoSeEntero = new JComboBox();
				contenedor.add(cboComoSeEntero);
				cboComoSeEntero.setModel(cboComoSeEnteroModel);
				cboComoSeEntero.setBounds(670, 625, 236, 32);
				cboComoSeEntero.setFont(new java.awt.Font("Segoe UI", 1, 24));
				cboComoSeEntero.setBorder(BorderFactory.createMatteBorder(1, 1,
						1, 1, new java.awt.Color(0, 0, 0)));
				cboComoSeEntero
				.setBackground(new java.awt.Color(255, 255, 255));
			}
			{
				txtEn = new JTextField();
				contenedor.add(txtEn);
				txtEn.setBounds(730, 352, 179, 32);
				txtEn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				txtEn.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtEn.setForeground(new java.awt.Color(0, 64, 128));
				txtEn.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							if (lblInteres.getText().equalsIgnoreCase("")) {
								JOptionPane
								.showMessageDialog(null,
										"Es necesario seleccionar el tipo de préstamo primero.");
							} else {
								if (validador(contenedor)) {
									try {
										agregarDetalle();
										sumatoriaConInteres();
										/* blanquearCajar(); */
									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
							}
						}
					}
				});
			}
			{
				jLabel17 = new JLabel();
				contenedor.add(jLabel17);
				jLabel17.setText("COVER:");
				jLabel17.setBounds(641, 356, 77, 27);
				jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20));
				jLabel17.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				txtNumeroContrato = new JTextField();
				contenedor.add(txtNumeroContrato);
				txtNumeroContrato.setBounds(121, 46, 200, 70);
				txtNumeroContrato.setFont(new java.awt.Font("Segoe UI",1,72));
				txtNumeroContrato.setForeground(new java.awt.Color(0,0,255));
				txtNumeroContrato.setOpaque(false);
				txtNumeroContrato.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				txtNumeroContrato.setHorizontalAlignment(SwingConstants.CENTER);
				txtNumeroContrato.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent ee){
						try {
							validarExistencia(Integer.parseInt(txtNumeroContrato.getText()));
						} catch (Exception e1) {						
							e1.printStackTrace();
						} 
					}
				});
			}
			{
				cboEstado = new JComboBox();
				contenedor.add(cboEstado);
				cboEstado.setBounds(479, 128, 236, 32);
				cboEstado.setFont(new java.awt.Font("Segoe UI", 1, 24));
				cboEstado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
			}
			{
				txtDni = new JTextField();
				contenedor.add(txtDni);
				txtDni.setBounds(170, 183, 161, 32);
				txtDni.setFont(new java.awt.Font("Segoe UI", 1, 24));
				txtDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new java.awt.Color(0, 0, 0)));
				txtDni.setForeground(new java.awt.Color(0, 64, 128));
				txtDni.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent ek) {
						if (ek.getKeyCode() == KeyEvent.VK_ENTER) {
							try {
								seleccionarCliente(txtDni.getText());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				});				
			}
			{
				jLabel21 = new JLabel();
				contenedor.add(jLabel21);
				jLabel21.setText("INICIO");
				jLabel21.setBounds(930, 60, 157, 27);
				jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 22));
				jLabel21.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel22 = new JLabel();
				contenedor.add(jLabel22);
				jLabel22.setText("VENCIMIENTO");
				jLabel22.setBounds(927, 104, 170, 32);
				jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 22));
				jLabel22.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				jLabel23 = new JLabel();
				contenedor.add(jLabel23);
				jLabel23.setText("REMATE");
				jLabel23.setBounds(928, 148, 157, 32);
				jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 22));
				jLabel23.setForeground(new java.awt.Color(0, 128, 0));
			}
			{
				dchInicio = new JDateChooser();
				contenedor.add(dchInicio);
				dchInicio.setBounds(1092, 60, 158, 32);
				dchInicio.setFont(new java.awt.Font("Segoe UI",1,22));
				dchInicio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				dchInicio.setBackground(new java.awt.Color(255,255,255));
			}
			{
				dchVencimiento = new JDateChooser();
				contenedor.add(dchVencimiento);
				dchVencimiento.setBounds(1093, 104, 158, 32);
				dchVencimiento.setFont(new java.awt.Font("Dialog", 1, 16));
				dchVencimiento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				dchVencimiento.getDateEditor().getUiComponent()
				.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							calcularFechaRemate();
						}
					}
				});
			}
			{
				dchRemate = new JDateChooser();
				contenedor.add(dchRemate);
				dchRemate.setBounds(1092, 148, 158, 32);
				dchRemate.setFont(new java.awt.Font("Dialog", 1, 16));
				dchRemate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			}
			{
				txtCapital = new JTextField();
				contenedor.add(txtCapital);
				txtCapital.setText("0.00");
				txtCapital.setBounds(1050, 438, 204, 60);
				txtCapital.setFont(new java.awt.Font("Segoe UI", 1, 60));
				txtCapital.setForeground(new java.awt.Color(128, 0, 0));
				txtCapital.setBorder(BorderFactory
						.createEmptyBorder(0, 0, 0, 0));
				txtCapital.setOpaque(false);
			}
			{
				txtInteres = new JTextField();
				contenedor.add(txtInteres);
				txtInteres.setText("0.00");
				txtInteres.setBounds(1048, 520, 204, 60);
				txtInteres.setFont(new java.awt.Font("Segoe UI", 1, 60));
				txtInteres.setForeground(new java.awt.Color(128, 0, 0));
				txtInteres.setBorder(BorderFactory
						.createEmptyBorder(0, 0, 0, 0));
				txtInteres.setOpaque(false);
			}
			{
				txtTotal = new JTextField();
				contenedor.add(txtTotal);
				txtTotal.setText("0.00");
				txtTotal.setBounds(1048, 597, 204, 60);
				txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 60));
				txtTotal.setForeground(new java.awt.Color(128, 0, 0));
				txtTotal.setOpaque(false);
				txtTotal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				jLabel24 = new JLabel();
				contenedor.add(jLabel24);
				jLabel24.setText("ANTERIOR");
				jLabel24.setBounds(928, 192, 139, 32);
				jLabel24.setFont(new java.awt.Font("Segoe UI",1,22));
				jLabel24.setForeground(new java.awt.Color(0,128,0));
			}
			{
				lblUltimoContrato = new JLabel();
				contenedor.add(lblUltimoContrato);
				lblUltimoContrato.setBounds(1092, 194, 158, 32);
				lblUltimoContrato.setBackground(new java.awt.Color(255,255,255));
				lblUltimoContrato.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				lblUltimoContrato.setFont(new java.awt.Font("Segoe UI",1,22));
				lblUltimoContrato.setOpaque(true);
			}
			
			lblP = new JTextField();
			contenedor.add(lblP);
			lblP.setBounds(18, 46, 73, 70);
			lblP.setFont(new java.awt.Font("Segoe UI",1,72));
			lblP.setText(Constantes.ELECTRO);
			lblP.setHorizontalAlignment(SwingConstants.CENTER);
			lblP.setForeground(new java.awt.Color(0,0,255));
			cargarTipoEstados();
			cargarTipoPrestamos();
			cargarCodigoArticulo();
			capturarSecuencia();
		}

		jLabel15 = new JLabel();
		contenedor.add(jLabel15);
		jLabel15.setText("-");
		jLabel15.setBounds(89, 40, 44, 70);
		jLabel15.setFont(new java.awt.Font("Segoe UI",1,72));
		jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel15.setForeground(new java.awt.Color(0,0,255));

	}

	public void grabarContrato(Contrato c) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "INSERT INTO tb_contrato_manual VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, c.getId_contrato());
			pst.setString(2, c.getDoc_cliente());
			pst.setString(3, c.getTipo_prestamo());
			pst.setString(4, c.getFecha_contrato());
			pst.setString(5, c.getFecha_vencimiento());
			pst.setString(6, c.getFecha_remate());
			pst.setDouble(7, c.getCapital());
			pst.setDouble(8, c.getInteres());
			pst.setString(9, c.getTipo_moneda());
			pst.setString(10, c.getDni_usuario());
			pst.setInt(11, c.getEstado_contrato());
			pst.setString(12, c.getObservacion());
			pst.setString(13, c.getP());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Se generó Contrato de Prestación con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void agregarDetalle() {
		try {
			Contrato.Detalle_Contrato dc = c.new Detalle_Contrato(
					Integer.parseInt(lblCodArticulo.getText()),
					Integer.parseInt(txtCantidad.getText()), txtDescripcion
							.getText().toUpperCase(), txtModelo.getText()
							.toUpperCase(), txtObservaciones.getText()
							.toUpperCase(), txtMarca.getText().toUpperCase(),
					Double.parseDouble(txtTasacion.getText()), txtEn.getText()
							.toUpperCase());
			c.agregar_detalle(dc);
			listarDetalle();
			siguienteCodigo();
			agregarArticulo();
			limpiarCajas();
			txtCantidad.requestFocus();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un monto correcto.");
			txtTasacion.setText("");
			txtTasacion.requestFocus();
		}
	}

	public int maxCharacters() {
		int n1 = 300;
		int n2 = txtObservaciones.getText().length();
		return n1 - n2;
	}

	public void removerDetalle() {
		int fila = tbContratos.getSelectedRow();
		int codigo = Integer.parseInt(tbContratos.getValueAt(fila, 0)
				.toString());
		for (Detalle_Contrato dc : c.getDetalle_contrato()) {
			if (dc.getCod_producto() == codigo) {
				c.remover_detalle(dc);
				break;
			}
		}
		redistribucion();
		sumatoriaConInteres();
		listarDetalle();
	}

	public void fillContrato() {
		simbolo.setDecimalSeparator('.');
		formatoDecimal.setDecimalFormatSymbols(simbolo);
		c.setDoc_cliente(txtDni.getText());
		c.setId_contrato(Integer.parseInt(txtNumeroContrato.getText()));
		c.setInteres(Double.parseDouble(formatoDecimal.format(Double
				.parseDouble(txtInteres.getText()))));
		c.setCapital(Double.parseDouble(formatoDecimal.format(Double
				.parseDouble(txtCapital.getText()))));
		c.setTipo_prestamo(((ComboItem) cboTipoPrestamo.getSelectedItem())
				.getValue());
		c.setTipo_moneda(cboTipoMoneda.getSelectedItem().toString());		
		c.setDni_usuario(Principal.user[2]);
		c.setEstado_contrato(((ComboItem) cboEstado.getSelectedItem())
				.getValue_int());
		c.setFecha_contrato(new SimpleDateFormat("yyyy-MM-dd").format(dchInicio
				.getDate()));
		c.setFecha_vencimiento(new SimpleDateFormat("yyyy-MM-dd")
				.format(dchVencimiento.getDate()));
		c.setFecha_remate(new SimpleDateFormat("yyyy-MM-dd").format(dchRemate
				.getDate()));
		c.setObservacion(cboComoSeEntero.getSelectedItem().toString());
		c.setP(lblP.getText());
	}

	public void redistribucion() {
		int inicio = Integer.parseInt(lblReferencia.getText());
		for (int i = inicio, j = 0; j < c.getDetalle_contrato().size(); i++, j++) {
			Detalle_Contrato dc = c.obtener_detalle(j);
			dc.setCod_producto(i);
		}
		int ultimaFila = tbContratos.getRowCount() - 1;
		int dato = Integer.parseInt(tbContratos.getValueAt(ultimaFila, 0)
				.toString());
		lblCodArticulo.setText(String.format("%07d", dato));
	}

	public void cargarCodigoArticulo() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT MAX(cod_art) FROM tb_articulo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblCodArticulo.setText(String.format("%07d", rs.getInt(1) + 1));
				lblReferencia.setText(String.format("%07d", rs.getInt(1) + 1));
			} else {
				lblCodArticulo.setText("0000001");
				lblReferencia.setText("00000001");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void siguienteCodigo() {
		int next = Integer.parseInt(lblCodArticulo.getText());
		lblCodArticulo.setText("");
		lblCodArticulo.setText(String.format("%07d", next + 1));
	}

	public void blanquearCajar() {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField)
				((JTextField) o).setBackground(Color.WHITE);
		}
	}

	public void cargarTipoPrestamos() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT cod_pre, des_pre, int_pre FROM tb_prestamo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				modeloPrestamo.addElement(new ComboItem(rs.getString(1), rs
						.getString(2), rs.getDouble(3)));
			}
			cboTipoPrestamo.setModel(modeloPrestamo);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void cargarTipoEstados() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT id_est, des_est FROM tb_estado_contrato";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				modeloEstado.addElement(new ComboItem(rs.getInt(1), rs
						.getString(2)));
			}
			cboEstado.setModel(modeloEstado);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void retirarArticulo() {
		int fila = tbContratos.getSelectedRow();
		int codigo = Integer.parseInt(tbContratos.getValueAt(fila, 0)
				.toString());
		for (Articulo a : listado_articulos) {
			if (codigo == a.getCodigo()) {
				listado_articulos.remove(a);
				break;
			}
		}
	}

	public void sumatoriaConInteres() {
		double capital = 00.00;
		double interes = Double.parseDouble(lblInteres.getText()) / 100;
		for (Detalle_Contrato dc : c.getDetalle_contrato()) {
			capital += dc.getTasacion();
		}
		/* simbolo.setGroupingSeparator(','); */
		simbolo.setDecimalSeparator('.');
		formatoDecimal.setDecimalFormatSymbols(simbolo);
		double interesCalculado = capital * interes;
		String tipo = cboTipoPrestamo.getSelectedItem().toString();
		if (interesCalculado < 10.00 && tipo.equalsIgnoreCase("ELECTRO PQ")) {
			interesCalculado = 10.00;
		}
		if (interesCalculado < 5.00 && tipo.equalsIgnoreCase("ORO")) {
			interesCalculado = 5.00;
		}
		txtCapital.setText(formatoDecimal.format(capital) + "");
		txtInteres.setText(formatoDecimal.format(interesCalculado) + "");
		txtTotal.setText(formatoDecimal.format(capital + interesCalculado) + "");
	}

	public void listarDetalle() {
		modeloContrato.setRowCount(0);
		for (int i = 0; i < c.getDetalle_contrato().size(); i++) {
			Detalle_Contrato x = c.getDetalle_contrato().get(i);
			modeloContrato.addRow(new Object[] { x.getCod_producto(),
					x.getDescripcion(), x.getMarca(), x.getModelo(),
					x.getObservaciones(),
					formatoDecimal.format(x.getTasacion()) });
			tbContratos.setRowHeight(100);
		}
		tbContratos.setModel(modeloContrato);
	}

	public void limpiarCajas() {
		txtCantidad.setText("");
		txtDescripcion.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		txtTasacion.setText("");
		txtObservaciones.setText("");
		txtEn.setText("");
	}

	public boolean validador(JPanel pnl) {
		boolean val = true;

		for (Object o : pnl.getComponents()) {
			if (o instanceof JTextField && !(o instanceof JXSearchField)) {
				if (((JTextField) o).getText().equals("")) {
					((JTextField) o).setBackground(Color.RED);
					((JTextField) o).requestFocus();
					return val = false;
				}
			}
		}
		return val;
	}

	public void grabarDetalle() throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			for (Detalle_Contrato dc : c.getDetalle_contrato()) {
				String sql = "INSERT INTO tb_detalle_contrato_manual VALUES(?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, dc.getCod_producto());
				pst.setInt(2, Integer.parseInt(txtNumeroContrato.getText()));
				pst.setInt(3, dc.getCantidad());
				pst.setDouble(4, dc.getTasacion());
				pst.executeUpdate();
			}
			JOptionPane.showMessageDialog(null,
					"Se guardó correctamente el detalle del Contrato");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void cerrar() {
		int n = JOptionPane.showConfirmDialog(null, "¿Salir?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void limpiarDetalle() {
		c.limpiar_detalle();
		modeloContrato.setRowCount(0);
		lblCodArticulo.setText(lblReferencia.getText());
		txtCapital.setText("00.00");
		txtInteres.setText("00.00");
		txtTotal.setText("00.00");
	}

	public void limpiarArticulos() {
		listado_articulos.clear();
	}

	public void bloquearCajas() {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setEnabled(false);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setEnabled(false);
			}
		}
	}

	public void agregarArticulo() {
		try {
			Articulo a = new Articulo(
					Integer.parseInt(lblCodArticulo.getText()),
					txtDescripcion.getText(), txtMarca.getText(),
					txtModelo.getText(), txtObservaciones.getText() + " "
							+ txtEn.getText(), 0.00, 1,0.00,"1970-01-01",0,0.00,lblP.getText());
			listado_articulos.add(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void grabarArticulo() throws SQLException {
		for (Articulo a : listado_articulos) {
			a.grabarArticulo();
		}
	}

	public void seleccionarCliente(String dni) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_cliente WHERE doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblNombres.setText(rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getString(4));
			} else {
				int o = JOptionPane
						.showConfirmDialog(
								null,
								"Este Cliente NO está registrado. ¿Desea proceder con el registro correspondiente?",
								"ALERTA", JOptionPane.YES_NO_OPTION);
				if (o == JOptionPane.YES_OPTION) {
					Mantenimiento_Clientes nuevo_cliente = new Mantenimiento_Clientes(null);
					Principal.dskPrincipal.add(nuevo_cliente);
					this.dispose();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

	public void calcularFechaRemate() {
		Calendar c = Calendar.getInstance();
		c.setTime(dchVencimiento.getDate());
		c.add(Calendar.DATE, 30);
		dchRemate.setDate(c.getTime());
	}
	
	public void guardarSecuencia(int codigo) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "UPDATE tb_correlativo SET ai_contrato_manual=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}		
	}
	
	public void capturarSecuencia(){
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT ai_contrato_manual FROM tb_correlativo";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lblUltimoContrato.setText(rs.getInt(1)+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{}
	}
	
	public void validarExistencia(int codigo) throws SQLException{
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT * FROM tb_contrato_manual WHERE id_con=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Este número de Contrato ya se encuentra registrado");
				txtNumeroContrato.requestFocus();
			}
		} catch (Exception e) {}
		finally{
			con.close();
		}
	}
}
