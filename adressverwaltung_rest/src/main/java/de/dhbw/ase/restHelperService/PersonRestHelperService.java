package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.PersonService;

import javax.inject.Inject;
import java.util.List;

public class PersonRestHelperService {

    @Inject
    private PersonService personService;

    protected PersonRestHelperService() {
    }

    @Inject
    public PersonRestHelperService(final PersonService personService) {
        this.personService = personService;
    }

    public Person getPerson(long id) {
        try {
            return personService.getPerson(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Person> getAllPersons() {
        try {
            return personService.getAllPersons();
        } catch (Exception e) {
            System.out.println("Es wurden keine Personen gefunden.");
            return null;
        }
    }

    public void updatePerson(Person person, long loggedInUserID) {
        try {
            personService.updatePerson(person, loggedInUserID);
            System.out.println("Person mit der ID: " + person.getId() + " wurde erfolgreich aktualisiert.");
        } catch (Exception e) {
            System.out.println("Person mit der ID: " + person.getId() + " konnte nicht aktualisiert werden. " + e.getMessage());
        }
    }

    public void addPerson(Person person) {
        try {
            personService.addPerson(person);
            System.out.println("Person mit der ID: " + person.getId() + " wurde erfolgreich hinzugefügt.");
        } catch (Exception e) {
            System.out.println("Person mit der ID: " + person.getId() + " konnte nicht hinzugefügt werden. " + e.getMessage());
        }
    }

    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            personService.removePhoneNumber(personID, phoneNumber);
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " wurde erfolgreich von der Person mit der ID: " + personID + " gelöscht");
        } catch (Exception e) {
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " konnte nicht von der Person mit der ID: " + personID + " gelöscht werden. " + e.getMessage());
        }
    }

    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            personService.addPhoneNumber(personID, phoneNumber);
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " wurde erfolgreich zu der Person mit der ID: " + personID + " hinzugefügt");
        } catch (Exception e) {
            System.out.println("Die Telefonnummer mit der ID: " + phoneNumber.getId() + " konnte nicht zu der Person mit der ID: " + personID + " hinzugefügt werden. " + e.getMessage());
        }
    }

    public void removeAddress(long personID, Address address) {
        try {

            personService.removeAddress(personID, address);
            System.out.println("Die Adresse mit der ID: " + address.getId() + " wurde erfolgreich von der Person mit der ID: " + personID + " gelöscht");
        } catch (Exception e) {
            System.out.println("Die Adresse mit der ID: " + address.getId() + " konnte nicht von der Person mit der ID: " + personID + " gelöscht werden. " + e.getMessage());
        }
    }

    public void addAddress(long personID, Address address) {
        try {
            personService.addAddress(personID, address);
            System.out.println("Die Adresse wurde zu der Person mit ID:+ " + personID + "hinzugefügt.");
        } catch (Exception e) {
            System.out.println("Die Adresse konnte nicht zu der Person mit ID:+ " + personID + "hinzugefügt werden. " + e.getMessage());
        }
    }


    public void deletePerson(long id) {

        //TODO person löschen implementieren
    }

    public void followPerson(long follower, long personToFollow) {
        try {
            personService.followPerson(follower, personToFollow);
            System.out.println("Der Person mit der ID: " + personToFollow + " wurde gefolgt.");
        } catch (Exception e) {
            System.out.println("Person mit der ID: " + personToFollow + " konnte nicht gefolgt werden. " + e.getMessage());
        }
    }

    public void unFollowPerson(long follower, long personToUnfollow) {
        try {
            personService.unfollowPerson(follower, personToUnfollow);
        } catch (Exception e) {
            System.out.println("Person mit der ID: " + personToUnfollow + " konnte nicht entfolgt werden. " + e.getMessage());

        }
    }
}
