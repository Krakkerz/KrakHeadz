package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.error.Client4xxException;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return PersonResponse.getPersonsFromEntities(persons);
    }

    public PersonResponse getPerson(int id) {
        PersonResponse response = new PersonResponse(personRepository.findById(id).orElseThrow(() ->new Client4xxException("no person with this id")));
        return response;
    }

    public PersonResponse addPerson(PersonRequest body) {
        Person personNew = personRepository.save(new Person(body));
        return new PersonResponse(personNew);
    }

    public void deletePerson(int id) {
        personRepository.delete(personRepository.getById(id));
        System.out.println("person deleted with ID: " + id);
    }
}
