package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberDAOImpl() {
        PhoneNumber phoneNumber = new PhoneNumber("0123456789", false);
        PhoneNumber phoneNumber1 = new PhoneNumber("9876543210", false);
        PhoneNumber phoneNumber2 = new PhoneNumber("3297626", true);
        PhoneNumber phoneNumber3 = new PhoneNumber("0583724", true);
        phoneNumbers.add(phoneNumber);
        phoneNumbers.add(phoneNumber1);
        phoneNumbers.add(phoneNumber2);
        phoneNumbers.add(phoneNumber3);

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
