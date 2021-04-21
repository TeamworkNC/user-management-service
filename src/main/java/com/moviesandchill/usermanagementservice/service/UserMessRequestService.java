package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.messrequest.MessRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.messrequest.MessRequestNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserMessRequestService {

    UserDto getUserByMessRequest(long messRequestId);

    UserDto getFriendByMessRequest(long messRequestId);

    List<MessRequestDto> getAllMessRequests(long userId) throws UserNotFoundException;

    void addMessRequest(long userId,long friendId, long messRequestId) throws UserNotFoundException;

    void deleteAllMessRequests(long userId) throws UserNotFoundException;

    void deleteMessRequest(long userId, long messRequestId) throws UserNotFoundException, MessRequestNotFoundException;
}
