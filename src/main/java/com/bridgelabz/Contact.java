package com.bridgelabz;

public class Contact implements Comparable<Contact>{
    private final String firstName;
    private final String lastName;
    private final String number;
    private final String Address;
    private final String city;
    private final String state;
    private final String email;
    private final int zipCode;

    public Contact(String firstName, String lastName, String number, String address, String city, String state, String email, int zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        Address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Name     :  " + firstName + " " + lastName+"\n"+
        "Number   :  " + number+"\n"+
        "Email    :  " + email+"\n"+
        "Address  :  " + Address+"\n"+
        "City     :  " + city+"\n"+
        "State    :  " + state+"\n"+
        "ZipCode  :  " + zipCode + "\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public int getZipCode() {
        return zipCode;
    }

    @Override
    public int compareTo(Contact that) {
        return this.getFirstName().compareTo(that.getFirstName());
    }
}
