package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
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
        List<User> users = userService.getAllUsers();
        return userMapper.mapToDto(users);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId).orElseThrow();
        return userMapper.mapToDto(user);
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        User user = userMapper.mapToUser(newUserDto);
        user = userService.addUser(user);
        return userMapper.mapToDto(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
