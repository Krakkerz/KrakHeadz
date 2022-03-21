package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.DTO.PersonResponse;
import dk.krakkerz.krakheadzbackend.service.PersonService;
import org.springframework.web.bind.annotation.*;

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
    public PersonResponse getPersons(@PathVariable int id) {
        return personService.getPerson(id);
    }

    @PostMapping
    public PersonResponse addPerson(@RequestBody PersonRequest body){
        return personService.addPerson(body);
    }

    @PutMapping("/{id}")
    public PersonResponse editCar(@PathVariable Integer id, @RequestBody PersonRequest body){
        return personService.editPerson(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        personService.deletePerson(id);
    }


    // Address Stuff
    @PostMapping("/{personId}/address")
    public PersonResponse addAddressToPerson(@PathVariable Integer personId, @RequestBody AddressRequest body){
        return personService.addAddressToPerson(personId, body);
    }


    // Hobby Stuff
    @PostMapping("/{personId}/hobby/{hobbyId}")
    public PersonResponse addHobbyToPerson(@PathVariable Integer personId, @PathVariable Integer hobbyId){
        return personService.addHobbyToPerson(personId, hobbyId);
    }


}

