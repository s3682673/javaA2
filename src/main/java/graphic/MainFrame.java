package graphic;

import pojo.system.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Const.*;

public class MainFrame extends JFrame {


    public MainFrame(){
        init();
    }

    /**
     * 初始化及相关设置
     */
    private void init() {
        setSize(MAIN_WIDTH, MAIN_HEIGHT);
        setResizable(false);
        setTitle(APP_TITLE);

        JScrollPane sp = new JScrollPane();
        sp.setLayout(new ScrollPaneLayout());

        for(int i = 0 ; i<100 ;i++){
            sp.add(new JButton("hhhhh"));
        }

        setContentPane(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
//        MainFrame mainFrame = new MainFrame();
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板
        JPanel panel = new JPanel();

        // 表头（列名）
        String[] columnNames = {"table ID", "year", "month", "day", "user name","xxx"};

        // 表格所有行数据
        Object[][] rowData = {
                {7, "Joe_07", 80, 70, 60, new JButton("xxx")},
                {8, "Joe_08", 80, 70, 60, new JButton("xxx")},
                {9, "Joe_09", 80, 70, 60, new JButton("xxx")},
                {10, "Joe_10", 80, 70, 60, new JButton("xxx")},
                {11, "Joe_11", 80, 70, 60, new JButton("xxx")},
                {12, "Joe_12", 80, 70, 60, new JButton("xxx")},
                {13, "Joe_13", 80, 70, 60, new JButton("xxx")},
                {14, "Joe_14", 80, 70, 60, new JButton("xxx")},
                {15, "Joe_15", 80, 70, 60, new JButton("xxx")},
                {16, "Joe_16", 80, 70, 60, new JButton("xxx")},
                {17, "Joe_17", 80, 70, 60, new JButton("xxx")},
                {18, "Joe_18", 80, 70, 60, new JButton("xxx")},
                {19, "Joe_19", 80, 70, 60, new JButton("xxx")},
                {20, "Joe_20", 80, 70, 60, new JButton("xxx")}
        };

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(rowData, columnNames);

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加 滚动面板 到 内容面板
        panel.add(scrollPane);

        // 设置 内容面板 到 窗口
        jf.setContentPane(panel);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
