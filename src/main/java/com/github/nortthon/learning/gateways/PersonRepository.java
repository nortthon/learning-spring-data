package com.github.nortthon.learning.gateways;

import com.github.nortthon.learning.domains.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface PersonRepository extends MongoRepository<Person, String> {

    Page<Person> findByEmail(String email, Pageable pageable);

    Stream<Person> findAllBy();
}