package Controllers;

import View.AdminMenuView;

import java.io.File;


public class AdminMenu {
    public static final String regPath="Students";
    public static final String matPath="Materials";
    private AdminMenuView view;
    String[]materials;


    public AdminMenu(){
        view=new AdminMenuView(this);
        buildList();
    }

    public void addItem(){
        String name=view.requestInput("Введите название темы");
        if(name==null || name.length()<1 || name.length()>100){
            view.showMessage("Неверный ввод названия темы ",1);
            return;}
        for(String s:materials)
            if(s.equals(name)){
                view.showMessage("Тема уже существует ",1);
                return;
            }
        if(name==null || name.length()==0)
            return;
        File folder=new File(matPath+"\\"+name);
        if (!folder.exists()) {
            folder.mkdir();
             }
        view.dispose();
        Main.load();
        new AdminMenu();
    }

    public void buildList(){
        materials=new File(matPath).list();
        view.setList(materials);
    }
}
