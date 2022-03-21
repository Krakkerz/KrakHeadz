package dk.krakkerz.krakheadzbackend.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyResponse {
    private int id;
    private String name;
    private String description;

    public HobbyResponse(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public static List<HobbyResponse> getHobbiesFromEntities(List<Hobby> hobbies) {

        return hobbies.stream().map(hobby -> new HobbyResponse(hobby)).collect(Collectors.toList());
    }

    public static HobbyResponse of(Hobby entity) {
        return new HobbyResponse(entity);
    }
}
