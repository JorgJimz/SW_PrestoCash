package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RenderIO implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}

		if (value instanceof Object) {
			e.setText(value.toString());
			e.setFont(new Font("Segoe UI", Font.BOLD, 16));
			if (value.toString().equalsIgnoreCase("I")) {
				e.setText("LLAMADA ENTRANTE");
				e.setIcon(new ImageIcon("img/in.png"));
				e.setHorizontalAlignment(SwingConstants.LEFT);
			}
			if (value.toString().equalsIgnoreCase("O")) {
				e.setText("LLAMADA SALIENTE");
				e.setIcon(new ImageIcon("img/out.png"));
				e.setHorizontalAlignment(SwingConstants.LEFT);
			}

		}

		return e;

	}

}
