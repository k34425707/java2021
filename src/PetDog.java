import java.time.LocalDateTime;

public class PetDog {
    private int HP;//生命值 上限1000
    private int hungerValue;//飢餓值 上限100
    private int thirstValue;//口渴值 上限100
    private LocalDateTime lastUpdateTime;
    private boolean isHunger;
    private boolean isThirst;
    private boolean doReviseHP;
    private boolean wearMask;//true為有戴
    private Decoration decoration;
    public PetDog(int HP,int hungerValue,int thirstValue,String lastTime,boolean doReviseHP,boolean isHunger,boolean isThirst,boolean wearMask,Decoration decoration)
    {
        this.HP=HP;
        this.hungerValue=hungerValue;
        this.thirstValue=thirstValue;
        this.isHunger=isHunger;
        this.isThirst=isThirst; 
        this.doReviseHP=doReviseHP;
        this.lastUpdateTime=LocalDateTime.parse(lastTime);
        this.wearMask=wearMask;
        this.decoration=decoration;
        updatePetStatus();
    }
    
    public void setHP(int HP){
        this.HP=HP;
    }

    public void setHungerValue(int hungerValue){
        this.hungerValue=hungerValue;
    }

    public void setThirstValue(int thirstValue){
        this.thirstValue=thirstValue;
    }

    public void setLastUpdateTime(LocalDateTime lt){
        this.lastUpdateTime=lt;
    }
    public void setDoReviseHP(boolean doReviseHP){
        this.doReviseHP;
    }

    public void setIsHunger(boolean isHunger){
        this.isHunger=isHunger;
    }

    public void setIsThirst(boolean isThirst){
        this.isThirst=isThirst;
    }

    public void setDecoration(Decoration decoration){
        this.decoration=decoration;
    }

    public int getHP(){
        return this.HP;
    }

    public int getHungerValue(){
        return this.hungerValue;
    }

    public int getThirstValue(){
        return this.thirstValue;
    }

    public LocalDateTime getLastUpdateTime(){
        return this.lastUpdateTime;
    }

    public boolean getIsHunger(){
        return this.isHunger;
    }
    public boolean getDoReviseHP(){
        return this.doReviseHP;
    }

    public boolean getIsThirst(){
        return this.isThirst;
    }

    public Decoration getDecoration(){
        return this.decoration;
    }

    public void eatFood(int number){
        this.hungerValue+=number*5;
        if(hungerValue>=100){
            this.hungerValue=100;
        }
        this.checkHungerAndThirstStatus();
    }

    public void drinkWater(int number){
        this.thirstValue+=number*5;
        if(thirstValue>=100){
            this.thirstValue=100;
        }
        this.checkHungerAndThirstStatus();
    }
    
    public void useMedicine(int input){
        this.hungerValue=100;
        this.thirstValue=100;
        this.HP=1000;
        this.checkHungerAndThirstStatus();
    }
    
    public void decreaseHungerAndThirstValue()//每隔15分鐘呼叫一次扣減飢渴值
    {
        this.hungerValue-=1;
        this.thirstValue-=1;
        if(this.hungerValue<0){
            this.hungerValue=0;
        }
        if(this.thirstValue<0){
            this.thirstValue=0;
        }
        if(doReviseHP){
            this.reviseHP();
            this.doReviseHP=false;
        }else{
            this.doReviseHP=true;
        }
        checkHungerAndThirstStatus();
    }

    public void reviseHP()//根據飢渴度調整HP
    {
        if((this.hungerValue>80 || this.thirstValue>80))
        {
            if (Math.min((this.hungerValue), this.thirstValue)>=80)
            {
                this.HP+=Math.max(200-this.hungerValue-thirstValue,25);
            }
            else if(this.hungerValue<60)
                this.HP-=(80-this.hungerValue)/4;
            else if(this.thirstValue<60)
                this.HP-=(80-this.thirstValue)/4;
        }else{
            this.HP-=((160-this.hungerValue-this.thirstValue)/8+Math.min(20, Math.abs(this.hungerValue-this.thirstValue)));
        }
        if(this.HP<0)
            this.HP=0;
        else if(this.HP>1000)
            this.HP=1000;
    }

    public void checkHungerAndThirstStatus()//確認寵物的饑渴狀態
    {
        if((this.hungerValue>80 || this.thirstValue>80))
        {
            if(this.hungerValue<60)
            {
                this.isHunger=true;
                this.isThirst=false;
            }
            else if(this.thirstValue<60)
            {
                this.isHunger=false;
                this.isThirst=true;
            }
        }
        else
        {
            this.isHunger=true;
            this.isThirst=true;
        }
    }
    public void updatePetStatus()//在開啟程式時補上次關掉程式後的進度
    {
        while(lastUpdateTime.plusMinutes(15).isBefore(LocalDateTime.now()))
        {
            lastUpdateTime=lastUpdateTime.plusMinutes(15);
            this.decreaseHungerAndThirstValue();
        }
    }
    public String formatCsvString(){
        return String.format(this.HP+","+this.hungerValue+","+this.thirstValue+","+this.lastUpdateTime.toString()+","+this.isHunger+","+this.isThirst+","+this.doReviseHP+","+this.wearMask+","+this.decoration);
    }
}
