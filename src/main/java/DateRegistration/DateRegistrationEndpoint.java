package DateRegistration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by finawei on 8/14/17.
 */

//create checkin date
@RestController
public class DateRegistrationEndpoint {
    private int date = 1;
    private int month = 1;
    private int year = 2017;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(method = RequestMethod.POST, value = {"/dateregistration"})
    //@RequestMapping(value={"/dateregistration"})
    public ResponseEntity<CheckinDate> checkindate(@RequestBody CheckinDate date) {
        System.out.println("=========Checkin date==========");
        CheckinDate checkinDate = new CheckinDate(1, 1, 2017);
        return new ResponseEntity<CheckinDate>(checkinDate, HttpStatus.CREATED);

    }


}
