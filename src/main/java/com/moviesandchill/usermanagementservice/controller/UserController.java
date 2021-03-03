package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.addUser(newUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/friends")
    public List<UserDto> getAllUserFriends(@PathVariable long userId) throws UserNotFoundException {
        return userService.getAllUserFriends(userId);
    }

    @PostMapping("/{userId}/friends")
    public void addUserFriend(@PathVariable long userId, @RequestBody long friendId) throws UserNotFoundException {
        userService.addUserFriend(userId, friendId);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto).orElseThrow();
    }
}
