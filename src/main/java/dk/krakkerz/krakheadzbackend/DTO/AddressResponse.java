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
    private Integer id;

    private String street;
    private String additionalInfo;
    private String zipCode;
    private String city;

    private AddressResponse(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }

    public static List<AddressResponse> of(List<Address> entities) {
        return entities.stream().map(AddressResponse::new).toList();
    }

    public static AddressResponse of(Address entity) {
        return new AddressResponse(entity);
    }
}
