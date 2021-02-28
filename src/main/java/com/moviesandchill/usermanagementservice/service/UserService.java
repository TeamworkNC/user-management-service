package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    void deleteAllUsers();

    Optional<UserDto> getUserById(long userId);

    UserDto addUser(NewUserDto user);

    void deleteUser(long userId);

    List<UserDto> getAllUserFriends(long userId);

    void addUserFriend(long userId, long friendId);

    Optional<UserDto> login(LoginRequestDto loginRequestDto);
}
