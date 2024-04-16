package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.AddressDAO;
import de.dhbw.ase.model.Address;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AddressDAOImpl implements AddressDAO {

    List<Address> addresses;


    @Override
    public Optional<Address> getAddress(long id) {
        return Optional.ofNullable(addresses.get((int)id));
    }

    @Override
    public List<Address> getAddress() {
        return addresses;
    }

    @Override
    public void insertAddress(Address address) {
        addresses.add(address);

    }

    @Override
    public void updateAddress(Address address) {
        for (Address a : addresses) {
            if (Objects.equals(a.getId(), address.getId())) {
                a.setStreetName(address.getStreetName());
                a.setHouseNumber(address.getHouseNumber());
                a.setCity(address.getCity());
                a.setZipCode(address.getZipCode());
                a.setCountry(address.getCountry());
            }
        }

    }
}
