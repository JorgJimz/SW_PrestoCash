package common;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXSearchField;

import com.toedter.calendar.JDateChooser;

import view.Contrato_Prestacion;
import view.Principal;
import view.Separacion_Articulos;
import view.Venta_Articulos;

public class Utiles {
	public static void MostrarOperaciones() {
		try {
			String[] botones = new String[] { "Contrato de Prestación", "Venta", "Separación"/* , "Recuperación" */ };
			int opc = JOptionPane.showOptionDialog(null, "¿Qué operación deseas realizar?", "Seleccionar",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			if (opc == 0) {
				Contrato_Prestacion c = new Contrato_Prestacion(null);
				Principal.dskPrincipal.add(c);
			} else if (opc == 1) {
				Venta_Articulos venta = new Venta_Articulos(null);
				Principal.dskPrincipal.add(venta);
			} else {
				Separacion_Articulos sep = new Separacion_Articulos(null);
				Principal.dskPrincipal.add(sep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Mensaje(String s, int type) {
		JOptionPane.showMessageDialog(null, "<html><h3>" + s + "</h3></html>", "Mensaje del Sistema", type);
	}

	public static boolean Validar(JPanel pnl) {
		boolean val = true;

		for (Object o : pnl.getComponents()) {
			if (o instanceof JTextField && !(o instanceof JXSearchField)) {
				if (((JTextField) o).getText().equals("")) {
					((JTextField) o).setBackground(Color.RED);
					((JTextField) o).setForeground(Color.WHITE);
					((JTextField) o).requestFocus();
					return val = false;
				}				
			}
			if(o instanceof JTextArea && !(o instanceof JXSearchField)) {
				if (((JTextArea) o).getText().equals("")) {
					((JTextArea) o).setBackground(Color.RED);
					((JTextArea) o).setForeground(Color.WHITE);
					((JTextArea) o).requestFocus();
					return val = false;
				}	
			}
		}
		return val;
	}

	public static void Limpiar(JPanel contenedor) {
		for (Object o : contenedor.getComponents()) {
			if (o instanceof JTextField) {
				((JTextField) o).setText("");
				((JTextField) o).setBackground(Color.WHITE);
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

	public static int[] mapToInt(String[] args) {
		int myar[] = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			myar[i] = Integer.parseInt(args[i]);
		}
		return myar;
	}
}
