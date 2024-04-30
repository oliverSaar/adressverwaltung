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
        addresses.add(address2);
    }

    @Override
    public Optional<Address> getAddress(long id) throws Exception {
        return Optional.ofNullable(addresses.get((int) id));
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
