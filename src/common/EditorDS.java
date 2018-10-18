package common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import controller.ArticuloController;

public class EditorDS extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;
	JComboBox<ComboItem> cbo;

	public EditorDS() {
		cbo = new JComboBox<ComboItem>();
		cbo.setFont(new java.awt.Font("Segoe UI", 1, 16));
		cbo.setForeground(new java.awt.Color(0, 64, 128));
		new ArticuloController().ListarSedes("CASA").forEach(ci -> cbo.addItem(ci));
	}

	@Override
	public Object getCellEditorValue() {
		return cbo.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (column == 3) {
			return cbo;
		}
		return null;

	}

}
