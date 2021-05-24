import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainScreen extends JFrame{
    private final JLabel screenTitle;
    private final JButton bookkeepingButton;
    private final JButton searchButton;
    private final JButton petButton;
    private final JPanel titlePanel;
    private final JPanel bookkeepingPanel;
    private final JPanel searchPanel;
    private final JPanel petButtonPanel;
    public mainScreen(){
        super("記帳程式");
        setLayout(new GridLayout(4,1,10,10));
        screenTitle = new JLabel("計帳程式");
        bookkeepingButton = new JButton("記帳");
        searchButton = new JButton("查詢帳本");
        petButton = new JButton("寵物養成");
        titlePanel = new JPanel(new BorderLayout());
        bookkeepingPanel = new JPanel(new BorderLayout());
        searchPanel = new JPanel(new BorderLayout());
        petButtonPanel = new JPanel(new BorderLayout());
        bookkeepingButton.setSize(5,5);
        titlePanel.add(screenTitle,BorderLayout.CENTER);
        bookkeepingPanel.add(bookkeepingButton,BorderLayout.CENTER);
        searchPanel.add(searchButton,BorderLayout.CENTER);
        petButtonPanel.add(petButton,BorderLayout.CENTER);
        titlePanel.setPreferredSize(new Dimension(50,50));
        add(titlePanel);
        add(bookkeepingPanel);
        add(searchPanel);
        add(petButtonPanel);
        revalidate();
    }
}