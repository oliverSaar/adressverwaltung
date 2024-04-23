package de.dhbw.ase.view;

import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;
import de.dhbw.ase.service.LoginService;

public class PhoneNumberView implements View {

    private final PhoneNumberRestHelperService phoneNumberRestHelperService;
    private final PersonRestHelperService personRestHelperService;
    private final AddressRestHelperService addressRestHelperService;
    private final LoginService loginService;

    public PhoneNumberView(PhoneNumberRestHelperService phoneNumberRestHelperService, PersonRestHelperService personRestHelperService, AddressRestHelperService addressRestHelperService, LoginService loginService) {
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
        this.personRestHelperService = personRestHelperService;
        this.addressRestHelperService = addressRestHelperService;
        this.loginService = loginService;
    }

    public void defaultView() {
    }

    public void addPhoneNumber() {

    }
}
