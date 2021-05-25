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
    private final JCheckBox outNumber;
    private final JCheckBox inNumber;
    private final JTextField dateTextField;
    private final JTextField moneyTextField;
    private final JScrollBar typeScrollBar;
    private final JTextField test;
    public bookkeepingScreen(){
        super("記帳程式");
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        //
        bookkeepingPanel = new JPanel(new GridLayout(6,1));
        backButton = new JButton("返回");
        bokkeepingButton = new JButton("記帳");
        checkboxPanel = new JPanel(new BorderLayout());
        checkboxPanel2 = new JPanel();
        outNumber = new JCheckBox("支出",true);
        inNumber = new JCheckBox("收入",false);
        checkboxPanel2.add(outNumber);
        checkboxPanel2.add(inNumber);
        checkboxPanel.add(checkboxPanel2,BorderLayout.SOUTH);
        dateTextField = new JTextField("請輸入年月日(例108/05/23)");
        moneyTextField = new JTextField("請輸入金錢");
        typeScrollBar = new JScrollBar();
        test = new JTextField("test");
        bookkeepingPanel.add(backButton);
        bookkeepingPanel.add(checkboxPanel);
        bookkeepingPanel.add(dateTextField);
        bookkeepingPanel.add(moneyTextField);
        bookkeepingPanel.add(typeScrollBar);
        bookkeepingPanel.add(bokkeepingButton);
        add(bookkeepingPanel);
        add(test);
    }

    public JButton getBackButton(){
        return backButton;
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == backButton)
            {
                bookkeepingScreen.this.setVisible(false);
            }
        }
    }
}