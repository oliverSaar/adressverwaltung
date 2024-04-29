package de.dhbw.ase.view;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;

import java.util.List;
import java.util.Scanner;

public class AddressView implements View {

    Scanner scanner = new Scanner(System.in);

    private final AddressRestHelperService addressRestHelperService;
    private final PersonRestHelperService personRestHelperService;
    private final PhoneNumberRestHelperService phoneNumberRestHelperService;
    private final LoginRestHelperService loginService;


    public AddressView(AddressRestHelperService addressRestHelperService, PersonRestHelperService personRestHelperService, PhoneNumberRestHelperService phoneNumberRestHelperService, LoginRestHelperService loginService) {
        this.addressRestHelperService = addressRestHelperService;
        this.personRestHelperService = personRestHelperService;
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
        this.loginService = loginService;
    }

    public void defaultView() {

        boolean done = false;
        while (!done) {

            consoleMenu();

            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    getAllAddresses();
                    break;
                case 2:
                    System.out.print("Bitte geben Sie die ID der Adresse ein: ");
                    addressRestHelperService.getAddress(scanner.nextInt());
                    break;
                case 3:
                    addAddress();
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    scanner.close();
                    done = true;
                    break;

            }
        }
        new MainView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginService).defaultView();
    }

    private void getAllAddresses() {
        List<Address> addresses = addressRestHelperService.getAllAddresses();

        String leftAlignFormat = "| %-15s | %-15d | %-15s | %-15s | %-15s |%n";
        System.out.format("+-----------------+-----------------+-----------------+-----------------+-----------------+%n");

        System.out.format("|      Land       |   Postleitzahl  |   Stadt / Ort   |     Straße      |    Hausnummer   |%n");
        System.out.format("+-----------------+-----------------+-----------------+-----------------+-----------------+%n");

        for (Address a : addresses) {

            System.out.format(leftAlignFormat, a.getCountry(), a.getZipCode(), a.getCity(), a.getStreetName(), a.getHouseNumber());
        }
        System.out.format("+-----------------+-----------------+-----------------+-----------------+-----------------+%n");

    }

    public void addAddress() {

        long id = 0;
        String country;
        int zipCode;
        String city;
        String streetName;
        String houseNumber;

        System.out.print("Bitte geben Sie das Land ein: ");
        country = scanner.next();
        System.out.println();
        System.out.println("Bitte geben Sie die Postleitzahl ein:");
        zipCode = scanner.nextInt();
        System.out.println();
        System.out.println("Bitte geben Sie die Stadt / den Ort ein:");
        city = scanner.next();
        System.out.println();
        System.out.println("Bitte geben Sie den Straßennamen ein:");
        streetName = scanner.next();
        System.out.println();
        System.out.println("Bitte geben Sie die Hausnummer ein:");
        houseNumber = scanner.next();

        Address address = new Address(id, country, zipCode, city, streetName, houseNumber);
        addressRestHelperService.addAddress(address);
    }


    public void consoleMenu() {
        System.out.println("Bitte geben Sie die Zahl der Aktion ein: ");

        System.out.println("1. Alle Adressen anzeigen");
        System.out.println("2. Eine Adresse anzeigen (ID benötigt)");
        System.out.println("3. Eine Adresse hinzufügen");
        System.out.println("4. Zurück zum Hauptmenü");
    }
}
