package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Person;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PersonDAOImpl implements PersonDAO {

AtomicLong id = new AtomicLong(0);
    List<Person> persons = List.of(
            new Person(id, "Hans", "Mueller", 1, 1, 1998, List.of(), List.of())
    );

    @Override
    public Optional<Person> getPerson(final long id) {
        //TODO Null check?
        return Optional.ofNullable(persons.get((int)id));
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
        for(Person p : persons){
            if(p.getId() == person.getId()){
                p.setAddresses(person.getAddresses());
                p.setPhoneNumbers(person.getPhoneNumbers());
                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setDateOfBirth(person.getDateOfBirth());
            }
        }

    }

}
