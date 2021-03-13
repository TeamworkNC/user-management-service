package com.moviesandchill.usermanagementservice.exception.staff;

public class StaffNotFoundException extends Exception {
    public StaffNotFoundException() {
    }

    public StaffNotFoundException(long staffId) {
        super("staff with id: " + staffId + " not found");
    }

    public StaffNotFoundException(String message) {
        super(message);
    }
}
