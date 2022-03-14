package dk.krakkerz.krakheadzbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HobbyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime dateSelected;

    @ManyToOne
    private Hobby specifiedHobby;

    @ManyToOne
    private Hobby specifiedPerson;
}
