package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.error.Client4xxException;
import dk.krakkerz.krakheadzbackend.repository.HobbyInfoRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;
    HobbyInfoRepository hobbyInfoRepository;

    public PersonService(PersonRepository personRepository, HobbyInfoRepository hobbyInfoRepository) {
        this.personRepository = personRepository;
        this.hobbyInfoRepository = hobbyInfoRepository;
    }

    public List<PersonResponse> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return PersonResponse.getPersonsFromEntities(persons);
    }

    public PersonResponse getPerson(int id) {
        PersonResponse response = new PersonResponse(personRepository.findById(id).orElseThrow(() -> new Client4xxException(HttpStatus.NOT_FOUND, "no person with this id")));
        return response;
    }

    public PersonResponse addPerson(PersonRequest body) {
        Person personNew = personRepository.save(new Person(body));
        return new PersonResponse(personNew);
    }

    public PersonResponse editPerson(PersonRequest body, int id){
        Person person = personRepository.findById(id).orElseThrow(() -> new Client4xxException(HttpStatus.NOT_FOUND, "no person with this id"));
        person.setEmail(body.getEmail());
        person.setFirstName(body.getFirstName());
        person.setLastName(body.getLastName());
        person.setPhoneNumber(body.getPhoneNumber());

        personRepository.save(person);
        return new PersonResponse(person);
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
}
