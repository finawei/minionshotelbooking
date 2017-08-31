package dateregistration;

import org.springframework.beans.factory.annotation.Autowired;
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
    private JdbcTemplate jdbcTemplate; //why not new JdbcTemplate()

    public Map<Long, DateRegistration> database = new HashMap<>();
    public AtomicLong idGenerator = new AtomicLong(0);

    public void insertData(DateRegistration dateRegistration) {
        String sqlInsert = "insert into booking(booking_checkinDate, booking_checkoutDate) values (?,?);";
        Date checkinDate = Date.from(dateRegistration.getCheckinDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date checkoutDate = Date.from(dateRegistration.getCheckoutDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
        jdbcTemplate.update(sqlInsert, new Object[]{checkinDate, checkoutDate});
        //database.put(idGenerator.getAndIncrement(), dateRegistration);
    }


    public void deleteData() {
        String sqlDelete = "delete from booking where booking_id >=1; ";
        jdbcTemplate.update(sqlDelete);
    }


    public List<DateRegistration> showBookingDates() {
        String sqlQuery = "select booking_checkinDate,booking_checkoutDate from booking";
        return jdbcTemplate.query(sqlQuery, new BookingRowMapper());
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
            return booking;
        }
    }
}
