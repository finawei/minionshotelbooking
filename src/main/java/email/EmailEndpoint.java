package email;

import dateregistration.Database;
import dateregistration.DateRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by finawei on 9/27/17.
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmailEndpoint {
    @Autowired
    public Database database;

    @Autowired
    EmailServiceImpl email;

    @RequestMapping(method= RequestMethod.GET, value = {"/admin/email"})
    public ResponseEntity <List<DateRegistration>> sendEmail () {
        List<DateRegistration> bookings = database.showBookingDates();
        List<String> bookingsString= new ArrayList<>();
        for (int i=0; i<bookings.size(); i++){
            bookingsString.add(bookings.get(i).toString());
        }
        String bookingsSeparated = String.join("\n", bookingsString);
        email.sendSimpleMessage("weifeng0413@hotmail.com","improve",bookingsSeparated);
        return new ResponseEntity<>(bookings, HttpStatus.OK);

}
}
