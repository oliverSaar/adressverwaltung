package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberDAOImpl() {
        PhoneNumber phoneNumber = new PhoneNumber(0, false, "0123456789");
        phoneNumbers.add(phoneNumber);
    }

    @Override
    public Optional<PhoneNumber> getPhoneNumber(long id) {
        return Optional.ofNullable(phoneNumbers.get((int) id));
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public void insertPhoneNumber(PhoneNumber phoneNumber) {

        phoneNumbers.add(phoneNumber);
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber) {

        for (PhoneNumber p : phoneNumbers) {
            if (p.getId() == phoneNumber.getId()) {
                p.setNumber(phoneNumber.getNumber());
                p.setMobile(phoneNumber.isMobile());
            }
        }


    }
}
