package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDtoCreateUser;
import com.switchfully.ctrlaltdefeatdigibooky.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
   public static User getUser(UserDtoCreateUser user) {
      return new User(user.getUserRole(), user.getUniqueID(), user.getEmail(),
              user.getFirstName(), user.getLastName(), user.getStreetName(),
              user.getStreetNumber(), user.getPostalCode(), user.getCity());
   }

   public static UserDto getUserDto(User user) {
      return new UserDto(user.getUserRole(),
              user.getEmail(), user.getFirstName(), user.getLastName(),
              user.getStreetName(), user.getStreetNumber(),
              user.getPostalCode(), user.getCity());
   }

   public static List<UserDto> getUserDtoList(Collection<User> users) {
      return users.stream().map(UserMapper::getUserDto).collect(Collectors.toList());
   }

}
