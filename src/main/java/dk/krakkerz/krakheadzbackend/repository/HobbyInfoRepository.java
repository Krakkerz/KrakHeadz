package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface HobbyInfoRepository extends JpaRepository<HobbyInfo, Integer> {
    @Transactional
    void deleteBySpecifiedPerson_Id(Integer personId);
}
