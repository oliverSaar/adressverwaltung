package org.ase.model;

import java.time.LocalDateTime;

public class PhoneNumber {


    private boolean mobile;
    private String number;

    private final LocalDateTime created;
    private LocalDateTime lastModified;


    public PhoneNumber(boolean mobile, String number) {
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
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
