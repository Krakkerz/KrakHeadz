package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.HttpStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class Client4xxException extends RuntimeException{
    HttpStatus status;
    public Client4xxException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
    public Client4xxException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

