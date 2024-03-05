package org.ase.model;

import java.time.LocalDateTime;
import java.util.List;

public class Person {

    private String firstName;
    private String lastName;
    private List<Address> addresses;
    private List<PhoneNumber> phoneNumbers;

    private final LocalDateTime created;
    private LocalDateTime lastModified;



    public Person(String firstName, String lastName, List<Address> addresses, List<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;

        assert this.addresses != null;
        this.addresses.addAll(addresses);
        assert this.phoneNumbers != null;
        this.phoneNumbers.addAll(phoneNumbers);
        this.created = LocalDateTime.now();
        this.lastModified = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
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
}
