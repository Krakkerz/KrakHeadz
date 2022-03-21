package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
