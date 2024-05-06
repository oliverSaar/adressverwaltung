package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.AddressDAO;
import de.dhbw.ase.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAOImpl implements AddressDAO {

    List<Address> addresses = new ArrayList<>();

    public AddressDAOImpl() {
        Address address = new Address(0, "country", 12345, "city", "streetName", "7357A");
        addresses.add(address);
        Address address2 = new Address(1, "land", 98765, "stadt", "hauptstraße", "13b");
        Address address3 = new Address(2, "Deutschland", 77656, "Offenburg", "Edekastraße", "1");
        Address address4 = new Address(3, "Legoland", 73601, "Technik", "Todesstern", "9s");
        addresses.add(address2);
        addresses.add(address3);
        addresses.add(address4);
    }

    @Override
    public Optional<Address> getAddress(long id) throws Exception {

        for (Address address : addresses) {
            if (address.getId() == id) {
                return Optional.of(address);
            }
        }
        throw new Exception("Die Adresse mit der ID: " + id + " konnte nicht gefunden werden.");
    }

    @Override
    public List<Address> getAddresses() throws Exception {
        return addresses;
    }

    @Override
    public void insertAddress(Address address) throws Exception {
        addresses.add(address);

    }
}
