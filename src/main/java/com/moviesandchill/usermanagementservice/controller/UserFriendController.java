package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.impl.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/friends",
        produces = "application/json"
)
@Slf4j
public class UserFriendController {

    private final UserFriendService userFriendService;

    public UserFriendController(UserFriendService userFriendService) {
        this.userFriendService = userFriendService;
    }

    @GetMapping()
    public List<UserDto> getAllFriends(@PathVariable long userId) throws UserNotFoundException {
        return userFriendService.getAllFriends(userId);
    }

    @PostMapping()
    public void addFriend(@PathVariable long userId, @RequestBody long friendId) throws UserNotFoundException {
        userFriendService.addFriend(userId, friendId);
    }

    @DeleteMapping()
    public void deleteAllFriends(@PathVariable long userId) throws UserNotFoundException {
        userFriendService.deleteAllFriends(userId);
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriend(@PathVariable long userId, @PathVariable long friendId) throws UserNotFoundException {
        userFriendService.deleteFriend(userId, friendId);
    }
}
