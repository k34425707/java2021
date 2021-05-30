import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class petScreen extends JFrame{
    private final JButton backButton;
    private final JPanel healthBarPanel;
    private final JProgressBar healthBar;
    private final JPanel thirstBarPanel;
    private final JProgressBar thirstBar;
    private int health;
    private int thirst;
    private final JTextArea stateTextArea;
    private final JScrollPane stateScrollPane;
    private final JLabel healthLabel;
    private final JLabel thirstLabel;
    private final JLabel userMoney;
    private final JButton useButton;
    private final JButton buyButton;
   // private final JScrollPane userListScrollPane;
    private final JScrollPane storeListScrollPane;
    //private final JList userList;
    private final JList storeList;
    private ArrayList<String> userBag;
    private String[] commodities = {"水","口罩","食物","狗骨頭"};

    public petScreen(){
        super("寵物系統");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backButton = new JButton("返回");

        healthBarPanel = new JPanel();
        healthBarPanel.setBounds(150,15,200,30);
        healthBar = new JProgressBar(0,100);
        healthBar.setPreferredSize(new Dimension(200,30));
        healthBar.setForeground(Color.red);
        healthBar.setValue(100);
        healthBarPanel.add(healthBar);

        thirstBarPanel = new JPanel();
        thirstBarPanel.setBounds(150,60,200,30);
        thirstBar = new JProgressBar(0,100);
        thirstBar.setPreferredSize(new Dimension(200,30));
        thirstBar.setForeground(Color.blue);
        thirstBar.setValue(100);
        thirstBarPanel.add(thirstBar);

        stateTextArea = new JTextArea();
        stateScrollPane = new JScrollPane(stateTextArea);
        stateScrollPane.setBounds(450,100,250,300);

        healthLabel = new JLabel("生命值 100/100");
        thirstLabel = new JLabel("口渴度 100/100");
        userMoney = new JLabel("目前金錢 $:0");
        healthLabel.setFont(new Font("生命值",Font.BOLD + Font.ITALIC,25));
        thirstLabel.setFont(new Font("口渴值",Font.BOLD + Font.ITALIC,25));
        userMoney.setFont(new Font("金錢",Font.ITALIC,25));
        healthLabel.setBounds(365,6,250,50);
        thirstLabel.setBounds(365,50,250,50);
        userMoney.setBounds(550,500,150,50);

        useButton = new JButton("使用");
        buyButton = new JButton("購買");
        useButton.setBounds(50,500,100,50);
        buyButton.setBounds(450,500,100,50);

        storeList = new JList(commodities);
        storeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        storeList.setLayoutOrientation(JList.VERTICAL);
        storeList.setVisibleRowCount(-1);
        storeListScrollPane = new JScrollPane(storeList);
        storeList.setBounds(50,400,650,100);
        storeList.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        backButton.setBounds(0,0,100,80);

        add(backButton);
        add(healthBarPanel);
        add(thirstBarPanel);
        add(healthLabel);
        add(thirstLabel);
        add(stateScrollPane);
        add(userMoney);
        add(storeList);
        add(useButton);
        add(buyButton);
    }

    public JButton getBackButton(){
        return backButton;
    }
}
