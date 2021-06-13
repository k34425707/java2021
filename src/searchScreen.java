import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchScreen extends JFrame{
    private final JButton backButton;
    private final JTextField startDateTextField;
    private final JTextField endDateTextField;
    private final JLabel label1;
    private final JLabel totalExpenditureLabel;
    private final JLabel totalIncomeLabel;
    private final JButton searchYearButton;
    private final JButton searchMonthButton;
    private final JButton searchTargetTimeButton;
    private final JTextArea showInfoTextArea;
    private final JScrollPane showInfoScrollPane;

    public searchScreen(){
        super("查詢系統");
        //設定視窗和排版
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化各個元件
        backButton = new JButton("返回");

        startDateTextField = new JTextField("請輸入開始年月日(例108/05/26)");
        endDateTextField = new JTextField("請輸入結束年月日(例108/12/23)");

        label1 = new JLabel("  ~  ");
        totalExpenditureLabel = new JLabel("總支出:");
        totalIncomeLabel = new JLabel("總收入: ");

        searchYearButton = new JButton("年資料查詢");
        searchMonthButton = new JButton("月資料查詢");
        searchTargetTimeButton = new JButton("查詢資料");

        showInfoTextArea = new JTextArea("");
        showInfoScrollPane = new JScrollPane(showInfoTextArea);
        //設定字體
        label1.setFont(new Font("~",Font.BOLD,30));
        totalExpenditureLabel.setFont(new Font("總支出:  ",3,50));
        totalIncomeLabel.setFont(new Font("總收入:  ",3,50));
        //設定元件位置
        backButton.setBounds(0,0,100,80);
        startDateTextField.setBounds(310,0,180,80);
        label1.setBounds(startDateTextField.getX() + 210,startDateTextField.getY(),50,80);
        endDateTextField.setBounds(602,0,180,80);//602 //// 550 200
        searchYearButton.setBounds(481,150,100,80);
        searchMonthButton.setBounds(581,150,100,80);
        searchTargetTimeButton.setBounds(681,150,100,80);
        showInfoScrollPane.setBounds(0,230,783,200);
        totalExpenditureLabel.setBounds(0,435,200,60);
        totalIncomeLabel.setBounds(0,495,200,65);
        //System.out.println(searchYearButton.getSize());
        //加到畫面上
        add(backButton);
        add(startDateTextField);
        add(label1);
        add(endDateTextField);
        add(searchYearButton);
        add(searchMonthButton);
        add(searchTargetTimeButton);
        add(showInfoScrollPane);
        add(totalExpenditureLabel);
        add(totalIncomeLabel);
    }

    public JButton getBackButton(){
        return backButton;
    }

}
