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
    private BufferedWriter bw;
    private BufferedReader br;
    static PetDog myDog;
    public PetOperation()
    {
        try
        {
            if(!petDataCsv.getParentFile().exists())
                petDataCsv.getParentFile().mkdirs();
            if(!petDataCsv.exists())
            {
                String now=LocalDateTime.now().toString();
                petDataCsv.createNewFile();
                bw=new BufferedWriter(new FileWriter(petDataCsv,false));
                
                bw.write("1000,100,100,"+now+",false,false,false");
                //生命值,饑餓值,口渴度,當前時間,是否飢餓,是否口渴,下次是否調整HP
                bw.close();
            }
        }catch(IOException e){
            System.out.println(e);
        }
        try{
            br=new BufferedReader(new FileReader(petDataCsv));
            String[] data=br.readLine().split(",");
            PetOperation.myDog=new PetDog(Integer.parseInt(data[0]),Integer.parseInt(data[1]) , Integer.parseInt(data[2]), data[3] ,Boolean.parseBoolean(data[4]), Boolean.parseBoolean(data[5]), Boolean.parseBoolean(data[6]));
            System.out.println(PetOperation.myDog.formatCsvString());
            PetOperation.myDog.updatePetStatus();
            this.writePetDataCsv();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void writePetDataCsv(){
        try{
            bw=new BufferedWriter(new FileWriter(petDataCsv,false));
            bw.write(PetOperation.myDog.formatCsvString());
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
