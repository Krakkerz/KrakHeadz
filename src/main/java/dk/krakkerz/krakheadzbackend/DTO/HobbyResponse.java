package dk.krakkerz.krakheadzbackend.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyResponse {
    private Integer id;
    private String name;
    private String description;
    private String category;
    private String type;

    public HobbyResponse(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
    }

    public static HobbyResponse of(Hobby entity) {
        return new HobbyResponse(entity);
    }

    public static List<HobbyResponse> of(List<Hobby> entities) {
        return entities.stream().map(HobbyResponse::new).toList();
    }
}
