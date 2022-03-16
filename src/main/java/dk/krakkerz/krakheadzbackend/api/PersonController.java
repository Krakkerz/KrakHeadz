package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/persons")
@CrossOrigin
public class PersonController {
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponse> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonResponse getPersons(@PathVariable int id) throws Exception{
        return personService.getPerson(id);
    }

    @PostMapping
    public PersonResponse addPerson(@RequestBody PersonRequest body){
        return personService.addPerson(body);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteCar(@PathVariable int id){
        personService.deletePerson(id);
    }
}

