package dk.krakkerz.krakheadzbackend.DTO;

import dk.krakkerz.krakheadzbackend.entity.Address;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String id;
    private String text;

    public Address toAddress() {
        Address address = new Address();

        if (this.id != null) address.setId( this.id );
        if (this.text != null) address.setText( this.text );

        return address;
    }
}
