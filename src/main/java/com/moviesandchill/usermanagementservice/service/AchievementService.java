package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.UpdateAchievementDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;

import java.util.List;

public interface AchievementService {
    List<AchievementDto> getAllAchievements();

    void deleteAllAchievements();

    AchievementDto getAchievementById(long achievementId) throws AchievementNotFoundException;

    AchievementDto addAchievement(NewAchievementDto newAchievementDto);

    void updateAchievement(long achievementId, UpdateAchievementDto updateAchievementDto) throws AchievementNotFoundException;

    void deleteAchievement(long achievementId);
}
