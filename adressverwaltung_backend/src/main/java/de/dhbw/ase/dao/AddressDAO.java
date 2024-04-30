package de.dhbw.ase.dao;

import de.dhbw.ase.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDAO {

    Optional<Address> getAddress(final long id) throws Exception;

    List<Address> getAddresses() throws Exception;

    void insertAddress(Address address) throws Exception;

}
