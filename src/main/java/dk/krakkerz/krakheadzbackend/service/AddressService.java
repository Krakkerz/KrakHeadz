package dk.krakkerz.krakheadzbackend.service;

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

        return AddressResponse.getAddressFromEntities(addresses);

    }
}

