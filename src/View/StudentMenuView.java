package View;

import Controllers.StudentMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class StudentMenuView extends View{
    private JList<String> list;
    private StudentMenu controller;
    public StudentMenuView(StudentMenu controllerIn){
        controller=controllerIn;
        setResizable(false);
        list=new JList<>(controller.materials);
        list.setFixedCellHeight(15);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    controller.openItem(index);
                }
            }
        });


        JLabel stud=new JLabel("Вы вошли под именем "+controller.user.getName());
        JLabel title=new JLabel("Список тем:");
        JButton back=new JButton("Выход");
        JButton results=new JButton("Результаты");

        back.addActionListener(e->controller.saveAndExit());
        results.addActionListener(e->controller.showResults());
        addWindowListener(new Closer());

        stud.setBounds(550,10,150,35);
        list.setBounds(20,20,300,300);
        results.setBounds(400,50,200,35);
        back.setBounds(400,400,75,35);
        title.setBounds(1,1,300,9);

        setLayout(null);
        add(title);
        add(results);
        add(back);
        add(list);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650,500);
        setVisible(true);
    }

    class Closer implements WindowListener {

        public void windowOpened(WindowEvent e) {
        }
        public void windowClosing(WindowEvent event) {
            controller.saveAndExit();
        }
        public void windowClosed(WindowEvent e) {
        }
        public void windowIconified(WindowEvent e) {
        }
        public void windowDeiconified(WindowEvent e) {
        }
        public void windowActivated(WindowEvent e) {
        }
        public void windowDeactivated(WindowEvent e) {
        }
    }
}
