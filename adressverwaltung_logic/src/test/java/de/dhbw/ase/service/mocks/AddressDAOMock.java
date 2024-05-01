package de.dhbw.ase.service.mocks;

import de.dhbw.ase.daoimpl.AddressDAOImpl;
import de.dhbw.ase.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAOMock extends AddressDAOImpl {

    List<Address> addresses = new ArrayList<>();

    private Address address = new Address(0, "country", 11111, "city", "street", "housenumber");
    private Address address2 = new Address(1, "country2", 22222, "city2", "street2", "housenumber2");
    private Address address3 = new Address(2, "country3", 33333, "city3", "street3", "housenumber3");

    public AddressDAOMock() {
        addresses.add(address);
        addresses.add(address2);
        addresses.add(address3);
    }


    @Override
    public Optional<Address> getAddress(long id) throws Exception {
        for (Address address : addresses) {
            if (address.getId() == id) {
                return Optional.of(address);
            }
        }
        return Optional.empty();
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
