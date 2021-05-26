import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookkeepingScreen extends JFrame{
    private final JPanel bookkeepingPanel;
    private final JButton backButton;
    private final JButton bokkeepingButton;
    private final JPanel checkboxPanel;
    private final JPanel checkboxPanel2;
    private final ButtonGroup checkboxGroup;
    private final JRadioButton outNumber;
    private final JRadioButton inNumber;
    private final JTextField dateTextField;
    private final JTextField moneyTextField;
    private final JPanel typePanel;
    private final JTextField typeTextField;
    private final String[] options = { "早餐","午餐","晚餐","其他"};
    private final JComboBox typeComboBox;
    private final JScrollPane recordPane;
    private final JTextArea recordArea;
    public bookkeepingScreen(){
        super("記帳程式");
        setLayout(new GridLayout(1,2));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        bookkeepingPanel = new JPanel(new GridLayout(6,1));
        backButton = new JButton("返回");
        backButton.setHorizontalAlignment(JButton.LEFT);
        backButton.setOpaque(false);
        bokkeepingButton = new JButton("記帳");
        checkboxPanel = new JPanel(new BorderLayout());
        checkboxPanel2 = new JPanel();
        checkboxGroup = new ButtonGroup();
        outNumber = new JRadioButton("支出",true);
        inNumber = new JRadioButton("收入",false);
        checkboxGroup.add(outNumber);
        checkboxGroup.add(inNumber);
        checkboxPanel2.add(outNumber);
        checkboxPanel2.add(inNumber);
        checkboxPanel.add(checkboxPanel2,BorderLayout.SOUTH);
        dateTextField = new JTextField("請輸入年月日(例108/05/23)");
        moneyTextField = new JTextField("請輸入金錢");
        typePanel = new JPanel(new GridLayout(2,1));
        typeTextField = new JTextField("請輸入類型");
        typeComboBox = new JComboBox(options);
        typePanel.add(typeTextField);
        typePanel.add(typeComboBox);
        recordArea = new JTextArea("");
        recordPane = new JScrollPane(recordArea);
        bookkeepingPanel.add(backButton);
        bookkeepingPanel.add(checkboxPanel);
        bookkeepingPanel.add(dateTextField);
        bookkeepingPanel.add(moneyTextField);
        bookkeepingPanel.add(typePanel);
        bookkeepingPanel.add(bokkeepingButton);
        add(bookkeepingPanel);
        add(recordPane);
        bokkeepingButton.addActionListener(new ButtonHandler());
    }

    public JButton getBackButton(){
        return backButton;
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == bokkeepingButton)
            {
                recordArea.append(String.format("%s %s %s%n",dateTextField.getText(),moneyTextField.getText(),typeTextField.getText()));
                recordArea.setCaretPosition(recordArea.getDocument().getLength());
            }
        }
    }
}
