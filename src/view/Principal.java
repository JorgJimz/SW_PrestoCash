package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import common.Utiles;
import controller.ClienteController;
import controller.LibroCajaController;
import maintenance.Mantenimiento_Articulos;
import maintenance.Mantenimiento_Cambio;
import maintenance.Mantenimiento_Clientes;
import maintenance.Mantenimiento_Prestamos;
import maintenance.Mantenimiento_Usuarios;
import model.Cliente;
import model.LibroCaja;
import model.Usuario;
import report.Estadistico_Mensual_Empenos;
import report.Estadistico_Mensual_Ganancias;
import report.Reporte_Asistencia;
import report.Reporte_Comision;
import report.Reporte_Comision_Oro;
import report.Reporte_Compra_Oro;
import report.Reporte_Contratos_Auto_Vigente;
import report.Reporte_Contratos_Casa_Vigente;
import report.Reporte_Contratos_X_Tipo;
import report.Reporte_Fundicion;
import report.Reporte_Inventario;
import report.Reporte_Mensual_Contratos;
import report.Reporte_Remates;
import report.Reporte_Superintendencia;
import report.Reporte_Ventas;
import report.Reporte_Vitrina;

@SuppressWarnings({ "serial" })
public class Principal extends JFrame {

	private JMenuBar menuBarPrincipal;
	private JMenuItem mniTipoCambio;
	private JMenuItem mniContrato;
	private JMenu mnTransacciones;
	private JMenu mnReportes;
	private JMenuItem mniArticulos;
	private JMenuItem mniClientes;
	private JMenuItem mniPrestamos;
	public static JDesktopPane dskPrincipal;
	private JMenuItem mniUsuario;
	private JMenu jMenu1;
	private JMenuItem mniReporteEstadisticoGanancia;
	private JMenuItem mniCasaViva;
	private JMenuItem mniReporteAutoVigente;
	private JMenuItem mnireporteSuper;
	private JMenuItem mniEstadisticoMensualEmpenos;
	private JMenu mnEstadistico;
	private JMenuItem mniReporteCompraOro;
	private JMenuItem mniVentas;
	private JMenuItem mniReporteComisionOro;
	private JMenuItem mniReporteFundicion;
	private JMenuItem mniReporteInventario;
	private JMenuItem mniEmpeniosMensuales;
	private JMenuItem mniReporteContratoXTipo;
	private JMenuItem mniReporteSeparacion;
	private JMenuItem mniReporteComision;
	private JMenuItem mniReporteVentas;
	private JMenuItem mniReporteVitrina;
	private JMenuItem mniReporteAsistencia;
	private JMenuItem mniReporteRemates;
	private JMenu mnLibroCaja;
	private JMenuItem mniLibroCaja;
	private JMenu mnSeguimiento;
	private JMenu mnContrato;
	private JMenu mnAdministrativo;
	private JMenuItem mniActualizarContratos;
	private JMenuItem mniRenovacionContrato;
	public static Usuario LOGGED;
	public static LibroCaja LIBRO_CAJA;

