package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;

public class IconComboBoxRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	private Map<String, ImageIcon> iconMap = new HashMap<>();
	private Color background = new Color(0, 100, 255, 15);
	private Color defaultBackground = (Color) UIManager.get("List.background");

	public IconComboBoxRenderer() {
		iconMap.put("PRINTABLE", new ImageIcon("img/print_small.png"));		
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		ComboItem ci = (ComboItem) value;

		this.setText(ci.getDescripcion());
		this.setIcon(iconMap.get(ci.getValor()));
		this.setFont(new Font("Segoe UI", 1, 16));		

		if (!isSelected) {
			this.setBackground(index % 2 == 0 ? background : defaultBackground);
		}

		return this;
	}

}
