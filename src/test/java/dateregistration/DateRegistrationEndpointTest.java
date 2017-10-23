package dateregistration;
import login.User;
import org.junit.*;
import static org.mockito.Mockito.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import javax.validation.constraints.AssertTrue;
import java.security.Principal;
import java.time.Month;
import java.util.*;
import java.util.List;
import static org.junit.Assert.*;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by finawei on 8/25/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DateRegistrationEndpointTest {

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private DateRegistrationEndpoint dateRegistrationEndpointtest;
    private Database database= new Database();

    @Mock
    private Principal principalMock;
    @Mock
    private Database databaseMock;

    @Mock
    private User user;

    //tells mockito to create the mocks based on the @mock annotation
    @Rule
    public MockitoRule mokitoRule = MockitoJUnit.rule();

    LocalDate checkinDate1 = LocalDate.of(2017, Month.JULY,7);
    LocalDate checkoutDate1= LocalDate.of(2017,Month.JULY,8);
    LocalDate checkinDate2 =LocalDate.of(2017,Month.AUGUST,9);
    LocalDate checkoutDate2=LocalDate.of(2017,Month.AUGUST,10);
    DateRegistration date1 =new DateRegistration(checkinDate1,checkoutDate1,1);
    DateRegistration date2=new DateRegistration(checkinDate2,checkoutDate2,2);
    DateRegistration date3=new DateRegistration(checkinDate1,checkoutDate2,3);
    DateRegistration date4 =new DateRegistration(checkoutDate2,checkinDate1,4);


    @Before
    public void setUp(){
        System.out.println("Before");
        dateRegistrationEndpointtest=new DateRegistrationEndpoint(databaseMock);
        when(databaseMock.loadUserByUsername(any(String.class))).thenReturn(user);
        when(user.getUserId()).thenReturn(1);
        List<DateRegistration> bookings = new ArrayList<>();
        bookings.add(date1);
        bookings.add(date2);
        when(databaseMock.showBookingDates()).thenReturn(bookings);
        when(databaseMock.showBookingDates(any(int.class))).thenReturn(bookings);

    }
    @After
    public void tearDown(){
        System.out.println("After");
    }

    @Test
    public void testMockito(){
        when(principalMock.getName()).thenReturn("roses");
        assertEquals("Mockito works :)","roses",principalMock.getName());
    }

    //test postCheckindate()
    @Test
    public void HttpCreatedWhenCheckinDateIsBeforeCheckoutDate() throws InvalidDateException {
        when(principalMock.getName()).thenReturn("roses");
        ResponseEntity<Void> result = dateRegistrationEndpointtest.postCheckinDate(date1,"roses",principalMock);
        verify(databaseMock,atLeastOnce()).insertData(date1,1);
        assertEquals("HttpStatus is CREATED",HttpStatus.CREATED,result.getStatusCode());
    }

    @Test
    public void HttpUnautherizedWhenPrincipalDoesntMatchUser() throws InvalidDateException {
        when(principalMock.getName()).thenReturn("Jon");
        ResponseEntity<Void> result = dateRegistrationEndpointtest.postCheckinDate(date1,"roses",principalMock);
        assertEquals(HttpStatus.UNAUTHORIZED,result.getStatusCode());
    }

    @Test
    public void HttpBadRequestWhenCheckinDateAfterCheckoutDate() throws InvalidDateException{
        when(principalMock.getName()).thenReturn("Arya");
        ResponseEntity<Void> result = dateRegistrationEndpointtest.postCheckinDate(date4,"Arya",principalMock);
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
    }


    //test listAllBookings
    @Test
    public void allBookingsFromDatabaseIsShown(){
        ResponseEntity<List<DateRegistration>> result = dateRegistrationEndpointtest.listAllBooking();
        assertEquals("HttpStatus is OK",HttpStatus.OK,result.getStatusCode());
    }


    //test listAllBookingsFromOneUser
    @Test
    public void bookingsFromOneUserListedWhenAutherized () {
        when(principalMock.getName()).thenReturn("Cersi");
        ResponseEntity<List<DateRegistration>> result = dateRegistrationEndpointtest.listAllBookingFromOneUser("Cersi",principalMock);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void bookingsFromUserListWhenUnautherized(){
        when(principalMock.getName()).thenReturn("Jamie");
        ResponseEntity<List<DateRegistration>> result = dateRegistrationEndpointtest.listAllBookingFromOneUser("Cersi",principalMock);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }



//    @Test(expected= InvalidBookingIDException.class)
//    public void whenDeletingWithInvalidIdThrowException() throws InvalidBookingIDException {
//        //when(databaseMock.deleteOneBooking(any(int.class))).thenReturn();
//        when(principalMock.getName()).thenReturn("roses");
//        dateRegistrationEndpointtest.deleteOne("roses",3,principalMock);
//    }
//
//    //test showOneBooking()
//    @Test
//    public void showSpecificBookingWhenGiveValidID() throws InvalidBookingIDException {
//        database.insertData(date1);
//        database.insertData(date2);
//
//        ResponseEntity<DateRegistration> result = test.viewOneBooking(0);
//
//        assertEquals("View the first booking",date1, result.getBody());
//        assertEquals("HttpStatus",HttpStatus.OK, result.getStatusCode());
//    }
//    @Test(expected= InvalidBookingIDException.class)
//    public void whenReferingBookingWithInvidBookingThrowException() throws InvalidBookingIDException {
//        database.insertData(date1);
//        database.insertData(date2);
//        test.viewOneBooking(2);
//    }
}
