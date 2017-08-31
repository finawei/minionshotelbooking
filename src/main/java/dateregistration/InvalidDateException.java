package dateregistration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by finawei on 8/28/17.
 */
public class InvalidDateException extends Exception {
    public ResponseEntity<Void> InvalidDateException (){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
