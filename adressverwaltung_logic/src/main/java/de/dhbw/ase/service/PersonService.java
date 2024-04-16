package de.dhbw.ase.service;

import de.dhbw.ase.model.Person;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService {

    protected PersonService() {

    }

    private final static AtomicLong ID_COUNTER = new AtomicLong();


    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public Person getPerson(long id) {
        return null;
    }

    public void addPerson(Person person) {

    }


}
