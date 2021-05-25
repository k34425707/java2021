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
    String directoryPath;//目錄路徑
    String csvFileName;//檔名
    File directory;
    File csvFile;
    public void addAccountsIntoCsvFile(Account account)
    {
        BufferedWriter bw;
        directoryPath=String.format("bookData/book_"+account.getYear());
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
        ArrayList<Account> accountsList=new ArrayList<>();
        Account account;
        for(int i=startY;i<=endY;i++)
        {
            directoryPath=String.format("bookData/book_"+i);
            directory=new File(directoryPath);
            File[] fileList=directory.listFiles();
            String dataString;//讀取csv檔的每一行儲存點
            String[] data;//將dataString的資料以逗號分開
            try
            {
                for(File csv:fileList)//將目錄裡所有資料讀一遍
                {
                    BufferedReader br=new BufferedReader(new FileReader(csv));
                    while((dataString=br.readLine())!=null)
                    {
                        data=dataString.split(",");
                        account=new Account(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],data[4],Integer.parseInt(data[5]));
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
    public ArrayList<Account> getAccountsFromCsv(int startY,int startM,int endY,int endM)
    {
        /*LocalDate startDate= LocalDate.of(startY, startM, 1);
        System.out.println(startDate);
        System.out.println(startDate.getYear());
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());

        for(int i=startY;i<=endY)
        {
            for(int)
        }*/
        return null;
    }
}