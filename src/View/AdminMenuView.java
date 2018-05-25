package View;

import Controllers.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdminMenuView extends View {
    private JList<String> list;
    private JButton results;
    private JButton addItem;
    private JButton back;
    private AdminMenu controller;

    public AdminMenuView(AdminMenu controllerIn){
        controller=controllerIn;
        setLayout(null);
        setResizable(false);
        results=new JButton("Результаты студентов");
        addItem=new JButton("Добавить тему");
        back=new JButton("Назад");
        JLabel title=new JLabel("Список тем:");

        addItem.addActionListener(e->controller.addItem());
        back.addActionListener(e->{dispose();new Main();});
        results.addActionListener(e->{dispose();new Students();});

        title.setBounds(1,1,300,9);
        addItem.setBounds(10,350,150,35);
        results.setBounds(400,50,200,35);

        back.setBounds(400,400,75,35);

        add(title);
        add(addItem);
        add(results);

        add(back);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650,500);
        setVisible(true);
    }

    public void setList(String[] materials) {
        list=new JList<String>(materials);
        list.setFixedCellHeight(15);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    dispose();
                    new ItemsAdmin(materials[index]);
                }
            }
        });
        list.setBounds(10,10,300,300);
        add(list);
    }



}
