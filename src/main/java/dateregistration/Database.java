package dateregistration;

import login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by finawei on 8/28/17.
 */
@Component
public class Database {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<Long, DateRegistration> database = new HashMap<>();
    public AtomicLong idGenerator = new AtomicLong(0);

    public void insertData(DateRegistration dateRegistration, int userID) {
        String sqlInsert = "insert into booking(booking_checkinDate, booking_checkoutDate, booking_foreignid) values (?,?,?);";
        Date checkinDate = Date.from(dateRegistration.getCheckinDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date checkoutDate = Date.from(dateRegistration.getCheckoutDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
        jdbcTemplate.update(sqlInsert, new Object[]{checkinDate, checkoutDate,userID});
        //database.put(idGenerator.getAndIncrement(), dateRegistration);
    }

    //register
    public void insertUser(User user){
        String sqlInsert="insert into user (user_name, user_password) values (?,?);";
        jdbcTemplate.update(sqlInsert, new Object[]{user.getUsername(),user.getPassword()});
    }

    //add role to user
    public void addRoleToUser(int userid) {
        String sqlInsertRole="insert into role (role_name, role_userid) values ('ROLE_USER',?);";
        jdbcTemplate.update(sqlInsertRole, new Object[]{userid});
    }

    //show all bookings
    public List<DateRegistration> showBookingDates() {
        String sqlQuery = "select booking_checkinDate,booking_checkoutDate, booking_id, user_name from booking inner join user on user_id=booking_foreignid;";
        return jdbcTemplate.query(sqlQuery, new BookingUserRowMapper());
    }
    //delete all bookings
    public void deleteData() {
        String sqlDelete = "delete from booking where booking_id >=1; ";
        jdbcTemplate.update(sqlDelete);
    }

    public void deleteUser(int userID){
        String sqlDelete = "delete from booking where booking_foreignid=?";
        jdbcTemplate.update(sqlDelete,new Object[]{userID});
    }


   // show booking from one user, role shown
    public List<DateRegistration> showBookingDates(int userID){
        String sqlQuery = "select booking_checkinDate, booking_checkoutDate, booking_id from booking where booking_foreignid =?;";
        return jdbcTemplate.query(sqlQuery,new Object[]{userID}, new BookingRowMapper());
    }

    //delete booking from one user
    public void deteleAllBookingFromOneUser (int userid) {
        String sqlDelete ="delete from booking where booking_foreignid=?;";
        jdbcTemplate.update (sqlDelete, new Object[] {userid});
    }
    //show user info
    public User getUserInfo (String username){
        try {
            String sqlQuery="SELECT user_id,user_name,user_password from user where user_id=?;";
            return jdbcTemplate.queryForObject(sqlQuery,new Object[]{username}, new UserRowMapper());}

        catch( EmptyResultDataAccessException e){
            System.out.println("error");
            return null;
        }
    }

    public User loadUserByUsername(String username){
        try {
            String sqlQuery = "Select user.user_id, user_name, user_password, role.role_name from user Inner join role on role.role_userid=user.user_id where user_name=?;";
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{username}, new UserRowMapper());
        }
        catch( EmptyResultDataAccessException e){
            System.out.println("error");
            return null;
        }
    }

    public User loadUserInfo (String username) {
        String sqlQuery="Select user_id, user_name,user_password from user where user_name=?;";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{username}, new UserInfoMapper());
    }


    public void deleteOneBooking(long id) throws InvalidBookingIDException {
        String sqlDelete = "delete from booking where booking_id=?";
        jdbcTemplate.update(sqlDelete, new Object[]{id});
    }

    public DateRegistration showOneBookingDates(long id) throws InvalidBookingIDException {
        String sqlQuery = "select booking_checkinDate, booking_checkoutDate from booking where booking_id=?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BookingRowMapper());
    }

    //get one booking
    class BookingRowMapper implements RowMapper<DateRegistration> {
        @Override
        public DateRegistration mapRow(ResultSet resultSet, int i) throws SQLException {
            DateRegistration booking = new DateRegistration();
            booking.setCheckinDate((resultSet.getDate("booking_checkinDate").toLocalDate()));
            booking.setCheckoutDate((resultSet.getDate("booking_checkoutDate").toLocalDate()));
            booking.setBookingID(resultSet.getInt("booking_id"));
            return booking;
        }
    }

    class BookingUserRowMapper extends BookingRowMapper{
        @Override
        public DateRegistration mapRow(ResultSet resultSet, int i) throws SQLException {
            DateRegistration booking=super.mapRow(resultSet,i);
            booking.setUsername(resultSet.getString("user_name"));
            return booking;
        }
    }



    //get user info
    class UserInfoMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException{
            User user= new User();
            user.setUsername(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("user_password"));
            user.setUserId(resultSet.getInt("user_id"));
            return user;
        }

    }

     class UserRowMapper extends UserInfoMapper {
       public User mapRow (ResultSet resultSet, int i) throws SQLException{
         User user = super.mapRow(resultSet, i) ;
         user.setRole(resultSet.getString("role_name"));
         return user;
       }
}


}
