package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDtoCreateUser;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.UserMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import com.switchfully.ctrlaltdefeatdigibooky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserUtils {
    private final UserRepository userRepository;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO Need a refactor
    public UserDto saveUser(UserDtoCreateUser userCreateDto, String uuid) {
        if (!isValidEmail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("Email is not valid");
        }
        if (!isUniqueMail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!isUniqueInss(userCreateDto.getUniqueID())) {
            throw new IllegalArgumentException("uniqueID already exists");
        }

        if (userCreateDto.getUserRole() == UserRole.ADMIN || userCreateDto.getUserRole() == UserRole.LIBRARIAN) {
            if (isUUIDUserRole(uuid, UserRole.ADMIN)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to do this.");
            }
        }

        User user = UserMapper.getUser(userCreateDto);
        userRepository.saveUser(user);

        return UserMapper.getUserDto(user);
    }

    //READ ONE
    public UserDto getUser(String email) {
        return UserMapper.getUserDto(
                userRepository.getUser(email)
        );
    }

    //READ MANY
    public List<UserDto> getUsers(String uuid) {

        if (isUUIDUserRole(uuid, UserRole.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see this.");
        }

        return UserMapper.getUserDtoList(userRepository.getUserRepository().values());
    }

    private boolean isUniqueInss(final String uniqueID) {

        return userRepository.getUserRepository().values()
                .stream()
                .noneMatch(user -> user.getUniqueID().equals(uniqueID));
    }

    private boolean isUniqueMail(final String email) {
        return userRepository.getUserRepository().values()
                .stream()
                .noneMatch(user -> user.getEmail().equals(email));
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean isUUIDUserRole(String uuid, UserRole role) {
        if (uuid == null) {
            return true;
        }

        for (User user : userRepository.getUserRepository().values()) {
            if (user.getUniqueID().equals(uuid)) {
                return user.getUserRole() != role;
            }
        }
        return true;
    }

}
