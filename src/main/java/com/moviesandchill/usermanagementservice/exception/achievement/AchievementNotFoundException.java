package com.moviesandchill.usermanagementservice.exception.achievement;

public class AchievementNotFoundException extends Exception {
    public AchievementNotFoundException(long achievementId) {
        super("achievement with id " + achievementId + " not found");
    }
}
