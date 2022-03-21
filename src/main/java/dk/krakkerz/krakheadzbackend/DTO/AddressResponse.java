package dk.krakkerz.krakheadzbackend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    private String id;
    private String text;

    private AddressResponse(Address address) {
        this.id = address.getId();
        this.text = address.getText();
    }

    public static List<AddressResponse> of(List<Address> entities) {
        return entities.stream().map(AddressResponse::new).toList();
    }

    public static AddressResponse of(Address entity) {
        return new AddressResponse(entity);
    }
}
