import javax.swing.*;
import java.time.LocalDateTime;

public class BackgroundCheck implements Runnable
{
    private PetOperation po;
    private JProgressBar hungerBar;
    private JProgressBar thirstBar;
    private JProgressBar healthBar;
    private JLabel hungerLabel;
    private JLabel thirstLabel;
    private JLabel healthLabel;
    private JLabel pet;
    private JTextArea stateTextArea;

    public BackgroundCheck(PetOperation po, JProgressBar hungerBar,JProgressBar thirstBar,JProgressBar healthBar,JLabel hungerLabel,JLabel thirstLabel,JLabel healthLabel,JLabel pet,JTextArea stateTextArea)
    {
        this.po=po;
        this.healthBar = healthBar;
        this.hungerBar = hungerBar;
        this.thirstBar = thirstBar;
        this.healthLabel = healthLabel;
        this.hungerLabel = hungerLabel;
        this.thirstLabel = thirstLabel;
        this.pet = pet;
        this.stateTextArea = stateTextArea;
    }
    public void run()
    {
        while(true)
        {
            if(po.myDog.getLastUpdateTime().plusMinutes(15).isBefore(LocalDateTime.now()))
            {
                po.myDog.decreaseHungerAndThirstValue();
                po.myDog.setLastUpdateTime(PetOperation.myDog.getLastUpdateTime().plusMinutes(15));
                this.po.writePetDataCsv();
                hungerBar.setValue(po.myDog.getHungerValue());
                thirstBar.setValue(po.myDog.getThirstValue());
                healthBar.setValue(po.myDog.getHP());
                healthLabel.setText("生命值 " + po.myDog.getHP() + "/1000");
                thirstLabel.setText("口渴度 " + po.myDog.getThirstValue() + "/100");
                hungerLabel.setText("飢餓度 " + po.myDog.getHungerValue() + "/100");
                if(po.myDog.getIsHunger())
                {
                    stateTextArea.append("寵物現在肚子餓了~");
                    stateTextArea.setCaretPosition(stateTextArea.getDocument().getLength());
                }
                if(po.myDog.getIsThirst())
                {
                    stateTextArea.append("寵物現在口渴了~");
                    stateTextArea.setCaretPosition(stateTextArea.getDocument().getLength());
                }
                System.out.println("過了15分鐘了~~~~~~~~~~");
            }
        }
    }
}