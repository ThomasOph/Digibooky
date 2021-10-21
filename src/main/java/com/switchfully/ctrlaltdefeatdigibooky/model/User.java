package com.switchfully.ctrlaltdefeatdigibooky.model;

public class User {
   private UserRole userRole;
   private String inss;
   private String email;
   private String firstName;
   private String lastName;
   private String streetName;
   private String streetNumber;
   private String postalCode;
   private String city;

   public User(final UserRole userRole, final String inss, final String email
           , final String firstName, final String lastName,
               final String streetName, final String streetNumber,
               final String postalCode, final String city) {
      this.userRole = userRole;
      this.inss = inss;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.streetName = streetName;
      this.streetNumber = streetNumber;
      this.postalCode = postalCode;
      this.city = city;
   }

   public User(final UserRole userRole, final String inss, final String email,
               final String lastName, final String city) {
      this.userRole = userRole;
      this.inss = inss;
      this.email = email;
      this.lastName = lastName;
      this.city = city;
   }

   public UserRole getUserRole() {
      return userRole;
   }

   public String getInss() {
      return inss;
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

   //TODO: Story 6A: out of scope: (Validate postal code and city)
   //TODO: REQUIRED: Validate the email using REGEX;


}
