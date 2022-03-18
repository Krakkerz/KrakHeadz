package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AddressDoesNotExistException extends ResponseStatusException {
    public AddressDoesNotExistException() {
        super(HttpStatus.NOT_FOUND);
    }

    public AddressDoesNotExistException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public AddressDoesNotExistException(String reason, Throwable cause) {
        super(HttpStatus.NOT_FOUND, reason, cause);
    }
}
