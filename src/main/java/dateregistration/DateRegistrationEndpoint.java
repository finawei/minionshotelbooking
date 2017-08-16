package dateregistration;

//import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by finawei on 8/14/17.
 */

//create checkin date
@RestController
public class DateRegistrationEndpoint {


    private Map<Long, DateRegistration> database = new HashMap<>();

    private AtomicLong idGenerator = new AtomicLong(0);

    @CrossOrigin(origins = "http://localhost:8080")

    //set dates
    @RequestMapping(method = RequestMethod.POST, value = {"/dateregistration"})
    public ResponseEntity<Void> checkindate(@RequestBody DateRegistration dateRegistration) {
        System.out.println("=========Checkin and Checkout date==========");
        System.out.println("Checkin Date: "+dateRegistration.getCheckinDate());
        database.put(idGenerator.getAndIncrement(), dateRegistration);

     //validate the date is correct
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get booking overview
    @RequestMapping(method=RequestMethod.GET, value={"/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBooking(){
        List<DateRegistration> dateRegistrations=new ArrayList<>(database.values());
        System.out.println("This is a test "+ dateRegistrations);
        return new ResponseEntity<>(dateRegistrations, HttpStatus.OK);
    }

    //get one booking
    @RequestMapping(method=RequestMethod.GET,value="/dateregistration/{id}")
    public ResponseEntity<DateRegistration> dateRegistration(@PathVariable("id") long id){
        DateRegistration dateRegistration=database.get(id);
        return new ResponseEntity<>(dateRegistration,HttpStatus.OK);

     //throw an exception if the id is not in datebase

    }

    //delete all booking
    @RequestMapping(method=RequestMethod.DELETE,value={"/dateregistration"})
    public ResponseEntity<Void>  deleteAll (){
         database.clear();
        //need to add delete
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete one booking
    @RequestMapping(method=RequestMethod.DELETE,value={"/dateregistration/{id}"})
    public ResponseEntity<Void> deleteOne(@PathVariable("id") long id){
        database.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
