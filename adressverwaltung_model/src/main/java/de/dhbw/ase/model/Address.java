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

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }


    public int getZipCode() {
        return zipCode;
    }


    public String getCountry() {
        return country;
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

        sb.append("{ID = ").append(id).append(", ").append(zipCode).append(", ").append(city).append(", ").append(streetName).append(", ").append(houseNumber).append("}");
        return sb.toString();
    }
}
