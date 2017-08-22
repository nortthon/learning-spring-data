package com.springdata.learning.gateways;

import com.springdata.learning.domains.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    Page<Person> findByEmail(String email, Pageable pageable);
}