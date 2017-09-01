package login;
import dateregistration.Database;
import dateregistration.DateRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * Created by finawei on 8/31/17.
 */

/**
 * This class is for creating new users, retrieving users, validating users
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserEndpoint {
private Database database;
@Autowired
public UserEndpoint(Database database){
    this.database=database;
}

public UserEndpoint(){}
//create users
    @RequestMapping(method= RequestMethod.POST, value={"/user"})
     public ResponseEntity<Void> register(@RequestBody User user){
     database.insertData(user);
     return new ResponseEntity<>(HttpStatus.CREATED);
    }

//retrieve user info
    @RequestMapping(method=RequestMethod.GET,value={"/user/{userID}"})
    public ResponseEntity<User> getUserBooking(@PathVariable ("userID") int userID){
         return new ResponseEntity<>(database.getUserInfo(userID),HttpStatus.OK);
    }
// auth
   // @RequestMapping(method=RequestMethod.PO)

}
