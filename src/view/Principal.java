package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
@SuppressWarnings({ "serial" })
public class Principal extends JFrame {

	private JMenuBar jMenuBar1;
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
	private JMenuItem mniBitacora;
	private JMenuItem mniAnularSeparacion;
	private JMenuItem mniReporteCompraOro;
	private JMenuItem mniReporteComisionOro;
	private JMenuItem mniCompraOro;
	private JMenuItem mniReporteFundicion;
	private JMenuItem mniReporteInventario;
	private JMenuItem mniEmpeniosMensuales;
	private JMenuItem mniReporteContratoXTipo;
	private JMenuItem mniReporteSeparacion;
	private JMenuItem mniReporteComision;
	private JMenuItem mniReporteVentas;
	private JMenuItem mniConsultarCargo;
	private JMenuItem mniHistorialCliente;
	private JMenuItem mniReporteVitrina;
	private JMenuItem mniReporteAsistencia;
	private JMenuItem mniReporteRemates;
	private JMenuItem mniCuotaSeparacion;
	private JMenuItem mniBackup;
	private JMenuItem mniHistorialCajas;
	private JMenuItem mniRemateContrato;
	private JMenuItem mniCrearCargo;
	private JMenu mnCargo;
	private JMenuItem mniRecuperarArticulo;
	private JMenuItem mniRegistrarIngreso;
	private JMenuItem mniFinalizarSeparacion;
	private JMenu mnEgresos;
	private JMenu mnIngresos;
	private JMenuItem mniSepararArticulo;
	private JMenuItem mniVentaArticulo;
	private JMenuItem mniEgreso;
	private JMenu mnLibroCaja;
	private JMenuItem mniLibroCaja;
	private JMenu mnSeguimiento;
	private JMenu mnContrato;
	private JMenu mnAdministrativo;
	private JMenuItem mniActualizarContratos;
	private JMenuItem mniRenovacionContrato;
	public static Usuario LOGGED;
	public static String[] data = new String[] { "", "" };
	public static LibroCaja LIBRO_CAJA;

