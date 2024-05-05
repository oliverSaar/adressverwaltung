package de.dhbw.ase.utils;

import de.dhbw.ase.model.Address;

import java.util.Comparator;
import java.util.List;

public class AddressSorter {


    public List<Address> sortByStreet(List<Address> addresses) {
        addresses.sort(Comparator.comparing(Address::getStreetName));
        return addresses;
    }

    public List<Address> sortByZipCode(List<Address> addresses) {
        addresses.sort(Comparator.comparing(Address::getZipCode));
        return addresses;
    }

    public List<Address> sortByCity(List<Address> addresses) {
        addresses.sort(Comparator.comparing(Address::getCity));
        return addresses;
    }

    public List<Address> sortByCountry(List<Address> addresses) {
        addresses.sort(Comparator.comparing(Address::getCountry));
        return addresses;
    }

    public List<Address> sortByHouseNumber(List<Address> addresses) {
        addresses.sort(Comparator.comparing(Address::getHouseNumber));
        return addresses;
    }
}
