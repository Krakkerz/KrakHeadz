package dk.krakkerz.krakheadzbackend.entity;


import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "specifiedPerson", fetch = FetchType.LAZY)
    private Set<HobbyInfo> hobbyInfoSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    public Person(String email, String firstName, String lastName, String phoneNumber, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
//        this.hobbyInfoSet = hobbyInfoSet;
        this.address = address;
    }

    public Person(PersonRequest body) {
        this.email = body.getEmail();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phoneNumber = body.getPhoneNumber();
    }
}
