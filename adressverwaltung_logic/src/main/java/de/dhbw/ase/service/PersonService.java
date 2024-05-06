package de.dhbw.ase.service;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.model.singleton.LoggedInPersonSingleton;

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
    private final static AtomicLong ID_COUNTER = new AtomicLong(4);

    private static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public List<Person> getAllPersons() throws Exception {

        if (personDAO.getPersons().isEmpty()) {
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
        //TODO sollte man so überhaupt eine Person hinzufügen können oder nur durch Registrierung?
        person.setId(getNextId());
        if (personDAO.getPersons().stream().anyMatch(p -> Objects.equals(p.getFirstName(), person.getFirstName()))
                && personDAO.getPersons().stream().anyMatch(p -> Objects.equals(p.getLastName(), person.getLastName()))) {
            throw new RuntimeException("Die Person mit dem Namen: " + person.getFirstName() + " " + person.getLastName() + " existiert bereits!" +
                    " Sind Sie das nicht und möchten einen neuen Account anlegen, dann hängen Sie bei der Registrierung eine 1 (2, bzw. fortlaufend) an das Ende Ihres Nachnamens!");
        } else {
            personDAO.insertPerson(person);
        }
    }

    public void deletePerson(long id) throws Exception {
        if (personDAO.getPerson(id).isPresent()) {

            if (LoggedInPersonSingleton.getLoggedInUserID() == id || LoggedInPersonSingleton.getLoggedInUserID() == 0) {
                personDAO.deletePerson(id);
            } else {
                throw new Exception("Person mit der ID: " + id + " konnte nicht gelöscht werden. Sie können keine anderen Benutzer löschen!");
            }
        } else {
            throw new Exception("Person mit der ID: " + id + " konnte nicht gefunden werden");
        }
    }

    public void updatePerson(final Person person) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();

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
            throw new Exception("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine anderen Benutzer ändern!");
        }


    }

    private Person changeValues(Person databasePerson, Person person) {


        if (person.getFirstName() != null) {
            databasePerson.setFirstName(person.getFirstName());
        } else {
            databasePerson.setFirstName(databasePerson.getFirstName());
        }
        if (person.getLastName() != null) {
            databasePerson.setLastName(person.getLastName());
        } else {
            databasePerson.setLastName(databasePerson.getLastName());
        }
        if (person.getDateOfBirth() != null) {
            databasePerson.setDateOfBirth(person.getDateOfBirth());
        } else {
            databasePerson.setDateOfBirth(databasePerson.getDateOfBirth());
        }

        databasePerson.setAddresses(databasePerson.getAddresses());
        databasePerson.setPhoneNumbers(databasePerson.getPhoneNumbers());
        databasePerson.setCreated(Objects.requireNonNull(databasePerson.getCreated()));
        databasePerson.setLastModified(LocalDateTime.now());

        return databasePerson;
    }


    public void followPerson(long personToFollow) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();

        if (loggedInUserID == personToFollow) {
            throw new RuntimeException("Sie können sich nicht selbst folgen!");
        } else if (personDAO.getPerson(loggedInUserID).get().getFollowing().contains(personDAO.getPerson(personToFollow).get())) {
            throw new RuntimeException("Sie folgen dieser Person bereits!");
        } else if (personDAO.getPerson(personToFollow).isPresent()) {
            personDAO.followPerson(loggedInUserID, personToFollow);
        } else {
            throw new RuntimeException("Die Person mit der ID: " + personToFollow + " konnte nicht gefunden werden!");
        }
    }

    public void unfollowPerson(long personToUnfollow) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();

        if (personDAO.getPerson(loggedInUserID).get().getFollowing().contains(personDAO.getPerson(personToUnfollow).get())) {
            personDAO.unfollowPerson(loggedInUserID, personToUnfollow);
        } else {
            throw new RuntimeException("Der Person mit der ID: " + personToUnfollow + " wird nicht gefolgt!");
        }
    }


    public void addAddress(long personID, Address address) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();

        if (loggedInUserID == personID || loggedInUserID == 0) {

            if (personDAO.getPerson(personID).isEmpty()) {
                throw new RuntimeException("Die Person konnte nicht gefunden werden!");
            } else if (personDAO.getPerson(personID).get().getAddresses().contains(address)) {
                throw new RuntimeException("Die Adresse wurde bereits zu der Person hinzugefügt!");
            } else {
                personDAO.addAddress(personID, address);
            }
        } else {
            throw new Exception("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine Adressen zu anderen Benutzern hinzufügen!");
        }
    }

    public void removeAddress(long personID, Address address) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();
        if (loggedInUserID == personID || loggedInUserID == 0) {
            if (personDAO.getPerson(personID).isEmpty()) {
                throw new RuntimeException("Die Person konnte nicht gefunden werden!");
            } else if (!personDAO.getPerson(personID).get().getAddresses().contains(address)) {
                throw new RuntimeException("Die Adresse ist nicht mit der Person verknüpft!");
            } else {
                personDAO.removeAddress(personID, address);
            }
        } else {
            throw new Exception("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine Adressen von anderen Benutzern entfernen!");
        }
    }

    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();
        if (loggedInUserID == personID || loggedInUserID == 0) {

            if (personDAO.getPerson(personID).isEmpty()) {
                throw new RuntimeException("Die Person konnte nicht gefunden werden!");
            } else if (personDAO.getPerson(personID).get().getPhoneNumbers().stream().noneMatch(p -> p.equals(phoneNumber))) {
                throw new RuntimeException("Die Telefonnummer ist nicht mit der Person verknüpft!");
            } else {
                personDAO.removePhoneNumber(personID, phoneNumber);
            }
        } else {
            throw new Exception("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine Telefonnummern von anderen Benutzern entfernen!");
        }
    }

    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {

        long loggedInUserID = LoggedInPersonSingleton.getLoggedInUserID();
        if (loggedInUserID == personID || loggedInUserID == 0) {

            if (personDAO.getPerson(personID).isEmpty()) {
                throw new RuntimeException("Die Person konnte nicht gefunden werden!");
            } else if (personDAO.getPerson(personID).get().getPhoneNumbers().stream().anyMatch(p -> p.equals(phoneNumber))) {
                throw new RuntimeException("Die Telefonnummer wurde bereits zu der Person hinzugefügt!");
            } else {
                personDAO.addPhoneNumber(personID, phoneNumber);
            }
        } else {
            throw new Exception("Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine Telefonnummern zu anderen Benutzern hinzufügen!");
        }
    }
}
