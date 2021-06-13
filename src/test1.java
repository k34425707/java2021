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
        System.out.println(LocalDate.now().toString());
        for(String t:y)
        {
            System.out.println(t);
        }
        list=new Account(2021,3,20,"飲食","測試4",45,true);
        bo.addAccountsIntoCsvFile(list);
        list=new Account(2021,3,20,"飲食","測試4",16,true);
        String[][] test=bo.getAccountsFromCsv(2021, 3,20, 2021, 6,20);
        bo.deleteAccount(list);
        for(int i=0;i<test.length;i++)
            System.out.println(test[i][0]+" "+test[i][1]+" "+test[i][2]+" "+test[i][3]+" "+test[i][4]+" ");
        LocalDate myObj = LocalDate.now(); // Create a date object
        System.out.println(myObj.plusMonths(-2)); // Display the current date
        bo.getAccountsFromCsv(2021, 3, 2021, 6);
        PetOperation po=new PetOperation();
    }
}
