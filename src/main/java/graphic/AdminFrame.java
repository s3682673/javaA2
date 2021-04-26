package graphic;

import graphic.menu.AdminMenu;
import graphic.menu.EmployeeMenu;
import pojo.user.Account;

import javax.swing.*;

import static util.Const.*;

public class AdminFrame extends JFrame{

    private Account account = null;


    EmployeeMenu menuBar = null;

    public AdminFrame(Account account){
        this.account = account;
        init();
    }

    /**
     * 初始化及相关设置
     */
    private void init() {
        setSize(MAIN_WIDTH, MAIN_HEIGHT);
        setResizable(false);
        setTitle(APP_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar = new AdminMenu(account);
        setJMenuBar(menuBar);

        setVisible(true);


    }
}
