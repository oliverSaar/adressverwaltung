package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonDAOImpl implements PersonDAO {


    List<Person> persons = null;

    @Override
    public Optional<Person> getPerson(final UUID id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void insert(Person person) {
        persons.add(person);

    }

    @Override
    public void update(Person person) {

    }

}
