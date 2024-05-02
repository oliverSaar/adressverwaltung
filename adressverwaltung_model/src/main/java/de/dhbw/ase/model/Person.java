package de.dhbw.ase.model;

import de.dhbw.ase.model.adapter.Birthday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Person {


    //TODO Personbuilder

    private long id;
    private String firstName;
    private String lastName;

    //TODO Geburtstagsadapter
    private Birthday dateOfBirth;
    private List<Address> addresses = new ArrayList<>();
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
    private boolean loggedIn = false;

    private List<Person> following = new ArrayList<>();
    private LocalDateTime created;
    private LocalDateTime lastModified;


    public Person(long id, String firstName, String lastName, int day, int month, int year, List<Address> addresses, List<PhoneNumber> phoneNumbers) {


        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Birthday(LocalDate.of(year, month, day));

        if (addresses != null) {
            if (!addresses.isEmpty()) {
                this.addresses.addAll(addresses);
            }
        }
        if (phoneNumbers != null) {
            if (!phoneNumbers.isEmpty()) {
                this.phoneNumbers.addAll(phoneNumbers);
            }
        }

        this.created = LocalDateTime.now();
        this.lastModified = null;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setLastModified(LocalDateTime.now());

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setLastModified(LocalDateTime.now());

    }

    public Birthday getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Birthday dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        setLastModified(LocalDateTime.now());
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
        setLastModified(LocalDateTime.now());
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        setLastModified(LocalDateTime.now());
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
        setLastModified(LocalDateTime.now());
    }

    public List<Person> getFollowing() {
        return following;
    }

    public void addFollowing(Person person) {
        this.following.add(person);
        setLastModified(LocalDateTime.now());
    }

    public void removeFollowing(Person person) {
        this.following.remove(person);
        setLastModified(LocalDateTime.now());
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID = ").append(id).append("\n")
                .append("Vorname = ").append(firstName).append('\n')
                .append("Nachname = ").append(lastName).append('\n')
                .append("Geburtsdatum = ").append(dateOfBirth.getBirthday()).append('\n')
                .append("Adresse(n) = ").append(addresses).append('\n')
                .append("Telefonnummer(n) = ").append(phoneNumbers).append('\n')
                .append("folgt = ").append(following).append('\n');
        return sb.toString();
    }

}
