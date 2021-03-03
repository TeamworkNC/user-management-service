package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    void deleteAllUsers();

    UserDto getUserById(long userId) throws UserNotFoundException;

    UserDto addUser(NewUserDto user);

    void deleteUser(long userId);

    List<UserDto> getAllUserFriends(long userId) throws UserNotFoundException;

    void addUserFriend(long userId, long friendId) throws UserNotFoundException;

    List<AchievementDto> getAllUserAchievements(long userId) throws UserNotFoundException;

    void addUserAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException;

    void deleteUserAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException;

    Optional<UserDto> login(LoginRequestDto loginRequestDto);
}
