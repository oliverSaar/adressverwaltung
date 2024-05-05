package de.dhbw.ase.service;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public class PhoneNumberService {

    private PhoneNumberDAO phoneNumberDAO;

    public PhoneNumberService(final PhoneNumberDAO phoneNumberDAO) {
        this.phoneNumberDAO = phoneNumberDAO;
    }


    public List<PhoneNumber> getAllPhoneNumbers() throws Exception {

        if (phoneNumberDAO.getPhoneNumbers().isEmpty()) {
            throw new Exception("Es konnten keine Telefonnummern gefunden werden!");
        }
        return phoneNumberDAO.getPhoneNumbers();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) throws Exception {

        phoneNumberDAO.insertPhoneNumber(phoneNumber);
    }
}
