package common;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXSearchField;

import view.Contrato_Prestacion;
import view.Principal;
import view.Separacion_Articulos;
import view.Venta_Articulos;

import com.toedter.calendar.JDateChooser;

public class Utiles {
	public static void MostrarOperaciones() {
		try {
			String[] botones = new String[] { "Contrato de Prestaci�n",	"Venta", "Separaci�n"/*, "Recuperaci�n" */};
			int opc = JOptionPane.showOptionDialog(null,
					"�Qu� operaci�n deseas realizar?", "Seleccionar",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
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
	
	public static void Mensaje(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	public static boolean Validar(JPanel pnl) {
		boolean val = true;

		for (Object o : pnl.getComponents()) {
			if (o instanceof JTextField && !(o instanceof JXSearchField)) {
				if (((JTextField) o).getText().equals("")) {
					((JTextField) o).setBackground(Color.RED);
					((JTextField) o).requestFocus();
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
	
	public static void LimpiarModelos(){
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
}
