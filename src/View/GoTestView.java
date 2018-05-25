package View;

import Controllers.GoTest;

import javax.swing.*;

public class GoTestView extends View{
    private JTextArea questionField;
    private JTextField answerField;
    private JLabel la;
    private JLabel size;
    private JButton answ;
    private JTextArea rightAnsw;
    private JLabel res;
    private JLabel num;
    private JLabel lt;
    private JButton nextQ;
    private GoTest controller;

    public GoTestView(GoTest controllerIn){
        controller=controllerIn;
        setResizable(false);

        questionField=new JTextArea();
        questionField.setEditable(false);
        answerField=new JTextField();
        JLabel time=new JLabel("Время на ответ: ");
        lt=new JLabel();
        JLabel lq=new JLabel("Вопрос");
        la=new JLabel("Ответ");
        JButton close=new JButton("Закрыть");
        size=new JLabel("");
        res=new JLabel();
        num=new JLabel();
        answ=new JButton("Ответить");
        rightAnsw=new JTextArea("");
        rightAnsw.setEnabled(false);
        rightAnsw.setEditable(false);
        nextQ=new JButton("Cледующий вопрос");

        close.addActionListener(e->{
            int i=(JOptionPane.showConfirmDialog(null,"Вы действительно хотите выйти?" +
                "\nВаша текущая оценка станет окончательной."));
            if(i==0)
            {controller.saveMark();
            dispose();}
        });

        res.setBounds(8,8,150,25);
        num.setBounds(170,8,150,25);
        rightAnsw.setBounds(50,320,500,100);
        questionField.setBounds(130,25,300,200);
        time.setBounds(490,30,120,25);
        lt.setBounds(525,50,38,25);
        lq.setBounds(25,150,100,25);
        la.setBounds(25,255,100,25);
        answerField.setBounds(130,250,300,50);
        nextQ.setBounds(100,460,180,40);
        answ.setBounds(10,460,180,40);
        nextQ.setBounds(210,460,180,40);
        size.setBounds(350,10,150,15);
        close.setBounds(420,460,120,40);

        nextQ.setEnabled(false);


        answ.addActionListener(e->controller.checkAnswer());
        nextQ.addActionListener(e->controller.nextQuestion());
        setLayout(null);
        add(close);
        add(lt);
        add(time);
        add(size);
        add(res);
        add(answ);
        add(rightAnsw);
        add(num);
        add(nextQ);
        add(answerField);
        add(la);
        add(lq);
        add(questionField);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600,550);

        size.setText("Количество вопросов: "+controller.getTestSize());
        setVisible(true);
    }

    public int substructTime(){
        int t=Integer.parseInt(lt.getText())-5;
        lt.setText(Integer.toString(t));
        return t;
    }

    public String getAnswer(){
        return answerField.getText();
    }

    public void showRightAnswer(String s){
        rightAnsw.setText(s);
    }

    public void updateFields(boolean answEnabled,boolean nextEnabled){
        res.setText("Текущая оценка: "+controller.getMark());
        answ.setEnabled(answEnabled);
        nextQ.setEnabled(nextEnabled);
    }

    public void setQuestion(){
        questionField.setLineWrap(true);
        questionField.setWrapStyleWord(true);
        questionField.setText(controller.getQuestion());
        answerField.setText("");
        rightAnsw.setText("");
        rightAnsw.setEnabled(true);
        num.setText("Номер теста: "+controller.getNumTest());
        lt.setText(Integer.toString(controller.getQuestionTime()));
        updateFields(true,false);
    }

    public void setFinButtonTitle(){
        nextQ.setText("Закончить тестирование ");
    }
}
