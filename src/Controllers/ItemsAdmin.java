package Controllers;

import Model.DataAccess;
import Model.Student;
import View.ItemsAdminView;


import java.io.*;


public class ItemsAdmin extends Items {
    private ItemsAdminView itemsView;
    public ItemsAdmin(String file){
        super(file,new Student("admin",""));
        itemsView=new ItemsAdminView(this);
    }

    public void save(){
        if(curPart!=null) {
            Main.dataAccess.writePart(AdminMenu.matPath + "\\" + curItem + "\\" + curPart,itemsView.getText());
            itemsView.showMessage("Изменения сохранены ",0);
        }
        else return;
    }

    public void addPart(){
        String name= itemsView.requestPartName();
        File f=new File(AdminMenu.matPath+"\\"+curItem+"\\"+name);

        if(name==null || name.length()==0)
            return;
        if(f.exists()){
            itemsView.showMessage("Тема уже существует ",0);
            return;
        }


        try{f.createNewFile(); }catch (Exception e){e.printStackTrace();}

        String buf=curItem;

        itemsView.dispose();
        new ItemsAdmin(buf);
    }

    public void addFile(File f){
        if(f==null || !f.getName().substring(f.getName().length()-3).equals("txt")){
            itemsView.showMessage("Неверный тип файла ",1);
            return;
        }

        String textI= Main.dataAccess.readPart(f.getAbsolutePath());


        File g=new File(AdminMenu.matPath+"\\"+curItem+"\\"+f.getName());
        if(g.exists()){
            itemsView.showMessage("Тема уже существует ",1);
            return;
        }
        try{g.createNewFile(); }catch (Exception e){e.printStackTrace();}
        curPart=f.getName();
        if(curPart!=null ) {
            Main.dataAccess.writePart(AdminMenu.matPath + "\\" + curItem + "\\" + curPart,textI);
            itemsView.showMessage("Изменения сохранены ",0);
            itemsView.dispose(); new ItemsAdmin(curItem);
        }

    }

}
