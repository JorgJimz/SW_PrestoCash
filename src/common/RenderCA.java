package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderCA implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);
		e.setFont(new Font("Segoe UI", Font.BOLD, 16));
		e.setText(value.toString());

		table.getColumn("CID").setMaxWidth(45);
		table.getColumn("CONTRATO").setMaxWidth(100);
		table.getColumn("AID").setMaxWidth(45);
		table.getColumn("ARTÍCULO").setMaxWidth(500);
		table.getColumn("ESTADO").setMaxWidth(100);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}		

		return e;

	}

}
