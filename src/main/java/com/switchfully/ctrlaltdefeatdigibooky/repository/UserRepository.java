package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
   private final Map<String, User> userRepository;

   public UserRepository(){
      userRepository = new HashMap<>();
      userRepository.put("TestID", new User(UserRole.MEMBER, "TestID", "Mail@mail.com", "Thomas", "Opheide", "Blah", "20", "9000", "Gent"));
      userRepository.put("TheAdmin", new User(UserRole.ADMIN, "TheAdmin", "Admin@mail.com", "Wouter", "Www", "Blah", "20", "9000", "Gent"));
      userRepository.put("TheLibrarian", new User(UserRole.LIBRARIAN,
              "TheLibrarian", "librarian@gmail.com", "Hans", "Reygaert",
              "Blah", "22", "9160", "Lokeren"));
   }

   public Map<String, User> getUserRepository() {
      return userRepository;
   }
   public User getUser(String uniqueID){
      return userRepository.get(uniqueID);
   }
   public User saveUser(User user){
      return userRepository.put(user.getUniqueID(), user);
   }

}
