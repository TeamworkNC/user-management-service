package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.mapper.UserMapper;
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
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.mapToDto(users);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<UserDto> getUserById(long userId) {
        var user = userRepository.findById(userId);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto addUser(NewUserDto dto) {
        var user = userMapper.mapToUser(dto);
        user = userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUserFriends(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        var friends = new ArrayList<>(user.getFriends());
        return userMapper.mapToDto(friends);
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
