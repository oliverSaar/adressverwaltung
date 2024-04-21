package de.dhbw.ase.service;

import de.dhbw.ase.dao.PhoneNumberDAO;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicLong;

public class PhoneNumberService {

    private PhoneNumberDAO phoneNumberDAO;

    protected PhoneNumberService() {
    }

    @Inject
    public PhoneNumberService(final PhoneNumberDAO phoneNumberDAO) {
        this.phoneNumberDAO = phoneNumberDAO;
    }

    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }
}
