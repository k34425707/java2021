import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.Object;

public class bookkeepingScreen extends JFrame{

    private final JButton backButton;

    private String[][] test = {
            {"108/08/09","500","gash","支出"},
            {"108/05/20","187","dinner","支出"},
            {"108/06/06","100","breakfast","支出"}};

    private BookOperation bookOperation = new BookOperation();

    private final String[] COLUME_NAMES = {"日期","金錢","敘述","類型"};

    private DefaultTableModel tableModel;
    private int verticalScrollBarMaximumValue;
    private JTable dataTable;
    private JScrollPane tableWithScrollbar;

    private final JPanel bookkeepingPanel;
    private final JButton bokkeepingButton;

    private final JPanel radioButtonPanel;
    private final JPanel radioButtonPanel2;
    private final ButtonGroup radioGroup;
    private final JRadioButton outNumber;
    private final JRadioButton inNumber;

    private final JPanel datePanel;
    private Calendar rightNow = Calendar.getInstance();
    private String[] YEAR = {"2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
    private String[] MONTH = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    private String[] DATE;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDate;
    private final comboBoxHandler comboHanedler = new comboBoxHandler();
    private final JComboBox yearComboBox;
    private final JComboBox monthComboBox;
    private JComboBox dateComboBox;

    private final JPanel moneyPanel;
    private final JLabel moneyLabel;
    private final JTextField moneyTextField;

    private final JPanel typePanel;
    private final JPanel typeTextPanel;
    private final JLabel typeLabel;
    private final JTextField typeTextField;
    private final String[] OPTIONS = { "早餐","午餐","晚餐","其他"};
    private final JComboBox typeComboBox;

    /*private final JScrollPane recordPane;
    private final JTextArea recordArea;*/
    public bookkeepingScreen(){
        super("記帳程式");
        //這個Frame的初始設定
        setLayout(new GridLayout(1,2));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookkeepingPanel = new JPanel(new GridLayout(6,1));
        //初始化各元件
        backButton = new JButton("返回");
        backButton.setHorizontalAlignment(JButton.LEFT);
        backButton.setOpaque(false);
        bokkeepingButton = new JButton("記帳");
        radioButtonPanel = new JPanel(new BorderLayout());
        radioButtonPanel2 = new JPanel();
        radioGroup = new ButtonGroup();
        outNumber = new JRadioButton("支出",true);
        inNumber = new JRadioButton("收入",false);
        //設定checkbox
        radioGroup.add(outNumber);
        radioGroup.add(inNumber);
        radioButtonPanel2.add(outNumber);
        radioButtonPanel2.add(inNumber);
        radioButtonPanel.add(radioButtonPanel2,BorderLayout.SOUTH);

        //創建日期的天數(因為每月的天數會不同，或有閏年問題)
        ArrayList<String> tmpDate = new ArrayList<String>();
        for(int i=1;i<=rightNow.getActualMaximum(rightNow.DATE);i++)
        {
            if(i<10)
                tmpDate.add("0" + i);
            else
                tmpDate.add(String.valueOf(i));
        }
        DATE = new String[tmpDate.size()];
        DATE = tmpDate.toArray(DATE);


        yearComboBox = new JComboBox(YEAR);
        yearComboBox.addItemListener(comboHanedler);

        //開得當下是哪一年並記錄那一年的index
        String tmpYear = String.valueOf(rightNow.get(Calendar.YEAR));
        int iyear=0;
        for(iyear=0;!tmpYear.equals(YEAR[iyear]);iyear++) { }
        System.out.println(iyear);

        monthComboBox = new JComboBox(MONTH);
        monthComboBox.addItemListener(comboHanedler);

        dateComboBox = new JComboBox(DATE);
        dateComboBox.addItemListener(comboHanedler);


        //設定預設選項(當天)
        yearComboBox.setSelectedIndex(iyear);
        monthComboBox.setSelectedIndex(rightNow.get(Calendar.MONTH));
        dateComboBox.setSelectedIndex(rightNow.get(Calendar.DATE) - 1);


        //這三個選單放在同一個排版
        datePanel = new JPanel();
        datePanel.add(yearComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(dateComboBox);


        moneyPanel = new JPanel(new BorderLayout());
        moneyLabel = new JLabel("金錢$");
        moneyTextField = new JTextField("請輸入金錢");
        moneyTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                typeTextField.requestFocus();
            }
        });
        moneyLabel.setPreferredSize(new Dimension(50,150));
        moneyPanel.add(moneyLabel,BorderLayout.WEST);
        moneyPanel.add(moneyTextField);

