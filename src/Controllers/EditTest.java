package Controllers;

import Model.Test;
import View.EditTestView;

import java.util.ArrayList;


public class EditTest{
    private final String lection;
    public ArrayList<Test> tests=new ArrayList<>();
    private EditTestView view;

    public EditTest(String lection){
        this.lection=lection;
        tests= Main.dataAccess.loadTests(lection);
        view=new EditTestView(this);
    }

    public void save(){
        if(tests.size()==0){
            view.showMessage("В тесте отсутствуют вопросы, тест не будет сохранен. ",1);
            view.dispose();
            new ItemsAdmin(lection);
            return;
        }

        Main.dataAccess.saveTests(lection,tests);

        view.showMessage("Контрольный тест сохранен ",0);
        view.dispose();
        new ItemsAdmin(lection);
    }

    public void next() {
        String q = view.getQuestion();
        String a = view.getAnswer();
        if (q.length() == 0 || q.length() > 2000 || a.length() == 0 || a.length() > 200) {
            view.showMessage("Неверный ввод данных ",1);
            return;
        }
        tests.add(new Test(q, a,(int)view.getTimeLimit()));

        view.clearFields();

    }

    public void delete(){
        if(tests.size()==0)
            return;
        Integer[] values=new Integer[tests.size()];
        String [] possibleValues=new String[tests.size()];
        for(int i=0;i<values.length;i++){
            values[i]=i+1;
            possibleValues[i]=Integer.toString(values[i]);
        }

        Object s =  view.requestSelection(possibleValues,"Выберите номер вопроса для удаления");
        Integer selectedValue;
        if(s==null)
            return;
        selectedValue=Integer.parseInt((String)s);
        int conf=view.confirm("Подтвердите удаление вопроса №"+ selectedValue +"\nВопрос: "+
                tests.get(selectedValue-1).getQuestion()+"\nОтвет: "+tests.get(selectedValue-1).getAnswer());
        if(conf==0)
            tests.remove(selectedValue-1);
        view.clearFields();
    }

}
