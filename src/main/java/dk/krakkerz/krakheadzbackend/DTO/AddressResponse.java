package dk.krakkerz.krakheadzbackend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.krakkerz.krakheadzbackend.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    private int id;

    private String street;
    private String additionalInfo;
    private String zipCode;
    private String city;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }

    public static List<AddressResponse> getAddressFromEntities(List<Address> addresses) {

        return addresses.stream().map(address -> new AddressResponse(address)).collect(Collectors.toList());
    }
}
