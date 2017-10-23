package login;

import dateregistration.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User user=database.loadUserByUsername(username);
//        List<GrantedAuthority> authorities =
//                buildUserAuthority(user.getRole());
        return user;
    }


    public void deleteUser(int userID){
        database.deleteUser(userID);
    }

    public void addUser(User user){
        database.insertUser(user);

    }
    public boolean isExist(String username){
       if(database.loadUserByUsername(username) == null) {
           return false;
       }else return true;
    }
    public void addRole(int userid){
        database.addRoleToUser(userid);
    }
//    public User loadUserByUserId(String username){
//        return database.getUserInfo(username);
//    }
    public int loadUserIdByUsername (String username) {
        return database.loadUserByUsername(username).getUserId();
    }
    public int loadId(String username){
        return database.loadUserInfo(username).getUserId();
    }


}
