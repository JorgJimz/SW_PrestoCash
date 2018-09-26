package common;

import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditorLCI extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;

	private static final int JCOMBOBOX = 0;
	private static final int JTEXTFIELD = 1;
	private static final int JCOMBOBOX_MONEDA = 2;

	int f;

	DefaultCellEditor[] editores;

	JTextField txt;
	JComboBox<String> cbo;
	JComboBox<String> cbo_moneda;

	public EditorLCI() {
		txt = new JTextField();
		txt.setFont(new Font("Segoe UI", 1, 20));
		cbo = new JComboBox<String>(new DefaultComboBoxModel(new String[] { "INGRESO MANUAL", "ABONO CAJA" }));
		cbo.setFont(new Font("Segoe UI", 1, 20));

		cbo_moneda = new JComboBox<String>(new DefaultComboBoxModel(new String[] { "SOLES", "DÓLARES" }));
		cbo_moneda.setFont(new Font("Segoe UI", 1, 20));

		editores = new DefaultCellEditor[3];
		editores[JCOMBOBOX] = new DefaultCellEditor(cbo);
		editores[JTEXTFIELD] = new DefaultCellEditor(txt);
		editores[JCOMBOBOX_MONEDA] = new DefaultCellEditor(cbo_moneda);

		editores[JCOMBOBOX].setClickCountToStart(2);
		editores[JTEXTFIELD].setClickCountToStart(2);
		editores[JCOMBOBOX_MONEDA].setClickCountToStart(2);
	}

	public Object getCellEditorValue() {
		switch (f) {
		case JCOMBOBOX:
			return cbo.getSelectedItem();
		case JCOMBOBOX_MONEDA:
			return cbo_moneda.getSelectedItem();
		default:
			return txt.getText().toUpperCase();
		}
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (column == 1) {
			f = JCOMBOBOX;
			return editores[JCOMBOBOX].getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		if (column == 6) {
			f = JCOMBOBOX_MONEDA;
			return editores[JCOMBOBOX_MONEDA].getTableCellEditorComponent(table, value, isSelected, row, column);
		} else if (column == 0 || column == 2 || column == 3 || column == 4) {
			f = JTEXTFIELD;
			return editores[JTEXTFIELD].getTableCellEditorComponent(table, value, isSelected, row, column);
		} else {
			return null;
		}
	}

}
