package de.dhbw.ase.service;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.mocks.PersonDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonServiceTest {


    public PersonDAOMock personDAOMock;
    PersonService personService;

    public Person toAdd;

    @BeforeEach
    void setUp() {

        personDAOMock = new PersonDAOMock();
        personService = new PersonService(personDAOMock);

        toAdd = new Person(0, "testV", "testN", 29, 4, 1990, null, null);


    }

    @Test
    void getAllPersons() {

        assertEquals(3, personService.getAllPersons().size());

    }

    @Test
    void getPerson() {

        Person person = personService.getPerson(0);

        assertEquals("vorname", person.getFirstName());
        assertEquals("nachname", person.getLastName());
        assertEquals(LocalDate.of(1990, 4, 29), person.getDateOfBirth());
    }

    @Test
    void addPerson() {

        assertEquals(3, personService.getAllPersons().size());

        personService.addPerson(toAdd);

        assertEquals(4, personService.getAllPersons().size());
        assertEquals("testV", personService.getAllPersons().get(3).getFirstName());

    }

    @Test
    void updatePerson() {
        assertEquals("vorname", personService.getPerson(0).getFirstName());
        personService.updatePerson(toAdd, toAdd.getId());
        assertEquals("testV", personService.getPerson(0).getFirstName());


    }

    @Test
    void followPerson() {
        assertEquals(0, personService.getPerson(0).getFollowing().size());
        personService.followPerson(0, 1);
        assertEquals(1, personService.getPerson(0).getFollowing().size());
    }

    @Test
    void unfollowPerson() {

        assertEquals(1, personService.getPerson(1).getFollowing().size());
        personService.unfollowPerson(1, 0);
        assertEquals(0, personService.getPerson(1).getFollowing().size());
    }

    @Test
    void addAddress() {

        assertEquals(0, personService.getPerson(0).getAddresses().size());
        personService.addAddress(0, new Address(0, "test", 12345, "test", "test", "test"));
        assertEquals(1, personService.getPerson(0).getAddresses().size());
    }

    @Test
    void removeAddress() {

        assertEquals(1, personService.getPerson(1).getAddresses().size());
        personService.removeAddress(1, personService.getPerson(1).getAddresses().get(0));
        assertEquals(0, personService.getPerson(1).getAddresses().size());
    }

    @Test
    void removePhoneNumber() {
        assertEquals(1, personService.getPerson(1).getPhoneNumbers().size());
        personService.removePhoneNumber(1, personService.getPerson(1).getPhoneNumbers().get(0));
        assertEquals(0, personService.getPerson(1).getPhoneNumbers().size());

    }

    @Test
    void addPhoneNumber() {

        assertEquals(0, personService.getPerson(0).getPhoneNumbers().size());
        personService.addPhoneNumber(0, new PhoneNumber(0, "0123456789", false));
        assertEquals(1, personService.getPerson(0).getPhoneNumbers().size());
    }
}