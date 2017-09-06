package dateregistration;

//import com.sun.org.glassfish.gmbal.ParameterNames;

import login.User;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by finawei on 8/14/17.
 */

//create checkin date
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DateRegistrationEndpoint {

//    private Map<Long, DateRegistration> database = new HashMap<>();
//
//    private AtomicLong idGenerator = new AtomicLong(0);
    private Database database;

   // @CrossOrigin(origins = "http://localhost:4200"
    @Autowired
    public DateRegistrationEndpoint(Database database){
        this.database=database;
    }

    //set dates
    @RequestMapping(method = RequestMethod.POST, value = {"/user/{userID}/dateregistration"})
    public ResponseEntity<Void> postCheckinDate(@RequestBody @Valid DateRegistration dateRegistration, @PathVariable("userID") int userID
    , Principal principal) throws InvalidDateException {
        User currentUser=database.loadUserByUsername(principal.getName());
        if (currentUser.getUserId()==userID ) {
            if (dateRegistration.getCheckinDate().isBefore(dateRegistration.getCheckoutDate())) {
                System.out.println("=========Checkin and Checkout date==========");
                System.out.println("Checkin Date: " + dateRegistration.getCheckinDate());
                System.out.println("Checkout Date: " + dateRegistration.getCheckoutDate());
                database.insertData(dateRegistration, userID);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                //throw new InvalidDateException();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    //get booking overview from
    @RequestMapping(method = RequestMethod.GET, value = {"/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBooking() {
        System.out.println("Booking Overview " + database.showBookingDates());
        return new ResponseEntity<>(database.showBookingDates(), HttpStatus.OK);
    }

    //get booking overview from one user
    @RequestMapping(method = RequestMethod.GET, value = {"user/{userID}/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBooking(@PathVariable ("userID") int userID, Principal principal) {
        User currentUser= database.loadUserByUsername(principal.getName());
        if(currentUser.getUserId()==userID) {
            System.out.println("Booking Overview " + database.showBookingDates(userID));
            return new ResponseEntity<>(database.showBookingDates(userID), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //delete all booking from one user
    @RequestMapping(method = RequestMethod.DELETE, value = {"/user/{userID}/dateregistration"})
    public ResponseEntity<Void> deleteAll(@PathVariable ("userID") int userID, Principal principal) {
        User currentUser= database.loadUserByUsername(principal.getName());
        if (currentUser.getUserId()==userID){
        database.deleteData();
        System.out.println("All bookings are deleted.");
        return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }

    //delete one booking
    @RequestMapping(method = RequestMethod.DELETE, value = {"/dateregistration/{id}"})
    public ResponseEntity<Void> deleteOne(@PathVariable long id) throws InvalidBookingIDException {
        database.deleteOneBooking(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //show one booking
    @RequestMapping(method = RequestMethod.GET, value = "/dateregistration/{id}")
    public ResponseEntity<DateRegistration> viewOneBooking(@PathVariable("id") long id) throws InvalidBookingIDException {
        database.showOneBookingDates(id);
        return new ResponseEntity<>(database.showOneBookingDates(id), HttpStatus.OK);
    }

}