	public Principal(Usuario user) {
		LIBRO_CAJA = new LibroCajaController().AperturarCaja();
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

		jMenuBar1 = new JMenuBar();
		setJMenuBar(jMenuBar1);

		jMenu1 = new JMenu();
		jMenuBar1.add(jMenu1);
		jMenu1.setText("MANTENIMIENTO");
		jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 22));
		jMenu1.setForeground(new java.awt.Color(255, 255, 255));

		mnTransacciones = new JMenu();
		jMenuBar1.add(mnTransacciones);
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
		jMenuBar1.add(mnReportes);
		mnReportes.setText("REPORTES");
		mnReportes.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnReportes.setForeground(new java.awt.Color(255, 255, 255));

		mnAdministrativo = new JMenu();
		jMenuBar1.add(mnAdministrativo);
		mnAdministrativo.setText("ADMINISTRATIVO");
		mnAdministrativo.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnAdministrativo.setForeground(new java.awt.Color(255, 255, 255));

		mniHistorialCliente = new JMenuItem();
		mnReportes.add(mniHistorialCliente);
		mniHistorialCliente.setText("HISTORIAL DEL CLIENTE");
		mniHistorialCliente.setFont(new java.awt.Font("Segoe UI", 1, 20));

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

		mniHistorialCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String dni = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese APELLIDOS o D.N.I. del Cliente a buscar ...</h2></html>");

					Historial_Cliente historial = new Historial_Cliente(dni);
					dskPrincipal.add(historial);
				} catch (SQLException e) {
					e.printStackTrace();
				}

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

		mnIngresos = new JMenu();
		mnTransacciones.add(mnIngresos);
		mnIngresos.setText("INGRESOS");
		mnIngresos.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnEgresos = new JMenu();
		mnTransacciones.add(mnEgresos);
		mnEgresos.setText("EGRESOS");
		mnEgresos.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnLibroCaja = new JMenu();
		mnTransacciones.add(mnLibroCaja);
		mnLibroCaja.setText("LIBRO CAJA");
		mnLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniContrato = new JMenuItem();
		mnContrato.add(mniContrato);
		mniContrato.setText("NUEVO CONTRATO");
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
							Mantenimiento_Clientes mc = new Mantenimiento_Clientes(docCliente);
							dskPrincipal.add(mc);
						} else {
							Contrato_Prestacion cp = new Contrato_Prestacion(c);
							dskPrincipal.add(cp);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniRenovacionContrato = new JMenuItem();
		mnContrato.add(mniRenovacionContrato);
		mniRenovacionContrato.setText("GESTIONAR CONTRATO");
		mniRenovacionContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniRenovacionContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String contrato = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese número del Contrato a renovar ...</h2></html>");
					Gestion_Contrato renovacion_contrato;
					renovacion_contrato = new Gestion_Contrato(contrato);
					dskPrincipal.add(renovacion_contrato);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniRemateContrato = new JMenuItem();
		mnContrato.add(mniRemateContrato);
		mniRemateContrato.setText("ENVIAR A VITRINA");
		mniRemateContrato.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniRemateContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String numero = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese número del Contrato cuyos Artículos saldrán a VITRINA ...</h2></html>");
					Remate_Contrato remate = new Remate_Contrato(numero);
					dskPrincipal.add(remate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniBackup = new JMenuItem();
		mnAdministrativo.add(mniBackup);
		mniBackup.setText("COPIA DE SEGURIDAD (BACK-UP)");
		mniBackup.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniBitacora = new JMenuItem();
		mniBitacora.setText("GENERAR BITACORA");
		mniBitacora.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniBitacora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Bitacora bitacora = new Bitacora();
				dskPrincipal.add(bitacora);
			}
		});

		mnCargo = new JMenu();
		mnAdministrativo.add(mnCargo);
		mnAdministrativo.add(mniBitacora);
		mnCargo.setText("CARGO");
		mnCargo.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniCrearCargo = new JMenuItem();
		mnCargo.add(mniCrearCargo);
		mniCrearCargo.setText("NUEVO CARGO");
		mniCrearCargo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniCrearCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Crear_Cargo nuevo_cargo = new Crear_Cargo();
				dskPrincipal.add(nuevo_cargo);
			}
		});

		mniConsultarCargo = new JMenuItem();
		mnCargo.add(mniConsultarCargo);
		mniConsultarCargo.setText("CONSULTAR CARGO");
		mniConsultarCargo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniConsultarCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Consultar_Cargo_Anterior consulta_cargo = new Consultar_Cargo_Anterior();
				dskPrincipal.add(consulta_cargo);
			}
		});

		mniBackup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Backup backup_db = new Backup();
				dskPrincipal.add(backup_db);
			}
		});

		mniLibroCaja = new JMenuItem();
		mnLibroCaja.add(mniLibroCaja);
		mniLibroCaja.setText("CONSULTAR CAJA ACTUAL");
		mniLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniHistorialCajas = new JMenuItem();
		mnLibroCaja.add(mniHistorialCajas);
		mniHistorialCajas.setText("CONSULTAR CAJA ANTERIOR");
		mniHistorialCajas.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniHistorialCajas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Consultar_Libro_Caja libro = new Consultar_Libro_Caja();
				dskPrincipal.add(libro);
			}
		});

		mniLibroCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Libro_Caja libro_caja = new Libro_Caja();
				dskPrincipal.add(libro_caja);
			}
		});

		mniCompraOro = new JMenuItem();
		mniCompraOro.setText("NUEVA COMPRA DE ORO");
		mniCompraOro.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniCompraOro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Compra_Oro cau = new Compra_Oro();
				dskPrincipal.add(cau);

			}
		});

		mniAnularSeparacion = new JMenuItem();
		mniAnularSeparacion.setText("ANULAR SEPARACION");
		mniAnularSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniAnularSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String codigo_separacion = JOptionPane.showInputDialog(null,
						"<html><h2>Ingresa el número de separación ...</h2></html>");
				Anular_Separacion anulacion = new Anular_Separacion(codigo_separacion);
				dskPrincipal.add(anulacion);
			}
		});

		mniEgreso = new JMenuItem();
		mnEgresos.add(mniEgreso);
		mnEgresos.add(mniCompraOro);
		mnEgresos.add(mniAnularSeparacion);
		mniEgreso.setText("GENERAR EGRESO MANUAL");
		mniEgreso.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniRegistrarIngreso = new JMenuItem();
		mnIngresos.add(mniRegistrarIngreso);
		mniRegistrarIngreso.setText("GENERAR INGRESO MANUAL");
		mniRegistrarIngreso.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniRegistrarIngreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Registrar_Ingreso registrar_ingreso = new Registrar_Ingreso();
				dskPrincipal.add(registrar_ingreso);
			}
		});

		mniVentaArticulo = new JMenuItem();
		mnIngresos.add(mniVentaArticulo);
		mniVentaArticulo.setText("VENDER ARTÍCULO");
		mniVentaArticulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniVentaArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Venta_Articulos venta_articulo = new Venta_Articulos(data);
				dskPrincipal.add(venta_articulo);
			}
		});

		mniSepararArticulo = new JMenuItem();
		mnIngresos.add(mniSepararArticulo);
		mniSepararArticulo.setText("SEPARAR ARTÍCULO");
		mniSepararArticulo.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniCuotaSeparacion = new JMenuItem();
		mnIngresos.add(mniCuotaSeparacion);
		mniCuotaSeparacion.setText("NUEVA CUOTA SEPARACIÓN");
		mniCuotaSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniCuotaSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String contrato = JOptionPane.showInputDialog("<html><h2>Ingrese Número de Contrato</h2></html>");
				Nueva_Cuota_Separacion ncs = new Nueva_Cuota_Separacion(Integer.parseInt(contrato));
				dskPrincipal.add(ncs);

			}
		});

		mniFinalizarSeparacion = new JMenuItem();
		mnIngresos.add(mniFinalizarSeparacion);
		mniFinalizarSeparacion.setText("FINALIZAR SEPARACIÓN");
		mniFinalizarSeparacion.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniRecuperarArticulo = new JMenuItem();
		mnIngresos.add(mniRecuperarArticulo);
		mniRecuperarArticulo.setText("RECUPERAR ARTÍCULO");
		mniRecuperarArticulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mniRecuperarArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Recuperar_Articulo recuperacion = new Recuperar_Articulo(data);
				dskPrincipal.add(recuperacion);
			}
		});

		mniFinalizarSeparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String codigo_separacion = JOptionPane.showInputDialog(null,
						"<html><h2>Ingresa el número de separación ...</h2></html>");
				Finalizar_Separacion finalizar_separacion = new Finalizar_Separacion(codigo_separacion);
				dskPrincipal.add(finalizar_separacion);

			}
		});

		mniSepararArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Separacion_Articulos separacion_articulo = new Separacion_Articulos(data);
				dskPrincipal.add(separacion_articulo);
			}
		});

		mniEgreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Registrar_Egreso egreso = new Registrar_Egreso();
				dskPrincipal.add(egreso);
			}
		});

		mniActualizarContratos = new JMenuItem();
		mnSeguimiento.add(mniActualizarContratos);
		mniActualizarContratos.setText("ACTUALIZAR CONTRATOS");
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

		/*
		 * JOptionPane .showMessageDialog(null,
		 * "<html><h2>Es necesario ACTUALIZAR los Contratos ...</h2></html>");
		 */
		/*
		 * Actualizar_Contratos ac = new Actualizar_Contratos(); dskPrincipal.add(ac);
		 */
		/*
		 * aperturarCaja(); if (validarCambioDia() == false) { Tipo_Cambio cambio = new
		 * Tipo_Cambio(); dskPrincipal.add(cambio); }
		 */

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

	public static void traerAlFrente(JInternalFrame fr) {
		fr.toFront();
	}

	public void bloquearMenu() {
		for (Object o : jMenuBar1.getComponents()) {
			if (o instanceof JMenu) {
				((JMenu) o).setEnabled(false);
			}
		}
	}

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
