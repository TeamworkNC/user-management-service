package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;

import java.util.List;

public interface UserFriendRequestService {
    List<FriendRequestDto> getAllFriendRequests(long userId);

    void acceptFriendRequest(long userId, long friendRequestId);

    void declineFriendRequest(long userId, long friendRequestId);
}
