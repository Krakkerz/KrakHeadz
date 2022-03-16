package dk.krakkerz.krakheadzbackend.entity;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private String additionalInfo;
    private String city;
    private String zipCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();
}
