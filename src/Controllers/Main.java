package Controllers;

import Model.DataAccess;
import Model.Student;
import View.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    private final String adminFile="adminFile.txt";
    private MainView view;
    public static DataAccess dataAccess;
    public static ArrayList<Student> students=new ArrayList<>();
    public static ArrayList<String>lections;
    public static int numTests;

    public Main(){
        dataAccess=new DataAccess();
        load();
        view=new MainView(this);
    }

    public static void load(){
        String []file=new File(AdminMenu.regPath).list();
        students=new ArrayList<>();
        for(String s:file){
            students.add(Main.dataAccess.readStudent(AdminMenu.regPath+"\\"+s));
        }
        lections=new ArrayList<>(Arrays.asList(new File(AdminMenu.matPath).list()));
        if(lections.size()!=numTests){
            for(Student s:students)
                s.addMarkFields();
            numTests=lections.size();
        }

    }

    public void checkAdmin(){
        String password=view.requestInput("Введите пароль администратора ");

        String s=Main.dataAccess.readPart(adminFile);

        if(!("admin"+" "+password).equals(s)){
            view.showMessage("Неверный пароль",1);
        } else{
            view.dispose();
            new AdminMenu();
        }
    }

    public static void main(String... args){
        new Main();
    }
}
