package de.dhbw.ase.service;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
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

    public PhoneNumber getPhoneNumber(long id) {

        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberDAO.getPhoneNumber(id);

        return optionalPhoneNumber
                .orElseThrow(() -> new IllegalArgumentException("Telefonnummer mit der ID: " + id + " konnte nicht gefunden werden"));
    }

    public List<PhoneNumber> getAllPhoneNumbers() {

        return phoneNumberDAO.getPhoneNumbers();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumber.setId(getNextId());
        phoneNumberDAO.insertPhoneNumber(phoneNumber);
    }
}
