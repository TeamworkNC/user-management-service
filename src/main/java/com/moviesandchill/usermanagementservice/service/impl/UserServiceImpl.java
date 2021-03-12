package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UpdateUserDto;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
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
    public UserDto addUser(NewUserDto dto) {
        User user = userMapper.mapToEntity(dto);
        UserPassword userPassword = new UserPassword();

        String passwordHash = passwordEncoder.encode(dto.getPassword());
        userPassword.setPasswordHash(passwordHash);

        user.setPassword(userPassword);
        userPassword.setUser(user);

        user = userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public UserDto getUser(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDto(user);
    }

    @Override
    public void updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userMapper.updateEntity(user, updateUserDto);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException {
        String oldPassword = updatePasswordDto.getOldPassword();
        String newPassword = updatePasswordDto.getNewPassword();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(oldPassword, user.getPassword().getPasswordHash())) {
            String newPasswordHash = passwordEncoder.encode(newPassword);
            user.getPassword().setPasswordHash(newPasswordHash);
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String login = loginRequestDto.getLogin();
        String password = loginRequestDto.getPassword();

        var userOptional = userRepository.findByLogin(login);

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
