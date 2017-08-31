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
 //   @JsonProperty(required = true)
    @NotNull
    private LocalDate checkinDate;

    @JsonSerialize(using=CustomDateTimeSerializer.class)
//    @JsonProperty(required = true)
    @NotNull
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


}
