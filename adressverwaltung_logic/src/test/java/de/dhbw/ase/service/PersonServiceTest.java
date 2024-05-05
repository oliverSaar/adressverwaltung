package de.dhbw.ase.service;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.model.adapter.Birthday;
import de.dhbw.ase.model.singleton.LoggedInPersonSingleton;
import de.dhbw.ase.service.mocks.PersonDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {


    public PersonDAOMock personDAOMock;
    public PersonService personService;

    public Person toAdd, follow;

    @BeforeEach
    void setUp() {

        personDAOMock = new PersonDAOMock();
        personService = new PersonService(personDAOMock);

        toAdd = new Person(0, "testV", "testN", 29, 4, 1990, null, null);
        follow = new Person(4, "testV", "testN", 29, 4, 1990, null, null);


    }

    @Test
    void getAllPersons() throws Exception {

        assertEquals(3, personService.getAllPersons().size());
    }

    @Test
    void getPerson() throws Exception {

        Person person = personService.getPerson(0);

        assertEquals("vorname", person.getFirstName());
        assertEquals("nachname", person.getLastName());
        assertEquals(Birthday.of(LocalDate.of(1990, 4, 29)).isToday(), person.getDateOfBirth().isToday());
        assertEquals(34, person.getDateOfBirth().getAge());
    }

    @Test
    void getPersonThrowsException() {

        Exception exception = assertThrows(Exception.class, () -> personService.getPerson(4));

        String expectedMessage = "Person mit der ID: 4 konnte nicht gefunden werden";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addPerson() throws Exception {

        assertEquals(3, personService.getAllPersons().size());

        personService.addPerson(toAdd);

        assertEquals(4, personService.getAllPersons().size());
        assertEquals("testV", personService.getAllPersons().get(3).getFirstName());

    }

    @Test
    void addPersonThrowsException() {

        Exception exception = assertThrows(Exception.class, () -> personService.addPerson(new Person(0, "vorname", "nachname", 29, 4, 1990, null, null)));

        String expectedMessage = "Person mit dem Namen: vorname nachname existiert bereits";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updatePerson() throws Exception {
        LoggedInPersonSingleton.setLoggedInUserID(0);

        assertEquals("vorname", personService.getPerson(0).getFirstName());
        personService.updatePerson(toAdd);
        assertEquals("testV", personService.getPerson(0).getFirstName());
    }

    @Test
    void updatePersonNotFound() throws Exception {

        LoggedInPersonSingleton.setLoggedInUserID(4);
        Exception exception = assertThrows(Exception.class, () -> personService.updatePerson(follow));

        String expectedMessage = "Es konnte keine Person mit der ID: 4 gefunden werden";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updatePersonOtherPerson() {

        LoggedInPersonSingleton.setLoggedInUserID(1);

        Exception exception = assertThrows(Exception.class, () -> personService.updatePerson(follow));

        String expectedMessage = "Die ID der zu ändernden Person stimmt nicht mit Ihrer ID überein. Sie dürfen keine anderen Benutzer ändern!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void followPerson() throws Exception {
        assertEquals(0, personService.getPerson(0).getFollowing().size());
        LoggedInPersonSingleton.setLoggedInUserID(0);
        personService.followPerson(1);
        assertEquals(1, personService.getPerson(0).getFollowing().size());
    }

    @Test
    void followPersonFollowSelf() {

        LoggedInPersonSingleton.setLoggedInUserID(0);

        Exception exception = assertThrows(Exception.class, () -> personService.followPerson(0));

        String expectedMessage = "Sie können sich nicht selbst folgen!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void followPersonAlreadyFollowing() {
        LoggedInPersonSingleton.setLoggedInUserID(1);

        Exception exception = assertThrows(Exception.class, () -> personService.followPerson(0));

        String expectedMessage = "Sie folgen dieser Person bereits!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Disabled
    @Test
    void followPersonWrongID() {

        LoggedInPersonSingleton.setLoggedInUserID(5);

        Exception exception = assertThrows(Exception.class, () -> personService.followPerson(1));

        String expectedMessage = "Die Person mit der ID: 5 konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void unfollowPerson() throws Exception {

        assertEquals(1, personService.getPerson(1).getFollowing().size());
        LoggedInPersonSingleton.setLoggedInUserID(1);

        personService.unfollowPerson(0);
        assertEquals(0, personService.getPerson(1).getFollowing().size());
    }

    @Test
    void unfollowPersonNotFollowed() {

        LoggedInPersonSingleton.setLoggedInUserID(2);

        Exception exception = assertThrows(Exception.class, () -> personService.unfollowPerson(1));

        String expectedMessage = "Der Person mit der ID: 1 wird nicht gefolgt!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addAddress() throws Exception {

        assertEquals(0, personService.getPerson(0).getAddresses().size());
        personService.addAddress(0, new Address(0, "test", 12345, "test", "test", "test"));
        assertEquals(1, personService.getPerson(0).getAddresses().size());
    }

    @Test
    void addAddressPersonNotFound() {

        Exception exception = assertThrows(Exception.class, () ->
                personService.addAddress(4, new Address(0, "test", 12345, "test", "test", "test")));

        String expectedMessage = "Die Person konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addAddressPersonAlreadyHasAddress() {

        Exception exception = assertThrows(Exception.class, () -> personService.addAddress(1, personService.getPerson(1).getAddresses().get(0)));
        String expectedMessage = "Die Adresse wurde bereits zu der Person hinzugefügt!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void removeAddress() throws Exception {

        assertEquals(1, personService.getPerson(1).getAddresses().size());
        personService.removeAddress(1, personService.getPerson(1).getAddresses().get(0));
        assertEquals(0, personService.getPerson(1).getAddresses().size());
    }

    @Test
    void removeAddressPersonNotFound() {

        Exception exception = assertThrows(Exception.class, () -> personService.removeAddress(4, new Address(0, "test", 12345, "test", "test", "test")));
        String expectedMessage = "Die Person konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void removeAddressNotConnected() {

        Exception exception = assertThrows(Exception.class, () -> personService.removeAddress(1, new Address(0, "test", 12345, "test", "test", "test")));
        String expectedMessage = "Die Adresse ist nicht mit der Person verknüpft!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removePhoneNumber() throws Exception {
        assertEquals(1, personService.getPerson(1).getPhoneNumbers().size());
        personService.removePhoneNumber(1, personService.getPerson(1).getPhoneNumbers().get(0));
        assertEquals(0, personService.getPerson(1).getPhoneNumbers().size());

    }

    @Test
    void removePhoneNumberPersonNotFound() {

        Exception exception = assertThrows(Exception.class, () -> personService.removePhoneNumber(4, new PhoneNumber(0, "0123456789", false)));
        String expectedMessage = "Die Person konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removePhoneNumberNotConnected() {

        Exception exception = assertThrows(Exception.class, () -> personService.removePhoneNumber(1, new PhoneNumber(0, "0123456789", false)));
        String expectedMessage = "Die Telefonnummer ist nicht mit der Person verknüpft!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addPhoneNumber() throws Exception {

        assertEquals(0, personService.getPerson(0).getPhoneNumbers().size());
        personService.addPhoneNumber(0, new PhoneNumber(0, "0123456789", false));
        assertEquals(1, personService.getPerson(0).getPhoneNumbers().size());
    }

    @Test
    void addPhoneNumberPersonNotFound() {

        Exception exception = assertThrows(Exception.class, () -> personService.addPhoneNumber(4, new PhoneNumber(0, "0123456789", false)));
        String expectedMessage = "Die Person konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addPhoneNumberPersonAlreadyHasPhoneNumber() {

        Exception exception = assertThrows(Exception.class, () -> personService.addPhoneNumber(1, personService.getPerson(1).getPhoneNumbers().get(0)));
        String expectedMessage = "Die Telefonnummer wurde bereits zu der Person hinzugefügt!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}