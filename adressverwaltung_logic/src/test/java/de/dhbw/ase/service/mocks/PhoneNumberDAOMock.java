package de.dhbw.ase.service.mocks;

import de.dhbw.ase.daoimpl.PhoneNumberDAOImpl;
import de.dhbw.ase.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAOMock extends PhoneNumberDAOImpl {

    List<PhoneNumber> phoneNumbers = new ArrayList<>();

    private PhoneNumber phoneNumber = new PhoneNumber("111111111", false);
    private PhoneNumber phoneNumber2 = new PhoneNumber("2222", false);
    private PhoneNumber phoneNumber3 = new PhoneNumber("333333333", false);


    public PhoneNumberDAOMock() {

        phoneNumbers.add(phoneNumber);
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