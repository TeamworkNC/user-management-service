package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.entity.Achievement;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.AchievementMapper;
import com.moviesandchill.usermanagementservice.repository.AchievementRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserAchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;

    public UserAchievementService(AchievementRepository achievementRepository, UserRepository userRepository, AchievementMapper achievementMapper) {
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
        this.achievementMapper = achievementMapper;
    }


    public List<AchievementDto> getAllAchievements(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        List<Achievement> achievements = new ArrayList<>(user.getAchievements());
        return achievementMapper.mapToDto(achievements);
    }

    public void addAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException {
        User user = findUserById(userId);
        Achievement achievement = findAchievementById(achievementId);
        user.getAchievements().add(achievement);
    }

    public void deleteAllAchievements(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getAchievements().clear();
    }

    public void deleteAchievement(long userId, long achievementId) throws AchievementNotFoundException, UserNotFoundException {
        User user = findUserById(userId);
        Achievement achievement = findAchievementById(achievementId);
        user.getAchievements().remove(achievement);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Achievement findAchievementById(long achievementId) throws AchievementNotFoundException {
        return achievementRepository.findById(achievementId).orElseThrow(AchievementNotFoundException::new);
    }
}
