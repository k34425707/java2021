import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.Object;
import java.util.Vector;

public class BookkeepingScreen extends JFrame{

    private final JButton backButton;


    private BookOperation bookOperation = new BookOperation();

    private final String[] COLUME_NAMES = {"日期","金錢","敘述","類型","收/支"};

    private DefaultTableModel tableModel;
    private int verticalScrollBarMaximumValue;
    private int selectedRow;
    private int selectedCol;
    private JTable dataTable;
    private JScrollPane tableWithScrollbar;
    private String[] selectedData = new String[5];

    private final JPanel bookkeepingPanel;

    private final JPanel buttonPanel;
    private final JButton bokkeepingButton;
    private final JButton deleteButton;

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
    private final String[] OPTIONS = { "早餐","午餐","晚餐","房租","交通","薪水","獎金","其他"};
    private final JComboBox typeComboBox;

    /*private final JScrollPane recordPane;
    private final JTextArea recordArea;*/
    public BookkeepingScreen(){
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

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        bokkeepingButton = new JButton("記帳");
        bokkeepingButton.setBounds(60,25,100,50);
        deleteButton = new JButton("刪除");
        deleteButton.setBounds(225,25,100,50);
        buttonPanel.add(bokkeepingButton);
        buttonPanel.add(deleteButton);

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


        moneyPanel = new JPanel(null);
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
        moneyLabel.setFont(new Font("l",Font.BOLD,16));
        moneyLabel.setBounds(0,40,100,50);
        moneyTextField.setBounds(50,45,341,45);
        moneyPanel.add(moneyLabel,BorderLayout.WEST);
        moneyPanel.add(moneyTextField);

        typePanel = new JPanel(new GridLayout(2,1));
        typeTextPanel = new JPanel(new BorderLayout());
        typeLabel = new JLabel("敘述");
        typeLabel.setFont(new Font("l",Font.BOLD,16));
        typeTextField = new JTextField("無");
        typeLabel.setPreferredSize(new Dimension(50,200));
        //typeLabel.setFont(new Font());
        typeTextPanel.add(typeLabel,BorderLayout.WEST);
        typeTextPanel.add(typeTextField);
        typeComboBox = new JComboBox(OPTIONS);
        typePanel.add(typeTextPanel);
        typePanel.add(typeComboBox);

        dataTable = new JTable(new DefaultTableModel(COLUME_NAMES,0));
        tableModel = (DefaultTableModel) dataTable.getModel();
        //只能選一項資料
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //設定成不能更改TABLE內的資料
        dataTable.setDefaultEditor(Object.class, null);
        //當選取Table的資料時，紀錄所選的資料。
        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                selectedRow = dataTable.getSelectedRow();
                selectedCol = dataTable.getSelectedColumn();
                /*System.out.println("The selected row is : " + selectedRow);
                System.out.println("The selected col is : " + selectedCol);*/
                System.out.println("The data is :" + tableModel.getDataVector().elementAt(selectedRow));
                Vector vectorData = tableModel.getDataVector().elementAt(selectedRow);
                for(int i=0;i<vectorData.size();i++)
                {
                    selectedData[i] = (String) vectorData.elementAt(i);
                }
            }
        });
        //dataTable.setEnabled(false);
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
        bookkeepingPanel.add(buttonPanel);

        add(bookkeepingPanel);
        add(tableWithScrollbar);

        ButtonHandler buttonHandler = new ButtonHandler();
        bokkeepingButton.addActionListener(buttonHandler);
        deleteButton.addActionListener(buttonHandler);
    }

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getBokkeepingButton() { return bokkeepingButton; }

    public JTextField getMoneyTextField() { return moneyTextField; }

    //按鈕的事件處理器
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == bokkeepingButton)
            {

                try {
                    //寫入帳本
                    Account temp = new Account(selectedYear, selectedMonth, selectedDate, (String) typeComboBox.getSelectedItem(), typeTextField.getText(), Integer.parseInt(moneyTextField.getText()), true);
                    if (outNumber.isSelected())
                        temp.setIsExpenditure(true);
                    else
                        temp.setIsExpenditure(false);
                    bookOperation.addAccountsIntoCsvFile(temp);
                    //在JTable中顯示出資料
                    String[] tableData = new String[5];
                    tableData[0] = String.valueOf(selectedYear) + "/" + String.valueOf(selectedMonth) + "/" + String.valueOf(selectedDate);
                    tableData[1] = moneyTextField.getText();
                    tableData[2] = typeTextField.getText();
                    tableData[3] = (String)typeComboBox.getSelectedItem();
                    if(outNumber.isSelected())
                    {
                        tableData[4] = "支出";
                    }
                    else
                    {
                        tableData[4] = "收入";
                    }
                    tableModel.addRow(tableData);
                    tableWithScrollbar.scrollRectToVisible(dataTable.getCellRect(dataTable.getRowCount(),dataTable.getColumnCount(),true));
                } catch (NumberFormatException err)
                {
                    JOptionPane.showMessageDialog(BookkeepingScreen.this,"金錢應輸入整數!","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(BookkeepingScreen.this,"出現了不知名的錯誤","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                }

            }
            else if(event.getSource() == deleteButton)
            {
                //刪除資料
                try {
                    if(dataTable.getSelectionModel().isSelectionEmpty())
                    {
                        JOptionPane.showMessageDialog(BookkeepingScreen.this,"尚未選取資料!","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        Account deleteAccount = getSelectedData(selectedData);
                        bookOperation.deleteAccount(deleteAccount);
                        tableModel.removeRow(selectedRow);
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(BookkeepingScreen.this,"尚未選取資料!","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //把選擇的table資料轉成account
        public Account getSelectedData(String[] data)
        {
            String[] Day = data[0].split("/");
            int year = Integer.parseInt(Day[0]);
            int month = Integer.parseInt(Day[1]);
            int date = Integer.parseInt(Day[2]);
            boolean status;
            if(data[4].equals("收入"))
                status = false;
            else
                status = true;
            Account tmp = new Account(year,month,date,data[3],data[2],Integer.parseInt(data[1]),status);
            System.out.println("The selected data account: \n" + tmp.formatCsvString());
            return tmp;
        }
    }

    //處理日期選單的處理器
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

                int currentDate = dateComboBox.getSelectedIndex() + 1;
                //設定日期月份的天數(因為每月的天數會不同，或有閏年問題)
                //月份日子比目前小的
                if(dateComboBox.getItemCount() > tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    //目的月份的天數
                    int dstDate = tmp.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if(currentDate > dstDate)
                    dateComboBox.setSelectedIndex(dstDate - 1);
                    for(int i = dateComboBox.getItemCount() - 1;i>=dstDate;i--)
                        dateComboBox.removeItemAt(i);
                }
                //月份日子比目前大的
                else if(dateComboBox.getItemCount() < tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
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

        //取得下拉式選單的時間
        public void getSelectTime()
        {
            //紀錄所選的 年/月
            selectedYear = Integer.parseInt(YEAR[yearComboBox.getSelectedIndex()]);
            selectedMonth = Integer.parseInt(MONTH[monthComboBox.getSelectedIndex()]);

        }
    }
}
