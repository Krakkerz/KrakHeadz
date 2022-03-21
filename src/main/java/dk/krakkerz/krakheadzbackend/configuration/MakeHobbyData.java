package dk.krakkerz.krakheadzbackend.configuration;

import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

//@Configuration
// Alternative to having data.sql. This just reads the data csv and uses jpa to add it to database
@AllArgsConstructor
public class MakeHobbyData implements ApplicationRunner {
    private final HobbyRepository hobbyRepository;


    @Override
    public void run(ApplicationArguments args) {
        InputStream resourceStream = ClassLoader.getSystemClassLoader().getResourceAsStream("hobbies.txt");
        assert resourceStream != null;
        InputStreamReader streamReader = new InputStreamReader(resourceStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        List<Hobby> hobbies = bufferedReader.lines()
                .map( line -> line.split(";") )
                .map( csvLine -> Hobby.builder()
                        .name(csvLine[0])
                        .description(csvLine[1])
                        .category(csvLine[2])
                        .type(csvLine[3])
                        .build())
                .toList();

        hobbyRepository.saveAll(hobbies);

    }
}
