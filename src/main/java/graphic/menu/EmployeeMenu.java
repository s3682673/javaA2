package graphic.menu;

import Service.BookService;
import Service.TableService;
import Service.UserService;
import com.sun.tools.javac.util.StringUtils;
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


public class EmployeeMenu extends JMenuBar {

    Account user = null;

    TableService tableService = new TableService();

    BookService bookService = new BookService();

    /*
     * first menu
     */
    JMenu accountMenu = null;
    JMenu tableMenu = null;
    JMenu bookMenu = null;

    public EmployeeMenu(Account user){
        this.user = user;
        setVisible(false);
        accountMenu = new JMenu("My Account");
        tableMenu = new JMenu("tables");
        bookMenu = new JMenu("my Bookings");
        init();
    }


    private void init(){


        add(accountMenu);
        add(tableMenu);
        add(bookMenu);

        JMenuItem myAccount = new JMenuItem("my account");
        JMenuItem chPas = new JMenuItem("change password");


        accountMenu.add(myAccount);
        accountMenu.add(chPas);

        JMenuItem viewAllTables = new JMenuItem("book");

        tableMenu.add(viewAllTables);

        JMenuItem viewAllBook = new JMenuItem("view all");

        bookMenu.add(viewAllBook);

        myAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("my account");
                jf.setSize(300, 300);
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                JLabel info = new JLabel();
                info.setText("name: "+ user.name + ", type: "+(user.type == 1? "employee":"admin"));

                jf.add(info);
                jf.setVisible(true);
            }
        });


        chPas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("change password");
                jf.setSize(400, 400);
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


                JPanel jp=new JPanel();
                jp.setLayout(new FlowLayout());

                JLabel header = new JLabel();
                JLabel info = new JLabel();
                info.setText("new password: ");
                JPasswordField pass = new JPasswordField(20);
                pass.setEchoChar('*');

                JButton con = new JButton("confirm");

                con.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean res = false;

                        if(pass.getPassword() != null && pass.getPassword().length >0 ){
                            res = new UserService().changePassword(user.name, pass.getPassword());
                            if(res = true){
                                header.setText("change successful");
                            }else {
                                header.setText("failed to change");
                            }
                        }else {
                            header.setText("password cannot be null");
                            header.setBackground(Color.RED);
                        }

                    }
                });


                jp.add(info);
                jp.add(pass);
                jp.add(con);
                jp.add(header);

                jf.setContentPane(jp);

                jf.setVisible(true);
            }
        });

        viewAllTables.addActionListener(new ActionListener() {
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
                            JFrame jf = new JFrame("Book table ");
                            jf.setSize(600, 300);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                            JPanel jPanel = new JPanel();
                            jPanel.setLayout(new FlowLayout());

                            JLabel header = new JLabel("table id: ");
                            JLabel tableId = new JLabel(String.valueOf(b.table.id));

                            JLabel year = new JLabel("yead: ");
                            JTextField yearT = new JTextField(6);

                            JLabel month = new JLabel("month: ");
                            JTextField monthT = new JTextField(6);

                            JLabel day = new JLabel("day: ");
                            JTextField dayT = new JTextField(6);

                            JButton query = new JButton("query");

                            JLabel notify = new JLabel();

                            JButton book = new JButton("book");
                            book.setVisible(false);

                            book.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Integer year = Integer.parseInt(yearT.getText());
                                    Integer month = Integer.parseInt(monthT.getText());
                                    Integer day = Integer.parseInt(dayT.getText());

                                    boolean insert = bookService.insert(new Booking(year, month, day, b.table.id, user.name));

                                    if(!insert){
                                        notify.setVisible(true);
                                        notify.setText("inset successful");
                                    }else {
                                        notify.setVisible(true);
                                        notify.setText("Failed to insert");
                                    }
                                }
                            });

                            query.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Integer year = Integer.parseInt(yearT.getText());
                                    Integer month = Integer.parseInt(monthT.getText());
                                    Integer day = Integer.parseInt(dayT.getText());
                                    if(year<2020 || month<0 || month>12 || day<0 || day>31){
                                        notify.setText("wrong date format");
                                        notify.setBackground(Color.RED);
                                    }else {
                                        Booking res = bookService.queryBookings(year,month,day,b.table.id);

                                        if(res == null){
                                            notify.setVisible(false);
                                            book.setVisible(true);
                                        }else {
                                            notify.setVisible(true);
                                            book.setVisible(false);
                                            notify.setText("have bean booked");
                                            notify.setBackground(Color.RED);
                                        }
                                    }

                                }
                            });

                            jPanel.add(header);

                            jPanel.add(tableId);
                            jPanel.add(year);
                            jPanel.add(yearT);
                            jPanel.add(month);
                            jPanel.add(monthT);
                            jPanel.add(day);
                            jPanel.add(dayT);
                            jPanel.add(query);
                            jPanel.add(notify);
                            jPanel.add(book);



                            jf.getContentPane().add(jPanel);
                            jf.setVisible(true);

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

        viewAllBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("bookings");
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();

                String[] columnNames = {"booking ID","table ID", "year", "month", "day", "user name", "delete"};

                String name = user.name;
                ArrayList<Booking> userBookings = bookService.getUserBookings(name);
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


        setVisible(true);

    }


}
