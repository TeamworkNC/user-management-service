package com.moviesandchill.usermanagementservice.exception.achievement;

public class AchievementNotFoundException extends Exception {
    public AchievementNotFoundException() {
    }

    public AchievementNotFoundException(String message) {
        super(message);
    }

    public AchievementNotFoundException(Throwable cause) {
        super(cause);
    }
}
