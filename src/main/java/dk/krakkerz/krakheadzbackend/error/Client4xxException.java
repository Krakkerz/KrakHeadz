package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Client4xxException extends ResponseStatusException {
    public Client4xxException(HttpStatus status) {
        super(status);
    }

    public Client4xxException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public Client4xxException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}

