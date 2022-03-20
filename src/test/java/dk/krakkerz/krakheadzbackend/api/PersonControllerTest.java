package dk.krakkerz.krakheadzbackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.krakkerz.krakheadzbackend.DTO.PersonRequest;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static Integer person1Id, person2Id;
    private static Address address1;

    @BeforeAll
    public static void setUp(@Autowired AddressRepository addressRepository, @Autowired PersonRepository personRepository) {
        address1 = Address.builder()
                .id("1")
                .text("John Hitlers Vej 69, 1849 England")
                .build();

        address1 = addressRepository.save(address1);

        person1Id = personRepository.save(new Person("emailmand1@mail.com", "firstnameX", "lastnameX", "12345678", address1)).getId();
        person2Id = personRepository.save(new Person("emailmand2@mail.com", "firstnameY", "lastnameY", "87654321", address1)).getId();
    }

    @AfterAll
    public static void tearDown(@Autowired AddressRepository addressRepository, @Autowired PersonRepository personRepository) {
        addressRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    void testGetAllPersons() throws Exception {
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
    void testGetOnePerson() throws Exception {
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
    void testAddPerson() throws Exception {
        PersonRequest newPerson = new PersonRequest("emailmand3@mail.com", "firstnameZ", "lastnameZ", "87654321");
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
    void testEditPerson() throws Exception {
        PersonRequest carToEdit = new PersonRequest("emailmand1@mail.com", "firstnameO", "lastnameO", "12345678");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/persons/" + person1Id)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(carToEdit)))
                .andDo(print())
                .andExpect(status().isOk());

        Person editedPersonFromDB = personRepository.getById(person1Id);
        assertEquals("firstnameO", editedPersonFromDB.getFirstName());
        assertEquals("lastnameO", editedPersonFromDB.getLastName());
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons/" + person1Id))
                .andDo(print())
                .andExpect(status().isOk());

        //Verify that we only have one person in the database
        assertEquals(1, personRepository.count());
    }
}
