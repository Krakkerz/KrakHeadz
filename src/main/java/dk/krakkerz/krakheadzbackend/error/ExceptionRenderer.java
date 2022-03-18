package dk.krakkerz.krakheadzbackend.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


/**
 * Spring Boot will consult all the classes marked as @ControllerAdvice whenever
 * an exception is thrown. If a class is found with a relevant @ExceptionHandler
 * then Spring Boot will run the handler method.
 *
 * If we always make sure our application code throws Exceptions extending the
 * ResponseStatusException class then we can render exceptions as JSON which can
 * be useful on the frontend, maybe.
 */

@RestControllerAdvice
public class ExceptionRenderer extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ResponseStatusException exception) {
        ErrorResponse response = new ErrorResponse(
                request.getRequestURI(),
                exception.getStatus().value(),
                exception.getClass().getSimpleName(),
                exception.getReason(),
                exception.getCause()
        );

        return new ResponseEntity<>(response, exception.getStatus());
    }

    private record ErrorResponse(String path, Integer status, String exception, String reason, Throwable cause) {}
}
