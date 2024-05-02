package de.dhbw.ase.model;

import java.time.LocalDateTime;

public class PhoneNumber {

    private long id;
    private boolean mobile;
    private String number;

    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public PhoneNumber(long id, String number, boolean mobile) {
        this.id = id;
        this.mobile = mobile;
        this.number = number;
        this.created = LocalDateTime.now();
        this.lastModified = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID = ").append(id).append(", ").append(number).append(", mobil: ").append(mobile).append("}");
        return sb.toString();
    }

}
