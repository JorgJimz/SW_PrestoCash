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

import common.Logger;
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
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("dollar.png")).getImage());
		Principal.LOGGED = user;
		Principal.SEDE = new LibroCajaController().ObtenerSedePrincipal();
		this.setTitle("USUARIO: " + user.getNombres() + " " + user.getPaterno() + " - PRESTOCASH " + Principal.SEDE.getDescripcion());
		dskPrincipal = new JDesktopPane();
		getContentPane().add(dskPrincipal, BorderLayout.CENTER);
		menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "<html><h3>¿Desea salir de la aplicación?</h3></html>",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		CargarMenuAplicaciones();		
		AperturarCaja();
	}

	public void CargarMenuAplicaciones() {
		try {
			Map<Integer, List<PerfilAplicacion>> apps = Principal.LOGGED.getPerfil().getPerfilAplicacions().stream()
					.collect(Collectors.groupingBy(e -> e.getAplicacion().getOrden()));
			List<JComponent> c = new ArrayList<JComponent>();
			for (int i = 1; i <= apps.size(); i++) {
				for (int j = 0; j < apps.get(i).size(); j++) {
					String padre = apps.get(i).get(j).getAplicacion().getPadre();
					Component o = c.stream().filter(k -> k.getName().equalsIgnoreCase(padre)).findAny().orElse(null);
					if (Objects.nonNull(o)) {
						String app = apps.get(i).get(j).getAplicacion().getDescripcion();
						if (Principal.LOGGED.getPerfil().getPerfilAplicacions().stream()
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
										iFrame.moveToFront();
									} catch (Exception ex) {
										ex.printStackTrace();
										Utiles.Mensaje("Sin aplicación asociada.", JOptionPane.ERROR_MESSAGE);
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
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
	}	

	public void AperturarCaja() {
		Object obj = new LibroCajaController().AperturarCaja();
		if (obj instanceof String) {
			Utiles.Mensaje(String.valueOf(obj), JOptionPane.WARNING_MESSAGE);
			Utiles.BloquearMenu(menuBarPrincipal);
		} else {
			LIBRO_CAJA = (LibroCaja) obj;
		}
	}

}
