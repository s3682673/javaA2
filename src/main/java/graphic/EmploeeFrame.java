package graphic;

import graphic.menu.EmployeeMenu;
import pojo.user.Account;

import javax.swing.*;
import java.awt.*;

import static util.Const.*;

public class EmploeeFrame extends JFrame {

    private Account account = null;


    EmployeeMenu menuBar = null;

    public EmploeeFrame(Account account){
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

        menuBar = new EmployeeMenu(account);
        setJMenuBar(menuBar);

        setVisible(true);


    }

}
