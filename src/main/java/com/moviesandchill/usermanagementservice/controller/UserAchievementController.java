package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.impl.UserAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/achievements",
        produces = "application/json"
)
@Slf4j
public class UserAchievementController {

    private final UserAchievementService userAchievementService;


    public UserAchievementController(UserAchievementService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @GetMapping
    List<AchievementDto> getAllAchievements(@PathVariable long userId) throws UserNotFoundException {
        return userAchievementService.getAllAchievements(userId);
    }

    @PostMapping
    void addAchievement(@PathVariable long userId, @RequestBody long achievementId) throws UserNotFoundException, AchievementNotFoundException {
        userAchievementService.addAchievement(userId, achievementId);
    }

    @DeleteMapping
    void deleteAllAchievements(@PathVariable long userId) throws UserNotFoundException {
        userAchievementService.deleteAllAchievements(userId);
    }

    @DeleteMapping("/{achievementId}")
    void deleteAchievement(@PathVariable long userId, @PathVariable long achievementId) throws AchievementNotFoundException, UserNotFoundException {
        userAchievementService.deleteAchievement(userId, achievementId);
    }
}
