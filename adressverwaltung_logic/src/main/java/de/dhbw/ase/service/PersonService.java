package de.dhbw.ase.service;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Person;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService {

    private PersonDAO personDAO;

    protected PersonService() {

    }

    //TODO kann ich hier public lassen?
    @Inject
    public PersonService(final PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public List<Person> getAllPersons() {
        return personDAO.getPersons();
    }

    public Person getPerson(long id) {
        Optional<Person> optionalPerson = personDAO.getPerson(id);

        return optionalPerson
                .orElseThrow(() -> new IllegalArgumentException("Person mit der ID: " + id + " konnte nicht gefunden werden"));
    }

    public void addPerson(Person person) {
        person.setId(getNextId());
        personDAO.insertPerson(person);

    }

    public void updatePerson(final Person person) {

        Person databasePerson = personDAO
                .getPersons()
                .stream()
                .filter(entry -> entry.getId() == person.getId())
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Es konnte keine Person mit der ID: " + person.getId() + " gefunden werden"));

        Person updatedPerson = changeValues(databasePerson, person);
        personDAO.updatePerson(updatedPerson);
    }

    private Person changeValues(Person databasePerson, Person person) {


        if (person.getFirstName() != null) {
            databasePerson.setFirstName(person.getFirstName());
        }
        if (person.getLastName() != null) {
            databasePerson.setLastName(person.getLastName());
        }
        if (person.getDateOfBirth() != null) {
            databasePerson.setDateOfBirth(person.getDateOfBirth());
        }
        if (person.getAddresses() != null) {
            databasePerson.setAddresses(person.getAddresses());
        }
        if (person.getPhoneNumbers() != null) {
            databasePerson.setPhoneNumbers(person.getPhoneNumbers());
        }
        if (person.getCreated() != null) {
            databasePerson.setCreated(Objects.requireNonNull(databasePerson.getCreated()));
        }

        databasePerson.setLastModified(LocalDateTime.now());

        return databasePerson;
    }


}
