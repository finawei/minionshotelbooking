//package dateregistration;
//import org.junit.*;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.Month;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//
//import java.lang.reflect.Array;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//
///**
// * Created by finawei on 8/25/17.
// */
//
//public class DateRegistrationEndpointTest {
//
//    private LocalDate checkinDate;
//    private LocalDate checkoutDate;
//    private DateRegistrationEndpoint test;
//    private Database database= new Database();
//    LocalDate checkinDate1 = LocalDate.of(2017, Month.JULY,7);
//    LocalDate checkoutDate1= LocalDate.of(2017,Month.JULY,8);
//    LocalDate checkinDate2 =LocalDate.of(2017,Month.AUGUST,9);
//    LocalDate checkoutDate2=LocalDate.of(2017,Month.AUGUST,10);
//    DateRegistration date1 =new DateRegistration(checkinDate1,checkoutDate1);
//    DateRegistration date2=new DateRegistration(checkinDate2,checkoutDate2);
//    DateRegistration date3=new DateRegistration(checkinDate1,checkoutDate2);
//    @Before
//    public void setUp(){
//        System.out.println("Before");
//        test=new DateRegistrationEndpoint(database);
//    }
//
//    @After
//    public void tearDown(){
//        System.out.println("After");
//    }
//
//
//    //test postCheckindate(), set dates into database
//    @Test
//    public void checkinDateShoudBeInsertedInTheDatebase() throws InvalidDateException {
//        test.postCheckinDate(date1);
//        List list =new ArrayList();
//        list.add(date1);
//        assertEquals("Date is inserted in Datebase",list,database.showBookingDates());
//        assertEquals("HttpStatus is CREATED",HttpStatus.CREATED,test.postCheckinDate(date1).getStatusCode());
//    }
//
//
//    //test listAllBookings
//    @Test
//    public void allBookingsShouldBeListed(){
//        database.insertData(date1);//put date in the database
//        database.insertData(date2);
//        assertEquals("Get Overview of database",database.showBookingDates(), test.listAllBooking().getBody());
//        assertEquals("HttpStatus is OK",HttpStatus.OK,test.listAllBooking().getStatusCode());
//    }
//
//
//    // test deleteAll()
//    @Test
//    public void databaseShouldBeCleared() throws InvalidDateException {
//        database.insertData(date1);
//        test.deleteAll();
//        assertEquals("Clear database",new ArrayList<>(),test.listAllBooking().getBody());
//        assertEquals("HttpStatus is OK", HttpStatus.OK,test.listAllBooking().getStatusCode());
//    }
//
//    @Test(expected= InvalidDateException.class)
//    public void whenCheckoutDateIsBeforeCheckinDateThenExceptionIsThrown()throws InvalidDateException{
//    DateRegistration dates= new DateRegistration(checkinDate2,checkoutDate1);
//    test.postCheckinDate(dates);
//    }
//
//
//    //test deleteOne()
//    @Test
//    public void OneBookingShouldBeDeletedWhenGivenValidID () throws InvalidBookingIDException {
//        database.insertData(date1);
//        database.insertData(date2);
//        test.deleteOne(1);
//        List list=new ArrayList<>();
//        list.add(date1);
//        List<DateRegistration> result = database.showBookingDates();
//        assertEquals("One Booking is deleted",1, result.size());
//        assertEquals("The second booking is deleted",list, result);
//    }
//
//    @Test(expected= InvalidBookingIDException.class)
//    public void whenDeletingWithInvalidIdThrowException() throws InvalidBookingIDException {
//        database.insertData(date1);
//        database.insertData(date2);
//        test.deleteOne(3);
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
//}
