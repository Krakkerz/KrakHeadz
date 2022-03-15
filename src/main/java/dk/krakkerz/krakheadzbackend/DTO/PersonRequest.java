package dk.krakkerz.krakheadzbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
