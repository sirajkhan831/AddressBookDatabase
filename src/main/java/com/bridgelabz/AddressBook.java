package com.bridgelabz;

import java.sql.SQLException;
import java.util.*;

public class AddressBook {
    public static void main(String[] args) throws SQLException {
        LinkedList<Contact> contact = new LinkedList<>();
        Database.importContact(contact);
        System.out.println("Hello welcome to your address book.");
        menu(contact);
    }

    // Menu method for user interaction
    public static void menu(LinkedList<Contact> contact) {
        System.out.println("Press 1 -> to view all your contacts.");
        System.out.println("Press 2 -> to add a contact.");
        System.out.println("Press 3 -> to remove a contact.");
        System.out.println("Press 4 -> to modify a contact.");
        System.out.println("Press 9 -> to stop the program.");
        Scanner optionScan = new Scanner(System.in);
        int option = optionScan.nextInt();
        switch (option) {
            case 1 : {
                printList(contact);
                contactDetails(contact);
                System.out.print("Enter 99 to continue to the menu : ");
                Scanner menuOptScan = new Scanner(System.in);
                int menuOpt = menuOptScan.nextInt();
                if (menuOpt == 99) {
                    menu(contact);
                }
                break;
            }
            case 2 : {
                Contact newContact = addContact();
                Database.addContact(newContact);
                contact.add(newContact);
                printList(contact);
                contactDetails(contact);
                System.out.print("Enter 99 to continue to the menu : ");
                Scanner menuOptScan = new Scanner(System.in);
                int menuOpt = menuOptScan.nextInt();
                if (menuOpt == 99) {
                    menu(contact);
                }
                break;
            }
            case 3 : {
                printList(contact);
                delete(contact);
                printList(contact);
                System.out.print("Enter 99 to continue to the menu : ");
                Scanner menuOptScan = new Scanner(System.in);
                int menuOpt = menuOptScan.nextInt();
                if (menuOpt == 99) {
                    menu(contact);
                }
                break;
            }
            case 4 : {
                modify(contact);
                printList(contact);
                contactDetails(contact);
                System.out.print("Enter 99 to continue to the menu : ");
                Scanner menuOptScan = new Scanner(System.in);
                int menuOpt = menuOptScan.nextInt();
                if (menuOpt == 99) {
                    menu(contact);
                }
                break;
            }
            case 9 : System.exit(1);
            break;
        }
    }

    // contactDetails method which prints out all the details of given method via getAll(); inside Contact class
    public static void contactDetails(LinkedList<Contact> contact) {
        System.out.println("Enter index position for all the details for chosen contact or press 99 for menu: ");
        Scanner numberScan = new Scanner(System.in);
        int number = numberScan.nextInt();
        if (number == 99) {
            menu(contact);
        } else
            System.out.println(contact.get(number));
    }

    // printList for printing the sorted contacts in the console
    public static void printList(LinkedList<Contact> contacts) {
        Collections.sort(contacts);
        int index = 0;
        System.out.println("  Index  |   Name");
        System.out.println("---------|---------");
        for (int i = 1; i < contacts.size(); i++) {
            index++;
            if (index < 10) {
                System.out.println(index + "        |   " + contacts.get(i).getFirstName());
            }
            else System.out.println(index + "       |   " + contacts.get(i).getFirstName());
        }
        System.out.println("\n");
    }

    // addContact method to add a new contact by creating a new class and returning it through addContact();
    public static Contact addContact() {
        System.out.println("Enter a new first name (The first letter should be in uppercase): ");
        Scanner firstNameScan = new Scanner(System.in);
        String firstNameTemp = firstNameScan.nextLine();
        String firstLetter = firstNameTemp.substring(0, 1).toUpperCase();
        String remLetter = firstNameTemp.substring(1);
        String firstName = firstLetter + remLetter;
        System.out.println("Enter last name : ");
        Scanner lastNameScan = new Scanner(System.in);
        String lastNameTemp = lastNameScan.nextLine();
        String firstLetterLastName = lastNameTemp.substring(0, 1).toUpperCase();
        String remLetterLastName = lastNameTemp.substring(1);
        String lastName = firstLetterLastName + remLetterLastName;
        System.out.println("Enter contact number : ");
        Scanner contactScan = new Scanner(System.in);
        String contact = contactScan.nextLine();
        System.out.println("Enter Email : ");
        Scanner emailScan = new Scanner(System.in);
        String email = emailScan.nextLine();
        System.out.println("Enter city : ");
        Scanner cityScan = new Scanner(System.in);
        String city = cityScan.nextLine();
        System.out.println("Enter State : ");
        Scanner stateScan = new Scanner(System.in);
        String state = stateScan.nextLine();
        System.out.println("Enter Address : ");
        Scanner addressScan = new Scanner(System.in);
        String address = addressScan.nextLine();
        System.out.println("Enter ZipCode : ");
        Scanner zipScan = new Scanner(System.in);
        int zipcode = zipScan.nextInt();

        return new Contact(firstName, lastName, contact, address, city, state, email, zipcode);
    }

    // delete method to delete selected contact by removing it from the linked list
    public static void delete(LinkedList<Contact> contacts) {
        System.out.print("Enter the NAME of contact to delete : ");
        Scanner nameScan = new Scanner(System.in);
        String name = nameScan.nextLine();
        for (int i = 0; i < contacts.size(); i++) {
            if (Objects.equals(name, contacts.get(i).getFirstName()))
            {
                System.out.println(name);
                contacts.remove(i);
                Database.removeContact(name);
            }
        }
        System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() + " has been deleted successfully");
    }

    //modify method to modify selected contact
    public static void modify(LinkedList<Contact> contacts) {
        System.out.print("Enter the NAME of contact to modify : ");
        Scanner nameScan = new Scanner(System.in);
        String name = nameScan.nextLine();
        for (int i = 0; i < contacts.size(); i++) {
            if (Objects.equals(name, contacts.get(i).getFirstName()))
            {
                System.out.println(name);
                contacts.remove(i);
                Database.removeContact(name);
            }
        }
        Contact newContact = addContact();
        Database.addContact(newContact);
        contacts.add(newContact);
        printList(contacts);
        contactDetails(contacts);
        System.out.print("Enter 99 to continue to the menu : ");
        Scanner menuOptScan = new Scanner(System.in);
        int menuOpt = menuOptScan.nextInt();
        if (menuOpt == 99) {
            menu(contacts);
        }
        System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() + " has been modified successfully");
    }
}