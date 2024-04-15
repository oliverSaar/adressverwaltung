package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    private List<PhoneNumber> phoneNumbers;

    @Override
    public Optional<PhoneNumber> getPhoneNumber(AtomicLong id) {
        return Optional.ofNullable(phoneNumbers.get(id.intValue()));
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public void addPhoneNumber(PhoneNumber phoneNumber) {

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
