package com.switchfully.ctrlaltdefeatdigibooky.model;

public enum UserRole {
   ADMIN("admin"), LIBRARIAN("librarian"), MEMBER("member");

   private String name;

   UserRole(String name) {
      this.name = name;
   }
}
