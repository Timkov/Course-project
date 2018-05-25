package View;

import Controllers.Items;
import Controllers.StudentMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemsView extends View {

    protected JTextArea text;
    protected JList<String> list;
    protected JButton test = new JButton("Пройти тест");
    protected JButton back=new JButton("Назад ");;
    protected JLabel label;
    protected Items controller;

    public ItemsView(Items controllerIn) {
        controller = controllerIn;
        setResizable(false);
        label = new JLabel("Вы вошли как " + controller.user.getName() + ", тема " + controller.curItem);
        setLayout(null);
        list = new JList<>(controller.material);
        list.setFixedCellHeight(15);


        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setText(" ");
        text.setFont(new Font("Verdana", Font.TYPE1_FONT, 12));
        JScrollPane sp = new JScrollPane(text);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = list.locationToIndex(e.getPoint());
                    text.setText(controller.read(index));
                }
            }
        });

        back.addActionListener(e->{dispose();new StudentMenu(controller.user);});

        test.addActionListener(e->{controller.startTest();});

        test.setBounds(50,420,150,35);
        list.setBounds(20,20,300,400);
        sp.setBounds(320,10,600,500);
        back.setBounds(800,520,75,35);
        label.setBounds(1,1,300,9);

        add(sp);
        add(test);
        add(list);
        add(back);
        add(label);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(950,600);
        setVisible(true);
    }

    public String getText(){
        return text.getText();
    }
}
