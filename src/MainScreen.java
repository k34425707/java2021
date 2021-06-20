import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame{
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
    //另外三個介面
    private final BookkeepingScreen bookkeepingFrame = new BookkeepingScreen();
    private final SearchScreen searchFrame = new SearchScreen();
    private final PetScreen petFrame = new PetScreen();
    //主畫面的三個按鈕
    private final JButton bookkeepingButton;
    private final JButton searchButton;
    private final JButton petButton;

    public MainScreen(){
        super("記帳程式");
        //設定主畫面的大小和出現位置還有排版
        setResizable(false);
        ((JPanel)this.getContentPane()).setOpaque(false);
        setSize(798,600);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - MainScreen.this.getWidth() / 2;
        int y = (int) screensize.getHeight() / 2 - MainScreen.this.getHeight() / 2;
        setLocation(x,y);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        /*setLayout(new GridLayout(5,3,0,40));
        setBackground(Color.blue);
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
        space12 = new JLabel("",JLabel.CENTER);*/
        //創建按鈕
        bookkeepingButton = new JButton("記帳");
        searchButton = new JButton("查詢帳本");
        petButton = new JButton("寵物養成");
        //設定位置
        bookkeepingButton.setBounds(242,80,300,80);
        searchButton.setBounds(242,230,300,80);
        petButton.setBounds(242,380,300,80);
        //設定主畫面
        /*add(space);
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
        add(space10);*/
        add(bookkeepingButton);
        add(searchButton);
        add(petButton);
        //設定其他畫面的大小
        bookkeepingFrame.setSize(MainScreen.this.getSize());
        searchFrame.setSize(MainScreen.this.getSize());
        petFrame.setSize(MainScreen.this.getSize());
        //Buttons regist
        ButtonHandler buttonHandler = new ButtonHandler();
        bookkeepingButton.addActionListener(buttonHandler);
        searchButton.addActionListener(buttonHandler);
        petButton.addActionListener(buttonHandler);
        bookkeepingFrame.getBackButton().addActionListener(buttonHandler);
        searchFrame.getBackButton().addActionListener(buttonHandler);
        petFrame.getBackButton().addActionListener(buttonHandler);
        bookkeepingFrame.getBokkeepingButton().addActionListener(buttonHandler);
    }

    //按鈕的事件處理器
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == bookkeepingButton)
            {
                bookkeepingFrame.setLocation(MainScreen.this.getX(), MainScreen.this.getY());
                bookkeepingFrame.setVisible(true);
                MainScreen.this.setVisible(false);
            }
            else if(event.getSource() == searchButton)
            {
                searchFrame.setLocation(MainScreen.this.getX(), MainScreen.this.getY());
                searchFrame.setVisible(true);
                MainScreen.this.setVisible(false);
            }
            else if(event.getSource() == petButton)
            {
                petFrame.setLocation(MainScreen.this.getX(), MainScreen.this.getY());
                petFrame.setVisible(true);
                MainScreen.this.setVisible(false);
            }
            else if (event.getSource() == bookkeepingFrame.getBackButton())
            {
                bookkeepingFrame.setVisible(false);
                MainScreen.this.setVisible(true);
            }
            else if(event.getSource() == searchFrame.getBackButton())
            {
                searchFrame.setVisible(false);
                MainScreen.this.setVisible(true);
            }
            else if(event.getSource() == petFrame.getBackButton())
            {
                petFrame.setVisible(false);
                MainScreen.this.setVisible(true);
            }
            else if(event.getSource() == bookkeepingFrame.getBokkeepingButton())
            {
                try {
                    int m = Integer.parseInt(bookkeepingFrame.getMoneyTextField().getText());
                    System.out.println("記了一筆帳:" + m);
                    petFrame.getMyPetDog().myBag.setMoney(petFrame.getMyPetDog().myBag.getMoney() + 50);
                    petFrame.getUserMoney().setText("目前金錢 $:" + petFrame.getMyPetDog().myBag.getMoney());
                    petFrame.getMyPetDog().writeBagDataCsv();
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("記帳失敗!");
                }
            }
        }
    }
}
