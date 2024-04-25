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
        return personService.getPerson(id);

    }

    public List<Person> getAllPersons() {

        return personService.getAllPersons();

    }

    public void createPerson(Person person) {

        personService.addPerson(person);

    }

    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) {
        personService.removePhoneNumber(personID, phoneNumber);
    }

    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) {
        personService.addPhoneNumber(personID, phoneNumber);

    }

    public void removeAddress(long personID, Address address) {
        personService.removeAddress(personID, address);
    }

    public void addAddress(long personID,Address address) {
        personService.addAddress(personID, address);
    }


    public void deletePerson(long id) {
    }

    public void updatePerson(Person person, long loggedInUserID) {
        personService.updatePerson(person, loggedInUserID);
    }

    public void addPerson(Person person) {
        personService.addPerson(person);
    }

    public void followPerson(long follower, long personToFollow) {
        personService.followPerson(follower, personToFollow);
    }

    public void unFollowPerson(long follower, long personToUnfollow) {
        personService.unfollowPerson(follower, personToUnfollow);

    }
}
