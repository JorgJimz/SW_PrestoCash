package common;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderIM implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel e = new JLabel();
		JCheckBox chk = new JCheckBox();
		e.setOpaque(true);
		chk.setOpaque(true);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
			chk.setBackground(Color.ORANGE);
		}

		if (value instanceof Integer) {
			if (column == 0) {
				e.setText(value.toString());
				e.setFont(new java.awt.Font("Segoe UI", 1, 20));
				return e;
			}
			if ((Integer) value == 1) {
				chk.setSelected(true);
				return chk;
			} else {
				chk.setSelected(false);
				return chk;
			}
		}

		if (value instanceof BigDecimal) {
			e.setText(value.toString());
			e.setFont(new java.awt.Font("Segoe UI", 1, 20));
			return e;
		}

		if (value instanceof Double) {
			e.setText(value.toString());
			e.setFont(new java.awt.Font("Segoe UI", 1, 20));
			return e;
		}

		if (value instanceof String) {
			e.setText(value.toString());
			e.setFont(new java.awt.Font("Segoe UI", 1, 20));
			return e;
		}

		return null;
	}
}
