import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class SearchScreen extends JFrame{
    private final JButton backButton;
    private BookOperation bookOperation = new BookOperation();

    private final String[] COLUME_NAMES = {"日期","金錢","敘述","類型","收/支"};
    private String[] YEAR = {"2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
    private String[] MONTH = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    private String[] DATE;
    private Calendar rightNow = Calendar.getInstance();
    private final comboBoxHandler comboHanedler = new comboBoxHandler();

    private final JPanel comboBoxPanel;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDate;
    private final JComboBox startYearComboBox;
    private final JComboBox startMonthComboBox;
    private JComboBox startDateComboBox;


    private final JPanel comboBoxPanel2;
    private int selectedYear2;
    private int selectedMonth2;
    private int selectedDate2;
    private final JComboBox endYearComboBox;
    private final JComboBox endMonthComboBox;
    private JComboBox endDateComboBox;

    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;

    private final JLabel totalExpenditureLabel;
    private final JLabel totalIncomeLabel;

    private buttonHandler buttonHandle = new buttonHandler();
    private final JButton deleteButton;
    private final JButton searchYearButton;
    private final JButton searchMonthButton;
    private final JButton searchTargetTimeButton;
    private final JButton filesaveButton;
    private String[] fileSaveData = new String[5];
    private ArrayList<Account> fileSaveList = new ArrayList<>();

    private String[][] data = {};
    private int totalExpenditure = 0;
    private int totalIncome = 0;
    private int selectedRow;
    private int selectedCol;
    private String[] selectedData = new String[5];
    private JTable showInfoTable;
    private tat test = new tat();
    private DefaultTableModel tableModel;
    private JScrollPane showInfoScrollPane;

    public SearchScreen(){
        super("查詢系統");
        //設定視窗和排版
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化各個元件
        backButton = new JButton("返回");

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

        //開得當下是哪一年並記錄那一年的index
        String tmpYear = String.valueOf(rightNow.get(Calendar.YEAR));
        int iyear=0;
        for(iyear=0;!tmpYear.equals(YEAR[iyear]);iyear++) { }

        comboBoxPanel = new JPanel();

        startYearComboBox = new JComboBox(YEAR);
        startYearComboBox.addItemListener(comboHanedler);

        startMonthComboBox = new JComboBox(MONTH);
        startMonthComboBox.addItemListener(comboHanedler);

        startDateComboBox = new JComboBox(DATE);
        startDateComboBox.addItemListener(comboHanedler);

        comboBoxPanel.add(startYearComboBox);
        comboBoxPanel.add(startMonthComboBox);
        comboBoxPanel.add(startDateComboBox);

        comboBoxPanel2 = new JPanel();

        endYearComboBox = new JComboBox(YEAR);
        endYearComboBox.addItemListener(comboHanedler);

        endMonthComboBox = new JComboBox(MONTH);
        endMonthComboBox.addItemListener(comboHanedler);

        endDateComboBox = new JComboBox(DATE);
        endDateComboBox.addItemListener(comboHanedler);

        comboBoxPanel2.add(endYearComboBox);
        comboBoxPanel2.add(endMonthComboBox);
        comboBoxPanel2.add(endDateComboBox);

        //設定預設選項
        startYearComboBox.setSelectedIndex(iyear);
        endYearComboBox.setSelectedIndex(iyear);

        startMonthComboBox.setSelectedIndex(rightNow.get(Calendar.MONTH));
        endMonthComboBox.setSelectedIndex(rightNow.get(Calendar.MONTH));

        startDateComboBox.setSelectedIndex(rightNow.get(Calendar.DATE) - 1);
        endDateComboBox.setSelectedIndex(rightNow.get(Calendar.DATE) - 1);

        label1 = new JLabel("  ~  ");
        label2 = new JLabel("起頭時間");
        label3 = new JLabel("目的時間");
        totalExpenditureLabel = new JLabel("總支出: " + totalExpenditure);
        totalIncomeLabel = new JLabel("總收入: " + totalIncome);

        searchYearButton = new JButton("年資料查詢");
        searchYearButton.addActionListener(buttonHandle);

        searchMonthButton = new JButton("月資料查詢");
        searchMonthButton.addActionListener(buttonHandle);

        searchTargetTimeButton = new JButton("查詢資料");
        searchTargetTimeButton.addActionListener(buttonHandle);

        deleteButton = new JButton("刪除資料");
        deleteButton.addActionListener(buttonHandle);

        filesaveButton = new JButton("匯出檔案");
        filesaveButton.addActionListener(buttonHandle);

        //初始化JTable
        showInfoTable = new JTable(new DefaultTableModel(data,COLUME_NAMES));
        tableModel = (DefaultTableModel) showInfoTable.getModel();
        showInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showInfoTable.setDefaultEditor(Object.class, null);
        showInfoTable.getSelectionModel().addListSelectionListener(test);

        showInfoScrollPane = new JScrollPane(showInfoTable);
        //設定字體
        label1.setFont(new Font("~",Font.BOLD,30));
        label2.setFont(new Font("~",Font.BOLD,20));
        label3.setFont(new Font("~",Font.BOLD,20));
        totalExpenditureLabel.setFont(new Font("總支出:  ",3,50));
        totalIncomeLabel.setFont(new Font("總收入:  ",3,50));
        //設定元件位置
        backButton.setBounds(0,0,100,80);
        comboBoxPanel.setBounds(200,50,180,80);
        label1.setBounds(comboBoxPanel.getX() + 210,comboBoxPanel.getY() - 25,50,80);
        comboBoxPanel2.setBounds(492,50,180,80);//602 //// 550 200
        label2.setBounds(comboBoxPanel.getX() + 50,comboBoxPanel.getY() - 35,100,30);
        label3.setBounds(comboBoxPanel2.getX() + 50,comboBoxPanel2.getY() - 35,100,30);
        deleteButton.setBounds(0,150,100,80);
        searchYearButton.setBounds(481,150,100,80);
        searchMonthButton.setBounds(581,150,100,80);
        searchTargetTimeButton.setBounds(681,150,100,80);
        showInfoScrollPane.setBounds(0,230,783,200);
        totalExpenditureLabel.setBounds(0,435,550,60);
        totalIncomeLabel.setBounds(0,495,550,65);
        filesaveButton.setBounds(680,430,100,80);
        //加到畫面上
        add(filesaveButton);
        add(backButton);
        add(comboBoxPanel);
        add(label1);
        add(label2);
        add(label3);
        add(comboBoxPanel2);
        add(deleteButton);
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

    //更新Table資料
    private void updateTable(String[][] data) {
        System.out.println("enter updateTable");
        remove(showInfoScrollPane);
        fillData(data);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void fillData(String[][] data) {
        System.out.println("enter fillData");

        //更新TABLE資料
        tableModel.setRowCount(0);
        for(int i=0;i< data.length;i++)
        {
            tableModel.addRow(data[i]);
        }
        showInfoScrollPane = new JScrollPane(showInfoTable);
        showInfoScrollPane.setBounds(0,230,783,200);
        add(showInfoScrollPane);
    }

    //計算總收入和支出
    private void computeMoney(String[][] data)
    {
        totalIncome = 0;
        totalExpenditure = 0;
        for(int i=0;i< data.length;i++)
        {
            if(data[i][4].equals("收入"))
            {
                totalIncome += Integer.parseInt(data[i][1]);
            }
            else
            {
                totalExpenditure += Integer.parseInt(data[i][1]);
            }
            data[i][1] = String.format("%,d",Integer.parseInt(data[i][1]));
        }
        totalExpenditureLabel.setText(String.format("總支出: %,d",totalExpenditure));
        totalIncomeLabel.setText(String.format("總收入: %,d",totalIncome));
    }

    //String money covert to int money
    public int convertToMoney(String m)
    {
        if(m.length() < 4)
        {
            return Integer.parseInt(m);
        }
        else
        {
            String[] str = m.split(",");
            String money = new String();
            for(int i=0;i<str.length;i++)
            {
                money += str[i];
            }
            //System.out.println("The money: " + money);
            return Integer.parseInt(money);
        }
    }



    private class buttonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == searchYearButton)
            {
                //檢查日期是否可行
                if(selectedYear > selectedYear2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Year wrong!!");
                }
                else {
                    showInfoTable.getSelectionModel().clearSelection();
                    bookOperation = new BookOperation();
                    data = bookOperation.getAccountsFromCsv(selectedYear,selectedYear2);
                    computeMoney(data);
                    updateTable(data);
                }
            }
            else if(event.getSource() == searchMonthButton)
            {
                //檢查日期是否可行
                if(selectedYear > selectedYear2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Month wrong!!");
                }
                else if(selectedYear == selectedYear2 && selectedMonth > selectedMonth2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Month wrong!! Same Year");
                }
                else {
                    showInfoTable.getSelectionModel().clearSelection();
                    bookOperation = new BookOperation();
                    data = bookOperation.getAccountsFromCsv(selectedYear,selectedMonth,selectedYear2,selectedMonth2);
                    computeMoney(data);
                    updateTable(data);
                }
            }
            else if(event.getSource() == searchTargetTimeButton)
            {
                //檢查日期是否可行
                if(selectedYear > selectedYear2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Date wrong!!");
                }
                else if(selectedYear == selectedYear2 && selectedMonth > selectedMonth2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Date wrong!! Same year");
                }
                else if(selectedYear == selectedYear2 && selectedMonth == selectedMonth2 && selectedDate > selectedDate2)
                {
                    JOptionPane.showMessageDialog(SearchScreen.this,"目的年月日應比起頭年月日大","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Date wrong!! Same year different Date!");
                }
                else {
                    showInfoTable.getSelectionModel().clearSelection();
                    bookOperation = new BookOperation();
                    data = bookOperation.getAccountsFromCsv(selectedYear,selectedMonth,selectedDate,selectedYear2,selectedMonth2,selectedDate2);
                    computeMoney(data);
                    updateTable(data);
                }
            }
            else if(event.getSource() == deleteButton)
            {
                //刪除資料
                try {
                    if(showInfoTable.getSelectionModel().isSelectionEmpty())
                    {
                        JOptionPane.showMessageDialog(SearchScreen.this,"尚未選取資料!","錯誤訊息",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        Account deleteAccount = getSelectedData(selectedData);
                        bookOperation.deleteAccount(deleteAccount); //刪除資料
                        tableModel.removeRow(selectedRow);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("刪除發生錯誤\n" + e);
                }
            }
            else if(event.getSource() == filesaveButton)
            {
                for(int i=0;i<tableModel.getRowCount();i++) {
                    //System.out.println("The data is :" + tableModel.getDataVector().elementAt(i));
                    Vector vectorData = tableModel.getDataVector().elementAt(i);
                    for (int j = 0; j < vectorData.size(); j++) {
                        fileSaveData[j] = (String) vectorData.elementAt(j);
                    }
                    Account tmp = getDataFileSave(fileSaveData);
                    fileSaveList.add(tmp);
                }
                /*for(int i=0;i<fileSaveList.size();i++)
                System.out.println(fileSaveList.get(i).formatCsvString());*/
                fileSaveList.clear();
                String filePath = new String();
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnVal = fileChooser.showOpenDialog(fileChooser);

                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    filePath= fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println(filePath);
                }

                bookOperation.exportFile(filePath,fileSaveList);
            }
        }

        //把所選擇的table資料轉成account型態
        public Account getDataFileSave(String[] data)
        {
            String[] Day = data[0].split("-");
            int year = Integer.parseInt(Day[0]);
            int month = Integer.parseInt(Day[1]);
            int date = Integer.parseInt(Day[2]);
            int money = convertToMoney(data[1]);
            boolean status;
            if(data[4].equals("收入")) {
                status = false;
            }
            else {
                status = true;
            }
            Account tmp = new Account(year,month,date,data[3],data[2],money,status);
            //System.out.println("The selected data account: \n" + tmp.formatCsvString());
            return tmp;
        }

        //把所選擇的table資料轉成account型態
        public Account getSelectedData(String[] data)
        {
            String[] Day = data[0].split("-");
            int year = Integer.parseInt(Day[0]);
            int month = Integer.parseInt(Day[1]);
            int date = Integer.parseInt(Day[2]);
            int money = convertToMoney(data[1]);
            boolean status;
            //順便把總收入和總支出的值更改一下
            if(data[4].equals("收入")) {
                status = false;
                totalIncome -= money;
                totalIncomeLabel.setText(String.format("總收入: %,d",totalIncome));
            }
            else {
                status = true;
                totalExpenditure -= money;
                totalExpenditureLabel.setText(String.format("總支出: %,d",totalExpenditure));
            }
            Account tmp = new Account(year,month,date,data[3],data[2],money,status);
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
                int currentDate = startDateComboBox.getSelectedIndex() + 1;
                int currentDate2 = endDateComboBox.getSelectedIndex() + 1;
                Calendar tmp = Calendar.getInstance();
                Calendar tmp2 = Calendar.getInstance();
                tmp.set(selectedYear,selectedMonth - 1,1);
                tmp2.set(selectedYear2,selectedMonth2 - 1,1);


                //查詢起頭年/月/日
                //設定日期月份的天數(因為每月的天數會不同，或有閏年問題)
                //月份日子比目前小的
                if(startDateComboBox.getItemCount() > tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    //目的月份的天數
                    int dstDate = tmp.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if(currentDate > dstDate)
                        startDateComboBox.setSelectedIndex(dstDate - 1);
                    for(int i = startDateComboBox.getItemCount() - 1;i>=dstDate;i--)
                        startDateComboBox.removeItemAt(i);
                }
                //月份日子比目前大的
                else if(startDateComboBox.getItemCount() < tmp.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    int addDate = startDateComboBox.getItemCount();
                    int times = tmp.getActualMaximum(Calendar.DAY_OF_MONTH) - startDateComboBox.getItemCount();
                    for(int i = 0;i < times;i++)
                    {
                        addDate++;
                        System.out.println("addDate : "  + addDate);
                        startDateComboBox.addItem(addDate);
                    }
                }
                //查詢目的年/月/日
                //設定日期月份的天數(因為每月的天數會不同，或有閏年問題)
                //月份日子比目前小的
                if(endDateComboBox.getItemCount() > tmp2.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    //目的月份的天數
                    int dstDate2 = tmp2.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if(currentDate2 > dstDate2)
                        endDateComboBox.setSelectedIndex(dstDate2 - 1);
                    for(int i = endDateComboBox.getItemCount() - 1;i>=dstDate2;i--)
                        endDateComboBox.removeItemAt(i);
                }
                //月份日子比目前大的
                else if(endDateComboBox.getItemCount() < tmp2.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    int addDate2 = endDateComboBox.getItemCount();
                    int times2 = tmp2.getActualMaximum(Calendar.DAY_OF_MONTH) - endDateComboBox.getItemCount();
                    for(int i = 0;i < times2;i++)
                    {
                        addDate2++;
                        System.out.println("addDate : "  + addDate2);
                        endDateComboBox.addItem(addDate2);
                    }
                }

                //紀錄所選的日
                try {
                    selectedDate = startDateComboBox.getSelectedIndex() + 1;
                }
                catch(NullPointerException e) {
                    System.out.println("selectedDate went wrong!!!");
                }

                try {
                    selectedDate2 = endDateComboBox.getSelectedIndex() + 1;
                }
                catch(NullPointerException e) {
                    System.out.println("selectedDate went wrong!!!");
                }

            }
        }

        //取得下拉式選單所選的時間
        public void getSelectTime()
        {
            //紀錄所選的 年/月
            selectedYear = Integer.parseInt(YEAR[startYearComboBox.getSelectedIndex()]);
            selectedMonth = Integer.parseInt(MONTH[startMonthComboBox.getSelectedIndex()]);
            selectedYear2 = Integer.parseInt(YEAR[endYearComboBox.getSelectedIndex()]);
            selectedMonth2 = Integer.parseInt(MONTH[endMonthComboBox.getSelectedIndex()]);
        }
    }

    //當選擇一個table資料時，把那一列的資料記錄下來
    private class tat implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            try {
                selectedRow = showInfoTable.getSelectedRow();
                selectedCol = showInfoTable.getSelectedColumn();

                /*System.out.println("The selected row is : " + selectedRow);
                System.out.println("The selected col is : " + selectedCol);*/
                System.out.println("The data is :" + tableModel.getDataVector().elementAt(selectedRow));
                Vector vectorData = tableModel.getDataVector().elementAt(selectedRow);
                for (int i = 0; i < vectorData.size(); i++) {
                    selectedData[i] = (String) vectorData.elementAt(i);
                }
            }
            catch (Exception ex)
            {
                System.out.println("清除所選的東西!!\n");
            }
        }
    }

}

