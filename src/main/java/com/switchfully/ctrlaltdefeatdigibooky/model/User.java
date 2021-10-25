package com.switchfully.ctrlaltdefeatdigibooky.model;


public class User {
   private final UserRole userRole;
   private final String uniqueID;
   private final String email;
   private String firstName;
   private final String lastName;
   private String streetName;
   private String streetNumber;
   private String postalCode;
   private final String city;

   public User(final UserRole userRole, final String uniqueID, final String email
           , final String firstName, final String lastName,
               final String streetName, final String streetNumber,
               final String postalCode, final String city) {
      this.userRole = userRole;
      this.uniqueID = uniqueID;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.streetName = streetName;
      this.streetNumber = streetNumber;
      this.postalCode = postalCode;
      this.city = city;
   }

   public User( UserRole userRole, String uniqueID, String email,
               String lastName, String city) {
      this.userRole = userRole;
      this.uniqueID = uniqueID;
      this.email = email;
      this.lastName = lastName;
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
      return getFirstName() + " " + getLastName();
   }

   //TODO: Story 6A: out of scope: (Validate postal code and city)
   //TODO: REQUIRED: Validate the email using REGEX;

}
