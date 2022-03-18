package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FunctionalityNotImplementedException extends ResponseStatusException {
    public FunctionalityNotImplementedException() {
        super(HttpStatus.NOT_IMPLEMENTED);
    }

    public FunctionalityNotImplementedException(String reason) {
        super(HttpStatus.NOT_IMPLEMENTED, reason);
    }

    public FunctionalityNotImplementedException(String reason, Throwable cause) {
        super(HttpStatus.NOT_IMPLEMENTED, reason, cause);
    }
}
