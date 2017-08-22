package com.springdata.learning.gateways;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.springdata.learning.domains.Person;
import com.springdata.learning.domains.SummaryPerson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;
import static org.springframework.data.domain.Sort.Direction.DESC;

@DataMongoTest
@RunWith(SpringRunner.class)
public class PersonGatewayTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonRepository repository;

    @BeforeClass
    public static void setupClass() {
        FixtureFactoryLoader.loadTemplates("com.springdata.learning.templates");
    }

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void testCreateANewPerson() {
        final PersonGateway gateway = new PersonGateway(repository);
        final Person savedPerson = gateway.save(from(Person.class).gimme("son goku"));

        assertEquals(savedPerson, repository.findOne(savedPerson.getId()));
    }

    @Test
    public void testFindWithPagination() {

        repository.save((Person)from(Person.class).gimme("son goku"));
        repository.save((Person)from(Person.class).gimme("iron man"));
        repository.save((Person)from(Person.class).gimme("xena"));

        final PersonGateway gateway = new PersonGateway(repository);

        final Page<Person> page1 = gateway.findAll(0, 2);
        assertEquals(2, page1.getTotalPages());
        assertEquals(3, page1.getTotalElements());
        assertEquals(2, page1.getContent().size());

        final Page<Person> page2 = gateway.findAll(1, 2);
        assertEquals(2, page2.getTotalPages());
        assertEquals(3, page2.getTotalElements());
        assertEquals(1, page2.getContent().size());
    }

    @Test
    public void testFindByEmailWithPagination() {
        repository.save((Person)from(Person.class).gimme("son goku"));
        repository.save((Person)from(Person.class).gimme("iron man"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));
        repository.save((Person)from(Person.class).gimme("xena"));


        final PersonGateway gateway = new PersonGateway(repository);

        final Page<Person> page = gateway.findByEmail("xena@warriorprincess.com", 0, 2);
        assertEquals(4, page.getTotalPages());
        assertEquals(7, page.getTotalElements());
        assertEquals(2, page.getContent().size());
    }

    @Test
    public void testFindAllSortedByNameDesc() {
        repository.save((Person)from(Person.class).gimme("son goku"));
        repository.save((Person)from(Person.class).gimme("iron man"));
        repository.save((Person)from(Person.class).gimme("xena"));

        final PersonGateway gateway = new PersonGateway(repository);

        final List<Person> personList = gateway.findAllSort(DESC, "name");

        assertEquals("Xena", personList.get(0).getName());
        assertEquals("Tony Stark", personList.get(1).getName());
        assertEquals("Son Goku", personList.get(2).getName());
    }

    @Test
    public void testMongoTemplate() {
        repository.save((Person)from(Person.class).gimme("son goku"));
        repository.save((Person)from(Person.class).gimme("iron man"));
        repository.save((Person)from(Person.class).gimme("xena"));

        final List<SummaryPerson> personList = mongoTemplate.findAll(SummaryPerson.class, "person");

        assertEquals("Son Goku", personList.get(0).getName());
        assertEquals("Tony Stark", personList.get(1).getName());
        assertEquals("Xena", personList.get(2).getName());
    }
}
