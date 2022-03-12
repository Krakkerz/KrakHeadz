package dk.krakkerz.krakheadzbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public static String index(@RequestHeader(value = "User-Agent") String userAgent) {
        return """
               <pre>
               Hello, World!
               You're accessing from %s
               The current server time is %s
               and 1 + 1 = %d
               </pre>
               """
                .formatted(userAgent, LocalDateTime.now().toString(), (1+1));
    }
}
