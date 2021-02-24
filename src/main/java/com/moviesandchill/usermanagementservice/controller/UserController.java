package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId).orElseThrow();
        return modelMapper.map(user, UserDto.class);
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        user = userService.addUser(user);
        return modelMapper.map(user, UserDto.class);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
