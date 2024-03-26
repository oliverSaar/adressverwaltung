package de.dhbw.ase.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private List<Address> addresses;
    private List<PhoneNumber> phoneNumbers;

    private LocalDateTime created;
    private LocalDateTime lastModified;


    public Person(String firstName, String lastName, List<Address> addresses, List<PhoneNumber> phoneNumbers) {

        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;

        if (addresses != null) {
            if (!addresses.isEmpty()) {
                this.addresses.addAll(addresses);
            }
        }

        if (phoneNumbers != null){
            if(!phoneNumbers.isEmpty()){
                this.phoneNumbers.addAll(phoneNumbers);
            }
        }

        this.created = LocalDateTime.now();
        this.lastModified = null;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        setLastModified(LocalDateTime.now());

    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
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

}
