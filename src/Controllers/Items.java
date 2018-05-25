package Controllers;

import Model.Student;
import View.ItemsView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Items {

    private ItemsView view;
    protected String curPart;
    protected File path;
    public Student user;
    public String curItem;
    public String[]material;

    Items(String lection, Student user){
        this.user=user;
        curItem=lection;
        path=new File(AdminMenu.matPath+"\\"+lection);
        material=path.list();

        int t=0;
        ArrayList<String> a= new ArrayList<>(Arrays.asList(material));
        for(int i=0;i<material.length;i++)                      //removing test from list
            if("test".equals(material[i]))
                t=i;
        if(t!=0) a.remove(t);
        material=new String[a.size()];
        for(int i=0;i<a.size();i++)
            material[i]=a.get(i);
        if(!user.getName().equals("admin"))view=new ItemsView(this);
    }

    public String read(int index){
        String fileName=material[index];
        curPart=fileName;
        return Main.dataAccess.readPart(AdminMenu.matPath+"\\"+curItem+"\\"+fileName);
    }

    public void startTest(){
        if(user.marks.get(Main.lections.indexOf(curItem))!=-1){
            view.showMessage("Вы уже проходили этот тест ",1);
            return;
        }
        else
            view.dispose();new GoTest(curItem,user);
        }
}
