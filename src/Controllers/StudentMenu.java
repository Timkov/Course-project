package Controllers;

import Model.Student;
import View.StudentMenuView;

import java.io.File;

public class StudentMenu {
    public final Student user;
    public String[]materials;
    private long start;
    private StudentMenuView view;

    public StudentMenu(Student user){
        this.user=user;
        start= System.currentTimeMillis();
        materials=new File(AdminMenu.matPath).list();
        view=new StudentMenuView(this);
    }

    public void showResults(){
        user.showStat();
    }

    public void saveAndExit(){
        long fin=System.currentTimeMillis();
        long work=fin-start;
        user.setTime(user.getTime()+work/60000);
        Main.dataAccess.saveStudent(user);
        System.exit(0);
    }

    public void openItem(int index){
        new Items(materials[index],user);
        view.dispose();
    }
}

