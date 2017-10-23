package dateregistration;
import static org.mockito.Mockito.*;
import login.User;
import login.UserEndpoint;
import login.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by finawei on 9/15/17.
 */
@Ignore
public class UserEndPointTest {
    private UserService userService = new UserService();
    private UserEndpoint userEndpointTest;
    User user1= new User("roses","arered");
    @Before
    public void setUp(){
        System.out.println("Before");
        userEndpointTest= new UserEndpoint();
    }

    @After
    public void tearDown(){
        System.out.println("After");

    }

    @Test
    //test addUser
    private void userShouldbeAdded(){
        userService.addUser(user1);
        // assertArrayEquals();

    }
}
