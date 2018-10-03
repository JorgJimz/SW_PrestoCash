package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RenderLCE implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);
		e.setFont(new Font("Segoe UI", Font.BOLD, 16));
		e.setText(value.toString());

		table.getColumn("DESCRIPCIÓN").setPreferredWidth(210);
		table.getColumn("TIPO").setPreferredWidth(180);
		table.getColumn("MONTO").setPreferredWidth(110);
		table.getColumn("MONEDA").setPreferredWidth(130);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}

		if (column == 3 && isSelected == false) {
			e.setText("");
			e.setBackground(new Color(130, 137, 143));
			e.setHorizontalTextPosition(SwingConstants.CENTER);
			e.setIcon((value.toString().equals("SOLES")) ? new ImageIcon("img/pen.png") : new ImageIcon("img/usd.png"));
		}

		if (column == 2 && isSelected == false) {
			e.setBackground(new Color(130, 137, 143));
			e.setForeground(Color.WHITE);
		}

		return e;

	}

}
