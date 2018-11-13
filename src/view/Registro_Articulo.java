package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.search.SearchFactory;

import common.Constantes;
import common.JIconTextField;
import common.Utiles;
import controller.ArticuloController;
import model.Articulo;
import model.DetalleContrato;
import model.EArticulo;

@SuppressWarnings("serial")
public class Registro_Articulo extends JInternalFrame {

	private JScrollPane jScrollPane1;
	private JButton btnGrabar;
	private JXTable tbArticulos;
	private JLabel txtId;
	private JIconTextField txtObs;
	private JIconTextField txtModelo;
	private JIconTextField txtMarca;
	private JIconTextField txtDescripcion;
	private JIconTextField txtPrecioInterno;
	private JIconTextField txtSerie;
	private JIconTextField txtP;
	private JIconTextField txtContratoAsociado;
	private JIconTextField txtCapital;
	private JIconTextField txtPrecioVenta;
	private JLabel lblIdSC;
	private JPanel contenedor;
	private JButton btnBuscar;

	public DefaultTableModel MtoArticuloModel = new DefaultTableModel(null,
			new String[] { "ID", "DESCRIPCIÓN", "MARCA", "MODELO", "SERIE", "OBSERVACIONES", "CONTRATO", "INICIO",
					"VENCIMIENTO", "REMATE", "CLIENTE", "CAPITAL", "P.VENTA", "P.INTERNO", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Registro_Articulo() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("MANTENIMIENTO DE ARTICULOS");
		this.setSize(520, 528);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(1369, 721));
		this.setBounds(0, 0, 1369, 721);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1368, 696);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtDescripcion = new JIconTextField();
		contenedor.add(txtDescripcion);
		txtDescripcion.setBounds(14, 12, 291, 50);
		txtDescripcion.setBorder(BorderFactory.createTitledBorder(null, "DESCRIPCIÓN",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtDescripcion.setForeground(new java.awt.Color(0, 64, 128));

		txtMarca = new JIconTextField();
		contenedor.add(txtMarca);
		txtMarca.setBounds(317, 12, 223, 50);
		txtMarca.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtMarca.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtMarca.setForeground(new java.awt.Color(0, 64, 128));
		txtMarca.setBorder(BorderFactory.createTitledBorder(null, "MARCA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtModelo = new JIconTextField();
		contenedor.add(txtModelo);
		txtModelo.setBounds(552, 12, 261, 50);
		txtModelo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtModelo.setForeground(new java.awt.Color(0, 64, 128));
		txtModelo.setBorder(BorderFactory.createTitledBorder(null, "MODELO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtObs = new JIconTextField();
		contenedor.add(txtObs);
		txtObs.setBounds(12, 78, 609, 50);
		txtObs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObs.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtObs.setForeground(new java.awt.Color(0, 64, 128));
		txtObs.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIONES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setOpaque(false);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnGrabar.setBounds(1264, 68, 70, 64);
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Utiles.Validar(contenedor)) {
						Articulo a = new Articulo();
						a.setDescripcion(txtDescripcion.getText());
						a.setMarca(txtMarca.getText());
						a.setModelo(txtModelo.getText());
						a.setSerie(txtSerie.getText());
						a.setObs(txtObs.getText());
						a.setPrecioVenta(new BigDecimal(txtPrecioVenta.getText()));
						a.setPrecioInterno(new BigDecimal(txtPrecioInterno.getText()));
						a.setContrato(txtP.getText() + "-" + txtContratoAsociado.getText());
						a.setCapitalContrato(new BigDecimal(txtCapital.getText()));
						a.setEArticulo(new EArticulo(5));
						a.setFechaCreacion(String.valueOf(LocalDate.now()));
						a.setUsuarioCreacion(Principal.LOGGED.getLogin());
						new ArticuloController().RegistrarArticulo(a);
						ListarArticulos();
						Utiles.Limpiar(contenedor);
						Utiles.Mensaje("Artículo registrado.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Utiles.Mensaje("Complete el formulario.", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					Utiles.Mensaje("Ingrese un número correcto.", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					Utiles.Mensaje("Error al registrar nuevo artículo.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBuscar = new JButton();
		contenedor.add(btnBuscar);
		btnBuscar.setIcon(new ImageIcon("img/find.png"));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 20));
		btnBuscar.setOpaque(false);
		btnBuscar.setBounds(1170, 68, 70, 64);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFactory.getInstance().showFindDialog(tbArticulos, tbArticulos.getSearchable());
			}
		});

		jScrollPane1 = new JScrollPane();
		contenedor.add(jScrollPane1);
		jScrollPane1.setBounds(12, 144, 1322, 522);
		jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

		tbArticulos = new JXTable();
		jScrollPane1.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setModel(MtoArticuloModel);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));

