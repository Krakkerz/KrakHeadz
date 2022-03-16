package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyInfoRepository extends JpaRepository<HobbyInfo, Integer> {
}
