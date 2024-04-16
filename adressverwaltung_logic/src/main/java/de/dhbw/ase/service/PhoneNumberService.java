package de.dhbw.ase.service;

import java.util.concurrent.atomic.AtomicLong;

public class PhoneNumberService {

    protected PhoneNumberService() {
    }


    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }
}
