package de.dhbw.ase.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Person {


    private long id;
    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;
    private List<Address> addresses;
    private List<PhoneNumber> phoneNumbers;
    private boolean loggedIn = false;

    private List<Person> following;
    private LocalDateTime created;
    private LocalDateTime lastModified;


    public Person(long id, String firstName, String lastName, int day, int month, int year, List<Address> addresses, List<PhoneNumber> phoneNumbers) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.of(year, month, day);

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", addresses=" + addresses +
                ", phoneNumbers=" + phoneNumbers +
                ", following=" + following +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }
}
