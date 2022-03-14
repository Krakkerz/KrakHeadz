package dk.krakkerz.krakheadzbackend.configuration;

import dk.krakkerz.krakheadzbackend.entity.Address;
import dk.krakkerz.krakheadzbackend.entity.Hobby;
import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import dk.krakkerz.krakheadzbackend.entity.Person;

import java.util.List;

public class MakeDummyData {

    public void makeDummyPersons(){
        Hobby hobby1 = new Hobby("Dog Fighting","dogs fight, you bet money on the game, the last dog standing wins");
        Address address1 = new Address();
        Person person1 = new Person("email@dummyman.com","Mario", "Super",88888888,address1);

        HobbyInfo hobbyInfo = new HobbyInfo(hobby1,person1);
        
    }
}
