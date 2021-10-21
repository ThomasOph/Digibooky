package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDtoCreateUser;
import com.switchfully.ctrlaltdefeatdigibooky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET ONE
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    //GET ALL
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }


    //POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDtoCreateUser userDto) {
        return userService.saveUser(userDto);
    }

    //UPDATE
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public UserDto updateUser(@PathVariable("id") String id, @RequestBody UserDtoCreateUser userDto) {
        if (!id.equals(userDto.getUniqueID())) {
            throw new IllegalArgumentException("123 -> You suck");
        }

        return userService.updateUser(userDto);
    }

    //DELETE
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }
}
