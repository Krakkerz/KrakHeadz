package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HobbyInfoRepository extends JpaRepository<HobbyInfo, Integer> {
    //@Query(value="select hobby_info from HobbyInfo hobby_info where hobby_info.specifiedPerson=?1")
    List<HobbyInfo> findAllBySpecifiedPersonEquals(Integer personId);
}