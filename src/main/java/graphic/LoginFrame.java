package graphic;

import Service.UserService;
import graphic.menu.AdminMenu;
import pojo.user.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static util.Const.*;

public class LoginFrame extends JFrame {

    UserService userService = new UserService();


    private Panel controlPanel;


    public LoginFrame(){
        init();
        showTextFieldDemo();
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

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        add(controlPanel);
        setVisible(true);
    }


    private void showTextFieldDemo() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        JLabel notify = new JLabel();
        Label namelabel = new Label("User ID: ", Label.RIGHT);
        Label passwordLabel = new Label("Password: ", Label.CENTER);
        final TextField userText = new TextField(12);
        final TextField passwordText = new TextField(12);
        passwordText.setEchoChar('*');

        Button loginButton = new Button("Login");
        Button registerButton = new Button("register");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account login = userService.login(userText.getText(), passwordText.getText());

                if( login != null){
                    if(login.type == 1){
                        EmploeeFrame emploeeFrame = new EmploeeFrame(login);

                        dispose();
                    }else if (login.type == 0){
                        AdminFrame adminFrame = new AdminFrame(login);
                        dispose();
                    }

                }else {
                    notify.setText("Wrong password or username");
                    notify.setForeground(Color.RED);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean register = userService.register(userText.getText(), passwordText.getText());
                if(register){
                    notify.setText("register success");
                }else {
                    notify.setText("Failed to register, may username duplicate");
                    notify.setForeground(Color.RED);
                }

            }
        });


        controlPanel.add(notify);
        controlPanel.add(namelabel);
        controlPanel.add(userText);
        controlPanel.add(passwordLabel);
        controlPanel.add(passwordText);
        controlPanel.add(loginButton);
        controlPanel.add(registerButton);
        setVisible(true);
    }


}
