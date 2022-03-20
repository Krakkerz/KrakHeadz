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
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "specifiedHobby", fetch = FetchType.LAZY)
    private Set<HobbyInfo> hobbyInfoSet = new HashSet<>();

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
