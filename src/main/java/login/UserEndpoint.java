package login;
import dateregistration.Database;
import dateregistration.DateRegistration;
import dateregistration.InvalidBookingIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
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
//private Database database;
private UserService userService;

//@Autowired
//public UserEndpoint(Database database){
//    this.database=database;
//}

    @Autowired
public UserEndpoint(UserService userService){
    this.userService=userService;
}

public UserEndpoint(){}
//create users
    @RequestMapping(method= RequestMethod.POST, value={"/user"})
    //what is principal
     public ResponseEntity<Void> register(@RequestBody User user){
   // User currentUser= database.loadUserByUsername(principal.getName());
        userService.addUser(user);//call userservice
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//retrieve user info
    @RequestMapping(method=RequestMethod.GET,value={"/user/{userID}"})
    public ResponseEntity<User> getUserBooking(@PathVariable ("userID") int userId, Principal principal) throws InvalidUserIdException{
     //   principal.getName();//current user
        if(principal.getName()==userService.loadUserByUserId(userId).getUsername()) {
            return new ResponseEntity<>(userService.loadUserByUserId(userId), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//    @RequestMapping(method=RequestMethod.DELETE,value={"/user/userID"})
//    public ResponseEntity<Void> deleteUser(@PathVariable ("userID")int userId, Principal principal ){
//     //   User currentUser=database.loadUserByUsername(principal.getName()) ;
//        if (principal.getName()==userService.loadUserByUserId(userId).getUsername()){
//            .deleteUser(userId);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }else
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }

// auth
   // @RequestMapping(method=RequestMethod.PO)

}
