package login;

import dateregistration.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by finawei on 9/4/17.
 */
@Service("userDetailsService")
public class UserService implements UserDetailsService {


    @Autowired
    private Database database;


    //this method is used to determine which user is logged in
    @Override
    public UserDetails loadUserByUsername (String username) {
      return database.loadUserByUsername(username);
    }

    public void deleteUser(int userID){
        database.deleteUser(userID);
    }

    public void addUser(User user){
        database.insertUser(user);
    }

    public User loadUserByUserId(int userID){
        return database.getUserInfo(userID);
    }


}
