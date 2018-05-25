package Controllers;

import Model.DataAccess;
import Model.Student;
import Model.Test;
import View.GoTestView;

import javax.swing.*;
import java.util.ArrayList;


public class GoTest  {
    private final String lection;
    private Student user;
    private ArrayList<Test> tests;
    private float mark=0;
    private int numTest=-1;
    private GoTestView view;
    private Timer timer;

    GoTest(String lection, Student user){
        this.lection=lection;
        this.user=user;

        tests= Main.dataAccess.loadTests(lection);
        view=new GoTestView(this);

        timer=new Timer(5000,e->{
            int t=view.substructTime();
            if(t<=0){
                view.showMessage("Время на ответ вышло.\n",0);
                checkAnswer();
                timer.stop();
            }
        });

        nextQuestion();
    }

    public void checkAnswer(){
        timer.stop();
        String a=view.getAnswer();
        if(a.length()<0 || a==null){
            view.showRightAnswer("Неверный формат ввода ответа.\n Повторите попытку");
            return;
        }
        if(tests.get(numTest).getAnswer().toLowerCase().equals(a.toLowerCase())){
            view.showRightAnswer("Вы ответили верно. ");
            mark++;
        }
        else
            view.showRightAnswer("Вы ответили неверно. \n Правильный ответ:\n"+tests.get(numTest).getAnswer());
        view.updateFields(false,true);
    }

    public void nextQuestion(){
        view.updateFields(false,false);
        if(numTest==tests.size()-2)
            view.setFinButtonTitle();
        if(numTest>=tests.size()-1){
            view.showMessage("Контрольный тест пройден.\nВаша оценка "+mark+"/"+(Integer)(1+numTest),0);
            saveMark();
            return;
        }
        numTest++;
        view.setQuestion();
        timer.restart();
    }

    public void saveMark(){
        Integer i= Main.lections.indexOf(lection);
        user.marks.set(i,(int)(mark*100/(1+numTest)));
        view.dispose();
        new Items(lection,user);
        return;
    }

    public float getMark() {
        return mark;
    }

    public int getQuestionTime(){
        return tests.get(numTest).getTime();
    }

    public String getQuestion(){
        return tests.get(numTest).getQuestion();
    }

    public int getNumTest(){
        return numTest+1;
    }

    public int getTestSize(){
        return tests.size();
    }
}
