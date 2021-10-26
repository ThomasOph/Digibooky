package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.UserDtoCreateUser;
import com.switchfully.ctrlaltdefeatdigibooky.exceptions.UserNotFoundException;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.UserMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.User;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import com.switchfully.ctrlaltdefeatdigibooky.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserUtils {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto saveUser(UserDtoCreateUser userCreateDto, String uuid) {
        if (!isValidEmail(userCreateDto.getEmail())) {
            logger.warn("Creating user - Email not valid");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not valid");
        }
        if (!isUniqueMail(userCreateDto.getEmail())) {
            logger.warn("Creating user - Email already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        if (!isUniqueInss(userCreateDto.getUniqueID())) {
            logger.warn("Creating user - uniqueID already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "uniqueID already exists");
        }
        if (userCreateDto.getUserRole() == UserRole.ADMIN || userCreateDto.getUserRole() == UserRole.LIBRARIAN) {
            if (!isUUIDUserRole(uuid, UserRole.ADMIN)) {
                logger.warn("Creating user - No authorization to create a higher user role");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to do this.");
            }
        }

        User user = userMapper.getUser(userCreateDto);
        userRepository.saveUser(user);

        logger.info("New user created " + userCreateDto.getUserRole());
        return userMapper.getUserDto(user);
    }

    //READ ONE
    public UserDto getUser(String uuid) {
        return userMapper.getUserDto(
                userRepository.getUser(uuid)
        );
    }

    //READ MANY
    public List<UserDto> getUsers(String uuid) {

        if (!isUUIDUserRole(uuid, UserRole.ADMIN)) {
            logger.warn("User is not allowed to watch all users info");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see this.");
        }

        return userMapper.getUserDtoList(userRepository.getUserRepository().values());
    }

    private boolean isUniqueInss(final String uniqueID) {

        if (uniqueID == null) {
            return false;
        }

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

        if (email == null) {
            return false;
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean isUUIDUserRole(String uuid, UserRole role) {
        if (uuid == null || role == null) {
            return false;
        }

        User user = userRepository.getUserRepository().get(uuid);
        if (user == null) {
            logger.warn("No user found when checking for role");
            throw new UserNotFoundException();
        }

        return user.getUserRole() == role;
    }

    public String getUserDetails(String uuid) {
        return userRepository.getUser(uuid).toString();
    }
}
