package de.dhbw.ase.service.mocks;

import de.dhbw.ase.daoimpl.PersonDAOImpl;
import de.dhbw.ase.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOMock extends PersonDAOImpl {


    private Person person = new Person(0, "vorname", "nachname", 29, 4, 1990, null, null);

    private Person person2 = new Person(1, "vorname2", "nachname2", 29, 4, 1990, null, null);

    private Person person3 = new Person(2, "vorname3", "nachname3", 29, 4, 1990, null, null);

    private List<Person> persons = new ArrayList<>();


    public PersonDAOMock() {
        persons.add(person);
        persons.add(person2);
        persons.add(person3);
    }

    @Override
    public List<Person> getPersons() {


        return persons;

    }

    @Override
    public Optional<Person> getPerson(final long id) {


        for (Person p : persons) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return null;
    }


    @Override
    public void insertPerson(Person person) {

        persons.add(person);
    }

}
