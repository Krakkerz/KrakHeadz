package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.AddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.service.AddressService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeAll
    public static void setUp(@Autowired AddressRepository addressRepository) {
        Address address = Address.builder()
                .id("abcdefg")
                .text("Strandvej 69, 420 Randers")
                .build();

        addressRepository.save(address);
    }

    @AfterAll
    public static void tearDown(@Autowired AddressRepository addressRepository) {
        addressRepository.deleteAll();
    }

    @Test
    void testAddAddress() throws Exception {
        AddressRequest addressRequest = AddressRequest.builder()
                .id("hijklmn")
                .text("Ved Kanten 23, 180 Langt Ude Overdrev")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/address")
                    .contentType("application/json")
                    .accept("application/json")
                    .content(objectMapper.writeValueAsString(addressRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").exists());

        assertEquals(2, addressRepository.count());
    }


    @Test
    void testDeleteAddress() {
        assertEquals(1, addressRepository.count());

        Address address = addressRepository.findAll().get(0);
        addressRepository.delete(address);

        assertEquals(0, addressRepository.count());

    }
}
