package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;


    static Address address = new Address();


    @BeforeEach
    public void setUp() {
        addressRepository.deleteAll();



        address.setStreet("vej");
        address.setCity("cph");
        address.setZipCode("8888");
        address.setAdditionalInfo("big parties every friday");

        address.setId(1);

        addressRepository.save(address);
    }

    @Test
    void testAddAddress() throws Exception {
        AddressRequest addressRequest = new AddressRequest("xxx","xxx","xxx","xxx");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/address")
                    .contentType("application/json")
                    .accept("application/json")
                    .content(objectMapper.writeValueAsString(addressRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        assertEquals(2,addressRepository.count());

    }


    @Test
    void testDeleteAddress() {

        assertEquals(1, addressRepository.count());

        addressRepository.delete(address);

        assertEquals(1, addressRepository.count());

    }
}