package de.dhbw.ase.service;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.service.mocks.PersonDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonServiceTest {

    public PersonDAOMock personDAOMock;

    public Person toAdd;

    @BeforeEach
    void setUp() {

        personDAOMock = new PersonDAOMock();

        toAdd = new Person(0, "testV", "testN", 29, 4, 1990, null, null);


    }

    @Test
    void getAllPersons() {


        assertEquals(3, personDAOMock.getPersons().size());


    }

    @Test
    void getPerson() {

        Optional<Person> optionalPerson = personDAOMock.getPerson(0);
        if (optionalPerson.isPresent()) {

            assertEquals("vorname", optionalPerson.get().getFirstName());
            assertEquals("nachname", optionalPerson.get().getLastName());
            assertEquals(LocalDate.of(1990, 4, 29), optionalPerson.get().getDateOfBirth());
        }


    }

    @Test
    void addPerson() {

        assertEquals(3, personDAOMock.getPersons().size());

        personDAOMock.insertPerson(toAdd);

        assertEquals(4, personDAOMock.getPersons().size());

    }

    @Test
    void updatePerson() {
    }

    @Test
    void followPerson() {
    }

    @Test
    void unfollowPerson() {
    }

    @Test
    void addAddress() {
    }

    @Test
    void removeAddress() {
    }

    @Test
    void removePhoneNumber() {
    }

    @Test
    void addPhoneNumber() {
    }
}