package common;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXSearchField;

import com.toedter.calendar.JDateChooser;

import view.Contrato_Prestacion;
import view.Principal;
import view.Venta_Articulos;

public class Utiles {
	public static void MostrarOperaciones() {
		try {
			String[] botones = new String[] { "Contrato de Prestación",
					"Venta", "Separación"/* , "Recuperación" */};
			int opc = JOptionPane.showOptionDialog(null,
					"¿Qué operación deseas realizar?", "Seleccionar",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			if (opc == 0) {
				Contrato_Prestacion c = new Contrato_Prestacion(null);
				Principal.dskPrincipal.add(c);
			} else if (opc == 1) {
				Venta_Articulos venta = new Venta_Articulos();
				Principal.dskPrincipal.add(venta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Mensaje(String s, int type) {
		JOptionPane.showMessageDialog(null, "<html><h3>" + s + "</h3></html>",
				"Mensaje del Sistema", type);
	}

	public static boolean Validar(Container pnl) {
		boolean val = true;

		for (Object o : pnl.getComponents()) {
			if (o instanceof JTextField && !(o instanceof JXSearchField)) {
				if (((JTextField) o).getText().equals("")) {
					((JTextField) o).setBackground(Color.RED);
					((JTextField) o).setForeground(Color.WHITE);
					((JTextField) o).requestFocus();
					val = false;
				} else {
					((JTextField) o).setBackground(Color.WHITE);
					((JTextField) o).setForeground(Color.BLACK);
				}
			}
			if (o instanceof JScrollPane && !(o instanceof JXSearchField)) {
				if (((JScrollPane) o).getViewport().getView() instanceof JTextArea) {
					JTextArea innerTextArea = (JTextArea) ((JScrollPane) o)
							.getViewport().getView();
					if (innerTextArea.getText().equals("")) {
						innerTextArea.setBackground(Color.RED);
						innerTextArea.setForeground(Color.WHITE);
						innerTextArea.requestFocus();
						val = false;
					} else {
						innerTextArea.setBackground(Color.WHITE);
						innerTextArea.setForeground(Color.BLACK);
					}
				}
			}
			if (o instanceof JRadioButton && !(o instanceof JXSearchField)) {
				if (!((JRadioButton) o).isSelected()) {
					((JRadioButton) o).setForeground(Color.RED);
					((JRadioButton) o).requestFocus();
					val = false;
				} else {
					((JRadioButton) o).setForeground(new Color(0, 128, 0));
				}
			}

		}
		return val;
	}

	public static void Limpiar(Container contenedor) {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setText("");
				((JTextField) o).setForeground(new java.awt.Color(0, 64, 128));
				((JTextField) o).setBackground(Color.WHITE);
			}
			if (o instanceof JScrollPane) {				
				if(o instanceof JTextArea){
					JTextArea a = (JTextArea) ((JScrollPane) o).getViewport()
							.getView();
					a.setText("");
					a.setBackground(Color.WHITE);
				}			
				
			}
		}
	}

	public static void LimpiarModelos() {
		Constantes.ContratoModel.setRowCount(0);
		Constantes.ArticuloModel.setRowCount(0);
	}

	public static void Bloquear(JPanel contenedor) {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setEnabled(false);
			}
			if (o instanceof JDateChooser) {
				((JDateChooser) o).setEnabled(false);
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
}