		lblIdSC = new JLabel();
		lblIdSC.setBackground(Color.red);
		contenedor.add(lblIdSC);
		lblIdSC.setBounds(12, 301, 43, 16);
		lblIdSC.setVisible(false);

		txtPrecioVenta = new JIconTextField();
		contenedor.add(txtPrecioVenta);
		txtPrecioVenta.setBounds(633, 78, 169, 50);
		txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPrecioVenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioVenta.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioVenta.setBorder(BorderFactory.createTitledBorder(null, "PRECIO VENTA",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtCapital = new JIconTextField();
		contenedor.add(txtCapital);
		txtCapital.setBounds(995, 78, 169, 50);
		txtCapital.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtCapital.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtCapital.setForeground(new java.awt.Color(0, 64, 128));
		txtCapital.setBorder(BorderFactory.createTitledBorder(null, "CAPITAL", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtContratoAsociado = new JIconTextField();
		contenedor.add(txtContratoAsociado);
		txtContratoAsociado.setBounds(1170, 12, 164, 50);
		txtContratoAsociado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtContratoAsociado.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtContratoAsociado.setForeground(new java.awt.Color(0, 64, 128));
		txtContratoAsociado.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtP = new JIconTextField();
		contenedor.add(txtP);
		txtP.setBounds(1100, 12, 64, 50);
		txtP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtP.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtP.setForeground(new java.awt.Color(0, 64, 128));
		txtP.setBorder(BorderFactory.createTitledBorder(null, "LETRA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtSerie = new JIconTextField();
		contenedor.add(txtSerie);
		txtSerie.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtSerie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtSerie.setForeground(new java.awt.Color(0, 64, 128));
		txtSerie.setBounds(827, 12, 261, 50);
		txtSerie.setBorder(BorderFactory.createTitledBorder(null, "SERIE", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 12),
				new java.awt.Color(0, 128, 0)));

		txtPrecioInterno = new JIconTextField();
		contenedor.add(txtPrecioInterno);
		txtPrecioInterno.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPrecioInterno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtPrecioInterno.setForeground(new java.awt.Color(0, 64, 128));
		txtPrecioInterno.setBounds(814, 78, 169, 50);
		txtPrecioInterno.setBorder(BorderFactory.createTitledBorder(null, "PRECIO INTERNO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtId = new JLabel();
		contenedor.add(txtId);
		txtId.setVisible(false);
		txtId.setBounds(12, 5, 10, 10);

		ListarArticulos();
	}

	public void ListarArticulos() {
		MtoArticuloModel.setRowCount(0);
		List<Articulo> l = new ArticuloController().ListarArticulos();
		for (Articulo a : l) {
			if (a.getDetalleContratos().size() > 0) {
				DetalleContrato dc = Collections.max(a.getDetalleContratos(), Constantes.UltimoContratoComparator);
				a.setFechaContrato(dc.getContrato().getFechaContrato());
				a.setFechaVencimiento(dc.getContrato().getFechaVencimiento());
				a.setFechaRemate(dc.getContrato().getFechaRemate());
				a.setDocumentoCliente(dc.getContrato().getCliente().getDocumento());
			} else {
				a.setFechaContrato("-");
				a.setFechaVencimiento("-");
				a.setFechaRemate("-");
				a.setDocumentoCliente("-");
			}
			MtoArticuloModel.addRow(new Object[] { a.getId(), a.getDescripcion(), a.getMarca(), a.getModelo(),
					a.getSerie(), a.getObs(), a.getContrato(), a.getFechaContrato(), a.getFechaVencimiento(),
					a.getFechaRemate(), a.getDocumentoCliente(), a.getCapitalContrato(), a.getPrecioVenta(),
					a.getPrecioInterno(), a.getEArticulo().getDescripcion() });
		}
		tbArticulos.setModel(MtoArticuloModel);
	}

}
