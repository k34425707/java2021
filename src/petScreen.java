import javax.swing.*;
import java.awt.*;

public class petScreen extends JFrame{
    private final JButton backButton;

    public petScreen(){
        super("寵物系統");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backButton = new JButton("返回");
        backButton.setBounds(0,0,100,80);
        add(backButton);
    }

    public JButton getBackButton(){
        return backButton;
    }
}
