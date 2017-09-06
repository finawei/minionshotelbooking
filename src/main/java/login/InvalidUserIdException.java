package login;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
/**
 * Created by finawei on 9/4/17.
 */
public class InvalidUserIdException extends Exception {
    public ResponseEntity<Void> InvalidUserIdException () {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
