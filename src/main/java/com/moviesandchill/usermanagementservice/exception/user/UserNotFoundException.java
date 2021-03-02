package com.moviesandchill.usermanagementservice.exception.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
