import javax.swing.*;
import java.awt.*;

public class petScreen extends JFrame{
    private final JButton backButton;

    public petScreen(){
        super("記帳程式");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backButton = new JButton("返回");
        add(backButton, BorderLayout.AFTER_LAST_LINE);
        setSize(500,600);
    }

    public JButton getBackButton(){
        return backButton;
    }
}
