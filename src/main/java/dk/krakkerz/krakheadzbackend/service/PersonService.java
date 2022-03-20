package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import dk.krakkerz.krakheadzbackend.DTO.HobbyRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.error.Client4xxException;
import dk.krakkerz.krakheadzbackend.error.FunctionalityNotImplementedException;
import dk.krakkerz.krakheadzbackend.error.PersonDoesNotExistException;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.HobbyInfoRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final HobbyInfoRepository hobbyInfoRepository;
    private final AddressRepository addressRepository;

    public List<PersonResponse> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return PersonResponse.of(persons);
    }

    public PersonResponse getPerson(Integer id) {
        if (!personRepository.existsById(id))
            throw new PersonDoesNotExistException();

        return PersonResponse.of(personRepository.getById(id));
    }

    public PersonResponse addPerson(PersonRequest body) {
        Person person = personRepository.save(body.toPerson());

        return PersonResponse.of(person);
    }

    public PersonResponse editPerson(Integer id, PersonRequest body){
        if (!personRepository.existsById(id))
            throw new PersonDoesNotExistException();

        Person person = personRepository.getById(id);
        person.setEmail(body.getEmail());
        person.setFirstName(body.getFirstName());
        person.setLastName(body.getLastName());
        person.setPhoneNumber(body.getPhoneNumber());

        return PersonResponse.of( personRepository.save(person) );
    }
    public void deletePerson(int id) {
        /*try {
            for (HobbyInfo link : hobbyInfoRepository.findAllBySpecifiedPersonEquals(id)) {
                hobbyInfoRepository.delete(link);
            }
        } catch (Exception e) {}*/

        personRepository.delete(personRepository.getById(id));
        System.out.println("person deleted with ID: " + id);
    }

    public PersonResponse addAddressToPerson(Integer personId, AddressRequest body) {
        if (!personRepository.existsById(personId))
            throw new PersonDoesNotExistException();

        if (!addressRepository.existsById(body.getId()))
            addressRepository.save(body.toAddress());

        Person person = personRepository.getById(personId);
        person.setAddress( body.toAddress() );

        return PersonResponse.of( personRepository.save(person) );
    }

    public PersonResponse addHobbyToPerson(Integer personId, HobbyRequest body) {
        if (!personRepository.existsById(personId))
            throw new PersonDoesNotExistException();

        throw new  FunctionalityNotImplementedException();
    }
}
