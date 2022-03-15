package dk.krakkerz.krakheadzbackend.service;

import dk.krakkerz.krakheadzbackend.DTO.HobbyResponse;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }
    public List<HobbyResponse> getHobbies() {
        List<Hobby> hobbies = hobbyRepository.findAll();

        return HobbyResponse.getHobbiesFromEntities(hobbies);
    }
}
