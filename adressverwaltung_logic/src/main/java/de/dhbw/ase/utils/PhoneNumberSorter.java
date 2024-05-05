package de.dhbw.ase.utils;

import de.dhbw.ase.model.PhoneNumber;

import java.util.Comparator;
import java.util.List;

public class PhoneNumberSorter {

    public List<PhoneNumber> sortByNumber(List<PhoneNumber> phoneNumbers) {
        phoneNumbers.sort(Comparator.comparing(PhoneNumber::getNumber));
        return phoneNumbers;
    }

    public List<PhoneNumber> sortByMobile(List<PhoneNumber> phoneNumbers) {
        phoneNumbers.sort(Comparator.comparing(PhoneNumber::isMobile));
        return phoneNumbers;
    }
}
