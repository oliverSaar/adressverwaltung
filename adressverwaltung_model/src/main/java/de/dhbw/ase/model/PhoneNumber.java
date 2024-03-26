package de.dhbw.ase.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PhoneNumber {

    private UUID id;
    private boolean mobile;
    private String number;

    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public PhoneNumber(boolean mobile, String number) {
        this.id = UUID.randomUUID();
        this.mobile = mobile;
        this.number = number;
        this.created = LocalDateTime.now();
        this.lastModified = null;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
        setLastModified(LocalDateTime.now());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
