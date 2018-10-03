package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RenderCC implements TableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel e = new JLabel();
		e.setOpaque(true);

		if (isSelected) {
			e.setBackground(Color.ORANGE);
		}

		if (value instanceof Object) {
			e.setText(value.toString());
			e.setFont(new Font("Segoe UI", Font.BOLD, 16));			
			if (value.toString().equalsIgnoreCase("ACTIVO")) {
				e.setIcon(new ImageIcon("img/activo.png"));				
				e.setForeground(new Color(0, 128, 0));
				e.setHorizontalAlignment(SwingConstants.CENTER);
			}
			if (value.toString().equalsIgnoreCase("VENCIDO")) {
				e.setIcon(new ImageIcon("img/vencido.png"));				
				e.setForeground(new Color(240, 80, 0));
				e.setHorizontalAlignment(SwingConstants.CENTER);				
			}
			if (value.toString().equalsIgnoreCase("VITRINA (SP)")) {
				e.setIcon(new ImageIcon("img/remate.png"));				
				e.setForeground(new Color(200, 0, 0));
				e.setHorizontalAlignment(SwingConstants.CENTER);				
			}
			if (value.toString().equalsIgnoreCase("PRE")) {
				e.setIcon(new ImageIcon("img/pre.png"));				
				e.setForeground(new Color(255, 0, 128));
				e.setHorizontalAlignment(SwingConstants.CENTER);				
			}
			if (value.toString().equalsIgnoreCase("POST")) {
				e.setIcon(new ImageIcon("img/post.png"));				
				e.setForeground(new Color(128, 0, 255));
				e.setHorizontalAlignment(SwingConstants.CENTER);				
			}
		}	

		return e;

	}

}
