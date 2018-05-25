package Model;

import Controllers.AdminMenu;

import java.io.*;
import java.util.ArrayList;

public class DataAccess {

    public  ArrayList<Test> loadTests(String lection){
        File file=new File(AdminMenu.matPath+"\\"+lection+"\\test");
        ArrayList<Test> tests=new ArrayList<>();

        if(!file.exists())
            return tests;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            tests=(ArrayList<Test>)ois.readObject();
        }
        catch(Exception ex){ex.printStackTrace();}
        return tests;
    }

    public  void saveTests(String lection,ArrayList<Test> tests){
        File file=new File(AdminMenu.matPath+"\\"+lection+"\\test");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(tests);
        }catch (IOException ex){ex.printStackTrace();}
    }

    public  String readPart(String fileName){
        File file=new File(fileName);
        StringBuilder result=new StringBuilder("");
        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null)
                result.append(line);
        }catch (IOException e){e.printStackTrace();}
        return result.toString();
    }

    public  void writePart(String fileName,String text){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Student readStudent(String s){
        Student student=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(s)))
        {
            student=(Student) ois.readObject();
        }
        catch(Exception ex){ex.printStackTrace();}
        return student;
    }

    public  void saveStudent(Student student){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AdminMenu.regPath+"\\"+student.getName())))
        {
            oos.writeObject(student);
        }catch (IOException ex){ex.printStackTrace();}
    }
}
