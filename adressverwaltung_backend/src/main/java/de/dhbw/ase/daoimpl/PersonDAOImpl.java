package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {

    List<Person> persons = new ArrayList<>();

    public PersonDAOImpl() {
        Person admin = new Person(0, "admin", "admin", 24, 4, 1990, null, null);
        persons.add(admin);
        Person person = new Person(1, "test", "user", 1, 1, 1999, null, null);
        person.addFollowing(admin);
        persons.add(person);


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


        try {
            Optional<Person> toFollow = getPerson(personToFollow);

            System.out.println("außen");
            if (toFollow.isPresent()) {

                System.out.println("drinne");
                for (Person p : persons) {
                    if (p.getId() == follower) {

                        System.out.println("if schleife");
                        System.out.println(toFollow.get().getFirstName());
                        p.addFollowing(toFollow.get());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Der Person mit der ID: " + personToFollow + " konnte nicht gefolgt werden");

        }
    }

    @Override
    public void unfollowPerson(long follower, long personToUnfollow) {


        try {
            Optional<Person> toUnfollow = getPerson(personToUnfollow);

            System.out.println("außen");
            if (toUnfollow.isPresent()) {

                System.out.println("drinne");
                for (Person p : persons) {
                    if (p.getId() == follower) {

                        System.out.println("if schleife");
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
