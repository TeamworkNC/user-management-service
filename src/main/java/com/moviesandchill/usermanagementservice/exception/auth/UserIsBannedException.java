package com.moviesandchill.usermanagementservice.exception.auth;

public class UserIsBannedException extends Exception {
    public UserIsBannedException(long userId) {
        super("user with id " + userId + " is banned");
    }
}
