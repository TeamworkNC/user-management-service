package com.moviesandchill.usermanagementservice.exception.auth;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException() {
        super("Password mismatch!");
    }
}
