package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.EquipmentView;

public class EquipmentController implements ActionListener {
	private EquipmentView equipmentView;

	public EquipmentController(EquipmentView equipmentView) {
		super();
		this.equipmentView = equipmentView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		searchEvent(equipmentView.getEquipmentTable(), equipmentView.getSearchBox());
	}
	
	private void searchEvent(JTable table, JTextField textField) {
		String room = "";
		if (equipmentView.getBedList().getSelectedItem() != "All") {
			room = room + equipmentView.getBedList().getSelectedItem().toString();
		}
		String equipment = "";
		if (equipmentView.getEquipmentList().getSelectedItem() != "All") {
			equipment = equipment + equipmentView.getEquipmentList().getSelectedItem().toString();
		}
		String status = "";
		if (equipmentView.getStatusList().getSelectedItem() != "All") {
			status = status + equipmentView.getStatusList().getSelectedItem().toString();
		}
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowFilter<Object,Object>> filters = new ArrayList<>();

        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + room,0);
		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + equipment,1);
		RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + status,2);
		filters.add(filter1);
		filters.add(filter2);
		filters.add(filter3);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
	            filters.clear();
	            RowFilter<Object,Object> filter4 = RowFilter.regexFilter("(?i)" + input);
	            filters.add(filter1);
	            filters.add(filter2);
	            filters.add(filter3);
	            filters.add(filter4);
	            sorter.setRowFilter(RowFilter.andFilter(filters));
			}
      });
	}
}
