package de.dhbw.ase.service;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

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

    @Inject
    public PersonService(final PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    //TODO initial value anpassen, damit nicht zwei die gleiche id haben
    private final static AtomicLong ID_COUNTER = new AtomicLong(1);

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
        try {
            personDAO.insertPerson(person);
        } catch (Exception e) {
            System.out.println("Person konnte nicht hinzugefügt werden");
        }
    }

    public void updatePerson(final Person person, final long loggedInUserID) {

        if (person.getId() == loggedInUserID || loggedInUserID == 0) {
            Person databasePerson = personDAO
                    .getPersons()
                    .stream()
                    .filter(entry -> entry.getId() == person.getId())
                    .findFirst()
                    .orElseThrow(() ->
                            new IllegalArgumentException("Es konnte keine Person mit der ID: " + person.getId() + " gefunden werden"));

            Person updatedPerson = changeValues(databasePerson, person);
            personDAO.updatePerson(updatedPerson);
        } else {
            System.out.println("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine anderen Benutzer ändern!");
        }


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


    public void followPerson(long follower, long personToFollow) {
        personDAO.followPerson(follower, personToFollow);
    }

    public void unfollowPerson(long follower, long personToUnfollow) {
        personDAO.unfollowPerson(follower, personToUnfollow);
    }


    public void addAddress(long PersonID, Address address) {

        try {
            personDAO.addAddress(PersonID, address);
            System.out.println("Die Adresse wurde erfolgreich zu der Person mit der ID: " + PersonID + " hinzugefügt");
        } catch (Exception e) {
            System.out.println("Die Adresse konnte nicht zu der Person mit ID:+ " + PersonID + "hinzugefügt werden");
        }
    }

    public void removeAddress(long personID, Address address) {
        try {
            personDAO.removeAddress(personID, address);
            System.out.println("Die Adresse mit der ID: " + address.getId() + " wurde erfolgreich von der Person mit der ID: " + personID + " gelöscht");
        } catch (Exception e) {
            System.out.println("Die Adresse mit der ID: " + address.getId() + " konnte nicht von der Person mit der ID: " + personID + " gelöscht werden");
        }
    }

    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            personDAO.removePhoneNumber(personID, phoneNumber);
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " wurde erfolgreich von der Person mit der ID: " + personID + " gelöscht");
        } catch (Exception e) {
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " konnte nicht von der Person mit der ID: " + personID + " gelöscht werden");
        }
    }

    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            personDAO.addPhoneNumber(personID, phoneNumber);
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " wurde erfolgreich zu der Person mit der ID: " + personID + " hinzugefügt");
        } catch (Exception e) {
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " konnte nicht zu der Person mit der ID: " + personID + " hinzugefügt werden");
        }


    }
}
