package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;
import com.moviesandchill.usermanagementservice.dto.friendrequest.NewFriendRequestDto;
import com.moviesandchill.usermanagementservice.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/friend_requests",
        produces = "application/json"
)
@Slf4j
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping
    List<FriendRequestDto> getAllFriendRequests() {
        return friendRequestService.getAllFriendRequests();
    }

    @PostMapping
    FriendRequestDto addFriendRequest(@RequestBody NewFriendRequestDto newFriendRequestDto) {
        return friendRequestService.addFriendRequest(newFriendRequestDto);
    }

    @DeleteMapping
    void deleteAllFriendRequests() {
        friendRequestService.deleteAllFriendRequests();
    }
}
