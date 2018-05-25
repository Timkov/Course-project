package View;


import javax.swing.*;

public abstract class View extends JFrame {
    public  void showMessage(String msg,int type){
        JOptionPane.showMessageDialog(null, msg, "OK"
                , (type==0?JOptionPane.INFORMATION_MESSAGE:JOptionPane.WARNING_MESSAGE));
    }

    public  String requestInput(String s){
        String name=JOptionPane.showInputDialog(s);
        return name;
    }

    public  Object requestSelection(String[]possibleValues,String title ){
        Object select = JOptionPane.showInputDialog(null,
                title, "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        return select;
    }
}
