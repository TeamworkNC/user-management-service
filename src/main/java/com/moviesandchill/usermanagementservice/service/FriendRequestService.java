package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;
import com.moviesandchill.usermanagementservice.dto.friendrequest.NewFriendRequestDto;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequestDto> getAllFriendRequests();

    FriendRequestDto addFriendRequest(NewFriendRequestDto newFriendRequestDto);

    void deleteAllFriendRequests();
}
