package dk.krakkerz.krakheadzbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    /**
     * DAWA api id
     */
    @Id
    private String id;

    private String text;

    @OneToMany(mappedBy = "address", fetch =FetchType.LAZY)
    private Set<Person> persons = new java.util.LinkedHashSet<>();
}


