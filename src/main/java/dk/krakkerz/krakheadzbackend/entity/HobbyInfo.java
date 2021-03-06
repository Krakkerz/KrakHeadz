package dk.krakkerz.krakheadzbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HobbyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dateSelected;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hobby specifiedHobby;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person specifiedPerson;

    public HobbyInfo(Hobby specifiedHobby, Person specifiedPerson) {
        this.specifiedHobby = specifiedHobby;
        this.specifiedPerson = specifiedPerson;
    }
}
