package de.dhbw.ase.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Address {

    private AtomicLong id;
    private String streetName;
    private String houseNumber;
    private String city;
    private int zipCode;
    private String country;
    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public Address(AtomicLong id, String streetName, String houseNumber, String city, int zipCode, String country) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.created = LocalDateTime.now();
        this.lastModified = null;

    }

    public AtomicLong getId() {
        return id;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
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
