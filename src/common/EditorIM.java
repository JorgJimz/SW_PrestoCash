package common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorIM extends AbstractCellEditor implements TableCellEditor {
	
	private static final long serialVersionUID = 1L;
	JCheckBox chk = new JCheckBox();	
	public Object getCellEditorValue() {
		if(chk.isSelected()){
			return new Integer(1);
		}else{
			return new Integer(0);
		}
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {		
		if (value instanceof Integer) {
			return chk;
		}
			return null;

	}

}
