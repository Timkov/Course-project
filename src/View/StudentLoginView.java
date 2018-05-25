package View;

import Controllers.Main;
import Controllers.StudentLogin;

import javax.swing.*;

public class StudentLoginView extends View {
    private JTextField name;
    private JTextField password;
    private StudentLogin controller;

    public StudentLoginView(StudentLogin controllerIn) {
        controller=controllerIn;
        setResizable(false);
        JLabel nameL = new JLabel("Введите фамилию и имя  ");
        name = new JTextField();
        JLabel passL = new JLabel("Введите пароль  ");
        password = new JTextField();
        JButton logIn = new JButton("Войти");
        JButton back = new JButton("Назад");

        logIn.addActionListener(e -> {
            controller.login();
        });
        back.addActionListener(e -> {
            dispose();
            new Main();
        });

        nameL.setBounds(50, 100, 250, 30);
        name.setBounds(210, 100, 200, 30);
        passL.setBounds(100, 200, 200, 30);
        password.setBounds(210, 200, 200, 30);
        logIn.setBounds(210, 250, 100, 35);
        back.setBounds(400, 400, 100, 35);

        setLayout(null);
        add(nameL);
        add(name);
        add(passL);
        add(password);
        add(logIn);
        add(back);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(560, 500);
        setVisible(true);
    }

    public String getInputName(){
        return name.getText();
    }

    public String getPassword(){
        return password.getText();
    }


}

