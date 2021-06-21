import javax.swing.*;
import java.awt.*;
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
    public void run()//每隔15分鐘要扣口渴度和飢餓度
    {
        while(true)
        {
            if(po.myDog.getLastUpdateTime().plusMinutes(15).isBefore(LocalDateTime.now()))
            {
                po.myDog.decreaseHungerAndThirstValue();
                po.myDog.setLastUpdateTime(PetOperation.myDog.getLastUpdateTime().plusMinutes(15));
                this.po.writePetDataCsv();
                //動態更新寵物資訊
                hungerBar.setValue(po.myDog.getHungerValue());
                thirstBar.setValue(po.myDog.getThirstValue());
                healthBar.setValue(po.myDog.getHP());
                healthLabel.setText("生命值 " + po.myDog.getHP() + "/1000");
                thirstLabel.setText("口渴度 " + po.myDog.getThirstValue() + "/100");
                hungerLabel.setText("飢餓度 " + po.myDog.getHungerValue() + "/100");
                if(po.myDog.getIsHunger())
                {
                    stateTextArea.append("寵物現在肚子餓了~\n");
                    stateTextArea.setCaretPosition(stateTextArea.getDocument().getLength());
                }
                if(po.myDog.getIsThirst())
                {
                    stateTextArea.append("寵物現在口渴了~\n");
                    stateTextArea.setCaretPosition(stateTextArea.getDocument().getLength());
                }
                System.out.println("過了15分鐘了~~~~~~~~~~");
                //根據寵物狀態設定GIF圖
                String status = new String();
                if(po.myDog.getHP() <= 0)
                {
                    status += "HP_zero";
                }
                else if(po.myDog.getHP() <= 200)
                {
                    status += "HP_low";
                }
                else {
                    if (po.myDog.getIsHunger())
                        status += "true_";
                    else
                        status += "false_";
                    if (po.myDog.getIsThirst())
                        status += "true_";
                    else
                        status += "false_";
                    status += po.myDog.getWearMask() + "_";
                    status += po.myDog.getDecoration().name();
                }
                ImageIcon petGif = new ImageIcon( "pet_Connected_Data/Gifs/" + status + ".gif");
                Image resizingImage = petGif.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
                pet.setIcon(new ImageIcon(resizingImage));
            }
        }
    }
}