package common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Cliente;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class JFilterComboBox extends JComboBox {

	private Vector<Cliente> entries;

	public Vector<Cliente> getEntries() {
		return entries;
	}

	public JFilterComboBox(Vector<Cliente> entries) {
		super(new DefaultComboBoxModel(entries));
		this.entries = entries;
		this.setEditable(true);

		final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();

		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						comboFilter(textfield.getText());
					}
				});
			}
		});

	}

	public void comboFilter(String enteredText) {
		Vector<Cliente> entriesFiltered = new Vector<Cliente>();

		for (Cliente entry : getEntries()) {
			if (entry.toString().toLowerCase().contains(enteredText.toLowerCase())) {
				entriesFiltered.add(entry);
			}
		}

		if (entriesFiltered.size() > 0) {
			this.setModel(new DefaultComboBoxModel(entriesFiltered));
			this.setSelectedItem(enteredText);
			this.showPopup();
		} else {
			this.hidePopup();
		}
	}
}