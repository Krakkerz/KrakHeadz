package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }
}

