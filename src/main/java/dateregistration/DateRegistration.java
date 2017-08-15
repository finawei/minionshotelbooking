package dateregistration;


import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by finawei on 8/14/17.
 */

//
public class DateRegistration {
 //   @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private LocalDate checkinDate;

    @JsonSerialize(using=CustomDateTimeSerializer.class)
 //   @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkoutDate;


    public DateRegistration(){}

    public DateRegistration(LocalDate checkinDate, LocalDate checkoutDate){
        this.checkinDate=checkinDate;
        this.checkoutDate=checkoutDate;
    }

    public void setCheckinDate(LocalDate checkinDate){
        this.checkinDate=checkinDate;
    }

    public LocalDate getCheckinDate(){
        return checkinDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate){
        this.checkoutDate=checkoutDate;
    }

    public LocalDate getCheckoutDate(){
        return checkoutDate;
    }

////getters and setters
//    public LocalDate getDate(){
//        return date;
//    }
//    public void setDate(int date){
//        this.date=date;
//    }
//
//    public int getMonth(){
//        return month;
//    }
//
//    public void setMonth(int month){
//        this.month=month;
//    }
//
//    public int getYear(){
//        return year;
//    }
//
//    public void setYear(int year){
//        this.year=year;
//    }
}
