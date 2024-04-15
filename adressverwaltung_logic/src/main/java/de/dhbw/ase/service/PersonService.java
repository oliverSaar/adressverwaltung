package de.dhbw.ase.service;

import de.dhbw.ase.model.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService {

    protected PersonService() {

    }

    private final static AtomicLong ID_COUNTER = new AtomicLong();

    private final Map<Long, Person> persons = new HashMap<>();

    public static long getNextId() {
        return ID_COUNTER.getAndIncrement();
    }

    public Person getPerson(long id) {
        return persons.get(id);
    }

    public void addPerson(Person person) {
//TODO vllt eher in PersonDAO
        persons.put((person.getId().longValue()), person);
    }


}
