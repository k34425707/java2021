import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public class test1 {
    public static void main(String[] args)
    {
        BookOperation bo=new BookOperation();
        Account list;
        File x=new File("bookData/book_2021");
        String[] y=x.list();
        System.out.println(x.getName());
        for(String t:y)
        {
            System.out.println(t);
        }
        list=new Account(2021,3,20,"飲食","測試4",45,true);
        bo.addAccountsIntoCsvFile(list);
        ArrayList<Account> test=bo.getAccountsFromCsv(2021, 3, 2021, 6);;
        for(Account i:test)
            System.out.println(i.formatCsvString());
        LocalDate myObj = LocalDate.now(); // Create a date object
        System.out.println(myObj.plusMonths(-1)); // Display the current date
        bo.getAccountsFromCsv(2021, 3, 2021, 6);
        PetOperation po=new PetOperation();
    }
}
