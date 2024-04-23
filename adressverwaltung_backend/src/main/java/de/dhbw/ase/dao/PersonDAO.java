package de.dhbw.ase.dao;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    Optional<Person> getPerson(final long id);

    List<Person> getPersons();


    void insertPerson(Person person);

    void updatePerson(Person person);

    void addAddress(long personID, Address address);

    void removeAddress(long personID, Address address);
}
