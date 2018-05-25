package View;


import Controllers.AdminMenu;
import Controllers.Students;

import javax.swing.*;

public class StudentsView extends View {
    private JTextArea list;
    private JScrollPane sp;
    private JButton addStud;
    private JButton delStud;
    private JButton back;
    private Students controller;
    public StudentsView(Students controllerIn){
        controller=controllerIn;
        setResizable(false);
        addStud=new JButton("Добавить студента");
        delStud=new JButton("Удалить студента");
        back=new JButton("Назад");
        list=new JTextArea();

        JButton fst=new JButton("Подробная статистика");
        sp=new JScrollPane(list);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        addStud.setBounds(5,220,200,35);
        delStud.setBounds(210,220,200,35);
        back.setBounds(250,300,100,35);
        sp.setBounds(1,20,550,200);
        fst.setBounds(410,220,210,35);

        list.setEditable(false);
        back.addActionListener(e->{dispose();new AdminMenu();});
        addStud.addActionListener(e->controller.addStud());
        delStud.addActionListener(e->controller.delStud());
        fst.addActionListener(e->controller.getFullStat());

        setLayout(null);
        add(sp);
        add(fst);
        add(addStud);
        add(delStud);
        add(back);
        setSize(650,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void setList(String s){
        list.setText(s);
    }
}
