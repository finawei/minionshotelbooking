package dateregistration;

//import com.sun.org.glassfish.gmbal.ParameterNames;

import login.User;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    private Database database;

   // @CrossOrigin(origins = "http://localhost:4200"
    @Autowired
    public DateRegistrationEndpoint(Database database){
        this.database=database;
    }

    //set dates
    @RequestMapping(method = RequestMethod.POST, value = {"/user/{username}/dateregistration"})
    public ResponseEntity<Void> postCheckinDate(@RequestBody @Valid DateRegistration dateRegistration, @PathVariable("username") String username
    , Principal principal) {
        int userid= database.loadUserByUsername(username).getUserId();
        if (principal.getName().equals(username) ) {
            if (dateRegistration.getCheckinDate().isBefore(dateRegistration.getCheckoutDate())) {
                database.insertData(dateRegistration, userid);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    //get booking overview ALL
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = {"/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBooking() {
        return new ResponseEntity<>(database.showBookingDates(), HttpStatus.OK);
    }

    //get booking overview ONE USER
    @RequestMapping(method =RequestMethod.GET, value={"user/{username}/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBookingFromOneUser (@PathVariable ("username") String username, Principal principal) {
        if(principal.getName().equals(username)){
            int userid= database.loadUserByUsername(username).getUserId();
            return new ResponseEntity<>(database.showBookingDates(userid),HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //delete all booking from one user
    @RequestMapping(method = RequestMethod.DELETE, value = {"/user/{username}/dateregistration"})
    public ResponseEntity<Void> deleteAll(@PathVariable ("username") String username, Principal principal) {
        int userid= database.loadUserByUsername(username).getUserId();
        if (principal.getName().equals(username)){
        database.deteleAllBookingFromOneUser(userid);
        System.out.println("All bookings from "+ username +" are deleted.");
        return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }

    //delete one booking from one user
    @RequestMapping(method = RequestMethod.DELETE, value = {"/user/{username}/dateregistration/{id}"})
    public ResponseEntity<Void> deleteOne(@PathVariable ("username") String username,
                                          @PathVariable ("id") int bookingid,
                                          Principal principal) throws InvalidBookingIDException { int userid= database.loadUserByUsername(username).getUserId();
        if(principal.getName().equals(username)) {
            database.deleteOneBooking(bookingid);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }


}
