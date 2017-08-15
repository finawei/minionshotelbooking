package dateregistration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get booking overview
    @RequestMapping(method=RequestMethod.GET, value={"/dateregistration"})
    public ResponseEntity<List<DateRegistration>> listAllBooking(){
        List<DateRegistration> dateRegistrations=new ArrayList<>(database.values());
        System.out.println("This is a test "+ dateRegistrations);
        return new ResponseEntity<>(dateRegistrations, HttpStatus.OK);
    }


}
