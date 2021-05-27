package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UpdateUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.auth.IncorrectCredentialsException;
import com.moviesandchill.usermanagementservice.exception.auth.PasswordMismatchException;
import com.moviesandchill.usermanagementservice.exception.auth.UserIsBannedException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users",
        produces = "application/json"
)
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.addUser(newUserDto);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable long userId, @RequestBody UpdateUserDto updateUserDto) throws UserNotFoundException {
        userService.updateUser(userId, updateUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping("/{userId}/password")
    public void updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) throws UserNotFoundException, PasswordMismatchException {
        userService.updateUserPassword(userId, updatePasswordDto);
    }

    @PostMapping(path = "/{userId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUserLogo(@PathVariable long userId, @RequestPart("file") MultipartFile file) throws UserNotFoundException {
        userService.updateUserLogo(userId, file);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) throws IncorrectCredentialsException, UserIsBannedException {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @PostMapping("/{userId}/ban")
    public void ban(@PathVariable long userId) throws UserNotFoundException {
        userService.ban(userId);
    }

    @PostMapping("/{userId}/unban")
    public void unban(@PathVariable long userId) throws UserNotFoundException {
        userService.unban(userId);
    }
}
