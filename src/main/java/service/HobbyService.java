package service;

import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {

    HobbyRepository hobbyRepository;

    public HobbyRepository getHobbyRepository() {
        return hobbyRepository;
    }
}
