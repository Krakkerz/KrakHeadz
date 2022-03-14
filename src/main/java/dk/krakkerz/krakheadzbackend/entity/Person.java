package dk.krakkerz.krakheadzbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;

    @OneToMany(mappedBy = "specifiedPerson")
    private Set<HobbyInfo> hobbyInfoSet;

    @ManyToOne
    private Address address;
}
