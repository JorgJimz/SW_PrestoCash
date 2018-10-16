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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import common.ComboItem;
import common.Constantes;
import common.EditorDS;
import controller.ContratoController;
import model.Articulo;
import model.Cargo;
import model.Contrato;
import model.DetalleCargo;
import model.DetalleContrato;
import model.Sede;

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
@SuppressWarnings("serial")
public class Multi_Cargo extends JInternalFrame {
	private JPanel contenedor;
	private JTextField txtNumeroContrato;
	private JButton btnBuscar;
	private JScrollPane spArticulos;
	private JTable tbArticulos;
	private JScrollPane spCargo;
	private JTextField txtObservaciones;
	private JTextField txtTransportado;
	private JButton btnGrabar;
	private JTable tbCargo;
	private JSeparator jSeparator1;
	Cargo cargo;
	List<DetalleCargo> detalle = new ArrayList<DetalleCargo>();

	public Multi_Cargo() {

		cargo = new Cargo();

		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1177, 700);
		this.setTitle("GENERAR CARGO");
		this.setClosable(true);
		this.setPreferredSize(new java.awt.Dimension(1177, 714));
		this.setBounds(0, 0, 1177, 714);

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1161, 689);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));

		txtNumeroContrato = new JTextField();
		contenedor.add(txtNumeroContrato);
		txtNumeroContrato.setBounds(20, 12, 340, 50);
		txtNumeroContrato.setBorder(BorderFactory.createTitledBorder(null, "NÚMERO DE CONTRATO",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));
		txtNumeroContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtNumeroContrato.setForeground(new java.awt.Color(0, 64, 128));
		txtNumeroContrato.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ObtenerDetalleContrato(txtNumeroContrato.getText());
				}
			}
		});

		spArticulos = new JScrollPane();
		contenedor.add(spArticulos);
		spArticulos.setBounds(20, 86, 1107, 224);
		spArticulos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		tbArticulos = new JTable();
		tbArticulos.setModel(Constantes.ArticulosMultiCargoModel);
		spArticulos.setViewportView(tbArticulos);
		tbArticulos.setRowHeight(25);
		tbArticulos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbArticulos.getTableHeader().setForeground(new Color(181, 0, 0));
		tbArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbArticulos.getSelectedRow();
					int contratoId = Integer
							.parseInt(Constantes.ArticulosMultiCargoModel.getValueAt(fila, 0).toString());
					String flagNumero = Constantes.ArticulosMultiCargoModel.getValueAt(fila, 1).toString();
					int articuloId = Integer
							.parseInt(Constantes.ArticulosMultiCargoModel.getValueAt(fila, 2).toString());
					String descripcion = Constantes.ArticulosMultiCargoModel.getValueAt(fila, 3).toString();
					Contrato cAsociado = new Contrato();
					cAsociado.setId(contratoId);
					cAsociado.setFlag(flagNumero.split("-")[0]);
					cAsociado.setNumero(Integer.parseInt(flagNumero.split("-")[1]));
					Articulo aa = new Articulo(articuloId);
					aa.setDescripcion(descripcion);
					DetalleCargo dcc = new DetalleCargo();
					dcc.setId(new Random().nextInt(100));
					dcc.setContrato(cAsociado);
					dcc.setArticulo(aa);
					Sede ss = new Sede();
					ss.setDescripcion("SELECCIONAR DESTINO");
					dcc.setSede(ss);
					detalle.add(dcc);
					ListarCargo();
				}
			}
		});

		jSeparator1 = new JSeparator();
		contenedor.add(jSeparator1);
		jSeparator1.setBounds(20, 316, 707, 10);

		spCargo = new JScrollPane();
		contenedor.add(spCargo);
		spCargo.setBounds(20, 332, 1108, 330);
		spCargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

		tbCargo = new JTable();
		spCargo.setViewportView(tbCargo);
		tbCargo.setModel(Constantes.MultiCargoModel);
		tbCargo.setDefaultEditor(Object.class, new EditorDS());
		tbCargo.setRowHeight(25);
		tbCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbCargo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbCargo.getTableHeader().setForeground(new Color(181, 0, 0));
		tbCargo.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int fila = tbCargo.getSelectedRow();
					int SelectedId = Integer.parseInt(Constantes.MultiCargoModel.getValueAt(fila, 0).toString());
					for (DetalleCargo dc : detalle) {
						if (dc.getId() == SelectedId) {
							Sede s = new Sede(((ComboItem) Constantes.MultiCargoModel.getValueAt(fila, 3)).getId());
							s.setDescripcion(
									((ComboItem) Constantes.MultiCargoModel.getValueAt(fila, 3)).getDescripcion());
							dc.setSede(s);
						}
					}
				}
			}
		});
		tbCargo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					int opc = JOptionPane.showConfirmDialog(null,
							"<html><h3>¿Está seguro de retirar el artículo seleccionado del Cargo?</h3></html>",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opc == JOptionPane.YES_OPTION) {
						int fila = tbCargo.getSelectedRow();
						int SelectedId = Integer.parseInt(Constantes.MultiCargoModel.getValueAt(fila, 0).toString());
						for (DetalleCargo dc : detalle) {
							if (dc.getId() == SelectedId) {
								detalle.remove(dc);
								break;
							}
						}
						ListarCargo();
					}
				}
			}
		});

		btnGrabar = new JButton();
		contenedor.add(btnGrabar);
		btnGrabar.setText("PROCESAR");
		btnGrabar.setBounds(800, 775, 327, 86);
		btnGrabar.setFont(new java.awt.Font("Segoe UI", 1, 22));
		btnGrabar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		btnGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*c.setCodigo_cargo(Integer.parseInt(lblNumeroCargo.getText()));
				c.setFecha_cargo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				c.setTotal_piezas(modeloCargo.getRowCount());
				c.setTransportado_por(txtTransportado.getText().toUpperCase());
				c.setObservacion(txtObservaciones.getText().toUpperCase());
				grabarCargo(c);
				grabarDetalleCargo();
				imprimirCargo();*/
			}
		});

		txtTransportado = new JTextField();
		contenedor.add(txtTransportado);
		txtTransportado.setBounds(264, 775, 522, 35);
		txtTransportado.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtTransportado.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtTransportado.setBorder(BorderFactory.createTitledBorder(null, "TRANSPORTADO POR",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		txtObservaciones = new JTextField();
		contenedor.add(txtObservaciones);
		txtObservaciones.setBounds(265, 822, 523, 35);
		txtObservaciones.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
		txtObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 22));
		txtObservaciones.setBorder(BorderFactory.createTitledBorder(null, "OBSERVACIONES",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", Font.BOLD, 12), new java.awt.Color(0, 128, 0)));

		btnBuscar = new JButton(new ImageIcon("img/buscar_historial.png"));
		contenedor.add(btnBuscar);
		btnBuscar.setBounds(372, 11, 64, 64);
		btnBuscar.setOpaque(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObtenerDetalleContrato(txtNumeroContrato.getText());
			}
		});

	}

	public void ListarCargo() {
		Constantes.MultiCargoModel.setRowCount(0);
		for (DetalleCargo dc : detalle) {
			Constantes.MultiCargoModel.addRow(new Object[] { dc.getId(), dc.getContrato().getId(),
					dc.getArticulo().getDescripcion(), dc.getSede().getDescripcion() });
		}
		tbCargo.setModel(Constantes.MultiCargoModel);
	}

	public void ObtenerDetalleContrato(String contrato) {
		Contrato cn = new ContratoController().CargarContrato(contrato.split("-")[0],
				Integer.parseInt(contrato.split("-")[1]));
		Constantes.ArticulosMultiCargoModel.setRowCount(0);
		for (DetalleContrato dc : cn.getDetalleContratos()) {
			Constantes.ArticulosMultiCargoModel
					.addRow(new Object[] {
							dc.getContrato().getId(), dc.getContrato().getFlag() + "-" + dc.getContrato().getNumero(),
							dc.getArticulo().getId(), dc.getArticulo().getDescripcion() + " "
									+ dc.getArticulo().getMarca() + " " + dc.getArticulo().getModelo(),
							dc.getArticulo().getEArticulo().getDescripcion() });
		}
		tbArticulos.setModel(Constantes.ArticulosMultiCargoModel);
	}

	/*
	 * public void grabarCargo(Cargo c) { Connection con =
	 * MySQLConexion.getConexion(); try { String sql =
	 * "INSERT INTO tb_cargo VALUES(?,?,?,?,?)"; PreparedStatement pst =
	 * con.prepareStatement(sql); pst.setInt(1, c.getCodigo_cargo());
	 * pst.setString(2, c.getFecha_cargo()); pst.setInt(3, c.getTotal_piezas());
	 * pst.setString(4, c.getTransportado_por()); pst.setString(5,
	 * c.getObservacion()); pst.executeUpdate(); JOptionPane.showMessageDialog(null,
	 * "SE GENERÓ CARGO"); } catch (Exception e) { e.printStackTrace(); } finally {
	 * try { con.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public void grabarDetalleCargo() { Connection con =
	 * MySQLConexion.getConexion(); try { for (Detalle_Cargo dc :
	 * c.getDetalle_cargo()) { String sql =
	 * "INSERT INTO tb_detalle_cargo VALUES(?,?,?,?)"; PreparedStatement pst =
	 * con.prepareStatement(sql); pst.setInt(1, dc.getCodigo_cargo()); pst.setInt(2,
	 * dc.getCodigo_articulo()); pst.setInt(3, dc.getCodigo_contrato());
	 * pst.setString(4, dc.getDestino()); pst.executeUpdate(); }
	 * JOptionPane.showMessageDialog(null, "SE GENERÓ DETALLE"); } catch (Exception
	 * e) { e.printStackTrace(); } finally { try { con.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } }
	 */

	/*
	 * public void imprimirCargo() { ArrayList<Cargo> arreglo_cargo = new
	 * ArrayList<Cargo>(); arreglo_cargo.add(c); HashMap<String, Object> params =
	 * new HashMap<String, Object>(); params.put("p", Constantes.NOMBRE_SUCURSAL);
	 * try { JasperReport reporte = (JasperReport)
	 * JRLoader.loadObject("cargo.jasper"); JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(reporte, params, new
	 * JRBeanCollectionDataSource(arreglo_cargo));
	 * JasperPrintManager.printReport(jasperPrint, true); } catch (Exception e) {
	 * Auditoria a = new Auditoria( "Error de tipo: " + e.getClass() +
	 * " en Contrato_Prestacion - imprimirContrato()", " Motivo: " + e.getCause() +
	 * " Detalle: " + e.getMessage(), new
	 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	 * a.registrarAuditoria(); } }
	 * 
	 * public void cerrar() { this.dispose(); }
	 */
}
