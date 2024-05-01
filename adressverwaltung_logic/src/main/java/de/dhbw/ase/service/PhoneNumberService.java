package de.dhbw.ase.service;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PhoneNumberService {

    private PhoneNumberDAO phoneNumberDAO;


    public PhoneNumberService(final PhoneNumberDAO phoneNumberDAO) {
        this.phoneNumberDAO = phoneNumberDAO;
    }

    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public PhoneNumber getPhoneNumber(long id) throws Exception {

        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberDAO.getPhoneNumber(id);

        return optionalPhoneNumber
                .orElseThrow(() -> new IllegalArgumentException("Die Telefonnummer mit der ID: " + id + " konnte nicht gefunden werden!"));
    }

    public List<PhoneNumber> getAllPhoneNumbers() throws Exception {

        if (phoneNumberDAO.getPhoneNumbers().isEmpty()) {
            throw new Exception("Keine Telefonnummern gefunden!");
        }
        return phoneNumberDAO.getPhoneNumbers();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) throws Exception {

        phoneNumber.setId(getNextId());
        phoneNumberDAO.insertPhoneNumber(phoneNumber);
    }
}
