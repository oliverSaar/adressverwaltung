package de.dhbw.ase.view;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;
import de.dhbw.ase.utils.AddressSorter;

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
            scanner.nextLine();
            switch (input) {
                case 1:
                    getAllAddresses();
                    break;
                case 2:
                    getAllAddressesSorted();
                    break;
                case 3:
                    getAddress();
                    break;
                case 4:
                    addAddress();
                    break;
                case 5:
                    done = true;
                    break;
                default:
                    System.out.println("Falsche Eingabe");
                    defaultView();
                    break;

            }
        }
        new MainView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginService).defaultView();
    }

    private void getAddress() {


        long id;
        List<Address> addresses = addressRestHelperService.getAllAddresses();

        while (true) {
            System.out.print("Bitte geben Sie die ID der Adresse ein: ");
            try {
                id = scanner.nextInt();

                while (id < 0 || id > addresses.size() - 1) {
                    System.out.println("Bitte geben Sie die korrekte ID ein: ");
                    id = scanner.nextInt();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }


        format(addresses);

    }

    private void getAllAddresses() {
        List<Address> addresses = addressRestHelperService.getAllAddresses();

        format(addresses);

    }

    private void getAllAddressesSorted() {

        AddressSorter addressSorter = new AddressSorter();

        List<Address> addresses = addressRestHelperService.getAllAddresses();
        System.out.println("Nach was möchten Sie sortieren?: ");

        System.out.println("1. Nach Land sortiert");
        System.out.println("2. Nach PLZ sortiert");
        System.out.println("3. Nach Ort sortiert");
        System.out.println("4. Nach Straße sortiert");
        System.out.println("5. Nach Hausnummer sortiert");
        System.out.println("6. Zurück zur Adressansicht");

        int input = scanner.nextInt();
        switch (input) {
            case 1:
                format(addressSorter.sortByCountry(addresses));
                break;
            case 2:
               format(addressSorter.sortByZipCode(addresses));
                break;
            case 3:
                format(addressSorter.sortByCity(addresses));
                break;
            case 4:
                format(addressSorter.sortByStreet(addresses));
                break;
            case 5:
                format(addressSorter.sortByHouseNumber(addresses));
                break;
            default:
                defaultView();
        }
    }

    private static void format(List<Address> addresses) {
        String leftAlignFormat = "| %-4d | %-15s | %-15d | %-15s | %-15s | %-15s |%n";
        System.out.format("+------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");

        System.out.format("| ID   |      Land       |   Postleitzahl  |   Stadt / Ort   |     Straße      |    Hausnummer   |%n");
        System.out.format("+------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");

        for (Address a : addresses) {

            System.out.format(leftAlignFormat, a.getId(), a.getCountry(), a.getZipCode(), a.getCity(), a.getStreetName(), a.getHouseNumber());
        }
        System.out.format("+------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
    }

    public void addAddress() {

        long id = 0;
        String country;
        int zipCode = 0;
        String city;
        String streetName;
        String houseNumber;

        //remove unused nextLine
        scanner.nextLine();

        System.out.print("Bitte geben Sie das Land ein: ");
        country = scanner.nextLine();
        System.out.println();


        while (true) {
            System.out.print("Bitte geben Sie die Postleitzahl ein: ");
            try {
                zipCode = scanner.nextInt();

                while (zipCode < 10000 || zipCode > 99999) {
                    System.out.println("Bitte geben Sie die korrekte Postleitzahl ein (fünfstellig):");
                    zipCode = scanner.nextInt();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }


        System.out.println();
        System.out.println("Bitte geben Sie die Stadt / den Ort ein:");
        city = scanner.nextLine();
        System.out.println();
        System.out.println("Bitte geben Sie den Straßennamen ein:");
        streetName = scanner.nextLine();
        System.out.println();
        System.out.println("Bitte geben Sie die Hausnummer ein:");
        houseNumber = scanner.nextLine();
        System.out.println();

        Address address = new Address(id, country, zipCode, city, streetName, houseNumber);
        addressRestHelperService.addAddress(address);
    }


    public void consoleMenu() {

        System.out.format("\n--------------------------------------------------------------------------------------------------\n");


        System.out.println("Bitte geben Sie die Zahl der Aktion ein: ");

        System.out.println("1. Alle Adressen anzeigen");
        System.out.println("2. Alle Adressen anzeigen (Sortiert)");
        System.out.println("3. Eine Adresse anzeigen (ID benötigt)");
        System.out.println("4. Eine Adresse hinzufügen");
        System.out.println("5. Zurück zum Hauptmenü");
    }
}
