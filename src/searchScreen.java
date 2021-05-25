import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchScreen extends JFrame{
    private final JButton backButton;

    public searchScreen(){
        super("記帳程式");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backButton = new JButton("返回");
        add(backButton,BorderLayout.LINE_END);
        setSize(500,600);
    }

    public JButton getBackButton(){
        return backButton;
    }

}
