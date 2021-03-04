package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.entity.Achievement;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.AchievementMapper;
import com.moviesandchill.usermanagementservice.repository.AchievementRepository;
import com.moviesandchill.usermanagementservice.service.AchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;

    public AchievementServiceImpl(AchievementRepository achievementRepository, AchievementMapper achievementMapper) {
        this.achievementRepository = achievementRepository;
        this.achievementMapper = achievementMapper;
    }

    @Override
    public List<AchievementDto> getAllAchievements() {
        var achievements = achievementRepository.findAll();
        return achievementMapper.mapToDto(achievements);
    }

    @Override
    public void deleteAllAchievements() {
        achievementRepository.deleteAll();
    }

    @Override
    public AchievementDto getAchievementById(long achievementId) throws AchievementNotFoundException {
        Achievement achievement = achievementRepository.
                findById(achievementId)
                .orElseThrow(AchievementNotFoundException::new);

        return achievementMapper.mapToDto(achievement);
    }

    @Override
    public AchievementDto addAchievement(NewAchievementDto newAchievementDto) {
        Achievement achievement = achievementMapper.mapToEntity(newAchievementDto);
        achievement = achievementRepository.save(achievement);
        return achievementMapper.mapToDto(achievement);
    }

    @Override
    public void deleteAchievement(long achievementId) {
        achievementRepository.deleteById(achievementId);
    }
}
