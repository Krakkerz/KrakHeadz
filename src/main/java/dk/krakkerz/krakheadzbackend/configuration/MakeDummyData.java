package dk.krakkerz.krakheadzbackend.configuration;

import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;
import dk.krakkerz.krakheadzbackend.repository.AddressRepository;
import dk.krakkerz.krakheadzbackend.repository.HobbyInfoRepository;
import dk.krakkerz.krakheadzbackend.repository.HobbyRepository;
import dk.krakkerz.krakheadzbackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"development", "staging"})
@AllArgsConstructor
public class MakeDummyData implements ApplicationRunner {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final HobbyRepository hobbyRepository;
    private final HobbyInfoRepository hobbyInfoRepository;

    public void makePersons(){
        Person person1 = new Person();
        person1.setEmail("email1@dummyman.com");
        person1.setFirstName("Mario");
        person1.setLastName("Super");
        person1.setPhoneNumber("88888888");
        personRepository.save( person1 );

        Person person2 = new Person();
        person2.setEmail("email2@dummyman.com");
        person2.setFirstName("Luigi");
        person2.setLastName("Bros");
        person2.setPhoneNumber("69696969");
        personRepository.save( person2 );
    }

    public void makeHobbies(){
        Hobby hobby1 = new Hobby("Dog Fighting","dogs fight, you bet money on the game, the last dog standing wins");
        hobbyRepository.save(hobby1);

        Hobby hobby2 = new Hobby("Kicking Puppies","you kick puppies, if you want you can collect the tears and use them for alchemy.");
        hobbyRepository.save(hobby2);
    }

    public void makeHobbyInfos(){
        HobbyInfo hobbyInfo1 = new HobbyInfo();
        hobbyInfo1.setSpecifiedPerson(personRepository.getById(1));
        hobbyInfo1.setSpecifiedHobby(hobbyRepository.getById(1));
        hobbyInfoRepository.save(hobbyInfo1);

        HobbyInfo hobbyInfo2 = new HobbyInfo();
        hobbyInfo2.setSpecifiedPerson(personRepository.getById(2));
        hobbyInfo2.setSpecifiedHobby(hobbyRepository.getById(2));
        hobbyInfoRepository.save(hobbyInfo2);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makePersons();
        makeHobbies();
        makeHobbyInfos();
    }
}
