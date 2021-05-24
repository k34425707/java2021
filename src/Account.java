public class Account 
{
    private int year;
    private int month;
    private int date;
    private int moneySum;
    private String type;
    private String description;    
    
    public Account(int year,int month,int date,String type,String des,int moneySum)
    {
        this.year=year;
        this.month=month;
        this.date=date;
        this.moneySum=moneySum;
        this.type=type;
        this.description=des;
    }
    
    public void setYear(int y){
        this.year=y;
    }

    public void setMonth(int m){
        this.month=m;
    }

    public void setDate(int d){
        this.date=d;
    }
    
    public void setMoneySum(int MS){
        this.moneySum=MS;
    } 

    public void setType (String t){
        this.type=t;
    }

    public void setDescription(String des){
        this.description=des;
    }

    public int getYear(){
        return this.year;
    }

    public int getMonth(){
        return this.month;
    }

    public int getDate(){
        return this.date;
    }
    public int getMoneySum() {
        return this.moneySum;
    }
    public String getType()  {
        return this.type;
    }
    public String getDescription() {
        return this.description;
    }

    public String formatCsvString()
    {
        return String.format(getYear()+","+getMonth()+","+getDate()+","+getType()+","+getDescription()+","+getMoneySum());
    }
}
