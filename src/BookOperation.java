import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
public class BookOperation 
{
    public void addAccountsIntoCsvFile( ArrayList<Account> input)
    {
        String directoryPath;//目錄路徑
        String csvFileName;//檔名
        File directory;
        File csvFile;
        BufferedWriter bw;
        for(Account account:input)
        {
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
    }
}