package dateregistration;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by finawei on 8/14/17.
 */

//
public class DateRegistration {

    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @NotNull
    private LocalDate checkinDate;

    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @NotNull
    private LocalDate checkoutDate;
    private int bookingID;
    private String username;

    public DateRegistration(){}
    public DateRegistration(LocalDate checkinDate, LocalDate checkoutDate, int bookingID){
        this.checkinDate=checkinDate;
        this.checkoutDate=checkoutDate;
        this.bookingID = bookingID;
    }

    public DateRegistration (LocalDate checkinDate, LocalDate checkoutDate, int bookingID, String username){
        this.checkinDate=checkinDate;
        this.checkoutDate=checkoutDate;
        this.bookingID = bookingID;
        this.username= username;
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

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID () {
        return bookingID;
    }

    public void setUsername (String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        String booking = this.getUsername()+ " "+ this.getCheckinDate()+" - "+ this.getCheckoutDate();
        return  booking;
    }
}
