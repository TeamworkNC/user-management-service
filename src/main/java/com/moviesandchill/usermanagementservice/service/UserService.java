package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UpdateUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto addUser(NewUserDto user);

    void deleteAllUsers();

    UserDto getUser(long userId) throws UserNotFoundException;

    void updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException;

    void deleteUser(long userId);

    boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException;

    Optional<UserDto> login(LoginRequestDto loginRequestDto);

}
