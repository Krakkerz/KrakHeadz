package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import dk.krakkerz.krakheadzbackend.DTO.AddressResponse;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.error.AddressDoesNotExistException;
import dk.krakkerz.krakheadzbackend.error.FunctionalityNotImplementedException;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public List<AddressResponse> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return AddressResponse.of(addresses);

    }

    public AddressResponse getAddressById(String id) {
        boolean addressDoesExist = addressRepository.existsById(id);
        if (!addressDoesExist) throw new AddressDoesNotExistException();

        Address address = addressRepository.getById(id);
        return AddressResponse.of(address);
    }

    public AddressResponse addAddress(AddressRequest body) {
        Address address = addressRepository.save(body.toAddress());

        return AddressResponse.of(address);
    }

    public AddressResponse editAddress(String id, AddressRequest body) {
        boolean addressDoesExist = addressRepository.existsById(id);
        if (!addressDoesExist) throw new AddressDoesNotExistException();

        // Get the entity from the repository and update all the non-null fields
        throw new FunctionalityNotImplementedException();
    }

    public void deleteAddress(String id) {
        if (!addressRepository.existsById(id))
            throw new AddressDoesNotExistException();

        Address address = addressRepository.getById(id);
        address.getPersons().forEach( person -> person.setAddress( null ) );
        personRepository.saveAll( address.getPersons() );

        addressRepository.deleteById(id);
    }
}
