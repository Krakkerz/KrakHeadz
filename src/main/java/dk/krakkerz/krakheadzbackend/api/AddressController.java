package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.AddressResponse;
import dk.krakkerz.krakheadzbackend.service.AddressService;
import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/address")
@CrossOrigin
public class AddressController {
    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponse> getAddresses(){
        return addressService.getAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddresses(@PathVariable int id) throws Exception {
        return addressService.getAddresses(id);
    }

    @PostMapping
    public AddressResponse addAddress(@RequestBody AddressRequest body){
        return addressService.addAddress(body);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteAddress(@PathVariable int id){
        addressService.deleteAddress(id);
    }
}
