package de.dhbw.ase.service.mocks;

import de.dhbw.ase.daoimpl.PersonDAOImpl;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOMock extends PersonDAOImpl {


    private Person person = new Person(0, "vorname", "nachname", 29, 4, 1990, null, null);

    private Person person2 = new Person(1, "vorname2", "nachname2", 29, 4, 1990, null, null);

    private Person person3 = new Person(2, "vorname3", "nachname3", 29, 4, 1990, null, null);

    private Address address = new Address(0, "test", 0, "test", "test", "test");

    private PhoneNumber phoneNumber = new PhoneNumber("0123456789", false);

    private List<Person> persons = new ArrayList<>();


    public PersonDAOMock() {
        persons.add(person);
        person2.addFollowing(person);
        person2.addAddress(address);
        person2.addPhoneNumber(phoneNumber);
        persons.add(person2);
        persons.add(person3);
    }

    @Override
    public List<Person> getPersons() {


        return persons;

    }

    @Override
    public Optional<Person> getPerson(final long id) {


        try {
            for (Person p : persons) {
                if (p.getId() == id) {
                    return Optional.of(p);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("Person konnte nicht gefunden werden");
            throw new RuntimeException(e);
        }
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

    @Override
    public void addAddress(long personID, Address address) {

        try {
            for (Person p : persons) {
                if (p.getId() == personID) {
                    p.addAddress(address);
                }
            }
        } catch (Exception e) {
            System.out.println("Adresse konnte nicht zu der Person mit der ID: " + personID + " hinzugefügt werden");
        }
    }

    @Override
    public void removeAddress(long personID, Address address) {
        try {
            for (Person p : persons) {
                if (p.getId() == personID) {
                    p.removeAddress(address);
                }
            }
        } catch (Exception e) {
            System.out.println("Adresse konnte nicht von der Person mit der ID: " + personID + " entfernt werden");
        }

    }

    @Override
    public void addPhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            for (Person p : persons) {
                if (p.getId() == personID) {
                    p.addPhoneNumber(phoneNumber);
                }
            }
        } catch (Exception e) {
            System.out.println("Telefonnummer konnte nicht zu der Person mit der ID: " + personID + " hinzugefügt werden");
        }
    }

    @Override
    public void removePhoneNumber(long personID, PhoneNumber phoneNumber) {
        try {
            for (Person p : persons) {
                if (p.getId() == personID) {
                    p.removePhoneNumber(phoneNumber);
                }
            }
        } catch (Exception e) {
            System.out.println("Telefonnummer konnte nicht von der Person mit der ID: " + personID + " entfernt werden");
        }

    }

    @Override
    public void followPerson(long follower, long personToFollow) {


        Optional<Person> toFollow = getPerson(personToFollow);

        if (toFollow.isPresent()) {

            for (Person p : persons) {
                if (p.getId() == follower) {
                    if (p.getFollowing().contains(toFollow.get())) {
                        throw new RuntimeException("Dieser Person wird bereits gefolgt!");
                    }
                    p.addFollowing(toFollow.get());
                }
            }
        }
    }

    @Override
    public void unfollowPerson(long follower, long personToUnfollow) {


        try {
            Optional<Person> toUnfollow = getPerson(personToUnfollow);

            if (toUnfollow.isPresent()) {

                for (Person p : persons) {
                    if (p.getId() == follower) {

                        System.out.println(toUnfollow.get().getFirstName());
                        p.removeFollowing(toUnfollow.get());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Der Person mit der ID: " + personToUnfollow + " konnte nicht gefolgt werden");

        }
    }

}
