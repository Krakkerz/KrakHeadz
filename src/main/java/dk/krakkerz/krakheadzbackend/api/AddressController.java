package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.AddressResponse;
import dk.krakkerz.krakheadzbackend.service.AddressService;
import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddresses(@PathVariable String id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public AddressResponse addAddress(@RequestBody AddressRequest body) {
        return addressService.addAddress(body);
    }

    @PutMapping("/{id}")
    public AddressResponse editAddress(@PathVariable String id, @RequestBody AddressRequest body) {
        return addressService.editAddress(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable String id){
        addressService.deleteAddress(id);
    }
}
