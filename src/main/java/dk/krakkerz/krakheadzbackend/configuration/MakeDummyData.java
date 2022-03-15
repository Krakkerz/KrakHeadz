package dk.krakkerz.krakheadzbackend.configuration;

import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile({"development", "staging"})
@AllArgsConstructor
public class MakeDummyData implements ApplicationRunner {
    private final PersonRepository personRepository;

    public void makeDummyPersons(){
        Person person1 = new Person();
        person1.setEmail("email1@dummyman.com");
        person1.setFirstName("Mario");
        person1.setLastName("Super");
        person1.setPhoneNumber("88888888");
        person1.getHobbyInfoSet().add(new HobbyInfo());
        person1.setAddress(new Address());

        Hobby hobby1 = new Hobby("Dog Fighting","dogs fight, you bet money on the game, the last dog standing wins");
        HobbyInfo hobbyInfo1 = new HobbyInfo();
        hobbyInfo1.setSpecifiedPerson(person1);
        hobbyInfo1.setSpecifiedHobby(hobby1);


        Person person2 = new Person();
        person2.setEmail("email2@dummyman.com");
        person2.setFirstName("Lugigi");
        person2.setLastName("Bros");
        person2.setPhoneNumber("69696969");
        person2.setAddress(new Address());

        Hobby hobby2 = new Hobby("Kicking Puppies","you kick puppies, if you want you can collect the tears and use them for alchemy.");
        HobbyInfo hobbyInfo2 = new HobbyInfo();
        hobbyInfo2.setSpecifiedPerson(person2);
        hobbyInfo2.setSpecifiedHobby(hobby2);

        personRepository.saveAll(List.of(person1, person2));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeDummyPersons();
    }
}
