package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.AddressResponse;
import dk.krakkerz.krakheadzbackend.DTO.HobbyRequest;
import dk.krakkerz.krakheadzbackend.DTO.HobbyResponse;
import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.error.AddressDoesNotExistException;
import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;


    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }
    public List<HobbyResponse> getHobbies() {
        List<Hobby> hobbies = hobbyRepository.findAll();

        return HobbyResponse.getHobbiesFromEntities(hobbies);
    }

    public HobbyResponse getHobbyById(int id) {
        Hobby hobby = hobbyRepository.getById(id);

        return HobbyResponse.of(hobby);

    }
}
