package Model;

import Controllers.Main;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;


public class Student implements Serializable {
    private String name;
    private String password;
    private long time;
    private static final long serialVersionUID = 1113799434508676095L;
    public ArrayList<Integer> marks;

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
        marks=new ArrayList<>(Main.numTests);
        addMarkFields();
        time=0;
    }

    public void addMarkFields(){
        int t=marks.size();
        for(int i=t-1;i<2*Main.numTests+1;i++) marks.add(-1);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String toString() {
        float total=0;
        int count=0;
        for(Integer i:marks) if(i!=-1){ total+=i; count++;}
        total=total/Main.numTests;
        String s=String.format("%-23s     \t%4d\t%2d/%3d\t%4.2f/100",name,time,count,Main.numTests,total);
        return s;
    }

    public void showStat(){
        Integer total=0,count=0;
        for(Integer i:marks)if(i!=-1){ total+=i; count++;}
            StringBuilder sb=new StringBuilder("");
        sb.append("Имя, фамилия: "+name);
        sb.append("\nВремя учебы: "+time);
        sb.append("\nПройденно лекций: "+count);
        sb.append("\nИтоговая оценка: "+total/Main.numTests);
        sb.append("\nОценки за каждую лекцию: ");
        for (int i=0;i<Main.numTests;i++)
            sb.append("\n  "+Main.lections.get(i)+":   "+(marks.get(i)==-1?"Нет оценки":marks.get(i)));
        JOptionPane.showMessageDialog(null,sb.toString(),
                "Результаты",JOptionPane.INFORMATION_MESSAGE);
    }
}
