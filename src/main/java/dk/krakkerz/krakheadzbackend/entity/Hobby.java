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
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String category;
    private String type;

    @OneToMany(mappedBy = "specifiedHobby", fetch = FetchType.LAZY)
    private Set<HobbyInfo> hobbyInfoSet = new HashSet<>();
}
