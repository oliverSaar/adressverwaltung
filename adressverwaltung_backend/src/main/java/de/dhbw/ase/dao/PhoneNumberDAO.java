package de.dhbw.ase.dao;

import de.dhbw.ase.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberDAO {

    List<PhoneNumber> getPhoneNumbers() throws Exception;

    void insertPhoneNumber(PhoneNumber phoneNumber) throws Exception;

}
