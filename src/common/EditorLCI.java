package common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditorLCI extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;

	JComboBox<String> cbo = new JComboBox<String>(new DefaultComboBoxModel(
			new String[] { "1%", "1% + M", "2%", "2% + M", "M", "PAG", "ABN", "IEX.", "VTA", "PET." }));

	public Object getCellEditorValue() {
		return cbo.getSelectedItem();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (column == 1) {
			return cbo;
		}
		return null;

	}

}
