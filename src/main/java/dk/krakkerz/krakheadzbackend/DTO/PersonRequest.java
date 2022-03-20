package dk.krakkerz.krakheadzbackend.DTO;

import dk.krakkerz.krakheadzbackend.entity.Person;
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

    public Person toPerson() {
        Person person = new Person();

        person.setEmail( this.email );
        person.setFirstName( this.firstName );
        person.setLastName( this.lastName );
        person.setPhoneNumber( this.phoneNumber );

        return person;
    }
}
