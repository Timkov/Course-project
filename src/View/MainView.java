package View;

import Controllers.Main;
import Controllers.StudentLogin;

import javax.swing.*;



public class MainView extends View {
    private Main controller;

    public MainView(Main controllerIn){
        controller=controllerIn;
        setLayout(null);
        setResizable(false);
        JButton student=new JButton("Войти как студент");
        JButton admin=new JButton("Войти как преподаватель");
        JButton exit=new JButton("Выход");
        JTextArea hello = new JTextArea("  Конструкции языка Java. Основные операции и управляющие структуры\n" +
                "   Java - обьектно-ориентированный, многопоточный язык программирования,\n" +
                " \tразработанный компанией Sun Microsystems.\n" +
                " \tВ данном курсе рассматриваются типы данных,\n" +
                "        лексика и управляющие структуры данного языка программирования.");
        hello.setLineWrap(true);
        hello.setWrapStyleWord(true);
        hello.setEditable(false);
        hello.setBounds(10,10,470,80);
        student.setBounds(25,130,150,35);
        admin.setBounds(200,130,200,35 );
        exit.setBounds(350,215,100,30);
        exit.addActionListener(e->dispose());
        student.addActionListener(e->{dispose();new StudentLogin();});
        admin.addActionListener(e->{controller.checkAdmin();});
        add(hello);
        add(student);
        add(admin);
        add(exit);
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }


}

