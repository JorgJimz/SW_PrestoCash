package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderHC implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();

		e.setOpaque(true);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}

		if (String.valueOf(table.getValueAt(row, 4)).equals("CANCELADO")) {
			e.setText(value.toString());
			e.setFont(new Font("Segoe UI", Font.BOLD, 16));
			e.setForeground(Color.RED);
		} else if (value instanceof Object) {
			e.setText(value.toString());
			e.setFont(new Font("Segoe UI", Font.BOLD, 16));
		}
		return e;

	}

}
