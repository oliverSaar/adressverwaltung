package de.dhbw.ase.dao;

import de.dhbw.ase.model.PhoneNumber;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface PhoneNumberDAO {

    Optional<PhoneNumber> getPhoneNumber(long id);

    List<PhoneNumber> getPhoneNumbers();

    void addPhoneNumber(PhoneNumber phoneNumber);

    void updatePhoneNumber(PhoneNumber phoneNumber);
}
