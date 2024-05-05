package de.dhbw.ase.model;

import java.time.LocalDateTime;

public class PhoneNumber {

    private boolean mobile;
    private String number;

    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public PhoneNumber(String number, boolean mobile) {
        this.mobile = mobile;
        this.number = number;
        this.created = LocalDateTime.now();
        this.lastModified = null;
    }

    public boolean isMobile() {
        return mobile;
    }


    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof PhoneNumber && number.equals(((PhoneNumber) o).number) && mobile == ((PhoneNumber) o).mobile;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(", mobil: ").append(mobile).append("}");
        return sb.toString();
    }

}
