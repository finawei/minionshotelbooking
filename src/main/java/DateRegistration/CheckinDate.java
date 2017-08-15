package DateRegistration;

/**
 * Created by finawei on 8/14/17.
 */
public class CheckinDate {
    private int date;
    private int month;
    private int year;



    public CheckinDate(){}

    public CheckinDate (int date, int month, int year){
        this.setDate(date);
        this.setMonth(month);
        this.setYear(year);
    }

//getters and setters
    public int getDate(){
        return date;
    }
    public void setDate(int date){
        this.date=date;
    }

    public int getMonth(){
        return month;
    }

    public void setMonth(int month){
        this.month=month;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year=year;
    }
}
