package common;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class JEditableTable extends JTable {

	public boolean EDIT;

	public void setCellEditable(boolean value) {
		this.EDIT = value;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (row == this.getRowCount() - 1 && column != 5) {
			return EDIT;
		} else {
			return false;
		}
	}

}
