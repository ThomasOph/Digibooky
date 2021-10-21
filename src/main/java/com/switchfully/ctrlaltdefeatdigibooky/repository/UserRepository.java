package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {
   private Map<String, User> userRepository;

   public UserRepository(){
      userRepository = new HashMap<>();
      userRepository.put("TestID", new User(UserRole.MEMBER, "TestID", "Mail@mail.com", "Thomas", "Opheide", "Blah", "20", "9000", "Gent"));
      userRepository.put("IkBenEenMongool", new User(UserRole.MEMBER, "TestID", "Mail@mail.com", "Wouter", "Www", "Blah", "20", "9000", "Gent"));

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
