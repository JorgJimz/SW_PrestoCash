package common;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JIconTextField extends JTextField {

	private Icon icon;

	public JIconTextField() {
		super();
		this.icon = null;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return this.icon;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.icon != null) {
			icon.paintIcon(this, g, this.getWidth() - 40, this.getHeight() / 3);
		}
	}
}