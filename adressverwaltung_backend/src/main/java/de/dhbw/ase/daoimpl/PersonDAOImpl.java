package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {

    List<Person> persons = new ArrayList<>();


    @Override
    public Optional<Person> getPerson(final long id) {
        return Optional.ofNullable(persons.get((int) id));
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void insertPerson(Person person) {
        persons.add(person);

    }

    @Override
    public void updatePerson(Person person) {
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

}
