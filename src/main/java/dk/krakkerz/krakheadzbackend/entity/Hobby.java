package dk.krakkerz.krakheadzbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "specifiedHobby")
    private Set<HobbyInfo> hobbyInfoSet;

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
