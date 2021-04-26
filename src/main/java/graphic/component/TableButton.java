package graphic.component;

import pojo.system.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TableButton extends JButton {

    public Table table = null;
    public TableButton(Table table){
        this.table = table;
        setText(String.valueOf(table.id));
        setPreferredSize(new Dimension(100, 100));
        if (table.status ==1 ){
            setEnabled(false);
            setBackground(Color.RED);
        }else {
            setEnabled(true);
            setBackground(Color.BLUE);
        }
    }

}
