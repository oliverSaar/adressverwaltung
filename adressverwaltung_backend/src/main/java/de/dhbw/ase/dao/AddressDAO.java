package de.dhbw.ase.dao;

import de.dhbw.ase.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDAO {

    Optional<Address> getAddress(final long id);

    List<Address> getAddresses();

    void insertAddress(Address address);

    void updateAddress(Address address);

}
