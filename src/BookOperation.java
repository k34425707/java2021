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
    ArrayList<Account> accountsList=new ArrayList<>();//查詢帳款時使用的回傳list
    Account account;//add進accountsList的物件
    private String baseFilePathString="bookData/book_";
    private String directoryPath;//目錄路徑
    private String csvFileName;//檔案路徑
    private File directory;//
    private File csvFile;
    String dataString;//讀取csv檔的每一行儲存點
    String[] data;//將dataString的資料以逗號分開
    public void addAccountsIntoCsvFile(Account account)
    {
        BufferedWriter bw;
        directoryPath=String.format(baseFilePathString+account.getYear());
        csvFileName=String.format(directoryPath+"/book_"+account.getYear()+"_"+account.getMonth()+".csv");
        try{
            directory=new File(directoryPath);
            csvFile=new File(csvFileName);
            if(!directory.exists())
            {
                directory.mkdirs();
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

    public ArrayList<Account> getAccountsFromCsv(int startY,int endY)//以年為單位查詢帳目
    {
        for(int i=startY;i<=endY;i++)
        {
            directoryPath=String.format(baseFilePathString+i);
            directory=new File(directoryPath);
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
                            data=dataString.split(",");
                            account=new Account(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],data[4],Integer.parseInt(data[5]),Boolean.parseBoolean(data[6]));
                            accountsList.add(account);
                        } 
                        
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        }
        return accountsList;
    }
    public ArrayList<Account> getAccountsFromCsv(int startY,int startM,int endY,int endM)//以月為單位查詢帳目
    {
        LocalDate current=LocalDate.of(startY,startM,2);
        LocalDate end=LocalDate.of(endY,endM,20);
        while(end.isAfter(current))
        {
            csvFileName=String.format(baseFilePathString+current.getYear()+"/book_"+current.getYear()+"_"+current.getMonthValue()+".csv");
            current=current.plusMonths(1);
            try
            {
                csvFile=new File(csvFileName);
                if(csvFile.exists())
                {
                    BufferedReader br=new BufferedReader(new FileReader(csvFile));
                    while((dataString=br.readLine())!=null)
                    {
                        data=dataString.split(",");
                        account=new Account(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],data[4],Integer.parseInt(data[5]),Boolean.parseBoolean(data[6]));
                        accountsList.add(account);
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
        return accountsList;
    }
    /*public ArrayList<Account> getAccountsFromCsv(int startY,int startM,int startD,int endY,int endM,int endD)//以日為單位查詢帳目
    {
        LocalDate startDate=LocalDate.of(startY,startM,startD);
        LocalDate endDate=LocalDate.of(endY,endM,endD);
        startDate=startDate.plusDays(-1);
        endDate=endDate.plusDays(1);
        if(startY==endY && startM==endM)
        {
            csvFile=new File(baseFilePathString+startY+"/book_"+startY+"_"+startM+".csv");
        }
    }*/
}