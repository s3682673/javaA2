package graphic.menu;

import Service.UserService;
import graphic.component.MyButtonRender;
import graphic.component.MyJTable;
import graphic.component.TableButton;
import pojo.system.Booking;
import pojo.system.Table;
import pojo.user.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminMenu extends EmployeeMenu{

    JMenu mgrUser = null;
    JMenu mgrBookings = null;

    UserService userService = null;


    public AdminMenu(Account user) {
        super(user);
        mgrUser = new JMenu("users management");
        mgrBookings = new JMenu("Bookings Management");
        userService = new UserService();
        initAdmin();
    }

    public  void initAdmin(){

        add(mgrUser);
        add(mgrBookings);

        JMenuItem block = new JMenuItem("block table");

        tableMenu.add(block);

        JMenuItem users = new JMenuItem("All users");

        JMenuItem bookings = new JMenuItem("All Bookings");

        mgrUser.add(users);

        mgrBookings.add(bookings);

        block.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("All tables");
                jf.setSize(300, 300);
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                ArrayList<Table> allTables = tableService.getAllTables();

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 3 ));

                List<TableButton> buttons = allTables.stream().map(TableButton::new).collect(Collectors.toList());
                for(TableButton b : buttons){
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tableService.blockTable(b.table.id);
                            b.setEnabled(false);
                        }
                    });
                    panel.add(b);
                    jf.setVisible(true);
                }
                JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                container.add(panel);
                JScrollPane scrollPane = new JScrollPane(container);

                jf.getContentPane().add(scrollPane);
                jf.pack();
                jf.setVisible(true);
            }
        });

        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("All users");
                jf.setSize(300, 300);
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                ArrayList<Account> allUsers = userService.getAllUsers();

                JPanel panel = new JPanel();


                if(allUsers.size()>0){
                    panel.setLayout(new GridLayout(allUsers.size(), 4));
                    for(Account account: allUsers){
                        panel.add(new JLabel(String.valueOf(account.id)));
                        panel.add(new JLabel(String.valueOf(account.type)));
                        panel.add(new JLabel(account.name));
                        Button button = new Button("change to admin");
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                userService.updateAdmin(account.id);
                                JFrame jf = new JFrame("change successful");
                                jf.setSize(300, 100);
                                jf.setLocationRelativeTo(null);
                                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                jf.setVisible(true);
                            }
                        });
                        panel.add(button);
                    }
                }

                JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                container.add(panel);
                JScrollPane scrollPane = new JScrollPane(container);

                jf.getContentPane().add(scrollPane);
                jf.pack();
                jf.setVisible(true);

            }
        });

        bookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("bookings");
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();

                String[] columnNames = {"booking ID","table ID", "year", "month", "day", "user name", "delete"};

                String name = user.name;
                ArrayList<Booking> userBookings = bookService.getAllBookings();
                Object[][] rowData = null;

                if(userBookings.size() != 0){
                    rowData = new Object[userBookings.size()][];
                    for(int i = 0; i<userBookings.size(); ++i){
                        Booking booking = userBookings.get(i);
                        rowData[i] = new Object[]{booking.id,booking.tableId,booking.year,booking.month,booking.day,booking.name,1};
                    }
                }else {
                    rowData = new Object[][]{{"null","null","null","null","null","null"}};
                }


                JTable table = new MyJTable(rowData, columnNames);


                table.setForeground(Color.BLACK);
                table.setFont(new Font(null, Font.PLAIN, 14));
                table.setSelectionForeground(Color.DARK_GRAY);
                table.setSelectionBackground(Color.LIGHT_GRAY);
                table.setGridColor(Color.GRAY);

                table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
                table.getTableHeader().setForeground(Color.RED);
                table.getTableHeader().setResizingAllowed(false);
                table.getTableHeader().setReorderingAllowed(false);

                table.setRowHeight(30);

                table.getColumnModel().getColumn(0).setPreferredWidth(40);

                table.setPreferredScrollableViewportSize(new Dimension(600, 300));

                table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender());

                Object[][] finalRowData = rowData;
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        int selectedRow = table.getSelectedRow();
                        Long id = (Long) finalRowData[selectedRow][0];
                        int col = table.getSelectedColumn();
                        if(col == 6) {
                            bookService.deleteById(id);
                            JFrame jf = new JFrame("delete table " + id + "successful");
                            jf.setSize(300, 100);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            jf.setVisible(true);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                JScrollPane scrollPane = new JScrollPane(table);

                panel.add(scrollPane);

                jf.setContentPane(panel);

                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
            }
        });

    }
}
