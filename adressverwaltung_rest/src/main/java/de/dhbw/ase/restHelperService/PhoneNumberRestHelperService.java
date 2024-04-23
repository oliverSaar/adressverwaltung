package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.PhoneNumberService;

import javax.inject.Inject;

public class PhoneNumberRestHelperService {

    @Inject
    private PhoneNumberService phoneNumberService;

    @Inject
    public PhoneNumberRestHelperService(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public PhoneNumber getPhoneNumber(long id) {
        return phoneNumberService.getPhoneNumber(id);
    }
}
