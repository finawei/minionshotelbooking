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
    private UserService userService;
    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    public UserEndpoint() {}

    //create users
    @RequestMapping(method = RequestMethod.POST, value = {"/user"})
    public ResponseEntity<Void> register(@RequestBody User user) {

        if(userService.isExist(user.getUsername()) ==false ){
       userService.addUser(user);
       int userID = userService.loadId(user.getUsername());
        userService.addRole(userID);
        return new ResponseEntity<>(HttpStatus.CREATED);}
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/user/{username}"})
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable("username") String username, Principal principal) throws InvalidUserIdException {
        if (principal.getName().equals(username)) {
            return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/user/{username}"})
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username, Principal principal) {
        int userid = userService.loadUserIdByUsername(username);
        if (principal.getName().equals(username)) {
            userService.deleteUser(userid);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }


}
