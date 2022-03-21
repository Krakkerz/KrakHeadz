package dk.krakkerz.krakheadzbackend.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PersonResponse {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private AddressResponse address;

    private PersonResponse(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
        if (person.getAddress() != null) this.address = AddressResponse.of(person.getAddress());
    }

    public static PersonResponse of(Person entity) {
        return new PersonResponse(entity);
    }

    public static List<PersonResponse> of(List<Person> entities) {
        return entities.stream().map( PersonResponse::new ).toList();
    }
}
