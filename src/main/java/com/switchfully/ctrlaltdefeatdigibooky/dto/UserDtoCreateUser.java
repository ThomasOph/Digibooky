package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;

// CODEREVIEW CreateUserDto would be a more conventional name
public class UserDtoCreateUser {
    private final UserRole userRole;
    private final String uniqueID;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String city;

    // CODEREVIEW looooooots of Strings. Have you considered using the Builder pattern?
    public UserDtoCreateUser(final String uniqueID,
                             final String email,
                             final String firstName, final String lastName,
                             final String streetName, final String streetNumber,
                             final String postalCode, final String city) {
        this.uniqueID = uniqueID;
        this.userRole = UserRole.MEMBER;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "UserDtoCreateUser{" +
                "userRole='" + userRole + '\'' +
                ", uniqueID='" + uniqueID + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
