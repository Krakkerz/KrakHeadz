package dk.krakkerz.krakheadzbackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static int person1Id, person2Id;
    static Address address1;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
        addressRepository.deleteAll();

        address1 = new Address();
        address1.setCity("England");
        address1.setStreet("John hitler's vej");
        address1.setZipCode("1849");
        address1.setAdditionalInfo("nope");

        addressRepository.save(address1);

        person1Id = personRepository.save(new Person("emailmand1@mail.com","firstnameX","lastnameX","12345678",address1)).getId();
        person2Id = personRepository.save(new Person("emailmand2@mail.com","firstnameY","lastnameY","12345678",address1)).getId();
    }

    @Test
    void getPersons() throws Exception{             //all persons
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetPersons() throws Exception {        //single person
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/" + person1Id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(person1Id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("emailmand1@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstnameX"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastnameX"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("12345678"));

        //need to decide how to handle addresses :/, so far not added to test
    }

    @Test
    void addPerson() throws Exception{
        PersonRequest newPerson = new PersonRequest("emailmand3@mail.com","firstnameZ","lastnameZ","87654321");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(newPerson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        //Verify that it actually ended in the database
        assertEquals(3, personRepository.count());
    }

    @Test
    void editPerson() throws Exception{
        //New price and discount for the ford
        PersonRequest carToEdit = new PersonRequest("emailmand1@mail.com","firstnameO","lastnameO","12345678");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/persons/" + person1Id)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(carToEdit)))
                .andExpect(status().isOk());
        Person editedPersonFromDB = personRepository.findById(person1Id).orElse(null);
        assertEquals("firstnameO", editedPersonFromDB.getFirstName());
        assertEquals("lastnameO", editedPersonFromDB.getLastName());
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons/" + person1Id))
                .andExpect(status().isOk());
        //Verify that we only have one person in the database
        assertEquals(1, personRepository.count());
    }
}