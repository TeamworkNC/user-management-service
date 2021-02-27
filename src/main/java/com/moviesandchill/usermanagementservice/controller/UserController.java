package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
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
    public UserDto getUserById(@PathVariable long userId) {
        return userService.getUserById(userId).orElseThrow();
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
    public List<UserDto> getAllUserFriends(@PathVariable long userId) {
        return userService.getAllUserFriends(userId);
    }

    @PostMapping("/{userId}/friends")
    public void addUserFriend(@PathVariable long userId, @RequestBody long friendId) {
        userService.addUserFriend(userId, friendId);
    }

    @PostMapping("/{userId}/check_password")
    public boolean getUserPasswordHash(@PathVariable long userId, @RequestBody String password) {
        return userService.checkPassword(userId, password);
    }
}
