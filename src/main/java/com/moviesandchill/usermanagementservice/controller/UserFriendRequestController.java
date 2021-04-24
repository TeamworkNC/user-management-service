package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;
import com.moviesandchill.usermanagementservice.service.UserFriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/friend_requests",
        produces = "application/json"
)
@Slf4j
public class UserFriendRequestController {
    private final UserFriendRequestService userFriendRequestService;

    public UserFriendRequestController(UserFriendRequestService userFriendRequestService) {
        this.userFriendRequestService = userFriendRequestService;
    }

    @GetMapping
    List<FriendRequestDto> getAllFriendRequests(@PathVariable long userId) {
        return userFriendRequestService.getAllFriendRequests(userId);
    }

    @PostMapping("/{friendRequestId}/accept")
    void acceptFriendRequest(@PathVariable long userId, @PathVariable long friendRequestId) {
        userFriendRequestService.acceptFriendRequest(userId, friendRequestId);
    }

    @PostMapping("/{friendRequestId}/decline")
    void declineFriendRequest(@PathVariable long userId, @PathVariable long friendRequestId) {
        userFriendRequestService.declineFriendRequest(userId, friendRequestId);
    }
}
