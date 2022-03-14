package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
