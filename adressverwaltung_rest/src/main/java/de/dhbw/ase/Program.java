package de.dhbw.ase;

import de.dhbw.ase.dao.AddressDAO;
import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.dao.PhoneNumberDAO;
import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.daoimpl.AddressDAOImpl;
import de.dhbw.ase.daoimpl.PersonDAOImpl;
import de.dhbw.ase.daoimpl.PhoneNumberDAOImpl;
import de.dhbw.ase.daoimpl.UserPasswordDAOImpl;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;
import de.dhbw.ase.service.AddressService;
import de.dhbw.ase.service.LoginService;
import de.dhbw.ase.service.PersonService;
import de.dhbw.ase.service.PhoneNumberService;
import de.dhbw.ase.view.MainView;

public class Program {

    public static void main(String[] args) {


        PersonDAO personDAO = new PersonDAOImpl();
        AddressDAO addressDAO = new AddressDAOImpl();
        PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAOImpl();
        UserPasswordDAO userPasswordDAO = new UserPasswordDAOImpl();


        PersonService personService = new PersonService(personDAO);
        AddressService addressService = new AddressService(addressDAO);
        PhoneNumberService phoneNumberService = new PhoneNumberService(phoneNumberDAO);
        LoginService loginService = new LoginService(userPasswordDAO, personService);


        PersonRestHelperService personRestHelperService = new PersonRestHelperService(personService);
        AddressRestHelperService addressRestHelperService = new AddressRestHelperService(addressService);
        PhoneNumberRestHelperService phoneNumberRestHelperService = new PhoneNumberRestHelperService(phoneNumberService);
        LoginRestHelperService loginRestHelperService = new LoginRestHelperService(loginService);


        MainView mainView = new MainView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginRestHelperService);

        mainView.loginView();
    }
}