        typePanel = new JPanel(new GridLayout(2,1));
        typeTextPanel = new JPanel(new BorderLayout());
        typeLabel = new JLabel("敘述");
        typeTextField = new JTextField("請輸入敘述");
        typeLabel.setPreferredSize(new Dimension(50,200));
        //typeLabel.setFont(new Font());
        typeTextPanel.add(typeLabel,BorderLayout.WEST);
        typeTextPanel.add(typeTextField);
        typeComboBox = new JComboBox(OPTIONS);
        typePanel.add(typeTextPanel);
        typePanel.add(typeComboBox);

        dataTable = new JTable(new DefaultTableModel(COLUME_NAMES,0));
        tableModel = (DefaultTableModel) dataTable.getModel();
        dataTable.setEnabled(false);
        tableWithScrollbar = new JScrollPane(dataTable);
        //當資料更新時，scorllBar會自動滾到最新的資料
        verticalScrollBarMaximumValue = tableWithScrollbar.getVerticalScrollBar().getMaximum();
        tableWithScrollbar.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if ((verticalScrollBarMaximumValue - e.getAdjustable().getMaximum()) == 0)
                    return;
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                verticalScrollBarMaximumValue = tableWithScrollbar.getVerticalScrollBar().getMaximum();
            }
        });

        bookkeepingPanel.add(backButton);
        bookkeepingPanel.add(radioButtonPanel);
        bookkeepingPanel.add(datePanel);
        bookkeepingPanel.add(moneyPanel);
        bookkeepingPanel.add(typePanel);
        bookkeepingPanel.add(bokkeepingButton);

        add(bookkeepingPanel);
        add(tableWithScrollbar);
        bokkeepingButton.addActionListener(new ButtonHandler());
    }

    public JButton getBackButton(){
        return backButton;
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == bokkeepingButton)
            {
                /*recordArea.append(String.format("%s %s %s%n",dateTextField.getText(),moneyTextField.getText(),typeTextField.getText()));
                recordArea.setCaretPosition(recordArea.getDocument().getLength());*/

                //寫入帳本
                try {
                    Account temp = new Account(selectedYear, selectedMonth, selectedDate, (String) typeComboBox.getSelectedItem(), typeTextField.getText(), Integer.parseInt(moneyTextField.getText()), true);
                    if (outNumber.isSelected())
                        temp.setIsExpenditure(true);
                    else
                        temp.setIsExpenditure(false);
                    bookOperation.addAccountsIntoCsvFile(temp);
                } catch (NumberFormatException err)
                {
                    JOptionPane.showMessageDialog(bookkeepingScreen.this,"金錢應輸入數字","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                }
                //System.out.println(temp.formatCsvString());

                //在JTable中顯示出資料
                String[] tableData = new String[4];
                tableData[0] = String.valueOf(selectedYear) + "/" + String.valueOf(selectedMonth) + "/" + String.valueOf(selectedDate);
                tableData[1] = moneyTextField.getText();
                tableData[2] = typeTextField.getText();
                tableData[3] = (String)typeComboBox.getSelectedItem();
                tableModel.addRow(tableData);
                tableWithScrollbar.scrollRectToVisible(dataTable.getCellRect(dataTable.getRowCount(),dataTable.getColumnCount(),true));

            }
        }
    }

    private class comboBoxHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if(event.getStateChange() == ItemEvent.SELECTED)
            {
                getSelectTime();
                Calendar tmp = Calendar.getInstance();
                tmp.set(selectedYear,selectedMonth - 1,1);
                
                /* for the debug
                System.out.println(tmp.getTime());
                System.out.println(tmp.getActualMaximum(Calendar.DAY_OF_MONTH));
                System.out.println(dateComboBox.getItemCount());*/

                //設定日期月份的天數(因為每月的天數會不同，或有閏年問題)
                if(dateComboBox.getItemCount() > tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    dateComboBox.setSelectedIndex(0);
                    for(int i = dateComboBox.getItemCount() - 1;i>=tmp.getActualMaximum(Calendar.DAY_OF_MONTH);i--)
                        dateComboBox.removeItemAt(i);
                }
                else if(dateComboBox.getItemCount() < tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    dateComboBox.setSelectedIndex(0);
                    int addDate = dateComboBox.getItemCount();
                    int times = tmp.getActualMaximum(Calendar.DAY_OF_MONTH) - dateComboBox.getItemCount();
                    for(int i = 0;i < times;i++)
                    {
                        addDate++;
                        System.out.println("addDate : "  + addDate);
                        dateComboBox.addItem(addDate);
                    }
                }

                //紀錄所選的日
                try {
                    selectedDate = dateComboBox.getSelectedIndex() + 1;
                }
                catch(NullPointerException e) {
                    System.out.println("selectedDate went wrong!!!");
                }

            }
        }

        public void getSelectTime()
        {
            //紀錄所選的 年/月
            selectedYear = Integer.parseInt(YEAR[yearComboBox.getSelectedIndex()]);
            selectedMonth = Integer.parseInt(MONTH[monthComboBox.getSelectedIndex()]);

        }
    }
}
