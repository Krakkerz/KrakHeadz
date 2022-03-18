package dk.krakkerz.krakheadzbackend.DTO;

import dk.krakkerz.krakheadzbackend.entity.Address;
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
    private String city;
    private String zipCode;
    private String additionalInfo;

    public Address toAddress() {
        Address address = new Address();

        address.setStreet( this.street );
        address.setCity( this.city );
        address.setZipCode( this.zipCode );
        address.setAdditionalInfo( this.additionalInfo );

        return address;
    }
}
