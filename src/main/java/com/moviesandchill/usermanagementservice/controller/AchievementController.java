package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.UpdateAchievementDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.service.impl.AchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/achievements",
        produces = "application/json"
)
@Slf4j
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    private List<AchievementDto> getAllAchievements() {
        return achievementService.getAllAchievements();
    }

    @PostMapping
    private AchievementDto addAchievement(@RequestBody NewAchievementDto newAchievementDto) {
        return achievementService.addAchievement(newAchievementDto);
    }

    @DeleteMapping
    private void deleteAllAchievements() {
        achievementService.deleteAllAchievements();
    }

    @GetMapping("/{achievementId}")
    private AchievementDto getAchievement(@PathVariable long achievementId) throws AchievementNotFoundException {
        return achievementService.getAchievement(achievementId);
    }

    @PutMapping("/{achievementId}")
    public void updateAchievement(@PathVariable long achievementId, @RequestBody UpdateAchievementDto updateAchievementDto) throws AchievementNotFoundException {
        achievementService.updateAchievement(achievementId, updateAchievementDto);
    }

    @DeleteMapping("/{achievementId}")
    private void deleteAchievement(@PathVariable long achievementId) throws AchievementNotFoundException {
        achievementService.deleteAchievement(achievementId);
    }
}
