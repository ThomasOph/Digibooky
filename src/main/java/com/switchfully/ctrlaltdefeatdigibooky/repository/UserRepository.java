package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRepository {
   Map<String, User> userRepository;

   public UserRepository(final Map<String, User> userRepository) {
      this.userRepository = userRepository;
   }

   public Map<String, User> getUserRepository() {
      return userRepository;
   }
   public User getUser(String email){
      return userRepository.get(email);
   }
   public User saveUser(User user){
      return userRepository.put(user.getEmail(), user);
   }

}
