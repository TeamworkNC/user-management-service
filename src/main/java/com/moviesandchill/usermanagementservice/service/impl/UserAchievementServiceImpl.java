package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.entity.Achievement;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.AchievementMapper;
import com.moviesandchill.usermanagementservice.repository.AchievementRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserAchievementService;
import com.moviesandchill.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserAchievementServiceImpl implements UserAchievementService {

    private final UserService userService;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;

    public UserAchievementServiceImpl(UserService userService, AchievementRepository achievementRepository, UserRepository userRepository, AchievementMapper achievementMapper) {
        this.userService = userService;
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
        this.achievementMapper = achievementMapper;
    }

    @Override
    public List<AchievementDto> getAllAchievements(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Achievement> achievements = new ArrayList<>(user.getAchievements());
        return achievementMapper.mapToDto(achievements);
    }

    @Override
    public void addAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Achievement achievement = achievementRepository.findById(achievementId).orElseThrow(AchievementNotFoundException::new);
        user.getAchievements().add(achievement);
    }

    @Override
    public void deleteAllAchievements(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.getAchievements().clear();
    }

    @Override
    public void deleteAchievement(long userId, long achievementId) throws AchievementNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Achievement achievement = achievementRepository.findById(achievementId).orElseThrow(AchievementNotFoundException::new);
        user.getAchievements().remove(achievement);
    }
}
