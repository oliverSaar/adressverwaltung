package de.dhbw.ase.restHelperService;

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

    public void createPerson(Person person){

        personService.addPerson(person);

    }

    public void deletePhoneNumber(int personID, int phoneNumberID) {
    }

    public void addPhoneNumber(int personID, int phoneNumberID) {
    }

    public void deleteAddress(int personID, int addressID) {
    }

    public void addAddress(int personID, int addressID) {
    }



    public void deletePerson(int id) {
    }

    public void updatePerson(int id, Person person) {
    }

    public void addPerson(Person person) {
        personService.addPerson(person);
    }

    public void followPerson(int id) {
        personService.followPerson(id);
    }

    public void unFollowPerson(int id) {
        personService.unFollowPerson(id);

    }
}
