package de.dhbw.ase.dao;

import de.dhbw.ase.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface AddressDAO {

    Optional<Address> getAddress(final AtomicLong id);

    List<Address> getAddress();

    void insertAddress(Address address);

    void updateAddress(Address address);
}
