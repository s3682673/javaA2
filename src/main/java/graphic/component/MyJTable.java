package graphic.component;

import javax.swing.*;

public class MyJTable extends JTable {

    public MyJTable(Object[][] rowData, String[] columnNames) {
        super(rowData,columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
