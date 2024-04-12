package de.dhbw.ase.dao;

import de.dhbw.ase.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    Optional<Person> getPerson(final UUID id);

    List<Person> getPersons();


    void insert(Person person);

    void update(Person person);

}
