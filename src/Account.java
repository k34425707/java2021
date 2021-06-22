import java.time.LocalDate;

public class Account 
{
    private LocalDate date;
    private int moneySum;
    private String type;
    private String description;    
    private boolean isExpenditure;//if為支出，則為true
    
    public Account(String date,String type,String des,int moneySum,boolean isExpenditure )
    {
        this.date=LocalDate.parse(date);
        this.moneySum=moneySum;
        this.type=type;
        this.description=des;
        this.isExpenditure=isExpenditure;
    }

    public Account(int year,int month,int day,String type,String des,int moneySum,boolean isExpenditure )
    {
        this.date=LocalDate.of(year,month,day);
        this.moneySum=moneySum;
        this.type=type;
        this.description=des;
        this.isExpenditure=isExpenditure;
    }
    
    public void setdate(LocalDate date){
        this.date=date;
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

    public  void setIsExpenditure(boolean ex){
        this.isExpenditure=ex;
    }

    public int getYear(){
        return this.date.getYear();
    }

    public int getMonth(){
        return this.date.getMonthValue();
    }

    public int getDay(){
        return this.date.getDayOfMonth();
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
    public boolean getIsExpenditure(){
        return  this.isExpenditure;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String formatCsvString()
    {
        return String.format(this.getYear()+","+this.getMonth()+","+this.getDay()+","+this.getType()+","+this.getDescription()+","+this.getMoneySum()+","+this.getIsExpenditure());
    }
    public String formatExportCsvString()
    {
        String output= String.format(this.getYear()+","+this.getMonth()+","+this.getDay()+","+this.getType()+","+this.getDescription()+","+this.getMoneySum()+",");
        if(this.getIsExpenditure())
        {
            output+="支出";
        }
        else
        {
            output+="收入";
        }
        return output;
    }
}
