package de.dhbw.ase;

import de.dhbw.ase.model.Person;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Person test = new Person("Max", "Mustermann", null, null);
        System.out.println(test);
    }
}
