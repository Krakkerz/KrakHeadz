package service;

import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return personRepository;
    }
}
