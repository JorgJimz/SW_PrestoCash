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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Articulo;
import model.Contrato;
import model.DetalleContrato;
import model.EstadoArticulo;
import model.EstadoContrato;
import model.Fundicion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.lang3.RandomStringUtils;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

import common.Utiles;

import controller.ArticuloController;
import controller.ContratoController;

@SuppressWarnings({ "serial", "deprecation" })
public class Fundicion_Oro extends JInternalFrame {
	private JPanel contenedor;
	private JButton btnMenos;
	private JButton btnMas;
	private JXSearchField txtNumeroContrato;
	private JLabel lblTotalGramaje;
	private JButton btnGrabar;
	private JScrollPane spArticulos;
	private JTable tbArticulos;
	private JScrollPane spFundicion;
	private JTable tbFundicion;
	List<Fundicion> detalle = new ArrayList<Fundicion>();

	TableColumn ArticuloId_Column;

	public DefaultTableModel ArticuloFundicionModel = new DefaultTableModel(null,
			new String[] { "CONTRATO", "AID", "PRENDA", "GRAMAJE", "ESTADO" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public DefaultTableModel FundicionModel = new DefaultTableModel(null,
			new String[] { "ID", "CONTRATO", "PRENDA", "GRAMAJE" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			if (colIndex == 3) {
				return true;
			}
			return false;
		}
	};

	public Fundicion_Oro() {
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("FUNDICIÓN");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.setSize(1710, 700);
		this.setBounds(0, 0, 679, 707);
		this.setPreferredSize(new java.awt.Dimension(679, 707));
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "<html><h3><b>¿Salir?</b></h3></html>", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 677, 682);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtNumeroContrato = new JXSearchField();
		contenedor.add(txtNumeroContrato);
		txtNumeroContrato.setBounds(12, 22, 465, 60);
		txtNumeroContrato.setPrompt("Digite, luego presione ENTER o clic en la LUPA");
		txtNumeroContrato.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO DE CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 18));
		txtNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		txtNumeroContrato.setSearchMode(SearchMode.REGULAR);
		txtNumeroContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObtenerDetalleContrato(txtNumeroContrato.getText());
			}
		});

		spArticulos = new JScrollPane();
		contenedor.add(spArticulos);
		spArticulos.setBounds(12, 94, 630, 209);
		spArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spArticulos.setBackground(new java.awt.Color(255, 255, 255));
		tbArticulos = new JTable();
		tbArticulos.setModel(ArticuloFundicionModel);
		ArticuloId_Column = tbArticulos.getColumnModel().getColumn(1);
		tbArticulos.getColumnModel().removeColumn(ArticuloId_Column);
		tbArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		spArticulos.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					AgregarPrenda();
				}
			}
		});

		btnGrabar = new JButton(new ImageIcon("img/grabar.png"));
		contenedor.add(btnGrabar);
		btnGrabar.setEnabled(detalle.size() > 0);
		btnGrabar.setBorderPainted(false);
		btnGrabar.setContentAreaFilled(false);
		btnGrabar.setText("FUNDIR");
		btnGrabar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnGrabar.setOpaque(false);
		btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGrabar.setBounds(451, 584, 190, 70);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null,
						"<html><h3><b>Se procederá con la Fundición, este proceso es irreversible ... ¿Desea continuar?</b></h3></html>",
						"Confirmar Fundición", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					if (new ArticuloController().FundirOro(detalle)) {
						Utiles.Mensaje("Proceso de fundición finalizado con éxito.", JOptionPane.INFORMATION_MESSAGE);
						ImprimirReporte();
						dispose();
					}
				}
			}
		});

		spFundicion = new JScrollPane();
		contenedor.add(spFundicion);
		spFundicion.setBounds(12, 315, 630, 257);
		spFundicion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		spFundicion.setBackground(new java.awt.Color(255, 255, 255));
		tbFundicion = new JTable();
		spFundicion.setViewportView(tbFundicion);
		tbFundicion.setModel(FundicionModel);
		tbFundicion.putClientProperty("terminateEditOnFocusLost", true);
		tbFundicion.setRowHeight(25);
		tbFundicion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbFundicion.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbFundicion.getTableHeader().setForeground(new Color(181, 0, 0));
		tbFundicion.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					QuitarPrenda();
				}
			}
		});

		btnMas = new JButton(new ImageIcon("img/mas.png"));
		contenedor.add(btnMas);
		btnMas.setBorderPainted(false);
		btnMas.setContentAreaFilled(false);
		btnMas.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnMas.setOpaque(false);
		btnMas.setEnabled(false);
		btnMas.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMas.setBounds(489, 12, 70, 70);
		btnMas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarPrenda();
			}
		});

		lblTotalGramaje = new JLabel();
		contenedor.add(lblTotalGramaje);
		lblTotalGramaje.setBounds(12, 584, 266, 70);
		lblTotalGramaje.setFont(new java.awt.Font("Segoe UI", 1, 16));
		lblTotalGramaje.setForeground(Color.RED);

		btnMenos = new JButton(new ImageIcon("img/menos.png"));
		contenedor.add(btnMenos);
		btnMenos.setBorderPainted(false);
		btnMenos.setContentAreaFilled(false);
		btnMenos.setFont(new java.awt.Font("Segoe UI", 1, 14));
		btnMenos.setOpaque(false);
		btnMenos.setEnabled(false);
		btnMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMenos.setBounds(571, 12, 70, 70);
		btnMenos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuitarPrenda();
			}
		});
	}

	public void AgregarPrenda() {
		int fila = tbArticulos.getSelectedRow();
		int articuloId = Integer.parseInt(ArticuloFundicionModel.getValueAt(fila, 1).toString());
		boolean exists = detalle.stream().anyMatch(t -> t.getArticulo().getId() == articuloId);
		if (!exists) {
			Articulo aAsociado = new ArticuloController().ObtenerArticulo(articuloId);
			aAsociado.setEstadoArticulo(new EstadoArticulo(EstadoArticulo.FUNDIDO));
			Contrato cAsociado = new ContratoController().CargarContrato(aAsociado.getFlagContrato(),
					aAsociado.getNumeroContrato());
			cAsociado.setEstadoContrato(new EstadoContrato(EstadoContrato.FUNDIDO));
			cAsociado.setFechaModificacion(String.valueOf(LocalDate.now()));
			cAsociado.setUsuarioModificacion(Principal.LOGGED.getLogin());
			aAsociado.setContrato(cAsociado);
			Fundicion f = new Fundicion();
			f.setAlphaId(RandomStringUtils.randomAlphabetic(5));
			f.setFecha(String.valueOf(LocalDate.now()));
			f.setArticulo(aAsociado);
			f.setFechaCreacion(String.valueOf(LocalDate.now()));
			f.setUsuarioCreacion(Principal.LOGGED.getLogin());
			detalle.add(f);
			ListarFundiciones();
			ArticuloFundicionModel.setRowCount(0);
			txtNumeroContrato.setText("");
			txtNumeroContrato.requestFocus();
		} else {
			Utiles.Mensaje("Esta prenda ya ha sido agregada al cargo actual.", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void QuitarPrenda() {
		int opc = JOptionPane.showConfirmDialog(null,
				"<html><h3>¿Está seguro de retirar el artículo seleccionado del Cargo?</h3></html>", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (opc == JOptionPane.YES_OPTION) {
			int fila = tbFundicion.getSelectedRow();
			String SelectedId = String.valueOf(FundicionModel.getValueAt(fila, 0));
			for (Fundicion f : detalle) {
				if (f.getAlphaId().equals(SelectedId)) {
					detalle.remove(f);
					break;
				}
			}
			ListarFundiciones();
		}
	}

	public void ListarFundiciones() {
		FundicionModel.setRowCount(0);
		BigDecimal cF = BigDecimal.ZERO;
		for (Fundicion f : detalle) {
			FundicionModel.addRow(new Object[] { f.getAlphaId(),
					f.getArticulo().getFlagContrato() + "-" + f.getArticulo().getNumeroContrato(),
					f.getArticulo().getDescripcion() + " " + f.getArticulo().getMarca(), f.getArticulo().getModelo() });
			cF = cF.add(new BigDecimal(f.getArticulo().getModelo()));
		}
		tbFundicion.setModel(FundicionModel);
		btnMenos.setEnabled(detalle.size() > 0);
		btnGrabar.setEnabled(detalle.size() > 0);
		lblTotalGramaje.setText(String.format("TOTAL A FUNDIR: %s GRAMOS.", String.valueOf(cF)));
	}

	public void ObtenerDetalleContrato(String contrato) {
		if (!contrato.isEmpty()) {
			String flag = contrato.split("-")[0];
			if (Principal.SEDE.getFlagOro().equalsIgnoreCase(flag)) {
				Contrato cn = new ContratoController().CargarContrato(flag, Integer.parseInt(contrato.split("-")[1]));
				if (Objects.nonNull(cn)) {
					ArticuloFundicionModel.setRowCount(0);
					for (DetalleContrato dc : cn.getDetalleContratos()) {
						ArticuloFundicionModel.addRow(new Object[] {
								dc.getContrato().getFlag() + "-" + dc.getContrato().getNumero(),
								dc.getArticulo().getId(),
								dc.getArticulo().getDescripcion() + " " + dc.getArticulo().getMarca(),
								dc.getArticulo().getModelo(), dc.getArticulo().getEstadoArticulo().getDescripcion() });
					}
					tbArticulos.setModel(ArticuloFundicionModel);
					btnMas.setEnabled(true);
				} else {
					Utiles.Mensaje(String.format("El contrato %s no existe. Por favor, verificar.", contrato),
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				Utiles.Mensaje(String.format("Ingrese un contrato de ORO para su fundición.", contrato),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			Utiles.Mensaje("Ingrese Número de Contrato ...", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void ImprimirReporte() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SEDE", Principal.SEDE.getDescripcion());
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reports/rptFundicion.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, params,
					new JRBeanCollectionDataSource(detalle));
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.show();
			viewer.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
