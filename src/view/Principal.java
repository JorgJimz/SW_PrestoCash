package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import common.Utiles;
import controller.ClienteController;
import controller.ContratoController;
import controller.LibroCajaController;
import maintenance.Mantenimiento_Articulos;
import maintenance.Mantenimiento_Clientes;
import maintenance.Mantenimiento_Prestamos;
import maintenance.Mantenimiento_Usuarios;
import model.Cliente;
import model.Contrato;
import model.LibroCaja;
import model.Sede;
import model.Usuario;

@SuppressWarnings({ "serial" })
public class Principal extends JFrame {

	private JMenuBar menuBarPrincipal;
	private JMenuItem mniContrato;
	private JMenu mnTransacciones;
	private JMenu mnReportes;
	private JMenuItem mniArticulos;
	private JMenuItem mniClientes;
	private JMenuItem mniPrestamos;
	public static JDesktopPane dskPrincipal;
	private JMenuItem mniUsuario;
	private JMenu mnMantenimiento;
	private JMenuItem mniReporteEstadisticoGanancia;
	private JMenuItem mniCasaViva;
	private JMenuItem mniReporteAutoVigente;
	private JMenuItem mnireporteSuper;
	private JMenuItem mniEstadisticoMensualEmpenos;
	private JMenu mnEstadistico;
	private JMenuItem mniReporteCompraOro;
	private JMenuItem mniBuscar;
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
	private JMenuItem mniActualizarContratos;
	private JMenuItem mniRenovacionContrato;
	public static Usuario LOGGED;
	public static LibroCaja LIBRO_CAJA;
	public static Sede SEDE;

