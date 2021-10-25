package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.service.FineService;
import org.springframework.stereotype.Service;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDtoCreateUser;
import com.switchfully.ctrlaltdefeatdigibooky.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    private final FineService fineService;

    public UserMapper(FineService service) {
        fineService = service;
    }

    public User getUser(UserDtoCreateUser user) {
        return new User(user.getUserRole(), user.getUniqueID(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getStreetName(),
                user.getStreetNumber(), user.getPostalCode(), user.getCity());
    }

    public UserDto getUserDto(User user) {
        return new UserDto(user.getUserRole(),
                user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getStreetName(), user.getStreetNumber(),
                user.getPostalCode(), user.getCity(),
                String.valueOf(fineService.calculateFinesForUser(user.getUniqueID()))
        );
    }

    public List<UserDto> getUserDtoList(Collection<User> users) {
        return users.stream().map(this::getUserDto).collect(Collectors.toList());
    }

}