	public Principal(Usuario user) {
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("USUARIO ACTUAL: " + user.getNombres() + " " + user.getPaterno());
		Principal.LOGGED = user;
		dskPrincipal = new JDesktopPane();
		getContentPane().add(dskPrincipal, BorderLayout.CENTER);
		dskPrincipal.setBounds(0, 0, 704, 377);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "<html><h2>¿Desea salir de la aplicación?</h2></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);

		jMenu1 = new JMenu();
		menuBarPrincipal.add(jMenu1);
		jMenu1.setText("MANTENIMIENTO");
		jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jMenu1.setForeground(new java.awt.Color(255, 255, 255));

		mnTransacciones = new JMenu();
		menuBarPrincipal.add(mnTransacciones);
		mnTransacciones.setText("TRANSACCIONES");
		mnTransacciones.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnTransacciones.setForeground(new java.awt.Color(255, 255, 255));

		mnContrato = new JMenu();
		mnTransacciones.add(mnContrato);
		mnContrato.setText("CONTRATOS DE PRESTACIÓN");
		mnContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnSeguimiento = new JMenu();
		mnTransacciones.add(mnSeguimiento);
		mnSeguimiento.setText("SEGUIMIENTO DE CONTRATOS");
		mnSeguimiento.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnReportes = new JMenu();
		menuBarPrincipal.add(mnReportes);
		mnReportes.setText("REPORTES");
		mnReportes.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnReportes.setForeground(new java.awt.Color(255, 255, 255));

		mnAdministrativo = new JMenu();
		menuBarPrincipal.add(mnAdministrativo);
		mnAdministrativo.setText("ADMINISTRATIVO");
		mnAdministrativo.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnAdministrativo.setForeground(new java.awt.Color(255, 255, 255));

		mniReporteRemates = new JMenuItem();
		mnReportes.add(mniReporteRemates);
		mniReporteRemates.setText("REPORTE DE REMATES MENSUAL");
		mniReporteRemates.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteAsistencia = new JMenuItem();
		mnReportes.add(mniReporteAsistencia);
		mniReporteAsistencia.setText("REPORTE DE ASISTENCIA");
		mniReporteAsistencia.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteVitrina = new JMenuItem();
		mnReportes.add(mniReporteVitrina);
		mniReporteVitrina.setText("REPORTE VITRINA");
		mniReporteVitrina.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteVentas = new JMenuItem();
		mnReportes.add(mniReporteVentas);
		mniReporteVentas.setText("REPORTE DE VENTAS MENSUAL");
		mniReporteVentas.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteVentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Ventas ventas = new Reporte_Ventas();
				dskPrincipal.add(ventas);
			}
		});

		mniReporteVitrina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Vitrina vitrina = new Reporte_Vitrina();
				dskPrincipal.add(vitrina);
				/* mostrarReporteVitrina(); */
			}
		});

		mniReporteAsistencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Asistencia reporte_asistencia = new Reporte_Asistencia();
				dskPrincipal.add(reporte_asistencia);

			}
		});
		mniReporteRemates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Remates reporte_remates = new Reporte_Remates();
				dskPrincipal.add(reporte_remates);

			}
		});

		mniReporteSeparacion = new JMenuItem();
		mniReporteSeparacion.setText("REPORTE DE SEPARACIONES");
		mniReporteSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// mostrarReporteSeparaciones();
			}
		});

		mniReporteContratoXTipo = new JMenuItem();
		mniReporteContratoXTipo.setText("REPORTE CONTRATOS X TIPO");
		mniReporteContratoXTipo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteContratoXTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Contratos_X_Tipo cxt = new Reporte_Contratos_X_Tipo();
				dskPrincipal.add(cxt);
			}
		});

		mniReporteComision = new JMenuItem();
		mniReporteComision.setText("REPORTE COMISION MENSUAL");
		mniReporteComision.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mnReportes.add(mniReporteComision);
		mnReportes.add(mniReporteSeparacion);
		mnReportes.add(mniReporteContratoXTipo);

		mniEmpeniosMensuales = new JMenuItem();
		mniEmpeniosMensuales.setText("REPORTE MENSUAL DE EMPEÑOS");
		mniEmpeniosMensuales.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniEmpeniosMensuales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Mensual_Contratos reporte_mensual = new Reporte_Mensual_Contratos();
				dskPrincipal.add(reporte_mensual);
			}
		});

		mniReporteInventario = new JMenuItem();
		mniReporteInventario.setText("REPORTE INVENTARIO");
		mniReporteInventario.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteInventario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Inventario inventario = new Reporte_Inventario();
				dskPrincipal.add(inventario);
			}
		});

		mniReporteFundicion = new JMenuItem();
		mniReporteFundicion.setText("REPORTE FUNDICIÓN DE ORO");
		mniReporteFundicion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteFundicion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Fundicion fundicion = new Reporte_Fundicion();
				dskPrincipal.add(fundicion);
			}
		});

		mniReporteComisionOro = new JMenuItem();
		mniReporteComisionOro.setText("REPORTE COMISION MENSUAL (ORO)");
		mniReporteComisionOro.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteComisionOro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reporte_Comision_Oro co = new Reporte_Comision_Oro();
				dskPrincipal.add(co);

			}
		});

		mniReporteCompraOro = new JMenuItem();
		mniReporteCompraOro.setText("REPORTE MENSUAL COMPRA DE ORO");
		mniReporteCompraOro.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteCompraOro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Compra_Oro compra = new Reporte_Compra_Oro();
				dskPrincipal.add(compra);
			}
		});
		mniEstadisticoMensualEmpenos = new JMenuItem();
		mniEstadisticoMensualEmpenos.setText("GRAFICO MENSUAL DE EMPEÑOS");
		mniEstadisticoMensualEmpenos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniEstadisticoMensualEmpenos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Estadistico_Mensual_Empenos mensual_empenos = new Estadistico_Mensual_Empenos();
				dskPrincipal.add(mensual_empenos);
			}
		});

		mnireporteSuper = new JMenuItem();
		mnReportes.add(mnireporteSuper);
		mnireporteSuper.setText("REPORTE CONTRATOS ANUAL");
		mnireporteSuper.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mnireporteSuper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Superintendencia reporte_super = new Reporte_Superintendencia();
				dskPrincipal.add(reporte_super);
			}
		});

		mnEstadistico = new JMenu();
		mnEstadistico.setText("GRAFICOS ESTADISTICOS");
		mnEstadistico.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mnEstadistico.add(mniEstadisticoMensualEmpenos);

		mniReporteEstadisticoGanancia = new JMenuItem();
		mnEstadistico.add(mniReporteEstadisticoGanancia);
		mniReporteEstadisticoGanancia.setText("GRAFICO MENSUAL DE GANANCIAS");
		mniReporteEstadisticoGanancia.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteEstadisticoGanancia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Estadistico_Mensual_Ganancias emg = new Estadistico_Mensual_Ganancias();
				dskPrincipal.add(emg);
			}
		});

		mnReportes.add(mniEmpeniosMensuales);
		mnReportes.add(mniReporteInventario);
		mnReportes.add(mniReporteFundicion);
		mnReportes.add(mniReporteComisionOro);
		mnReportes.add(mniReporteCompraOro);
		mnReportes.add(mnEstadistico);

		mniReporteAutoVigente = new JMenuItem();
		mnReportes.add(mniReporteAutoVigente);
		mniReporteAutoVigente.setText("REPORTE AUTO VIVO");
		mniReporteAutoVigente.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniReporteAutoVigente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Contratos_Auto_Vigente vivo = new Reporte_Contratos_Auto_Vigente();
				dskPrincipal.add(vivo);
			}
		});

		mniCasaViva = new JMenuItem();
		mnReportes.add(mniCasaViva);
		mniCasaViva.setText("REPORTE CASA VIVO");
		mniCasaViva.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniCasaViva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reporte_Contratos_Casa_Vigente casa = new Reporte_Contratos_Casa_Vigente();
				dskPrincipal.add(casa);
			}
		});

		mniReporteComision.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Comision comision = new Reporte_Comision();
				dskPrincipal.add(comision);
			}
		});

		mnLibroCaja = new JMenu();
		mnTransacciones.add(mnLibroCaja);
		mnLibroCaja.setText("LIBRO CAJA");
		mnLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniContrato = new JMenuItem();
		mnContrato.add(mniContrato);
		mniContrato.setText("NUEVO");
		mniContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String docCliente = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese el número de D.N.I. del Cliente para generar un Contrato de Prestación.</h2></html>",
							"");
					if (docCliente != null) {
						Cliente c = new ClienteController().BuscarCliente(docCliente);

						if (c == null) {
							mensaje("No existe ningún Cliente registrado con tal número de documento, regístrelo primero.");
							dskPrincipal.add(new Mantenimiento_Clientes(docCliente));
						} else {
							dskPrincipal.add(new Contrato_Prestacion(c));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniRenovacionContrato = new JMenuItem();
		mnContrato.add(mniRenovacionContrato);
		mniRenovacionContrato.setText("GESTIONAR");
		mniRenovacionContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniRenovacionContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String contrato = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese número del Contrato a renovar ...</h2></html>");
					dskPrincipal.add(new Gestion_Contrato(contrato));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniLibroCaja = new JMenuItem();
		mnLibroCaja.add(mniLibroCaja);
		mniLibroCaja.setText("CONSULTAR");
		mniLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniVentas = new JMenuItem();
		mnLibroCaja.add(mniVentas);
		mniVentas.setText("VENDER");
		mniVentas.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniVentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Venta_Articulos venta = new Venta_Articulos();
				dskPrincipal.add(venta);
			}
		});

		mniLibroCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dskPrincipal.add(
						new Libro_Caja(new LibroCajaController().ObtenerLibroCaja(String.valueOf(LocalDate.now()))));
			}
		});

		mniActualizarContratos = new JMenuItem();
		mnSeguimiento.add(mniActualizarContratos);
		mniActualizarContratos.setText("ACTUALIZAR");
		mniActualizarContratos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniActualizarContratos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actualizar_Contratos actualizacion = new Actualizar_Contratos();
				dskPrincipal.add(actualizacion);
			}
		});

		mniUsuario = new JMenuItem();
		jMenu1.add(mniUsuario);
		mniUsuario.setText("USUARIOS");
		mniUsuario.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniPrestamos = new JMenuItem();
		jMenu1.add(mniPrestamos);
		mniPrestamos.setText("PRÉSTAMOS");
		mniPrestamos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniPrestamos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Prestamos mp = new Mantenimiento_Prestamos();
				dskPrincipal.add(mp);
				mp.moveToFront();
			}
		});

		mniClientes = new JMenuItem();
		jMenu1.add(mniClientes);
		mniClientes.setText("CLIENTES");
		mniClientes.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Clientes mc = new Mantenimiento_Clientes("");
				dskPrincipal.add(mc);
			}
		});

		mniTipoCambio = new JMenuItem();
		jMenu1.add(mniTipoCambio);
		mniTipoCambio.setText("TIPO DE CAMBIO");
		mniTipoCambio.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniTipoCambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Cambio mtc = new Mantenimiento_Cambio();
				dskPrincipal.add(mtc);
			}
		});

		mniArticulos = new JMenuItem();
		jMenu1.add(mniArticulos);
		mniArticulos.setText("ARTÍCULOS");
		mniArticulos.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniArticulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Articulos ma = new Mantenimiento_Articulos();
				dskPrincipal.add(ma);
			}
		});

		mniUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Usuarios mu = new Mantenimiento_Usuarios();
				dskPrincipal.add(mu);
			}
		});
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("dollar.png")).getImage());

		Object obj = new LibroCajaController().AperturarCaja();
		if (obj instanceof String) {
			Utiles.Mensaje(String.valueOf(obj), JOptionPane.WARNING_MESSAGE);
			Utiles.BloquearMenu(menuBarPrincipal);
		} else {
			LIBRO_CAJA = (LibroCaja) obj;
			/*
			 * Utiles.Mensaje("Favor de actualizar los contratos.",
			 * JOptionPane.WARNING_MESSAGE); Actualizar_Contratos ac = new
			 * Actualizar_Contratos(); dskPrincipal.add(ac);
			 */
		}
	}

	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	public JFrame obtenerFrame() {
		return this;
	}

	/*
	 * public String[] validarCliente(String dniCliente) { String[] datosCliente =
	 * null; try { String sql =
	 * "SELECT doc_cli,pat_cli,mat_cli,nom_cli,dir_cli,fi1_cli,mo1_cli FROM tb_cliente WHERE doc_cli=?"
	 * ; PreparedStatement pst = con.prepareStatement(sql); pst.setString(1,
	 * dniCliente); ResultSet rs = pst.executeQuery(); if (rs.next()) { String[]
	 * array = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
	 * rs.getString(5), rs.getString(6), rs.getString(7) }; datosCliente = array; }
	 * else { datosCliente = null; } } catch (Exception e) { e.printStackTrace(); }
	 * return datosCliente; }
	 */

	/*
	 * public void validarContratoAntiguo(int contrato) { try { String sql =
	 * "SELECT * FROM tb_contrato_manual WHERE id_con=?"; PreparedStatement pst =
	 * con.prepareStatement(sql); pst.setInt(1, contrato); ResultSet rs =
	 * pst.executeQuery(); if (rs.next()) { Registrar_Pago_Manual pago_manual = new
	 * Registrar_Pago_Manual( rs.getInt("id_con"));
	 * Principal.dskPrincipal.add(pago_manual); } else { JOptionPane
	 * .showMessageDialog(null,
	 * "<html><h2>No existe tal Número de Contrato</h2></html>"); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * public void validarContratoAntiguoMora(int contrato) { try { String sql =
	 * "SELECT * FROM tb_contrato_manual WHERE id_con=?"; PreparedStatement pst =
	 * con.prepareStatement(sql); pst.setInt(1, contrato); ResultSet rs =
	 * pst.executeQuery(); if (rs.next()) { Registrar_Mora_Manual mora_manual = new
	 * Registrar_Mora_Manual( rs.getInt("id_con"));
	 * Principal.dskPrincipal.add(mora_manual); } else { JOptionPane
	 * .showMessageDialog(null,
	 * "<html><h2>No existe tal Número de Contrato</h2></html>"); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * public void mostrarReporteSeparaciones() { Connection con =
	 * MySQLConexion.getConexion(); HashMap<String, Object> parametros = new
	 * HashMap<String, Object>(); parametros.put("p", Constantes.SUCURSAL); try {
	 * JasperReport reporte = (JasperReport) JRLoader
	 * .loadObject("reporte_separacion.jasper"); JasperPrint print =
	 * JasperFillManager.fillReport(reporte, parametros, con); JasperViewer viewer =
	 * new JasperViewer(print, false); viewer.show(); viewer.toFront(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * public boolean validarCambioDia() { Connection con =
	 * MySQLConexion.getConexion(); boolean flag = false; try { String sql =
	 * "SELECT MAX(fec_cam) FROM prestocash.tb_cambio"; PreparedStatement pst =
	 * con.prepareStatement(sql); ResultSet rs = pst.executeQuery(); if (rs.next())
	 * { if (rs.getString(1).equalsIgnoreCase( new
	 * SimpleDateFormat("yyyy-MM-dd").format(new Date()))) { flag = true; } } }
	 * catch (Exception e) {
	 * 
	 * } return flag; }
	 */

}
