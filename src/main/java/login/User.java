package login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by finawei on 8/31/17.
 */
public class User implements UserDetails {
    private String username,password;
    private int id;
    private boolean enabled;
    public User(){}
    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
    public User(int id, String username, String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }


    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setUserId(int id){
        this.id=id;
    }
    public int getUserId(){
        return id;
    }
}
