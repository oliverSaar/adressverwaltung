package de.dhbw.ase.service;

import de.dhbw.ase.dao.AddressDAO;
import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService {

    private PersonDAO personDAO;
    private Scanner scanner = new Scanner(System.in);


    protected PersonService() {

    }

    //TODO kann ich hier public lassen?
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


    public void followPerson(int followId) {
//        int userId = loginService.getLoggedInUser().getId();
//        personDAO.followPerson(userId, followId);
    }

    public void unFollowPerson(int id) {
    }

    public Person dataInput() {

        long id = 0;
        String firstName;
        String lastName;
        int day;
        int month;
        int year;

        System.out.print("Vorname: ");
        firstName = scanner.next();
        System.out.println();
        System.out.print("Nachname: ");
        lastName = scanner.next();
        System.out.println();
        System.out.println("Geburtsdatum : ");
        System.out.print("Tag:");
        day = scanner.nextInt();
        System.out.println();
        System.out.print("Monat (1-12): ");
        month = scanner.nextInt();
        System.out.println();
        System.out.print("Jahr (z.B. 1990): ");

        year = scanner.nextInt();

        while (year < 1900 || year > LocalDate.now().getYear()) {
            System.out.println("Falsches Jahr! Bitte geben Sie ihr Geburtsjahr erneut ein: ");
            year = scanner.nextInt();
        }

        System.out.println("Sie können später eine bestehende Adresse oder Telefonnummer hinzufügen oder ganz neue Einträge erstellen");

//        scanner.close();
        return new Person(id, firstName, lastName, day, month, year, null, null);
    }

    public void addAddress() {
        System.out.print("Bitte geben Sie die ID der Person an, zu der Sie eine Adresse hinzufügen wollen: ");
        int personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Adresse an, um Sie hinzuzufügen");
        int addressID = scanner.nextInt();

        //TODO kann ich hier den addresseService aufrufen oder lieber nicht?
//        Address address = addressService.getAddress(addressID);
//        personDAO.addAddress(personID, address);
    }
}
