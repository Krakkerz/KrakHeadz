package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HobbyDoesNotExistException extends ResponseStatusException {
    public HobbyDoesNotExistException() {
        super(HttpStatus.NOT_ACCEPTABLE);
    }

    public HobbyDoesNotExistException(String reason) {
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }

    public HobbyDoesNotExistException(String reason, Throwable cause) {
        super(HttpStatus.NOT_ACCEPTABLE, reason, cause);
    }
}
