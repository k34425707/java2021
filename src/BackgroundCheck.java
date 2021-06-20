import java.time.LocalDateTime;

public class BackgroundCheck implements Runnable
{
    private PetOperation po;

    public BackgroundCheck(PetOperation po){
        this.po=po;
    }
    public void run()
    {
        while(true)
        {
            if(PetOperation.myDog.getLastUpdateTime().plusMinutes(15).isBefore(LocalDateTime.now()))
            {
                PetOperation.myDog.decreaseHungerAndThirstValue();
                PetOperation.myDog.setLastUpdateTime(PetOperation.myDog.getLastUpdateTime().plusMinutes(15));
                this.po.writePetDataCsv();

            }
        }
    }
}