package dk.krakkerz.krakheadzbackend.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
    }

    public static List<PersonResponse> getPersonsFromEntities(List<Person> persons) {

        return persons.stream().map(person -> new PersonResponse(person)).collect(Collectors.toList());
    }

}
