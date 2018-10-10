package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderLCI implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);
		e.setFont(new Font("Segoe UI", Font.BOLD, 16));
		e.setText(value.toString());

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}

		if (column == 3 && isSelected == false || column == 5 && isSelected == false) {
			e.setBackground(new Color(130, 137, 143));
			e.setForeground(Color.WHITE);
		}

		return e;

	}

}
