package de.dhbw.ase.model;

import java.time.LocalDateTime;

public class Address {

    private long id;
    private String streetName;
    private String houseNumber;
    private String city;
    private int zipCode;
    private String country;
    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public Address(long id, String country, int zipCode, String city, String streetName, String houseNumber) {
        this.id = id;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.created = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID = ").append(id).append(" Postleitzahl = ").append(zipCode).append(" Straße = ").append(streetName).append(" Hausnummer = ").append(houseNumber);
        return sb.toString();
    }
}
