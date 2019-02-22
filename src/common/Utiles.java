package common;

import java.awt.Color;
import java.awt.Container;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXSearchField;

import com.toedter.calendar.JDateChooser;

import controller.ClienteController;
import model.Cliente;
import model.Contrato;
import model.EstadoArticulo;
import model.EstadoContrato;
import model.Prestamo;
import view.Contrato_Prestacion;
import view.Principal;
import view.Venta_Articulos;

public class Utiles {
	public static void MostrarOperaciones(String documento, JInternalFrame internal) {
		try {
			String[] botones = new String[] { "Contrato de Prestación", "Venta / Separación", "Ninguna" };
			int opc = JOptionPane.showOptionDialog(null, "¿Qué operación deseas realizar?", "Seleccionar",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			if (opc == 0) {
				Cliente k = new ClienteController().BuscarCliente(documento);
				Contrato_Prestacion contrato = new Contrato_Prestacion(k);
				Principal.dskPrincipal.add(contrato);
				internal.dispose();
			} else if (opc == 1) {
				Venta_Articulos venta = new Venta_Articulos(documento);
				Principal.dskPrincipal.add(venta);
				internal.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Mensaje(String s, int type) {
		JOptionPane.showMessageDialog(null, "<html><h3>" + s + "</h3></html>", "Mensaje del Sistema", type);
	}

	public static boolean ValidarTabla(JTable tabla) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			for (int j = 0; j < tabla.getColumnCount(); j++) {
				String om = tabla.getValueAt(i, j).toString();
				if (om.trim().length() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<ComboItem> ConversorComboItem(List<Prestamo> lPrestamo) {
		return lPrestamo.stream().map(p -> {
			ComboItem ci = new ComboItem();
			ci.setId(p.getId());
			ci.setDescripcion(p.getDescripcion());
			ci.setValor(p.getInteres());
			ci.setExtraValor(p.getFlag());
			return ci;
		}).collect(Collectors.toList());
	}

	public static BigDecimal redondearCentimos(BigDecimal param) {
		BigDecimal Tick = new BigDecimal("0.10");
		BigDecimal formattedNumber = param.divide(Tick, 9, RoundingMode.HALF_EVEN);
		formattedNumber = formattedNumber.setScale(0, RoundingMode.HALF_UP).multiply(Tick);
		return formattedNumber;
	}

	public static boolean Validar(Container pnl) {
		boolean val = true;
		for (Object o : pnl.getComponents()) {
			if (o instanceof JIconTextField && !(o instanceof JXSearchField)) {
				if (((JIconTextField) o).getText().equals("")) {
					val = false;
					((JIconTextField) o).setOpaque(false);
					TitledBorder titledBorder = (TitledBorder) ((JIconTextField) o).getBorder();
					titledBorder.setTitleColor(new Color(200, 0, 0));
					titledBorder.setBorder(new LineBorder(new Color(200, 0, 0), 2));
					((JIconTextField) o).setIcon(new ImageIcon("img/alerta.png"));
					((JIconTextField) o).setOrientation(SwingConstants.LEFT);
					((JIconTextField) o).requestFocus();
					pnl.repaint();
				} else {
					((JIconTextField) o).setOpaque(false);
					TitledBorder titledBorder = (TitledBorder) ((JIconTextField) o).getBorder();
					titledBorder.setTitleColor(new Color(0, 128, 0));
					titledBorder.setBorder(new LineBorder(new Color(0, 128, 0), 2));
					((JIconTextField) o).setIcon(null);
					pnl.repaint();
				}
			}
			if (o instanceof JScrollPane && !(o instanceof JXSearchField)) {
				if (((JScrollPane) o).getViewport().getView() instanceof JTextArea) {
					JTextArea innerTextArea = (JTextArea) ((JScrollPane) o).getViewport().getView();
					if (innerTextArea.getText().isEmpty()) {
						innerTextArea.setOpaque(true);
						innerTextArea.setBackground(Color.RED);
						innerTextArea.setForeground(Color.GRAY);
						val = false;
					} else {
						innerTextArea.setOpaque(false);
						innerTextArea.setBackground(Color.WHITE);
						innerTextArea.setForeground(Color.GRAY);
					}
				}
			}
		}
		return val;
	}

	public static boolean ValidarGrupos(ButtonGroup bg) {
		boolean flag = true;
		if (Objects.isNull(bg.getSelection())) {
			for (int i = 1; i <= bg.getButtonCount(); i++) {
				JRadioButton rd = (JRadioButton) bg.getElements().nextElement();
				rd.setForeground(Color.RED);
				flag = false;
			}
		} else {
			for (int i = 1; i <= bg.getButtonCount(); i++) {
				JRadioButton rd = (JRadioButton) bg.getElements().nextElement();
				rd.setForeground(new java.awt.Color(0, 128, 0));
				flag = true;
			}
		}
		return flag;
	}

	public static void Limpiar(Container contenedor) {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				if (Objects.nonNull(((JTextField) o).getName()) && !((JTextField) o).getName().endsWith("_HOLD")) {
					((JTextField) o).setText("");
					((JTextField) o).setForeground(new java.awt.Color(0, 64, 128));
					((JTextField) o).setBackground(Color.WHITE);
				}
			}
			if (o instanceof JScrollPane) {
				if (((JScrollPane) o).getViewport().getView() instanceof JTextArea) {
					JTextArea a = (JTextArea) ((JScrollPane) o).getViewport().getView();
					a.setText("");
					a.setBackground(Color.WHITE);
					a.setForeground(Color.GRAY);
				}
			}
		}
	}

	public static void Bloquear(Container contenedor) {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setEnabled(false);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setEnabled(false);
			}
			if (o instanceof JRadioButton) {
				((JRadioButton) o).setEnabled(false);
			}
			if (o instanceof JScrollPane) {
				JTextArea txt = (JTextArea) ((JScrollPane) o).getViewport().getView();
				txt.setEnabled(false);
			}
			if (o instanceof JButton) {
				((JButton) o).setEnabled(false);
			}
		}
	}

	public static void BloquearMenu(JMenuBar bar) {
		for (Object o : bar.getComponents()) {
			if (o instanceof JMenu) {
				((JMenu) o).setEnabled(false);
			}
		}
	}

	public static int[] mapToInt(String[] args) {
		int myar[] = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			myar[i] = Integer.parseInt(args[i]);
		}
		return myar;
	}

	public static void ActualizacionContrato(Contrato c) {
		try {
			if (c.getEstadoContrato().getId() == EstadoContrato.CANCELADO) {
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.LIBRE));
					item.getArticulo().setFlagContrato(c.getFlag());
					item.getArticulo().setNumeroContrato(c.getNumero());
					item.getArticulo().setCapitalContrato(item.getTasacion());
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("UPD CNT");
				});
			} else if (c.getEstadoContrato().getId() == EstadoContrato.FUNDIDO) {
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.FUNDIDO));
					item.getArticulo().setFlagContrato(c.getFlag());
					item.getArticulo().setNumeroContrato(c.getNumero());
					item.getArticulo().setCapitalContrato(item.getTasacion());
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("UPD CNT");
				});
			} else if (c.getEstadoContrato().getId() == EstadoContrato.ANULADO) {
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.BAJA));
					item.getArticulo().setFlagContrato(c.getFlag());
					item.getArticulo().setNumeroContrato(c.getNumero());
					item.getArticulo().setCapitalContrato(item.getTasacion());
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("UPD CNT");
				});
			} else if (c.getEstadoContrato().getId() == EstadoContrato.REMATADO) {
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.REMATADO));
					item.getArticulo().setFlagContrato(c.getFlag());
					item.getArticulo().setNumeroContrato(c.getNumero());
					item.getArticulo().setCapitalContrato(item.getTasacion());
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("UPD CNT");
				});
			} else if (c.getEstadoContrato().getId() == EstadoContrato.VITRINA) {
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.VITRINA));
					item.getArticulo().setFlagContrato(c.getFlag());
					item.getArticulo().setNumeroContrato(c.getNumero());
					item.getArticulo().setCapitalContrato(item.getTasacion());
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("UPD CNT");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void DetectarEstado(Contrato c) {
		try {
			LocalDate hoy = LocalDate.now();
			LocalDate gcPre = LocalDate.parse(c.getFechaVencimiento()).plusMonths(1).minusDays(Constantes.RANGO_PRE);
			LocalDate gcPost = LocalDate.parse(c.getFechaVencimiento()).plusMonths(1).plusDays(Constantes.RANGO_POST);
			LocalDate gcRem = LocalDate.parse(c.getFechaRemate());
			if (hoy.isAfter(gcPost)) {
				int totalDetalle = c.getDetalleContratos().size();
				c.setEstadoContrato(new EstadoContrato(EstadoContrato.VITRINA_SP));
				c.getDetalleContratos().forEach(item -> {
					if (!Arrays.asList(Constantes.ESTADOS_INACTIVIDAD_ARTICULO)
							.contains(item.getArticulo().getEstadoArticulo().getId())) {
						int neoEstado = item.getArticulo().getPrecioVenta().compareTo(BigDecimal.ZERO) == 0
								&& item.getArticulo().getEstadoArticulo().getId() != EstadoArticulo.BAJA
										? EstadoArticulo.SIN_PRECIO
										: EstadoArticulo.VITRINA;
						item.getArticulo().setEstadoArticulo(new EstadoArticulo(neoEstado));
						item.getArticulo().setFlagContrato(c.getFlag());
						item.getArticulo().setNumeroContrato(c.getNumero());
						item.getArticulo().setCapitalContrato(item.getTasacion());
						item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
						item.getArticulo().setUsuarioModificacion("AUTO UPD");
					}
				});

				int conteoVitrina = (int) c.getDetalleContratos().stream().filter(Constantes.predicadoConversorVitrina)
						.count();
				if (totalDetalle == conteoVitrina) {
					c.setEstadoContrato(new EstadoContrato(EstadoContrato.VITRINA));
				} else {
					c.setEstadoContrato(new EstadoContrato(EstadoContrato.VITRINA_SP));
				}

			} else if (hoy.isAfter(gcRem) && hoy.isBefore(gcPost) || hoy.isEqual(gcPost)) {
				c.setEstadoContrato(new EstadoContrato(EstadoContrato.POST));
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.ACTIVO));
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("AUTO UPD");
				});
			} else if (hoy.isAfter(gcPre) || hoy.isEqual(gcPre) && hoy.isBefore(gcRem) || hoy.isEqual(gcRem)) {
				c.setEstadoContrato(new EstadoContrato(EstadoContrato.PRE));
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.ACTIVO));
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("AUTO UPD");
				});
			} else if (hoy.isAfter(LocalDate.parse(c.getFechaVencimiento()))
					&& !hoy.isEqual(LocalDate.parse(c.getFechaVencimiento()))) {
				c.setEstadoContrato(new EstadoContrato(EstadoContrato.VENCIDO));
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.ACTIVO));
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("AUTO UPD");
				});
			} else {
				c.setEstadoContrato(new EstadoContrato(EstadoContrato.ACTIVO));
				c.getDetalleContratos().forEach(item -> {
					item.getArticulo().setEstadoArticulo(new EstadoArticulo(EstadoArticulo.ACTIVO));
					item.getArticulo().setFechaModificacion(String.valueOf(LocalDate.now()));
					item.getArticulo().setUsuarioModificacion("AUTO UPD");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
