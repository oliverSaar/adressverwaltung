package de.dhbw.ase.view;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.restHelperService.AddressRestHelperService;

import java.util.Scanner;

public class AddressView implements View{

    Scanner scanner = new Scanner(System.in);

AddressRestHelperService addressRestHelperService;


public AddressView(AddressRestHelperService addressRestHelperService) {
    this.addressRestHelperService = addressRestHelperService;
}


    public void defaultView() {


        System.out.println("Bitte geben Sie die Zahl der Aktion ein: ");

        System.out.println("1. Alle Adressen anzeigen");
        System.out.println("2. Eine Adresse anzeigen (ID benötigt)");
        System.out.println("3. Eine Adresse hinzufügen");


        int input = scanner.nextInt();
        switch (input) {
            case 1:
                addressRestHelperService.getAllAddresses();
                scanner.close();
            case 2:
                System.out.print("Bitte geben Sie die ID der Adresse ein: ");
                addressRestHelperService.getAddress(scanner.nextInt());
                scanner.close();
            case 3:
                addAddress();
                scanner.close();
            default:
                scanner.close();
                break;

        }
    }

    public Address addAddress() {

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

        Address address = new Address(id, streetName, houseNumber, city, zipCode, country);
        addressRestHelperService.addAddress(address);
        return address;
    }
}
