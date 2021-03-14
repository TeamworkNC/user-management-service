package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserAchievementService {
    List<AchievementDto> getAllAchievements(long userId) throws UserNotFoundException;

    void addAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException;

    void deleteAllAchievements(long userId) throws UserNotFoundException;

    void deleteAchievement(long userId, long achievementId) throws AchievementNotFoundException, UserNotFoundException;
}
