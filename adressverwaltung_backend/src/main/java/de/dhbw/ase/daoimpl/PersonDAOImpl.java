package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {

    List<Person> persons = new ArrayList<>();

    public PersonDAOImpl() {
        Person admin = new Person(0, "admin", "admin", 24, 4, 1990, null, null);
        persons.add(admin);
        Person person = new Person(1, "test", "user", 1, 1, 1999, null, null);
        person.addFollowing(admin);
        persons.add(person);


    }


    @Override
    public Optional<Person> getPerson(final long id) throws Exception {

        for (Person p : persons) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();

    }

    @Override
    public List<Person> getPersons() throws Exception {
        return persons;
    }

    @Override
    public void insertPerson(Person person) throws Exception {
        persons.add(person);

    }

    @Override
    public void updatePerson(Person person) throws Exception {
        for (Person p : persons) {
            if (p.getId() == person.getId()) {
                p.setAddresses(person.getAddresses());
                p.setPhoneNumbers(person.getPhoneNumbers());
                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setDateOfBirth(person.getDateOfBirth());
            }
        }

    }

    @Override
    public void addAddress(long personID, Address address) throws Exception {

        for (Person p : persons) {
            if (p.getId() == personID) {
                p.addAddress(address);
            }
        }
    }

    @Override
    public void removeAddress(long personID, Address address) throws Exception {
        for (Person p : persons) {
            if (p.getId() == personID) {
                p.removeAddress(address);
            }
        }
    }

    @Override
    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {

        for (Person p : persons) {
            if (p.getId() == personID) {
                p.addPhoneNumber(phoneNumber);
            }
        }
    }

    @Override
    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception {
        for (Person p : persons) {
            if (p.getId() == personID) {
                p.removePhoneNumber(phoneNumber);
            }
        }

    }

    @Override
    public void followPerson(long follower, long personToFollow) throws Exception {


        Optional<Person> toFollow = getPerson(personToFollow);

        if (toFollow.isPresent()) {

            for (Person p : persons) {
                if (p.getId() == follower) {
                    if (p.getFollowing().contains(toFollow.get())) {
                        throw new RuntimeException("Dieser Person wird bereits gefolgt!");
                    }
                    p.addFollowing(toFollow.get());
                }
            }
        }

    }

    @Override
    public void unfollowPerson(long follower, long personToUnfollow) throws Exception {


        Optional<Person> toUnfollow = getPerson(personToUnfollow);

        if (toUnfollow.isPresent()) {

            for (Person p : persons) {
                if (p.getId() == follower) {
                    if (p.getFollowing().contains(getPerson(personToUnfollow).get())) {
                        p.removeFollowing(toUnfollow.get());
                    }
                    else{
                        throw new Exception("Der Person mit der ID: " + personToUnfollow + " wird nicht gefolgt!");
                    }
                }
            }
        }
    }
}
