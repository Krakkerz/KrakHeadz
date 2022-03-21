package dk.krakkerz.krakheadzbackend.api;

import dk.krakkerz.krakheadzbackend.DTO.HobbyResponse;
import dk.krakkerz.krakheadzbackend.service.HobbyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hobbies")
@CrossOrigin
public class HobbyController {
    HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping
    public List<HobbyResponse> getHobbies() {
        return hobbyService.getHobbies();
    }

    @GetMapping("/{id}")
    public HobbyResponse getHobbies(@PathVariable int id) {
        return hobbyService.getHobbyById(id);
    }


}
