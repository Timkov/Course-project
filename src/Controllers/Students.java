package Controllers;

import Model.Student;
import View.StudentsView;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


public class Students  {
    private StudentsView view;

    public Students(){
        view=new StudentsView(this);
        showStat();
    }

    public void getFullStat(){
        String[] possibleValues=new String[Main.students.size()];
        for(int i=0;i<Main.students.size();i++)
            possibleValues[i]=Main.students.get(i).getName();
        if(possibleValues.length==0)
            return;

        String name=(String) view.requestSelection(possibleValues,"Выберите студента");

        if(name==null) return;
        for(Student s: Main.students)
            if(s.getName().equals(name)){
                s.showStat();
                return;
            }
        view.showMessage("Студент с таким именем отсутствует в " +
                "списке участников курса.",1);
    }

    public void showStat(){
        ArrayList<Student> st=Main.students;
        StringBuilder sb=new StringBuilder("");
        sb.append("   Имя, фамилия   Время обучения,мин   Пройдено лекций   Итоговая оценка\n");
        for(Student s:st)
            sb.append(s.toString()+" \n ");
        view.setList(sb.toString());
    }

    public void addStud(){
        String name=view.requestInput("Введите фамилию и имя студента");
        if(name==null) return;
        String password=JOptionPane.showInputDialog("Введите пароль студента");
        if(password.length()<4 || password==null){
            view.showMessage("Длина пароля - менее четырех символов. ",1);
            return;
        }

        File st=new File(AdminMenu.regPath+"\\"+name);
        if(!st.exists())
            try{st.createNewFile();}catch (Exception e){e.printStackTrace();}
        else{
            view.showMessage("Студент с таким именем уже присутствует в " +
                    "списке участников курса.",1);
            return;
        }

        Main.dataAccess.saveStudent(new Student(name,password));

        view.showMessage("Студент "+name+" успешно добавлен к курсу",0);
        Main.load();
        view.dispose();
        new Students();
    }

    public void delStud(){
        String name=view.requestInput("Введите фамилию и имя студента");
        if(name==null) return;
        boolean find=false;
        String []stud=new File(AdminMenu.regPath).list();
        for(String s:stud)
            if(s.equals(name)) {
                view.showMessage("Студент "+name+" удален из списка участников курса",0);
                new File(AdminMenu.regPath+"\\"+s).delete();
                find=true;
            }

        if(!find)
            view.showMessage("Студент "+name+" отсутствует в списке участников курса.",1);
        Main.load();
        view.dispose();
        new Students();
    }
}
