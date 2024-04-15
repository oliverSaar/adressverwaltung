package de.dhbw.ase.dao;

import de.dhbw.ase.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface PersonDAO {

    Optional<Person> getPerson(final AtomicLong id);

    List<Person> getPersons();


    void insert(Person person);

    void update(Person person);

}
