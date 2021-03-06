import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public class BookOperation 
{
    ArrayList<Account> accountsList=new ArrayList<>();//查詢帳款時使用的暫存list
    private String baseFilePathString="bookData/book_";
    private String csvFilePath;//檔案路徑
    private File directory;
    private File csvFile;
    String dataString;//讀取csv檔的每一行儲存點

    public void addAccountsIntoCsvFile(Account account)
    {
        BufferedWriter bw;
        csvFilePath=String.format(baseFilePathString+account.getYear()+"/book_"+account.getYear()+"_"+account.getMonth()+".csv");
        try{
            csvFile=new File(csvFilePath);
            if(!csvFile.getParentFile().exists())
            {
                csvFile.getParentFile().mkdirs();
            }
            if(!csvFile.exists())
            {
                csvFile.createNewFile();
            }
            bw=new BufferedWriter(new FileWriter(csvFile,true));
            bw.write(account.formatCsvString());
            bw.newLine();
            bw.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public String[][] getAccountsFromCsv(int startY,int endY)//以年為單位查詢帳目
    {
        for(int i=startY;i<=endY;i++)
        {
            directory=new File(baseFilePathString+i);
            File[] fileList;
            if(directory.exists())
            {
                fileList=directory.listFiles();
                try
                {
                    for(File csv:fileList)//將目錄裡所有資料讀一遍
                    {
                        BufferedReader br=new BufferedReader(new FileReader(csv));
                        while((dataString=br.readLine())!=null)
                        {
                            accountsList.add(makeAccount(dataString));
                        }
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        }
        return makeTableString(accountsList);
    }

    public String[][] getAccountsFromCsv(int startY,int startM,int endY,int endM)//以月為單位查詢帳目
    {
        LocalDate current=LocalDate.of(startY,startM,5);
        LocalDate end=LocalDate.of(endY,endM,20);
        while(end.isAfter(current))
        {
           accountsList.addAll(getTotalAccountsFromAMonth(current.getYear(),current.getMonthValue()));
           current=current.plusMonths(1);
        }
        return makeTableString(accountsList);
    }


    public String[][] getAccountsFromCsv(int startY,int startM,int startD,int endY,int endM,int endD)//以日為單位查詢帳目
    {
        LocalDate startDate=LocalDate.of(startY,startM,startD);
        LocalDate endDate=LocalDate.of(endY,endM,endD);
        if(startY==endY && startM==endM)//如果是在一個月內只需要開一個檔案
        {
           accountsList=getSomeOfAccountsFromAMonth(startDate,endDate);
        }
        else
        {
            LocalDate current=LocalDate.of(startY,startM,5).plusMonths(1);
            accountsList.addAll(getSomeOfAccountsFromAMonth(startDate,startDate.plusMonths(1)));
            while(current.isBefore(LocalDate.of(endY,endM,1)))
            {
                accountsList.addAll(getTotalAccountsFromAMonth(current.getYear(),current.getMonthValue()));
                current=current.plusMonths(1);
            }
            accountsList.addAll(getSomeOfAccountsFromAMonth(LocalDate.of(endY,endM,1),endDate));
        }
        return makeTableString(accountsList);
    }

    public void deleteAccount(Account account)//刪除資料
    {
        ArrayList<Account> tempList=new ArrayList<>();
        ArrayList<String> StringList=new ArrayList<>();
        tempList.addAll(getTotalAccountsFromAMonth(account.getYear(),account.getMonth()));
        for(Account a:tempList)
            StringList.add(a.formatCsvString());
        System.out.println("----------------");
        System.out.println(StringList.remove(account.formatCsvString()));
        
        try
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(baseFilePathString+account.getYear()+"/book_"+account.getYear()+"_"+account.getMonth()+".csv",false));
            for(String input:StringList)
            {
                bw.write(input);
                bw.newLine();
            }
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    private ArrayList<Account> getTotalAccountsFromAMonth(int year,int month)
    {
        ArrayList<Account> tempList=new ArrayList<>();
        try
        {
            csvFile=new File(baseFilePathString+year+"/book_"+year+"_"+month+".csv");
            if(csvFile.exists())
            {
                BufferedReader br=new BufferedReader(new FileReader(csvFile));
                while((dataString=br.readLine())!=null)
                {
                    tempList.add(makeAccount(dataString));
                }
                
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return tempList;
    }

    private ArrayList<Account> getSomeOfAccountsFromAMonth(LocalDate start,LocalDate end)
    {
        ArrayList<Account> tempList=new ArrayList<>();
        tempList=this.getTotalAccountsFromAMonth(start.getYear(),start.getMonthValue());
        for(int i=tempList.size()-1;i>=0;i--)
        {
            if(tempList.get(i).getDate().isBefore(start)|| tempList.get(i).getDate().isAfter(end)){
                tempList.remove(i);
            }
        }
        return tempList;
    }
    

    private Account makeAccount(String dataString)
    {
        String[] data;//將dataString的資料以逗號分開
        data=dataString.split(",");
        return new Account(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],data[4],Integer.parseInt(data[5]),Boolean.parseBoolean(data[6]));
    }

    private String[][] makeTableString(ArrayList<Account> list)
    {
        String[][] accountsString=new String[list.size()][];
        for(int i=0;i<list.size();i++)
        {
            String[] account=new String[5];
            account[0]=list.get(i).getDate().toString();
            account[1]=Integer.toString(list.get(i).getMoneySum());
            account[2]=list.get(i).getDescription();
            account[3]=list.get(i).getType();
            if(list.get(i).getIsExpenditure())
            {
                account[4]="支出";
            }
            else
            {
                account[4]="收入";
            }
            accountsString[i]=account;
        }
        return accountsString;
    }

    public void exportFile(String filePath,ArrayList<Account> input,String fileName)
    {
        File exportCSV=new File(filePath+"/"+fileName+".csv");
        
        try
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(exportCSV,false));
            exportCSV.createNewFile();
            bw.write("年,月,日,類型,類型,金額,收入/支出");
            for(Account a:input)
            {
                //System.out.println(a.formatExportCsvString());
                bw.newLine();
                bw.write(a.formatExportCsvString());
            }
            bw.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}