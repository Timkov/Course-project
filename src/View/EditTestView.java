package View;


import Controllers.EditTest;

import javax.swing.*;

public class EditTestView extends View{
    private JTextArea questionField;
    private JTextField answerField;
    private JComboBox timers;
    private JLabel lq;
    private JLabel la;
    private JButton save;
    private JButton add;
    private JButton del;
    private JLabel size;
    private EditTest controller;

    public EditTestView(EditTest controllerIn){
        controller=controllerIn;
        setResizable(false);
        lq=new JLabel("Введите вопрос:");
        la=new JLabel("Введите ответ:");
        JLabel lt=new JLabel("Укажите время на ответ:");
        save=new JButton("Закончить и сохранить");
        add=new JButton("Добавить вопрос");
        del=new JButton("Удалить вопрос");
        size=new JLabel("Количество вопросов: "+controller.tests.size());
        questionField=new JTextArea();
        answerField=new JTextField();
        questionField.setLineWrap(true);
        questionField.setWrapStyleWord(true);
        Integer[] items = {30,60,120,180,240};
        timers = new JComboBox(items);
        timers.setEditable(true);
        save.addActionListener(e->controller.save());
        add.addActionListener(e->controller.next());
        del.addActionListener(e->controller.delete());

        size.setBounds(350,10,150,15);
        lq.setBounds(25,25,100,25);
        lt.setBounds(25,485,200,25);
        timers.setBounds(245,485,200,25);
        questionField.setBounds(130,25,300,200);
        la.setBounds(25,250,100,25);
        answerField.setBounds(130,250,300,50);
        save.setBounds(260,400,200,30);
        add.setBounds(70,400,150,30);
        del.setBounds(70,450,150,27);
        setLayout(null);
        add(lq);
        add(del);
        add(la);
        add(lt);
        add(timers);
        add(save);
        add(add);
        add(size);
        add(questionField);
        add(answerField);
        setSize(600,550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        size.setText("Количество вопросов: "+controller.tests.size());
    }

    public String getQuestion(){
        return questionField.getText();
    }

    public String getAnswer(){
        return answerField.getText();
    }

    public Object getTimeLimit(){
        return timers.getSelectedItem();
    }

    public void clearFields(){
        questionField.setText("");
        answerField.setText("");
        size.setText("Количество вопросов: " + controller.tests.size());
    }

    public int confirm(String msg){
        int ans=JOptionPane.showConfirmDialog(null,msg);
        return ans;
    }
}
