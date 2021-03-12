package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserFriendService {
    List<UserDto> getAllFriends(long userId) throws UserNotFoundException;

    void addFriend(long userId, long friendId) throws UserNotFoundException;

    void deleteAllFriends(long userId) throws UserNotFoundException;

    void deleteFriend(long userId, long friendId) throws UserNotFoundException;
}
