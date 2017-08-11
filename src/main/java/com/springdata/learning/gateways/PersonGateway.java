package com.springdata.learning.gateways;

import com.springdata.learning.domains.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PersonGateway {

    private final PersonRepository repository;

    @Autowired
    public PersonGateway(PersonRepository repository) {
        this.repository = repository;
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public Person findById(final String id) {
        return repository.findOne(id);
    }

    public Page<Person> findAll(final int page, final int size) {
        return repository.findAll(new PageRequest(page, size));
    }
}
