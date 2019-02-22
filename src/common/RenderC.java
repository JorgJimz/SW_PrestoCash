package common;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.EstadoArticulo;

public class RenderC implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		JCheckBox chk = new JCheckBox();
		e.setOpaque(true);
		chk.setOpaque(true);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
			chk.setBackground(Color.ORANGE);
		}

		if (column == 6) {
			EstadoArticulo ea = (EstadoArticulo) value;
			e.setText(ea.getDescripcion());
			e.setBackground(ea.getBackground());
			e.setForeground(ea.getForeground());
		}

		if (column == 8) {
			if ((Integer) value == 4 || (Integer) value == 99) {
				chk.setSelected(true);
				return chk;
			} else {
				chk.setSelected(false);
				return chk;
			}
		} else {
			e.setText(value.toString());
			e.setFont(new java.awt.Font("Segoe UI", 1, 16));
			return e;
		}

	}
}
