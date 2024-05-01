package de.dhbw.ase.service;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService {

    private PersonDAO personDAO;


    public PersonService(final PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    //TODO initial value anpassen, damit nicht zwei die gleiche id haben
    private final static AtomicLong ID_COUNTER = new AtomicLong(1);

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public List<Person> getAllPersons() throws Exception {

        if(personDAO.getPersons().isEmpty()){
            throw new Exception("Keine Personen gefunden!");
        }
        return personDAO.getPersons();
    }

    public Person getPerson(long id) throws Exception {
        Optional<Person> optionalPerson = personDAO.getPerson(id);

        return optionalPerson
                .orElseThrow(() -> new IllegalArgumentException("Person mit der ID: " + id + " konnte nicht gefunden werden"));
    }

    public void addPerson(Person person) throws Exception {
        //TODO sollte man eine Person hinzufügen können oder nur durch Registrierung?
        person.setId(getNextId());
        if (personDAO.getPersons().stream().anyMatch(p -> Objects.equals(p.getFirstName(), person.getFirstName()))
                && personDAO.getPersons().stream().anyMatch(p -> Objects.equals(p.getLastName(), person.getLastName()))) {
            throw new RuntimeException("Die Person mit dem Namen: " + person.getFirstName() + " " + person.getLastName() + " existiert bereits!" +
                    " Sind Sie das nicht und möchten einen neuen Account anlegen, dann hängen Sie eine 1 (2, bzw. fortlaufend) an das Ende Ihres Nachnamens!");
        } else {
            personDAO.insertPerson(person);
        }

    }

    public void updatePerson(final Person person, final long loggedInUserID) throws Exception {

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


    public void followPerson(long follower, long personToFollow) throws Exception {
        if (follower == personToFollow) {
            throw new RuntimeException("Sie können sich nicht selbst folgen!");
        } else if (personDAO.getPerson(personToFollow).isPresent()) {
            personDAO.followPerson(follower, personToFollow);
        } else {
            throw new RuntimeException("Der Person mit der ID: " + personToFollow + " konnte nicht gefunden werden!");
        }

    }

    public void unfollowPerson(long follower, long personToUnfollow) throws Exception {

        if (personDAO.getPerson(follower).get().getFollowing().contains(personDAO.getPerson(personToUnfollow).get())) {
            personDAO.unfollowPerson(follower, personToUnfollow);
        } else {
            throw new RuntimeException("Der Person mit der ID: " + personToUnfollow + " wird nicht gefolgt!");
        }
    }


    public void addAddress(long PersonID, Address address) throws Exception {

        if (personDAO.getPerson(PersonID).isEmpty()) {
            throw new RuntimeException("Die Person konnte nicht gefunden werden!");
        } else if (personDAO.getPerson(PersonID).get().getAddresses().contains(address)) {
            throw new RuntimeException("Die Adresse wurde bereits zu der Person hinzugefügt!");
        } else {
            personDAO.addAddress(PersonID, address);
        }

    }

    public void removeAddress(long personID, Address address) throws Exception {
        if (personDAO.getPerson(personID).isEmpty()) {
            throw new RuntimeException("Die Person konnte nicht gefunden werden!");
        } else if (!personDAO.getPerson(personID).get().getAddresses().contains(address)) {
            throw new RuntimeException("Die Adresse ist nicht mit der Person verknüpft!");
        } else {
            personDAO.removeAddress(personID, address);
        }


    }

    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {

        if (personDAO.getPerson(personID).isEmpty()) {
            throw new RuntimeException("Die Person konnte nicht gefunden werden!");
        } else if (!personDAO.getPerson(personID).get().getPhoneNumbers().contains(phoneNumber)) {
            throw new RuntimeException("Die Telefonnummer ist nicht mit der Person verknüpft!");
        } else {
            personDAO.removePhoneNumber(personID, phoneNumber);
        }

    }

    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {
        personDAO.addPhoneNumber(personID, phoneNumber);

    }
}
