package dk.krakkerz.krakheadzbackend.DTO;


import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<HobbyInfoResponse> hobbyInfos = new ArrayList<>();

    private PersonResponse(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
        if (person.getAddress() != null) this.address = AddressResponse.of(person.getAddress());
        if (person.getHobbyInfoSet() != null) this.hobbyInfos = HobbyInfoResponse.of(person.getHobbyInfoSet().stream().toList());
    }

    public static PersonResponse of(Person entity) {
        return new PersonResponse(entity);
    }

    public static List<PersonResponse> of(List<Person> entities) {
        return entities.stream().map( PersonResponse::new ).toList();
    }
}
