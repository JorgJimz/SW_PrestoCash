package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import common.Utiles;
import controller.LibroCajaController;
import model.Aplicacion;
import model.LibroCaja;
import model.PerfilAplicacion;
import model.Sede;
import model.Usuario;

@SuppressWarnings({ "serial" })
public class Principal extends JFrame {

	private JMenuBar menuBarPrincipal;

	public static JDesktopPane dskPrincipal;

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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "<html><h3>�Desea salir de la aplicaci�n?</h3></html>",
						"Confirmaci�n", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);

		Map<Integer, List<PerfilAplicacion>> apps = user.getPerfil().getPerfilAplicacions().stream()
				.collect(Collectors.groupingBy(e -> e.getAplicacion().getOrden()));
		List<JComponent> c = new ArrayList<JComponent>();
		for (int i = 1; i <= apps.size(); i++) {
			for (int j = 0; j < apps.get(i).size(); j++) {
				String padre = apps.get(i).get(j).getAplicacion().getPadre();
				Component o = c.stream().filter(k -> k.getName().equalsIgnoreCase(padre)).findAny().orElse(null);
				if (Objects.nonNull(o)) {
					String app = apps.get(i).get(j).getAplicacion().getDescripcion();
					if (user.getPerfil().getPerfilAplicacions().stream()
							.anyMatch(p -> p.getAplicacion().getPadre().equals(app))) {
						JMenu mn = new JMenu(apps.get(i).get(j).getAplicacion().getDescripcion());
						mn.setName(apps.get(i).get(j).getAplicacion().getDescripcion());
						mn.setFont(new Font("Segoe UI", 1, 16));
						((JMenu) o).add(mn);
						c.add(mn);
					} else {
						Aplicacion x = apps.get(i).get(j).getAplicacion();
						JMenuItem mn = new JMenuItem(x.getDescripcion());
						mn.setName(x.getDescripcion());
						mn.setFont(new Font("Segoe UI", 1, 16));
						mn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								try {
									JInternalFrame iFrame = (JInternalFrame) Class.forName(x.getUrl()).newInstance();
									Principal.dskPrincipal.add(iFrame);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						});
						((JMenu) o).add(mn);
						c.add(mn);
					}
				} else {
					JMenu mn = new JMenu(apps.get(i).get(j).getAplicacion().getDescripcion());
					mn.setName(apps.get(i).get(j).getAplicacion().getDescripcion());
					mn.setFont(new Font("Segoe UI", 1, 16));
					menuBarPrincipal.add(mn);
					c.add(mn);
				}
			}
		}

		/*
		 * mniContrato = new JMenuItem(); mnContrato.add(mniContrato);
		 * mniContrato.setText("NUEVO"); mniContrato.setFont(new
		 * java.awt.Font("Segoe UI", 1, 16)); mniContrato.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { try { String
		 * docCliente = JOptionPane.showInputDialog(null,
		 * "<html><h2>Ingrese el n�mero de D.N.I. del Cliente para generar un Contrato de Prestaci�n.</h2></html>"
		 * , ""); if (Objects.nonNull(docCliente)) { Cliente c = new
		 * ClienteController().BuscarCliente(docCliente);
		 * 
		 * if (Objects.isNull(c)) { Utiles.Mensaje(
		 * "No existe ning�n Cliente registrado con tal n�mero de documento, reg�strelo primero."
		 * , JOptionPane.WARNING_MESSAGE); dskPrincipal.add(new
		 * Mantenimiento_Clientes(docCliente)); } else { dskPrincipal.add(new
		 * Contrato_Prestacion(c)); } } } catch (Exception e) { e.printStackTrace(); } }
		 * });
		 */

		/*
		 * mniRenovacionContrato.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { try { String c =
		 * JOptionPane.showInputDialog(null,
		 * "<html><h2>Ingrese n�mero del Contrato a renovar ...</h2></html>"); if
		 * (Objects.nonNull(c)) { String flag = String.valueOf(c.split("-")[0]); int
		 * numero = Integer.parseInt(String.valueOf(c.split("-")[1])); Contrato contrato
		 * = new ContratoController().CargarContrato(flag.toUpperCase(), numero); if
		 * (Objects.nonNull(contrato)) { dskPrincipal.add(new
		 * Gestion_Contrato(contrato)); } else {
		 * Utiles.Mensaje("Contrato no existe. Verifique.",
		 * JOptionPane.WARNING_MESSAGE); } } } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */

		/*
		 * mniPrestamos.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * Mantenimiento_Prestamos mp = new Mantenimiento_Prestamos();
		 * dskPrincipal.add(mp); mp.moveToFront(); } });
		 */

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
