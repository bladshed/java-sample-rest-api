package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RestResource(exported=false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    @Query(
            value = "SELECT * FROM PERSON p WHERE p.email = ?1",
            nativeQuery = true
    )
    Optional<Person> findByEmail(String email);

    @Query(
            value = "SELECT * FROM PERSON p WHERE p.id = ?1 AND p.type = ?2 ",
            nativeQuery = true
    )
    Optional<Person> findByPersonByType(long id, char type);
}
