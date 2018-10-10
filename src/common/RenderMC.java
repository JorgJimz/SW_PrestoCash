package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderMC implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);
		e.setFont(new Font("Segoe UI", Font.BOLD, 16));
		e.setText(value.toString());

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}
		
		if (table.getColumnName(column).equals("STATUS")) {
			if (String.valueOf(value).equals("0")) {
				e.setText("INACTIVO");
				e.setBackground(new Color(200, 0, 0));
				e.setForeground(Color.WHITE);
			} else {
				e.setText("ACTIVO");
			}
		} else {
			e.setText(value.toString());
		}

		return e;

	}

}
