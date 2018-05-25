package View;

import Controllers.AdminMenu;
import Controllers.EditTest;
import Controllers.ItemsAdmin;

import javax.swing.*;

public class ItemsAdminView extends ItemsView {
    private ChooseFileFrame chooseFileFrame;
    private ItemsAdmin itemsController;
    public ItemsAdminView(ItemsAdmin controllerIn){
        super(controllerIn);
        itemsController=controllerIn;
        setResizable(false);
        remove(back);
        JButton addPart=new JButton("Добавить порцию материала");
        JButton editTest=new JButton("Редактировать контрольный тест ");

        JButton back1=new JButton("Назад");
        text.setEditable(true);
        JButton save=new JButton("Сохранить изменения");
        JButton addFile=new JButton("Добавить файл с материалом");

        save.addActionListener(e->itemsController.save());
        addPart.addActionListener(e->itemsController.addPart());
        addFile.addActionListener(e->{chooseFileFrame=new ChooseFileFrame(this);});
        editTest.addActionListener(e->{dispose();new EditTest(itemsController.curItem);});
        back1.addActionListener(e->{dispose();new AdminMenu();});
        addFile.setBounds(20,490,250,35);
        addPart.setBounds(20,455,250,35);
        editTest.setBounds(20,420,250,35);
        save.setBounds(550,520,230,35);
        back1.setBounds(800,520,75,35);
        remove(test);
        add(addPart);
        add(addFile);
        add(editTest);
        add(save);
        add(back1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public String requestPartName(){
        String name=JOptionPane.showInputDialog("Введите имя порции материала: ");
        return name;
    }

    class ChooseFileFrame extends JFrame{
        public JFileChooser chooser=new JFileChooser();
        ChooseFileFrame(JFrame parent){
            super();
            JFrame frame=new JFrame();
            chooser.addActionListener(e->{if (e.getActionCommand().equals("ApproveSelection"))
            {itemsController.addFile(chooser.getSelectedFile()); frame.dispose();}
            else frame.dispose();});
            frame.add(chooser);
            frame.pack();
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}
