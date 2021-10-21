package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {
   private Map<String, User> userRepository;

   public UserRepository(){
      userRepository = new HashMap<>();
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
   /*public boolean removeUser(String id){

   }*/
}
