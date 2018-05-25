package Controllers;

import Model.Student;
import View.StudentLoginView;

public class StudentLogin  {
    private Student user;
    private StudentLoginView view;

    public StudentLogin() {
        view=new StudentLoginView(this);
    }

    public void login() {
        String nameIn = view.getInputName();
        boolean isCorrect=false;
        int status = find();
        if (status == 0) {
            view.showMessage("Ваше имя отсутствует в списке участников курса." +
                    "Обратитесь к преподавателю.",1);
        }
        if (status == 1) {
            view.showMessage("Неверный пароль.",1);
        }
        if (status == 2) {
            view.showMessage("Вы вошли под именем " + nameIn,0);
            isCorrect=true;
        }
        if(isCorrect){
            view.dispose();
            new StudentMenu(user);
        }
    }

    private int find() {

        int isReg = 0;

        for (Student st : Main.students)
            if (st.getName().equals(view.getInputName())) {
                isReg = 1;
                user = st;
            }

        if (isReg == 1) {
            if (user.getPassword().equals(view.getPassword()))
                isReg = 2;
        }
        return isReg;
    }
}



