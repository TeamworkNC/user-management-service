package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(long userId);

    User addUser(User user);

    void deleteUser(long userId);
}
