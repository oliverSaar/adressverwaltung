package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
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

    public Person getPerson(int id) {
        return personService.getPerson(id);

    }

    public List<Person> getAllPersons() {

        return personService.getAllPersons();

    }

    public void createPerson(Person person) {

        personService.addPerson(person);

    }

    public void deletePhoneNumber(long personID, long phoneNumberID) {
    }

    public void addPhoneNumber(long personID, long phoneNumberID) {
    }

    public void removeAddress(long personID, Address address) {
        personService.removeAddress(personID, address);
    }

    public void addAddress(long personID,Address address) {
        personService.addAddress(personID, address);
    }


    public void deletePerson(long id) {
    }

    public void updatePerson(long id, Person person) {
    }

    public void addPerson(Person person) {
        personService.addPerson(person);
    }

    public void followPerson(long id) {
        personService.followPerson(id);
    }

    public void unFollowPerson(long id) {
        personService.unFollowPerson(id);

    }

    public Person dataInput() {
        return personService.dataInput();
    }
}
