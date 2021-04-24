package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.UpdateAchievementDto;
import com.moviesandchill.usermanagementservice.entity.Achievement;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.AchievementMapper;
import com.moviesandchill.usermanagementservice.repository.AchievementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;

    public AchievementService(AchievementRepository achievementRepository, AchievementMapper achievementMapper) {
        this.achievementRepository = achievementRepository;
        this.achievementMapper = achievementMapper;
    }

    public List<AchievementDto> getAllAchievements() {
        var achievements = achievementRepository.findAll();
        return achievementMapper.mapToDto(achievements);
    }

    public void deleteAllAchievements() {
        achievementRepository.deleteAll();
    }

    public AchievementDto getAchievement(long achievementId) throws AchievementNotFoundException {
        Achievement achievement = findAchievementById(achievementId);

        return achievementMapper.mapToDto(achievement);
    }

    public AchievementDto addAchievement(NewAchievementDto newAchievementDto) {
        Achievement achievement = achievementMapper.mapToEntity(newAchievementDto);
        achievement = achievementRepository.save(achievement);
        return achievementMapper.mapToDto(achievement);
    }

    public void updateAchievement(long achievementId, UpdateAchievementDto updateAchievementDto) throws AchievementNotFoundException {
        Achievement achievement = findAchievementById(achievementId);
        achievementMapper.updateEntity(achievement, updateAchievementDto);
    }

    public void deleteAchievement(long achievementId) {
        achievementRepository.deleteById(achievementId);
    }

    private Achievement findAchievementById(long achievementId) throws AchievementNotFoundException {
        return achievementRepository.findById(achievementId)
                .orElseThrow(() -> new AchievementNotFoundException(achievementId));
    }
}
