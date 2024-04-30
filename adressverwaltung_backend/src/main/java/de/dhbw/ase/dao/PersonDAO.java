package de.dhbw.ase.dao;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    Optional<Person> getPerson(final long id) throws Exception;

    List<Person> getPersons() throws Exception;


    void insertPerson(Person person) throws Exception;

    void updatePerson(Person person) throws Exception;

    void addAddress(long personID, Address address) throws Exception;

    void removeAddress(long personID, Address address) throws Exception;

    void addPhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception;


    void removePhoneNumber(long personID, PhoneNumber phoneNumber) throws Exception;

    void followPerson(long follower, long personToFollow) throws Exception;

    void unfollowPerson(long follower, long personToFollow) throws Exception;
}
