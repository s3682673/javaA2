package graphic.component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonRender implements TableCellRenderer {
    private JPanel jPanel;
    private JButton jButton;

    public MyButtonRender() {
        initJPanel();
        initButton();
        jPanel.add(jButton);
    }

    private void initButton() {
        jButton = new JButton();
        jButton.setBounds(2, 3, 80, 30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ererere");
            }
        });
    }

    private void initJPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        jButton.setText("Delete");
        return jPanel;
    }
}