package dk.krakkerz.krakheadzbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;
}