	public Principal(Usuario user) {
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("USUARIO ACTUAL: " + user.getNombres() + " " + user.getPaterno());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("dollar.png")).getImage());
		Principal.LOGGED = user;
		Principal.SEDE = new LibroCajaController().ObtenerSedePrincipal();
		dskPrincipal = new JDesktopPane();/*
											 * { ImageIcon icon = new ImageIcon("img/bkg.png"); Image image =
											 * icon.getImage(); Image newimage = image.getScaledInstance(739, 533,
											 * Image.SCALE_SMOOTH);
											 * 
											 * @Override protected void paintComponent(Graphics g) {
											 * super.paintComponent(g); g.drawImage(newimage, 0, 0, this); } };
											 */
		getContentPane().add(dskPrincipal, BorderLayout.CENTER);
		// dskPrincipal.setBounds(0, 0, 704, 377);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "<html><h2>�Desea salir de la aplicaci�n?</h2></html>",
						"Confirmaci�n", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);

		mnMantenimiento = new JMenu();
		menuBarPrincipal.add(mnMantenimiento);
		mnMantenimiento.setText("MANTENIMIENTO");
		mnMantenimiento.setFont(new java.awt.Font("Segoe UI", 1, 18));
		mnMantenimiento.setForeground(new java.awt.Color(255, 255, 255));

		mnTransacciones = new JMenu();
		menuBarPrincipal.add(mnTransacciones);
		mnTransacciones.setText("TRANSACCIONES");
		mnTransacciones.setFont(new java.awt.Font("Segoe UI", 1, 18));
		mnTransacciones.setForeground(new java.awt.Color(255, 255, 255));

		mnContrato = new JMenu();
		mnTransacciones.add(mnContrato);
		mnContrato.setText("CONTRATOS DE PRESTACI�N");
		mnContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mnSeguimiento = new JMenu();
		mnTransacciones.add(mnSeguimiento);
		mnSeguimiento.setText("SEGUIMIENTO DE CONTRATOS");
		mnSeguimiento.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mnReportes = new JMenu();
		mnReportes.setVisible(false);
		menuBarPrincipal.add(mnReportes);
		mnReportes.setText("REPORTES");
		mnReportes.setFont(new java.awt.Font("Segoe UI", 1, 22));
		mnReportes.setForeground(new java.awt.Color(255, 255, 255));

		mniReporteRemates = new JMenuItem();
		mnReportes.add(mniReporteRemates);
		mniReporteRemates.setText("REPORTE DE REMATES MENSUAL");
		mniReporteRemates.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteAsistencia = new JMenuItem();
		mnReportes.add(mniReporteAsistencia);
		mniReporteAsistencia.setText("REPORTE DE ASISTENCIA");
		mniReporteAsistencia.setFont(new java.awt.Font("Segoe UI", 1, 18));

		mniReporteVitrina = new JMenuItem();
		mnReportes.add(mniReporteVitrina);
		mniReporteVitrina.setText("REPORTE VITRINA");
		mniReporteVitrina.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteVentas = new JMenuItem();
		mnReportes.add(mniReporteVentas);
		mniReporteVentas.setText("REPORTE DE VENTAS MENSUAL");
		mniReporteVentas.setFont(new java.awt.Font("Segoe UI", 1, 20));

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

		mniReporteComision = new JMenuItem();
		mniReporteComision.setText("REPORTE COMISION MENSUAL");
		mniReporteComision.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mnReportes.add(mniReporteComision);
		mnReportes.add(mniReporteSeparacion);
		mnReportes.add(mniReporteContratoXTipo);

		mniEmpeniosMensuales = new JMenuItem();
		mniEmpeniosMensuales.setText("REPORTE MENSUAL DE EMPE�OS");
		mniEmpeniosMensuales.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteInventario = new JMenuItem();
		mniReporteInventario.setText("REPORTE INVENTARIO");
		mniReporteInventario.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteFundicion = new JMenuItem();
		mniReporteFundicion.setText("REPORTE FUNDICI�N DE ORO");
		mniReporteFundicion.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteComisionOro = new JMenuItem();
		mniReporteComisionOro.setText("REPORTE COMISION MENSUAL (ORO)");
		mniReporteComisionOro.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniReporteCompraOro = new JMenuItem();
		mniReporteCompraOro.setText("REPORTE MENSUAL COMPRA DE ORO");
		mniReporteCompraOro.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mniEstadisticoMensualEmpenos = new JMenuItem();
		mniEstadisticoMensualEmpenos.setText("GRAFICO MENSUAL DE EMPE�OS");
		mniEstadisticoMensualEmpenos.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnireporteSuper = new JMenuItem();
		mnReportes.add(mnireporteSuper);
		mnireporteSuper.setText("REPORTE CONTRATOS ANUAL");
		mnireporteSuper.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnEstadistico = new JMenu();
		mnEstadistico.setText("GRAFICOS ESTADISTICOS");
		mnEstadistico.setFont(new java.awt.Font("Segoe UI", 1, 20));
		mnEstadistico.add(mniEstadisticoMensualEmpenos);

		mniReporteEstadisticoGanancia = new JMenuItem();
		mnEstadistico.add(mniReporteEstadisticoGanancia);
		mniReporteEstadisticoGanancia.setText("GRAFICO MENSUAL DE GANANCIAS");
		mniReporteEstadisticoGanancia.setFont(new java.awt.Font("Segoe UI", 1, 20));

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

		mniCasaViva = new JMenuItem();
		mnReportes.add(mniCasaViva);
		mniCasaViva.setText("REPORTE CASA VIVO");
		mniCasaViva.setFont(new java.awt.Font("Segoe UI", 1, 20));

		mnLibroCaja = new JMenu();
		mnTransacciones.add(mnLibroCaja);
		mnLibroCaja.setText("LIBRO CAJA");
		mnLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mniContrato = new JMenuItem();
		mnContrato.add(mniContrato);
		mniContrato.setText("NUEVO");
		mniContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String docCliente = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese el n�mero de D.N.I. del Cliente para generar un Contrato de Prestaci�n.</h2></html>",
							"");
					if (Objects.nonNull(docCliente)) {
						Cliente c = new ClienteController().BuscarCliente(docCliente);

						if (Objects.isNull(c)) {
							Utiles.Mensaje(
									"No existe ning�n Cliente registrado con tal n�mero de documento, reg�strelo primero.",
									JOptionPane.WARNING_MESSAGE);
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
		mniRenovacionContrato.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mniBuscar = new JMenuItem();
		mnContrato.add(mniBuscar);
		mniBuscar.setText("BUSCAR");
		mniBuscar.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Historial h = new Historial();
				dskPrincipal.add(h);
			}
		});

		mniRenovacionContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String c = JOptionPane.showInputDialog(null,
							"<html><h2>Ingrese n�mero del Contrato a renovar ...</h2></html>");
					if (Objects.nonNull(c)) {
						String flag = String.valueOf(c.split("-")[0]);
						int numero = Integer.parseInt(String.valueOf(c.split("-")[1]));
						Contrato contrato = new ContratoController().CargarContrato(flag.toUpperCase(), numero);
						if (Objects.nonNull(contrato)) {
							dskPrincipal.add(new Gestion_Contrato(contrato));
						} else {
							Utiles.Mensaje("Contrato no existe. Verifique.", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mniLibroCaja = new JMenuItem();
		mnLibroCaja.add(mniLibroCaja);
		mniLibroCaja.setText("CONSULTAR");
		mniLibroCaja.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mniVentas = new JMenuItem();
		mnLibroCaja.add(mniVentas);
		mniVentas.setText("VENDER");
		mniVentas.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniVentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Venta_Articulos venta = new Venta_Articulos("");
				dskPrincipal.add(venta);
			}
		});

		mniLibroCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dskPrincipal.add(new Libro_Caja(new LibroCajaController().ObtenerLibroCaja(LocalDate.now())));
			}
		});

		mniActualizarContratos = new JMenuItem();
		mnSeguimiento.add(mniActualizarContratos);
		mniActualizarContratos.setText("ACTUALIZAR");
		mniActualizarContratos.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniActualizarContratos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actualizar_Contratos actualizacion = new Actualizar_Contratos();
				dskPrincipal.add(actualizacion);
			}
		});

		mniUsuario = new JMenuItem();
		mnMantenimiento.add(mniUsuario);
		mniUsuario.setText("USUARIOS");
		mniUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16));

		mniPrestamos = new JMenuItem();
		mnMantenimiento.add(mniPrestamos);
		mniPrestamos.setText("PR�STAMOS");
		mniPrestamos.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniPrestamos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Prestamos mp = new Mantenimiento_Prestamos();
				dskPrincipal.add(mp);
				mp.moveToFront();
			}
		});

		mniClientes = new JMenuItem();
		mnMantenimiento.add(mniClientes);
		mniClientes.setText("CLIENTES");
		mniClientes.setFont(new java.awt.Font("Segoe UI", 1, 16));
		mniClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mantenimiento_Clientes mc = new Mantenimiento_Clientes("");
				dskPrincipal.add(mc);
			}
		});

		mniArticulos = new JMenuItem();
		mnMantenimiento.add(mniArticulos);
		mniArticulos.setText("ART�CULOS");
		mniArticulos.setFont(new java.awt.Font("Segoe UI", 1, 16));
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

}
