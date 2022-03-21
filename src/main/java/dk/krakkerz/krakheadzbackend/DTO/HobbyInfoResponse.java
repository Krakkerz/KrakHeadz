package dk.krakkerz.krakheadzbackend.DTO;

import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HobbyInfoResponse {
    private Integer id;
    private LocalDateTime selectedDate;
    private HobbyResponse specifiedHobby;

    public HobbyInfoResponse(HobbyInfo hobbyInfo) {
        this.id = hobbyInfo.getId();
        this.selectedDate = hobbyInfo.getDateSelected();
        this.specifiedHobby = HobbyResponse.of(hobbyInfo.getSpecifiedHobby());
    }

    public static HobbyInfoResponse of(HobbyInfo entity) {
        return new HobbyInfoResponse(entity);
    }

    public static List<HobbyInfoResponse> of(List<HobbyInfo> entities) {
        return entities.stream().map(HobbyInfoResponse::new).toList();
    }
}
