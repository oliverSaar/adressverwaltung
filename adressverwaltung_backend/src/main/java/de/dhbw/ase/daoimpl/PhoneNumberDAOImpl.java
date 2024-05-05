package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberDAOImpl() {
        PhoneNumber phoneNumber = new PhoneNumber(0, "0123456789", false);
        phoneNumbers.add(phoneNumber);
    }

    @Override
    public Optional<PhoneNumber> getPhoneNumber(long id) throws Exception {
        for (PhoneNumber phoneNumber : phoneNumbers) {
            if (phoneNumber.getId() == id) {
                return Optional.of(phoneNumber);
            }
        }
        throw new Exception("Keine Telefonnummer mit der ID: " + id + " gefunden");
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers() throws Exception {
        return phoneNumbers;
    }

    @Override
    public void insertPhoneNumber(PhoneNumber phoneNumber) throws Exception {
        phoneNumbers.add(phoneNumber);
    }

}
