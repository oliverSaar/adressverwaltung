package de.dhbw.ase.dao;

import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberDAO {

    Optional<PhoneNumber> getPhoneNumber(long id) throws Exception;

    List<PhoneNumber> getPhoneNumbers() throws Exception;

    void insertPhoneNumber(PhoneNumber phoneNumber) throws Exception;

}
