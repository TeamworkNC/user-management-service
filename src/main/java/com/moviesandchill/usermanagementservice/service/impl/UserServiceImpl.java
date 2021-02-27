package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUserFriends(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return new ArrayList<>(user.getFriends());
    }

    @Override
    public void addUserFriend(long userId, long friendId) {
        User user = userRepository.findById(userId).orElseThrow();
        User friend = userRepository.findById(friendId).orElseThrow();
        user.getFriends().add(friend);
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow();
        String hash = user.getPassword().getPasswordHash();
        return passwordEncoder.matches(password, hash);
    }
}
