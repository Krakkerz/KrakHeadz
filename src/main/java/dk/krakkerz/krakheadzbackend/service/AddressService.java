package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import dk.krakkerz.krakheadzbackend.DTO.AddressResponse;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressResponse> getAddresses() {
        List<Address> addresses = addressRepository.findAll();

        return AddressResponse.getAddressesFromEntities(addresses);

    }

    public AddressResponse getAddresses(int id) throws Exception {
        Address address = addressRepository.findById(id).orElseThrow(() -> new Exception("No addresses with provided ID found"));
        return new AddressResponse(address);
    }

    public AddressResponse addAddress(AddressRequest body) {
        Address address = addressRepository.save(new Address());
        return new AddressResponse(address);

    }

    public void deleteAddress(int id) {
        addressRepository.delete(addressRepository.getById(id));
        System.out.println("address deleted: " + id);
    }
}
