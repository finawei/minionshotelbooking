package login;

/**
 * Created by finawei on 8/31/17.
 */
public class User {
    private String username,password;
    private int id;
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
