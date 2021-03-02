package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.entity.UserPassword;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
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
    public UserDto getUserById(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto addUser(NewUserDto dto) {
        User user = userMapper.mapToUser(dto);
        UserPassword userPassword = new UserPassword();

        String passwordHash = passwordEncoder.encode(dto.getPassword());
        userPassword.setPasswordHash(passwordHash);

        user.setPassword(userPassword);
        userPassword.setUser(user);

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
    public void addUserFriend(long userId, long friendId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User friend = userRepository.findById(friendId).orElseThrow(UserNotFoundException::new);
        user.getFriends().add(friend);
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String name = loginRequestDto.getName();
        String password = loginRequestDto.getPassword();

        var userOptional = userRepository.findByName(name);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        String hash = user.getPassword().getPasswordHash();

        if (passwordEncoder.matches(password, hash)) {
            UserDto dto = userMapper.mapToDto(user);
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
