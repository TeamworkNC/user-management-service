package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.staff.StaffDto;
import com.moviesandchill.usermanagementservice.exception.staff.StaffNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.impl.UserFavoriteStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/favorite-staffs",
        produces = "application/json"
)
@Slf4j
public class UserFavoriteStaffController {
    private final UserFavoriteStaffService userFavoriteStaffService;

    public UserFavoriteStaffController(UserFavoriteStaffService userFavoriteStaffService) {
        this.userFavoriteStaffService = userFavoriteStaffService;
    }

    @GetMapping
    List<StaffDto> getAllFavoriteStaffs(@PathVariable long userId) throws UserNotFoundException {
        return userFavoriteStaffService.getAllFavoriteStaffs(userId);
    }

    @PostMapping
    void addFavoriteStaff(@PathVariable long userId, @RequestBody long staffId) throws UserNotFoundException {
        userFavoriteStaffService.addFavoriteStaff(userId, staffId);
    }

    @DeleteMapping
    void deleteAllFavoriteStaffs(@PathVariable long userId) throws UserNotFoundException {
        userFavoriteStaffService.deleteAllFavoriteStaffs(userId);
    }

    @DeleteMapping("/{staffId}")
    void deleteFavoriteStaff(@PathVariable long userId, @PathVariable long staffId) throws UserNotFoundException, StaffNotFoundException {
        userFavoriteStaffService.deleteFavoriteStaff(userId, staffId);
    }

}
