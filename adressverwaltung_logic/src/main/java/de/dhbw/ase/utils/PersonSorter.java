package de.dhbw.ase.utils;

import de.dhbw.ase.model.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonSorter {

    public List<Person> sortByFirstName(List<Person> persons) {
        persons.sort(Comparator.comparing(Person::getFirstName));
        return persons;
    }

    public List<Person> sortByLastName(List<Person> persons) {
        persons.sort(Comparator.comparing(Person::getLastName));
        return persons;
    }

    public List<Person> sortByAgeYoungest(List<Person> persons) {

        persons.sort(Comparator.comparingInt(t -> t.getDateOfBirth().getAge()));
        return persons;
    }

    public List<Person> sortByAgeOldest(List<Person> persons) {

        persons.sort(Comparator.comparingInt(t -> t.getDateOfBirth().getAge()));
        Collections.reverse(persons);
        return persons;
    }
}

