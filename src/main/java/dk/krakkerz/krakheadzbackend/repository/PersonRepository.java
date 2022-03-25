package dk.krakkerz.krakheadzbackend.repository;

import dk.krakkerz.krakheadzbackend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Modifying
    @Transactional
    @Query("update Person p set p.address = :newValue where p.address.id = :addressId")
    void updateAddressToValueWhereAddressId(@Param("newValue") String newValue, @Param("addressId") String addressId);
}
