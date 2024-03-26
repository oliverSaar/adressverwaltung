package de.dhbw.ase.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Address {

    private UUID id;
    private String streetName;
    private String houseNumber;
    private String city;
    private String zipCode;
    private String country;
    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public Address(String streetName, String houseNumber, String city, String zipCode, String country) {
        this.id = UUID.randomUUID();
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.created = LocalDateTime.now();
        this.lastModified = null;

    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
        setLastModified(LocalDateTime.now());

    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        setLastModified(LocalDateTime.now());

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        setLastModified(LocalDateTime.now());

    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
        setLastModified(LocalDateTime.now());

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        setLastModified(LocalDateTime.now());

    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
