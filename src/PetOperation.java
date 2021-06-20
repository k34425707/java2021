import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class PetOperation 
{
    private File petDataCsv=new File("pet_Connected_Data/pet_Data.csv");
    private File bag=new File("pet_Connected_Data/bag.csv");
    private BufferedWriter bw;
    private BufferedReader br;
    public static PetDog myDog;
    public static Bag myBag;
    public PetOperation()//建構函式
    {
        try
        {
            if(!petDataCsv.getParentFile().exists())
                petDataCsv.getParentFile().mkdirs();
            if(!petDataCsv.exists())//如果沒有寵物檔案就創建他
            {
                String now=LocalDateTime.now().toString();
                petDataCsv.createNewFile();
                bw=new BufferedWriter(new FileWriter(petDataCsv,false));
                bw.write("1000,100,100,"+now+",false,false,false,false,"+Decoration.nothing);
                //生命值,饑餓值,口渴度,當前時間,是否飢餓,是否口渴,下次是否調整HP,mask,decoration
                bw.close();
            }
            if(!bag.exists())//如果沒有背包檔案就創建他
            {
                bag.createNewFile();
                bw=new BufferedWriter(new FileWriter(bag,false));
                bw.write("0,0,0,false,false,false,false,false,0");
                bw.close();
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
        try{
            br=new BufferedReader(new FileReader(petDataCsv));
            String[] data=br.readLine().split(",");
            PetOperation.myDog=new PetDog(Integer.parseInt(data[0]),Integer.parseInt(data[1]) , Integer.parseInt(data[2]), data[3] ,Boolean.parseBoolean(data[4]), Boolean.parseBoolean(data[5]), Boolean.parseBoolean(data[6]),Boolean.parseBoolean(data[7]),Decoration.valueOf(data[8]));
            br=new BufferedReader(new FileReader(bag));
            data=br.readLine().split(",");
            PetOperation.myBag=new Bag(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),Boolean.parseBoolean(data[3]),Boolean.parseBoolean(data[4]),Boolean.parseBoolean(data[5]),Boolean.parseBoolean(data[6]),Boolean.parseBoolean(data[7]));
            //System.out.println(PetOperation.myDog.formatCsvString());
            PetOperation.myDog.updatePetStatus();
            this.writePetDataCsv();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    
    public void writePetDataCsv()//將寵物資訊寫回檔案裡
    {
        try{
            bw=new BufferedWriter(new FileWriter(petDataCsv,false));
            bw.write(PetOperation.myDog.formatCsvString());
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void writeBagDataCsv()//將背包資料寫入檔案
    {
        try{
            bw=new BufferedWriter(new FileWriter(bag,false));
            bw.write(PetOperation.myBag.formatCsvString());
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
