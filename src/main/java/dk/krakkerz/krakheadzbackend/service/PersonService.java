package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.entity.Person;
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
}
