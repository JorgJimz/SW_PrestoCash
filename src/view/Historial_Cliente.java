package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Historial_Cliente extends JInternalFrame {

	private JPanel contenedor;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JLabel lblCliente;
	private JButton btnDetalleContrato;
	private JLabel lblCondicion;
	private JLabel jLabel3;
	private JLabel lblDni;
	private JTable tbContratos;
	private JLabel jLabel1;
	private String documento;
	private String numero_contrato;
	@SuppressWarnings("serial")
	DefaultTableModel modeloContrato = new DefaultTableModel(null,
			new String[] { "N° CONTRATO", "FEC.INI", "FEC.VCTO", "FEC.REM",
					"ESTADO","ARTICULO","TIPO", "VALOR" }) {
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}
	};

	public Historial_Cliente(String dni) throws SQLException {
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1223, 471);
		this.setPreferredSize(new java.awt.Dimension(1223, 859));
		this.setBounds(0, 0, 1223, 859);
		this.setClosable(true);
		documento = dni;

		contenedor = new JPanel();
		getContentPane().add(contenedor);
		contenedor.setLayout(null);
		contenedor.setBounds(0, 0, 1264, 834);
		contenedor.setBackground(new java.awt.Color(255, 200, 147));
		{
			jLabel1 = new JLabel();
			contenedor.add(jLabel1);
			jLabel1.setText("DOC.ID.:");
			jLabel1.setBounds(12, 19, 104, 33);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel1.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jLabel2 = new JLabel();
			contenedor.add(jLabel2);
			jLabel2.setText("CLIENTE:");
			jLabel2.setBounds(323, 19, 104, 33);
			jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel2.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			jScrollPane1 = new JScrollPane();
			contenedor.add(jScrollPane1);
			jScrollPane1.setBounds(12, 84, 1177, 649);
			jScrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));

			tbContratos = new JTable();
			jScrollPane1.setViewportView(tbContratos);
			tbContratos.setRowHeight(30);
			tbContratos.setDefaultRenderer(Object.class, new RenderHC());
			tbContratos.setFont(new Font("Segoe UI", Font.BOLD, 18));
			tbContratos.getTableHeader().setFont(
					new Font("Segoe UI", Font.BOLD, 20));
			tbContratos.getTableHeader().setForeground(new Color(181, 0, 0));
			tbContratos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						int fila = tbContratos.getSelectedRow();
						String cod = tbContratos.getValueAt(fila, 0).toString();
						numero_contrato = cod;
					}
				}
			});
		}
		{
			lblDni = new JLabel();
			contenedor.add(lblDni);
			lblDni.setBounds(112, 19, 189, 33);
			lblDni.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblDni.setBackground(new java.awt.Color(255, 255, 255));
			lblDni.setOpaque(true);
			lblDni.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblDni.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			lblCliente = new JLabel();
			contenedor.add(lblCliente);
			lblCliente.setBounds(427, 19, 392, 33);
			lblCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCliente.setBackground(new java.awt.Color(255, 255, 255));
			lblCliente.setOpaque(true);
			lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblCliente.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			jLabel3 = new JLabel();
			contenedor.add(jLabel3);
			jLabel3.setText("CONDICION:");
			jLabel3.setBounds(847, 17, 143, 33);
			jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22));
			jLabel3.setForeground(new java.awt.Color(0, 128, 0));
		}
		{
			lblCondicion = new JLabel();
			contenedor.add(lblCondicion);
			lblCondicion.setBounds(990, 19, 199, 33);
			lblCondicion.setOpaque(true);
			lblCondicion.setBackground(new java.awt.Color(255, 255, 255));
			lblCondicion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
					new java.awt.Color(0, 0, 0)));
			lblCondicion.setFont(new java.awt.Font("Segoe UI", 1, 22));
			lblCondicion.setForeground(new java.awt.Color(0, 64, 128));
		}
		{
			btnDetalleContrato = new JButton();
			contenedor.add(btnDetalleContrato);
			btnDetalleContrato.setText("VER DETALLE CONTRATO");
			btnDetalleContrato.setBounds(763, 745, 426, 51);
			btnDetalleContrato.setFont(new java.awt.Font("Segoe UI", 1, 22));
			btnDetalleContrato.setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, new java.awt.Color(0, 0, 0)));
			btnDetalleContrato.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Consultar_Contrato consulta = new Consultar_Contrato(
								numero_contrato);
						Principal.dskPrincipal.add(consulta);
						consulta.moveToFront();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		if (validarCoincidencias(documento) != null) {
			cargarDatosCliente(documento);
			cargarContratos(lblDni.getText());
		} else {
			cargarDatosCliente(documento);
			cargarContratos(lblDni.getText());
		}
	}

	public void cargarDatosCliente(String dni) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT doc_cli,CONCAT(nom_cli,space(1),pat_cli,space(1),mat_cli),cat_cli,count(*) FROM tb_cliente WHERE doc_cli=? OR CONCAT(nom_cli,space(1),pat_cli,space(1),mat_cli) LIKE ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni.toUpperCase());
			pst.setString(2, "%" + dni.toUpperCase() + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				lblDni.setText(rs.getString(1));
				lblCliente.setText(rs.getString(2));
				lblCondicion.setText(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void cargarContratos(String dni) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		try {
			String sql = "SELECT id_con, fec_con, fec_ven_con, fec_rem_con, des_est,CONCAT(des_art,SPACE(1), mar_art),des_pre,cap_con FROM tb_contrato c INNER JOIN tb_estado_contrato e ON c.tb_estado_contrato_id_est = e.id_est INNER JOIN tb_prestamo p  ON c.tb_prestamo_cod_pre = p.cod_pre INNER JOIN tb_detalle_contrato dc ON dc.tb_contrato_id_con=c.id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art WHERE tb_cliente_doc_cli=? UNION ALL SELECT  id_con, fec_con, fec_ven_con, fec_rem_con, des_est,CONCAT(des_art,SPACE(1), mar_art),des_pre,cap_con FROM tb_contrato_oro c INNER JOIN tb_estado_contrato e ON c.tb_estado_contrato_id_est = e.id_est INNER JOIN tb_prestamo p ON c.tb_prestamo_cod_pre = p.cod_pre INNER JOIN tb_detalle_contrato_oro dc ON dc.tb_contrato_oro_id_con=c.id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art WHERE tb_cliente_doc_cli=? UNION ALL SELECT  id_con, fec_con, fec_ven_con, fec_rem_con, des_est,CONCAT(des_art,SPACE(1), mar_art),des_pre,cap_con FROM tb_contrato_manual c INNER JOIN tb_estado_contrato e ON c.tb_estado_contrato_id_est = e.id_est INNER JOIN tb_prestamo p ON c.tb_prestamo_cod_pre = p.cod_pre INNER JOIN tb_detalle_contrato_manual dc ON dc.tb_contrato_manual_id_con=c.id_con INNER JOIN tb_articulo a ON a.cod_art = dc.tb_articulo_cod_art WHERE tb_cliente_doc_cli=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setString(2, dni);
			pst.setString(3, dni);
			ResultSet rs = pst.executeQuery();
			modeloContrato.setRowCount(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			TableRowSorter<TableModel> orden = new TableRowSorter<TableModel>(modeloContrato);
			while (rs.next()) {
				modeloContrato.addRow(new Object[] { rs.getInt(1),
						sdf.format(rs.getDate(2)), sdf.format(rs.getDate(3)),
						sdf.format(rs.getDate(4)), rs.getString(5),rs.getString(6),
						rs.getString(7), rs.getDouble(8) });
			}
			tbContratos.setModel(modeloContrato);
			tbContratos.setRowSorter(orden);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public Object validarCoincidencias(String dni) throws SQLException {
		Connection con = MySQLConexion.getConexion();
		int c = 0;
		Object seleccion = null;
		try {
			String sql = "SELECT CONCAT(nom_cli,space(1),pat_cli,space(1),mat_cli) FROM tb_cliente WHERE doc_cli=? OR CONCAT(nom_cli,space(1),pat_cli,space(1),mat_cli) LIKE ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setString(2, "%" + dni + "%");
			ResultSet rs = pst.executeQuery();
			List<String> listado = new ArrayList<String>();
			while (rs.next()) {
				listado.add(rs.getString(1));
				c++;
			}
			if (c > 1) {
				seleccion = JOptionPane
						.showInputDialog(
								this,
								"<html><h1>La búsqueda retornó más de un resultado según el criterio ingresado, seleccione...</h1></html> ",
								"Coincidencia de Resultados",
								JOptionPane.QUESTION_MESSAGE, null,
								listado.toArray(), "opcion 1");
				documento = seleccion.toString();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return seleccion;
	}

}
