package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.error.HobbyDoesNotExistException;
import dk.krakkerz.krakheadzbackend.error.PersonDoesNotExistException;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final HobbyRepository hobbyRepository;
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
        //jpa does not support cascade set null, so we have to fix this ourselves:
        personRepository.getById(id).setAddress(null);

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

    public PersonResponse addHobbyToPerson(Integer personId, Integer hobbyId) {
        if (!personRepository.existsById(personId))
            throw new PersonDoesNotExistException();
        if (!hobbyRepository.existsById(hobbyId))
            throw new HobbyDoesNotExistException();

        Person person = personRepository.getById(personId);
        Hobby hobby = hobbyRepository.getById(hobbyId);
        HobbyInfo hobbyInfo = new HobbyInfo(hobby, person);

        person.getHobbyInfoSet().add(hobbyInfo);

        return PersonResponse.of(personRepository.save(person));
    }
}
