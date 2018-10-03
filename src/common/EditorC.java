package common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorC extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;
	JCheckBox chk = new JCheckBox();

	public Object getCellEditorValue() {
		if (chk.isSelected()) {
			return new Integer(99);
		} else {
			return new Integer(1);
		}
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof Integer && ((Integer) value).intValue() != 4) {
			return chk;
		}
		return null;
	}

}
