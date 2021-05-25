import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainScreen extends JFrame{
    //private final JLabel screenTitle;
    //private final JPanel cards;
    private JLabel space;
    private JLabel space2;
    private JLabel space3;
    private JLabel space4;
    private JLabel space5;
    private JLabel space6;
    private JLabel space7;
    private JLabel space8;
    private JLabel space9;
    private JLabel space10;
    private JLabel space11;
    private JLabel space12;
    private final bookkeepingScreen bookkeepingFrame = new bookkeepingScreen();
    private final searchScreen searchFrame = new searchScreen();
    private final petScreen petFrame = new petScreen();
    private final JButton bookkeepingButton;
    private final JButton searchButton;
    private final JButton petButton;
    public mainScreen(){
        super("記帳程式");
        //設定主畫面的大小和出現位置還有排版
        setSize(798,600);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - mainScreen.this.getWidth() / 2;
        int y = (int) screensize.getHeight() / 2 - mainScreen.this.getHeight() / 2;
        setLocation(x,y);
        setLayout(new GridLayout(5,3,0,40));
        space = new JLabel("",JLabel.CENTER);
        space2 = new JLabel("",JLabel.CENTER);
        space3 = new JLabel("",JLabel.CENTER);
        space4 = new JLabel("",JLabel.CENTER);
        space5 = new JLabel("",JLabel.CENTER);
        space6 = new JLabel("",JLabel.CENTER);
        space7 = new JLabel("",JLabel.CENTER);
        space8 = new JLabel("",JLabel.CENTER);
        space9 = new JLabel("",JLabel.CENTER);
        space10 = new JLabel("",JLabel.CENTER);
        space11 = new JLabel("",JLabel.CENTER);
        space12 = new JLabel("",JLabel.CENTER);
        //創建按鈕
        bookkeepingButton = new JButton("記帳");
        searchButton = new JButton("查詢帳本");
        petButton = new JButton("寵物養成");
        //設定主畫面
        add(space);
        add(space2);
        add(space3);
        add(space4);
        add(bookkeepingButton);
        add(space5);
        add(space6);
        add(searchButton);
        add(space7);
        add(space8);
        add(petButton);
        add(space9);
        add(space10);
        /*cards = new JPanel(new CardLayout());
        cards.add(bookkeepingFrame);
        cards.add(searchFrame);
        cards.add(petFrame);
        add(cards);*/
        bookkeepingFrame.setSize(mainScreen.this.getSize());
        searchFrame.setSize(mainScreen.this.getSize());
        petFrame.setSize(mainScreen.this.getSize());
        //Buttons regist
        ButtonHandler buttonHandler = new ButtonHandler();
        bookkeepingButton.addActionListener(buttonHandler);
        searchButton.addActionListener(buttonHandler);
        petButton.addActionListener(buttonHandler);
        bookkeepingFrame.getBackButton().addActionListener(buttonHandler);
        searchFrame.getBackButton().addActionListener(buttonHandler);
        petFrame.getBackButton().addActionListener(buttonHandler);
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == bookkeepingButton)
            {
                bookkeepingFrame.setLocation(mainScreen.this.getX(),mainScreen.this.getY());
                bookkeepingFrame.setVisible(true);
                mainScreen.this.setVisible(false);
            }
            else if(event.getSource() == searchButton)
            {
                searchFrame.setLocation(mainScreen.this.getX(),mainScreen.this.getY());
                searchFrame.setVisible(true);
                mainScreen.this.setVisible(false);
            }
            else if(event.getSource() == petButton)
            {
                petFrame.setLocation(mainScreen.this.getX(),mainScreen.this.getY());
                petFrame.setVisible(true);
                mainScreen.this.setVisible(false);
            }
            else if (event.getSource() == bookkeepingFrame.getBackButton())
            {
                bookkeepingFrame.setVisible(false);
                mainScreen.this.setVisible(true);
            }
            else if(event.getSource() == searchFrame.getBackButton())
            {
                searchFrame.setVisible(false);
                mainScreen.this.setVisible(true);
            }
            else if(event.getSource() == petFrame.getBackButton())
            {
                petFrame.setVisible(false);
                mainScreen.this.setVisible(true);
            }
        }
    }
}
