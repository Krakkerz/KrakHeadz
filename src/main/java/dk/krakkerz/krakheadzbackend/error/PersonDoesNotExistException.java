package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersonDoesNotExistException extends ResponseStatusException {
    public PersonDoesNotExistException() {
        super(HttpStatus.NOT_ACCEPTABLE);
    }

    public PersonDoesNotExistException(String reason) {
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }

    public PersonDoesNotExistException(String reason, Throwable cause) {
        super(HttpStatus.NOT_ACCEPTABLE, reason, cause);
    }
}
