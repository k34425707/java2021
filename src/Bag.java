public class Bag
{
    private int food;
    private int water;
    private int medicine;
    private boolean bowknot;
    private boolean goldChain;
    private boolean greenScarf;
    private boolean redScarf;
    private boolean mask;

    public Bag(int food,int water ,int medicine ,boolean bowknot,boolean goldChain,boolean greenScarf,boolean redScarf,boolean mask){
        this.food=food;
        this.water=water;
        this.medicine=medicine;
        this.bowknot=bowknot;
        this.goldChain=goldChain;
        this.greenScarf=greenScarf;
        this.redScarf=redScarf;
        this.mask=mask;
    }

    void setFood(int food){
        this.food=food;
    }

    void setWarter(int water){
        this.water=water;
    }

    void setMedicine(int medicine){
        this.medicine=medicine;
    }

    void setBowknot(boolean bowknot){
        this.bowknot=bowknot;
    }
    void setGoldChain(boolean goldChain){
        this.goldChain=goldChain;
    }
    void setGreenscarf(boolean greenScarf){
        this.greenScarf=greenScarf;
    }
    void setRedScarf(boolean redScarf){
        this.redScarf=redScarf;
    }
    void setMask(boolean mask){
        this.mask=mask;
    }
    int getFood(){
        return this.food;
    }
    int getWater(){
        return this.water;
    }
    int getMedicine(){
        return this.medicine;
    }
    boolean getBowknot(){
        return this.bowknot;
    }
    boolean getGoldChain(){
        return this.goldChain;
    }
    boolean getGreenScarf(){
        return this.greenScarf;
    }
    boolean getRedScarf(){
        return this.redScarf;
    }
    boolean getMask(){
        return this.mask;
    }
    public String formatCsvString()
    {
        return String.format(this.food+","+this.water+","+this.medicine+","+this.bowknot+","+this.goldChain+","+this.greenScarf+","+this.redScarf+","+this.mask);
    }
}